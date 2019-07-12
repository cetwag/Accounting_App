package com.example.accounting_app.database;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Creator yuebanquan
 * @Version V2.0.0
 * @Time 2019.7.11
 * @Description 数据库表——记账表
 */
public class Tally extends LitePalSupport {
    private String tallyComment;     //记账备注

    @Column(nullable = false, defaultValue = "0.00")
    private String tallyMoney;       //记账金额

    @Column(nullable = false)
    private Date tallyDate;         //记账日期

    //Tally : User = n : 1
    private User user;

    //Tally : Synchronize = 1 : 1
    private Synchronize synchronize;

    //Tally : AssetAccount = n : 1
    private AssetAccount assetAccount;

    //Tally : Classify = n : 1
    private Classify classify;

    //Tally : Currency = 1 : n
    private List<Currency> currencyList = new ArrayList<>();

    /**
     * @parameter
     * @description getter & setter
     * @Time 2019/7/12 15:36
     */
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

    public AssetAccount getAssetAccount() {
        return assetAccount;
    }

    public void setAssetAccount(AssetAccount assetAccount) {
        this.assetAccount = assetAccount;
    }

    public Classify getClassify() {
        return classify;
    }

    public void setClassify(Classify classify) {
        this.classify = classify;
    }

    public List<Currency> getCurrencyList() {
        return currencyList;
    }

    public void setCurrencyList(List<Currency> currencyList) {
        this.currencyList = currencyList;
    }
}
