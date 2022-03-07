package com.test;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.test.utils.DruidUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.Time;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: Rrow
 * @date: 2022/2/23 12:18
 */
public class TestDruid {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(TestDruid.class);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 30, TimeUnit.SECONDS, new LinkedBlockingQueue<>(2), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < 10; i++) {
            Runnable runnable = (() -> {
                while (true) {
                    DruidPooledConnection connection = DruidUtils.getConnection();
                    DruidUtils.closeAll(connection);
                    logger.info("connection is returned");
                }
            });
            executor.submit(runnable);
        }


    }
}
