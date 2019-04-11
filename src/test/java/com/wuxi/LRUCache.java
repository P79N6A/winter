package com.wuxi;


import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author: 青铜
 * @create: 2018-05-11
 **/
public class LRUCache<K, V> extends LinkedHashMap<K, V> {


    public LRUCache(int initialCapacity,
                    float loadFactor,
                    boolean accessOrder) {
        super(initialCapacity, loadFactor, accessOrder);
    }


    public static void main(String[] args) {
        LRUCache<Character, Integer> lruCache = new LRUCache<>(16, 0.75f, true);

        String str = "abcdefghijk";
        for (int i = 0; i < str.length(); i++) {
            lruCache.put(str.charAt(i), i);
        }

        System.out.println(lruCache.size());
        System.out.println(lruCache);

    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        if (size() > 6) {
            return true;
        }
        return false;
    }
}
