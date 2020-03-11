package com.example.film.mapper;

import com.example.film.entity.Type;

import java.util.List;

public interface TypeMapper {

    List<Type> findAll();

    int deleteByPrimaryKey(Integer id);

    int insert(Type record);

    int insertSelective(Type record);

    Type selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Type record);

    int updateByPrimaryKey(Type record);
}