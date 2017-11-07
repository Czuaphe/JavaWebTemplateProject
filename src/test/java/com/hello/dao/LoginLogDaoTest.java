package com.hello.dao;

import com.hello.entity.LoginLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.List;


@ContextConfiguration(locations = {"classpath*:/hello-dao.xml"})
public class LoginLogDaoTest extends AbstractTransactionalTestNGSpringContextTests {

    private LoginLogDao loginLogDao;
    private HibernateTransactionManager hibernateTransactionManager;
    @Autowired
    public void setLoginLogDao(LoginLogDao loginLogDao) {
        this.loginLogDao = loginLogDao;
    }
    @Autowired
    public void setHibernateTransactionManager(HibernateTransactionManager hibernateTransactionManager) {
        this.hibernateTransactionManager = hibernateTransactionManager;
    }

    @Test
    public void loadAllTest(){

        List<LoginLog> list = loginLogDao.loadAll();
        System.out.println(list.size());
        for (LoginLog loginLog : list) {
            System.out.println(loginLog.toString());
        }

    }

    // 当你使用是getSession中的save函数时，对数据库进行了改变，但是事务还没有提交，所以，数据在缓存中。
    // 这时，你在使用getSession中的其它函数，访问的就是缓存中的数据。
    // 而使用CriteriaQuery就不一样了，它只查询数据，所以，一直访问的是数据库中的数据，所以，导致两种查询结果有不同。
    @Test
    @Rollback(false)
    public void saveTest() {
        // 动态查询和静态查询不能一起用？
        getLoginLogByUserIdTest();

        LoginLog loginLog = new LoginLog();
        loginLog.setUserId(1);
        loginLog.setLoginDate(new Date());
        loginLog.setIp("192.168.1.11");
        loginLogDao.save(loginLog);

        getLoginLogByUserIdTest();

    }

    @AfterClass
    public void After() {
        getLoginLogByUserIdTest();
    }

    @Test
    public void saveTest2() {

        loadAllTest();

        LoginLog loginLog = new LoginLog();
        loginLog.setUserId(1);
        loginLog.setLoginDate(new Date());
        loginLog.setIp("192.168.1.11");
        loginLogDao.save(loginLog);

        loadAllTest();

    }


    @Test
    public void getLoginLogByUserIdTest() {

        List<LoginLog> list = loginLogDao.getLoginLogByUserId(1);
        System.out.println(list.size());
        for (LoginLog loginLog : list) {
            System.out.println(loginLog.toString());
        }

    }


}
