package com.example.accounting_app.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.accounting_app.R;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

/**
 * @Creator cetwag yuebanquan
 * @Version V2.0.0
 * @Time 2019.7.5
 * @Description 关于我们的页面activity
 */
public class activity_home_about extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_about);

        //屏幕适配
        ScreenAdapterTools.getInstance().reset(this);//如果希望android7.0分屏也适配的话,加上这句
        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());
    }
}
