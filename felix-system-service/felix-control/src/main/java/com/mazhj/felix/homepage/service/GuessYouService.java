package com.mazhj.felix.homepage.service;

import com.mazhj.common.pojo.dto.BookDTO;
import com.mazhj.felix.homepage.pojo.param.Reason;

import java.util.List;

/**
 * @author mazhj
 */
public interface GuessYouService {

    /**
     * 获取用户可能喜欢的图书
     * @param userId 用户id
     * @param reason 用户搜索记录
     * @return 图书列表
     */
    List<BookDTO> getPossibleLikes(String userId, Reason reason);

    /**
     * 收集猜用户喜欢的依据
     * @param reason 猜用户喜欢的依据
     */
    void collectReason(Reason reason);

}
