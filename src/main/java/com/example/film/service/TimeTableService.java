package com.example.film.service;

import com.example.film.doo.TimeTableDo;
import com.example.film.entity.TimeTable;

import java.util.List;

/**
 * @author 陈乐
 * @version 1.0
 * @date 2020/3/11 22:23
 */
public interface TimeTableService {

    List<TimeTableDo> findByModel(TimeTable timeTable);

    Integer insert(TimeTable timeTable);

    Integer update(TimeTable timeTable);

    Integer delete(Integer id);
}
