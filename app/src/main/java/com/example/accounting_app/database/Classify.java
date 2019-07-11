package com.example.accounting_app.database;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

/**
 * @Creator yuebanquan
 * @Version V2.0.0
 * @Time 2019.7.11
 * @Description 数据库表——分类表
 */
public class Classify extends LitePalSupport {
    @Column(nullable = false)
    String classifyName;    //分类名称

    @Column(nullable = false)
    int classifyType;       //分类类别，0：支出；1：收入

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    public int getClassifyType() {
        return classifyType;
    }

    public void setClassifyType(int classifyType) {
        this.classifyType = classifyType;
    }
}
