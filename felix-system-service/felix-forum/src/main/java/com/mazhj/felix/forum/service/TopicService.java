package com.mazhj.felix.forum.service;


import com.mazhj.felix.forum.pojo.model.Topic;

import java.util.List;

/**
 * @author mazhj
 */
public interface TopicService {

    /**
     * 获取所有的topic
     * @return topic们
     */
    List<Topic> getTopics();

}
