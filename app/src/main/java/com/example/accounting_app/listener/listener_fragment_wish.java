package com.example.accounting_app.listener;

import android.content.Intent;
import android.view.View;

import com.example.accounting_app.R;
import com.example.accounting_app.activity.activity_add_wish;
import com.example.accounting_app.activity.activity_show_wish;
import com.example.accounting_app.fragment.fragment_wish;

/**
 * @Creator cetwag yuebanquan
 * @Version V2.0.0
 * @Time 2019.6.28
 * @Description 心愿碎片的监听类
 */
public class listener_fragment_wish implements View.OnClickListener {
    fragment_wish frag_w;//声明对应类的对象,通过构造函数传参传入赋值，否则无法获取对应的控件

    /**
     * @parameter
     * @description 编写两个有参和无参构造函数，用于获取对应类的控件
     * @Time 2019/6/28 14:17
     */
    public listener_fragment_wish() {
    }

    public listener_fragment_wish(fragment_wish fw) {
        frag_w = fw;
    }

    public void listener_fw() {
        frag_w.btn_save_money.setOnClickListener(this);
        frag_w.btn_wish_list.setOnClickListener(this);
    }


    /**
     * @parameter
     * @description 实现View.onClickListener接口, 可以采用switch case简化setOnClickListener
     * @Time 2019/6/28 14:39
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_save_money:
                Intent intent_save_money = new Intent(frag_w.getContext(), activity_add_wish.class);
                frag_w.startActivity(intent_save_money);
                break;
            case R.id.btn_wish_list:
                Intent intent_wish_list = new Intent(frag_w.getContext(), activity_show_wish.class);
                frag_w.startActivity(intent_wish_list);
                break;
        }
    }
}
