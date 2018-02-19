package com.example.concurrency.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * 数组原子性操作
 * @author OKali
 * 需要注意的是，数组value通过构造方法传递进去，然后AtomicIntegerArray会将当前数组
复制一份，所以当AtomicIntegerArray对内部的数组元素进行修改时，不会影响传入的数组。
 *
 */
public class AtomicIntegerArrayTest {
	static int[] value = new int[] {1,2};
	static 	AtomicIntegerArray array = new AtomicIntegerArray(value);

	public static void main(String[] args) {
//		array.set(0, 3);
		array.addAndGet(0, 3);
		System.out.println(array.get(0));
		System.out.println(array.get(1));
		System.out.println(value[0]);
	}
}
