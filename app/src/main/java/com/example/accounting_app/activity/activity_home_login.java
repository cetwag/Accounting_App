package com.example.accounting_app.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.accounting_app.R;
import com.example.accounting_app.listener.listener_activity_home_login;

/**
 * @Creator cetwag yuebanquan
 * @Version V2.0.0
 * @Time 2019.6.28
 * @Description 登录activity
 */
public class activity_home_login extends AppCompatActivity {

    public Button btn_goto_register;
    listener_activity_home_login listener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_login);

        init();
        listener.listener_hg();
    }

    /**
     * @parameter
     * @description 初始化view控件
     * @Time 2019/6/28 15:36
     */
    void init() {
        btn_goto_register=findViewById(R.id.btn_goto_register);
        listener=new listener_activity_home_login(this);
    }
}
