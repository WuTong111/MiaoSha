/**
 * $Id: Prefix.java,v 1.0 2018/12/9 15:54 G Exp $
 * <p>
 * Copyright 2018 Asiainfo Technologies(China),Inc. All rights reserved.
 */
package com.gy.miaosha.redis;

/**
 * @author G
 * @version $Id: Prefix.java,v 1.1 2018/12/9 15:54 G Exp $
 * Created on 2018/12/9 15:54
 */
public interface KeyPrefix {
    int expireSeconds();
    String getPrefix();
}