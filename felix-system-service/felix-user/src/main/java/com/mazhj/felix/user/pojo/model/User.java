package com.mazhj.felix.user.pojo.model;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @author mazhj
 */

@Data
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /** 数据库自增id*/
    private Long id;

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

    /** 头像地址*/
    private String headImgUrl;

    /** 账户等级 默认为reader*/
    private String level;

    /** 创建时间*/
    private Date createTime;

    /** 更新时间*/
    private Date updateTime;

}
