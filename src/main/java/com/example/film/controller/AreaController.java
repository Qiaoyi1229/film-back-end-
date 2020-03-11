package com.example.film.controller;

import com.example.film.entity.Area;
import com.example.film.service.AreaService;
import com.example.film.utils.ResultUtil;
import com.example.film.utils.SuccessCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author 陈乐
 * @version 1.0
 * @date 2020/3/11 15:42
 */
@RestController
@RequestMapping("/api/v1/film/area")
public class AreaController {

    @Autowired
    AreaService areaService;

    @RequestMapping(value = "/findAll")
    public ResultUtil findAll() {
        return ResultUtil.build(SuccessCode.SUCCESS_CODE, SuccessCode.FIND_SUCCESS, areaService.findAll());
    }

    @RequestMapping(value = "/insert")
    public ResultUtil insert(Area area) {
        area.setCreateTime(new Date());
        areaService.insert(area);
        return ResultUtil.build(SuccessCode.SUCCESS_CODE, SuccessCode.INSERT_SUCCESS, areaService.findAll());
    }

    @RequestMapping(value = "/update")
    public ResultUtil update(Area area) {
        areaService.update(area);
        return ResultUtil.build(SuccessCode.SUCCESS_CODE, SuccessCode.UPDATE_SUCCESS, areaService.findAll());
    }

    @RequestMapping(value = "/delete")
    public ResultUtil delete(Integer id) {
        areaService.delete(id);
        return ResultUtil.build(SuccessCode.SUCCESS_CODE, SuccessCode.DEL_SUCCESS, areaService.findAll());
    }
}
