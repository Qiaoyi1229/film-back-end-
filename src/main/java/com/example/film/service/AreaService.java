package com.example.film.service;

import com.example.film.entity.Area;

import java.util.List;

/**
 * @author 陈乐
 * @version 1.0
 * @date 2020/3/11 15:40
 */
public interface AreaService {

    List<Area> findAll();

    Integer insert(Area area);

    Integer update(Area area);

    Integer delete(Integer id);
}
