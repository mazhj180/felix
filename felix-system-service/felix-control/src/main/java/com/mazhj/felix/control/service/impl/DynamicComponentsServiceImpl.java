package com.mazhj.felix.control.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.mazhj.common.core.exception.BusinessException;
import com.mazhj.common.minio.service.MinioService;
import com.mazhj.common.redis.keys.KeyBuilder;
import com.mazhj.common.redis.service.RedisService;
import com.mazhj.felix.control.mapper.DynamicComponentsMapper;
import com.mazhj.felix.control.pojo.model.DynamicComponents;
import com.mazhj.felix.control.service.DynamicComponentsService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;

/**
 * @author mazhj
 */
@Service
public class DynamicComponentsServiceImpl implements DynamicComponentsService {

    private final DynamicComponentsMapper dynamicComponentsMapper;

    private final MinioService minioService;

    private enum Category {
        GREAT_WORK("creator-center-great-work"),
        WORD_LINE("client-word-line"),
        SLIDESHOW("slideshow");

        final String id;

        Category(String id) {
            this.id = id;
        }
    }

    public DynamicComponentsServiceImpl(DynamicComponentsMapper dynamicComponentsMapper, MinioService minioService) {
        this.dynamicComponentsMapper = dynamicComponentsMapper;
        this.minioService = minioService;
    }

    @Override
    public List<DynamicComponents> getComponents(String componentId) {

        List<DynamicComponents> components = this.dynamicComponentsMapper.selectComponents(componentId);

        if (componentId.equals(Category.GREAT_WORK.id) || componentId.equals(Category.SLIDESHOW.id)) {
            components.forEach(dynamicComponents -> {
                dynamicComponents.setContent(this.minioService.getEndpoint()+dynamicComponents.getContent());
            });
        }
        return components;
    }

    @Override
    public void removeComponents(List<Long> ids) {
        this.dynamicComponentsMapper.delComponents(ids);
    }

    @Override
    public void removeComponent(Long id) {
        this.dynamicComponentsMapper.delComponent(id);
    }

    @Override
    public void addComponent(MultipartFile file, DynamicComponents dynamicComponents) {
        String bucket = switch (dynamicComponents.getComponentId()){
            case "creator-center-great-work" -> "greate-works";
            case "client-word-line" -> "word-line";
            case "slideshow" -> "slideshow";
            case "good-book" -> "good-books";
            default -> throw new BusinessException("操作类型不存在");
        };

        String url = this.minioService.upload(bucket, file);
        switch (dynamicComponents.getComponentId()) {
            case "creator-center-great-work","slideshow" -> dynamicComponents.setContent(url);
            case "client-word-line","good-book" -> {
                JSONObject content = JSON.parseObject(dynamicComponents.getContent());
                content.put("image",url);
                dynamicComponents.setContent(content.toJSONString());
            }
            default -> throw new BusinessException("操作类型不存在");
        }
        this.dynamicComponentsMapper.addComponent(dynamicComponents);
    }
}
