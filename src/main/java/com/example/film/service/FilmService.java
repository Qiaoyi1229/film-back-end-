package com.example.film.service;

import com.example.film.doo.FilmDo;
import com.example.film.dto.req.FilmReq;
import com.example.film.entity.Film;

import java.util.List;

/**
 * @author 陈乐
 * @version 1.0
 * @date 2020/3/11 17:49
 */
public interface FilmService {

    List<FilmDo> findByCinemaId(Integer id);

    List<FilmDo> priceList();

    List<FilmDo> findByModel(FilmReq filmReq);

    Integer insert(Film film);

    Integer update(Film film);

    Integer delete(Integer id);
}
