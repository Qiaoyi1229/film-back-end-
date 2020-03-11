package com.example.film.mapper;

import com.example.film.entity.TimeTable;

public interface TimeTableMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TimeTable record);

    int insertSelective(TimeTable record);

    TimeTable selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TimeTable record);

    int updateByPrimaryKey(TimeTable record);
}