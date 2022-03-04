package com.test;

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
public class TestDruid3 {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(TestDruid3.class);
        ExecutorService threadPool = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 100; i++) {
            threadPool.submit(() -> {
                while (true) {
                    try {
                        Connection connection = DruidUtils.getConnection();
                        int time = (int) Math.ceil(Math.random() * 5000);
                        logger.info("获得连接{}", connection);
                        logger.info("时间为{}",time);
                        Thread.sleep(time);
                        DruidUtils.closeAll(connection);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    }
}
