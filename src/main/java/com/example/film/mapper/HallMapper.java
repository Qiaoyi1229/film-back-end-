package com.example.film.mapper;

import com.example.film.doo.HallDo;
import com.example.film.entity.Hall;

import java.util.List;

public interface HallMapper {

    List<HallDo> findByModel(Hall hall);

    List<Hall> findAll();

    int deleteByPrimaryKey(Integer id);

    int insert(Hall record);

    int insertSelective(Hall record);

    Hall selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Hall record);

    int updateByPrimaryKey(Hall record);
}