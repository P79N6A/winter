package com.panda.cache;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisClientPool {
    private static JedisClientPool jedisClientPool = getInstance();
    private static JedisPool jedisPool;
    private static int maxTotal;
    private static int maxIdle;
    private static int maxWaitMillis;

    public static synchronized JedisClientPool getInstance() {
        if (null == jedisClientPool) {
            jedisClientPool = new JedisClientPool();
        }
        return jedisClientPool;
    }

    public JedisClientPool() {
        if (null == jedisPool) {
            init();
        }
    }

    /**
     * 初始化Jedis
     *
     * @return
     */
    private static JedisPoolConfig initPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // #最大分配的对象数 :控制一个pool最多有多少个状态为idle的jedis实例
        jedisPoolConfig.setMaxTotal(maxTotal);
        // 控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取
        // 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)
        jedisPoolConfig.setMaxIdle(maxIdle);
        // 当池内没有返回对象时，最大等待时间 : 超时时间
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        // 当调用borrow Object方法时，是否进行有效性检查:
        // 在borrow一个jedis实例时，是否提前进行alidate操作；如果为true，则得到的jedis实例均是可用的；
        jedisPoolConfig.setTestOnBorrow(true);
        // 当调用return Object方法时，是否进行有效性检查 :在还给pool时，是否提前进行validate操作
        jedisPoolConfig.setTestOnReturn(true);
        return jedisPoolConfig;
    }

    /**
     * 初始化jedis连接池
     */
    public static void init() {
//		maxTotal = Integer.parseInt(OptimusConfig.getValue("redis.maxTotal"));
//		maxIdle = Integer.parseInt(OptimusConfig.getValue("redis.maxIdle"));
//		maxWaitMillis = Integer.parseInt(OptimusConfig.getValue("redis.maxWaitMillis"));
//		JedisPoolConfig jedisPoolConfig = initPoolConfig();
//		String host = OptimusConfig.getValue("redis.host");
//		int port = Integer.parseInt(OptimusConfig.getValue("redis.port"));
//		int timeout = Integer.parseInt(OptimusConfig.getValue("redis.timeout"));
//		String pwd = StringUtil.isEmpty(OptimusConfig.getValue("redis.password"))?null:OptimusConfig.getValue("redis.password");
//		// 构造连接池
//		jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout,pwd);
    }

    /**
     * 获取Jedis
     */
    public static Jedis getResource() {
        return JedisClientPool.jedisPool.getResource();
    }

    /**
     * 销毁对象
     *
     * @param resource
     */
    @SuppressWarnings("deprecation")
    public static void returnBrokenResource(Jedis resource) {
        jedisPool.returnBrokenResource(resource);
    }

    /**
     * 还原到连接池
     *
     * @param resource
     */
    @SuppressWarnings("deprecation")
    public static void returnResource(Jedis resource) {
        jedisPool.returnResource(resource);
    }


}
