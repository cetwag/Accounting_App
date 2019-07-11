package com.example.accounting_app.database;

import org.litepal.crud.LitePalSupport;

/**
 * @Creator yuebanquan
 * @Version V2.0.0
 * @Time 2019.7.11
 * @Description 数据库表——用户表
 *              客户端用户表不需要记录用户密码，
 *              只做用户数据的区分
 */

public class User extends LitePalSupport {
    String userName;    //用户名,还没登陆的时候为空

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
