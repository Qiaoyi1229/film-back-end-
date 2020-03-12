package com.example.film.controller;

import com.example.film.entity.Comment;
import com.example.film.service.CommentService;
import com.example.film.utils.ResultUtil;
import com.example.film.utils.SuccessCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author 陈乐
 * @version 1.0
 * @date 2020/3/12 10:40
 */
@RestController
@RequestMapping("/api/v1/film/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @RequestMapping(value = "/findByModel")
    public ResultUtil findByModel(Comment comment) {
        return ResultUtil.build(SuccessCode.SUCCESS_CODE, SuccessCode.FIND_SUCCESS, commentService.findByModel(comment));
    }

    @RequestMapping(value = "/insert")
    public ResultUtil insert(Comment comment) {
        comment.setCreateTime(new Date());
        commentService.insert(comment);
        return ResultUtil.build(SuccessCode.SUCCESS_CODE, SuccessCode.INSERT_SUCCESS, commentService.findByModel(new Comment()));
    }

    @RequestMapping(value = "/update")
    public ResultUtil update(Comment comment) {
        commentService.update(comment);
        return ResultUtil.build(SuccessCode.SUCCESS_CODE, SuccessCode.UPDATE_SUCCESS, commentService.findByModel(new Comment()));
    }

    @RequestMapping(value = "/delete")
    public ResultUtil delete(Integer id) {
        commentService.delete(id);
        return ResultUtil.build(SuccessCode.SUCCESS_CODE, SuccessCode.DEL_SUCCESS, commentService.findByModel(new Comment()));
    }
}
