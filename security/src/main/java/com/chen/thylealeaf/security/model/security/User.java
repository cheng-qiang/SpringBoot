package com.chen.thylealeaf.security.model.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author 程强
 * @date 2020年04月23日 10:56
 * @Description:
 */
public class User implements UserDetails {
        private Integer id;
        private String username;
        private String password;
        private Boolean enabled;
        private Boolean locked;
        private List<Role> roles;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        @Override
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }


        @Override
        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setEnabled(Boolean enabled) {
            this.enabled = enabled;
        }


        public void setLocked(Boolean locked) {
            this.locked = locked;
        }

        public List<Role> getRoles() {
            return roles;
        }

        public void setRoles(List<Role> roles) {
            this.roles = roles;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            for (Role role:roles) {
                authorities.add(new SimpleGrantedAuthority(role.getName()));
            }
            return authorities;
        }

        @Override
        public boolean isAccountNonExpired() {
            /**
             * 此方法验证账户是否未过期,由于数据库中没有该字段,这里演示就直接返回true
             */
            return true;
        }


        @Override
        public boolean isAccountNonLocked() {
            /**
             * 此方法验证账户是否未锁定
             */
            return !locked;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            /**
             * 凭证（密码）是否未过期
             */
            return true;
        }

        @Override
        public boolean isEnabled() {
            /**
             * 是否可用
             */
            return enabled;
        }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", locked=" + locked +
                ", roles=" + roles +
                '}';
    }
}
