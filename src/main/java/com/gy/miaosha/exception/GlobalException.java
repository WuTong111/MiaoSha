/**
 * $Id: GlobleException.java,v 1.0 2018/12/10 16:38 G Exp $
 * <p>
 * Copyright 2018 Asiainfo Technologies(China),Inc. All rights reserved.
 */
package com.gy.miaosha.exception;

import com.gy.miaosha.result.CodeMsg;

/**
 * @Description: 该类的功能描述
 * @author G
 * @version $Id: GlobleException.java,v 1.1 2018/12/10 16:38 G Exp $
 * Created on 2018/12/10 16:38
 */
public class GlobalException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private CodeMsg cm;
    public GlobalException(CodeMsg cm){
        super(cm.toString());
        this.cm = cm;
    }

    public CodeMsg getCm() {
        return cm;
    }
}