package com.example.pattern.adapter;

/**
 * 使用继承方式
 * @author OKali
 *
 */
public class TwoPluginAdapterExtends extends GBPlugin implements ThreePlugnIf {

	@Override
	public void porwerWithThree() {
		this.powerWithTwo();
	}

}
