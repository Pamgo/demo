package com.okali.db.business.dao;

import org.apache.ibatis.annotations.Mapper;

import com.okali.db.business.bean.User;


@Mapper
public interface UserMapper {

	User getUserById(Integer id);
}
