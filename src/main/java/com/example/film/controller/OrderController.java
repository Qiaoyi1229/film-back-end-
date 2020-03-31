package com.example.film.controller;

import com.example.film.doo.DetailDo;
import com.example.film.doo.FilmDo;
import com.example.film.doo.TimeTableDo;
import com.example.film.dto.req.FilmReq;
import com.example.film.dto.req.OrderReq;
import com.example.film.dto.req.TimeTableReq;
import com.example.film.dto.resp.MailResp;
import com.example.film.dto.resp.OrderResp;
import com.example.film.entity.Detail;
import com.example.film.entity.Order;
import com.example.film.entity.Seat;
import com.example.film.entity.User;
import com.example.film.service.*;
import com.example.film.utils.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author 陈乐
 * @version 1.0
 * @date 2020/3/12 19:07
 */
@RestController
@RequestMapping("/api/v1/film/order")
public class OrderController {

    @Autowired
    JavaMailSender mailSender;

    @Autowired
    OrderService orderService;

    @Autowired
    DetailService detailService;

    @Autowired
    SeatService seatService;

    @Autowired
    TimeTableService timeTableService;

    @Autowired
    FilmService filmService;

    @Autowired
    UserService userService;


    @RequestMapping(value = "/findByModel")
    public ResultUtil findByModel(Order order) {
        return ResultUtil.build(SuccessCode.SUCCESS_CODE, SuccessCode.FIND_SUCCESS, orderService.findByModel(order));
    }

    /**
     * 下单时确认该座位有没有被锁定
     *
     * @param orderReq
     * @return
     */
    @RequestMapping(value = "/checkSeat")
    public ResultUtil checkSeat(OrderReq orderReq) {
        String[] seat1 = orderReq.getSeatsInfo().split(",");
        List<Seat> seatList;
        for (String item : seat1) {
            String[] seat2 = item.split("\\*");
            Seat seat = new Seat();
            seat.setTimeId(orderReq.getTimeId());
            seat.setRow(Integer.parseInt(seat2[0]));
            seat.setNum(Integer.parseInt(seat2[1]));
            seatList = seatService.findByModel(seat);

            if (seatList.size() > 0) {
                return ResultUtil.build(ErrCode.ERR_CODE, "位置" + seat2[0] + "排" + seat2[1] + "座已锁定", null);
            }
        }

        return ResultUtil.build(SuccessCode.SUCCESS_CODE, "位置可以购买", null);
    }


    /**
     * 选择好座位和票的种类进入待付款状态
     *
     * @param orderReq
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/lockSeat")
    public ResultUtil lockSeat(OrderReq orderReq) {
        Order order = new Order();
        //生成订单号；时间戳+场次+用户
        String orderNo;
        if (orderReq.getUserId() != null) {
            orderNo = String.valueOf(new Date().getTime()) + orderReq.getTimeId() + orderReq.getUserId();
        } else {
            orderNo = String.valueOf(new Date().getTime()) + orderReq.getTimeId();
        }
        //取票码：订单号后八位
        String ticketCode = orderNo.substring(orderNo.length() - 8);

        BeanUtils.copyProperties(orderReq, order);
        order.setCreateTime(new Date());
        order.setOrderNo(orderNo);
        order.setTicketCode(ticketCode);
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

        return ResultUtil.build(SuccessCode.SUCCESS_CODE, "订单创建成功", orderId);
    }

    /**
     * 确认付款、付款方式以及修改状态
     *
     * @param orderReq
     * @return
     */
    @RequestMapping(value = "/paySuccess")
    public ResultUtil paySuccess(OrderReq orderReq) throws Exception {
        //更新订单
        Order orderModel = new Order();
        BeanUtils.copyProperties(orderReq, orderModel);
        orderModel.setStatus(1);
        orderService.update(orderModel);

        //生成PDF
        //查询该笔订单信息
        Order order = orderService.findById(orderReq.getId());

        //查找场次信息
        TimeTableReq timeTableReq = new TimeTableReq();
        timeTableReq.setId(order.getTimeId());
        List<TimeTableDo> timeTableDos = timeTableService.findByModel(timeTableReq);
        TimeTableDo timeTableDo = null;
        if (timeTableDos.size() > 0) {
            timeTableDo = timeTableDos.get(0);
        }

        //查找电影信息
        FilmReq filmQuery = new FilmReq();
        filmQuery.setId(timeTableDo.getFilmId());
        List<FilmDo> filmDos = filmService.findByModel(filmQuery);
        FilmDo filmDo = null;
        if (filmDos.size() > 0) {
            filmDo = filmDos.get(0);
        }

        //购票人数以及座位信息
        Detail detailQuery = new Detail();
        detailQuery.setOrderId(order.getId());
        List<DetailDo> details = detailService.findByOrderId(detailQuery);
        Integer peopleNum = details.size();
        StringBuilder ticketSeatInfo = new StringBuilder();
        for (DetailDo item : details) {
            String seatInfo = item.getRow() + "排" + item.getNum() + "座   ";
            ticketSeatInfo.append(seatInfo);
        }

        //生成PDF文件
        String pdfURL = "F:/" + order.getTicketCode() + ".pdf";
        PDFUtil.createPDF(pdfURL, order, timeTableDo, filmDo, peopleNum, ticketSeatInfo.toString());

        //取票码生成二维码
        String codeURL = "/image/" + order.getTicketCode() + ".png";
        QRCodeUtil.generateQRCode(order.getTicketCode(), 100, 100, "png", "D:\\IdeaWorkspace\\spring-framework-master\\film\\src\\main\\resources\\templates\\image\\" + order.getTicketCode() + ".png");

        //邮件发送pdf文件
        //判断该订单是用户还是直接邮箱登录、用户的话则发送给用户邮箱否则直接发送给登录邮箱
        if (order.getUserId() != null) {
            User user = userService.findById(order.getUserId());
            sendTicket(user.getMail(), pdfURL);
        } else {
            sendTicket(order.getMail(), pdfURL);
        }

        OrderResp orderResp = new OrderResp();
        orderResp.setOrder(order);
        orderResp.setTimeTableDo(timeTableDo);
        orderResp.setFilmDo(filmDo);
        orderResp.setTicketSeatInfo(ticketSeatInfo.toString());
        orderResp.setPdfURL(pdfURL);
        orderResp.setCodeURL(codeURL);

        return ResultUtil.build(SuccessCode.SUCCESS_CODE, "付款成功", orderResp);
    }

