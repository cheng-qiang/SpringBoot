package com.chen.thylealeaf.security.dao;

import com.chen.thylealeaf.security.model.jpa.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 程强
 * @date 2020年04月23日 19:33
 * @Description:
 */
@Repository
public interface UserDao extends JpaRepository<User,Long> {
    /**
     * 用户名查找用户
     * @param username
     * @return
     */
    User findUserByUsername(String username);
}
