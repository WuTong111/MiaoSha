/**
 * $Id: UserDao.java,v 1.0 2018/12/9 13:44 G Exp $
 * <p>
 * Copyright 2018 Asiainfo Technologies(China),Inc. All rights reserved.
 */
package com.gy.miaosha.dao;

import com.gy.miaosha.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Description: 该类的功能描述
 * @author G
 * @version $Id: UserDao.java,v 1.1 2018/12/9 13:44 G Exp $
 * Created on 2018/12/9 13:44
 */
@Mapper
public interface UserDao {
    @Select("select * from user where id = #{id}")
    public User getById(@Param("id") int id);

    @Insert("insert into user(id,name) values (#{id},#{name})")
    public int insert(User user);
}