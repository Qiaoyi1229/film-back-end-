package com.example.film.test;

import com.example.film.entity.User;
import com.example.film.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.DigestUtils;

import java.util.List;

/**
 * @author 陈乐
 * @version 1.0
 * @date 2020/3/11 9:18
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    @Autowired
    UserService userService;

    @Test
    public void insert() {
        User user = new User();
        user.setName("cc");
        user.setPassword("123456");
        user.setAge(20);
        user.setPhone("138888888888");
        user.setMail("123456789@qq.com");
        System.out.println(userService.insert(user));
    }

    @Test
    public void login() {
        User query = new User();
        query.setName("cc");
        query.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        query.setRole(1);
        List<User> users = userService.findByModel(query);
        if (users.size() > 0) {
            System.out.println(users.get(0).toString());
        } else {
            System.out.println("无该用户");
        }
    }

    @Test
    public void findAll() {
        List<User> users = userService.findByModel(new User());
        for (User item : users
        ) {
            System.out.println(item.getName());
        }
    }
}
