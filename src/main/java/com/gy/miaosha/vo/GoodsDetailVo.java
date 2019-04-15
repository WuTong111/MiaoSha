package com.gy.miaosha.vo;

import com.gy.miaosha.domain.MiaoshaUser;
import lombok.Data;

/**
 * 类作用:
 * 项目名称: miaoshaReal
 * 包: com.gy.miaosha.vo
 * 类名称: GoodsDetailVo
 * 类描述: 类功能详细描述
 * 创建人: wuguang
 * 创建时间: 2019/04/12 15:45
 */
@Data
public class GoodsDetailVo {
    private int miaoshaStatus;
    private int remainSeconds;
    private GoodsVo goods;
    private MiaoshaUser user;
}