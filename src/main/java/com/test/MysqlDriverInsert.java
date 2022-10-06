package com.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

/**
 * @author: Rrow
 * @date: 2022/10/7 0:14
 */
public class MysqlDriverInsert {
    public static void main(String[] args) throws ClassNotFoundException {
        HashMap<String, Integer> threadValue = new HashMap<>();
        Class.forName("com.mysql.cj.jdbc.Driver");
        for (int k = 0; k < 4; k++) {
            new Thread(() -> {
                try {
                    String name = Thread.currentThread().getName();
                    threadValue.put(name, 0);
                    for (; ; ) {
                        int i = 0;
                        Integer integer = threadValue.get(name);
                        String time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(System.currentTimeMillis());
                        Connection connection = DriverManager.getConnection("jdbc:mysql://ecs01:3306/testDelete?serverTimezone=UTC&rewriteBatchedStatements=true", "test", "test");
                        connection.setAutoCommit(false);
                        PreparedStatement preparedStatement = connection.prepareStatement("insert user(name,age,hobby) value (?,?,?)");
                        preparedStatement.setString(++i, name + "&" + integer + "&&" + time);
                        preparedStatement.setString(++i, name + "&" + integer + "&&" + time);
                        preparedStatement.setString(++i, name + "&" + integer + "&&" + time);
                        preparedStatement.executeUpdate();
                        connection.commit();
                        preparedStatement.close();
                        connection.close();
                        threadValue.put(name, ++integer);
                        Thread.sleep(10000);
                        System.out.println("name is " + name + " has finished at :" + time);
                    }
                } catch (InterruptedException | SQLException | RuntimeException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
