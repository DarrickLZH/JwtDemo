package com.uchain.jwtlogindemo.service;

import com.uchain.jwtlogindemo.entity.User;
import org.springframework.mobile.device.Device;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author；lzh
 * @Date:2019/8/1415:39 Descirption:
 */
public interface UserService {

    User getUserByUserId(String userId);

    Map login(String username, String password, HttpServletResponse response);

    User getCurrentUser();

    Map<String, List<User>> getAllUser();
}
