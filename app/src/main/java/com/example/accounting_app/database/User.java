package com.example.accounting_app.database;

import org.litepal.crud.LitePalSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * @Creator yuebanquan
 * @Version V2.0.0
 * @Time 2019.7.11
 * @Description 数据库表——用户表
 *              客户端用户表不需要记录用户密码，
 *              只做用户数据的区分
 */

public class User extends LitePalSupport {
    private String userName;    //用户名,还没登陆的时候为空

    //User : AssetAccount = 1 : n
    private List<AssetAccount> assetAccountList = new ArrayList<AssetAccount>();

    //User : Tally = 1 : n
    private List<Tally> tallyList = new ArrayList<Tally>();

    //User : Wish = 1 : n
    private List<Wish> wishList = new ArrayList<Wish>();

    /**
     * @parameter
     * @description getter & setter
     * @Time 2019/7/12 15:36
     */
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public List<Wish> getWishList() {
        return wishList;
    }

    public void setWishList(List<Wish> wishList) {
        this.wishList = wishList;
    }
}
