package com.mazhj.felix.user.service.impl;

import com.mazhj.common.core.utils.Convert;
import com.mazhj.common.pojo.dto.BookDTO;
import com.mazhj.common.web.response.Message;
import com.mazhj.felix.feign.book.clients.BookClient;
import com.mazhj.felix.user.mapper.BookshelfMapper;
import com.mazhj.felix.user.pojo.model.Bookshelf;
import com.mazhj.felix.user.pojo.param.BookshelfParam;
import com.mazhj.felix.user.pojo.vo.BookshelfVO;
import com.mazhj.felix.user.service.BookshelfService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * @author mazhj
 */
@Slf4j
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
    public List<BookshelfVO> getBookshelfList(String userId) {
        List<Bookshelf> bookshelves = this.bookshelfMapper.selectListByUserId(userId);
        List<BookshelfVO> bookshelfVOS = new ArrayList<>();
        for (Bookshelf bookshelf : bookshelves) {
            BookDTO bookDTO = bookClient.getBookByBookId(bookshelf.getBookId());
            if (bookDTO != null){
                BookshelfVO bookshelfVO = Convert.to(bookDTO, BookshelfVO.class);
                bookshelfVO.setUserId(userId);
                bookshelfVOS.add(bookshelfVO);
            }
        }
        return bookshelfVOS;
    }

    @Override
    public Message syncBookshelfInfo(BookshelfParam bookshelfParam) {
        Integer syncType = bookshelfParam.getSyncType();
        boolean flag = switch (syncType){
            case 1 -> add(bookshelfParam);
            case 2 -> update(bookshelfParam);
            case 3 -> remove(bookshelfParam);
            default -> false;
        };
        Message.MessageBuilder builder = Message.builder();
        return flag?builder.message("操作成功").build():builder.message("操作失败").build();
    }

    private boolean add(BookshelfParam bookshelfParam){
        String bookId = bookshelfParam.getBookId();
        String userId = bookshelfParam.getUserId();
        int counts = this.bookshelfMapper.selectCountsByUserIdAndBookId(userId, bookId);
        if (counts != 0){
            return false;
        }
        Bookshelf bookshelf = Convert.to(bookshelfParam, Bookshelf.class);
        counts = this.bookshelfMapper.insert(bookshelf);
        return counts == 1;
    }

    private boolean update(BookshelfParam bookshelfParam){
        Bookshelf bookshelf = Convert.to(bookshelfParam, Bookshelf.class);
        int counts = this.bookshelfMapper.updateByUserIdAndBookId(bookshelf);
        return counts == 1;
    }

    private boolean remove(BookshelfParam bookshelfParam){
        String bookId = bookshelfParam.getBookId();
        String userId = bookshelfParam.getUserId();
        int counts = this.bookshelfMapper.deleteByUserIdAndBookId(bookId,userId);
        return counts == 1;
    }
}
