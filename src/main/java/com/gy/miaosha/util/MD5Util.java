/**
 * $Id: MD5Util.java,v 1.0 2018/12/10 11:17 G Exp $
 * <p>
 * Copyright 2018 Asiainfo Technologies(China),Inc. All rights reserved.
 */
package com.gy.miaosha.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @Description: 该类的功能描述
 * @author G
 * @version $Id: MD5Util.java,v 1.1 2018/12/10 11:17 G Exp $
 * Created on 2018/12/10 11:17
 */
public class MD5Util {
    public static String md5(String src){
        return DigestUtils.md5Hex(src);
    }
    private static String salt = "1a2b3c4d";

    public static String inputPassToFromPass(String pass){
        String str = "" + salt.charAt(0) + salt.charAt(2) + pass + salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    public static String formPassToDBPass(String formPass,String salt){
        String str = "" + salt.charAt(0) + salt.charAt(2) + formPass + salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    public static String inputPassToDBPass(String pwd,String salt){
        String formPass = inputPassToFromPass(pwd);
        String dbpwd = formPassToDBPass(formPass,salt);
        return dbpwd;
    }

    public static void main(String[] args) {
        System.out.println();
        System.out.println(formPassToDBPass("d064a11fdb02147bab3940b5d47858bf",salt) );

    }
}