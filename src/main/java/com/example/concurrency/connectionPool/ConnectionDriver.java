
package com.example.concurrency.connectionPool;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.concurrent.TimeUnit;


public class ConnectionDriver {
	
	static class ConnectionHandler implements InvocationHandler {

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			if (method.getName().equals("commit")) {
				TimeUnit.MILLISECONDS.sleep(500);
			}
			return null;
		}
		
	}

	// 篡改就一个Connection代理，在commit时休眠500毫秒
	public static final Connection createConnection() {
		return (Connection)Proxy.newProxyInstance(ConnectionDriver.class.getClassLoader(), 
				new Class[] {Connection.class}, new ConnectionHandler());
	}
}
