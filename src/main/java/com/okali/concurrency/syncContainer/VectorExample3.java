package com.okali.concurrency.syncContainer;

import java.util.Iterator;
import java.util.Vector;

import com.annotation.NotThreadSafe;

import lombok.extern.slf4j.Slf4j;

/**
 *  对一个遍历的同时，对集合进行更像操作。
 *  如果我们使用了foreach、iterator迭代器循环来循环我们的集合的时候，
 *   尽量不要在操作的过程中进行remove相关的更新操作。
 *  如果一定要进行更新的话：建议方案为，在遍历的过程中把需要的值进行标志，遍历完之后再进行更新操作。
 *  推荐使用for循环
 * 
 * @author OKali
 *
 */
@Slf4j
public class VectorExample3 {
	
	private static Vector<Integer> vector = new Vector<>();
	
	// java.util.ConcurrentModificationException
	private static void test1(Vector<Integer> v1) {  //foreach
		for (Integer i : v1) {
			if (i.equals(3)) {
				v1.remove(i);
			}
		}
	}
	
	//java.util.ConcurrentModificationException
	private static void test2(Vector<Integer> v2) { // iterator
		Iterator<Integer> iterator = v2.iterator();
		while (iterator.hasNext()) {
			Integer i = iterator.next();
			if (i.equals(3)) {
				v2.remove(i);
			}
		}
	}
	
	// 正常 success
	private static void test3(Vector<Integer> v3) {
		for (int i = 0; i < v3.size(); i++) {
			if (v3.get(i).equals(3)) {
				v3.remove(i);
			}
		}
	}
	
	public static void main(String[] args) {
		Vector<Integer> v = new Vector<>();
		v.add(1);
		v.add(2);
		v.add(3);
		test3(v);
	}

}
