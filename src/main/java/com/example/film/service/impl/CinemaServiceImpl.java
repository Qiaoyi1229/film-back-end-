package com.example.film.service.impl;

import com.example.film.entity.Cinema;
import com.example.film.mapper.CinemaMapper;
import com.example.film.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 陈乐
 * @version 1.0
 * @date 2020/3/27 16:04
 */
@Service
public class CinemaServiceImpl implements CinemaService {

    @Autowired
    CinemaMapper cinemaMapper;

    @Override
    public List<Cinema> findByFilmId(Integer id) {
        return cinemaMapper.findByFilmId(id);
    }

    @Override
    public List<Cinema> findAll() {
        return cinemaMapper.findAll();
    }

    @Override
    public Integer insert(Cinema cinema) {
        return cinemaMapper.insertSelective(cinema);
    }

    @Override
    public Integer update(Cinema cinema) {
        return cinemaMapper.updateByPrimaryKeySelective(cinema);
    }

    @Override
    public Integer delete(Integer id) {
        return cinemaMapper.deleteByPrimaryKey(id);
    }
}
