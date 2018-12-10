/**
 * $Id: MiaoshaUserService.java,v 1.0 2018/12/10 13:58 G Exp $
 * <p>
 * Copyright 2018 Asiainfo Technologies(China),Inc. All rights reserved.
 */
package com.gy.miaosha.service;

import com.gy.miaosha.dao.MiaoshaUserDao;
import com.gy.miaosha.domain.MiaoshaUser;
import com.gy.miaosha.result.CodeMsg;
import com.gy.miaosha.util.MD5Util;
import com.gy.miaosha.vo.LoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 该类的功能描述
 * @author G
 * @version $Id: MiaoshaUserService.java,v 1.1 2018/12/10 13:58 G Exp $
 * Created on 2018/12/10 13:58
 */
@Service
public class MiaoshaUserService {

    @Autowired
    MiaoshaUserDao miaoshaUserDao;

    public MiaoshaUser getById(long id){
        return miaoshaUserDao.getById(id);
    }

    public CodeMsg login(LoginVO loginVO) {
        if(loginVO == null){
            return CodeMsg.SERVER_ERRO;
        }
        String mobile = loginVO.getMobile();
        String formPass = loginVO.getPassword();
        //判断手机号是否存在
        MiaoshaUser user = getById(Long.parseLong(mobile));
        if(user == null){
            return CodeMsg.MOBILE_NOT_EXIST;
        }
        //验证密码
        String dbPass = user.getPassword();
        String dbSalt = user.getSalt();

        String formToDBPass = MD5Util.formPassToDBPass(formPass,dbSalt);
        if(!formToDBPass.equals(dbPass)) {
            return CodeMsg.PASSWORD_ERROR;
        }
        return CodeMsg.SUCCESS;
    }
}