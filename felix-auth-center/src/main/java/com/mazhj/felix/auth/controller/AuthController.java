package com.mazhj.felix.auth.controller;

import com.mazhj.common.core.exception.AuthException;
import com.mazhj.common.core.utils.JwtUtil;
import com.mazhj.common.pojo.claims.Claims;
import com.mazhj.common.web.controller.BaseController;
import com.mazhj.common.web.response.AjaxResult;
import com.mazhj.felix.auth.pojo.vo.AuthVO;
import com.mazhj.felix.auth.service.AuthService;
import com.nimbusds.jose.JOSEException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.HashMap;

/**
 * @author mazhj
 */
@RestController
@RequestMapping("/auth")
public class AuthController extends BaseController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public AjaxResult login(String account,String password) {
        AuthVO vo = this.authService.login(account, password);
        return success(vo);
    }

    @GetMapping("/valid/")
    public AjaxResult validated(String accessToken){
        try {
            Claims claims = JwtUtil.validateToken(accessToken);
            return success(new HashMap<String,Object>(3){{
                put("message","success");
                put("access",true);
                put("claims",claims);
            }});
        } catch (AuthException e) {
            return success(new HashMap<String,Object>(2){{
                put("message",e.getMessage());
                put("access", false);
            }});
        } catch (ParseException | JOSEException e) {
            return success(new HashMap<String,Object>(2){{
                put("message","fail");
                put("access",false);
            }});
        }
    }

    @GetMapping("/refresh")
    public AjaxResult refreshToken(String refreshToken){
        try {
            Claims claims = JwtUtil.validateToken(refreshToken);
            long twoHours = 3600 * 2 * 1000;
            String accessToken = JwtUtil.generateToken(claims.setExp(System.currentTimeMillis() + twoHours));
            return success(new HashMap<String,Object>(3){{
                put("message","续期成功");
                put("refresh", true);
                put("accessToken",accessToken);
            }});
        } catch (AuthException e) {
            return success(new HashMap<String,Object>(2){{
                put("message","refresh token "+e.getMessage());
                put("refresh", false);
            }});
        } catch (ParseException | JOSEException e) {
            return success(new HashMap<String,Object>(2){{
                put("message","未知错误");
                put("refresh", false);
            }});
        }
    }
}
