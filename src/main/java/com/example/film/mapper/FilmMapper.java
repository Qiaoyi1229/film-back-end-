package com.example.film.mapper;

import com.example.film.doo.FilmDo;
import com.example.film.entity.Film;
import com.example.film.query.FilmQuery;

import java.util.List;

public interface FilmMapper {

    List<FilmDo> findByModel(FilmQuery film);

    int deleteByPrimaryKey(Integer id);

    int insert(Film record);

    int insertSelective(Film record);

    Film selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Film record);

    int updateByPrimaryKey(Film record);
}