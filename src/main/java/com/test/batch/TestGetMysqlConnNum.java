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
public class TestGetMysqlConnNum {
    public static void main(String[] args) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long start = System.currentTimeMillis();
        Connection conn = null;
        new Thread(() -> {
            while (true) {
                try {
                    DruidPooledConnection connection = DruidUtils.getConnection();
                    Thread.sleep(3000);
                    System.out.println("Thread 1 get one Connection");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        // conn = DruidUtils.getConnection();
        // Connection finalConn = conn;
        // new Thread(() -> {
        //     try {
        //         while (true) {
        //             Thread.sleep(4000);
        //             String sql = "SHOW FULL PROCESSLIST";
        //             PreparedStatement ps = finalConn.prepareStatement(sql);
        //             ResultSet resultSet = ps.executeQuery(sql);
        //             System.out.println("resultSet.getRow() = " + resultSet.getRow());
        //             // while (resultSet.next()) {
        //             //     System.out.println("有...");
        //             // }
        //             // int row = resultSet.getStatement().getMaxRows();
        //             // System.out.println("thread 2 query row = " + row);
        //         }
        //     } catch (SQLException e) {
        //         e.printStackTrace();
        //     } catch (InterruptedException e) {
        //         e.printStackTrace();
        //     }
        // }).start();


    }
}
