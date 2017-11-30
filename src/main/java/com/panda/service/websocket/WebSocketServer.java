package com.panda.service.websocket;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebSocketServer {

	private static final Logger logger = LoggerFactory.getLogger(WebSocketServer.class);
	
	private final int port;
	public static ChannelHandlerContext ctx = null;

	public WebSocketServer(int port){
		this.port = port;
	}
	
	public void start(){
		ServerBootstrap bootstrap = new ServerBootstrap(new NioServerSocketChannelFactory(Executors.newCachedThreadPool(),Executors.newCachedThreadPool()));
		bootstrap.setPipelineFactory(new WebSocketServerPipelineFactory());
		bootstrap.bind(new InetSocketAddress(port));
		logger.info("websocket server started at port {}",port);
	}
	
}
