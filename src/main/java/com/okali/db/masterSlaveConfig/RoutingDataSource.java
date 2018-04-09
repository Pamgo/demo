package com.okali.db.masterSlaveConfig;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <code>@Target</code> 是作用的目标，接口，方法，类，字段，包等，具体看：<code>ElementType</code>
 * <code>@Retention</code> 是注解范围，<code>RUNTIME</code>代表注解会在class字节码文件中存在，
 * 在运行时可以通过反射获取到，具体看：<code>RetentionPolicy</code>
 * @author OKali
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface RoutingDataSource {

	String value() default DataSources.MASTER_DB;
}
