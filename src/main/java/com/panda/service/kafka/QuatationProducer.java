package com.panda.service.kafka;

import java.text.DecimalFormat;
import java.util.Properties;
import java.util.Random;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import com.panda.service.kafka.bean.StockInfo;


public class QuatationProducer {

	/**
	 * kafka集群地址
	 */
	static String BROKER_LIST = "127.0.0.1:9092,127.0.0.1:9093";
	static String TOPIC = "stock-action";
	static int MSG_SIZE = 10;
	static KafkaProducer<String, String> producer = null;
	
	static{
		producer = new KafkaProducer<>(initProperties());
	}
	
	public static void main(String[] args) {
		ProducerRecord<String, String> record = null;
		StockInfo stockInfo = null;
		try {
			int num = 0;
			for(int i=0; i<MSG_SIZE; i++){
				stockInfo = createStock();
				record = new ProducerRecord<String, String>(TOPIC,null,stockInfo.getTradeTime(),stockInfo.getStockCode(),stockInfo.toString());
				//producer.send(record);
				producer.send(record, new Callback() {
					@Override
					public void onCompletion(RecordMetadata metadata, Exception exception) {
						if(null != exception){
							exception.printStackTrace();
						}
						if(null != metadata){
							System.out.println("offset:" + metadata.offset() + " partition:" + metadata.partition());
						}
					}
				});
//				if(num++ % 10 == 0){
//					Thread.sleep(2000);
//				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			producer.close();
		}
	}
	
	/**
	 * 初始化kafka
	 * @return
	 */
	private static Properties initProperties(){
		Properties properties = new Properties();
		//kafka集群
		properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BROKER_LIST);
		//设置序列化类
		properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		return properties;
	}
	
	private static StockInfo createStock(){
		StockInfo info = new StockInfo();
		info.setStockCode("00070" + new Random().nextInt(10));
		float random = (float) Math.random();
		if(random / 2 < 0.5){
			random = -random;
		}
		DecimalFormat decimalFormat = new DecimalFormat(".00");
		info.setPrice(Float.valueOf(decimalFormat.format(400+random)));
		info.setTradeTime(System.currentTimeMillis());
		return info;
	}
}
