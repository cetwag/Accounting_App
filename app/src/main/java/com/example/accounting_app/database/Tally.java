package com.example.accounting_app.database;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

import java.util.Date;

/**
 * @Creator yuebanquan
 * @Version V2.0.0
 * @Time 2019.7.11
 * @Description 数据库表——记账表
 */
public class Tally extends LitePalSupport {
    String tallyComment;     //记账备注

    @Column(nullable = false, defaultValue = "0.00")
    String tallyMoney;       //记账金额

    @Column(nullable = false)
    Date tallyDate;         //记账日期

    public String getTallyComment() {
        return tallyComment;
    }

    public void setTallyComment(String tallyComment) {
        this.tallyComment = tallyComment;
    }

    public String getTallyMoney() {
        return tallyMoney;
    }

    public void setTallyMoney(String tallyMoney) {
        this.tallyMoney = tallyMoney;
    }

    public Date getTallyDate() {
        return tallyDate;
    }

    public void setTallyDate(Date tallyDate) {
        this.tallyDate = tallyDate;
    }
}
