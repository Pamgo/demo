package com.example.concurrency.st;

/**
 * 计算两个数的商
 * @author OKali
 * 用于线程池打印堆栈信息的案例
 */
public class DivTsk implements Runnable{
	int a,b;
	
	public DivTsk(int a, int b) {
		this.a = a;
		this.b = b;
	}

	@Override
	public void run() {
		double re = a/b;
		System.out.println(re);
	}

}
