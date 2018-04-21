package com.okali.netty.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;

/**
 * 处理请求
 * @author OKali
 *
 */
@Slf4j
public class TimeServerThread implements Runnable {

	private Socket socket;
	
	public TimeServerThread(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		BufferedReader reader = null;
		PrintWriter writer = null;
		try {
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			writer = new PrintWriter(socket.getOutputStream(), true);
			
			String currentTime = null;
			String body = null;
			while (true) {
				body = reader.readLine();
				if (body == null) {
					break;
				}
				log.info("The time server receive order:{}", body);
				currentTime = "QUERY TIME ORDER".equals(body) ? new Date(System.currentTimeMillis()).toString() : "BAD ORDER";
				writer.println(currentTime);
			}
		} catch (Exception e) {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (writer != null) {
				writer.close();
				writer = null;
			}
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				socket = null;
			}
		} 
	}

}
