package com.example.accounting_app.database;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

/**
 * @Creator yuebanquan
 * @Version V2.0.0
 * @Time 2019.7.11
 * @Description 数据库表——币种表
 */
public class Currency extends LitePalSupport {
    @Column(nullable = false)
    String currencyName;    //币种名称

    @Column(defaultValue = "0.00")
    String currencyMoney;   //币种金额
}
