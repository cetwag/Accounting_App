package com.example.accounting_app.database;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;
/**
 * @Creator yuebanquan
 * @Version V2.0.0
 * @Time 2019.7.11
 * @Description 数据库表——账户资产表
 */

public class AssetAccount extends LitePalSupport {
    @Column(nullable = false)
    int assetAccountType;           //账户类别

    String assetAccountBankName;    //银行名称

    long assetAccountCardNum;       //账户卡号

    @Column(defaultValue = "0.00")
    String AssetAccountMoney;       //账户金额

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

    public long getAssetAccountCardNum() {
        return assetAccountCardNum;
    }

    public void setAssetAccountCardNum(long assetAccountCardNum) {
        this.assetAccountCardNum = assetAccountCardNum;
    }

    public String getAssetAccountMoney() {
        return AssetAccountMoney;
    }

    public void setAssetAccountMoney(String assetAccountMoney) {
        AssetAccountMoney = assetAccountMoney;
    }
}
