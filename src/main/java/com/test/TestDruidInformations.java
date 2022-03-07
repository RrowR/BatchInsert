package com.test;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.test.utils.DbUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: Rrow
 * @date: 2022/3/7 18:39
 */
public class TestDruidInformations {
    public static void main(String[] args) throws SQLException, InterruptedException {
        Logger logger = LoggerFactory.getLogger(TestDruidInformations.class);
        ExecutorService pool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            pool.submit(() -> {
                while (true) {
                    try {
                        DruidPooledConnection connection = DbUtil.getDruidConnection();
                        try {
                            int num = 1;
                            connection.setAutoCommit(false);
                            int time = (int) Math.ceil(3000 + Math.random() * 10000);
                            String strTime = String.valueOf(time);
                            String sql = "insert into user(name, hobby, time) VALUE (?,?,?)";
                            PreparedStatement preparedStatement = connection.prepareStatement(sql);
                            preparedStatement.setString(num++, "猫羽雫");
                            preparedStatement.setString(num++, "sleep");
                            preparedStatement.setString(num++, strTime);
                            preparedStatement.execute();
                            connection.commit();
                            logger.info("我这个线程要睡眠" + time + "秒");
                            Thread.sleep(time);
                            DbUtil.closeResource(connection);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            });

        }


    }
}
