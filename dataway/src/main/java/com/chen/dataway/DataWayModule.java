package com.chen.dataway;

import net.hasor.core.ApiBinder;
import net.hasor.core.DimModule;
import net.hasor.db.JdbcModule;
import net.hasor.db.Level;
import net.hasor.spring.SpringModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * @author 言少钱
 * @date 2020年05月03日 16:25
 * @GitHub： https://github.com/cheng-qiang
 * @参考资料：
 * @Description:把数据源设置到 Hasor 容器中
 */
@Component
@DimModule
public class DataWayModule implements SpringModule {

    @Autowired
    private DataSource dataSource = null;

    @Override
    public void loadModule(ApiBinder apiBinder) throws Throwable {
        apiBinder.installModule(new JdbcModule(Level.Full, this.dataSource));
    }
}
