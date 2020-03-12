package com.example.film.service.impl;

import com.example.film.doo.CommentDo;
import com.example.film.entity.Comment;
import com.example.film.mapper.CommentMapper;
import com.example.film.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 陈乐
 * @version 1.0
 * @date 2020/3/12 10:39
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentMapper commentMapper;

    @Override
    public List<CommentDo> findByModel(Comment comment) {
        return commentMapper.findByModel(comment);
    }

    @Override
    public Integer insert(Comment comment) {
        return commentMapper.insertSelective(comment);
    }

    @Override
    public Integer update(Comment comment) {
        return commentMapper.updateByPrimaryKeySelective(comment);
    }

    @Override
    public Integer delete(Integer id) {
        return commentMapper.deleteByPrimaryKey(id);
    }
}
