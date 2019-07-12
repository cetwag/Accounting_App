package com.example.accounting_app.database;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * @Creator yuebanquan
 * @Version V2.0.0
 * @Time 2019.7.11
 * @Description 数据库表——币种表
 */
public class Currency extends LitePalSupport {
    @Column(nullable = false)
    private String currencyName;    //币种名称

    @Column(defaultValue = "0.00")
    private String currencyMoney;   //币种金额

    //Currency : AssetAccount = n : m
    private List<AssetAccount> assetAccountList = new ArrayList<>();

    //Currency : Tally = n : 1
    private List<Tally> tallyList = new ArrayList<>();

    /**
     * @parameter
     * @description getter & setter
     * @Time 2019/7/12 15:36
     */
    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getCurrencyMoney() {
        return currencyMoney;
    }

    public void setCurrencyMoney(String currencyMoney) {
        this.currencyMoney = currencyMoney;
    }

    public List<AssetAccount> getAssetAccountList() {
        return assetAccountList;
    }

    public void setAssetAccountList(List<AssetAccount> assetAccountList) {
        this.assetAccountList = assetAccountList;
    }

    public List<Tally> getTallyList() {
        return tallyList;
    }

    public void setTallyList(List<Tally> tallyList) {
        this.tallyList = tallyList;
    }

}
