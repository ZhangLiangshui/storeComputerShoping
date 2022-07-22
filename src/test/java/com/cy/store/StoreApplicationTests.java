package com.cy.store;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
class StoreApplicationTests {
@Resource
    private DataSource dataSource;
    @Test
    void contextLoads() {
    }

    /**
     * HikariProxyConnection@746436902 wrapping com.mysql.cj.jdbc.ConnectionImpl@2577a95d
     * 数据库连接池
     * @throws SQLException
     */
    @Test
    void getConnection() throws SQLException {
    System.out.println(dataSource.getConnection());
}
}
