package com.chen.thylealeaf.security.service;

import com.chen.thylealeaf.security.dao.UserDao;
import com.chen.thylealeaf.security.model.jpa.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author 程强
 * @date 2020年04月23日 19:35
 * @Description:
 */
@Service
public class UserDaoService  implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findUserByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException("用户名不存在！");
        }
        return user;
    }
}
