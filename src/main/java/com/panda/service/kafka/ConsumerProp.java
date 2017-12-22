package com.panda.service.kafka;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsumerProp<K, V> extends HashMap<K, V>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7865087813347510325L;

	private static final Logger logger = LoggerFactory.getLogger(ConsumerProp.class);
	//消息类型
	private String messageModel;
	
	public ConsumerProp(){};
	
	public ConsumerProp(Map<? extends K, ? extends V> m) {
		super(m);
	}
	
	@PostConstruct
	public void initGroup(){
		String groupKey = "group.id";
		String ip = "";
		try {
			ip = InetAddress.getLocalHost().getCanonicalHostName();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		};
		if(MessageModel.BROADCASTING.toString().equalsIgnoreCase(messageModel)){
			this.put((K)groupKey, (V)(this.get(groupKey) + "-" + ip));
		}
		logger.info("消费组 group.id={}",this.get(groupKey));
	}
	
	public String getMessageModel() {
		return messageModel;
	}


	public void setMessageModel(String messageModel) {
		this.messageModel = messageModel;
	}


	static enum MessageModel {
	    /**
	     * 广播模型
	     */
	    BROADCASTING,
	    /**
	     * 集群模型
	     */
	    CLUSTERING,
	 }
}
