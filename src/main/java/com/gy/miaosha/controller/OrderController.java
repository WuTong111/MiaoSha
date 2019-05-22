package com.gy.miaosha.controller;

import com.gy.miaosha.domain.MiaoshaUser;
import com.gy.miaosha.domain.OrderInfo;
import com.gy.miaosha.redis.RedisService;
import com.gy.miaosha.result.CodeMsg;
import com.gy.miaosha.result.Result;
import com.gy.miaosha.service.GoodsService;
import com.gy.miaosha.service.MiaoshaUserService;
import com.gy.miaosha.service.OrderService;
import com.gy.miaosha.vo.GoodsVo;
import com.gy.miaosha.vo.OrderDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 类作用:
 * 项目名称: miaoshaReal
 * 包: com.gy.miaosha.controller
 * 类名称: OrderController
 * 类描述: 类功能详细描述
 * 创建人: wuguang
 * 创建时间: 2019/04/15 09:56
 */
@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    MiaoshaUserService userService;

    @Autowired
    RedisService redisService;

    @Autowired
    OrderService orderService;

    @Autowired
    GoodsService goodsService;

    @RequestMapping("/detail")
    @ResponseBody
    public Result<OrderDetailVo> info(Model model, MiaoshaUser user,
                                      @RequestParam("orderId") long orderId){
        if(user == null){
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        OrderInfo orderInfo = orderService.getOrderById(orderId);
        if(orderInfo == null){
            return Result.error(CodeMsg.ORDER_NOT_EXIST);
        }
        long goodsId = orderInfo.getGoodsId();
        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        OrderDetailVo vo = new OrderDetailVo();
        vo.setOrder(orderInfo);
        vo.setGoods(goods);
        return Result.success(vo);
    }
}