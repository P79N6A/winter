package com.panda.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

public class MyWebSocketHandler extends AbstractWebSocketHandler {

    private static final Logger logger = LoggerFactory.getLogger(MyWebSocketHandler.class);

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        logger.info("websocket断开连接");
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.info("websocket建立连接");
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        logger.info("收到信息：" + message.getPayload());
        session.sendMessage(new TextMessage("行动！"));
    }


}
