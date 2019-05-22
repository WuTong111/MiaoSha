/**
 * $Id: UserArgumentResolver.java,v 1.0 2018/12/12 15:12 G Exp $
 * <p>
 * Copyright 2018 Asiainfo Technologies(China),Inc. All rights reserved.
 */
package com.gy.miaosha.config;

import com.gy.miaosha.access.UserContext;
import com.gy.miaosha.domain.MiaoshaUser;
import com.gy.miaosha.service.MiaoshaUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 该类的功能描述
 * @author G
 * @version $Id: UserArgumentResolver.java,v 1.1 2018/12/12 15:12 G Exp $
 * Created on 2018/12/12 15:12
 */
@Service
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    MiaoshaUserService userService;
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        Class<?> clazz = methodParameter.getParameterType();
        return clazz == MiaoshaUser.class;
    }
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return UserContext.getUser();
    }
}