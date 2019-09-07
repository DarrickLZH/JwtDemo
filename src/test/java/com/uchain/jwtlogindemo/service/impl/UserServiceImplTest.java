package com.uchain.jwtlogindemo.service.impl;

import com.uchain.jwtlogindemo.entity.User;
import com.uchain.jwtlogindemo.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author；lzh
 * @Date:2019/8/1415:43 Descirption:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    public void getUserByUserId() {
        User user = userService.getUserByUserId("201831064119");
        Assert.assertNotNull(user);
    }

//    @Test
//    public void login() {
//        Map map = userService.login("201831064119","123456");
//        System.out.println(map);
//    }

    @Test
    public void bcrypt(){
        System.out.println(new BCryptPasswordEncoder().encode("123456"));
    }
}