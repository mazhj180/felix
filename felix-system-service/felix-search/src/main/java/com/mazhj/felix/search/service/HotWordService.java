package com.mazhj.felix.search.service;

import com.mazhj.felix.search.pojo.model.HotWord;

import java.util.List;
import java.util.Set;

/**
 * @author mazhj
 */
public interface HotWordService {

    /**
     * 获取当天热搜词
     * @return 热搜词
     */
    Set<String> getCurrentHotWord();


}
