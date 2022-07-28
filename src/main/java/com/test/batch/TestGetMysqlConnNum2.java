package com.test.batch;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.test.utils.DruidUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

/**
 * @Author: Rrow
 * @Date: 2022/7/28 08:50
 */
public class TestGetMysqlConnNum2 {
    public static void main(String[] args) throws SQLException, InterruptedException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long start = System.currentTimeMillis();
        Connection conn = null;
        conn = DruidUtils.getConnection();
        Connection finalConn = conn;
        while (true) {
            Thread.sleep(4000);
            String sql = "SHOW FULL PROCESSLIST";
            PreparedStatement ps = finalConn.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery(sql);
            int i = 0;
            while (resultSet.next()) {
                i++;
            }
            System.out.println("Conn num is " + i);
        }
    }
}
