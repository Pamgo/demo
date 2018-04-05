package com.okali.db.multiDBSource;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.okali.db.masterSlaveConfig.DataSources;

/**
 * @author OKali
 */
/*
@Configuration
@MapperScan(basePackages = {"com.okali.db.multiDBSource.business.dao"})
public class MybatisConfig {

	@Autowired
	@Qualifier(DataSources.MASTER_DB)
	private DataSource masterDB;
	
	@Bean
	@ConfigurationProperties(prefix = "mybatis")
	public SqlSessionFactoryBean sqlSessionFactoryBean() {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(masterDB);
		return sqlSessionFactoryBean;
	}
	
}
*/