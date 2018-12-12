/**
 * $Id: IsMobileValidator.java,v 1.0 2018/12/10 14:54 G Exp $
 * <p>
 * Copyright 2018 Asiainfo Technologies(China),Inc. All rights reserved.
 */
package com.gy.miaosha.validator;

import com.gy.miaosha.util.ValidatorUtil;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @Description: 该类的功能描述
 * @author G
 * @version $Id: IsMobileValidator.java,v 1.1 2018/12/10 14:54 G Exp $
 * Created on 2018/12/10 14:54
 */
public class IsMobileValidator implements ConstraintValidator<IsMobile,String> {


    private boolean required = false;
    @Override
    public void initialize(IsMobile isMobile) {
        required = isMobile.required();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(required){
            return ValidatorUtil.isMobile(s);
        }else{
            if(StringUtils.isEmpty(s)){
                return true;
            }else{
                 return ValidatorUtil.isMobile(s);
            }
        }
    }
}