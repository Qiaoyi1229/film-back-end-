package com.example.film.test;

import com.example.film.doo.OrderDo;
import com.example.film.dto.resp.TypeStatistical;
import com.example.film.entity.User;
import com.example.film.service.OrderService;
import com.example.film.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 陈乐
 * @version 1.0
 * @date 2020/3/11 9:18
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DataAnalysisTest {

    @Autowired
    OrderService orderService;

    @Test
    public void getTotal() {
        System.out.println(orderService.getTotal());
    }


    @Test
    public void getByWeek() {
        List<OrderDo> orderDos = orderService.getByWeek();
        List total = new ArrayList();
        List typeName = new ArrayList();
        for (OrderDo item : orderDos) {
            total.add(item.getTotal());
            typeName.add(item.getWeek());
        }
        TypeStatistical typeStatistical = new TypeStatistical();
        typeStatistical.setTotal(total);
        typeStatistical.setTypeName(typeName);
        System.out.println(typeStatistical.toString());
    }

    @Test
    public void typeStatistical() {
        List<OrderDo> orderDos = orderService.typeStatistical();
        List total = new ArrayList();
        List typeName = new ArrayList();
        for (OrderDo item : orderDos) {
            total.add(item.getTotal());
            typeName.add(item.getTypeName());
        }
        TypeStatistical typeStatistical = new TypeStatistical();
        typeStatistical.setTotal(total);
        typeStatistical.setTypeName(typeName);
        System.out.println(typeStatistical.toString());
    }
}
