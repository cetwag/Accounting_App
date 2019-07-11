package com.example.accounting_app.database;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

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
    int synchronizeStatus;      //同步状态：-1：删除；0：添加；1：修改；9：已同步；

    @Column(nullable = false)
    Date synchronizeAnchor;     //同步时间戳

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
}
