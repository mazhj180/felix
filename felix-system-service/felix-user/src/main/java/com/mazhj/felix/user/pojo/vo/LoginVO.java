package com.mazhj.felix.user.pojo.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author mazhj
 */
@Data
@Accessors(chain = true)
public class LoginVO {

    /** token*/
    private String token;

    /** 用户id*/
    private String userId;

    /** 昵称*/
    private String nickName;

    /** 手机号*/
    private String phoneNumber;

    /** 邮箱*/
    private String email;

    /** 头像地址*/
    private String headImgUrl;
}
