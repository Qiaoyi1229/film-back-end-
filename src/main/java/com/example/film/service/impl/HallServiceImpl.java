package com.example.film.service.impl;

import com.example.film.entity.Hall;
import com.example.film.mapper.HallMapper;
import com.example.film.service.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 陈乐
 * @version 1.0
 * @date 2020/3/11 15:59
 */
@Service
public class HallServiceImpl implements HallService {

    @Autowired
    HallMapper hallMapper;

    @Override
    public List<Hall> findAll() {
        return hallMapper.findAll();
    }

    @Override
    public Integer insert(Hall hall) {
        return hallMapper.insertSelective(hall);
    }

    @Override
    public Integer update(Hall hall) {
        return hallMapper.updateByPrimaryKeySelective(hall);
    }

    @Override
    public Integer delete(Integer id) {
        return hallMapper.deleteByPrimaryKey(id);
    }
}
