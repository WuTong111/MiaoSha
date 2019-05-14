package com.gy.miaosha.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 类作用:
 * 项目名称: miaoshaReal
 * 包: com.gy.miaosha.rabbitmq
 * 类名称: MQConfig
 * 类描述: 类功能详细描述
 * 创建人: wuguang
 * 创建时间: 2019/04/15 17:32
 */
@Configuration
public class MQConfig {
    static final String MIAOSHA_QUEUE = "miaosha.queue";
    /**
     * Direct模式 交换机Exchange
     * */
    @Bean
    public Queue queue() {
        return new Queue(MIAOSHA_QUEUE, true);
    }
}