/**
 * $Id: UUIDUtil.java,v 1.0 2018/12/11 11:11 G Exp $
 * <p>
 * Copyright 2018 Asiainfo Technologies(China),Inc. All rights reserved.
 */
package com.gy.miaosha.util;

import java.util.UUID;

/**
 * @Description: 该类的功能描述
 * @author G
 * @version $Id: UUIDUtil.java,v 1.1 2018/12/11 11:11 G Exp $
 * Created on 2018/12/11 11:11
 */
public class UUIDUtil {
    public static String uuid(){
        return UUID.randomUUID().toString().replace("-","");
    }

    public static void main(String[] args) {
        System.out.println(uuid());
    }

}