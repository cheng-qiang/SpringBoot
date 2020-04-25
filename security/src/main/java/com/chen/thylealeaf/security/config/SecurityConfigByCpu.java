package com.chen.thylealeaf.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @author 程强
 * @date 2020年04月23日 16:29
 * @Description:基于InMemoryUserDetailsManager内存的用户数据源配置！
 */
// @Configuration
public class SecurityConfigByCpu extends WebSecurityConfigurerAdapter {

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("yiwannuofulasi@163.com").password("admin").roles("admin").build());
        manager.createUser(User.withUsername("言少钱").password("user").roles("user").build());
        return manager;
    }
}
