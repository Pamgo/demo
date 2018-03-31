package com.okali.concurrency.deadLock;

import lombok.extern.slf4j.Slf4j;

/**
 * 一个简单的死锁
 * 当DeadLock类的对象flag == 1是（dl1），先锁定o1，睡眠500毫秒
 * 而dl1在睡眠的时候另一个flag== 0 的对象（dl2）线程启动，先锁定o2,睡眠500毫秒
 * dl1睡眠结束后需要锁定o2才能继续执行，而此时o2已经被dl1锁定；
 * dl2睡眠结束后需要锁定o1才能继续执行，而此时o1已经被dl2锁定；
 * dl1,dl2相互等待，都需要得到对方锁定的资源才能继续执行，从而死锁
 * @author OKali
 *
 */
@Slf4j
public class DeadLock implements Runnable {
	public int flag = 1;
	// 静态对象是类的所有对象共享的
	private static Object o1 = new Object(), o2 = new Object();
	
	@Override
	public void run() {
		log.info("flag : {}", flag);
		if (flag == 1) {
			synchronized (o1) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized (o2) {
					log.info("--1");
				}
			}
		}
		if (flag == 0) {
			synchronized (o2) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized (o1) {
					log.info("--0");
				}
			}
		}
	}

	public static void main(String[] args) {
		DeadLock dl1 = new DeadLock();
		DeadLock dl2 = new DeadLock();
		
		dl1.flag = 0;
		dl2.flag = 1;
		
		// dl1,dl2都处于可执行的状态，但JVM线程调度先执行哪个是不确定的
		// dl2的run()可能在dl1的run()之前执行
		new Thread(dl1).start();
		new Thread(dl2).start();
	}
}
