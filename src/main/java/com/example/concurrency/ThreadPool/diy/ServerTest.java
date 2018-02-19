package com.example.concurrency.ThreadPool.diy;

import java.io.IOException;

public class ServerTest {

	public static void main(String[] args) throws IOException {
		SimpleHttpServer.setPort(8888);
		SimpleHttpServer.setBasePath("C:\\Users\\pamgo\\Desktop\\test\\");
		SimpleHttpServer.start();
	}
}
