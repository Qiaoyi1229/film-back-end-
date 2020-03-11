package com.example.film.mapper;

import com.example.film.doo.TimeTableDo;
import com.example.film.entity.TimeTable;

import java.util.List;

public interface TimeTableMapper {

    List<TimeTableDo> findByModel(TimeTable timeTable);

    int deleteByPrimaryKey(Integer id);

    int insert(TimeTable record);

    int insertSelective(TimeTable record);

    TimeTable selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TimeTable record);

    int updateByPrimaryKey(TimeTable record);
}