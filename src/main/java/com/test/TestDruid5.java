package com.test;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.test.utils.DruidUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: Rrow
 * @date: 2022/2/23 12:18
 */
public class TestDruid5 {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(TestDruid5.class);
        ExecutorService threadPool = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 100; i++) {
            threadPool.submit(() -> {
                while (true) {
                    try {
                        long start = System.currentTimeMillis();
                        Connection connection = DruidUtils.getConnection();
                        long end = System.currentTimeMillis();
                        logger.info(connection.toString());
                        Thread.sleep(5000);
                        DruidUtils.closeAll((DruidPooledConnection) connection);
                        logger.info("连接关闭,时间为{}", System.currentTimeMillis());
                        logger.info("获取连接执行时间为{}", end - start);
                    } catch (Exception e) {
                        logger.info("出现错误{}",e);
                    }
                }
            });
        }

    }
}
