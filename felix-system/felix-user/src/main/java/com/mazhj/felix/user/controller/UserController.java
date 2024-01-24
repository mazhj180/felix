package com.mazhj.felix.user.controller;

import com.mazhj.common.auth.anno.Auth;
import com.mazhj.common.auth.enums.AccountLevel;
import com.mazhj.common.core.utils.Convert;
import com.mazhj.common.web.controller.BaseController;
import com.mazhj.common.web.request.Params;
import com.mazhj.common.web.response.AjaxResult;
import com.mazhj.common.web.response.Message;
import com.mazhj.felix.user.pojo.model.User;
import com.mazhj.felix.user.pojo.param.UserParam;
import com.mazhj.felix.user.pojo.vo.LoginVO;
import com.mazhj.felix.user.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        User user = Convert.to(userParam, User.class);
        Message message = this.userService.register(user);
        return success().buildMsg(message.getMessage());
    }

    @GetMapping("/get-user")
    public AjaxResult getUser(@RequestParam(required = false) String userId,@RequestParam(required = false) String userName){
        startPage();
        List<User> users = this.userService.getUsers(userId, userName);
        return success(users);
    }

    @Auth(AccountLevel.ADMINISTRATORS)
    @PostMapping("/update-state")
    public AjaxResult updateState(User user){
        this.userService.updateState(user);
        return success();
    }

}
