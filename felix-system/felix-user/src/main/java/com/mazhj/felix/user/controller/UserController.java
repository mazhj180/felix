package com.mazhj.felix.user.controller;

import com.mazhj.common.web.controller.BaseController;
import com.mazhj.common.web.request.Params;
import com.mazhj.common.web.response.AjaxResult;
import com.mazhj.felix.user.pojo.param.UserParam;
import com.mazhj.felix.user.pojo.vo.LoginVO;
import com.mazhj.felix.user.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mazhj
 */
@RestController
@RequestMapping("user")
public class UserController extends BaseController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public AjaxResult login(@RequestBody Params params){
        String userId = params.getStringValue("userId");
        String password = params.getStringValue("password");
        LoginVO loginVO = this.userService.login(userId, password);
        return success(loginVO);
    }

    @PostMapping("/register")
    public AjaxResult register(@RequestBody UserParam userParam){
        return success();
    }
}
