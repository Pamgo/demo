package com.okali.db.business.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.okali.db.business.bean.User;
import com.okali.db.business.dao.UserMapper;
import com.okali.db.business.service.UserService;
import com.okali.db.masterSlaveConfig.DataSources;
import com.okali.db.masterSlaveConfig.RoutingDataSource;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService{
	
	@Resource
	private UserMapper userMapper;

	@RoutingDataSource(DataSources.MASTER_DB)
	@Override
	public User getUserById(Integer id) {
		return userMapper.getUserById(id);
	}

	@RoutingDataSource(DataSources.SLAVE_DB)
	@Override
	public User getUserById2(Integer id) {
		return userMapper.getUserById(id);
	}

}
