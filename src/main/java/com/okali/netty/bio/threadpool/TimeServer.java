package com.okali.netty.bio.threadpool;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.okali.netty.bio.TimeServerThread;

import lombok.extern.slf4j.Slf4j;

/**
 * 服务端
 * @author OKali
 *
 */
@Slf4j
public class TimeServer {

	public static void main(String[] args) {
		int port = 8080;
		if (args != null && args.length > 0) {
			port = Integer.valueOf(args[0]);
		}
		
		ServerSocket ss = null;
		try {
			ss = new ServerSocket(port);
			log.info("The time server is start in port:{}", port);
			Socket socket = null;
			TimeServerHandlerExecutePool handlerExecutePool = new TimeServerHandlerExecutePool(50, 10000);
			while (true) {
				socket = ss.accept();
				handlerExecutePool.execute(new TimeServerThread(socket));
			}
			
		} catch(Exception e) {} finally {
			if (ss != null) {
				log.info("The time server close");
				try {
					ss.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				ss = null;
			}
		}
	}
}
