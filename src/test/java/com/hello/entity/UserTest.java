package com.hello.entity;


import com.hello.dao.UserDao;
import org.junit.Test;

import java.util.List;

public class UserTest {

    @Test
    public void entityTest() {

        User user = new User();

        user.setUserId(1);
        user.setUserName("Tom");
        user.setPassword("123123");

        System.out.println(user.toString());




    }

}
