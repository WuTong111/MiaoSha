package com.gy.miaosha.service;

import com.gy.miaosha.domain.MiaoshaOrder;
import com.gy.miaosha.domain.MiaoshaUser;
import com.gy.miaosha.domain.OrderInfo;
import com.gy.miaosha.redis.MiaoshaKey;
import com.gy.miaosha.redis.MiaoshaUserKey;
import com.gy.miaosha.redis.RedisService;
import com.gy.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class MiaoshaService {
	
	@Autowired
	GoodsService goodsService;
	
	@Autowired
	OrderService orderService;

	@Autowired
    RedisService redisService;

	@Transactional
	public OrderInfo miaosha(MiaoshaUser user, GoodsVo goods) {
		//减库存 下订单 写入秒杀订单
		boolean success = goodsService.reduceStock(goods);
		if (success){
			//order_info maiosha_order
			return orderService.createOrder(user, goods);
		}else {
		    setGoodsOver(goods.getId());
			return null;
		}
	}

	public void reset(List<GoodsVo> goodsVoList) {
	    goodsService.resetStock(goodsVoList);
	    orderService.deleteOrders();
    }

    public long getMiaoshaResult(Long userId, long goodsId) {
        MiaoshaOrder order = orderService.getMiaoshaOrderByUserIdGoodsId(userId, goodsId);
        if(order != null){//秒杀成功
            return order.getOrderId();
        }else {
            boolean isOver = getGoodsOver(goodsId);
            if(isOver) {
                return -1;
            }else {
                return 0;
            }
        }
    }

    private void setGoodsOver(Long goodsId) {
        redisService.set(MiaoshaKey.isGoodsOver,""+goodsId, true);
    }
    private boolean getGoodsOver(long goodsId) {
	    return redisService.exits(MiaoshaKey.isGoodsOver, ""+goodsId);
    }
}
