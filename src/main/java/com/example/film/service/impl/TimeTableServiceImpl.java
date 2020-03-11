package com.example.film.service.impl;

import com.example.film.doo.TimeTableDo;
import com.example.film.entity.TimeTable;
import com.example.film.mapper.TimeTableMapper;
import com.example.film.service.TimeTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 陈乐
 * @version 1.0
 * @date 2020/3/11 22:24
 */
@Service
public class TimeTableServiceImpl implements TimeTableService {

    @Autowired
    TimeTableMapper timeTableMapper;

    @Override
    public List<TimeTableDo> findByModel(TimeTable timeTable) {
        return timeTableMapper.findByModel(timeTable);
    }

    @Override
    public Integer insert(TimeTable timeTable) {
        return timeTableMapper.insertSelective(timeTable);
    }

    @Override
    public Integer update(TimeTable timeTable) {
        return timeTableMapper.updateByPrimaryKeySelective(timeTable);
    }

    @Override
    public Integer delete(Integer id) {
        return timeTableMapper.deleteByPrimaryKey(id);
    }
}
