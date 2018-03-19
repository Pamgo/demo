package com.example.pattern;

import com.annotation.Recommend;
import com.annotation.ThreadSafe;

/**
 * 使用枚举单例:最安全
 * @author OKali
 */
@ThreadSafe
@Recommend("推荐写法")
public class SingletonExample4 {
	
	// 私有构造方法
	private SingletonExample4 () {
		
	}

	public static SingletonExample4 getInstance () {
		return Singleton.INSTANCE.getInstance();
	}

	private enum Singleton {
		INSTANCE;
		private SingletonExample4 singleton;
		
		// JVM保证这个方法绝对只调用一次
		private Singleton() {
			singleton = new SingletonExample4();
		}
		
		public SingletonExample4 getInstance () {
			return singleton;
		}
	}
}
