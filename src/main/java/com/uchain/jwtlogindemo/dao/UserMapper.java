package com.uchain.jwtlogindemo.dao;

import com.uchain.jwtlogindemo.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    User selectByPrimaryKey(Integer id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    User getUserByUserId(String stuId);
}