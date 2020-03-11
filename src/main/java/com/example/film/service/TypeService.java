package com.example.film.service;

import com.example.film.entity.Type;

import java.util.List;

/**
 * @author 陈乐
 * @version 1.0
 * @date 2020/3/11 14:58
 */
public interface TypeService {

    List<Type> findAll();

    Integer insert(Type type);

    Integer update(Type type);

    Integer delete(Integer id);


}
