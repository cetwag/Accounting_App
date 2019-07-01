package com.example.accounting_app.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;

import com.example.accounting_app.R;
import com.example.accounting_app.adapter.adapter_activity_house_rate;
import com.example.accounting_app.fragment.fragment_house_rate_business;
import com.example.accounting_app.fragment.fragment_house_rate_combination;
import com.example.accounting_app.fragment.fragment_house_rate_find;
import com.example.accounting_app.fragment.fragment_house_rate_public_fund;
import com.example.accounting_app.listener.listener_activity_house_rate;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import java.util.ArrayList;
import java.util.List;

/**
 * @Creator cetwag yuebanquan
 * @Version V2.0.0
 * @Time 2019.6.28
 * @Description 房贷计算器类
 */
public class activity_house_rate extends AppCompatActivity {

    public ViewPager viewpager;
    public List<Fragment> pages = new ArrayList<>();
    adapter_activity_house_rate adapter;
    listener_activity_house_rate listener;
    public RadioButton rdb_business, rdb_public_fund, rdb_combination, rdb_find;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_rate);

        //屏幕适配
        ScreenAdapterTools.getInstance().reset(this);//如果希望android7.0分屏也适配的话,加上这句
        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        //控件初始化
        init();

        //适配器功能方法
        adapter.adapter_Activity_house_rate();

        //监听功能方法
        listener.listener_Activity_house_rate();
    }

    void init() {
        viewpager = findViewById(R.id.viewpager);
        adapter = new adapter_activity_house_rate(this);
        listener = new listener_activity_house_rate(this);
        //将房贷计算器的四个碎片类装入
        pages.add(new fragment_house_rate_business());
        pages.add(new fragment_house_rate_public_fund());
        pages.add(new fragment_house_rate_combination());
        pages.add(new fragment_house_rate_find());
        rdb_business = findViewById(R.id.rdb_business);
        rdb_public_fund = findViewById(R.id.rdb_public_fund);
        rdb_combination = findViewById(R.id.rdb_combination);
        rdb_find = findViewById(R.id.rdb_find);
    }
}
