package com.example.film.service.impl;

import com.example.film.doo.FilmDo;
import com.example.film.dto.req.FilmReq;
import com.example.film.entity.Film;
import com.example.film.mapper.FilmMapper;
import com.example.film.query.FilmQuery;
import com.example.film.service.FilmService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 陈乐
 * @version 1.0
 * @date 2020/3/11 18:05
 */
@Service
public class FilmServiceImpl implements FilmService {

    @Autowired
    FilmMapper filmMapper;

    @Override
    public List<FilmDo> findByModel(FilmReq filmReq) {
        FilmQuery film = new FilmQuery();
        BeanUtils.copyProperties(filmReq, film);
        return filmMapper.findByModel(film);
    }

    @Override
    public Integer insert(Film film) {
        return filmMapper.insertSelective(film);
    }

    @Override
    public Integer update(Film film) {
        return filmMapper.updateByPrimaryKeySelective(film);
    }

    @Override
    public Integer delete(Integer id) {
        return filmMapper.deleteByPrimaryKey(id);
    }
}
