package com.panda.service.kafka;

import java.util.Arrays;
import java.util.Collection;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

public class QuatationConsumer {

	/**
	 * kafka集群地址
	 */
	static String BROKER_LIST = "127.0.0.1:9092,127.0.0.1:9093";
	static String TOPIC = "stock-action";
	static KafkaConsumer<String, String> kafkaConsumer = null;
	
	static{
		kafkaConsumer = new KafkaConsumer<>(initConfig());
	}
	
	public static void main(String[] args) {
		kafkaConsumer.subscribe(Arrays.asList(TOPIC), new ConsumerRebalanceListener() {
			@Override
			public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
				//提交偏移量
				kafkaConsumer.commitSync();
			}
			@Override
			public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
				long commitOffset = -1;
				for(TopicPartition partition : partitions){
					commitOffset = kafkaConsumer.committed(partition).offset();
					kafkaConsumer.seek(partition, commitOffset);
				}
			}
		});
		while(true){
			ConsumerRecords<String, String> records = kafkaConsumer.poll(1000);
			System.out.println(records.count());
			for(ConsumerRecord<String, String> record : records){
				System.out.println(record.offset()+"|"+record.partition()+"|"+record.key()+":"+record.value());
			}
		}
	}
	
	private static Properties initConfig(){
		Properties properties = new Properties();
		properties.put("bootstrap.servers", BROKER_LIST);
		properties.put("group.id", "stockG1");
		properties.put("client.id", "stockC1");
		
		//设置反序列化类
		properties.put("key.deserializer", StringDeserializer.class.getName());
		properties.put("value.deserializer", StringDeserializer.class.getName());
		
		return properties;
	}
}
