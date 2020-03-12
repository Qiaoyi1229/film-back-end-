package com.example.film.controller;

import com.example.film.doo.DetailDo;
import com.example.film.dto.req.OrderReq;
import com.example.film.entity.Detail;
import com.example.film.entity.Order;
import com.example.film.entity.Seat;
import com.example.film.service.DetailService;
import com.example.film.service.OrderService;
import com.example.film.service.SeatService;
import com.example.film.utils.ResultUtil;
import com.example.film.utils.SuccessCode;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @author 陈乐
 * @version 1.0
 * @date 2020/3/12 19:07
 */
@RestController
@RequestMapping("/api/v1/film/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    DetailService detailService;

    @Autowired
    SeatService seatService;


    @RequestMapping(value = "/findByModel")
    public ResultUtil findByModel(Order order) {
        return ResultUtil.build(SuccessCode.SUCCESS_CODE, SuccessCode.FIND_SUCCESS, orderService.findByModel(order));
    }

    @RequestMapping(value = "/insert")
    public ResultUtil insert(OrderReq orderReq) {
        //先插入订单，返回主键
        //将座位插入表中，返回主键
        //将座位主键插入进订单详情表中

        //生成订单号；时间戳+场次+用户
        String orderNo = String.valueOf(new Date().getTime()) + orderReq.getTimeId() + orderReq.getUserId();
        Order order = new Order();
        BeanUtils.copyProperties(orderReq, order);
        order.setCreateTime(new Date());
        order.setOrderNo(orderNo);
        Integer orderId = orderService.insert(order);

        //分解座位信息 格式为1*1,1*2
        String[] seat1 = orderReq.getSeatsInfo().split(",");
        for (String item : seat1) {
            String[] seat2 = item.split("\\*");
            Seat seat = new Seat();
            seat.setTimeId(orderReq.getTimeId());
            seat.setRow(Integer.parseInt(seat2[0]));
            seat.setNum(Integer.parseInt(seat2[1]));
            int seatId = seatService.insert(seat);
            Detail detail = new Detail();
            detail.setOrderId(orderId);
            detail.setSeatId(seatId);
            detailService.insert(detail);
        }
        return ResultUtil.build(SuccessCode.SUCCESS_CODE, SuccessCode.INSERT_SUCCESS, null);
    }

    /**
     * 退票成功
     *
     * @param order
     * @return
     */
    @RequestMapping(value = "/refund")
    public ResultUtil refund(Order order) {
        //首相将订单状态修改为退款成功
        //根据订单id查询订单详情，得到具体的座位id
        //在根据id将座位表中的数据删除
        orderService.update(order);

        List<DetailDo> detailDos = detailService.findByOrderId(order.getId());
        for (DetailDo item : detailDos) {
            seatService.delete(item.getSeatId());
        }
        return ResultUtil.build(SuccessCode.SUCCESS_CODE, SuccessCode.UPDATE_SUCCESS, orderService.findByModel(new Order()));
    }

    @RequestMapping(value = "/update")
    public ResultUtil update(Order order) {
        orderService.update(order);
        return ResultUtil.build(SuccessCode.SUCCESS_CODE, SuccessCode.UPDATE_SUCCESS, orderService.findByModel(new Order()));
    }

    @RequestMapping(value = "/delete")
    public ResultUtil delete(Integer id) {
        orderService.delete(id);
        return ResultUtil.build(SuccessCode.SUCCESS_CODE, SuccessCode.DEL_SUCCESS, orderService.findByModel(new Order()));
    }
}
