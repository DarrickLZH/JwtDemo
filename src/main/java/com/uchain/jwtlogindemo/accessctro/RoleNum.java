package com.uchain.jwtlogindemo.accessctro;

import com.uchain.jwtlogindemo.Enum.RoleEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author；lzh
 * @Date:2019/8/1614:47 Descirption:
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RoleNum {
    //默认0为一般用户，1为普通管理员，2为超级管理员
    RoleEnum role();
}
