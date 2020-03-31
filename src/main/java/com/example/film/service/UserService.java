package com.example.film.service;

import com.example.film.entity.User;

import java.util.List;

/**
 * @author 陈乐
 * @version 1.0
 * @date 2020/3/11 9:12
 */
public interface UserService {

    List<User> findByModel(User user);

    Integer insert(User user);

    Integer update(User user);

    Integer delete(Integer id);

    User findById(Integer id);
}
