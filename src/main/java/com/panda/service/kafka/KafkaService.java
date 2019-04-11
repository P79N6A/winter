package com.panda.service.kafka;

import java.util.Properties;

import org.apache.kafka.common.security.JaasUtils;

import kafka.admin.AdminUtils;
import kafka.utils.ZkUtils;

public class KafkaService {

    /**
     * jdk1.8以下报错：java.lang.UnsupportedClassVersionError
     */

    static String ZK_CONNECT = "127.0.0.1:2181,127.0.0.1:2182";
    static int SESSION_TIMEOUT = 30000;
    static int CONNECT_TIMEOUT = 30000;

    public static void main(String[] args) {
        String topic = "stock-action";
        createTopic(topic, 2, 2, new Properties());
        //deleteTopic(topic);
    }

    public static void createTopic(String topic, int partition, int repilca, Properties properties) {
        ZkUtils zkUtils = null;
        try {
            zkUtils = ZkUtils.apply(ZK_CONNECT, SESSION_TIMEOUT, CONNECT_TIMEOUT, JaasUtils.isZkSecurityEnabled());
            if (AdminUtils.topicExists(zkUtils, topic)) {
                System.out.println(topic + " 已经存在！");
            } else {
                AdminUtils.createTopic(zkUtils, topic, partition, repilca, properties, AdminUtils.createTopic$default$6());
                System.out.println(topic + " 创建成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            zkUtils.close();
        }
    }

    public static void deleteTopic(String topic) {
        ZkUtils zkUtils = null;
        try {
            zkUtils = ZkUtils.apply(ZK_CONNECT, SESSION_TIMEOUT, CONNECT_TIMEOUT, JaasUtils.isZkSecurityEnabled());
            if (!AdminUtils.topicExists(zkUtils, topic)) {
                System.out.println(topic + " 不存在！");
            } else {
                AdminUtils.deleteTopic(zkUtils, topic);
                System.out.println(topic + " 删除成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            zkUtils.close();
        }
    }
}
