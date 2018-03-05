package com.panda.cache;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.panda.util.MD5Utils;

import redis.clients.jedis.Jedis;


public class JedisClient {

	private static final Logger logger = LoggerFactory.getLogger(JedisClient.class);
	
	private static String KEY_PREFIX;
	/**
	 * NX -- Only set the key if it does not already exist.
	 */
	private static final String REDIS_NXXX_NX = "NX";
	/**
	 * XX -- Only set the key if it already exist.
	 */
	private static final String REDIS_NXXX_XX = "XX";
	
	/**
	 * seconds -- Set the specified expire time, in seconds.
	 */
	private static final String REDIS_EXPX_EX = "EX";
	/**
	 * milliseconds -- Set the specified expire time, in milliseconds.
	 */
	private static final String REDIS_EXPX_PX = "PX";
	
	static{
		//KEY_PREFIX = OptimusConfig.getValue("redis.keyprefix","souche_sync_redis_");
	}
	
	/**  
     * 字符串增加  
     * @param key  
     * @param value  
     */  
	public static void set(String key, String value) {
		Jedis redisClient = null;
		try {
			redisClient = JedisClientPool.getResource();
			redisClient.set(getCacheKey(key), value);
		} catch (Exception e) {
			logger.error("JedisClient.set error!",e);
			JedisClientPool.returnBrokenResource(redisClient);
		} finally {
			JedisClientPool.returnResource(redisClient);
		}
	}
	/**
	 * 字符串增加  
	 * @param key
	 * @param value
	 * @param expiredTime
	 */
	public static void set(String key,String value,long expiredTime){
		Jedis redisClient = null;
		try {
			redisClient = JedisClientPool.getResource();
			redisClient.set(getCacheKey(key), value, getNxxx(redisClient, key), REDIS_EXPX_EX, expiredTime);
		} catch (Exception e) {
			logger.error("JedisClient.set by expiredTime error!",e);
			JedisClientPool.returnBrokenResource(redisClient);
		} finally {
			JedisClientPool.returnResource(redisClient);
		}
	}
	
	/**   
    * 获取String值   
    * @param key   
    * @return value   
    */
	public static String get(String key) {
		Jedis redisClient = null;
		try {
			redisClient = JedisClientPool.getResource();
			return redisClient.get(getCacheKey(key));
		} catch (Exception e) {
			logger.error("JedisClient.get error!",e);
			JedisClientPool.returnBrokenResource(redisClient);
		} finally {
			JedisClientPool.returnResource(redisClient);
		}
		return null;
	}
	
	/**  
	* 删除  
	* @param
	* @return  
	*/  
	public static long delete(String key) {
		long result = 0;
		Jedis redisClient = null;
		try {
			redisClient = JedisClientPool.getResource();
			result = redisClient.del(getCacheKey(key));
		} catch (Exception e) {
			logger.error("JedisClient.delete error!",e);
			JedisClientPool.returnBrokenResource(redisClient);
		} finally {
			// 还原到连接池
			JedisClientPool.returnResource(redisClient);
		}
		return result;
	}
	
	/**
	 * nx xx 选择
	 * @param redisClient
	 * @param key
	 * @return
	 */
	public static String getNxxx(Jedis redisClient,String key){
		if(redisClient == null){
			return REDIS_NXXX_NX;
		}
		if(redisClient.exists(getCacheKey(key))){
			return REDIS_NXXX_XX;
		}
		//可以用作分布式锁
		//redisClient.setnx(key, value);
		return REDIS_NXXX_NX;
	}
	
	/**
	 * 自增1
	 * @param key
	 * @return
	 */
	public static Long incr(String key){
		long result = 0;
		Jedis redisClient = null;
		try {
			redisClient = JedisClientPool.getResource();
			result = redisClient.incr(getCacheKey(key));
		} catch (Exception e) {
			logger.error("JedisClient.incr error!",e);
			JedisClientPool.returnBrokenResource(redisClient);
		} finally {
			JedisClientPool.returnResource(redisClient);
		}
    	return result;
    }


    /**
     * 根据l相加
     * @param key
     * @param l
     * @return
     */
    public static Long incrBy(String key,long l){
    	long result = 0;
		Jedis redisClient = null;
		try {
			redisClient = JedisClientPool.getResource();
			result = redisClient.incrBy(getCacheKey(key),l);
		} catch (Exception e) {
			logger.error("JedisClient.incrBy error!",e);
			JedisClientPool.returnBrokenResource(redisClient);
		} finally {
			JedisClientPool.returnResource(redisClient);
		}
    	return result;
    }
    /**
     * 放入队列(左)
     * @param key
     * @param strings
     */
    public static void lpush(String key,String... strings) {  
    	Jedis redisClient = null;
		try {
			redisClient = JedisClientPool.getResource();
			redisClient.lpush(getCacheKey(key), strings);
		} catch (Exception e) {
			logger.error("JedisClient.lpush error!",e);
			JedisClientPool.returnBrokenResource(redisClient);
		} finally {
			JedisClientPool.returnResource(redisClient);
		}
    }
    
    /**
     * 放入队列（右）
     * @param key
     * @param strings
     */
    public static void rpush(String key,String... strings) {  
    	Jedis redisClient = null;
		try {
			redisClient = JedisClientPool.getResource();
			redisClient.rpush(getCacheKey(key), strings);
		} catch (Exception e) {
			logger.error("JedisClient.rpush error!",e);
			JedisClientPool.returnBrokenResource(redisClient);
		} finally {
			JedisClientPool.returnResource(redisClient);
		}
    } 
    
    
    /**
     * 拿出队列（右）
     * @param key
     * @param
     */
    public static String rpop(String key) { 
    	String result = null;
    	Jedis redisClient = null;
		try {
			redisClient = JedisClientPool.getResource();
			result = redisClient.rpop(getCacheKey(key));
		} catch (Exception e) {
			logger.error("JedisClient.rpop error!",e);
			JedisClientPool.returnBrokenResource(redisClient);
		} finally {
			JedisClientPool.returnResource(redisClient);
		}
		return result;
    } 
    
    /**
     * 拿出队列（左）
     * @param key
     * @param 
     */
    public static String lpop(String key) {  
    	String result = null;
    	Jedis redisClient = null;
		try {
			redisClient = JedisClientPool.getResource();
			result = redisClient.lpop(getCacheKey(key));
		} catch (Exception e) {
			logger.error("JedisClient.lpop error!",e);
			JedisClientPool.returnBrokenResource(redisClient);
		} finally {
			JedisClientPool.returnResource(redisClient);
		}
		return result;
    }
	
	/**
	 * get cache key
	 * @param key
	 * @return
	 */
	public static String getCacheKey(String key){
		return MD5Utils.md5(KEY_PREFIX + key);
	}
	
}
