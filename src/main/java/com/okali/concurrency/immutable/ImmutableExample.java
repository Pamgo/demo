package com.okali.concurrency.immutable;

import java.util.Map;

import com.google.common.collect.Maps;

import lombok.extern.slf4j.Slf4j;

/**
 * final修饰引用数据类型
 * @author OKali
 *
 */
@Slf4j
public class ImmutableExample {
	
	private final static Integer  a = 1;
	private final static String   b = "2";
	private final static Map<Integer, Integer> map = Maps.newHashMap();
	
	static {
		map.put(1, 2);
		map.put(3, 4);
		map.put(5, 6);
		map.put(7, 8);
	}
	
	public static void main(String[] args) {
//		a = 2;			// 不可修改
//		b = "3";        // 不可修改
//		map = Maps.newHashMap();  // 不可重新指向新的引用对象
		
		map.put(1, 3);
		log.info("{}",map.get(1));
	}
	
	private void test(final int a) {
//		a = 1;   不允许修改
	}

}
