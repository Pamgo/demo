package com.okali.netty.course.wx;


import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.ssl.SslHandler;

/**
 * web socket 加密
 * @author OKali
 *
 */
public class SecureChatServerIntailizer extends ChatServerInitializer{
	
	private final SSLContext context;

	public SecureChatServerIntailizer(ChannelGroup group, SSLContext context) {
		super(group);
		this.context = context;
	}

	@Override
	protected void initChannel(Channel ch) throws Exception {
		super.initChannel(ch);
		SSLEngine engine = context.createSSLEngine();
		engine.setUseClientMode(false);
		ch.pipeline().addFirst(new SslHandler(engine));
	}
}
