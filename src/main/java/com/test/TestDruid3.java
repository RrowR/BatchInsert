package com.test;

import com.test.utils.DruidUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: Rrow
 * @date: 2022/2/23 12:18
 */
public class TestDruid3 {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(TestDruid3.class);
        ExecutorService threadPool = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 100; i++) {
            threadPool.submit(() -> {
                while (true) {
                    try {
                        int num = 1;
                        long start = System.currentTimeMillis();
                        Connection connection = DruidUtils.getConnection();
                        long end = System.currentTimeMillis();
                        connection.setAutoCommit(false);
                        int time = (int) Math.ceil(Math.random() * 5000);
                        String strTime = String.valueOf(time);
                        String sql = "insert into user(name, hobby, time) VALUE (?,?,?)";
                        PreparedStatement preparedStatement = connection.prepareStatement(sql);
                        preparedStatement.setString(num++, "猫羽雫");
                        preparedStatement.setString(num++, "sleep");
                        preparedStatement.setString(num++, strTime);
                        boolean flag = preparedStatement.execute();
                        connection.commit();
                        logger.info("数据库执行{}", flag);
                        logger.info("获得连接{}", connection);
                        logger.info("线程休息时间为{}", time);
                        logger.info("获得链接的时间为{}", end - start);
                        Thread.sleep(time);
                        DruidUtils.closeAll(connection);
                        logger.info("数据库连接已经关闭{}", System.currentTimeMillis());
                    } catch (Exception e) {
                        e.printStackTrace();
                        logger.error("出现了异常" + e.getMessage());
                    }
                }
            });
        }

    }
}
