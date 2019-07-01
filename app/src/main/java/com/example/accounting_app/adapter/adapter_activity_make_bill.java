package com.example.accounting_app.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.accounting_app.activity.activity_make_bill;
import com.example.accounting_app.fragment.fragment_make_bill_pay;

/**
 * @Creator cetwag yuebanquan
 * @Version V2.0.0
 * @Time 2019.6.29
 * @Description 记一笔的适配类
 */
public class adapter_activity_make_bill {

    activity_make_bill activity_mb;

    /**
     * @parameter
     * @description 编写两个有参和无参构造函数，用于获取对应类的控件
     * @Time 2019/6/29 9:39
     */
    public adapter_activity_make_bill() {
    }

    public adapter_activity_make_bill(activity_make_bill fmbp) {
        activity_mb = fmbp;
    }

    /**
     * @parameter
     * @description 记一笔的适配类方法
     * @Time 2019/6/29 9:42
     */
    public void adapter_Activity_make_bill() {
        activity_mb.viewpager.setAdapter(new FragmentPagerAdapter(activity_mb.getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                //确定返回哪个碎片view
                return activity_mb.pages.get(i);
            }

            @Override
            public int getCount() {
                return activity_mb.pages.size();
            }
        });
    }
}
