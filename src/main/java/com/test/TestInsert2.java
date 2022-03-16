package com.test;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.test.utils.DruidUtils;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Author: Rrow
 * @Date: 2022/3/15 3:34 下午
 */
public class TestInsert2 {
    public static void main(String[] args) throws SQLException {
        DruidPooledConnection connection = DruidUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into testBase(num) values (?)");
        byte[] bytes = "hello".getBytes();
        preparedStatement.setByte(1,bytes[1]);
    }
}
