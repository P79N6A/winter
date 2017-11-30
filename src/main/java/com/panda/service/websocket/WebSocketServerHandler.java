package com.panda.service.websocket;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.jboss.netty.handler.codec.http.HttpRequest;
import org.jboss.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import org.jboss.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.jboss.netty.handler.codec.http.websocketx.WebSocketFrame;
import org.jboss.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import org.jboss.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebSocketServerHandler extends SimpleChannelUpstreamHandler{

	private static final Logger logger = LoggerFactory.getLogger(WebSocketServerHandler.class);
	private static final String WEBSOCKET_PATH = "/note";
	private WebSocketServerHandshaker handshaker;
	
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
		Object message = e.getMessage();
		String receivedMessage = message.toString();
		logger.debug("recieve message from channel:" + receivedMessage);
		if(message instanceof HttpRequest){
			logger.info("http 请求");
			handleHttpRequest(ctx, (HttpRequest)message);
		}else if(message instanceof WebSocketFrame){
			logger.info("ws 请求");
			if(message instanceof CloseWebSocketFrame){
				handshaker.close(ctx.getChannel(), (CloseWebSocketFrame)message);
				WebSocketServer.ctx = null;
			}else{
				ctx.getChannel().write(new TextWebSocketFrame("收到！"));
			}
			
		}
	}
	
	private void handleHttpRequest(ChannelHandlerContext ctx,HttpRequest req){
		WebSocketServerHandshakerFactory wsfactory = new WebSocketServerHandshakerFactory(getWebSocketLocation(req), null, false);
		handshaker = wsfactory.newHandshaker(req);
		if(handshaker == null){
			wsfactory.sendUnsupportedWebSocketVersionResponse(ctx.getChannel());
		}else {
			handshaker.handshake(ctx.getChannel(), req).addListener(WebSocketServerHandshaker.HANDSHAKE_LISTENER);
		}
		WebSocketServer.ctx = ctx;
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
		e.getCause().printStackTrace();
		ctx.getChannel().close();
		WebSocketServer.ctx = null;
	}

	@Override
	public void channelOpen(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		WebSocketGlobal.ctxs.add(ctx.getChannel());
		logger.info("建立连接：{}",ctx.getChannel());
	}

	@Override
	public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		WebSocketGlobal.ctxs.remove(ctx.getChannel());
		logger.info("断开连接：{}",ctx.getChannel());
	}

	private String getWebSocketLocation(HttpRequest req){
		String url =  "ws://" + req.headers().get("host") + WEBSOCKET_PATH;
		logger.info("ws 地址：{}",url);
		return url;
	}
	
}
