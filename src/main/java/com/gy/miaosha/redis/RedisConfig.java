/**
 * $Id: RedisConfig.java,v 1.0 2018/12/9 15:04 G Exp $
 * <p>
 * Copyright 2018 Asiainfo Technologies(China),Inc. All rights reserved.
 */
package com.gy.miaosha.redis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description: 该类的功能描述
 * @author G
 * @version $Id: RedisConfig.java,v 1.1 2018/12/9 15:04 G Exp $
 * Created on 2018/12/9 15:04
 */
@Component
@ConfigurationProperties(prefix = "redis")
@Data
public class RedisConfig {
    private String host;
    private int port;
    private int timeout;//秒
    private String password;
    private int poolMaxTotal;
    private int poolMaxIdle;
    private int poolMaxWait;//秒
}