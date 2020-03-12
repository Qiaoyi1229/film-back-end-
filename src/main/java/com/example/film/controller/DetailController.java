package com.example.film.controller;

import com.example.film.doo.DetailDo;
import com.example.film.dto.resp.DetailResp;
import com.example.film.entity.Detail;
import com.example.film.service.DetailService;
import com.example.film.utils.ResultUtil;
import com.example.film.utils.SuccessCode;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 陈乐
 * @version 1.0
 * @date 2020/3/12 19:36
 */
@RestController
@RequestMapping("/api/v1/film/detail")
public class DetailController {

    @Autowired
    DetailService detailService;

    @RequestMapping(value = "/findByOrderId")
    public ResultUtil findByOrderId(Integer id) {

        List<DetailDo> detailDos = detailService.findByOrderId(id);
        List<DetailResp> detailResps = new ArrayList<>();
        for (DetailDo item : detailDos) {
            DetailResp detailResp = new DetailResp();
            BeanUtils.copyProperties(item, detailResp);
            detailResp.setSeatInfo(item.getRow() + "排" + item.getNum() + "座");
            detailResps.add(detailResp);
        }

        return ResultUtil.build(SuccessCode.SUCCESS_CODE, SuccessCode.FIND_SUCCESS, detailResps);
    }

    @RequestMapping(value = "/insert")
    public ResultUtil insert(Detail detail) {
        detailService.insert(detail);
        return ResultUtil.build(SuccessCode.SUCCESS_CODE, SuccessCode.INSERT_SUCCESS, null);
    }

    @RequestMapping(value = "/update")
    public ResultUtil update(Detail detail) {
        detailService.update(detail);
        return ResultUtil.build(SuccessCode.SUCCESS_CODE, SuccessCode.UPDATE_SUCCESS, null);
    }

    @RequestMapping(value = "/delete")
    public ResultUtil delete(Integer id) {
        detailService.delete(id);
        return ResultUtil.build(SuccessCode.SUCCESS_CODE, SuccessCode.DEL_SUCCESS, null);
    }
}
