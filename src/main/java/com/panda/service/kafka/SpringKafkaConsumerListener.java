package com.panda.service.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.listener.MessageListener;

public class SpringKafkaConsumerListener implements MessageListener<String, String>{

	private static final Logger logger = LoggerFactory.getLogger(SpringKafkaConsumerListener.class);
	
	@Override
	public void onMessage(ConsumerRecord<String, String> data) {
		if(data != null){
			logger.info("消费成功：主题：{}，分区：{}，时间：{}，内容：{}",data.topic(),data.partition(),data.timestamp(),data.value());
		}
	}

}
