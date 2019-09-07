package com.uchain.jwtlogindemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.uchain.jwtlogindemo.dao")
public class JwtLogInDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwtLogInDemoApplication.class, args);
    }

}
