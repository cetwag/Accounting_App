package com.example.accounting_app.listener;

import android.view.View;

import com.example.accounting_app.R;
import com.example.accounting_app.fragment.fragment_make_bill_pay;

/**
 * @Creator cetwag yuebanquan
 * @Version V2.0.0
 * @Time 2019.6.29
 * @Description 记一笔支出类的监听类
 */
public class listener_fragment_make_bill_pay implements View.OnClickListener {
    fragment_make_bill_pay frag_mbp;

    /**
     * @parameter
     * @description 编写两个有参和无参构造函数，用于获取对应类的控件
     * @Time 2019/6/29 11:21
     */
    public listener_fragment_make_bill_pay() {
    }

    public listener_fragment_make_bill_pay(fragment_make_bill_pay fmbp) {
        frag_mbp = fmbp;
    }

    /**
     * @parameter
     * @description 监听绑定
     * @Time 2019/6/29 11:23
     */
    public void listener_Fragment_make_bill_pay() {
        frag_mbp.rdb_select_time_pay.setOnClickListener(this);
        frag_mbp.rdb_food.setOnClickListener(this);
        frag_mbp.rdb_travel.setOnClickListener(this);
        frag_mbp.rdb_shop.setOnClickListener(this);
        frag_mbp.rdb_traffic.setOnClickListener(this);
        frag_mbp.rdb_communication.setOnClickListener(this);
        frag_mbp.rdb_hospital.setOnClickListener(this);
        frag_mbp.rdb_house.setOnClickListener(this);
        frag_mbp.rdb_child.setOnClickListener(this);
        frag_mbp.rdb_teach.setOnClickListener(this);
        frag_mbp.rdb_play.setOnClickListener(this);
        frag_mbp.rdb_pet.setOnClickListener(this);
        frag_mbp.rdb_life.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rdb_select_time_pay:
                frag_mbp.pvTime.show();//显示出时间选择器
                break;
            case R.id.rdb_food:
                frag_mbp.rdg_2.clearCheck();
                frag_mbp.rdg_3.clearCheck();
                break;
            case R.id.rdb_travel:
                frag_mbp.rdg_2.clearCheck();
                frag_mbp.rdg_3.clearCheck();
                break;
            case R.id.rdb_shop:
                frag_mbp.rdg_2.clearCheck();
                frag_mbp.rdg_3.clearCheck();
                break;
            case R.id.rdb_traffic:
                frag_mbp.rdg_2.clearCheck();
                frag_mbp.rdg_3.clearCheck();
                break;
            case R.id.rdb_communication:
                frag_mbp.rdg_1.clearCheck();
                frag_mbp.rdg_3.clearCheck();
                break;
            case R.id.rdb_hospital:
                frag_mbp.rdg_1.clearCheck();
                frag_mbp.rdg_3.clearCheck();
                break;
            case R.id.rdb_house:
                frag_mbp.rdg_1.clearCheck();
                frag_mbp.rdg_3.clearCheck();
                break;
            case R.id.rdb_child:
                frag_mbp.rdg_1.clearCheck();
                frag_mbp.rdg_3.clearCheck();
                break;
            case R.id.rdb_teach:
                frag_mbp.rdg_1.clearCheck();
                frag_mbp.rdg_2.clearCheck();
                break;
            case R.id.rdb_play:
                frag_mbp.rdg_1.clearCheck();
                frag_mbp.rdg_2.clearCheck();
                break;
            case R.id.rdb_pet:
                frag_mbp.rdg_1.clearCheck();
                frag_mbp.rdg_2.clearCheck();
                break;
            case R.id.rdb_life:
                frag_mbp.rdg_1.clearCheck();
                frag_mbp.rdg_2.clearCheck();
                break;
        }
    }
}
