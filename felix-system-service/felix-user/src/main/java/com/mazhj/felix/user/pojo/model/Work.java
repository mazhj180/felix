package com.mazhj.felix.user.pojo.model;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import lombok.Data;

/**
 * @author mazhj
 */
@Data
public class Work {

    // 作者ID
    private String authorId;

    // 作者名称
    private String authorName;

    // 作品ID
    private String workId;

    // 作品名称
    private String workName;

    // 作品标签分类
    private String tag;

    // 作品封面
    private String workImg;

    // 存放路径
    private String workUrl;

    // 作品描述
    private String introduction;

    // 作品状态 : 未审核、审核成功、退回
    private String status;

    // 创建时间
    private String createTime;

    private JSONArray tags;

    public Work toJsonArray(){
        this.tags = JSON.parseArray(this.tag);
        return this;
    }

}
