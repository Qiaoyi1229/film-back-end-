package com.example.film.doo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 陈乐
 * @version 1.0
 * @date 2020/3/11 22:02
 */
public class TimeTableDo {

    private Integer id;

    private Integer filmId;

    private Integer hallId;

    private Date startTime;

    private BigDecimal price;

    private String filmName;

    private String hallName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFilmId() {
        return filmId;
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }

    public Integer getHallId() {
        return hallId;
    }

    public void setHallId(Integer hallId) {
        this.hallId = hallId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }


    @Override
    public String toString() {
        return "TimeTableDo{" +
                "id=" + id +
                ", filmId=" + filmId +
                ", hallId=" + hallId +
                ", startTime=" + startTime +
                ", price=" + price +
                ", filmName='" + filmName + '\'' +
                ", hallName='" + hallName + '\'' +
                '}';
    }
}
