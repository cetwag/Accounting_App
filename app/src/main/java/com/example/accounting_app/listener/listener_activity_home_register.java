package com.example.accounting_app.listener;

import android.content.Intent;
import android.view.View;

import com.example.accounting_app.R;
import com.example.accounting_app.activity.activity_home_login;
import com.example.accounting_app.activity.activity_home_register;


/**
 * @Creator cetwag yuebanquan
 * @Version V2.0.0
 * @Time 2019.6.28
 * @Description 注册activity的监听
 */
public class listener_activity_home_register implements View.OnClickListener {


    activity_home_register activity_home_register;

    /**
     * @parameter
     * @description 编写两个有参和无餐构造函数用来获取对应类的控件
     * @Time 2019/6/28 15:56
     */
    public listener_activity_home_register() {
    }

    public listener_activity_home_register(activity_home_register ahg) {
        activity_home_register = ahg;
    }

    /**
     * @parameter
     * @description 对应类控件的点击监听绑定
     * @Time 2019/6/28 15:56
     */
    public void listener_hr() {
        activity_home_register.btn_goto_login.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_goto_login:
                Intent intent_goto_login = new Intent(activity_home_register, activity_home_login.class);
                activity_home_register.startActivity(intent_goto_login);
                activity_home_register.finish();
                break;
        }
    }
}
