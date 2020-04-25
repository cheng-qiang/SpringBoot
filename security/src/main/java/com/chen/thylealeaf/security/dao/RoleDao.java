package com.chen.thylealeaf.security.dao;

import com.chen.thylealeaf.security.model.jpa.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 言少钱
 * @date 2020年04月23日 20:14
 * @GitHub： https://github.com/cheng-qiang
 * @参考资料：
 * @Description:
 */
@Repository
public interface RoleDao extends JpaRepository<Role,Long> {

}
