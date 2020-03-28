package com.example.film.controller;

import com.example.film.dto.req.FilmReq;
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
    public ResultUtil findByModel(FilmReq film) {
        return ResultUtil.build(SuccessCode.SUCCESS_CODE, SuccessCode.FIND_SUCCESS, filmService.findByModel(film));
    }

    /**
     * 电影票房榜单
     *
     * @return
     */
    @RequestMapping(value = "/priceList")
    public ResultUtil priceList() {
        return ResultUtil.build(SuccessCode.SUCCESS_CODE, SuccessCode.FIND_SUCCESS, filmService.priceList());
    }

    /**
     * 根据电影院查找当前正在播放的电影
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/findByCinemaId")
    public ResultUtil findByCinemaId(Integer id) {
        return ResultUtil.build(SuccessCode.SUCCESS_CODE, SuccessCode.FIND_SUCCESS, filmService.findByCinemaId(id));
    }

    @RequestMapping(value = "/insert")
    public ResultUtil insert(@RequestParam(value = "file") MultipartFile file, Film film) {
        String fileName = ImageUpload.upload(file);
        film.setImage(fileName);
        film.setCreateTime(new Date());
        filmService.insert(film);
        return ResultUtil.build(SuccessCode.SUCCESS_CODE, SuccessCode.INSERT_SUCCESS, filmService.findByModel(new FilmReq()));
    }

    @RequestMapping(value = "/update")
    public ResultUtil update(@RequestParam(value = "file") MultipartFile file, Film film) {
        if (!file.isEmpty()) {
            String fileName = ImageUpload.upload(file);
            film.setImage(fileName);
        }
        filmService.update(film);
        return ResultUtil.build(SuccessCode.SUCCESS_CODE, SuccessCode.UPDATE_SUCCESS, filmService.findByModel(new FilmReq()));
    }

    @RequestMapping(value = "/updateShelves")
    public ResultUtil updateShelves(Film film) {
        filmService.update(film);
        return ResultUtil.build(SuccessCode.SUCCESS_CODE, SuccessCode.UPDATE_SUCCESS, filmService.findByModel(new FilmReq()));
    }

    @RequestMapping(value = "/delete")
    public ResultUtil delete(Integer id) {
        filmService.delete(id);
        return ResultUtil.build(SuccessCode.SUCCESS_CODE, SuccessCode.DEL_SUCCESS, filmService.findByModel(new FilmReq()));
    }
}
