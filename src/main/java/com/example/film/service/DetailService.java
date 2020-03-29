package com.example.film.service;

import com.example.film.doo.DetailDo;
import com.example.film.entity.Detail;

import java.util.List;

/**
 * @author 陈乐
 * @version 1.0
 * @date 2020/3/12 19:32
 */
public interface DetailService {

    List<DetailDo> findByOrderId(Detail detail);

    Integer insert(Detail detail);

    Integer update(Detail detail);

    Integer delete(Integer id);
}
