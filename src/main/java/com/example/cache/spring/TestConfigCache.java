package com.example.cache.spring;

public class TestConfigCache {

	public static void main(String[] args) {
		String msg = "xxxxxxxxxxxxxxx";
		String msg2 = "yyyyyyyyyyyyyy";
		String name ="alison";
		String name2 = "pamgo";
		ConfigContext configContext = (ConfigContext) ConfigCache.getConfigCacheManger().getCache(name);
		ConfigContext configContext3 = (ConfigContext) ConfigCache.getConfigCacheManger().getCache(name);
		if (configContext == configContext3) {
			System.out.println("同一个实例");
		} else {
			System.out.println("不是同一个实例");
		}
		ConfigContext configCache2 = (ConfigContext) ConfigCache.getConfigCacheManger().getCache(name2);
		for (int i = 0; i < 100; i++) {
			String cacheMsg = (String) configContext.getCacheValue("key");
			if (cacheMsg == null) {
				configContext.setCacheObject("key", msg);
				System.out.println("非缓存中读取《《《《《《《《《《《《《《《《");
			} else {
				System.out.println(name+"缓存中读取");
			}
		}
		for (int j = 0; j < 100; j++) {
			String cacheMsg2 = (String) configCache2.getCacheValue("key2");
			if (cacheMsg2 == null) {
				configCache2.setCacheObject("key2", msg2);
				System.out.println("非缓存中读取《《《《《《《《《《《《《《《《");
			} else {
				System.out.println(name2+"缓存中读取");
			}
			
		}
		ConfigCache.getConfigCacheManger().flushCache(name2);
		System.out.println("缓存一名称："+configContext.getName()+"信息：" + configContext.store.toString());
		System.out.println("缓存二名称：" + configCache2.getName()+"信息:" + configCache2.store.toString());
	}
}
