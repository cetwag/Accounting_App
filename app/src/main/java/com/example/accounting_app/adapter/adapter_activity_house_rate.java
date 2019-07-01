package com.example.accounting_app.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.accounting_app.activity.activity_house_rate;

/**
 * @Creator cetwag yuebanquan
 * @Version V2.0.0
 * @Time 2019.6.29
 * @Description 房贷计算器的适配类
 */
public class adapter_activity_house_rate {

    activity_house_rate activity_hr;//对应类的对象，用于获取控件

    /**
     * @parameter
     * @description 编写两个有参和无参函数，用于获取到正确的对象和控件
     * @Time 2019/6/29 22:12
     */
    public adapter_activity_house_rate() {
    }

    public adapter_activity_house_rate(activity_house_rate ahr) {
        activity_hr = ahr;
    }

    /**
     * @parameter
     * @description 适配器具体功能方法
     * @Time 2019/6/29 22:14
     */
    public void adapter_Activity_house_rate() {
        activity_hr.viewpager.setAdapter(new FragmentPagerAdapter(activity_hr.getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return activity_hr.pages.get(i);
            }

            @Override
            public int getCount() {
                return activity_hr.pages.size();
            }
        });
    }

}
