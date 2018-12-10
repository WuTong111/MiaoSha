/**
 * $Id: LoginController.java,v 1.0 2018/12/10 12:20 G Exp $
 * <p>
 * Copyright 2018 Asiainfo Technologies(China),Inc. All rights reserved.
 */
package com.gy.miaosha.controller;

import com.gy.miaosha.result.CodeMsg;
import com.gy.miaosha.result.Result;
import com.gy.miaosha.service.MiaoshaUserService;
import com.gy.miaosha.vo.LoginVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description: 该类的功能描述
 * @author G
 * @version $Id: LoginController.java,v 1.1 2018/12/10 12:20 G Exp $
 * Created on 2018/12/10 12:20
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    private static Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    MiaoshaUserService miaoshaUserService;

    @RequestMapping("/to_login")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/do_login")
    @ResponseBody
    public Result<CodeMsg> doLogin(LoginVO loginVO){
        log.info(loginVO.toString());
        //登陆
        CodeMsg cm = miaoshaUserService.login(loginVO);
        if (cm.getCode() == 0){
            return Result.success(CodeMsg.SUCCESS);
        }
        return Result.error(cm);
    }
}