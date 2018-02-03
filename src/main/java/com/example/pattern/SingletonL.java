package com.example.pattern;

/**
 * 懒汉模式（非线程安全）
 * @author OKali
 *
 */
public class SingletonL {

	// 1、将构造方法私有化，不允许外部直接创建对象
	private SingletonL() {}
	// 2、声明类的唯一实例，使用private static修饰
	private static SingletonL instance;
	// 3、提供外部调用创建实例方法
	public static SingletonL getInstance() {
		if (instance == null) {
			instance = new SingletonL();
		}
		return instance;
	}
	
	public static void main(String[] args) {
		SingletonL singletonL = SingletonL.getInstance();
		SingletonL singletonL2 = SingletonL.getInstance();
		if (singletonL == singletonL2) {
			System.out.println("同一个实例");
		} else {
			System.out.println("不同对象");
		}
	}
}
