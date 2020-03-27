package com.example.film.mapper;

import com.example.film.entity.Cinema;

import java.util.List;

public interface CinemaMapper {

    List<Cinema> findAll();

    int deleteByPrimaryKey(Integer id);

    int insert(Cinema record);

    int insertSelective(Cinema record);

    Cinema selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Cinema record);

    int updateByPrimaryKey(Cinema record);
}