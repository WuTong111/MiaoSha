package com.gy.miaosha.rabbitmq;

import com.gy.miaosha.redis.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 类作用:
 * 项目名称: miaoshaReal
 * 包: com.gy.miaosha.rabbitmq
 * 类名称: MQSender
 * 类描述: 类功能详细描述
 * 创建人: wuguang
 * 创建时间: 2019/04/15 17:39
 */
@Service
public class MQSender {
    private static Logger log = LoggerFactory.getLogger(MQSender.class);

    @Autowired
    AmqpTemplate amqpTemplate;

    public void sendMiaoshaMessage(MiaoshaMessage miaoshaMessage) {
        String msg = RedisService.beanToString(miaoshaMessage);
        log.info("send message:" + msg);
        amqpTemplate.convertAndSend(MQConfig.MIAOSHA_QUEUE, msg);
    }
}