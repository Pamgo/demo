package com.okali.concurrency.immutable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import lombok.extern.slf4j.Slf4j;

/**
 * ImmutableXX不可变对象
 * @author OKali
 *
 */
@Slf4j
public class ImmutableExample3 {
	
	private final static ImmutableList list = ImmutableList.of(1,2,3);
	
	private final static ImmutableSet set = ImmutableSet.copyOf(list);
	
	private final static ImmutableMap<Integer, Integer> map = ImmutableMap.of(1, 2, 3, 4); // 成对出现
	
	private final static ImmutableMap<Integer, Integer> map2 = ImmutableMap.<Integer, Integer>builder()
			.put(1,2).put(3,4).build();
	
	public static void main(String[] args) {
//		list.add(4);  不能使用
//		set.add(4); 不能使用
//		map.put(1, 3); 不能使用
		log.info("{}", map.get(1));
	}
	
}
