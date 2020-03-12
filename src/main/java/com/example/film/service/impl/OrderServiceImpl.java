package com.example.film.service.impl;

import com.example.film.doo.OrderDo;
import com.example.film.entity.Order;
import com.example.film.mapper.OrderMapper;
import com.example.film.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 陈乐
 * @version 1.0
 * @date 2020/3/12 19:01
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Override
    public List<OrderDo> ageStatistical(Integer id) {
        return orderMapper.ageStatistical(id);
    }

    @Override
    public List<OrderDo> priceStatistical() {
        return orderMapper.priceStatistical();
    }

    @Override
    public List<OrderDo> typeStatistical() {
        return orderMapper.typeStatistical();
    }

    @Override
    public List<OrderDo> findByModel(Order order) {
        return orderMapper.findByModel(order);
    }

    @Override
    public Integer insert(Order order) {
        orderMapper.insertSelective(order);
        return order.getId();
    }

    @Override
    public Integer update(Order order) {
        return orderMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public Integer delete(Integer id) {
        return orderMapper.deleteByPrimaryKey(id);
    }
}
