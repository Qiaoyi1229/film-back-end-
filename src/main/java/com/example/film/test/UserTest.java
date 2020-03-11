package com.example.film.test;

import com.example.film.entity.User;
import com.example.film.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
}
