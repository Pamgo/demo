package com.example.pattern.adapter;

/**
 * 适配器模式
 * @author OKali
 *
 */
public class MacBook {

	private ThreePlugnIf threePlugnIf;
	
	public MacBook (ThreePlugnIf threePlugnIf) {
		this.threePlugnIf = threePlugnIf;
	}
	
	public void charge() {
		threePlugnIf.porwerWithThree();
	}
	
	public static void main(String[] args) {
		
		//使用组合方式
		GBPlugin gbPlugin = new GBPlugin();
		ThreePlugnIf threePlugnIf = new TwoPluginIfAdapter(gbPlugin);
		MacBook book = new MacBook(threePlugnIf);
		book.charge();
		
		//继承方式 （单一，只为该操作服务）
		ThreePlugnIf threePlugnIf2 = new TwoPluginAdapterExtends();
		book = new MacBook(threePlugnIf2);
		book.charge();
	}
}
