package com.example.accounting_app.database;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

import java.util.Date;
import java.util.List;

/**
 * @Creator yuebanquan
 * @Version V2.0.0
 * @Time 2019.7.11
 * @Description 数据库表——心愿表
 */
public class Wish extends LitePalSupport {
    @Column(nullable = false)
    private String wishName;    //心愿名称

    @Column(nullable = false, defaultValue = "0.00")
    private String wishMoney;   //心愿金额

    @Column(nullable = false)
    private Date wishDate;      //心愿日期

    @Column(nullable = false)
    private int wishNeedMonth;  //心愿需要月份

    private String wishLocal;   //心愿添加地点

    //Wish : User = n : 1
    private User user;

    //Wish : Synchronize = 1 : 1
    private Synchronize synchronize;

    /**
     * @parameter
     * @description getter & setter
     * @Time 2019/7/12 15:36
     */
    public String getWishName() {
        return wishName;
    }

    public void setWishName(String wishName) {
        this.wishName = wishName;
    }

    public String getWishMoney() {
        return wishMoney;
    }

    public void setWishMoney(String wishMoney) {
        this.wishMoney = wishMoney;
    }

    public Date getWishDate() {
        return wishDate;
    }

    public void setWishDate(Date wishDate) {
        this.wishDate = wishDate;
    }

    public int getWishNeedMonth() {
        return wishNeedMonth;
    }

    public void setWishNeedMonth(int wishNeedMonth) {
        this.wishNeedMonth = wishNeedMonth;
    }

    public String getWishLocal() {
        return wishLocal;
    }

    public void setWishLocal(String wishLocal) {
        this.wishLocal = wishLocal;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Synchronize getSynchronize() {
        return synchronize;
    }

    public void setSynchronize(Synchronize synchronize) {
        this.synchronize = synchronize;
    }
}
