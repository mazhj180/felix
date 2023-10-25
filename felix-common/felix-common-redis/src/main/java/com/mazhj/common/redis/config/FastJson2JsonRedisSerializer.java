package com.mazhj.common.redis.config;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.filter.Filter;
import com.mazhj.common.core.constant.SystemConstant;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * redis序列化器
 * @param <T>
 * @author mazhj
 */
public class FastJson2JsonRedisSerializer<T> implements RedisSerializer<T> {

    public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    public static final Filter AUTO_TYPE_FILTER = JSONReader.autoTypeFilter(SystemConstant.JSON_WHITELIST_STR);

    private final Class<T> tClass;

    public FastJson2JsonRedisSerializer(Class<T> tClass) {
        this.tClass = tClass;
    }

    @Override
    public byte[] serialize(T t) throws SerializationException {

        if (null == t){
            return new byte[0];
        }

        return JSON.toJSONString(t, JSONWriter.Feature.WriteClassName).getBytes(DEFAULT_CHARSET);
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {

        if (bytes == null || bytes.length <= 0) {
            return null;
        }

        String target =new String(bytes,DEFAULT_CHARSET);
        return JSON.parseObject(target,tClass,AUTO_TYPE_FILTER);
    }
}
