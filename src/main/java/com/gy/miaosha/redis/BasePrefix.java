/**
 * $Id: BasePrefix.java,v 1.0 2018/12/9 15:55 G Exp $
 * <p>
 * Copyright 2018 Asiainfo Technologies(China),Inc. All rights reserved.
 */
package com.gy.miaosha.redis;

/**
 * @Description: 该类的功能描述
 * @author G
 * @version $Id: BasePrefix.java,v 1.1 2018/12/9 15:55 G Exp $
 * Created on 2018/12/9 15:55
 */
public abstract class BasePrefix implements KeyPrefix {


    private int expireSeconds;

    private String prefix;

    public BasePrefix(String prefix) { //0代表永不过期
        this(0, prefix);
    }
    public BasePrefix(int expireSeconds, String prefix) {
        this.expireSeconds = expireSeconds;
        this.prefix = prefix;
    }

    @Override
    public int expireSeconds() {  //默认0代表永不过期
        return expireSeconds;
    }

    @Override
    public String getPrefix() {
        String className = getClass().getSimpleName();
        return className + ":" + prefix;
    }
}