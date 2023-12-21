package com.mazhj.felix.forum.mapper;

import com.mazhj.felix.forum.pojo.model.TopicRemark;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author mazhj
 */
@Mapper
public interface TopicRemarkMapper {

    /**
     * @param topicId
     * @return
     */
    @Select("""
                select * from topic_remark
                where root_remark_id = null and topic_id = #{topicId}
            """)
    List<TopicRemark> selectRootRemarks(String topicId);

    @Select("""
                select * from topic_remark
                where root_remark_id = #{rootRemarkId} and topic_id = #{topicId}
            """)
    List<TopicRemark> selectChildRemarks(String topicId,String rootRemarkId);


}
