package com.gy.miaosha.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * 类作用:
 * 项目名称: miaoshaReal
 * 包: com.gy.miaosha.util
 * 类名称: DBUtil
 * 类描述: 类功能详细描述
 * 创建人: wuguang
 * 创建时间: 2019/04/11 14:55
 */
public class DBUtil {
    private static Properties properties;
    static {
        try{
            InputStream in = DBUtil.class.getClassLoader()
                    .getResourceAsStream("application.properties");
            properties = new Properties();
            properties.load(in);
            in.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Connection getConn() throws Exception{
        String url = properties.getProperty("spring.datasource.url");
        String username = properties.getProperty("spring.datasource.username");
        String password = properties.getProperty("spring.datasource.password");
        String driver = properties.getProperty("spring.datasource.driver-class-name");
        Class.forName(driver);
        return DriverManager.getConnection(url, username, password);
    }
}