package com.example.film.service;

import com.example.film.entity.Cinema;

import java.util.List;

/**
 * @author 陈乐
 * @version 1.0
 * @date 2020/3/27 16:03
 */
public interface CinemaService {

    List<Cinema> findAll();

    Integer insert(Cinema cinema);

    Integer update(Cinema cinema);

    Integer delete(Integer id);
}
