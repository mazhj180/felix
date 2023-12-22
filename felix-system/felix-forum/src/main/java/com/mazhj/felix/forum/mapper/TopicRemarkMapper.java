package com.mazhj.felix.forum.mapper;

import com.mazhj.felix.forum.pojo.model.TopicRemark;
import org.apache.ibatis.annotations.Insert;
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
                where topic_id = #{topicId} and root_remark_id is null
                order by support desc , create_time
            """)
    List<TopicRemark> selectRootRemarks(Long topicId);

    @Select("""
            <script>
                select * from topic_remark
                where topic_id = #{topicId} and root_remark_id = #{rootRemarkId}
                order by create_time, support desc
                <if test='limits != null'>
                    limit = #{limits}
                </if>
            </script>
            """)
    List<TopicRemark> selectChildRemarks(Long topicId, Long rootRemarkId, Integer limits);

    @Select("""
                select * from topic_remark
                where remark_id = #{remarkId} and topic_id = #{topicId}
            """)
    List<TopicRemark> selectRemarksById(Long topicId,Long remarkId);

    @Insert("""
                insert into topic_remark (
                    root_remark_Id,reply_remark_Id,topic_id,user_id,
                    nick_name,head_img,content,healthy_content,
                    support_count,create_time
                ) values (
                    #{rootRemarkId},#{replyRemarkId},#{topicId},#{userId},
                    #{nickName},#{headImg},#{content},#{healthyContent},
                    #{supportCount},#{createTime}
                )
            """)
    int insertRemark(TopicRemark topicRemark);


}
