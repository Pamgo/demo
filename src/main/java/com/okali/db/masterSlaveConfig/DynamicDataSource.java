package com.okali.db.masterSlaveConfig;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import lombok.extern.slf4j.Slf4j;

/**
 * 动态数据源，继承AbstractRoutingDataSource并重写determineCurrentLookupKey()方法
 * @author OKali
 *
 */
@Slf4j
public class DynamicDataSource extends AbstractRoutingDataSource{

	@Override
	protected Object determineCurrentLookupKey() {
		log.info("数据源为{}", DataSourceContextHolder.getDB());
		return DataSourceContextHolder.getDB();	
	}

	
}
