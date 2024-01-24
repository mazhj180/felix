package com.mazhj.felix.forum.pojo.ws;

import com.mazhj.felix.forum.common.enums.MsgScope;
import com.mazhj.felix.forum.common.enums.MsgType;
import lombok.Data;
import net.minidev.json.JSONObject;

/**
 * @author mzj18
 */
@Data
public class MsgBody {

    MsgType msgType;

    String userId;

}
