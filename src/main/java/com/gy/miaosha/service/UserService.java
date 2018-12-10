/**
 * $Id: UserService.java,v 1.0 2018/12/9 13:46 G Exp $
 * <p>
 * Copyright 2018 Asiainfo Technologies(China),Inc. All rights reserved.
 */
package com.gy.miaosha.service;

import com.gy.miaosha.dao.UserDao;
import com.gy.miaosha.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: 该类的功能描述
 * @author G
 * @version $Id: UserService.java,v 1.1 2018/12/9 13:46 G Exp $
 * Created on 2018/12/9 13:46
 */
@Service
public class UserService {

    @Autowired
    UserDao userDao;
    public User getById(int id){
        return userDao.getById(id);
    }

    @Transactional
    public boolean tx() {
        User u1 = new User();
        u1.setId(7);
        u1.setName("777777777777");
        userDao.insert(u1);
        User u2 = new User();
        u2.setId(6);
        u2.setName("6666666666666");
        userDao.insert(u2);
        return true;
    }
}