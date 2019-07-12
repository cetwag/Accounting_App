package com.example.accounting_app.database;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

import java.util.ArrayList;
import java.util.Date;

/**
 * @Creator yuebanquan
 * @Version V2.0.0
 * @Time 2019.7.11
 * @Description 数据库表——同步表
 *              用于与服务器数据库同步用，同步逻辑待写。。。。。。
 */
public class Synchronize extends LitePalSupport {
    @Column(nullable = false)
    private int synchronizeStatus;      //同步状态：-1：删除；0：添加；1：修改；9：已同步；

    @Column(nullable = false)
    private Date synchronizeAnchor;     //同步时间戳

    //Synchronize : AssetAccount = 1 : 1
    private AssetAccount assetAccount;

    //Synchronize : Tally = 1 : 1
    private Tally tally;

    //Synchronize : Wish = 1 : 1
    private Wish wish;

    /**
     * @parameter
     * @description getter & setter
     * @Time 2019/7/12 15:36
     */
    public int getSynchronizeStatus() {
        return synchronizeStatus;
    }

    public void setSynchronizeStatus(int synchronizeStatus) {
        this.synchronizeStatus = synchronizeStatus;
    }

    public Date getSynchronizeAnchor() {
        return synchronizeAnchor;
    }

    public void setSynchronizeAnchor(Date synchronizeAnchor) {
        this.synchronizeAnchor = synchronizeAnchor;
    }

    public AssetAccount getAssetAccount() {
        return assetAccount;
    }

    public void setAssetAccount(AssetAccount assetAccount) {
        this.assetAccount = assetAccount;
    }

    public Tally getTally() {
        return tally;
    }

    public void setTally(Tally tally) {
        this.tally = tally;
    }

    public Wish getWish() {
        return wish;
    }

    public void setWish(Wish wish) {
        this.wish = wish;
    }
}
