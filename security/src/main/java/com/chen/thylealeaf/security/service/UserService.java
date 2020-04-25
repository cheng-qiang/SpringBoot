package com.chen.thylealeaf.security.service;

import com.chen.thylealeaf.security.mapper.UserMapper;
import com.chen.thylealeaf.security.model.security.User;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author 程强
 * @date 2020年04月23日 10:59
 * @Description:
 */
@Service
public class UserService implements UserDetailsService {
    private static final Logger logger = getLogger(UserService.class);
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.myLoadUserByUsername(username);
        logger.info(username+"++++++++++++++++++++++++++++++++"+user);
        if (user == null){
            throw new UsernameNotFoundException("用户不存在!");
        }
        user.setRoles(userMapper.getUserRolesById(user.getId()));
        logger.info(user.toString());
        return user;
    }
}
