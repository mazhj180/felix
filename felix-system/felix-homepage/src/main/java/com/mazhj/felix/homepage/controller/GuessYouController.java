package com.mazhj.felix.homepage.controller;

import com.mazhj.common.pojo.dto.BookDTO;
import com.mazhj.common.web.controller.BaseController;
import com.mazhj.common.web.response.AjaxResult;
import com.mazhj.felix.homepage.pojo.param.Reason;
import com.mazhj.felix.homepage.service.GuessYouService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author mazhj
 */
@RestController
@RequestMapping("/guess")
public class GuessYouController extends BaseController {

    private final GuessYouService guessYouService;


    public GuessYouController(GuessYouService guessYouService) {
        this.guessYouService = guessYouService;
    }

    @GetMapping
    public AjaxResult guessYou(String userId, Reason reason){
        List<BookDTO> likes = this.guessYouService.getPossibleLikes(userId, reason);
        return success(likes);
    }
}
