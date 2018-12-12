/**
 * $Id: GlobleExceptionHandle.java,v 1.0 2018/12/10 15:16 G Exp $
 * <p>
 * Copyright 2018 Asiainfo Technologies(China),Inc. All rights reserved.
 */
package com.gy.miaosha.exception;

import com.gy.miaosha.result.CodeMsg;
import com.gy.miaosha.result.Result;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Description: 该类的功能描述
 * @author G
 * @version $Id: GlobleExceptionHandle.java,v 1.1 2018/12/10 15:16 G Exp $
 * Created on 2018/12/10 15:16
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    public Result<String> exceptionHandle(HttpServletRequest request, Exception e){
        if(e instanceof  GlobalException){
            GlobalException ex = (GlobalException) e;
            return Result.error(ex.getCm());
        } else if(e instanceof BindException){
            BindException bx = (BindException) e;

            List<ObjectError> errors = ((BindException) e).getAllErrors();
            ObjectError objectError = errors.get(0);
            String msg = objectError.getDefaultMessage();
            return Result.error(CodeMsg.BIND_ERROR.fillArgs(msg));
        }else{
            e.printStackTrace();
            return Result.error(CodeMsg.SERVER_ERRO);
        }
    }
}