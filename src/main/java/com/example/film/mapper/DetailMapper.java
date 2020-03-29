package com.example.film.mapper;

import com.example.film.doo.DetailDo;
import com.example.film.entity.Detail;

import java.util.List;

public interface DetailMapper {

    List<DetailDo> findByOrderId(Detail detail);

    int deleteByPrimaryKey(Integer id);

    int insert(Detail record);

    int insertSelective(Detail record);

    Detail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Detail record);

    int updateByPrimaryKey(Detail record);
}