package com.okali.netty.course.wx;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;

/**
 * websocket，处理消息
 * @author OKali
 *
 */
public class TextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame>{
	
	private final ChannelGroup group;
	
	public TextWebSocketFrameHandler(ChannelGroup group) {
		this.group = group;
	}
	
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		// 如果websocket握手完成
		if (evt == WebSocketServerProtocolHandler.ServerHandshakeStateEvent.HANDSHAKE_COMPLETE) {
			// 删除ChannelPipeline中的HttpRequestHandler
			ctx.pipeline().remove(HttpRequestHandler.class);
			// 写一个消息到ChannelGroup
			group.writeAndFlush(new TextWebSocketFrame("client " + ctx.channel() + " joined"));
			
			// 将channel添加到group
			group.add(ctx.channel());
		} else {
			super.userEventTriggered(ctx, evt);
		}
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
		// 将接收的消息通过ChannelGroup转发到所有已连接的客户端
		group.writeAndFlush(msg.retain());
	}

}
