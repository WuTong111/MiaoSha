package com.gy.miaosha.controller;

import com.gy.miaosha.domain.MiaoshaUser;
import com.gy.miaosha.rabbitmq.MQSender;
import com.gy.miaosha.rabbitmq.MiaoshaMessage;
import com.gy.miaosha.redis.GoodsKey;
import com.gy.miaosha.redis.MiaoshaKey;
import com.gy.miaosha.redis.OrderKey;
import com.gy.miaosha.redis.RedisService;
import com.gy.miaosha.result.CodeMsg;
import com.gy.miaosha.result.Result;
import com.gy.miaosha.service.GoodsService;
import com.gy.miaosha.service.MiaoshaService;
import com.gy.miaosha.service.MiaoshaUserService;
import com.gy.miaosha.service.OrderService;
import com.gy.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

/***
 * 〈方法描述〉.
 * 〈方法详细描述〉
 *
 * @return
 * @throws Exception
 * @author wuguang
 */
@Controller
@RequestMapping("/miaosha")
public class MiaoshaController implements InitializingBean {

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

	@Autowired
	MQSender mqSender;

    private HashMap<Long, Boolean> localOverMap =  new HashMap<Long, Boolean>();

    @RequestMapping(value = "/reset", method = RequestMethod.GET)
    @ResponseBody
	public Result<Boolean> reset(Model model){
        List<GoodsVo> goodsVos = goodsService.listGoodsVo();
        goodsVos.forEach(goods -> {
            goods.setStockCount(10);
            redisService.set(GoodsKey.getMiaoShaGoodsStock, "" +goods.getId(), 10);
            localOverMap.put(goods.getId(), false);
        });
        redisService.delete(OrderKey.getMiaoshaOrderByUidGid);
        redisService.delete(MiaoshaKey.isGoodsOver);
        miaoshaService.reset(goodsVos);
        return Result.success(true);
	}
	/***
	 * 〈系统初始化〉.
	 * 〈将库存的数量在系统初始化的时候加载到redis中〉
	 *
	 * @return
	 * @throws Exception
	 * @author wuguang
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		List<GoodsVo> goodsVoList = goodsService.listGoodsVo();
		if(goodsVoList != null){
			goodsVoList.forEach(goodsVo ->
					redisService.set(GoodsKey.getMiaoShaGoodsStock,""+goodsVo.getId(),goodsVo.getStockCount()));
		}
	}

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
    public Result<Integer> list(Model model, MiaoshaUser user,
					   @RequestParam("goodsId")long goodsId) throws Exception{
    	model.addAttribute("user", user);
    	if(user == null) {
    		return Result.error(CodeMsg.SESSION_ERROR);
    	}
    	//redis中预减库存
		Long stock = redisService.decr(GoodsKey.getMiaoShaGoodsStock, "" + goodsId);
    	if(stock < 0){
    		return Result.error(CodeMsg.MIAO_SHA_OVER);
		}
		//入队
		MiaoshaMessage miaoshaMessage = new MiaoshaMessage();
    	miaoshaMessage.setUser(user);
    	miaoshaMessage.setGoodsId(goodsId);
		mqSender.sendMiaoshaMessage(miaoshaMessage);
		//排队中
		return Result.success(0);
    }
    /***
     * 〈方法描述〉.
     * 〈方法详细描述〉
	 * 	orderId 成功
	 * 	-1：秒杀失败
	 * 	0：排队中
     * @return
     * @throws Exception
     * @author wuguang
     */
	@RequestMapping(value = "/result", method = RequestMethod.GET)
	@ResponseBody
	public Result<Long> miaoshaResult(Model model, MiaoshaUser user,
								@RequestParam("goodsId")long goodsId) {
		if(user == null) {
			return Result.error(CodeMsg.SESSION_ERROR);
		}
		long result = miaoshaService.getMiaoshaResult(user.getId(), goodsId);
		return Result.success(result);
	}
}
