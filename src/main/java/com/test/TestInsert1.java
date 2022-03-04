package com.test;

import com.test.utils.DruidUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Author: Rrow
 * @Date: 2022/3/4 2:43 下午
 */
public class TestInsert1 {
    public static void main(String[] args) throws SQLException {
        Connection connection = DruidUtils.getConnection();
        connection.setAutoCommit(false);
        int time = (int) Math.ceil(Math.random() * 5000);
        String strTime = String.valueOf(time);
        System.out.println(time);
        String sql = "insert into user(name, hobby, time) VALUE (?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        int i = 0;
        preparedStatement.setString(++i,"猫羽雫");
        preparedStatement.setString(++i,"sleep");
        preparedStatement.setString(++i,strTime);
        boolean flag = preparedStatement.execute();
        System.out.println(flag);
        connection.commit();
        DruidUtils.closeAll(connection);
    }
}
