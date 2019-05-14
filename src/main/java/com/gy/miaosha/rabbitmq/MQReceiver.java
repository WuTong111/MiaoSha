package com.gy.miaosha.rabbitmq;

import com.gy.miaosha.domain.MiaoshaOrder;
import com.gy.miaosha.domain.MiaoshaUser;
import com.gy.miaosha.redis.RedisService;
import com.gy.miaosha.service.GoodsService;
import com.gy.miaosha.service.MiaoshaService;
import com.gy.miaosha.service.OrderService;
import com.gy.miaosha.vo.GoodsVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 类作用:
 * 项目名称: miaoshaReal
 * 包: com.gy.miaosha.rabbitmq
 * 类名称: MQReceiver
 * 类描述: 类功能详细描述
 * 创建人: wuguang
 * 创建时间: 2019/04/15 17:29
 */
@Service
public class MQReceiver {
    private static Logger logger = LoggerFactory.getLogger(MQReceiver.class);

    @Autowired
    RedisService redisService;

    @Autowired
    GoodsService goodsService;
    @Autowired
    OrderService orderService;
    @Autowired
    MiaoshaService miaoshaService;

    @RabbitListener(queues = MQConfig.MIAOSHA_QUEUE)
    public void receive(String message){
        logger.info("receive message: " + message);
        MiaoshaMessage miaoshaMessage = RedisService.stringToBean(message, MiaoshaMessage.class);
        MiaoshaUser user = miaoshaMessage.getUser();
        Long goodsId = miaoshaMessage.getGoodsId();

        //判断库存
    	GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
    	int stock = goods.getStockCount();
    	if(stock <= 0) {
    	    return;
    	}
        //判断是否已经秒杀到了
    	MiaoshaOrder order = orderService.getMiaoshaOrderByUserIdGoodsId(user.getId(), goodsId);
    	if(order != null) {
    	    return;
    	}
        //减库存 下订单 写入秒杀订单
        miaoshaService.miaosha(user,goods);
    }
}
