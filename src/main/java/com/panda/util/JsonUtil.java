package com.panda.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 
 * @author dasouche
 *
 */
public class JsonUtil {

	public static String toJson(Object object){
		return JSON.toJSONString(object);
	}
	
	public static String toJson(Object object,SerializerFeature... features){
		return JSON.toJSONString(object, features);
	}
	
	/**
	 * 反序列化
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> T fromJson(String json,Class<T> clazz){
		return JSON.parseObject(json, clazz);
	}
}
