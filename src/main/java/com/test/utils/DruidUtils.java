package com.test.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author: Rrow
 * @date: 2022/2/18 23:14
 */
public class DruidUtils {
    //Druid德鲁伊,据说是魔兽世界中的一个角色,森林女神
    private static DruidDataSource dataSource;
    private static Logger logger = LoggerFactory.getLogger(DruidUtils.class);

    //1.初始化Druid连接池
    static {
        //第二种方式:使用软编码通过配置文件初始化DBCP
        try {
            Properties properties = new Properties();
            //通过类加载器加载配置文件
            InputStream inputStream = DruidUtils.class.getClassLoader().getResourceAsStream(
                    "druid.properties");
            properties.load(inputStream);
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
            dataSource.init();
            logger.info("静态代码块初始化了数据源");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //获取连接
    public static DruidPooledConnection getConnection() {
        try {
            logger.info("getDataSource().getActiveCount() = " + getDataSource().getActiveCount());
            DruidPooledConnection connection = dataSource.getConnection();
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static DruidDataSource getDataSource(){
        return  dataSource;
    }
    //关闭连接
    public static void closeAll(DruidPooledConnection conn) {
        try {
           if (conn != null){
               conn.close();
           }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeAll(Connection conn) {
        try {
           if (conn != null){
               conn.close();
           }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
