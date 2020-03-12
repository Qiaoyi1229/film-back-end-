package com.example.film.service;

import com.example.film.doo.CommentDo;
import com.example.film.entity.Comment;

import java.util.List;

/**
 * @author 陈乐
 * @version 1.0
 * @date 2020/3/12 10:38
 */
public interface CommentService {

    List<CommentDo> findByModel(Comment comment);

    Integer insert(Comment comment);

    Integer update(Comment comment);

    Integer delete(Integer id);
}
