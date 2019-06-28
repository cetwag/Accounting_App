package com.example.accounting_app.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;

import com.example.accounting_app.activity.MainActivity;


/**
 * @Creator cetwag, yuebanquan
 * @Version V2.0.0
 * @Time 2019.6.27
 * @Description MainActivity内控件的适配器相关
 */
public class adapter_mainactivity {

    MainActivity mainactivity;//声明对应类的对象,通过构造函数传参传入赋值，否则无法获取对应的控件
    String[] home_menu = new String[]{"消息中心", "个性皮肤", "同步", "汇率计算器", "房贷计算器",
            "帮助与反馈", "关于小账本", "清空缓存", "设置"};//侧滑菜单的内容

    /**
     * @parameter
     * @description 编写两个有参和无参构造函数，用于获取对应类的控件
     * @Time 2019/6/27 22:53
     */
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
        mainactivity.viewpager.setAdapter(new FragmentPagerAdapter(mainactivity.getSupportFragmentManager()) {
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
