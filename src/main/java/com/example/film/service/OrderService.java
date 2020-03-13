package com.example.film.service;

import com.example.film.doo.OrderDo;
import com.example.film.entity.Order;

import java.util.List;

/**
 * @author 陈乐
 * @version 1.0
 * @date 2020/3/12 19:00
 */
public interface OrderService {

    List<OrderDo> getByWeek();

    Integer getTotal();

    List<OrderDo> ageStatistical(Integer id);

    List<OrderDo> priceStatistical();

    List<OrderDo> typeStatistical();

    List<OrderDo> findByModel(Order order);

    Integer insert(Order order);

    Integer update(Order order);

    Integer delete(Integer id);
}
