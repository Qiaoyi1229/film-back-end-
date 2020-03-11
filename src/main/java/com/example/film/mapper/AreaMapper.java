package com.example.film.mapper;

import com.example.film.entity.Area;

import java.util.List;

public interface AreaMapper {

    List<Area> findAll();

    int deleteByPrimaryKey(Integer id);

    int insert(Area record);

    int insertSelective(Area record);

    Area selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Area record);

    int updateByPrimaryKey(Area record);
}