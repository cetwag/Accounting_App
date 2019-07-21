package com.example.accounting_app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.RadioButton;

import com.example.accounting_app.R;
import com.example.accounting_app.adapter.adapter_activity_make_bill;
import com.example.accounting_app.fragment.fragment_make_bill_income;
import com.example.accounting_app.fragment.fragment_make_bill_pay;
import com.example.accounting_app.listener.listener_activity_make_bill;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import java.util.ArrayList;
import java.util.List;

/**
 * @Creator cetwag yuebanquan
 * @Version V2.0.0
 * @Time 2019.6.28
 * @Description 记一笔activity
 */
public class activity_make_bill extends AppCompatActivity {

    public ViewPager viewpager;
    public List<Fragment> pages = new ArrayList<>();
    adapter_activity_make_bill adapter;
    listener_activity_make_bill listener;
    public RadioButton rbd_pay, rbd_income;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_bill);

        //屏幕适配
        ScreenAdapterTools.getInstance().reset(this);//如果希望android7.0分屏也适配的话,加上这句
        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        //初始化控件
        init();

        //适配器类方法
        adapter.adapter_Activity_make_bill();

        //监听类方法
        listener.listener_activity_mb();

    }

    /**
     * @parameter
     * @description view初始化控件
     * @Time 2019/6/29 9:34
     */
    void init() {
        viewpager = findViewById(R.id.viewpager);
        rbd_pay = findViewById(R.id.rdb_pay);
        rbd_income = findViewById(R.id.rdb_income);
        pages.add(new fragment_make_bill_pay());//加入记一笔的支出碎片
        pages.add(new fragment_make_bill_income());//加入记一笔的收入碎片
        adapter = new adapter_activity_make_bill(this);
        listener = new listener_activity_make_bill(this);
    }

}
