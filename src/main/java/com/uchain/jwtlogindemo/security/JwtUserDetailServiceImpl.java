package com.uchain.jwtlogindemo.security;

import com.uchain.jwtlogindemo.entity.User;
import com.uchain.jwtlogindemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author；lzh
 * @Date:2019/8/1320:47 Descirption:
 */
@Service
@Slf4j
public class JwtUserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String stuId) throws UsernameNotFoundException {
        User user = userService.getUserByUserId(stuId);
        if(user == null) {
            log.info("用户不存在");
            throw new UsernameNotFoundException(String.format("用户名为 %s 的用户不存在！", stuId));
        } else {
            Integer role = user.getRole();
            return  new JwtUser(stuId, user.getPassword(), role);
        }
    }
}
