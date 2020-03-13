package com.example.film.controller;

import com.example.film.doo.OrderDo;
import com.example.film.dto.resp.TypeStatistical;
import com.example.film.service.OrderService;
import com.example.film.utils.ResultUtil;
import com.example.film.utils.SuccessCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 陈乐
 * @version 1.0
 * @date 2020/3/12 23:24
 */
@RestController
@RequestMapping("/api/v1/film/analysis")
public class DataAnalysisController {


    @Autowired
    OrderService orderService;


    /**
     * 总票房统计
     *
     * @return
     */
    @RequestMapping(value = "/getTotal")
    public ResultUtil getTotal() {
        return ResultUtil.build(SuccessCode.SUCCESS_CODE, SuccessCode.FIND_SUCCESS, orderService.getTotal());
    }


    /**
     * 每周票房统计
     *
     * @return
     */
    @RequestMapping(value = "/getByWeek")
    public ResultUtil getByWeek() {
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
        return ResultUtil.build(SuccessCode.SUCCESS_CODE, SuccessCode.FIND_SUCCESS, typeStatistical);
    }

    /**
     * 类型统计：每种类型的电影票房多少
     *
     * @return
     */
    @RequestMapping(value = "/typeStatistical")
    public ResultUtil typeStatistical() {
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
        return ResultUtil.build(SuccessCode.SUCCESS_CODE, SuccessCode.FIND_SUCCESS, typeStatistical);
    }

    /**
     * 票房统计：每个电影的票房
     *
     * @return
     */
    @RequestMapping(value = "/priceStatistical")
    public ResultUtil priceStatistical() {
        List<OrderDo> orderDos = orderService.priceStatistical();
        List total = new ArrayList();
        List typeName = new ArrayList();
        for (OrderDo item : orderDos) {
            total.add(item.getTotal());
            typeName.add(item.getFilmName());
        }
        TypeStatistical typeStatistical = new TypeStatistical();
        typeStatistical.setTotal(total);
        typeStatistical.setTypeName(typeName);
        return ResultUtil.build(SuccessCode.SUCCESS_CODE, SuccessCode.FIND_SUCCESS, typeStatistical);
    }

    /**
     * 年龄分布统计：选择电影，查看他的年龄分布
     *
     * @return
     */
    @RequestMapping(value = "/ageStatistical")
    public ResultUtil ageStatistical(Integer id) {
        List<OrderDo> orderDos = orderService.ageStatistical(id);
        List total = new ArrayList();
        List typeName = new ArrayList();
        for (OrderDo item : orderDos) {
            total.add(item.getCount());
            typeName.add(item.getAgeRange());
        }
        TypeStatistical typeStatistical = new TypeStatistical();
        typeStatistical.setTotal(total);
        typeStatistical.setTypeName(typeName);
        return ResultUtil.build(SuccessCode.SUCCESS_CODE, SuccessCode.FIND_SUCCESS, typeStatistical);
    }
}
