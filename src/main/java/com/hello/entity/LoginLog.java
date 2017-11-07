package com.hello.entity;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "t_login_log")
public class LoginLog extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "login_log_id")
    private int loginLogId;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "ip")
    private String ip;

    @Column(name = "login_datetime")
    private Date loginDate;

    public LoginLog() {}

    public LoginLog(int userId, String ip, Date loginDate) {
        this.userId = userId;
        this.ip = ip;
        this.loginDate = loginDate;
    }

    public int getLoginLogId() {
        return loginLogId;
    }

    public void setLoginLogId(int loginLogId) {
        this.loginLogId = loginLogId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }
}
