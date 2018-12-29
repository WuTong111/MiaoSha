/**
 * $Id: GoodsVo.java,v 1.0 2018/12/13 10:01 G Exp $
 * <p>
 * Copyright 2018 Asiainfo Technologies(China),Inc. All rights reserved.
 */
package com.gy.miaosha.vo;

import com.gy.miaosha.domain.Goods;

import java.util.Date;

/**
 * @Description: 该类的功能描述
 * @author G
 * @version $Id: GoodsVo.java,v 1.1 2018/12/13 10:01 G Exp $
 * Created on 2018/12/13 10:01
 */
public class GoodsVo extends Goods {
    private Double miaoshaPrice;
    private Integer stockCount;
    private Date startDate;
    private Date endDate;
    public Integer getStockCount() {
        return stockCount;
    }
    public void setStockCount(Integer stockCount) {
        this.stockCount = stockCount;
    }
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public Double getMiaoshaPrice() {
        return miaoshaPrice;
    }
    public void setMiaoshaPrice(Double miaoshaPrice) {
        this.miaoshaPrice = miaoshaPrice;
    }
}