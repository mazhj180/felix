package com.mazhj.felix.user.service;

import com.mazhj.felix.user.pojo.model.Author;

/**
 * @author mazhj
 */
public interface AuthorService {

    /**
     * 获取作者信息
     * @param authorId 作者id
     * @return 作者信息
     */
    Author getAuthor(String authorId);

    /**
     * 新增作者
     * @param author 作者信息
     */
    void addOneAuthor(Author author);

}
