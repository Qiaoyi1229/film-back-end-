package com.example.film.dto.req;

import java.util.Date;

/**
 * @author 陈乐
 * @version 1.0
 * @date 2020/3/12 19:41
 */
public class OrderReq {

    private Integer id;

    private String orderNo;

    private Integer userId;

    private Integer timeId;

    private String total;

    private Integer status;

    private Date createTime;

    private String seatsInfo;

    private String mail;

    private String payType;

    private String cardNo;

    private String orderType;

    private Integer vipSeat;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTimeId() {
        return timeId;
    }

    public void setTimeId(Integer timeId) {
        this.timeId = timeId;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSeatsInfo() {
        return seatsInfo;
    }

    public void setSeatsInfo(String seatsInfo) {
        this.seatsInfo = seatsInfo;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public Integer getVipSeat() {
        return vipSeat;
    }

    public void setVipSeat(Integer vipSeat) {
        this.vipSeat = vipSeat;
    }
}
