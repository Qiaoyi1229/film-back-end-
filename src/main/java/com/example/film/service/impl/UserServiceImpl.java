package com.example.film.service.impl;

import com.example.film.entity.User;
import com.example.film.mapper.UserMapper;
import com.example.film.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 陈乐
 * @version 1.0
 * @date 2020/3/11 9:16
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> findByModel(User user) {
        return userMapper.findByModel(user);
    }

    @Override
    public Integer insert(User user) {
        return userMapper.insertSelective(user);
    }

    @Override
    public Integer update(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public Integer delete(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public User findById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
