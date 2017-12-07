package com.panda.service.zk.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.data.Stat;

public class CuratorClient {

	public static void main(String[] args) {
		String path = "/book";
		CuratorFramework client = CuratorFrameworkFactory.builder()
								  .connectString("127.0.0.1:2181")
								  .sessionTimeoutMs(5000)
								  .retryPolicy(new ExponentialBackoffRetry(1000, 3))
								  .build();
		client.start();
		Stat stat = new Stat();
		try {
			System.out.println(new String(client.getData().storingStatIn(stat).forPath("/book")));
			client.setData().forPath("/book","oo".getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
