package com.example.film.controller;

import com.example.film.entity.Hall;
import com.example.film.service.HallService;
import com.example.film.utils.ResultUtil;
import com.example.film.utils.SuccessCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 陈乐
 * @version 1.0
 * @date 2020/3/11 16:01
 */
@RestController
@RequestMapping("/api/v1/film/hall")
public class HallController {

    @Autowired
    HallService hallService;

    @RequestMapping(value = "/findByModel")
    public ResultUtil findByModel(Hall hall) {
        return ResultUtil.build(SuccessCode.SUCCESS_CODE, SuccessCode.FIND_SUCCESS, hallService.findByModel(hall));
    }

    @RequestMapping(value = "/findAll")
    public ResultUtil findAll() {
        return ResultUtil.build(SuccessCode.SUCCESS_CODE, SuccessCode.FIND_SUCCESS, hallService.findAll());
    }

    @RequestMapping(value = "/insert")
    public ResultUtil insert(Hall hall) {
        hallService.insert(hall);
        return ResultUtil.build(SuccessCode.SUCCESS_CODE, SuccessCode.INSERT_SUCCESS, hallService.findAll());
    }

    @RequestMapping(value = "/update")
    public ResultUtil update(Hall hall) {
        hallService.update(hall);
        return ResultUtil.build(SuccessCode.SUCCESS_CODE, SuccessCode.UPDATE_SUCCESS, hallService.findAll());
    }

    @RequestMapping(value = "/delete")
    public ResultUtil delete(Integer id) {
        hallService.delete(id);
        return ResultUtil.build(SuccessCode.SUCCESS_CODE, SuccessCode.DEL_SUCCESS, hallService.findAll());
    }
}
