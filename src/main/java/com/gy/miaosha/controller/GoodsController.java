/**
 * $Id: LoginController.java,v 1.0 2018/12/10 12:20 G Exp $
 * <p>
 * Copyright 2018 Asiainfo Technologies(China),Inc. All rights reserved.
 */
package com.gy.miaosha.controller;

import com.gy.miaosha.domain.MiaoshaUser;
import com.gy.miaosha.redis.RedisService;
import com.gy.miaosha.result.Result;
import com.gy.miaosha.service.GoodsService;
import com.gy.miaosha.service.MiaoshaUserService;
import com.gy.miaosha.vo.GoodsVo;
import com.gy.miaosha.vo.LoginVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * @Description: 该类的功能描述
 * @author G
 * @version $Id: LoginController.java,v 1.1 2018/12/10 12:20 G Exp $
 * Created on 2018/12/10 12:20
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    MiaoshaUserService userService;
    @Autowired
    RedisService redisService;
    @Autowired
    GoodsService goodsService;
    @RequestMapping("/to_list")
    public String loLogin(HttpServletResponse response,Model model,
//                          @CookieValue(value = MiaoshaUserService.COOKI_NAME_TOKEN, required = false) String cookieToken,
//                          @RequestParam(value = MiaoshaUserService.COOKI_NAME_TOKEN, required = false) String paramToken,
                            MiaoshaUser user){
//        if(StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)){
//            return "login";
//        }
//        String token = StringUtils.isEmpty(paramToken) ? cookieToken : paramToken;
//        MiaoshaUser miaoshaUser = userService.getByToken(response, token);
        model.addAttribute("user", user);
        List<GoodsVo> goodsList = goodsService.listGoodsVo();
        model.addAttribute("goodsList", goodsList);
        return "goods_list";
    }

    @RequestMapping("/to_detail/{goodsId}")
    public String detail(Model model, MiaoshaUser user,
                         @PathVariable("goodsId")long goodsId){

        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        model.addAttribute("goods", goods);
        model.addAttribute("user", user);

        long startAt = goods.getStartDate().getTime();
        long endAt = goods.getEndDate().getTime();
        long now = System.currentTimeMillis();

        int miaoshaStatus = 0;
        int remainSeconds = 0;

        if(now < startAt){ //秒杀还没开始  倒计时
            miaoshaStatus = 0;
            remainSeconds = (int) ((endAt - now)/1000);
        }else if(now > endAt){  //秒杀已经结束
            miaoshaStatus = 2;
            remainSeconds = -1;
        }else{    //秒杀进行中
            miaoshaStatus = 1;
            remainSeconds = 0;
        }
        model.addAttribute("miaoshaStatus", miaoshaStatus);
        model.addAttribute("remainSeconds", remainSeconds);
        return "goods_detail";
    }
}