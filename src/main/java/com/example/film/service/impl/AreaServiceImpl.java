package com.example.film.service.impl;

import com.example.film.entity.Area;
import com.example.film.mapper.AreaMapper;
import com.example.film.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 陈乐
 * @version 1.0
 * @date 2020/3/11 15:41
 */
@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    AreaMapper areaMapper;


    @Override
    public List<Area> findAll() {
        return areaMapper.findAll();
    }

    @Override
    public Integer insert(Area area) {
        return areaMapper.insertSelective(area);
    }

    @Override
    public Integer update(Area area) {
        return areaMapper.updateByPrimaryKeySelective(area);
    }

    @Override
    public Integer delete(Integer id) {
        return areaMapper.deleteByPrimaryKey(id);
    }
}
