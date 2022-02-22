package com.test.batch;

import com.test.utils.DruidUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author: Rrow
 * @date: 2022/2/18 23:13
 * 单线程一条一条插入            493 秒
 */
public class BatchInsert {
    public static void main(String[] args) throws SQLException {
        Connection connection = DruidUtils.getConnection();
        long start = System.currentTimeMillis();
        // connection.setAutoCommit(false);
        PreparedStatement preparedStatement = connection.prepareStatement("insert user(name,age,hobby) value (?,?,?)");
        boolean execute = false;
        for (int b = 0;b < 10000;b++) {
            int i = 0;
            preparedStatement.setString(++i,"猫羽雫"+b);
            preparedStatement.setString(++i,"1"+b);
            preparedStatement.setString(++i,"sleep"+b);
            System.out.println("preparedStatement.executeUpdate() = " + preparedStatement.executeUpdate());
        }
        long end = System.currentTimeMillis();
        System.out.println((end - start)/1000);

    }
}
