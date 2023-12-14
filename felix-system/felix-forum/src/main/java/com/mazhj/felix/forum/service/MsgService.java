package com.mazhj.felix.forum.service;

import com.mazhj.felix.forum.pojo.MsgBody;

public interface MsgService {

    void sendMsg();

    void broadcast(MsgBody msgBody,String topic);


}
