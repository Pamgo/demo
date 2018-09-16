package com.okali.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.rmi.server.SocketSecurityException;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.apache.ibatis.annotations.SelectKey;


import lombok.extern.slf4j.Slf4j;

/**
 * 多路复用类，负责轮询多路复用器Selector ,可以处理多个客户端的并发接入。
 * @author OKali
 *
 */
@Slf4j
public class MultiplexerTimeServer implements Runnable {

	// 多路复用器
	private Selector selector;
	
	private ServerSocketChannel servChannel;
	
	private volatile boolean stop;
	
	public MultiplexerTimeServer(int port) {
		try {
			selector = Selector.open();   				// 创建多路复用器 Selector
			servChannel = ServerSocketChannel.open();   // 创建多路复用器ServerSocketChannel
			servChannel.configureBlocking(false);       // 设置为非阻塞
			servChannel.socket().bind(new InetSocketAddress(port), 1024);
			servChannel.register(selector, SelectionKey.OP_ACCEPT);	// 将channel注册到Selector,监听SelectionKey.OP_ACCEPT操作位
			log.info("The time server is start in port:" + port);
		} catch (IOException e) {
			log.error("构造函数出错。。。", e);
			System.exit(1); // 如果初始化失败，则退出
		}
	}
	
	@Override
	public void run() {
		while (!stop) {
			try {
				selector.select(1000); // 当注册事件到达时，方法返回，该方法会阻塞一秒
				Set<SelectionKey> keys = selector.selectedKeys();
				Iterator<SelectionKey> it = keys.iterator();
				SelectionKey key = null;
				while(it.hasNext()) {
					key = it.next();
					it.remove();
					try {
						handleInput(key);
					} catch (Exception e) {
						if (key != null) {
							key.cancel();
							if (key.channel() != null) {
								key.channel().close();
							}
						}
					}
				}
			} catch (IOException e) {
				log.error("轮询出错。。。", e);
			}
		}
		
		// 多路复用器关闭后，所有注册在上面的channel和Pige等资源都会被自动去注册并关闭，所以不需要重复释放资源
		if (selector != null) {
			try {
				selector.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void handleInput(SelectionKey key) throws IOException {
		if(key.isValid()) {
			// 处理新接入的请求消息
			if (key.isAcceptable()) {
				// 接收新连接
				ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
				SocketChannel sc = ssc.accept();	     // 完成TCP三次握手，TCP物理链路正式建立
				sc.configureBlocking(false);
				
				// 向Selector注册监听读操作
				sc.register(selector, SelectionKey.OP_READ);
			} 
			if (key.isReadable()) {   // 读取客户端数据
				// 读取数据
				SocketChannel sc = (SocketChannel) key.channel();
				ByteBuffer readBuffer = ByteBuffer.allocate(1024);
				int readBytes = sc.read(readBuffer);  // 将数据读到buffer
				if (readBytes > 0) {
					readBuffer.flip(); // 将缓冲区当前的limit设置为position，position设置为0，用于后续对缓冲区的读取操作。
					byte[] bytes = new byte[readBuffer.remaining()];
					readBuffer.get(bytes);  // 将缓冲区可读的字节数组复制到新创建的字节数组中
					String body = new String (bytes,"UTF-8");
					log.info("the time server receive order:" + body);
					
					String currentTime = "QUERY-TIME-ORDER".equalsIgnoreCase(body) ? new Date(System.currentTimeMillis()).toString() : " BAD ORDER";
					
					// 将应答消息发送给客户端
					doWrite(sc, body+currentTime);
				} else if (readBytes < 0) {   // 表示链路已经关闭
					// 对端链路关闭
					key.cancel();
					sc.close();
				} else
					;  // 读到0字节，忽略
			}
		}
	}
	
	private void doWrite(SocketChannel channel, String response) throws IOException {
		if (response != null && response.trim().length() > 0) {
			byte[] bytes = response.getBytes();    // 将字符串编码成字节数组
			ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
			writeBuffer.put(bytes);    // 将数组赋值到缓冲区中
			writeBuffer.flip();
			channel.write(writeBuffer);   // TODO 可能会出现半包问题
		}
	}
	
	public void stop() {
		this.stop = true;
	}

}
