package com.uchain.jwtlogindemo.controller;

import com.uchain.jwtlogindemo.Enum.RoleEnum;
import com.uchain.jwtlogindemo.accessctro.RoleNum;
import com.uchain.jwtlogindemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author；lzh
 * @Date:2019/8/1412:03 Descirption:
 */
@RestController
public class LogInController {

    @Autowired
    private UserService userService;

    /**
     * 登录接口
     */
    @PostMapping("/login")
    public Map login(String username, String password, HttpServletResponse response) {
        return userService.login(username, password, response);
    }
}
