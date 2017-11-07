package com.hello.service;

import com.hello.dao.LoginLogDao;
import com.hello.dao.UserDao;
import com.hello.entity.LoginLog;
import com.hello.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.List;

@ContextConfiguration(locations = {"classpath*:/hello-dao.xml"})
public class LoginServiceTest extends AbstractTransactionalTestNGSpringContextTests{

    private LoginService loginService;
    @Autowired
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }
    @Test
    public void loginCheckTest() {

        System.out.println(loginService.loginCheck(null, null));

    }

    @Test
    public void loginSuccessTest() {
        UserDao userDao = loginService.getUserDao();
        LoginLogDao loginLogDao = loginService.getLoginLogDao();

        User user = userDao.get(2);
        System.out.println(user.toString());
        List<LoginLog> list = loginLogDao.getLoginLogByUserId(2);
        System.out.println(list.size());

        user.setCredits(user.getCredits() + 5);
        user.setLastVisit(new Date());
        user.setLastIp("111.111.111.111");

        loginService.loginSuceess(user);

        user = userDao.get(2);
        System.out.println(user.toString());
        list = loginLogDao.loadAll();
        System.out.println(list.size());
        System.out.println(list.get(list.size() - 1).toString());


    }


}
