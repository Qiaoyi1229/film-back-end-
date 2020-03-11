package com.example.film.controller;

import com.example.film.entity.Film;
import com.example.film.service.FilmService;
import com.example.film.utils.ImageUpload;
import com.example.film.utils.ResultUtil;
import com.example.film.utils.SuccessCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * @author 陈乐
 * @version 1.0
 * @date 2020/3/11 18:06
 */
@RestController
@RequestMapping("/api/v1/film/film")
public class FilmController {

    @Autowired
    FilmService filmService;

    @RequestMapping(value = "/findByModel")
    public ResultUtil findByModel(Film film) {
        return ResultUtil.build(SuccessCode.SUCCESS_CODE, SuccessCode.FIND_SUCCESS, filmService.findByModel(film));
    }

    @RequestMapping(value = "/insert")
    public ResultUtil insert(@RequestParam(value = "file") MultipartFile file, Film film) {
        String fileName = ImageUpload.upload(file);
        film.setImage(fileName);
        film.setCreateTime(new Date());
        filmService.insert(film);
        return ResultUtil.build(SuccessCode.SUCCESS_CODE, SuccessCode.INSERT_SUCCESS, filmService.findByModel(new Film()));
    }

    @RequestMapping(value = "/update")
    public ResultUtil update(@RequestParam(value = "file") MultipartFile file, Film film) {
        String fileName = ImageUpload.upload(file);
        film.setImage(fileName);
        filmService.update(film);
        return ResultUtil.build(SuccessCode.SUCCESS_CODE, SuccessCode.UPDATE_SUCCESS, filmService.findByModel(new Film()));
    }

    @RequestMapping(value = "/delete")
    public ResultUtil delete(Integer id) {
        filmService.delete(id);
        return ResultUtil.build(SuccessCode.SUCCESS_CODE, SuccessCode.DEL_SUCCESS, filmService.findByModel(new Film()));
    }
}
