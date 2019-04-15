/**
 * $Id: LoginController.java,v 1.0 2018/12/10 12:20 G Exp $
 * <p>
 * Copyright 2018 Asiainfo Technologies(China),Inc. All rights reserved.
 */
package com.gy.miaosha.controller;

import com.gy.miaosha.domain.MiaoshaUser;
import com.gy.miaosha.redis.GoodsKey;
import com.gy.miaosha.redis.RedisService;
import com.gy.miaosha.result.Result;
import com.gy.miaosha.service.GoodsService;
import com.gy.miaosha.service.MiaoshaUserService;
import com.gy.miaosha.vo.GoodsDetailVo;
import com.gy.miaosha.vo.GoodsVo;
import com.gy.miaosha.vo.LoginVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.spring4.context.SpringWebContext;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * @author G
 * @version $Id: LoginController.java,v 1.1 2018/12/10 12:20 G Exp $
 * Created on 2018/12/10 12:20
 * @Description: 该类的功能描述
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
    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    ThymeleafViewResolver thymeleafViewResolver;

    /**
     *  QPS : 1228  load: 15 mysql heigh
     *  5000*10
     *  QPS : 2884
     *  5000*10 ,load(系统负载 5)
    */
    // 页面缓存
    @RequestMapping(value = "/to_list", produces = "text/html")
    @ResponseBody
    public String list(HttpServletRequest request,
            HttpServletResponse response, Model model,
                          MiaoshaUser user) {

        String html = redisService.get(GoodsKey.getGoodsList, "", String.class);
        if(!StringUtils.isEmpty(html)){
            return html;
        }
        List<GoodsVo> goodsList = goodsService.listGoodsVo();
        model.addAttribute("goodsList", goodsList);
        SpringWebContext ctx = new SpringWebContext(request, response,
                request.getServletContext(), request.getLocale(),
                model.asMap(), applicationContext);
        //手动渲染
        html = thymeleafViewResolver.getTemplateEngine().process("goods_list", ctx);

        if(!StringUtils.isEmpty(html)){
            redisService.set(GoodsKey.getGoodsList, "", html);
        }
        return html;
    }

    @RequestMapping(value = "/detail/{goodsId}")
    @ResponseBody
    public Result<GoodsDetailVo> detail(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Model model, MiaoshaUser user,
                                        @PathVariable("goodsId") long goodsId) {

        //2.手动渲染
        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);

        long startAt = goods.getStartDate().getTime();
        long endAt = goods.getEndDate().getTime();
        long now = System.currentTimeMillis();

        int miaoshaStatus = 0;
        int remainSeconds = 0;

        if (now < startAt) { //秒杀还没开始  倒计时
            miaoshaStatus = 0;
            remainSeconds = (int) ((startAt - now) / 1000);
        } else if (now > endAt) {  //秒杀已经结束
            miaoshaStatus = 2;
            remainSeconds = -1;
        } else {    //秒杀进行中
            miaoshaStatus = 1;
            remainSeconds = 0;
        }
        GoodsDetailVo dto = new GoodsDetailVo();
        dto.setRemainSeconds(remainSeconds);
        dto.setMiaoshaStatus(miaoshaStatus);
        dto.setUser(user);
        dto.setGoods(goods);
        return Result.success(dto);
    }
}