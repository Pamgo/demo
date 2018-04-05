package com.okali.db.masterSlaveConfig;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 通过编写切面，对我们自定义切库注解方法进行拦截，动态的选择数据源
 * @author OKali
 *
 */
@Aspect
@Component
@Slf4j
public class DynamicDataSourceAspect {

	@Before("@annotation(RoutingDataSource")
	public void beforeSwitchDs(JoinPoint point) {
		
		// 获得当前访问的class
		Class<?> className = point.getTarget().getClass();
		
		// 获得访问的方法名
		String methodName = point.getSignature().getName();
		// 得到方法的参数类型
		Class<?>[] argClass = ((MethodSignature)point.getSignature()).getParameterTypes();
		String dataSource = DataSourceContextHolder.DEFAULT_DATASOURCE;
		try {
			// 得到访问的方法对象
			Method method = className.getMethod(methodName, argClass);
			
			//判断是否存在@DS注解
			if (method.isAnnotationPresent(RoutingDataSource.class)) {
				RoutingDataSource annotation = method.getAnnotation(RoutingDataSource.class);
				dataSource = annotation.value();
			}
		} catch (Exception e) {
			log.error("routing datasource exception, " + methodName, e);
		}
		
		// 切换数据源
		DataSourceContextHolder.setDB(dataSource);
	}
	
	@After("@annotation(RoutingDataSource)")
	public void afterSwitchDS(JoinPoint point) {
		DataSourceContextHolder.clearDB();
	}
}
