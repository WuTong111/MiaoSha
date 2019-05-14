/**
 * $Id: OrderKey.java,v 1.0 2018/12/10 9:25 G Exp $
 * <p>
 * Copyright 2018 Asiainfo Technologies(China),Inc. All rights reserved.
 */
package com.gy.miaosha.redis;

/**
 * @Description: 该类的功能描述
 * @author G
 * @version $Id: OrderKey.java,v 1.1 2018/12/10 9:25 G Exp $
 * Created on 2018/12/10 9:25
 */
public class OrderKey extends BasePrefix {

    private OrderKey(String prefix) {
        super(prefix);
    }
    public static OrderKey getMiaoshaOrderByUidGid = new OrderKey("moug");
}