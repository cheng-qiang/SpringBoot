package com.chen.thylealeaf.security.mapper;

import com.chen.thylealeaf.security.model.security.Role;
import com.chen.thylealeaf.security.model.security.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 程强
 * @date 2020年04月23日 10:57
 * @Description:
 */
@Mapper
public interface UserMapper {
    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    User myLoadUserByUsername(@Param("username") String username);

    /**
     * 根据用户id查询用户角色
     * @param id
     * @return
     */
    List<Role> getUserRolesById(@Param("id") Integer id);
}
