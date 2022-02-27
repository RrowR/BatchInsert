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
public class TestDruid2 {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(TestDruid2.class);
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 11; i++) {
            threadPool.submit(() -> {
                while (true) {
                    try {
                        Connection connection = DruidUtils.getConnection();
                        logger.info(connection.toString());
                        Thread.sleep(5000);
                        DruidUtils.closeAll(connection);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    }
}
