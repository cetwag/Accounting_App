package com.example.accounting_app.listener;

import android.content.Intent;
import android.view.View;

import com.example.accounting_app.R;
import com.example.accounting_app.activity.activity_home_login;
import com.example.accounting_app.activity.activity_home_register;

/**
 * @Creator
 * @Version
 * @Time
 * @Description
 */
public class listener_activity_home_login implements View.OnClickListener {
    activity_home_login activity_home_login;

    /**
     * @parameter
     * @description 编写两个有参和午餐构造函数用来获取对应类的控件
     * @Time 2019/6/28 15:37
     */
    public listener_activity_home_login() {
    }

    public listener_activity_home_login(activity_home_login ahl) {
        activity_home_login = ahl;
    }

    /**
     * @parameter
     * @description 对应类控件的点击监听绑定
     * @Time 2019/6/28 15:39
     */
    public void listener_hg() {
        activity_home_login.btn_goto_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_goto_register:
                Intent intent_goto_register = new Intent(activity_home_login, activity_home_register.class);
                activity_home_login.startActivity(intent_goto_register);
                activity_home_login.finish();//同时要finish掉自己的活动，否则会造成多次切换多次冗余页面的问题
                break;
        }
    }
}
