package com.mazhj.felix.auth.pojo.model;

import lombok.Data;

import java.util.Date;

/**
 * @author mazhj
 */
@Data
public class User {

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

    /** 是否已注册为作者*/
    private Boolean isCreator;

    /** 创建时间*/
    private Date createTime;

    /** 更新时间*/
    private Date updateTime;

}

