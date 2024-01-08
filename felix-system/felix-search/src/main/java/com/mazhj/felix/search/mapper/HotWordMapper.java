package com.mazhj.felix.search.mapper;

import com.mazhj.felix.search.pojo.model.HotWord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author mazhj
 */
@Mapper
public interface HotWordMapper {

    /**
     * 获取当日热搜
     * @param date 日期
     * @return 当日热搜
     */
    List<HotWord> selectCurrent(String date);

}
