package com.bluefish.seckill.dao;

import com.bluefish.seckill.model.MiaoshaUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author bluefish 2018/1/20
 * @version 1.0.0
 */
@Mapper
public interface UserDao {

    MiaoshaUser getById(@Param("id") Long id);
}
