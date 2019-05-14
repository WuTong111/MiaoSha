/**
 * $Id: SampleController.java,v 1.0 2018/12/7 17:01 G Exp $
 * <p>
 * Copyright 2018 Asiainfo Technologies(China),Inc. All rights reserved.
 */
package com.gy.miaosha.controller;

import com.gy.miaosha.domain.User;
import com.gy.miaosha.rabbitmq.MQSender;
import com.gy.miaosha.redis.RedisService;
import com.gy.miaosha.redis.UserKey;
import com.gy.miaosha.result.Result;
import com.gy.miaosha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @Description: 主要用来测试
 * @author G
 * @version $Id: SampleController.java,v 1.1 2018/12/7 17:01 G Exp $
 * Created on 2018/12/7 17:01
 */
@Controller
@RequestMapping("/demo")
public class SampleController {

    @Autowired
    UserService userService;

    @Autowired
    RedisService redisService;

    @Autowired
    MQSender sender;

    //测试SpringBoot是否能执行
    @RequestMapping("/thymeleaf")
    public String thymeleaf(Model model){
        model.addAttribute("name","Wuguang");
        return "hello";
    }

    //测试是否能够连接数据库
    @RequestMapping("/db/get")
    @ResponseBody
    public Result<User> dbGet(){
        User user = userService.getById(1);
        return Result.success(user);
    }

    //测试数据库事务
    @RequestMapping("/db/tx")
    @ResponseBody
    public Result<Boolean> dbTx(){
        userService.tx();
        return Result.success(true);
    }

    //测试redis get命令
    @RequestMapping("/redis/get")
    @ResponseBody
    public Result<User> redisGet(){
        User v1 = redisService.get(UserKey.getById,""+1, User.class);
        return Result.success(v1);
    }

    //测试redis set命令
    @RequestMapping("/redis/set")
    @ResponseBody
    public Result<Boolean> redisSet(){
        User user = new User(9,"999999");
        Boolean v1 = redisService.set(UserKey.getById,"" + 1, user);
        return Result.success(v1);
    }

}