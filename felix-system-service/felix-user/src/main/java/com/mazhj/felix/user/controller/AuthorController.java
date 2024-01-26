package com.mazhj.felix.user.controller;

import com.mazhj.common.web.controller.BaseController;
import com.mazhj.common.web.response.AjaxResult;
import com.mazhj.felix.user.pojo.model.Author;
import com.mazhj.felix.user.service.AuthorService;
import org.springframework.web.bind.annotation.*;

/**
 * @author mazhj
 */
@RestController
@RequestMapping("/author")
public class AuthorController extends BaseController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("/tobe-writer")
    public AjaxResult becomingWriter(@RequestBody Author author){
        this.authorService.addOneAuthor(author);
        return success();
    }


}
