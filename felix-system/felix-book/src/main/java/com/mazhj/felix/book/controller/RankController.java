package com.mazhj.felix.book.controller;

import com.mazhj.common.core.exception.BusinessException;
import com.mazhj.common.web.controller.BaseController;
import com.mazhj.common.web.response.AjaxResult;
import com.mazhj.felix.book.pojo.vo.BookVO;
import com.mazhj.felix.book.service.RankService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author mazhj
 */
@RestController
@RequestMapping("/rank")
public class RankController extends BaseController {

    private final RankService rankService;


    public RankController(
            RankService rankService
    ) {
        this.rankService = rankService;
    }

    @GetMapping("/get-ranking/{type}")
    public AjaxResult getRanking(@PathVariable String type){
        List<BookVO> books = switch (type){
            case "hot" -> this.rankService.getHotRankings();
            case "like" -> this.rankService.getLikeRankings();
            case "score" -> this.rankService.getScoreRankings();
            default -> throw new BusinessException("请求错误");
        };
        return success(books);
    }

}
