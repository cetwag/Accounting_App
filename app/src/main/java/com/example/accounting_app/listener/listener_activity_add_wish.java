package com.example.accounting_app.listener;

import android.content.Intent;
import android.view.View;

import com.example.accounting_app.R;
import com.example.accounting_app.activity.activity_make_wish;
import com.example.accounting_app.activity.activity_show_wish;

/**
 * @Creator cetwag yuebanquan
 * @Version V2.0.0
 * @Time 2019.6.29
 * @Description 记一笔心愿的监听类
 */
public class listener_activity_add_wish implements View.OnClickListener {

    activity_make_wish activity_add_wish;

    /**
     * @parameter
     * @description 编写两个有参和午餐构造函数用来获取对应类的控件
     * @Time 2019/6/28 22:37
     */
    public listener_activity_add_wish() {
    }

    public listener_activity_add_wish(activity_make_wish addw) {
        activity_add_wish = addw;
    }

    /**
     * @parameter
     * @description 监听绑定
     * @Time 2019/6/28 22:37
     */
    public void listener_adw() {
        activity_add_wish.tv_select_month.setOnClickListener(this);
        activity_add_wish.btn_next.setOnClickListener(this);
    }

    /**
     * @parameter
     * @description 实现View.OnClickListener接口的函数
     * @Time 2019/6/28 22:35
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_select_month:
                activity_add_wish.pvTime.show();
                break;
            case R.id.btn_next:
                //点击进入显示心愿页面
                Intent intent_to_show_wish = new Intent(activity_add_wish, activity_show_wish.class);
                activity_add_wish.startActivity(intent_to_show_wish);
                break;
        }
    }
}
