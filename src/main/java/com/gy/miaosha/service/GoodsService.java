/**
 * $Id: GoodsService.java,v 1.0 2018/12/13 10:19 G Exp $
 * <p>
 * Copyright 2018 Asiainfo Technologies(China),Inc. All rights reserved.
 */
package com.gy.miaosha.service;

import com.gy.miaosha.dao.GoodsDao;
import com.gy.miaosha.domain.MiaoshaGoods;
import com.gy.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 该类的功能描述
 * @author G
 * @version $Id: GoodsService.java,v 1.1 2018/12/13 10:19 G Exp $
 * Created on 2018/12/13 10:19
 */
@Service
public class GoodsService {
    @Autowired
    GoodsDao goodsDao;

    public List<GoodsVo> listGoodsVo(){
        return goodsDao.listGoodsVo();
    }

    public GoodsVo getGoodsVoByGoodsId(long goodsId) {
        return goodsDao.getGoodsVoByGoodsId(goodsId);
    }

    public void reduceStock(GoodsVo goods) {
        MiaoshaGoods g = new MiaoshaGoods();
        g.setGoodsId(goods.getId());
        goodsDao.reduceStock(g);
    }

}