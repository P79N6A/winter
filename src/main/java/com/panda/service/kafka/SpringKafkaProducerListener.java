package com.panda.service.kafka;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.support.ProducerListener;

public class SpringKafkaProducerListener implements ProducerListener<String, String> {

    private static final Logger logger = LoggerFactory.getLogger(SpringKafkaProducerListener.class);

    @Override
    public void onSuccess(String topic, Integer partition, String key, String value, RecordMetadata recordMetadata) {
        logger.info("委托成功：主题：{}，分区：{}，时间：{}，内容：{}", topic, recordMetadata.partition(), recordMetadata.timestamp(), value);
    }

    @Override
    public void onError(String topic, Integer partition, String key, String value, Exception exception) {
        logger.info("消息发送失败：topic:{},value:{},exception:{}", topic, value, exception.getLocalizedMessage());
    }

    @Override
    public boolean isInterestedInSuccess() {
        return true;
    }

}
