package com.mazhj.felix.user.service.impl;

import com.mazhj.common.web.response.Message;
import com.mazhj.felix.feign.book.clients.BookClient;
import com.mazhj.felix.user.mapper.BookshelfMapper;
import com.mazhj.felix.user.pojo.model.Bookshelf;
import com.mazhj.felix.user.pojo.param.BookshelfParam;
import com.mazhj.felix.user.service.BookshelfService;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author mazhj
 */
@Service
public class BookshelfServiceImpl implements BookshelfService {

    private final BookshelfMapper bookshelfMapper;

    private final BookClient bookClient;

    public BookshelfServiceImpl(
            BookshelfMapper bookshelfMapper,
            BookClient bookClient) {
        this.bookshelfMapper = bookshelfMapper;
        this.bookClient = bookClient;
    }

    @Override
    public List<Bookshelf> getBookshelfList(String userId) {
        List<Bookshelf> bookshelves = this.bookshelfMapper.selectListByUserId(userId);
        return null;
    }

    @Override
    public Message syncBookshelfInfo(BookshelfParam bookshelfParam) {
        return null;
    }
}
