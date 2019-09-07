package com.uchain.jwtlogindemo.security;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.uchain.jwtlogindemo.Enum.ResultEnum;
import com.uchain.jwtlogindemo.accessctro.RoleNum;
import com.uchain.jwtlogindemo.entity.User;
import com.uchain.jwtlogindemo.service.UserService;
import com.uchain.jwtlogindemo.util.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author；lzh
 * @Date:2019/8/1614:58 Descirption:权限验证
 */
@Slf4j
@Service
public class AuthRoleInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        String json = JSON.toJSONString(ResultVOUtil.error(ResultEnum.AUTHENTICATION_ERROR));
        User user = userService.getCurrentUser();
        //若当前用户为未认证用户则跳过权限验证，交给security做身份认证
        if (user == null) {
            return true;
        }
        log.info("============执行权限验证============");
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            //获取方法上的注解
            RoleNum roleNum = handlerMethod.getMethodAnnotation(RoleNum.class);
            if (roleNum == null) {
                return true;
            }
            //注解上的role对应的值
            Integer roleValue = roleNum.role().getValue();
            //用户数据库中对应的role值
            Integer userValue = user.getRole();
            log.info("RoleValue:{},userRole:{}", roleValue, userValue);
            //比较，判断权限
            if (userValue >= roleValue) {
                return true;
            } else {
                json = JSON.toJSONString(ResultVOUtil.error(ResultEnum.PERMISSION_DENNY));
                log.info("============权限不足===============");
            }
        }
        response.getWriter().append(json);
        return false;
    }
}
