package com.example.pattern.templates;

/**
 * 具体子类
 * @author OKali
 *
 */
public class Coffee extends Template {

	@Override
	protected void brew() {
		System.out.println("====brew()");
	}

	@Override
	protected void boilwater() {
		System.out.println("====boilwater()");
	}

	/**
	 * 子类通过覆盖的形式选择挂载钩子函数
	 */
	@Override
	protected boolean isNeedBoilwater() {
		return false;
	}
}
