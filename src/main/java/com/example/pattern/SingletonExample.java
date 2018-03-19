package com.example.pattern;

import com.annotation.NotThreadSafe;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@NotThreadSafe
public class SingletonExample {
	
	private SingletonExample () {
		
	}
	
	// 一般情况
	
	// 1、memory = allocate() 分配对象的内存空间
	// 2、ctorInstance() 初始化对象
	// 3、instance = memory 设置instance指向刚分配的内存
	
	// JVM和CPU优化，发生了指令重排
	
	// 1、memory = allocate() 分配对象的内存空间
	// 3、instance = memory 设置instance指向刚分配的内存
	// 2、ctorInstance() 初始化对象
	
	// 单例对象
	private static SingletonExample instance = null;
	
	public static SingletonExample getInstance () {
		if (instance == null) {  // 双重检测机制			// B
			synchronized (SingletonExample.class) { // 同步锁
				if (instance == null) {
					instance = new SingletonExample();  // A - 3 （A线程执行到了第三步）
				}
			}
		}
		return instance;
	}

}
