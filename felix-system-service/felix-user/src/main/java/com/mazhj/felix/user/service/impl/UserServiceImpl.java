package com.mazhj.felix.user.service.impl;

import com.github.pagehelper.Page;
import com.mazhj.common.auth.enums.AccountLevel;
import com.mazhj.common.core.exception.BusinessException;
import com.mazhj.common.core.utils.Convert;
import com.mazhj.common.core.utils.JwtUtil;
import com.mazhj.common.pojo.claims.Claims;
import com.mazhj.common.web.response.Message;
import com.mazhj.felix.user.mapper.UserMapper;
import com.mazhj.felix.user.pojo.model.User;
import com.mazhj.felix.user.pojo.vo.LoginVO;
import com.mazhj.felix.user.service.UserService;
import com.nimbusds.jose.JOSEException;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.Optional;

/**
 * @author mazhj
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


    @Override
    public LoginVO login(String userId, String password) {
        try {
            User user = this.userMapper.selectByUserId(userId);
            if (user == null){
                throw new BusinessException("用户不存在");
            }
            password = DigestUtils.md5DigestAsHex(password.getBytes());
            if (!password.equals(user.getUserPwd())){
                throw new BusinessException("密码错误");
            }
            long oneDay = 3600 * 24 * 1000;
            String token = JwtUtil.generateToken(new Claims().setUserId(userId).setExp(System.currentTimeMillis()+oneDay));
            return Convert.to(user,LoginVO.class).setToken(token);
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Message register(User user) {
        User user1 = this.userMapper.selectByUserId(user.getUserId());
        if (user1 != null){
            throw new BusinessException("用户已存在");
        }
        String password = user.getUserPwd();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        user.setUserPwd(password);
        this.userMapper.insert(user);
        return Message.builder().message("注册成功").build();
    }

    @Override
    public Boolean userIsExited(String userId) {
        User user = this.userMapper.selectByUserId(userId);
        return user != null;
    }

    @Override
    public AccountLevel userLevel(String userId) {
        User user = this.userMapper.selectByUserId(userId);
        String level = user.getLevel();
        return AccountLevel.valueOf(level.toUpperCase());
    }

    @Override
    public Page<User> getUsers(String userId, String userName, Boolean isWriter) {
        isWriter = Optional.ofNullable(isWriter).orElse(false);
        return this.userMapper.selectUserList(userId,userName,isWriter);
    }

    @Override
    public void updateState(User user) {
        this.userMapper.updateState(user);
    }

    @Override
    public User getUserInfo(String userId) {
        return this.userMapper.selectByUserId(userId);
    }
}
