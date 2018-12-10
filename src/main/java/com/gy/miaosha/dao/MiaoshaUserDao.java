/**
 * $Id: MiaoshaUserDao.java,v 1.0 2018/12/10 13:56 G Exp $
 * <p>
 * Copyright 2018 Asiainfo Technologies(China),Inc. All rights reserved.
 */
package com.gy.miaosha.dao;

import com.gy.miaosha.domain.MiaoshaUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author G
 * @version $Id: MiaoshaUserDao.java,v 1.1 2018/12/10 13:56 G Exp $
 * Created on 2018/12/10 13:56
 */
@Mapper
public interface MiaoshaUserDao {

    @Select("select * from miaosha_user where id = #{id}")
    public MiaoshaUser getById(@Param("id") long id);
}