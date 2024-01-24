package com.mazhj.felix.forum.service.impl;

import com.mazhj.felix.forum.mapper.TopicMapper;
import com.mazhj.felix.forum.pojo.model.Topic;
import com.mazhj.felix.forum.service.TopicService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mazhj
 */
@Service
public class TopicServiceImpl implements TopicService {

    private final TopicMapper topicMapper;

    public TopicServiceImpl(TopicMapper topicMapper) {
        this.topicMapper = topicMapper;
    }

    @Override
    public List<Topic> getTopics() {
        return this.topicMapper.selectTopics();
    }
}
