package com.example.film.service;

import com.example.film.entity.Seat;

import java.util.List;

/**
 * @author 陈乐
 * @version 1.0
 * @date 2020/3/12 12:55
 */
public interface SeatService {

    List<Seat> findByModel(Seat seat);

    Integer insert(Seat seat);

    Integer update(Seat seat);

    Integer delete(Integer id);
}
