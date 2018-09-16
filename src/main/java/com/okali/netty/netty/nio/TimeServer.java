package com.okali.netty.netty.nio;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

public class TimeServer {

	public void bind(int port) throws Exception {
		// 配置服务端的NIO线程组
		// 服务端持有两个EventLoopGroup，
		// 其中boss组是专门用来接收客户端发来的TCP链接请求的，
		// worker组是专门用来具体处理完成 三次握手的链接套接字的网络IO请求的
		EventLoopGroup bossGroup = new NioEventLoopGroup(1); //[线程组，默认2*cpu] Runtime.getRuntime().availableProcessors() * 2
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		
		try {
			ServerBootstrap b = new ServerBootstrap();  // 启动NIO服务端的辅助启动类，目的降低服务端的开发复杂度
			b.group(bossGroup,workerGroup)
				.channel(NioServerSocketChannel.class) // 注册到bossGroup上 // 设置服务端socketchannel
				.option(ChannelOption.SO_BACKLOG, 1024)
				.childHandler(new ChildChannelHandler());
			
			// 绑定端口，同步等待成功,服务端channel入口
			ChannelFuture f = b.bind(port).sync(); 
			
			// 等待服务器监听端口关闭
			f.channel().closeFuture().sync();
		} finally {
			// 优雅退出，释放线程池资源
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
	
	private class ChildChannelHandler extends ChannelInitializer<SocketChannel> {

		@Override
		protected void initChannel(SocketChannel arg0) throws Exception {
			arg0.pipeline().addLast(new LineBasedFrameDecoder(1024));  // 添加节码器
			arg0.pipeline().addLast(new StringDecoder());
			arg0.pipeline().addLast(new TimeServerHandler());
		}
	}
	
	public static void main(String[] args) throws Exception {
		int port = 7001;
		if (args != null && args.length > 0) {
			port = Integer.valueOf(args[0]);
		}
		new TimeServer().bind(port);
	}
}
