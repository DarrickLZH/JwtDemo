package com.uchain.jwtlogindemo.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author；lzh
 * @Date:2019/8/1410:12 Descirption:
 */
@Data
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {


    private String secret;

    private long expiration;

    private String tokenStart;

    private String tokenName;

    /**
     * 添加用户默认密码
     */
    private String defaultPassword;
}
