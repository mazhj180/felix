package com.mazhj.felix.forum.controller;

import com.mazhj.common.web.controller.BaseController;
import com.mazhj.common.web.response.AjaxResult;
import com.mazhj.felix.forum.pojo.vo.TopicRemarkVO;
import com.mazhj.felix.forum.service.TopicRemarkService;
import com.mazhj.felix.forum.service.TopicService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author mazhj
 */
@RestController
@RequestMapping("/topic")
public class TopicController extends BaseController {

    private final TopicService topicService;

    private final TopicRemarkService topicRemarkService;

    public TopicController(TopicService topicService, TopicRemarkService topicRemarkService) {
        this.topicService = topicService;
        this.topicRemarkService = topicRemarkService;
    }

    @GetMapping
    public AjaxResult showTopics(){
        return success(this.topicService.getTopics());
    }

    @GetMapping("/remarks")
    public AjaxResult showRemarks(Long topicId){
        List<TopicRemarkVO> remarks = this.topicRemarkService.getRootRemarksWith3Child(topicId);
        return success(remarks);
    }

    @GetMapping("/remarks/all")
    public AjaxResult showAllRemarks(Long topicId,Long rootRemarkId){
        List<TopicRemarkVO> childRemarks = this.topicRemarkService.getChildRemarks(topicId, rootRemarkId);
        return success(childRemarks);
    }

}
