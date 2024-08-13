package com.mazhj.felix.user.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.mazhj.common.auth.anno.Auth;
import com.mazhj.common.auth.enums.AccountLevel;
import com.mazhj.common.core.utils.Convert;
import com.mazhj.common.pojo.dto.BookDTO;
import com.mazhj.common.web.controller.BaseController;
import com.mazhj.common.web.holder.UserContextHolder;
import com.mazhj.common.web.request.Params;
import com.mazhj.common.web.response.AjaxResult;
import com.mazhj.common.web.response.Message;
import com.mazhj.felix.user.pojo.model.User;
import com.mazhj.felix.user.pojo.param.Reason;
import com.mazhj.felix.user.pojo.param.UserParam;
import com.mazhj.felix.user.pojo.vo.LoginVO;
import com.mazhj.felix.user.service.GuessYouService;
import com.mazhj.felix.user.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author mazhj
 */
@RestController
public class UserController extends BaseController {

    private final UserService userService;

    private final GuessYouService guessYouService;

    public UserController(UserService userService, GuessYouService guessYouService) {
        this.userService = userService;
        this.guessYouService = guessYouService;
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
        return success("ok").buildMsg(message.getMessage());
    }

    @GetMapping("/is-exited/{userId}")
    public AjaxResult isExited(@PathVariable String userId) {
        Boolean res = this.userService.userIsExited(userId);
        return success(res?"yes":"no");
    }

    @GetMapping("/is-writer/{userId}")
    public AjaxResult isWriter(@PathVariable String userId) {
        AccountLevel accountLevel = this.userService.userLevel(userId);
        return success(accountLevel.equals(AccountLevel.WRITER)?"yes":"no");
    }

    @GetMapping("/get-user")
    public AjaxResult getUser(@RequestParam(required = false) String userId,
                              @RequestParam(required = false) String userName,
                              @RequestParam(required = false) Boolean isWriter
                              ){
        startPage();
        Page<User> users = this.userService.getUsers(userId, userName, isWriter);
        PageInfo<User> userPage = new PageInfo<>(users);
        return success(userPage);
    }

    @Auth(AccountLevel.ADMINISTRATOR)
    @PostMapping("/update-state")
    public AjaxResult updateState(User user){
        this.userService.updateState(user);
        return success();
    }

    @GetMapping("/guess-you")
    public AjaxResult guess(String userId){
        List<BookDTO> possibleLikes = this.guessYouService.getPossibleLikes(userId, new Reason());
        return success(possibleLikes);
    }

    @GetMapping("/get-user-info")
    public AjaxResult getUserInfo(@RequestParam String userId) {
        User user = this.userService.getUserInfo(userId);
        return success(user);
    }



}
