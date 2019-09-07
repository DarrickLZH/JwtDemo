package com.uchain.jwtlogindemo.service.impl;

import com.uchain.jwtlogindemo.dao.UserMapper;
import com.uchain.jwtlogindemo.entity.User;
import com.uchain.jwtlogindemo.security.JwtProperties;
import com.uchain.jwtlogindemo.security.JwtUserDetailServiceImpl;
import com.uchain.jwtlogindemo.service.UserService;
import com.uchain.jwtlogindemo.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author；lzh
 * @Date:2019/8/1415:41 Descirption:
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUserDetailServiceImpl jwtUserDetailService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtProperties jwtProperties;

    @Override
    public User getUserByUserId(String userId) {
        return userMapper.getUserByUserId(userId);
    }

    @Override
    public Map login(String username, String password, HttpServletResponse response) {
        User user = userMapper.getUserByUserId(username);
        if (user == null) {
            Map<String, java.io.Serializable> map = new HashMap<>();
            map.put("code",0);
            map.put("message","用户不存在.");
            return map;
        }
        UserDetails userDetails = jwtUserDetailService.loadUserByUsername(username);
        if (!(new BCryptPasswordEncoder().matches(password, userDetails.getPassword()))) {
            Map<String, java.io.Serializable> map = new HashMap<String, java.io.Serializable>();
            map.put("code",1);
            map.put("message","密码错误..");
            return map;
        }
        //TODO，将username和password被获得后封装到UsernamePasswordAuthenticationToken
        Authentication token = new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
        //TODO，token被传递给AuthenticationManager进行验证
        Authentication authentication = authenticationManager.authenticate(token);
        //将生成的authentication放入容器中，生成安全的上下文
        log.info("验证成功.");
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //生成token
        final String realToken = jwtTokenUtil.generateToken(userDetails);
        response.addHeader(jwtProperties.getTokenName(), realToken);

        Map<String, String> map = new HashMap<>();
        map.put("message", "token生成成功.");
        map.put("token", realToken);

        return map;
}

    @Override
    public User getCurrentUser() {
        //获取当前认证的authentication
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String stuId = authentication.getName();
        String key = "anonymousUser";
        //TODO,当登陆时name会自动赋值为登陆时的用户名，如果未登陆默认值为anonymousUser
        if (!stuId.equals(key)) {
            return getUserByUserId(stuId);
        }
        return null;
    }

    @Override
    public Map<String, List<User>> getAllUser() {
        List<User> users = userMapper.selectAll();
        Map<String, List<User>> map = new HashMap<>();
        map.put("查询成功", users);
        return map;
    }
}
