package com.mazhj.felix.homepage.service;

import com.mazhj.common.pojo.dto.BookDTO;

import java.util.List;

/**
 * @author mazhj
 */
public interface RankService {

    /**
     * 获取热榜
     * @return 热榜列表
     */
    List<BookDTO> getHotRankings();

    /**
     * 获取点赞榜
     * @return 点赞榜列表
     */
    List<BookDTO> getLikeRankings();

}
