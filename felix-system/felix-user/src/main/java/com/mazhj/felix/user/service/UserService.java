package com.mazhj.felix.user.service;

import com.mazhj.felix.user.pojo.model.User;
import com.mazhj.felix.user.pojo.vo.LoginVO;

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

}
