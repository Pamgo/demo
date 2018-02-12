package com.example.concurrency;

import java.util.concurrent.TimeUnit;

/**
 * 睡眠工具
 * @author OKali
 *
 */
public class SleepUtil {

	public static final void second(long seconds) {
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
