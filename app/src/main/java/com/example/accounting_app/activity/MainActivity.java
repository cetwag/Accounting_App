package com.example.accounting_app.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;

import com.example.accounting_app.R;
import com.example.accounting_app.adapter.adapter_mainactivity;
import com.example.accounting_app.fragment.fragment_bill;
import com.example.accounting_app.fragment.fragment_home;
import com.example.accounting_app.fragment.fragment_statements;
import com.example.accounting_app.fragment.fragment_wish;
import com.example.accounting_app.listener.listener_mainactivity;

import java.util.ArrayList;


/**
 * @Creator cetwag, yuebanquan
 * @Version V2.0.0
 * @Time 2019.6.27
 * @Description 底部导航栏对碎片控制的切换
 */


public class MainActivity extends AppCompatActivity {

    //下面创建三个华滑动切换四个Fragment碎片必要的组件
    public ViewPager viewpager;
    public ArrayList<Fragment> pages = new ArrayList<>();

    //声明底部导航栏的五个选择按钮
    public RadioButton rdb_home;
    public RadioButton rdb_bill;
    public RadioButton rdb_wish;
    public RadioButton rdb_statements;
    public RadioButton rdb_add;

    //本类的监听器和适配器类对象
    listener_mainactivity listener;
    adapter_mainactivity adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();//找到声明的控件

        //该类对应的监听器
        listener.listener_MainActivity_vp();
        listener.listener_MainActivity_rdb();
        //该类对应的适配器
        adapter.adapter_MainActivity();

    }

    /**
     * @parameter
     * @description findViewById等控件的初始化
     * @Time 2019/6/27 14:27
     */
    public void init() {
        viewpager = findViewById(R.id.ViewPager);
        rdb_home = findViewById(R.id.rdb_home);
        rdb_bill = findViewById(R.id.rdb_bill);
        rdb_wish = findViewById(R.id.rdb_wish);
        rdb_statements = findViewById(R.id.rdb_statements);
        rdb_add = findViewById(R.id.rdb_add);
        pages.add(new fragment_home());
        pages.add(new fragment_bill());
        pages.add(new fragment_wish());
        pages.add(new fragment_statements());
        listener = new listener_mainactivity(this);
        adapter = new adapter_mainactivity(this);
    }

}
