package com.okali.netty.aio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class AcceptCompletionHandler implements CompletionHandler<AsynchronousSocketChannel, Runnable>{

	@Override
	public void completed(AsynchronousSocketChannel result, Runnable attachment) {
		AsyncTimeServerHandler serverHandler = (AsyncTimeServerHandler) attachment;
		serverHandler.ssChannel.accept(attachment, this);
		
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		result.read(buffer, buffer, new ReadCompletionHandler(result));
	}

	@Override
	public void failed(Throwable exc, Runnable attachment) {
		exc.printStackTrace();
		AsyncTimeServerHandler serverHandler = (AsyncTimeServerHandler) attachment;
		serverHandler.latch.countDown();
	}

}
