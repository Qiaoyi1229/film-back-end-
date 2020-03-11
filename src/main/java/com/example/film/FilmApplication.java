package com.example.film;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// mapper 接口类扫描包配置
@MapperScan("com.example.film.mapper")
public class FilmApplication {

    public static void main(String[] args) {
        SpringApplication.run(FilmApplication.class, args);
    }

}
