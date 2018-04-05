package com.okali.db.masterSlaveConfig;

import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.collect.Maps;

@Configuration
@MapperScan(basePackages = {"com.okali.db.business.dao"})
public class MybatisConfig {

	@Autowired
	@Qualifier(DataSources.MASTER_DB)
	private DataSource masterDB;
	
	@Autowired
	@Qualifier(DataSources.SLAVE_DB)
	private DataSource slaveDB;
	
	/**
	 * 动态数据源
	 * @return
	 */
	@Bean(name="dynamicDataSource")
	public DataSource dynamicDataSource() {
		DynamicDataSource dynamicDataSource = new DynamicDataSource();
		//默认数据库
		dynamicDataSource.setDefaultTargetDataSource(masterDB);
		
		//配置多数据源
		Map<Object, Object> dsMap = Maps.newHashMap();
		dsMap.put(DataSources.MASTER_DB, masterDB);
		dsMap.put(DataSources.SLAVE_DB, slaveDB);
		dynamicDataSource.setTargetDataSources(dsMap);
		return dynamicDataSource;
	}
	
	@Bean
	@ConfigurationProperties(prefix = "mybatis")
	public SqlSessionFactoryBean sqlSessionFactoryBean() {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		// 配置数据源，此处配置为关键配置，如果没有将dynamicDataSource作为数据源，则不能实现切换
		sqlSessionFactoryBean.setDataSource(dynamicDataSource());
		return sqlSessionFactoryBean;
	}
	
}
