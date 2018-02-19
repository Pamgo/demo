package com.example.concurrency.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * 原子性更新类字段：AtomicIntegerFieldUpdater
 * @author OKali <br />
 * 注意 :  <b>更新类的字段（属性）必须使用public volatile修饰</b>
 *
 */
public class AtomicIntegerFieldUpdateTest {
	
	private static AtomicIntegerFieldUpdater<User> atomicIntegerFieldUpdater = 
			AtomicIntegerFieldUpdater.newUpdater(User.class, "old");
	
	public static void main(String[] args) {
		User user = new User("alison", 10);
		
		System.out.println(atomicIntegerFieldUpdater.getAndIncrement(user));
		System.out.println(atomicIntegerFieldUpdater.get(user));
		
	}

	public static class User {
		private String name;
		public volatile int old;  // 关注点
		
		public User(String name, int old) {
			this.name = name;
			this.old = old;
		}
		
		public String getName() {
			return name;
		}
		
		public int getOld() {
			return old;
		}
	}
}
