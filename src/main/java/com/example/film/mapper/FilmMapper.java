package com.example.film.mapper;

import com.example.film.entity.Film;

import java.util.List;

public interface FilmMapper {

    List<Film> findByModel(Film film);

    int deleteByPrimaryKey(Integer id);

    int insert(Film record);

    int insertSelective(Film record);

    Film selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Film record);

    int updateByPrimaryKey(Film record);
}