package com.example.film.controller;

import com.example.film.dto.resp.SeatResp;
import com.example.film.entity.Seat;
import com.example.film.service.SeatService;
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
 * @date 2020/3/12 12:58
 */
@RestController
@RequestMapping("/api/v1/film/seat")
public class SeatController {

    @Autowired
    SeatService seatService;

    @RequestMapping(value = "/findByModel")
    public ResultUtil findByModel(Seat seat) {

        List<Seat> seats = seatService.findByModel(seat);

        List<Integer> row = new ArrayList<>();
        List<Integer> column = new ArrayList<>();
        for (Seat item : seats) {
            row.add(item.getRow());
            column.add(item.getNum());
        }
        SeatResp seatResp = new SeatResp();
        seatResp.setColumn(column);
        seatResp.setRow(row);
        return ResultUtil.build(SuccessCode.SUCCESS_CODE, SuccessCode.FIND_SUCCESS, seatResp);
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
