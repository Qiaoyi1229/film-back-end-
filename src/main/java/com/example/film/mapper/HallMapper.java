package com.example.film.mapper;

import com.example.film.entity.Hall;

public interface HallMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Hall record);

    int insertSelective(Hall record);

    Hall selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Hall record);

    int updateByPrimaryKey(Hall record);
}