package com.mazhj.felix.user.service;

import com.mazhj.common.web.response.Message;
import com.mazhj.felix.user.pojo.model.User;
import com.mazhj.felix.user.pojo.vo.LoginVO;

import java.util.List;

/**
 * @author mazhj
 */
public interface UserService {
    /**
     * 用户登录操作
     * @param userId 账号
     * @param password 密码
     * @return 登录结果和token
     */
    LoginVO login(String userId, String password);

    /**
     * 注册用户
     * @param user 用户信息
     * @return 消息（成功或失败）
     */
    Message register(User user);

    /**
     * 用户是否已存在
     * @param userId 用户id
     * @return 结果
     */
    Boolean userIsExited(String userId);

    /**
     * 获取用户列表
     * @param userId 用户id
     * @param userName 用户名
     * @param isWriter 是否是作者
     * @return 用户列表
     */
    List<User> getUsers(String userId,String userName,Boolean isWriter);

    /**
     * 修改用户状态
     * @param user 用户
     */
    void updateState(User user);

}
