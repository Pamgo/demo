package com.okali.netty.course.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.yaml.snakeyaml.events.DocumentStartEvent;

import lombok.extern.slf4j.Slf4j;

/**
 * 服务器端
 * @author OKali
 *
 */
@Slf4j
public class Server {
	
	private ServerSocket serverSocket;
	
	public Server(int port) {
		try {
			this.serverSocket = new ServerSocket(port);
			log.info("服务端启动成功，端口：" + port);
		} catch (IOException e) {
			log.error("服务器端启动失败", e);
		}
	}
	
	public void start() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				doStart();
			}
		}).start();
	}

	private void doStart() {
		while(true) {
			try {
				Socket socket = serverSocket.accept();
				new ClientHandler(socket).start();
			} catch (IOException e) {
				log.info("服务端异常", e);
			}
		}
	}
	
	public static void main(String[] args) {
		new Server(8000).start();
	}
}
