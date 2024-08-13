package com.mazhj.felix.control.service;

import com.mazhj.felix.control.pojo.model.DynamicComponents;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author mazhj
 */
public interface DynamicComponentsService {

    /**
     * 获取动态组件
     * @param componentId 组件id
     * @return 组件信息
     */
    List<DynamicComponents> getComponents(String componentId);

    /**
     * 批量删除动态组件
     * @param ids 组件们的id
     */
    void removeComponents(List<Long> ids);

    /**
     * 删除动态组件
     * @param id 组件id
     */
    void removeComponent(Long id);

    /**
     * 添加动态组件
     * @param file 文件
     * @param dynamicComponents 动态组件
     */
    void addComponent(MultipartFile file, DynamicComponents dynamicComponents);



}
