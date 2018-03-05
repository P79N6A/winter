package com.panda.util;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

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
	
	public static <T> List<T> toBeanList(String json, Class<T> clazz) throws Exception {
		List<T> lst = new ArrayList<T>();
		JsonArray array = new JsonParser().parse(json).getAsJsonArray();
		for (final JsonElement elem : array) {
			lst.add(new Gson().fromJson(elem, clazz));
		}
		return lst;
	}
}
