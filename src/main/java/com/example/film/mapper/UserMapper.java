package com.example.film.mapper;

import com.example.film.entity.User;

import java.util.List;

public interface UserMapper {

    List<User> findByModel(User user);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}