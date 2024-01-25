package com.mazhj.felix.auth.service;

import com.mazhj.common.core.exception.AuthException;
import com.mazhj.felix.auth.pojo.vo.AuthVO;
import com.nimbusds.jose.JOSEException;

import java.text.ParseException;

/**
 * @author mazhj
 */
public interface AuthService {
    /**
     * 登录
     * @param userId 用户id
     * @param password 密码
     * @return 认证对象
     */
    AuthVO login(String userId, String password);

}
