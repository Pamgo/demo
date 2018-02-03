package com.example.pattern.templates;

/**
 * 模板方法
 * @author OKali
 *
 */
public abstract class Template {

	/**
	 * 封装所有子类遵行的算法框架
	 * final修饰不允许子类进行修改
	 */
	public final void prepareBeverageTemplate() {
		
		if (isNeedBoilwater()) {
			boilwater();
		}
		
		brew();
		
		pourInCup();
		
		addCondiments();
	}

	// 是否需要，Hook，钩子函数，提供一个默认或者空的实现
	// 具体子类可以自行决定是否挂钩以及如何挂钩
	protected boolean isNeedBoilwater() {
		return true;
	}

	// 基本方法
	private void addCondiments() {
		System.out.println("=====addCondiments");
	}

	// 基本方法
	private void pourInCup() {
		System.out.println("=====pourInCup");
	}
	
	//protected是遵循了最小访问权限原则，非继承关系的类没必要看到这个方法
	//protected修饰的内容可以被同一个包的其他类访问，也可以被不同包中的子类访问，
	//在实际项目开发中，一般用来修饰只开放给子类使用的属性、方法和构造方法；
	// 抽象基本方法，让子类去实现
	protected abstract void brew();

	// 抽象方法，让子类去实现
	protected abstract void boilwater();
	
}
