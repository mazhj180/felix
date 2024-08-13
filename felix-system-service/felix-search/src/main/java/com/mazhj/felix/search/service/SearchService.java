package com.mazhj.felix.search.service;

import com.mazhj.common.pojo.dto.BookDTO;

import java.util.List;

/**
 * @author mazhj
 */
public interface SearchService {

    /**
     * 全文搜索
     * @param key 搜索凭据
     * @return 图书信息
     */
    List<BookDTO> search(String key);

    /**
     * 根据关键词搜索图书
     * @param keyword 关键词
     * @return 图书列表
     */
    List<BookDTO> searchBooksByKeyword(String keyword);

    /**
     * 根据书名搜索图书
     * @param name 书名
     * @return 图书列表
     */
    List<BookDTO> searchBooksByName(String name);

}
