package com.example.accounting_app.listener;

import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.accounting_app.activity.MainActivity;

/**
 * @Creator cetwag, yuebanquan
 * @Version V2.0.0
 * @Time 2019.6.27
 * @Description mainactivity的监听器
 */
public class listener_mainactivity {

    MainActivity mainactivity;//声明对应类的对象,通过构造函数传参传入赋值，否则无法获取对应的控件


    /**
     * @parameter
     * @description 编写两个有参和无参构造函数，用于获取对应类的控件
     * @Time 2019/6/27 22:43
     */
    public listener_mainactivity() {
    }

    public listener_mainactivity(MainActivity m) {
        mainactivity = m;
    }


    /**
     * @parameter
     * @description MainActivity中的viewpaerd的监听器
     * @Time 2019/6/27 22:44
     */
    public void listener_MainActivity_vp() {
        mainactivity.viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                //当页面滚动时候触发
            }

            @Override
            public void onPageSelected(int i) {
                //当滑动完成
                switch (i) {
                    case 0:
                        mainactivity.rdb_home.setChecked(true);
                        break;
                    case 1:
                        mainactivity.rdb_bill.setChecked(true);
                        break;
                    case 2:
                        mainactivity.rdb_wish.setChecked(true);
                        break;
                    case 3:
                        mainactivity.rdb_statements.setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {
                //当滑动页面状态改变的时候
            }
        });
    }

    /**
     * @parameter
     * @description MainActivity中底部导航栏按钮的监听器
     * @Time 2019/6/27 22:44
     */
    public void listener_MainActivity_rdb() {
        mainactivity.rdb_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainactivity.viewpager.setCurrentItem(0, true);
            }
        });
        mainactivity.rdb_bill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainactivity.viewpager.setCurrentItem(1, true);
            }
        });
        mainactivity.rdb_wish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainactivity.viewpager.setCurrentItem(2, true);
            }
        });
        mainactivity.rdb_statements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainactivity.viewpager.setCurrentItem(3, true);
            }
        });
    }


}
