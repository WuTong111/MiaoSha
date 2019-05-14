/**
 * $Id: MiaoshaUserService.java,v 1.0 2018/12/10 13:58 G Exp $
 * <p>
 * Copyright 2018 Asiainfo Technologies(China),Inc. All rights reserved.
 */
package com.gy.miaosha.service;

import com.gy.miaosha.dao.MiaoshaUserDao;
import com.gy.miaosha.domain.MiaoshaUser;
import com.gy.miaosha.exception.GlobalException;
import com.gy.miaosha.redis.MiaoshaUserKey;
import com.gy.miaosha.redis.RedisService;
import com.gy.miaosha.result.CodeMsg;
import com.gy.miaosha.util.MD5Util;
import com.gy.miaosha.util.UUIDUtil;
import com.gy.miaosha.vo.LoginVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 该类的功能描述
 * @author G
 * @version $Id: MiaoshaUserService.java,v 1.1 2018/12/10 13:58 G Exp $
 * Created on 2018/12/10 13:58
 */
@Service
public class MiaoshaUserService {

    public static final String COOKI_NAME_TOKEN = "token";
    @Autowired
    MiaoshaUserDao miaoshaUserDao;
    @Autowired
    RedisService redisService;

    public MiaoshaUser getById(long id){
        MiaoshaUser user = redisService.get(MiaoshaUserKey.getById, "" + id, MiaoshaUser.class);
        if(user != null){
            return user;
        }
        //空 就取数据库
        user = miaoshaUserDao.getById(id);
        if(user != null){
            redisService.set(MiaoshaUserKey.getById, "" + id, user);
        }
        return user;
    }

    boolean updatePassword(String token, long id, String formPassword){
        //1.取user对象
        MiaoshaUser user = getById(id);
        if(user == null){
            throw  new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }
        MiaoshaUser toBeUpdate = new MiaoshaUser();
        toBeUpdate.setId(id);
        toBeUpdate.setPassword(MD5Util.formPassToDBPass(formPassword, user.getSalt()));
        miaoshaUserDao.update(toBeUpdate);
        //处理缓存
        redisService.delete(MiaoshaUserKey.getById, "" + id);
        //
        user.setPassword(toBeUpdate.getPassword());
        redisService.set(MiaoshaUserKey.token, token, user);
        return true;
    }

    public String login(HttpServletResponse response,LoginVO loginVO) {
        if(loginVO == null){
            throw new GlobalException(CodeMsg.SERVER_ERRO);
        }
        String mobile = loginVO.getMobile();
        String formPass = loginVO.getPassword();
        //判断手机号是否存在
        MiaoshaUser user = getById(Long.parseLong(mobile));
        if(user == null){
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }
        //验证密码
        String dbPass = user.getPassword();
        String dbSalt = user.getSalt();

        String formToDBPass = MD5Util.formPassToDBPass(formPass,dbSalt);
        if(!formToDBPass.equals(dbPass)) {
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }

        //生成cookie
        String token = UUIDUtil.uuid();
        addCookie(user, token ,response);
        return token;
    }

    public MiaoshaUser getByToken(HttpServletResponse response, String token) {
        if(StringUtils.isEmpty(token)){
            return null;
        }
        MiaoshaUser miaoshaUser = redisService.get(MiaoshaUserKey.token, token, MiaoshaUser.class);
        //延长有效期

        if(miaoshaUser != null){
            addCookie(miaoshaUser, token, response);
        }
        return miaoshaUser;

    }

    private void addCookie(MiaoshaUser user, String token, HttpServletResponse response){

        //需要标识用户所以需要写入到redis中 tk, uuid
        redisService.set(MiaoshaUserKey.token, token, user);
        //cookie中存的是uuid        cookie.setMaxAge(MiaoshaUserKey.token.expireSeconds());
        Cookie cookie = new Cookie(COOKI_NAME_TOKEN ,token);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

}