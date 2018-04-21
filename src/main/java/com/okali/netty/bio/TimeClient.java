package com.okali.netty.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeClient {

	public static void main(String[] args) {
		int port = 8080;
		if (args != null && args.length > 0) {
			port = Integer.valueOf(args[0]);
		}
		
		Socket socket = null;
		BufferedReader reader = null;
		PrintWriter writer = null;
		
		try {
			socket = new Socket("127.0.0.1", port);
			
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new PrintWriter(socket.getOutputStream(), true);
			
			writer.println("QUERY TIME ORDER");
			log.info("Send order2 server successed");
			String resp = reader.readLine();
			log.info("Now is:{}", resp);
		} catch (Exception e) {} finally {
			if (writer != null) {
				writer.close();
				writer = null;
			}
			
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				reader = null;
			}
			
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				socket = null;
			}
		}
		
	}
}
