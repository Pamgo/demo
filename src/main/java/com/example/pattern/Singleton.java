package com.example.pattern;

/**
 * 饿汉模式（属于线程安全），类在初始化的时候会获得一个锁
 * 单例模式
 * 应用场合：有些对象只需要一个就可以了，例如数据库链接对象,不需要重复创建连接
 * 作用：保证整个运用程序中某个实例有且只有一个
 * 类型：饿汉模式，懒汉模式
 * @author OKali
 */
public class Singleton {
	
	// 1、将构造方法私有化，不允许外部直接创建对象，这样就可以通过类对象来创建单一实例
	private Singleton() {}
	// 2、创建类的唯一实例(private static提供类的静态成员变量，类加载的时候，就已经创建了该对象)
	private static Singleton instance = new Singleton();
	// 3、提供一个获取实例的方法
	public static Singleton getInstance() {
		return instance;
	}
	
	public static void main(String[] args) {
		Singleton singleton1 = Singleton.getInstance(); //饿汉模式
		Singleton singleton2 = Singleton.getInstance();
		if (singleton1 == singleton2) {
			System.out.println("同一个对象");
		} else {
			System.out.println("不同对象");
		}
	}
}
