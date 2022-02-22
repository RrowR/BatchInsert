package com.test.batch;

import com.alibaba.druid.util.JdbcUtils;
import com.test.utils.DruidUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author: Rrow
 * @date: 2022/2/18 23:13
 * 批量插入   10.445秒  batch = 500
 * 39.196秒 batch = 50
 * 7.831秒 batch = 1000
 * 6.53秒 batch = 5000
 * 6.526秒   batch = 20000
 */
public class BatchInsert2 {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Connection conn = null;
        PreparedStatement ps;
        try {
            // 1.获取数据库连接
            conn = DruidUtils.getConnection();
            // 2.设置为不自动提交数据
            conn.setAutoCommit(false);
            // 3.创建PreparedStatement实例
            String sql = "insert into user(name,age,hobby) values(?,?,?)";
            ps = conn.prepareStatement(sql);
            // 4.插入数据
            for (int i = 1; i <= 20000; i++) {
                ps.setString(1, "猫羽雫" + i);
                ps.setString(2, "1" + i);
                ps.setString(3, "hobby" + i);
                //每满500条数据执行一次更新
                ps.addBatch();
                if (i % 2000 == 0) {
                    // 执行批量更新
                    ps.executeBatch();
                    // 清空执行过的sql
                    ps.clearBatch();
                }
            }
            // 5.提交数据
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 6.关闭资源
            DruidUtils.closeAll(conn);
        }
        long end = System.currentTimeMillis();
        System.out.println("花费的时间为：" + (end - start));

    }
}
