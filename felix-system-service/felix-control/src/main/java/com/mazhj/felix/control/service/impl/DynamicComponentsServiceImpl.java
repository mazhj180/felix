package com.mazhj.felix.control.service.impl;

import com.mazhj.common.redis.keys.KeyBuilder;
import com.mazhj.common.redis.service.RedisService;
import com.mazhj.felix.control.mapper.DynamicComponentsMapper;
import com.mazhj.felix.control.pojo.model.DynamicComponents;
import com.mazhj.felix.control.service.DynamicComponentsService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mazhj
 */
@Service
public class DynamicComponentsServiceImpl implements DynamicComponentsService {

    private final DynamicComponentsMapper dynamicComponentsMapper;

    private final RedisService redisService;

    public DynamicComponentsServiceImpl(DynamicComponentsMapper dynamicComponentsMapper, RedisService redisService) {
        this.dynamicComponentsMapper = dynamicComponentsMapper;
        this.redisService = redisService;
    }

    @Override
    public List<DynamicComponents> getComponents(String componentId) {
        String key = KeyBuilder.Control.getDynamicComponentKey(componentId);
        List<DynamicComponents> components = this.redisService.get(key);
        if (components == null || components.isEmpty()){
            components = this.dynamicComponentsMapper.selectComponents(componentId);
            this.redisService.set(key,components);
        }
        components.forEach(dynamicComponents -> {
            dynamicComponents.setContent("http://127.0.0.1:9000"+dynamicComponents.getContent());
        });
        return components;
    }
}
