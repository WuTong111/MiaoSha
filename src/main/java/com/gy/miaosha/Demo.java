/**
 * $Id: Demo.java,v 1.0 2019/3/7 10:47 G Exp $
 * <p>
 * Copyright 2018 Asiainfo Technologies(China),Inc. All rights reserved.
 */
package com.gy.miaosha;

import com.gy.miaosha.domain.Goods;
import com.gy.miaosha.service.GoodsService;

/**
 * @Description: 测试
 * @author Wuguang
 * @version $Id: Demo.java,v 1.1 2019/3/7 10:47 G Exp $
 * Created on 2019/3/7 10:47
 */
public class Demo {
    public static void main(String[] args) {
        GoodsService goodsService = new GoodsService();
        goodsService.listGoodsVo();
    }

}