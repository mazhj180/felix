package com.mazhj.felix.forum.pojo.bo;

import com.mazhj.felix.forum.pojo.vo.TopicRemarkVO;
import lombok.Data;

import java.util.List;

/**
 * @author mazhj
 */
@Data
public class TopicRemarkBO {

    private TopicRemarkVO rootRemark;

    private List<TopicRemarkVO> childRemark;

}
