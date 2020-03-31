package com.example.film.dto.resp;

import com.example.film.doo.FilmDo;
import com.example.film.doo.TimeTableDo;
import com.example.film.entity.Order;

/**
 * @author 陈乐
 * @version 1.0
 * @date 2020/3/31 9:35
 */
public class OrderResp {

    private Order order;

    private TimeTableDo timeTableDo;

    private FilmDo filmDo;

    private String ticketSeatInfo;

    private String pdfURL;

    private String codeURL;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public TimeTableDo getTimeTableDo() {
        return timeTableDo;
    }

    public void setTimeTableDo(TimeTableDo timeTableDo) {
        this.timeTableDo = timeTableDo;
    }

    public FilmDo getFilmDo() {
        return filmDo;
    }

    public void setFilmDo(FilmDo filmDo) {
        this.filmDo = filmDo;
    }

    public String getTicketSeatInfo() {
        return ticketSeatInfo;
    }

    public void setTicketSeatInfo(String ticketSeatInfo) {
        this.ticketSeatInfo = ticketSeatInfo;
    }

    public String getPdfURL() {
        return pdfURL;
    }

    public void setPdfURL(String pdfURL) {
        this.pdfURL = pdfURL;
    }

    public String getCodeURL() {
        return codeURL;
    }

    public void setCodeURL(String codeURL) {
        this.codeURL = codeURL;
    }
}
