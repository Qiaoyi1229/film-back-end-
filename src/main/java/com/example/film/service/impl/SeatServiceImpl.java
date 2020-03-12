package com.example.film.service.impl;

import com.example.film.entity.Seat;
import com.example.film.mapper.SeatMapper;
import com.example.film.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 陈乐
 * @version 1.0
 * @date 2020/3/12 12:56
 */

@Service
public class SeatServiceImpl implements SeatService {

    @Autowired
    SeatMapper seatMapper;

    @Override
    public List<Seat> findByModel(Seat seat) {
        return seatMapper.findByModel(seat);
    }

    @Override
    public Integer insert(Seat seat) {
        seatMapper.insertSelective(seat);
        return seat.getId();
    }

    @Override
    public Integer update(Seat seat) {
        return seatMapper.updateByPrimaryKeySelective(seat);
    }

    @Override
    public Integer delete(Integer id) {
        return seatMapper.deleteByPrimaryKey(id);
    }
}
