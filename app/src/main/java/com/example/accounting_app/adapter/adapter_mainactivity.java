package com.example.accounting_app.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.accounting_app.activity.MainActivity;


/**
 * @Creator cetwag, yuebanquan
 * @Version V2.0.0
 * @Time 2019.6.27
 * @Description MainActivity内控件的适配器
 */
public class adapter_mainactivity extends AppCompatActivity {

    MainActivity mainactivity;


    public adapter_mainactivity() {
    }

    public adapter_mainactivity(MainActivity m) {
        mainactivity = m;
    }


    /**
     * @parameter
     * @description 底部导航栏滑动切换的适配器
     * @Time 2019/6/27 15:05
     */
    public void adapter_MainActivity() {
        mainactivity.viewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                //确定返回哪个页面
                return mainactivity.pages.get(i);
            }

            @Override
            public int getCount() {
                //返回碎片数
                return mainactivity.pages.size();
            }
        });
    }

}
