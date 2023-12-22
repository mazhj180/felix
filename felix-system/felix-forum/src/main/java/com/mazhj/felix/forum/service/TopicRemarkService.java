package com.mazhj.felix.forum.service;

import com.mazhj.felix.forum.pojo.bo.TopicRemarkBO;
import com.mazhj.felix.forum.pojo.model.TopicRemark;
import com.mazhj.felix.forum.pojo.vo.TopicRemarkVO;

import java.util.List;

/**
 * @author mazhj
 */
public interface TopicRemarkService {

    /**
     * 按层级获取所有评论
     * @param topicId 所属话题id
     * @return 所有评论
     */
    List<TopicRemarkBO> getRootRemarksWith3Child(Long topicId);

    /**
     * 获取子评论
     * @param topicId 话题id
     * @param rootRemarkId 根评论id
     * @return 子评论们
     */
    List<TopicRemarkVO> getChildRemarks(Long topicId, Long rootRemarkId);

    void saveRemark(TopicRemark topicRemark);

}
