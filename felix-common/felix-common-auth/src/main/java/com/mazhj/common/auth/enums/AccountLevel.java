package com.mazhj.common.auth.enums;

import lombok.Getter;

/**
 * @author mazhj
 */
@Getter
public enum AccountLevel {
    /** 管理员*/
    ADMINISTRATOR(1),
    /** 读者*/
    READER(3),
    /** 作者*/
    WRITER(2);

    private final Integer level;

    AccountLevel(Integer level) {
        this.level = level;
    }
}
