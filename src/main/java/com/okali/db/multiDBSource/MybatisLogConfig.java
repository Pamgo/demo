package com.okali.db.multiDBSource;

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
import com.okali.db.masterSlaveConfig.DataSources;

/**
 * @author OKali
 *
 */
/*
@Configuration
@MapperScan(basePackages = {"com.okali.db.multiDBSource.business.logdao"})
public class MybatisLogConfig {

	@Autowired
	@Qualifier(DataSources.LOG_BD)
	private DataSource logDB;
	
	@Bean
	@ConfigurationProperties(prefix = "mybatisLog")
	public SqlSessionFactoryBean sqlSessionFactoryBean() {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(logDB);
		return sqlSessionFactoryBean;
	}
	
}
*/