    @RequestMapping(value = "/insert")
    public ResultUtil insert(OrderReq orderReq) throws IOException {
        //先插入订单，返回主键
        //将座位插入表中，返回主键
        //将座位主键插入进订单详情表中

        Order order = new Order();

        //生成订单号；时间戳+场次+用户
        String orderNo;
        if (orderReq.getUserId() != null) {
            orderNo = String.valueOf(new Date().getTime()) + orderReq.getTimeId() + orderReq.getUserId();
        } else {
            orderNo = String.valueOf(new Date().getTime()) + orderReq.getTimeId();
        }
        //取票码：订单号后八位
        String ticketCode = orderNo.substring(orderNo.length() - 8);

        BeanUtils.copyProperties(orderReq, order);
        order.setCreateTime(new Date());
        order.setOrderNo(orderNo);
        order.setTicketCode(ticketCode);
        Integer orderId = orderService.insert(order);

        //分解座位信息 格式为1*1,1*2
        String[] seat1 = orderReq.getSeatsInfo().split(",");

        Integer peopleNum = seat1.length; //付款人数
        StringBuffer ticketSeatInfo = new StringBuffer();//座位信息

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

            ticketSeatInfo.append(seat2[0] + "排" + seat2[1] + "座     ");
        }

        //查找场次信息
        TimeTableReq timeTableReq = new TimeTableReq();
        timeTableReq.setId(orderReq.getTimeId());
        List<TimeTableDo> timeTableDos = timeTableService.findByModel(timeTableReq);
        TimeTableDo timeTableDo = null;
        if (timeTableDos.size() > 0) {
            timeTableDo = timeTableDos.get(0);
        }

        //查找电影信息
        FilmReq filmQuery = new FilmReq();
        filmQuery.setId(timeTableDo.getFilmId());
        List<FilmDo> filmDos = filmService.findByModel(filmQuery);
        FilmDo filmDo = null;
        if (filmDos.size() > 0) {
            filmDo = filmDos.get(0);
        }

        //生成PDF文件
        PDFUtil.createPDF("F:/ticket.pdf", order, timeTableDo, filmDo, peopleNum, ticketSeatInfo.toString());

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

        Detail query = new Detail();
        query.setOrderId(order.getId());
        List<DetailDo> detailDos = detailService.findByOrderId(query);
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

    @RequestMapping(value = "/preview", method = RequestMethod.GET)
    public void pdfStreamHandler(HttpServletRequest request, HttpServletResponse response, String pdfURL) {
        //PDF文件地址
        File file = new File(pdfURL);
        if (file.exists()) {
            byte[] data = null;
            FileInputStream input = null;
            try {
                input = new FileInputStream(file);
                data = new byte[input.available()];
                input.read(data);
                response.getOutputStream().write(data);
            } catch (Exception e) {
                System.out.println("pdf文件处理异常：" + e);
            } finally {
                try {
                    if (input != null) {
                        input.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 未登录用户使用邮箱验证登录
     */
    @RequestMapping(value = "/sendMail")
    public ResultUtil sendMail(String mail) {
        Random random = new Random();
        Integer code = random.nextInt(8999) + 1000;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("cl1429745331@163.com");
        message.setTo(mail);
        message.setSubject("登录验证信息");
        message.setText("欢迎登陆淘票票电影网，您本次的登录验证码为：" + code);
        try {
            mailSender.send(message);
            System.out.println("发送成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        MailResp mailResp = new MailResp();
        mailResp.setCode(code);
        mailResp.setMail(mail);
        return ResultUtil.build(SuccessCode.SUCCESS_CODE, "邮件发送成功", mailResp);
    }

    /**
     * 发送电影票
     */
    public void sendTicket(String mail, String pdfURL) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("cl1429745331@163.com");
            helper.setTo(mail);
            helper.setSubject("购票成功");
            helper.setText("购票成功，请查看附件。");
            FileSystemResource file = new FileSystemResource(pdfURL);
            helper.addAttachment("电影票.pdf", file);
            mailSender.send(message);
            System.out.println("电影票发送成功");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("电影票发送失败");
        }
    }
}
