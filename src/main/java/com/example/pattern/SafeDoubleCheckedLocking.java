package com.example.pattern;

public class SafeDoubleCheckedLocking {

	// volatile 修饰，防止指令重排
	private volatile static Instance instance;
	
	// 线程安全的延迟初始化
	public static Instance getInstance() {
		if (instance == null) {
			synchronized (SafeDoubleCheckedLocking.class) {
				if (instance == null) {
					 // 该行代码执行了三个步骤，2,3步骤可能会发生指令重排，所以该对象引用使用volatile防止重排
					 // 1、memory = allocate(); // 1:分配对象内存空间
					 // 2、ctorInstance(memory);// 2:初始化对象
					 // 3、instance = memory;   // 3:设置instance指向刚分配的内存地址
					instance = new Instance();
				}
			}
		}
		return instance;
	}
}
