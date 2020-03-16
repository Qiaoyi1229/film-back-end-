package com.example.film.controller;

import com.example.film.dto.req.TimeTableReq;
import com.example.film.entity.TimeTable;
import com.example.film.service.TimeTableService;
import com.example.film.utils.ResultUtil;
import com.example.film.utils.SuccessCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 陈乐
 * @version 1.0
 * @date 2020/3/11 22:26
 */
@RestController
@RequestMapping("/api/v1/film/timeTable")
public class TimeTableController {

    @Autowired
    TimeTableService timeTableService;

    @RequestMapping(value = "/findByModel")
    public ResultUtil findByModel(TimeTableReq timeTableReq) {
        return ResultUtil.build(SuccessCode.SUCCESS_CODE, SuccessCode.FIND_SUCCESS, timeTableService.findByModel(timeTableReq));
    }

    @RequestMapping(value = "/insert")
    public ResultUtil insert(TimeTable timeTable) {
        timeTableService.insert(timeTable);
        return ResultUtil.build(SuccessCode.SUCCESS_CODE, SuccessCode.INSERT_SUCCESS, timeTableService.findByModel(new TimeTableReq()));
    }

    @RequestMapping(value = "/update")
    public ResultUtil update(TimeTable timeTable) {
        timeTableService.update(timeTable);
        return ResultUtil.build(SuccessCode.SUCCESS_CODE, SuccessCode.UPDATE_SUCCESS, timeTableService.findByModel(new TimeTableReq()));
    }

    @RequestMapping(value = "/delete")
    public ResultUtil delete(Integer id) {
        timeTableService.delete(id);
        return ResultUtil.build(SuccessCode.SUCCESS_CODE, SuccessCode.DEL_SUCCESS, timeTableService.findByModel(new TimeTableReq()));
    }
}
