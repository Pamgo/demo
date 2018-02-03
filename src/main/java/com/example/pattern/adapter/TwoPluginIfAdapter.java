package com.example.pattern.adapter;

/**
 * 使用组合方式
 * @author OKali
 *
 */
public class TwoPluginIfAdapter implements ThreePlugnIf {
	
	private GBPlugin twoPlugin;
	
	public TwoPluginIfAdapter(GBPlugin twoPlugin) {
		this.twoPlugin = twoPlugin;
	}

	@Override
	public void porwerWithThree() {
		System.out.println("二相转三相插座");
		twoPlugin.powerWithTwo();
	}

}
