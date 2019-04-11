package com.panda.service.zk.curator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.data.Stat;

public class CuratorClient {

    public static void main(String[] args) {
        //master();
        distributedlock();
    }

    /**
     * master选举
     */
    public static void master() {
        String path = "/book_master";
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("127.0.0.1:2181")
                .sessionTimeoutMs(5000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .build();
        client.start();
        LeaderSelector selector = new LeaderSelector(client, path, new LeaderSelectorListenerAdapter() {
            @Override
            public void takeLeadership(CuratorFramework arg0) throws Exception {
                System.out.println("成为master!");
                System.out.println("操作...");
                System.out.println("释放master！");
            }
        });
        //自动重新排队竞争
        selector.autoRequeue();
        selector.start();
        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 分布式锁
     */
    public static void distributedlock() {
        String path = "/book_lock";
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("127.0.0.1:2181")
                .sessionTimeoutMs(5000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .build();
        client.start();

        final InterProcessMutex lock = new InterProcessMutex(client, path);
        final CountDownLatch latch = new CountDownLatch(1);
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        latch.await();
                        lock.acquire();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss:SSS");
                    String orderNo = format.format(new Date());
                    System.out.println(orderNo);
                    try {
                        lock.release();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        latch.countDown();
    }

    public static void get() {
        String path = "/book";
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("127.0.0.1:2181")
                .sessionTimeoutMs(5000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .build();
        client.start();
        Stat stat = new Stat();
        try {
            System.out.println(new String(client.getData().storingStatIn(stat).forPath(path)));
            client.setData().forPath(path, "oo".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
