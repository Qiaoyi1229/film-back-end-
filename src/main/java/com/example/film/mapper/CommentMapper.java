package com.example.film.mapper;

import com.example.film.doo.CommentDo;
import com.example.film.entity.Comment;

import java.util.List;

public interface CommentMapper {

    List<CommentDo> findByModel(Comment comment);

    int deleteByPrimaryKey(Integer id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);
}