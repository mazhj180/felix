package com.mazhj.felix.user.pojo.param;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author mazhj
 */
public class UserParam implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /** 唯一id 也是用户登录名*/
    private String userId;

    /** 账号密码*/
    private String userPwd;

    /** 昵称*/
    private String nickName;

    /** 联系电话*/
    private String phoneNumber;

    /** 用户邮箱*/
    private String email;
}
