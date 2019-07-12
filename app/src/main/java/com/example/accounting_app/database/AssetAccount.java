package com.example.accounting_app.database;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * @Creator yuebanquan
 * @Version V2.0.0
 * @Time 2019.7.11
 * @Description 数据库表——账户资产表
 */

public class AssetAccount extends LitePalSupport {
    @Column(nullable = false)
    private int assetAccountType;           //账户类别

    private String assetAccountBankName;    //银行名称

    private String assetAccountCardNum;       //账户卡号

    @Column(defaultValue = "0.00")
    private String AssetAccountMoney;       //账户金额

    //AssetAccount : User = n : 1
    private User user;

    //AssetAccount : Synchronize = 1 : 1
    private Synchronize synchronize;

    //AssetAccount : Tally = 1 : n
    private List<Tally> tallyList = new ArrayList<>();

    //AssetAccount : Curency = n : m
    private List<Currency> currencyList = new ArrayList<>();

    /**
     * @parameter
     * @description getter & setter
     * @Time 2019/7/12 15:36
     */
    public int getAssetAccountType() {
        return assetAccountType;
    }

    public void setAssetAccountType(int assetAccountType) {
        this.assetAccountType = assetAccountType;
    }

    public String getAssetAccountBankName() {
        return assetAccountBankName;
    }

    public void setAssetAccountBankName(String assetAccountBankName) {
        this.assetAccountBankName = assetAccountBankName;
    }

    public String getAssetAccountCardNum() {
        return assetAccountCardNum;
    }

    public void setAssetAccountCardNum(String assetAccountCardNum) {
        this.assetAccountCardNum = assetAccountCardNum;
    }

    public String getAssetAccountMoney() {
        return AssetAccountMoney;
    }

    public void setAssetAccountMoney(String assetAccountMoney) {
        AssetAccountMoney = assetAccountMoney;
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

    public List<Tally> getTallyList() {
        return tallyList;
    }

    public void setTallyList(List<Tally> tallyList) {
        this.tallyList = tallyList;
    }

    public List<Currency> getCurrencyList() {
        return currencyList;
    }

    public void setCurrencyList(List<Currency> currencyList) {
        this.currencyList = currencyList;
    }
}
