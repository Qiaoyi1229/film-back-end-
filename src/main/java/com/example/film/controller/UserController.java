package com.example.film.controller;

import com.example.film.dto.req.UserReq;
import com.example.film.entity.User;
import com.example.film.service.UserService;
import com.example.film.utils.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
    public ResultUtil login(UserReq userReq, HttpSession session) {
        User query = new User();
        query.setName(userReq.getName());
        query.setPassword(DigestUtils.md5DigestAsHex(userReq.getPassword().getBytes()));
        query.setRole(userReq.getRole());
        List<User> users = userService.findByModel(query);
        if (users.size() > 0) {
            return ResultUtil.build(SuccessCode.SUCCESS_CODE, SuccessCode.LOGIN_SUCCESS, users.get(0));
        } else {
            return ResultUtil.build(ErrCode.ERR_CODE, ErrCode.ERR_LOGIN, null);
        }
    }

    /**
     * 生成验证码
     */
    @RequestMapping(value = "/getVerify")
    public void getVerify(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
            response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);
            RandomValidateCodeUtil randomValidateCode = new RandomValidateCodeUtil();
            randomValidateCode.getRandcode(request, response);//输出验证码图片方法
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/reg")
    public ResultUtil reg(@RequestParam(value = "file") MultipartFile file, UserReq userReq) {
        //先查询数据库有无相同账号
        User query = new User();
        query.setName(userReq.getName());
        List<User> queryUser = userService.findByModel(query);
        if (queryUser.size() > 0) {
            return ResultUtil.build(ErrCode.ERR_CODE, "用户名存在", null);
        } else {
            User userModel = new User();
            BeanUtils.copyProperties(userReq, userModel);
            String headImage = ImageUpload.upload(file);
            userModel.setHeadIamge(headImage);
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

    @RequestMapping(value = "/regAndroid")
    public ResultUtil regAndroid(UserReq userReq) {
        User query = new User();
        query.setName(userReq.getName());
        List<User> queryUser = userService.findByModel(query);
        if (queryUser.size() > 0) {
            return ResultUtil.build(ErrCode.ERR_CODE, "用户名存在", null);
        } else {
            User userModel = new User();
            BeanUtils.copyProperties(userReq, userModel);
            String headImage = "/image/user.png";
            userModel.setHeadIamge(headImage);
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

    @RequestMapping(value = "/findByModel")
    public ResultUtil findByModel(User user) {
        return ResultUtil.build(SuccessCode.SUCCESS_CODE, SuccessCode.UPDATE_SUCCESS, userService.findByModel(user));
    }


    @RequestMapping(value = "/update")
    //public ResultUtil update(@RequestParam(value = "file") MultipartFile file, User user) {
    public ResultUtil update(User user) {
        /*if (!file.isEmpty()) {
            String headImage = ImageUpload.upload(file);
            user.setHeadIamge(headImage);
        }*/
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
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
