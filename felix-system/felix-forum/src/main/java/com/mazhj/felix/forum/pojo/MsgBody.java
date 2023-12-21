package com.mazhj.felix.forum.pojo;

import com.mazhj.felix.forum.common.enums.MsgScope;
import lombok.Data;

/**
 * @author mzj18
 */
@Data
public class MsgBody {

    /** 1:topic 2:group 3:group 4:private*/
    private MsgScope msgScope;

    private Integer msgType;

    private String content;


}
