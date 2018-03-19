package com.example.pattern;

import com.annotation.ThreadSafe;

@ThreadSafe
public class SingletonExample2 {
	
	// 私有构造方法
	private SingletonExample2 () {
		
	}
	
	// 单例对象
	private static SingletonExample2 instance = null;
	// 静态代码块是按照顺序执行的，所以上下两个顺序不能调
	static {
		instance = new SingletonExample2();
	}
	
	
	public static SingletonExample2 getInstance () {
		return instance;
	}

}
