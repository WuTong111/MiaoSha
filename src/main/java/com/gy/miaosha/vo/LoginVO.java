/**
 * $Id: LoginVO.java,v 1.0 2018/12/10 13:22 G Exp $
 * <p>
 * Copyright 2018 Asiainfo Technologies(China),Inc. All rights reserved.
 */
package com.gy.miaosha.vo;

/**
 * @Description: 该类的功能描述
 * @author G
 * @version $Id: LoginVO.java,v 1.1 2018/12/10 13:22 G Exp $
 * Created on 2018/12/10 13:22
 */
public class LoginVO {
    String mobile;
    String password;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginVO{" +
                "mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}