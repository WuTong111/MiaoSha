package com.gy.miaosha.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gy.miaosha.domain.MiaoshaUser;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 类作用:
 * 项目名称: miaoshaReal
 * 包: com.gy.miaosha.util
 * 类名称: UserUtil
 * 类描述: 类功能详细描述
 * 创建人: wuguang
 * 创建时间: 2019/04/11 14:43
 */
public class UserUtil {
    private static void createUser(int count) throws Exception{
        List<MiaoshaUser> users = new ArrayList<>(count);
        //生成用户
        for(int i=0;i<count;i++){
            MiaoshaUser user = new MiaoshaUser();
            user.setId(13000000000L+i)
                    .setLoginCount(1)
                    .setNickname("user" + i)
                    .setRegisterDate(new Date())
                    .setSalt("1a2b3c")
                    .setPassword(MD5Util.inputPassToDBPass("123456", user.getSalt()));
            users.add(user);
        }
        System.out.println("create user");
        //插入数据库
//        Connection conn = DBUtil.getConn();
//        String sql = "insert miaosha_user(login_count, nickname, register_date, salt, password, id) values(?,?,?,?,?,?)";
//        PreparedStatement pstmt = conn.prepareStatement(sql);
//        for(int i=0;i<users.size();i++){
//            MiaoshaUser user = users.get(i);
//            pstmt.setInt(1, user.getLoginCount());
//            pstmt.setString(2,user.getNickname());
//            pstmt.setTimestamp(3,new Timestamp(user.getRegisterDate().getTime()));
//            pstmt.setString(4,user.getSalt());
//            pstmt.setString(5,user.getPassword());
//            pstmt.setLong(6,user.getId());
//            pstmt.addBatch();
//        }
//        pstmt.executeBatch();
//        pstmt.close();
//        conn.close();
//        System.out.println("insert into db");
        //登陆，生成token
        String urlString = "http://localhost:8080/login/do_login";
        File file = new File("C:\\Project\\miaosha\\MiaoSha\\token.txt");
        if (file.exists()){
            file.delete();
        }
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        file.createNewFile();
        raf.seek(0);
        for(int i=0;i<users.size();i++){
            MiaoshaUser user = users.get(i);
            URL url = new URL(urlString);
            HttpURLConnection co = (HttpURLConnection) url.openConnection();
            co.setRequestMethod("POST");
            co.setDoOutput(true);
            OutputStream out = co.getOutputStream();
            String params = "mobile="+user.getId()+"&password="+MD5Util.inputPassToFromPass("123456");
            out.write(params.getBytes());
            out.flush();
            InputStream inputStream = co.getInputStream();
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            byte[] buff = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(buff)) >= 0){
                bout.write(buff, 0, len);
            }
            inputStream.close();
            bout.close();
            String response = new String(bout.toByteArray());
            JSONObject jsonObject = JSON.parseObject(response);
            String token = jsonObject.getString("data");
            System.out.println("create token : " + user.getId());

            String row = user.getId() + "," + token;
            raf.seek(raf.length());
            raf.write(row.getBytes());
            raf.write("\r\n".getBytes());
            System.out.println("write to file : " + user.getId());
        }
        raf.close();

        System.out.println("over");
    }

    public static void main(String[] args) throws Exception {
        createUser(1000);
    }
}