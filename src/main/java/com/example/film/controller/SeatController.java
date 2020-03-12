package com.example.film.controller;

import com.example.film.entity.Seat;
import com.example.film.service.SeatService;
import com.example.film.utils.ResultUtil;
import com.example.film.utils.SuccessCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 陈乐
 * @version 1.0
 * @date 2020/3/12 12:58
 */
@RestController
@RequestMapping("/api/v1/film/seat")
public class SeatController {

    @Autowired
    SeatService seatService;

    @RequestMapping(value = "/findByModel")
    public ResultUtil findByModel(Seat seat) {
        return ResultUtil.build(SuccessCode.SUCCESS_CODE, SuccessCode.FIND_SUCCESS, seatService.findByModel(seat));
    }

    @RequestMapping(value = "/insert")
    public ResultUtil insert(Seat seat) {
        seatService.insert(seat);
        return ResultUtil.build(SuccessCode.SUCCESS_CODE, SuccessCode.INSERT_SUCCESS, null);
    }

    @RequestMapping(value = "/update")
    public ResultUtil update(Seat seat) {
        seatService.update(seat);
        return ResultUtil.build(SuccessCode.SUCCESS_CODE, SuccessCode.UPDATE_SUCCESS, null);
    }

    @RequestMapping(value = "/delete")
    public ResultUtil delete(Integer id) {
        seatService.delete(id);
        return ResultUtil.build(SuccessCode.SUCCESS_CODE, SuccessCode.DEL_SUCCESS, null);
    }
}
