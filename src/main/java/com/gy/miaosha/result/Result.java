/**
 * $Id: Result.java,v 1.0 2018/12/9 13:51 G Exp $
 * <p>
 * Copyright 2018 Asiainfo Technologies(China),Inc. All rights reserved.
 */
package com.gy.miaosha.result;

/**
 * @Description: 该类的功能描述
 * @author G
 * @version $Id: Result.java,v 1.1 2018/12/9 13:51 G Exp $
 * Created on 2018/12/9 13:51
 */
public class Result<T> {
    private int code;
    private String msg;
    private T data;

    public Result(T data) {
        this.code = 0;
        this.msg = "success";
        this.data = data;
    }

    public Result(CodeMsg cm) {
        if(cm == null){
            return;
        }
        this.code = cm.getCode();
        this.msg = cm.getMsg();
    }

    /**
    *  成功时候的调用
    * @param:参数描述 
    * @return：返回结果描述
    * @throws：异常描述
    *
    * @version: v1.0.0
    */
    public static <T> Result<T> success(T data){
        return new Result<T>(data);
    }
    /**
    *  失败时调用
    * @param:参数描述 
    * @return：返回结果描述
    * @throws：异常描述
    *
    * @version: v1.0.0
    */
    
    public static <T> Result<T> error(CodeMsg cm){
        return new Result<T>(cm);
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }
}