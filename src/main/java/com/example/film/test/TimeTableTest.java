package com.example.film.test;

import com.example.film.doo.TimeTableDo;
import com.example.film.dto.req.TimeTableReq;
import com.example.film.entity.TimeTable;
import com.example.film.entity.User;
import com.example.film.service.TimeTableService;
import com.example.film.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.DigestUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author 陈乐
 * @version 1.0
 * @date 2020/3/11 9:18
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TimeTableTest {

    @Autowired
    TimeTableService timeTableService;

    @Test
    public void insert() {
        TimeTable timeTable = new TimeTable();
        timeTable.setFilmId(1);
        timeTable.setHallId(1);
        timeTable.setPrice(BigDecimal.valueOf(100.0));
        timeTable.setStartTime(new Date());
        System.out.println(timeTableService.insert(timeTable));
    }


    @Test
    public void findAll() {
        List<TimeTableDo> timeTableDos = timeTableService.findByModel(new TimeTableReq());
        for (TimeTableDo item : timeTableDos
        ) {
            System.out.println(item);
        }
    }
}
