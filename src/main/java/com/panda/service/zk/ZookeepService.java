package com.panda.service.zk;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.ZooKeeper;

public class ZookeepService implements Watcher{
	
	//private static final Logger logger = LoggerFactory.getLogger(ZookeepService.class);
	
	static final CountDownLatch  connectedSemaphore = new CountDownLatch(1);
	
	public static void main(String[] args)  {
		//构造函数初始化客户端立即返回,此时会话处于connecting
		ZooKeeper zooKeeper = null;
		try {
			zooKeeper = new ZooKeeper("127.0.0.1:2181,127.0.0.1:2182", 5000, new ZookeepService());
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			connectedSemaphore.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("connect establish!");
		//System.out.println(zooKeeper.getSessionId());
		
		//create(zooKeeper);
		//ls(zooKeeper);
		get(zooKeeper);
		//update(zooKeeper);
		//exits(zooKeeper);
		//auth(zooKeeper);
	    //auth_delete(zooKeeper);
	}
	
	
	public static void auth_delete(ZooKeeper zooKeeper){
		String path = "/auth_d_test";
		String pathchild = "/auth_d_test/child";
		zooKeeper.addAuthInfo("digest", "foo:true".getBytes());
		try {
			zooKeeper.exists(path, true);
			//只有永久节点才能加子节点
			zooKeeper.create(path, "init".getBytes(), Ids.CREATOR_ALL_ACL, CreateMode.PERSISTENT);
			zooKeeper.exists(pathchild, true);
			zooKeeper.create(pathchild, "initchild".getBytes(), Ids.CREATOR_ALL_ACL, CreateMode.EPHEMERAL);
			//添加watcher
			zooKeeper.exists(pathchild, true);
			zooKeeper.exists(path, true);
			ZooKeeper zooKeeper2 = new ZooKeeper("127.0.0.1:2181,127.0.0.1:2182",5000,null);
			try {
				zooKeeper2.addAuthInfo("digest", "foo:true".getBytes());
				zooKeeper2.delete(pathchild, -1);
				//此处的zookeeper3并没有权限，却可以删除path，说明删除操作的权限控制比较特殊：对一个节点添加权限信息后，对于删除操作，其作用范围是子节点
				ZooKeeper zooKeeper3 = new ZooKeeper("127.0.0.1:2181,127.0.0.1:2182",5000,null);
				zooKeeper2.delete(path, -1);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
//			zooKeeper.create(pathchild, "initchild".getBytes(), Ids.CREATOR_ALL_ACL, CreateMode.EPHEMERAL);
//			ZooKeeper zooKeeper3 = new ZooKeeper("127.0.0.1:2181,127.0.0.1:2182",5000,null);
//			zooKeeper3.addAuthInfo("digest", "foo:true".getBytes());
//			zooKeeper3.delete(pathchild, -1);
//			System.out.println("delete success " + pathchild);
//			
//			
//			try {
//				zooKeeper2.delete(path, -1);
//				System.out.println("delete success " + path);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
		} catch (KeeperException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static void auth(ZooKeeper zooKeeper){
		String path = "/auth_test";
		zooKeeper.addAuthInfo("digest", "foo:true".getBytes());
		try {
			zooKeeper.create(path, "init".getBytes(), Ids.CREATOR_ALL_ACL, CreateMode.EPHEMERAL);
			ZooKeeper zooKeeper2 = new ZooKeeper("127.0.0.1:2181,127.0.0.1:2182",5000,null);
			zooKeeper2.addAuthInfo("digest", "foo:true".getBytes());
			System.out.println(zooKeeper2.getData(path, false, null));
			ZooKeeper zooKeeper3 = new ZooKeeper("127.0.0.1:2181,127.0.0.1:2182",5000,null);
			//无权限查询
			System.out.println(zooKeeper3.getData(path, false, null));
			
		} catch (KeeperException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static void exits(final ZooKeeper zooKeeper){
		final String path = "/student";
		try {
			//注册watcher三种方式：exists、getData、getChildren，
			//前两个是对本节点监测，exsit监测创建、删除，getData监测数据或状态变更;getChildren监测子节点
			//watcher是一次性的，触发之后会被移除,watcher的使用一般是反复注册
			zooKeeper.exists(path, true);
			zooKeeper.create(path, "ss".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			Stat stat = new Stat();
			
			zooKeeper.getData(path, true, stat);
			stat = zooKeeper.setData(path, "sss".getBytes(), stat.getVersion());
			zooKeeper.getData(path, true, stat);
			zooKeeper.delete(path, stat.getVersion());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void update(ZooKeeper zooKeeper){
		try {
			Stat stat = new Stat();
			zooKeeper.getData("/book", null, stat);
			System.out.println(stat.getVersion());
			Stat stat2 = zooKeeper.setData("/book", "kk".getBytes(), stat.getVersion());
			System.out.println(stat2.getVersion());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void get(ZooKeeper zooKeeper){
		try {
			Stat stat = new Stat();
			byte[] data = zooKeeper.getData("/book", null, stat);
			System.out.println(new String(data));
			System.out.println(stat.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void ls(ZooKeeper zooKeeper){
		try {
			List<String> children = zooKeeper.getChildren("/book", new Watcher() {
				@Override
				public void process(WatchedEvent event) {
					System.out.println(event.getPath());
					System.out.println(event.getState());
					System.out.println(event.getType());
				}
			});
			System.out.println(children);
			zooKeeper.create("/book/english", "ee".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
			Thread.sleep(500);
		} catch (KeeperException | InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void create(ZooKeeper zooKeeper){
		try {
			String path1 = zooKeeper.create("/book/maths", "123".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
			System.out.println("create success: " + path1);
			String path2 = zooKeeper.create("/book/maths", "456".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
			System.out.println("create success: " + path2);
		} catch (KeeperException | InterruptedException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void process(WatchedEvent event) {
		if(KeeperState.SyncConnected == event.getState()){
			if(EventType.None == event.getType() && null == event.getPath()){
				//收到通知才表示会话真正建立
				connectedSemaphore.countDown();
			}else if(event.getType() == EventType.NodeCreated){
				System.out.println("create " + event.getPath());
			}else if (event.getType() == EventType.NodeDataChanged) {
				System.out.println("update " + event.getPath());
				
			}else if (event.getType() == EventType.NodeDeleted) {
				System.out.println("delete " + event.getPath());
			}
		}
	}
}