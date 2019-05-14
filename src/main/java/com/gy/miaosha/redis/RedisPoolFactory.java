/**
 * $Id: RedisPoolFactory.java,v 1.0 2018/12/9 15:46 G Exp $
 * <p>
 * Copyright 2018 Asiainfo Technologies(China),Inc. All rights reserved.
 */
package com.gy.miaosha.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Description: 该类的功能描述
 * @author G
 * @version $Id: RedisPoolFactory.java,v 1.1 2018/12/9 15:46 G Exp $
 * Created on 2018/12/9 15:46
 */
@Service
public class RedisPoolFactory {

    @Autowired
    RedisConfig redisConfig;

//    自动注入到容器
    @Bean
    public JedisPool jedisPoolFactory(){
        System.out.println("RedisPoolFactory我已经自动加载");
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(redisConfig.getPoolMaxIdle());
        poolConfig.setMaxTotal(redisConfig.getPoolMaxTotal());
        poolConfig.setMaxWaitMillis(redisConfig.getPoolMaxWait() * 1000);
        JedisPool jp = new JedisPool(poolConfig,redisConfig.getHost(),redisConfig.getPort(),
                redisConfig.getTimeout() * 1000,redisConfig.getPassword(),0);
        return jp;
    }

}