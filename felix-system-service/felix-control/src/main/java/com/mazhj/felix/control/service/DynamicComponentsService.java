package com.mazhj.felix.control.service;

import com.mazhj.felix.control.pojo.model.DynamicComponents;

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

}
