package com.mazhj.felix.control.pojo.model;

import lombok.Data;

/**
 * @author mazhj
 */
@Data
public class DynamicComponents {

    // 数据库组件
    private Long id;

    // 组件ID
    private String componentId;

    // 组件名称
    private String componentName;

    // 组件内容
    private String content;

    // 创建日期
    private String createTime;

    // 修改日期
    private String updateTime;

}
