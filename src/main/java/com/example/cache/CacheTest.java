package com.example.cache;

import com.example.cache.spring.ConfigCache_v1;

public class CacheTest {

	public static void main(String[] args) {
		//缓存一
		ConfigCache_v1.getConfigCache().clearCache();
		// 缓存二
		for (int i = 0; i < 100; i++) {
			CacheCallBack cacheCallBack = new CacheCallBack("val",10,10) {
					@Override
					public Object doWrite() {
						return 1;
					}
				};
			Object obj = cacheCallBack.doWriteAndGet();
			System.out.println(obj);
		}
		
		
		
	}
}
