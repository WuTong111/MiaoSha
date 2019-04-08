/**
 * $Id: GoodsVo.java,v 1.0 2018/12/13 10:01 G Exp $
 * <p>
 * Copyright 2018 Asiainfo Technologies(China),Inc. All rights reserved.
 */
package com.gy.miaosha.vo;

import com.gy.miaosha.domain.Goods;
import lombok.Data;

import java.util.Date;

/**
 * @Description: 该类的功能描述
 * @author G
 * @version $Id: GoodsVo.java,v 1.1 2018/12/13 10:01 G Exp $
 * Created on 2018/12/13 10:01
 */
@Data
public class GoodsVo extends Goods {
    private Double miaoshaPrice;
    private Integer stockCount;
    private Date startDate;
    private Date endDate;
    public Integer getStockCount() {
        return stockCount;
    }
}