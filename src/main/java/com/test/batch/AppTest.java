package com.test.batch;

import com.test.utils.DbUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CREATE TABLE `auth_code` (
 * `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
 * `auth_code` varchar(255) CHARACTER SET utf8mb4 NOT NULL COMMENT '授权码',
 * `status` varchar(255) CHARACTER SET utf8mb4 NOT NULL DEFAULT '1' COMMENT '状态',
 * `remark` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '备注',
 * `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
 * `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改日期',
 * PRIMARY KEY (`id`)
 * ) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;
 */
public class AppTest {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static ExecutorService executorService = Executors.newCachedThreadPool();// 线程池

    public static void main(String[] args) throws Exception {
        String INSERT_SQL = "INSERT INTO `auth_code` " +
                "(`auth_code`, `status`, `remark`, `create_date`, `update_time`) VALUES ('%s',%s,'%s','%s','%s')";
        // 模拟多线程向数据库插入1万条数据
        for (int i = 0; i < 10000; i++) {
            Thread.sleep(5);
            String code = UUID.randomUUID().toString().replaceAll("-", "");
            String dateStr = dateFormat.format(new Date());
            String formatSql = String.format(INSERT_SQL, code, i, code, dateStr, dateStr);
            executorService.execute(() -> {
                try {
                    DbUtil.insert(formatSql);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }
}