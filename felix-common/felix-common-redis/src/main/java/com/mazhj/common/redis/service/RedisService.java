package com.mazhj.common.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.data.redis.core.*;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author mazhj
 */
@ConditionalOnMissingBean({RedisService.class})
@SuppressWarnings(value = { "unchecked", "rawtypes" })
public class RedisService {

    private final RedisTemplate redisTemplate;


    @Autowired
    public RedisService(
            @Qualifier("redisTemplate") RedisTemplate redisTemplate
    ) {
        this.redisTemplate = redisTemplate;
    }


    public <T> void set(final String key,final T value){
        ValueOperations<String,T> operations = redisTemplate.opsForValue();
        if (value != null){
            operations.set(key,value);
        }
    }

    public <T> void set(final String key,final T value,final long timeout){
        ValueOperations<String,T> operations = redisTemplate.opsForValue();
        if ( null != value){
            operations.set(key,value,timeout, TimeUnit.SECONDS);
        }
    }

    public <T> T get(final String key){
        ValueOperations<String,T> operations = redisTemplate.opsForValue();
        return operations.get(key);
    }

    public <T> void setHashVal(final String key,final String hashKey,final T value){
        HashOperations<String,String,T> operations = redisTemplate.opsForHash();
        operations.put(key,hashKey,value);
    }

    public <T> void setHashEntry(final String key, final Map<String,T> map){
        redisTemplate.opsForHash().putAll(key,map);
    }

    public <T> T getHashVal(final String key,final String hashKey){
        HashOperations<String,String,T> operations = redisTemplate.opsForHash();
        return operations.get(key,hashKey);
    }

    public <T> Map<String,T> getHashEntries(final String key){
        return redisTemplate.opsForHash().entries(key);
    }

    public <T> List<T> getHashVals(final String key, final Collection<Object> hashkeys){
        return redisTemplate.opsForHash().multiGet(key,hashkeys);
    }

    public Set<String> rangeVal(final String key,final Long start, final Long end){
        return redisTemplate.opsForZSet().reverseRangeWithScores(key, start, end);
    }

    public void incrementScore(final String key, final String hotWord ,final Double delta){
        redisTemplate.opsForZSet().incrementScore(key,hotWord,delta);
    }


    public boolean remove(final String key){
        return Boolean.TRUE.equals(redisTemplate.delete(key));
    }

    public boolean removeHashVal(final String key,final String hashKey){
        return redisTemplate.opsForHash().delete(key,hashKey) > 0;
    }


    public void expire(final String key,final long timeout,final TimeUnit timeUnit){
        this.redisTemplate.expire(key,timeout,timeUnit);
    }

    public long getExpire(final String key,final TimeUnit timeUnit){
        Long expire = this.redisTemplate.getExpire(key, timeUnit);
        return Optional.ofNullable(expire).orElse(0L);
    }

    public boolean hasKey(final String key){
        return Boolean.TRUE.equals(this.redisTemplate.hasKey(key));
    }


}
