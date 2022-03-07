package com.test;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.test.utils.DbUtil;
import com.test.utils.DruidUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author: Rrow
 * @Date: 2022/3/5 9:03 下午
 */
public class TestFalseAutoCommitToSearch {
    public static void main(String[] args) throws SQLException {
        DruidPooledConnection connection = DbUtil.getDruidConnection();
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement = connection.prepareStatement("select * from user where id=1");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            System.out.println("resultSet.getObject(1) = " + resultSet.getObject(1));
            System.out.println("resultSet.getObject(2) = " + resultSet.getObject(2));
            System.out.println("resultSet.getObject(3) = " + resultSet.getObject(3));
            System.out.println("resultSet.getObject(3) = " + resultSet.getObject(4));
        }
        DruidUtils.closeAll(connection);
    }
}
