/**
 * $Id: ValidatorUtil.java,v 1.0 2018/12/10 13:48 G Exp $
 * <p>
 * Copyright 2018 Asiainfo Technologies(China),Inc. All rights reserved.
 */
package com.gy.miaosha.util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: 该类的功能描述
 * @author G
 * @version $Id: ValidatorUtil.java,v 1.1 2018/12/10 13:48 G Exp $
 * Created on 2018/12/10 13:48
 */
public class ValidatorUtil {
   private static final Pattern MOBILE_PATTERN = Pattern.compile("1\\d{10}");

   public static boolean isMobile(String src){
       if(src == null){
           return false;
       }
       Matcher matcher = MOBILE_PATTERN.matcher(src);
       return matcher.matches();
   }

    public static void main(String[] args) {
        System.out.println(isMobile("18912341234"));
        System.out.println(isMobile("1891234123"));
    }

}