package com.gy.miaosha.rabbitmq;

import com.gy.miaosha.domain.MiaoshaUser;
import lombok.Data;

/**
 * 类作用:
 * 项目名称: miaoshaReal
 * 包: com.gy.miaosha.rabbitmq
 * 类名称: MiaoshaMessage
 * 类描述: 类功能详细描述
 * 创建人: wuguang
 * 创建时间: 2019/04/30 10:43
 */
@Data
public class MiaoshaMessage {
    private MiaoshaUser user;
    private Long goodsId;
}