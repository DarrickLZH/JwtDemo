package com.uchain.jwtlogindemo.security;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author；lzh
 * @Date:2019/8/1320:45 Descirption: JWTUser配置类
 */
@Data
public class JwtUser implements UserDetails {

    private String stuId;

    private String password;

    private Integer role;


    public JwtUser(String stuId, String password, Integer role) {
        this.stuId = stuId;
        this.password = password;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return stuId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
