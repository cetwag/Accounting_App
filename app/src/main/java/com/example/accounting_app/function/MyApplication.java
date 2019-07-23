package com.example.accounting_app.function;

import android.app.Application;
import android.content.res.Configuration;

import com.yatoooon.screenadaptation.ScreenAdapterTools;

import org.litepal.LitePal;

/**
 * @Creator cetwag, yuebanquan
 * @Version
 * @Time 2019.6.28  2019.7.11
 * @Description 作为第一个调用的Application
 *              内含screenadaptation（屏幕适配）
 *                  LitePal（数据库）
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //初始化屏幕适配控件
        ScreenAdapterTools.init(this);

        //初始化数据库控件
        LitePal.initialize(this);
    }

    //旋转适配,如果应用屏幕固定了某个方向不旋转的话(比如qq和微信),下面可不写.
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        ScreenAdapterTools.getInstance().reset(this);
    }
}
