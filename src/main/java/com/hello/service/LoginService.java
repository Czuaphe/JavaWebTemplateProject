package com.hello.service;

import com.hello.dao.LoginLogDao;
import com.hello.dao.UserDao;
import com.hello.entity.LoginLog;
import com.hello.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private UserDao userDao;

    private LoginLogDao loginLogDao;
    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
    @Autowired
    public void setLoginLogDao(LoginLogDao loginLogDao) {
        this.loginLogDao = loginLogDao;
    }

    // 登录检查（不包括数据的格式检查）
    public boolean loginCheck(String userName, String MD5pswd) {

        if (userName == null || MD5pswd == null) {
            return false;
        }
        if (userName.equals("") || MD5pswd.equals("")) {
            return false;
        }
        User user = userDao.getUserByUserName(userName);

        return user != null && user.getPassword().equals(MD5pswd);
    }
    // 登录成功之后，进行的数据库操作

    /**
     *
     * @param user 更新登录IP和登录时间之后的用户
     */
    public void loginSuceess(User user) {
        //
        userDao.update(user);

        loginLogDao.save(new LoginLog(user.getUserId(), user.getLastIp(), user.getLastVisit()));

    }

    public UserDao getUserDao() {
        return userDao;
    }

    public LoginLogDao getLoginLogDao() {
        return loginLogDao;
    }
}
