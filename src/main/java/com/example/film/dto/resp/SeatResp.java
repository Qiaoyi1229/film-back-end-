package com.example.film.dto.resp;

import java.util.List;

/**
 * @author 陈乐
 * @version 1.0
 * @date 2020/3/12 15:17
 */
public class SeatResp {


    private List<Integer> row;

    private List<Integer> column;

    public List<Integer> getRow() {
        return row;
    }

    public void setRow(List<Integer> row) {
        this.row = row;
    }

    public List<Integer> getColumn() {
        return column;
    }

    public void setColumn(List<Integer> column) {
        this.column = column;
    }
}
