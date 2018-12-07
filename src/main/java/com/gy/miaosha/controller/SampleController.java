/**
 * $Id: SampleController.java,v 1.0 2018/12/7 17:01 G Exp $
 * <p>
 * Copyright 2018 Asiainfo Technologies(China),Inc. All rights reserved.
 */
package com.gy.miaosha.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description: 该类的功能描述
 * @author G
 * @version $Id: SampleController.java,v 1.1 2018/12/7 17:01 G Exp $
 * Created on 2018/12/7 17:01
 */
@Controller
@RequestMapping("/demo")
public class SampleController {

    @RequestMapping("/thymeleaf")
    public String thymeleaf(Model model){
        model.addAttribute("name","Wuguang");
        return "hello";
    }
}