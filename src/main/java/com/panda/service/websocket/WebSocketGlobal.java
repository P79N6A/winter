package com.panda.service.websocket;

import java.util.HashSet;
import java.util.Set;

import org.jboss.netty.channel.Channel;

public class WebSocketGlobal {

    public static Set<Channel> ctxs = new HashSet<>();
}
