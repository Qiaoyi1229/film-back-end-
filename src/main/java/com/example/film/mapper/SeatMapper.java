package com.example.film.mapper;

import com.example.film.entity.Seat;

import java.util.List;

public interface SeatMapper {

    List<Seat> findByModel(Seat seat);

    int deleteByPrimaryKey(Integer id);

    int insert(Seat record);

    int insertSelective(Seat record);

    Seat selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Seat record);

    int updateByPrimaryKey(Seat record);
}