package com.uchain.jwtlogindemo.controller;

import com.uchain.jwtlogindemo.Enum.RoleEnum;
import com.uchain.jwtlogindemo.accessctro.RoleNum;
import com.uchain.jwtlogindemo.entity.User;
import com.uchain.jwtlogindemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author；lzh
 * @Date:2019/8/1615:18 Descirption:
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/getAll")
    @RoleNum(role = RoleEnum.ADMIN)
    public Map<String, List<User>> getAllUser() {
        Map<String, List<User>> map = userService.getAllUser();
        return map;
    }
}
