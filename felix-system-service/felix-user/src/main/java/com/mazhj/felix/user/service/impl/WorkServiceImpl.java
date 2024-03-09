package com.mazhj.felix.user.service.impl;

import com.mazhj.felix.user.mapper.WorkMapper;
import com.mazhj.felix.user.pojo.model.Work;
import com.mazhj.felix.user.service.WorkService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mazhj
 */
@Service
public class WorkServiceImpl implements WorkService {

    private final WorkMapper workMapper;

    public WorkServiceImpl(WorkMapper workMapper) {
        this.workMapper = workMapper;
    }

    @Override
    public List<Work> getWorks(String authorId) {
        List<Work> works = this.workMapper.selectWorksByAuthorId(authorId);
        return works.stream()
                .map(Work::toJsonArray)
                .toList();
    }

    @Override
    public void createWork(Work work) {
        this.workMapper.insert(work);
    }
}
