package com.okali.netty.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousChannel;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;

import lombok.extern.slf4j.Slf4j;

/**
 * 创建AIO时间服务器处理类
 * @author OKali
 *
 */
@Slf4j
public class AsyncTimeServerHandler implements Runnable {
	
	private int port;
	
	CountDownLatch latch;
	
	AsynchronousServerSocketChannel ssChannel;
	
	public AsyncTimeServerHandler(int port) {
		this.port = port;
		try {
			ssChannel = AsynchronousServerSocketChannel.open();
			ssChannel.bind(new InetSocketAddress(port));
			log.info("The time server is start in port:{}", port);
		} catch (IOException e) {
			log.error("启动服务端出错", e);
		}
	}

	@Override
	public void run() {
		latch = new CountDownLatch(1);
		doAccept();
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public void doAccept() {
		ssChannel.accept(this, new AcceptCompletionHandler());
	}

}
