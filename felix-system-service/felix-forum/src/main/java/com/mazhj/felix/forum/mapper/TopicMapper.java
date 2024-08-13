package com.mazhj.felix.forum.mapper;

import com.mazhj.felix.forum.pojo.model.Topic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

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
    @Results(id = "baseMap",value = {
            @Result(column = "topic_id",jdbcType = JdbcType.BIGINT,property = "topicId"),
            @Result(column = "topic_name",jdbcType = JdbcType.VARCHAR,property = "topicName"),
            @Result(column = "remark_count",jdbcType = JdbcType.BIGINT,property = "remarkCount"),
            @Result(column = "img_uri",jdbcType = JdbcType.VARCHAR,property = "imgUri"),
            @Result(column = "creator",jdbcType = JdbcType.VARCHAR,property = "creator"),
            @Result(column = "create_time",jdbcType = JdbcType.TIMESTAMP,property = "createTime")
    })
    @Select("""
                select * from topic
            """)
    List<Topic> selectTopics();
}
