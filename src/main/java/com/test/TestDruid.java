package com.test;

import com.test.utils.DruidUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;

/**
 * @author: Rrow
 * @date: 2022/2/23 12:18
 */
public class TestDruid {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(TestDruid.class);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        Connection connection = DruidUtils.getConnection();
                        logger.info(connection.toString());
                        DruidUtils.closeAll(connection);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }


    }
}
