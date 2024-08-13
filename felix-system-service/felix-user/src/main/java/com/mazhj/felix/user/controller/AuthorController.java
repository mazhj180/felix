package com.mazhj.felix.user.controller;

import com.mazhj.common.auth.anno.Auth;
import com.mazhj.common.auth.enums.AccountLevel;
import com.mazhj.common.web.controller.BaseController;
import com.mazhj.common.web.holder.UserContextHolder;
import com.mazhj.common.web.response.AjaxResult;
import com.mazhj.common.web.utils.ServletUtil;
import com.mazhj.felix.user.pojo.model.Author;
import com.mazhj.felix.user.pojo.model.Work;
import com.mazhj.felix.user.pojo.param.AuthorPo;
import com.mazhj.felix.user.service.AuthorService;
import com.mazhj.felix.user.service.WorkService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author mazhj
 */
@RestController
@RequestMapping("/author")
public class AuthorController extends BaseController {

    private final AuthorService authorService;

    private final WorkService workService;

    public AuthorController(AuthorService authorService, WorkService workService) {
        this.authorService = authorService;
        this.workService = workService;
    }

    @PostMapping("/become-writer")
    public AjaxResult becomingWriter(@RequestBody AuthorPo authorPo){
        Author author = new Author();
        author.setIdentityCard(authorPo.getIdCard())
                .setRealName(authorPo.getRealName())
                .setPseudonym(authorPo.getPseudonym())
                .setBirthday(authorPo.getDate())
                .setIntroduction(authorPo.getIntroduction())
                .setAuthorId(UserContextHolder.get())
                .setNationality("中国");
        this.authorService.addOneAuthor(author);
        return success("success");
    }

    @Auth(AccountLevel.WRITER)
    @GetMapping("/my-works/{userId}")
    public AjaxResult showWorks(@PathVariable String userId){
        List<Work> works = this.workService.getWorks(userId);
        return success(works);
    }


}
