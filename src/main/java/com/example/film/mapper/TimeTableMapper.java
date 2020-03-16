package com.example.film.mapper;

import com.example.film.doo.TimeTableDo;
import com.example.film.entity.TimeTable;
import com.example.film.query.TimeTableQueryModel;

import java.util.List;

public interface TimeTableMapper {

    List<TimeTableDo> findByModel(TimeTableQueryModel timeTableQueryModel);

    int deleteByPrimaryKey(Integer id);

    int insert(TimeTable record);

    int insertSelective(TimeTable record);

    TimeTable selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TimeTable record);

    int updateByPrimaryKey(TimeTable record);
}