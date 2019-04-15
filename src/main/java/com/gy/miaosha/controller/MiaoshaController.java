package com.gy.miaosha.controller;

import com.gy.miaosha.domain.MiaoshaOrder;
import com.gy.miaosha.domain.MiaoshaUser;
import com.gy.miaosha.domain.OrderInfo;
import com.gy.miaosha.redis.RedisService;
import com.gy.miaosha.result.CodeMsg;
import com.gy.miaosha.result.Result;
import com.gy.miaosha.service.GoodsService;
import com.gy.miaosha.service.MiaoshaService;
import com.gy.miaosha.service.MiaoshaUserService;
import com.gy.miaosha.service.OrderService;
import com.gy.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/miaosha")
public class MiaoshaController {

	@Autowired
	MiaoshaUserService userService;
	
	@Autowired
	RedisService redisService;
	
	@Autowired
	GoodsService goodsService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	MiaoshaService miaoshaService;

	/***
	 * GET 幂等  代表从服务端获取数据  多少次和一次产生的效果时一样的 不会对服务端数据产生影响
     *  例如获取用户信息
     * POST 不是幂等  会改变服务端数据 例如增删改
	 *
	 * @return
	 * @throws Exception
	 * @author wuguang
	 */
    @RequestMapping(value = "/do_miaosha", method = RequestMethod.POST)
    @ResponseBody
    public Result<OrderInfo> list(Model model, MiaoshaUser user,
					   @RequestParam("goodsId")long goodsId) {
    	model.addAttribute("user", user);
    	if(user == null) {
    		return Result.error(CodeMsg.SESSION_ERROR);
    	}
    	//判断库存
    	GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
    	int stock = goods.getStockCount();
    	if(stock <= 0) {
    	    return Result.error(CodeMsg.MIAO_SHA_OVER);
    	}
    	//判断是否已经秒杀到了
    	MiaoshaOrder order = orderService.getMiaoshaOrderByUserIdGoodsId(user.getId(), goodsId);
    	if(order != null) {
    	    return Result.error(CodeMsg.REPEATE_MIAOSHA);
    	}
    	//减库存 下订单 写入秒杀订单
    	OrderInfo orderInfo = miaoshaService.miaosha(user, goods);
    	return Result.success(orderInfo);
    }
}
