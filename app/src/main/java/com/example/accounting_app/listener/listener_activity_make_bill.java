package com.example.accounting_app.listener;

import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.accounting_app.R;
import com.example.accounting_app.activity.activity_make_bill;


/**
 * @Creator cetwag yuebanquan
 * @Version v2.0.0
 * @Time 2019.6.29
 * @Description 记一笔的监听类
 */
public class listener_activity_make_bill implements View.OnClickListener {

    activity_make_bill activity_mb;

    /**
     * @parameter
     * @description 编写两个有参和无参构造函数用来获取对应类的控件
     * @Time 2019/6/29 10:07
     */
    public listener_activity_make_bill() {
    }

    public listener_activity_make_bill(activity_make_bill amb) {
        activity_mb = amb;
    }

    /**
     * @parameter
     * @description 监听具体方法
     * @Time 2019/6/29 10:23
     */
    public void listener_activity_mb() {
        activity_mb.rbd_pay.setOnClickListener(this);
        activity_mb.rbd_income.setOnClickListener(this);

        activity_mb.viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i) {
                    case 0:
                        activity_mb.rbd_pay.setChecked(true);
                        break;
                    case 1:
                        activity_mb.rbd_income.setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    /**
     * @parameter
     * @description 实现view.OnClickListener的具体监听方法
     * @Time 2019/6/29 10:46
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rdb_pay:
                activity_mb.viewpager.setCurrentItem(0, true);
                break;
            case R.id.rdb_income:
                activity_mb.viewpager.setCurrentItem(1, true);
                break;
        }
    }
}
