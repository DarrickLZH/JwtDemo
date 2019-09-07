package com.uchain.jwtlogindemo.security;

import com.uchain.jwtlogindemo.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author；lzh
 * @Date:2019/8/1321:26 Descirption:
 */

@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private String tokenHeader = "Authorization";

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        if (httpServletRequest.getMethod().equals("OPTIONS")) {
            //TODO，这里是解决前后端对接时的跨域问题
            log.info("浏览器的请求预处理");
            httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
            httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS,DELETE");
            httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
            httpServletResponse.setHeader("Access-Control-Allow-Headers", "Origin,X-Requested-With,Content-Type,Accept,Authorization,token,Cookie");
            return;
        } else {
            String requestUrl = httpServletRequest.getRequestURI();
            log.info("requestURL: {}", requestUrl);
            String authToken = httpServletRequest.getHeader(this.tokenHeader);

            String stuId = jwtTokenUtil.getUsernameFromToken(authToken);

            log.info("checking authentication for user " + stuId);

            //当token中的username不为空时进行验证token是否是有效的token
            if (stuId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                //token中username不为空，并且Context中的认证为空，进行token验证
                //TODO,从数据库得到带有密码的完整user信息
                UserDetails userDetails = userDetailsService.loadUserByUsername(stuId);
                log.info("加载userDetails:{}", userDetails.getUsername());
                if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                    /**
                     * UsernamePasswordAuthenticationToken继承AbstractAuthenticationToken实现Authentication
                     * 所以当在页面中输入用户名和密码之后首先会进入到UsernamePasswordAuthenticationToken验证(Authentication)，
                     * 然后生成的Authentication会被交由AuthenticationManager来进行管理
                     * 而AuthenticationManager管理一系列的AuthenticationProvider，
                     * 而每一个Provider都会通UserDetailsService和UserDetail来返回一个
                     * 以UsernamePasswordAuthenticationToken实现的带用户名和密码以及权限的Authentication
                     */
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    log.info("authenticated user " + stuId + ", setting security context");
                    //将authentication放入SecurityContextHolder中
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
            filterChain.doFilter(httpServletRequest, httpServletResponse);

        }
    }
}
