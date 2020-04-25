package com.chen.thylealeaf.security.config;

import com.chen.thylealeaf.security.model.security.User;
import com.chen.thylealeaf.security.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.PrintWriter;

/**
 * @author 程强
 * @date 2020年04月23日 11:01
 * @Description:Spring security配置
 * @江南一点雨：https://mp.weixin.qq.com/s/VWJvINbi1DB3fF-Mcx7mGg
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userService;

    /**
     * 用户管理构建配置：自定义或者从数据库获取
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*
        * 自定义用户,会覆盖在配置文件中配置的用户名和密码！
        * */
        // auth.inMemoryAuthentication()
        //         .withUser("yiwannuofulasi@163.com")
        //         .password("admin")
        //         .roles("admin")
        //         .and()
        //         .withUser("言少钱")
        //         .password("user")
        //         .roles("user");
        /** 动态数据用户：任何实现了userDetailsService的对象都可以作为认证的数据源 */
        auth.userDetailsService(userService);
    }



    @Override
    public void configure(WebSecurity web) throws Exception {
        /*
        * 将不走spring security filter过滤器的接口或者资源放在这里！
        * */
        web.ignoring().antMatchers("/login","/js/**","/css/**","/images/**","/login.html");
    }

    /**
     * Spring Security 5 之前可以不强制加密如下配置即可
     * @return
     */
    /*@Bean
    PasswordEncoder notPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }*/

    /**
     * 不可逆加密：通常加密使用不可逆,通信时用可逆！
     * @return
     */
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * 角色继承机制配置
     * @return
     */
    @Bean
    RoleHierarchy roleHierarchy(){
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        roleHierarchy.setHierarchy("ROLE_admin > ROLE_dba");
        return roleHierarchy;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*开启授权请求配置*/
        http.authorizeRequests()
                /*配置拦截规则*/
                .antMatchers("/root/**").hasAnyRole("dba","admin")
                .antMatchers("/admin/**").hasRole("admin")
                .antMatchers("/song/**").hasRole("user")
                /*除此之外任何请求认证就能访问,注意顺序且不能放在authorizeRequests()后面,启动会报异常！*/
                .anyRequest().authenticated()
                .and()
                /*表单登录配置*/
                .formLogin()
                /**
                 * 登录页面地址:这里如果没有额外配置loginProcessingUrl,默认'/login'也作为登录接口地址！
                 */
                .loginPage("/login.html")
                /*登录接口地址*/
                .loginProcessingUrl("/doLogin")
                /*配置用户名密码时和登录页name属性保持一致即可*/
                .usernameParameter("username")
                .passwordParameter("password")
                /*默认登录成功跳转路径*/
                .defaultSuccessUrl("/root/toHello")
                .successHandler((req,resp,authentication)->{
                    resp.setContentType("application/json;charset=utf-8");
                    PrintWriter out = resp.getWriter();
                    User user = (User) authentication.getPrincipal();
                    user.setPassword(null);
                    out.write(new ObjectMapper().writeValueAsString(user));
                    out.flush();
                    out.close();
                })
                /*和登录相关的通通放行*/
                .permitAll()
                .and()
                .csrf().disable();
    }
}
