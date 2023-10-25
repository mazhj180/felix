package com.mazhj.felix.user.service;

import com.mazhj.common.web.response.Message;
import com.mazhj.felix.user.pojo.model.Bookshelf;
import com.mazhj.felix.user.pojo.param.BookshelfParam;

import java.util.List;

/**
 * @author mazhj
 */
public interface BookshelfService {

    /**
     * 获取用户书架图书列表
     * @param userId 用户id
     * @return 封装后的用户书架图书列表
     */
    List<Bookshelf> getBookshelfList(String userId);

    /**
     * 同步书架信息
     * 同步类型 :1 add; 2 modify; 3 remove
     * @param bookshelfParam 用户书架入参
     * @return 消息
     */
    Message syncBookshelfInfo(BookshelfParam bookshelfParam);

}
