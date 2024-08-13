package com.mazhj.felix.auth.service.impl;

import com.mazhj.common.core.constant.TimeConstants;
import com.mazhj.common.core.exception.BusinessException;
import com.mazhj.common.core.utils.Convert;
import com.mazhj.common.core.utils.JwtUtil;
import com.mazhj.common.pojo.claims.Claims;
import com.mazhj.felix.auth.mapper.UserMapper;
import com.mazhj.felix.auth.pojo.model.User;
import com.mazhj.felix.auth.pojo.vo.AuthVO;
import com.mazhj.felix.auth.service.AuthService;
import com.nimbusds.jose.JOSEException;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * @author mazhj
 */
@Service
public class AuthServiceImpl implements AuthService {

    private final UserMapper userMapper;

    public AuthServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public AuthVO login(String userId, String password) {
        try {
            User user = this.userMapper.selectByUserId(userId);
            if (user == null){
                throw new BusinessException("用户不存在");
            }
            password = DigestUtils.md5DigestAsHex(password.getBytes());
            if (!password.equals(user.getUserPwd())){
                throw new BusinessException("密码错误");
            }
            if (user.getIsBan()){
                throw new BusinessException("用户被禁用");
            }

            String accessToken = JwtUtil.generateToken(
                    new Claims()
                            .setUserId(userId)
                            .setLevel(user.getLevel())
                            .setExp(System.currentTimeMillis()+ TimeConstants.ONE_HOUR)
            );
            String refreshToken = JwtUtil.generateToken(
                    new Claims()
                            .setUserId(userId)
                            .setExp(System.currentTimeMillis()+TimeConstants.ONE_WEEK)
            );
            return Convert.to(user,AuthVO.class)
                    .setAccessToken(accessToken)
                    .setRefreshToken(refreshToken);
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }

}
