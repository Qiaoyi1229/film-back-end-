package com.example.film.controller;

import com.example.film.entity.Type;
import com.example.film.service.TypeService;
import com.example.film.utils.ResultUtil;
import com.example.film.utils.SuccessCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author 陈乐
 * @version 1.0
 * @date 2020/3/11 15:27
 */
@RestController
@RequestMapping("/api/v1/film/type")
public class TypeController {

    @Autowired
    TypeService typeService;

    @RequestMapping(value = "/findAll")
    public ResultUtil findAll() {
        return ResultUtil.build(SuccessCode.SUCCESS_CODE, SuccessCode.FIND_SUCCESS, typeService.findAll());
    }

    @RequestMapping(value = "/insert")
    public ResultUtil insert(Type type) {
        type.setCreateTime(new Date());
        typeService.insert(type);
        return ResultUtil.build(SuccessCode.SUCCESS_CODE, SuccessCode.INSERT_SUCCESS, typeService.findAll());
    }

    @RequestMapping(value = "/update")
    public ResultUtil update(Type type) {
        typeService.update(type);
        return ResultUtil.build(SuccessCode.SUCCESS_CODE, SuccessCode.UPDATE_SUCCESS, typeService.findAll());
    }

    @RequestMapping(value = "/delete")
    public ResultUtil delete(Integer id) {
        typeService.delete(id);
        return ResultUtil.build(SuccessCode.SUCCESS_CODE, SuccessCode.DEL_SUCCESS, typeService.findAll());
    }
}
