package com.example.accounting_app.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.accounting_app.R;
import com.example.accounting_app.listener.listener_activity_home_register;

/**
 * @Creator cetwag yuebanquan
 * @Version v2.0.0
 * @Time 2019.6.28
 * @Description 注册activity
 */
public class activity_home_register extends AppCompatActivity {

    public Button btn_goto_login;
    listener_activity_home_register listener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_register);

        init();
        listener.listener_hr();
    }

    /**
     * @parameter
     * @description 初始化控件
     * @Time 2019/6/28 15:58
     */
    void init() {
        btn_goto_login = findViewById(R.id.btn_goto_login);
        listener=new listener_activity_home_register(this);
    }
}
