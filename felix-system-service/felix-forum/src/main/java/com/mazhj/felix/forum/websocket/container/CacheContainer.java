package com.mazhj.felix.forum.websocket.container;

/**
 * @author mazhj
 */
public interface CacheContainer<K,V> {

    /**
     * 放入缓存
     * @param key 连接的key
     * @param val 连接
     */
    void put(K key,V val);

    /**
     * 从缓存中取出
     * @param key 连接的key
     * @return 返回缓存中的连接
     */
    V get(K key);

    /**
     * 删除key
     * @param key key
     */
    void del(K key);

}
