package com.mazhj.felix.control.pojo.param;

import lombok.Data;

import java.util.List;

@Data
public class Reason {

    /** 搜索记录*/
    private List<String> searchRecord;

    /** 最近浏览图书的id*/
    private List<String> lookRecent;

}
