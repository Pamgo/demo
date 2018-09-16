package com.okali.netty.course.test;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * 引导配置客户端
 * @author OKali
 *
 */
public class BoostrapingClient {

    public static void main(String[] args) {
    	
    	EventLoopGroup group = new NioEventLoopGroup();
    	
    	Bootstrap b = new Bootstrap();
    	b.group(group).channel(NioSocketChannel.class).handler(new SimpleChannelInboundHandler<ByteBuf>() {
    		
    		protected void channelRead0(io.netty.channel.ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
    			System.out.println("Received data");
    			msg.clear();
    		};
		});
    	
    	ChannelFuture f = b.connect("192.168.1.103", 8080);
    	f.addListener(new ChannelFutureListener() {
			
			@Override
			public void operationComplete(ChannelFuture future) throws Exception {
				if (future.isSuccess()) {
					System.out.println("connection finished");
				} else {
					System.out.println("connection failed");
					future.cause().printStackTrace();
				}
			}
		});
    	
    }
}