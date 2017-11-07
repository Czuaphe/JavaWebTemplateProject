package com.hello.dao;

import com.hello.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;
import static org.testng.Assert.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Scanner;

@ContextConfiguration(locations = {"classpath*:/hello-dao.xml"})
public class UserDaoTest extends AbstractTransactionalTestNGSpringContextTests {

    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Test
    public void loadTest() {
        User user = userDao.load(2);
        System.out.println(user.toString());
    }

    @Test
    public void loadAllTest() {

        List<User> list = userDao.loadAll();
        System.out.println(list.size());
        for (User user : list) {
            System.out.println(user.toString());
        }

    }

    @Test
    public void getTest() {
        User user = userDao.get(2);
        System.out.println(user.toString());
    }

    @Test
    public void updateTest() {
        User user = userDao.get(1);
        System.out.println(user.toString());
        user.setCredits(30);
        userDao.update(user);
//        Scanner input = new Scanner(System.in);
//        int num = input.nextInt();
        user = userDao.get(1);
        System.out.println(user.toString());
    }

    @Test
    @Rollback(false)
    public void saveTest() {
        loadAllTest();

        User user = new User();
        user.setUserName("jack");
        user.setPassword("123456");
        userDao.save(user);

        loadAllTest();

    }

    @Test
//    @Rollback(value = false)
    public void removeTest() {
        User user = new User();
        user.setUserId(6);
        user.setUserName("Tom");
        user.setCredits(0);
        user.setPassword("123456");
        userDao.remove(user);

        loadAllTest();

    }

    @Test
    public void removeAllTest() {
        loadAllTest();

        userDao.removeAll("t_user");

        loadAllTest();
    }


    @Test
    public void getUserByUserNameTest() {
        User user = userDao.getUserByUserName("cui");
        System.out.println(user == null ? "NULL" : user.toString());
    }

    @Test
    public void queryUserByUserNameTest() {
        List<User> list = userDao.queryUserByUserName("ad");

        for (User user : list) {
            System.out.println(user.toString());
        }

    }


}
