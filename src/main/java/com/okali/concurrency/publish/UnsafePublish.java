package com.okali.concurrency.publish;

import java.util.Arrays;

import com.annotation.NotThreadSafe;

import lombok.extern.slf4j.Slf4j;

/**
 * 发布对象 
 * @author OKali
 * 发布对象：使一个对象能够被当前范围之外的代码所使用，例如：public修饰的方法或者属性
 */
@Slf4j
@NotThreadSafe
public class UnsafePublish {

	private String[] states = {"a", "b", "c"};
	
	public String[] getStates() {
		return states;
	}
	
	public static void main(String[] args) {
		UnsafePublish publish = new UnsafePublish();
		log.info("{}",Arrays.toString(publish.getStates()));
		
		publish.getStates()[0] = "d";
		log.info("{}",Arrays.toString(publish.getStates()));
	}
}
