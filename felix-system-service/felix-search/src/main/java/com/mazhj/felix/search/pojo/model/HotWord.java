package com.mazhj.felix.search.pojo.model;

import lombok.Data;

import java.util.Date;

/**
 * @author mazhj
 */
@Data
public class HotWord {

    /** 自增id*/
    private Long id;
    /** 内容*/
    private String content;
    /** 热度*/
    private Double heat;
    /** 日期*/
    private Date createDate;


}
