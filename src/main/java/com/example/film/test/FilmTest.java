package com.example.film.test;

import com.example.film.doo.FilmDo;
import com.example.film.dto.req.FilmReq;
import com.example.film.entity.Film;
import com.example.film.entity.User;
import com.example.film.service.FilmService;
import com.example.film.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.DigestUtils;

import java.util.List;

/**
 * @author 陈乐
 * @version 1.0
 * @date 2020/3/11 9:18
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FilmTest {

    @Autowired
    FilmService filmService;

    @Test
    public void findAll() {
        List<FilmDo> filmDos = filmService.findByModel(new FilmReq());
        for (FilmDo item : filmDos
        ) {
            System.out.println(item.getName());
        }
    }
}
