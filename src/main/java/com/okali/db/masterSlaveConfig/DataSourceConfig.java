package com.okali.db.masterAndslaveDB;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
public class DataSourceConfig {

	/**
	 * 主库配置
	 * @return
	 */
	@Bean(destroyMethod = "close", name = DataSources.MASTER_DB)
	@ConfigurationProperties(prefix="spring.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().type(DruidDataSource.class).build();
	}
	
	/**
	 * 从库
	 * @return
	 */
	@Bean(destroyMethod = "close", name = DataSources.SLAVE_DB)
	public DataSource dataSourceSlave() {
		return DataSourceBuilder.create().type(DruidDataSource.class).build();
	}
	
	
}
