package com.example.accounting_app.listener;


import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.accounting_app.R;
import com.example.accounting_app.activity.activity_house_rate;

/**
 * @Creator cetwag yuebanquan
 * @Version V2.0.0
 * @Time 2019.6.29
 * @Description 房贷计算器的监听
 */
public class listener_activity_house_rate implements View.OnClickListener {

    activity_house_rate activity_hr;

    /**
     * @parameter
     * @description 编写两个有参和无参构造函数，用于正确获取对应类的控件
     * @Time 2019/6/29 23:00
     */
    public listener_activity_house_rate() {
    }

    public listener_activity_house_rate(activity_house_rate ahr) {
        activity_hr = ahr;
    }

    public void listener_Activity_house_rate() {
        activity_hr.rdb_business.setOnClickListener(this);
        activity_hr.rdb_public_fund.setOnClickListener(this);
        activity_hr.rdb_combination.setOnClickListener(this);
        activity_hr.rdb_find.setOnClickListener(this);
        activity_hr.viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                //当页面滚动时候触发
            }

            @Override
            public void onPageSelected(int i) {
                //当滑动完成
                switch (i) {
                    case 0:
                        activity_hr.rdb_business.setChecked(true);
                        break;
                    case 1:
                        activity_hr.rdb_public_fund.setChecked(true);
                        break;
                    case 2:
                        activity_hr.rdb_combination.setChecked(true);
                        break;
                    case 3:
                        activity_hr.rdb_find.setChecked(true);
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
     * @description 实现View.OnClickListener接口的监听函数
     * @Time 2019/6/29 22:17
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rdb_business:
                activity_hr.viewpager.setCurrentItem(0, true);
                break;
            case R.id.rdb_public_fund:
                activity_hr.viewpager.setCurrentItem(1, true);
                break;
            case R.id.rdb_combination:
                activity_hr.viewpager.setCurrentItem(2, true);
                break;
            case R.id.rdb_find:
                activity_hr.viewpager.setCurrentItem(3, true);
                break;
        }
    }

}
