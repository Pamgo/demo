package com.okali.netty.course.wx;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * WebSocket,初始化ChannelHandlelr
 * @author OKali
 *
 */
public class ChatServerInitializer extends ChannelInitializer<Channel>{
	
	private final ChannelGroup group;
	
	public ChatServerInitializer(ChannelGroup group) {
		this.group = group;
	}

	@Override
	protected void initChannel(Channel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		
		// 编码解码http请求
		pipeline.addLast(new HttpServerCodec());
		
		// 写文件内容
		pipeline.addLast(new ChunkedWriteHandler());
		
		// 聚合解码 HttpRequest/HttpContent/LastHttpContent 到FullHttpRequest
		// 保证接收的Http完整性
		pipeline.addLast(new HttpObjectAggregator(64 * 1024));
		// 处理FullHttpRequest
		pipeline.addLast(new HttpRequestHandler("/ws"));
		// 处理其它的websocketFrame
		pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
		// 处理TextWebSocketFrame
		pipeline.addLast(new TextWebSocketFrameHandler(group));
		
	}

}
