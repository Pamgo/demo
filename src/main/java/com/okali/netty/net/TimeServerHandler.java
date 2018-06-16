package com.okali.netty.net;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * netty时间服务器
 * @author OKali
 *
 */
@Slf4j
public class TimeServerHandler extends ChannelInboundHandlerAdapter {
	
	//当Channel上的某个读操作完成时被调用
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		ByteBuf buf = (ByteBuf) msg;
		
		byte[] req = new byte[buf.readableBytes()];
		buf.readBytes(req);
		String body = new String(req, "UTF-8");
		log.info("The time server receive order : " + body);
		String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? dateFormat.format(new Date()) : "BAD ORDER";
		ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
		ctx.write(resp);
	}
	
	// 当Channel上的某个读操作完成时被调用
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}
	
	// 处理过程中ChannelPipeline中发生错误时被调用 
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.close();
	}
}
