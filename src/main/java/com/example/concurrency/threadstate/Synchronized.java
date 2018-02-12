package com.example.concurrency.threadstate;

public class Synchronized {

	public static void main(String[] args) {
		synchronized (Synchronized.class) {
		}
		m();
	}
	
	public static synchronized void m() {}
}
