package com.gy.miaosha.redis;

/**
 * 类作用:
 * 项目名称: miaoshaReal
 * 包: com.gy.miaosha.redis
 * 类名称: MiaoshaKey
 * 类描述: 类功能详细描述
 * 创建人: wuguang
 * 创建时间: 2019/04/30 11:26
 */
public class MiaoshaKey extends BasePrefix{
    private MiaoshaKey(String prefix){
        super(prefix);
    }
    private MiaoshaKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static MiaoshaKey isGoodsOver = new MiaoshaKey("goodsOver");
    public static MiaoshaKey getMiaoshaPath = new MiaoshaKey(60,"mpath");
    public static MiaoshaKey getMiaoshaVerifyCode = new MiaoshaKey(300, "vc");
}