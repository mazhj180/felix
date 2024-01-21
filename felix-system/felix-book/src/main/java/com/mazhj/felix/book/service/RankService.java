package com.mazhj.felix.book.service;

import com.mazhj.felix.book.pojo.vo.BookVO;
import com.mazhj.felix.book.pojo.vo.RankVO;

import java.util.List;

/**
 * @author mazhj
 */
public interface RankService {

    /**
     * 获取热榜
     * @return 热榜列表
     */
    List<RankVO> getHotRankings();

    /**
     * 获取点赞榜
     * @return 点赞榜列表
     */
    List<RankVO> getLikeRankings();

    /**
     * 获取评分榜
     * @return 评分榜列表
     */
    List<RankVO> getScoreRankings();

}
