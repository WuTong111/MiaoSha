/**
 * $Id: CodeMsg.java,v 1.0 2018/12/9 13:52 G Exp $
 * <p>
 * Copyright 2018 Asiainfo Technologies(China),Inc. All rights reserved.
 */
package com.gy.miaosha.result;

/**
 * @Description: 该类的功能描述
 * @author G
 * @version $Id: CodeMsg.java,v 1.1 2018/12/9 13:52 G Exp $
 * Created on 2018/12/9 13:52
 */
public class CodeMsg {
    private int code;
    private String msg;

    //通用异常
    public static CodeMsg SUCCESS = new CodeMsg(0 , "success");
    public static CodeMsg SERVER_ERRO = new CodeMsg(500100,"服务端异常");
    //登录模块 5002XX

    //商品模块 5003XX

    //订单模块 5004XX

    //秒杀模块 5005XX
    public CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}