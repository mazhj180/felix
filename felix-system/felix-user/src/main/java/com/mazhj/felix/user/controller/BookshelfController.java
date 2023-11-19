package com.mazhj.felix.user.controller;

import com.mazhj.common.web.controller.BaseController;
import com.mazhj.common.web.response.AjaxResult;
import com.mazhj.common.web.response.Message;
import com.mazhj.felix.user.pojo.param.BookshelfParam;
import com.mazhj.felix.user.pojo.vo.BookshelfVO;
import com.mazhj.felix.user.service.BookshelfService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author mazhj
 */
@RestController
@RequestMapping("/user/bookshelf")
public class BookshelfController extends BaseController {

    private final BookshelfService bookshelfService;

    public BookshelfController(BookshelfService bookshelfService) {
        this.bookshelfService = bookshelfService;
    }

    @GetMapping("/show-bookshelf")
    public AjaxResult show(String userId){
        List<BookshelfVO> vos = this.bookshelfService.getBookshelfList(userId);
        return success(vos);
    }

    @PostMapping("/sync-bookshelf")
    public AjaxResult sync(BookshelfParam bookshelfParam){
        Message message = this.bookshelfService.syncBookshelfInfo(bookshelfParam);
        return success().buildMsg(message.getMessage());
    }


}
