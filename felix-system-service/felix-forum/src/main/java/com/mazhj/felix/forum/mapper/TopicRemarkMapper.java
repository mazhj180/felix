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
     * 查询所有根评论
     * @param topicId 话题id
     * @return 所有根评论
     */
    @Select("""
                select * from topic_remark
                where topic_id = #{topicId} and root_remark_id is null
                order by support_count desc , create_time
            """)
    List<TopicRemark> selectRootRemarks(Long topicId);

    @Select("""
                select tr.* from topic_remark tr
                where topic_id = #{topicId} and
                    remark_id in (
                        select remark_id from topic_remark tr1
                        where root_remark_id = tr.remark_id and reply_remark_id = tr.remark_id
                        order by support_count desc , create_time
                        limit 3
                    )
            """)
    List<TopicRemark> selectRootRemarksWith3Child(Long topicId);

    @Select("""
            <script>
                select * from topic_remark
                where topic_id = #{topicId} and root_remark_id = #{rootRemarkId}
                order by create_time, support_count desc
                <if test='limits != null'>
                    limit #{limits}
                </if>
            </script>
            """)
    List<TopicRemark> selectChildRemarks(Long topicId, Long rootRemarkId, Integer limits);

    @Select("""
                select * from topic_remark
                where remark_id = #{remarkId} and topic_id = #{topicId}
            """)
    TopicRemark selectRemarksById(Long topicId,Long remarkId);

    @Select("""
                select * from topic_remark
                where topic_id = #{topicId} and reply_remark_id = #{replyRemarkId}
                order by create_time ,support_count desc
            """)
    List<TopicRemark> selectReplies(Long topicId,Long replyRemarkId );

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
