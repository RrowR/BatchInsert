package com.test;

import com.test.utils.DruidUtils;

import java.sql.Connection;

/**
 * @author: Rrow
 * @date: 2022/2/18 23:13
 */
public class Batch {
    public static void main(String[] args) {
        Connection connection = DruidUtils.getConnection();

    }
}
