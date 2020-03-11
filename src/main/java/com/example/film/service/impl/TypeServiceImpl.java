package com.example.film.service.impl;

import com.example.film.entity.Type;
import com.example.film.mapper.TypeMapper;
import com.example.film.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 陈乐
 * @version 1.0
 * @date 2020/3/11 15:09
 */
@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    TypeMapper typeMapper;

    @Override
    public List<Type> findAll() {
        return typeMapper.findAll();
    }

    @Override
    public Integer insert(Type type) {
        return typeMapper.insertSelective(type);
    }

    @Override
    public Integer update(Type type) {
        return typeMapper.updateByPrimaryKeySelective(type);
    }

    @Override
    public Integer delete(Integer id) {
        return typeMapper.deleteByPrimaryKey(id);
    }
}
