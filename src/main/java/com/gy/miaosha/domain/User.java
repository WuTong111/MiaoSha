/**
 * $Id: User.java,v 1.0 2018/12/9 13:40 G Exp $
 * <p>
 * Copyright 2018 Asiainfo Technologies(China),Inc. All rights reserved.
 */
package com.gy.miaosha.domain;

/**
 * @Description: 测试数据库连通信
 * @author G
 * @version $Id: User.java,v 1.1 2018/12/9 13:40 G Exp $
 * Created on 2018/12/9 13:40
 */
public class User {
    int id;
    String name;

    public User() {
    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}