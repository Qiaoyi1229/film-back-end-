package com.example.film.controller;

import com.example.film.entity.Cinema;
import com.example.film.entity.User;
import com.example.film.service.CinemaService;
import com.example.film.utils.ImageUpload;
import com.example.film.utils.ResultUtil;
import com.example.film.utils.SuccessCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 陈乐
 * @version 1.0
 * @date 2020/3/27 16:07
 */
@RestController
@RequestMapping("/api/v1/film/cinema")
public class CinemaController {


    @Autowired
    CinemaService cinemaService;

    /**
     * 根据电影id，查询当前正在上映带电影的影院
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/findByFilmId")
    public ResultUtil findByFilmId(Integer id) {
        return ResultUtil.build(SuccessCode.SUCCESS_CODE, SuccessCode.FIND_SUCCESS, cinemaService.findByFilmId(id));
    }

    @RequestMapping(value = "/findAll")
    public ResultUtil findAll() {
        return ResultUtil.build(SuccessCode.SUCCESS_CODE, SuccessCode.FIND_SUCCESS, cinemaService.findAll());
    }

    @RequestMapping(value = "/insert")
    public ResultUtil insert(@RequestParam(value = "file") MultipartFile file, Cinema cinema) {
        String image = ImageUpload.upload(file);
        cinema.setImage(image);
        cinemaService.insert(cinema);
        return ResultUtil.build(SuccessCode.SUCCESS_CODE, SuccessCode.INSERT_SUCCESS, cinemaService.findAll());
    }

    @RequestMapping(value = "/update")
    public ResultUtil update(Cinema cinema) {
        cinemaService.update(cinema);
        return ResultUtil.build(SuccessCode.SUCCESS_CODE, SuccessCode.UPDATE_SUCCESS, cinemaService.findAll());
    }

    @RequestMapping(value = "/updateImageHead")
    public ResultUtil updateImageHead(@RequestParam(value = "file") MultipartFile file, Cinema cinema) {
        String image = ImageUpload.upload(file);
        cinema.setImage(image);
        cinemaService.update(cinema);
        return ResultUtil.build(SuccessCode.SUCCESS_CODE, SuccessCode.UPDATE_SUCCESS, cinemaService.findAll());
    }

    @RequestMapping(value = "/delete")
    public ResultUtil delete(Integer id) {
        cinemaService.delete(id);
        return ResultUtil.build(SuccessCode.SUCCESS_CODE, SuccessCode.DEL_SUCCESS, cinemaService.findAll());
    }

}
