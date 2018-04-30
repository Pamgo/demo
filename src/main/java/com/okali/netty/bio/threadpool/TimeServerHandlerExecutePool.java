package com.okali.netty.bio.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

/**
 * 构建线程池处理客户端请求
 * @author OKali
 *
 */
@Slf4j
public class TimeServerHandlerExecutePool {

	private ExecutorService executorService;
	
	public TimeServerHandlerExecutePool(int maxPoolSize, int queueSize) {
		executorService = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), 
				maxPoolSize, 120L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(queueSize));
		
	}
	
	public void execute(Runnable task) {
		log.info("接收请求》》》》》");
		executorService.execute(task);
	}
}
