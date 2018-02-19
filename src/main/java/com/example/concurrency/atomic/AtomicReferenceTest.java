package com.example.concurrency.atomic;

import java.util.concurrent.atomic.AtomicReference;

import com.example.model.User;

/**
 * 引用类的原子性更新
 * @author OKali
 *
 */
public class AtomicReferenceTest {

	public static AtomicReference<User> atomicReference = new AtomicReference<User>();
	
	public static void main(String[] args) {
		User user = new User("alison", 15);
		atomicReference.set(user);
		User newUser = new User("pamgo", 26);
		atomicReference.compareAndSet(user, newUser);
		System.out.println(atomicReference.get().getUsrname());
		System.out.println(atomicReference.get().getAge());
	}
	
}
