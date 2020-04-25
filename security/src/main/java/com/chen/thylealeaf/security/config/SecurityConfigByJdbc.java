package com.chen.thylealeaf.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

/**
 * @author 程强
 * @date 2020年04月23日 17:16
 * @Description:基于JdbcUserDetailsManager数据库的数据源配置，通过JdbcTemplate在数据库中创建用户！
 * 每次启动都会执行,避免重复创建相同用户,所以加判断！
 */
// @Configuration
public class SecurityConfigByJdbc extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
        if (!manager.userExists("yiwannuofulasi@163.com")){
            manager.createUser(User.withUsername("yiwannuofulasi@163.com").password("admin").roles("admin").build());
        }
        if (!manager.userExists("言少钱")){
            manager.createUser(User.withUsername("言少钱").password("user").roles("user").build());
        }
        return manager;
    }
}
