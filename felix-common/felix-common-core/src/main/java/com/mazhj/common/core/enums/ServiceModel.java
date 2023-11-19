package com.mazhj.common.core.enums;

/**
 * @author mazhj
 */
public enum ServiceModel {
    /** 用户模块*/
    USER("user-service-model"),
    /** 图书模块*/
    BOOK("book-service-model"),
    /** 主页模块*/
    HOMEPAGE("homepage-service-model"),
    /** 论坛模块*/
    FORUM("forum-service-model"),
    /** 搜索模块*/
    SEARCH("search-service-model");

    final String name;

    ServiceModel(String name) {
        this.name = name;
    }

    public String str() {
        return name;
    }
}
