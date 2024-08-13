package com.mazhj.felix.user.service;

import com.mazhj.felix.user.pojo.model.Work;

import java.util.List;

/**
 * @author mazhj
 */
public interface WorkService {

    /**
     * 获取作品信息
     * @param authorId 作者id
     * @return 作品信息
     */
    List<Work> getWorks(String authorId);

    /**
     * 根据status获取作品信息
     * @param status 状态
     * @return 作品信息
     */
    List<Work> getWorksByStatus(String status);

    /**
     * 创建作品
     * @param work 作品信息
     */
    void createWork(Work work);

}
