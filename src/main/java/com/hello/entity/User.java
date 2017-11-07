package com.hello.entity;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "t_user")
public class User extends BaseEntity {

    // 主键
    @Id
    // 键生成策略，自增
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 映射数据库中的列
    @Column(name = "user_id")
    private int userId;         // 用户ID

    @Column(name = "user_name")
    private String userName;    // 用户名，登录时使用此登录

    @Column(name = "password")
    private String password;    // 密码

    @Column(name = "credits")
    private int credits;        // 积分

    @Column(name = "last_ip")
    private String lastIp;      // 上次登录的IP

    @Column(name = "last_visit")
    private Date lastVisit;     // 上次登录的时间

    public User() {}

    public User(String userName, String password, int credits, String lastIp, Date lastVisit) {
        this.userName = userName;
        this.password = password;
        this.credits = credits;
        this.lastIp = lastIp;
        this.lastVisit = lastVisit;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getLastIp() {
        return lastIp;
    }

    public void setLastIp(String lastIp) {
        this.lastIp = lastIp;
    }

    public Date getLastVisit() {
        return lastVisit;
    }

    public void setLastVisit(Date lastVisit) {
        this.lastVisit = lastVisit;
    }
}
