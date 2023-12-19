package com.mazhj.felix.forum.mapper;

import com.mazhj.felix.forum.pojo.model.Topic;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author mazhj
 */
@Mapper
public interface TopicMapper {

    /**
     * 查询全部话题
     * @return 话题列表
     */
    List<Topic> selectTopics();
}
