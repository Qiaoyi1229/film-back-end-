package com.example.film.service.impl;

import com.example.film.doo.DetailDo;
import com.example.film.entity.Detail;
import com.example.film.mapper.DetailMapper;
import com.example.film.service.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 陈乐
 * @version 1.0
 * @date 2020/3/12 19:34
 */
@Service
public class DetailServiceImpl implements DetailService {

    @Autowired
    DetailMapper detailMapper;


    @Override
    public List<DetailDo> findByOrderId(Integer id) {
        return detailMapper.findByOrderId(id);
    }

    @Override
    public Integer insert(Detail detail) {
        return detailMapper.insertSelective(detail);
    }

    @Override
    public Integer update(Detail detail) {
        return detailMapper.updateByPrimaryKeySelective(detail);
    }

    @Override
    public Integer delete(Integer id) {
        return detailMapper.deleteByPrimaryKey(id);
    }
}
