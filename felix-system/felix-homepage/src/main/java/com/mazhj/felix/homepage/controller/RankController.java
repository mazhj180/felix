package com.mazhj.felix.homepage.controller;

import com.mazhj.common.core.exception.BusinessException;
import com.mazhj.common.pojo.dto.BookDTO;
import com.mazhj.common.redis.keys.KeyBuilder;
import com.mazhj.common.redis.service.RedisService;
import com.mazhj.common.web.controller.BaseController;
import com.mazhj.common.web.response.AjaxResult;
import com.mazhj.felix.homepage.service.RankService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author mazhj
 */
@RestController
@RequestMapping("/rank")
public class RankController extends BaseController {

    private final RankService rankService;

    private final RedisService redisService;

    public RankController(
            RankService rankService,
            RedisService redisService
            ) {
        this.rankService = rankService;
        this.redisService = redisService;
    }

    @GetMapping("/get-ranking/{type}")
    public AjaxResult getRanking(@PathVariable String type){
        List<BookDTO> books = switch (type){
            case "hot" -> this.rankService.getHotRankings();
            case "like" -> this.rankService.getLikeRankings();
            case "score" -> this.rankService.getScoreRankings();
            default -> throw new BusinessException("请求错误");
        };
        return success(books);
    }

    @PostMapping("/feign/incr")
    public void incrSore(String bookId,Double hot){
        String key = KeyBuilder.Homepage.getHotRankingsKey();
        if (!this.redisService.hasKey(key)){
            this.redisService.addForZSet(key,bookId,0);
        }
        this.redisService.incrScore(key,bookId,hot);
    }
}
