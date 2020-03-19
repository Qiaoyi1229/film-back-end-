package com.example.film.dto.resp;

import java.util.List;

/**
 * @author 陈乐
 * @version 1.0
 * @date 2020/3/13 0:02
 */
public class TypeStatistical {
    private List total;

    private List typeName;

    public List getTotal() {
        return total;
    }

    public void setTotal(List total) {
        this.total = total;
    }

    public List getTypeName() {
        return typeName;
    }

    public void setTypeName(List typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return "TypeStatistical{" +
                "total=" + total +
                ", typeName=" + typeName +
                '}';
    }
}
