package com.example.pattern;

import com.annotation.ThreadSafe;

@ThreadSafe
public class SingletonExample3 {
	
	private SingletonExample3 () {
		
	}
	
	// 一般情况
	// 1、memory = allocate() 分配对象的内存空间
	// 2、ctorInstance() 初始化对象
	// 3、instance = memory 设置instance指向刚分配的内存
	
	// 单例对象 volatile+双重检测机制 ->禁止指令重排
	private static volatile SingletonExample3 instance = null;
	
	public static SingletonExample3 getInstance () {
		if (instance == null) {  // 双重检测机制			// B
			synchronized (SingletonExample3.class) { // 同步锁
				if (instance == null) {
					instance = new SingletonExample3();  // A 
				}
			}
		}
		return instance;
	}

}
