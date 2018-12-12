/**
 * $Id: LoginController.java,v 1.0 2018/12/10 12:20 G Exp $
 * <p>
 * Copyright 2018 Asiainfo Technologies(China),Inc. All rights reserved.
 */
package com.gy.miaosha.controller;

import com.gy.miaosha.domain.MiaoshaUser;
import com.gy.miaosha.redis.RedisService;
import com.gy.miaosha.result.Result;
import com.gy.miaosha.service.MiaoshaUserService;
import com.gy.miaosha.vo.LoginVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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
        return "goods_list";
    }
}