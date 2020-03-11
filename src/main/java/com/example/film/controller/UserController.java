package com.example.film.controller;

import com.example.film.dto.req.UserReq;
import com.example.film.entity.User;
import com.example.film.service.UserService;
import com.example.film.utils.ErrCode;
import com.example.film.utils.ResultUtil;
import com.example.film.utils.SuccessCode;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @author 陈乐
 * @version 1.0
 * @date 2020/3/11 10:35
 */
@RestController
@RequestMapping("/api/v1/film/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/login")
    public ResultUtil login(UserReq userReq) {
        User query = new User();
        query.setName(userReq.getName());
        query.setPassword(DigestUtils.md5DigestAsHex(userReq.getPassword().getBytes()));
        List<User> users = userService.findByModel(query);
        if (users.size() > 0) {
            return ResultUtil.build(SuccessCode.SUCCESS_CODE, SuccessCode.LOGIN_SUCCESS, users.get(0));
        } else {
            return ResultUtil.build(ErrCode.ERR_CODE, ErrCode.ERR_LOGIN, null);
        }

    }

    @RequestMapping(value = "/reg")
    public ResultUtil reg(UserReq userReq) {
        //先查询数据库有无相同账号
        User query = new User();
        query.setName(userReq.getName());
        List<User> queryUser = userService.findByModel(query);
        if (queryUser.size() > 0) {
            return ResultUtil.build(ErrCode.ERR_CODE, "用户名存在", null);
        } else {
            User userModel = new User();
            BeanUtils.copyProperties(userReq, userModel);
            userModel.setCreateTime(new Date());
            userModel.setPassword(DigestUtils.md5DigestAsHex(userReq.getPassword().getBytes()));
            Integer flag = userService.insert(userModel);
            if (flag > 0) {
                return ResultUtil.build(SuccessCode.SUCCESS_CODE, SuccessCode.REG_SUCCESS, null);
            } else {
                return ResultUtil.build(ErrCode.ERR_CODE, ErrCode.ERR_REG, null);
            }
        }
    }

    @RequestMapping(value = "/findAll")
    public ResultUtil findAllUser() {
        User query = new User();
        query.setRole(1);
        return ResultUtil.build(SuccessCode.SUCCESS_CODE, SuccessCode.UPDATE_SUCCESS, userService.findByModel(query));
    }


    @RequestMapping(value = "/update")
    public ResultUtil update(User user) {
        userService.update(user);
        User query = new User();
        query.setRole(1);
        return ResultUtil.build(SuccessCode.SUCCESS_CODE, SuccessCode.UPDATE_SUCCESS, userService.findByModel(query));
    }

    @RequestMapping(value = "/delete")
    public ResultUtil delete(Integer id) {
        userService.delete(id);
        User query = new User();
        query.setRole(1);
        return ResultUtil.build(SuccessCode.SUCCESS_CODE, SuccessCode.DEL_SUCCESS, userService.findByModel(query));
    }


}
