package com.okali.db.masterAndslaveDB;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DynamicDataSource extends AbstractRoutingDataSource{

	@Override
	protected Object determineCurrentLookupKey() {
		log.info("数据源为{}", DataSourceContextHolder.getDB());
		return DataSourceContextHolder.getDB();	
	}

	
}
