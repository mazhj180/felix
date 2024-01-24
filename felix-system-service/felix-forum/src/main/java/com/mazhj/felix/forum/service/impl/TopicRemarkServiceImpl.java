package com.mazhj.felix.forum.service.impl;

import com.mazhj.common.core.exception.BusinessException;
import com.mazhj.common.core.utils.Convert;
import com.mazhj.common.redis.keys.KeyBuilder;
import com.mazhj.common.redis.service.RedisService;
import com.mazhj.felix.forum.mapper.TopicRemarkMapper;
import com.mazhj.felix.forum.pojo.bo.TopicRemarkBO;
import com.mazhj.felix.forum.pojo.model.TopicRemark;
import com.mazhj.felix.forum.pojo.vo.TopicRemarkVO;
import com.mazhj.felix.forum.service.TopicRemarkService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author mazhj
 */
@Service
public class TopicRemarkServiceImpl implements TopicRemarkService {

    private final TopicRemarkMapper topicRemarkMapper;

    private final RedisService redisService;

    public TopicRemarkServiceImpl(
            TopicRemarkMapper topicRemarkMapper,
            RedisService redisService
            ) {
        this.topicRemarkMapper = topicRemarkMapper;
        this.redisService = redisService;
    }


    @Override
    public List<TopicRemarkBO> getRootRemarksWith3Child(Long topicId) {
        String key = KeyBuilder.Forum.getTopicRemarkKey(topicId.toString());
        if (this.redisService.hasKey(key)){
            this.redisService.expire(key,3600, TimeUnit.SECONDS);
            return this.redisService.get(key);
        }
        List<TopicRemark> rootRemarks = this.topicRemarkMapper.selectRootRemarks(topicId);
        List<TopicRemarkBO> bos = new ArrayList<>();
        for (TopicRemark rootRemark : rootRemarks) {
            List<TopicRemark> childRemarks = this.topicRemarkMapper.selectChildRemarks(topicId, rootRemark.getRootRemarkId(), 3);
            TopicRemarkBO bo = new TopicRemarkBO();
            bo.setRootRemark(Convert.to(rootRemark,TopicRemarkVO.class));
            bo.setChildRemark(Convert.to(childRemarks,TopicRemarkVO.class));
            bos.add(bo);
        }
        this.redisService.set(key,bos,3600);
        return bos;
    }

    @Override
    public List<TopicRemarkVO> getChildRemarks(Long topicId, Long rootRemarkId) {
        List<TopicRemark> remarks = this.topicRemarkMapper.selectChildRemarks(topicId, rootRemarkId, null);
        return Convert.to(remarks, TopicRemarkVO.class);
    }

    @Override
    public TopicRemark getRemarksById(Long topicId, Long remarkId) {
        return this.topicRemarkMapper.selectRemarksById(topicId,remarkId);

    }

    @Override
    public void saveRemark(TopicRemark topicRemark) {
        if (0 >= this.topicRemarkMapper.insertRemark(topicRemark)){
            throw new BusinessException("数据插入失败");
        }
    }
}
