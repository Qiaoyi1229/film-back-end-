package com.example.film.service;

import com.example.film.doo.HallDo;
import com.example.film.entity.Hall;

import java.util.List;

/**
 * @author 陈乐
 * @version 1.0
 * @date 2020/3/11 15:58
 */
public interface HallService {

    List<HallDo> findByModel(Hall hall);

    List<Hall> findAll();

    Integer insert(Hall hall);

    Integer update(Hall hall);

    Integer delete(Integer id);
}
