package com.example.film.mapper;

import com.example.film.doo.OrderDo;
import com.example.film.entity.Order;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.List;

public interface OrderMapper {

    List<OrderDo> getByWeek();

    Integer getTotal();

    List<OrderDo> ageStatistical(Integer id);

    List<OrderDo> priceStatistical();

    List<OrderDo> typeStatistical();

    List<OrderDo> findByModel(Order order);

    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}