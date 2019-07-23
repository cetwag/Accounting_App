package com.example.accounting_app.listener;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.example.accounting_app.R;
import com.example.accounting_app.activity.MainActivity;
import com.example.accounting_app.activity.activity_make_wish;
import com.example.accounting_app.activity.activity_make_asset;
import com.example.accounting_app.activity.activity_make_bill;
import com.example.accounting_app.fragment.fragment_home;
import com.example.accounting_app.function.CustomViewPager;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

/**
 * @Creator cetwag, yuebanquan
 * @Version V2.0.0
 * @Time 2019.6.27
 * @Description mainactivity的监听器
 */
public class listener_mainactivity implements View.OnClickListener {

    MainActivity mainactivity;//声明对应类的对象,通过构造函数传参传入赋值，否则无法获取对应的控件
    AlertDialog.Builder dialog;//弹窗对象


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
                        mainactivity.viewpager.setAllowedSwipeDirection(CustomViewPager.SwipeDirection.right);
                        break;
                    case 1:
                        mainactivity.rdb_bill.setChecked(true);
                        mainactivity.viewpager.setAllowedSwipeDirection(CustomViewPager.SwipeDirection.all);
                        break;
                    case 2:
                        mainactivity.rdb_wish.setChecked(true);
                        mainactivity.viewpager.setAllowedSwipeDirection(CustomViewPager.SwipeDirection.all);
                        break;
                    case 3:
                        mainactivity.rdb_statements.setChecked(true);
                        mainactivity.viewpager.setAllowedSwipeDirection(CustomViewPager.SwipeDirection.all);
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
     * @description MainActivity中底部导航栏按钮绑定监听器
     * @Time 2019/6/27 22:44
     */
    public void listener_MainActivity_rdb() {
        mainactivity.rdb_home.setOnClickListener(this);
        mainactivity.rdb_bill.setOnClickListener(this);
        mainactivity.rdb_wish.setOnClickListener(this);
        mainactivity.rdb_statements.setOnClickListener(this);
        mainactivity.rdb_add.setOnClickListener(this);
    }

    /**
     * @parameter
     * @description 实现View.onClickListener接口, 可以采用switch case简化setOnClickListener
     * @Time 2019/6/28 12:51
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rdb_home:
                mainactivity.viewpager.setCurrentItem(0, true);
                break;
            case R.id.rdb_bill:
                mainactivity.viewpager.setCurrentItem(1, true);
                break;
            case R.id.rdb_wish:
                mainactivity.viewpager.setCurrentItem(2, true);
                break;
            case R.id.rdb_statements:
                mainactivity.viewpager.setCurrentItem(3, true);
                break;
            case R.id.rdb_add:
                //编写出现弹窗
                dialog = new AlertDialog.Builder(mainactivity);//先通过AlertDialog.Builder创建一个AlertDialog实例
                View view = View.inflate(mainactivity, R.layout.dialog_add_one, null);
                ScreenAdapterTools.getInstance().loadView(view);
                dialog.setView(view);
                dialog.show();
                /**
                 * 编写弹窗里的三个按钮的监听事件
                 */
                Button btn_make_bill = view.findViewById(R.id.btn_make_bill);
                Button btn_make_asset = view.findViewById(R.id.btn_make_asset);
                Button btn_make_wish = view.findViewById(R.id.btn_make_wish);
                btn_make_bill.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent_make_bill = new Intent(mainactivity, activity_make_bill.class);
                        mainactivity.startActivity(intent_make_bill);
                    }
                });
                btn_make_asset.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent_make_asset = new Intent(mainactivity, activity_make_asset.class);
                        mainactivity.startActivity(intent_make_asset);
                    }
                });
                btn_make_wish.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //点击进入记心愿页面
                        Intent intent_make_wish = new Intent(mainactivity, activity_make_wish.class);
                        mainactivity.startActivity(intent_make_wish);
                    }
                });
                break;
        }
    }
}
