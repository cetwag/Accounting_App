package com.example.accounting_app.listener;

import android.view.View;

import com.bigkoo.pickerview.view.TimePickerView;
import com.example.accounting_app.R;
import com.example.accounting_app.fragment.fragment_make_bill_income;

/**
 * @Creator cetwag yuebanquan
 * @Version V2.0.0
 * @ 2019.6.29
 * @Description 记一笔收入碎片的监听
 */
public class listener_fragment_make_bill_income implements View.OnClickListener {
    fragment_make_bill_income frag_mbi;


    /**
     * @parameter
     * @description 编写两个有参和无参构造函数，用于获取对应类的控件
     * @Time 2019/6/29 11:00
     */
    public listener_fragment_make_bill_income() {
    }

    public listener_fragment_make_bill_income(fragment_make_bill_income fmbi) {
        frag_mbi = fmbi;
    }

    /**
     * @parameter
     * @description 监听绑定
     * @Time 2019/6/29 11:04
     */
    public void listener_fragment_mbi() {
        frag_mbi.rdb_select_time_income.setOnClickListener(this);
        frag_mbi.rdb_bonus.setOnClickListener(this);
        frag_mbi.rdb_wages.setOnClickListener(this);
        frag_mbi.rdb_invest_profit.setOnClickListener(this);
        frag_mbi.rdb_reimbursement.setOnClickListener(this);
        frag_mbi.rdb_borrow.setOnClickListener(this);
        frag_mbi.rdb_invest_recovery.setOnClickListener(this);
        frag_mbi.rdb_debts_collection.setOnClickListener(this);
        frag_mbi.rdb_red_envelope.setOnClickListener(this);
    }


    /**
     * @parameter
     * @description 实现view.OnClickListener接口的监听函数
     * @Time 2019/6/29 11:06
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rdb_select_time_income:
                frag_mbi.pvTime.show();//显示出时间选择器
                break;
            case R.id.rdb_bonus:
                frag_mbi.rdg_2.clearCheck();
                break;
            case R.id.rdb_wages:
                frag_mbi.rdg_2.clearCheck();
                break;
            case R.id.rdb_invest_profit:
                frag_mbi.rdg_2.clearCheck();
                break;
            case R.id.rdb_reimbursement:
                frag_mbi.rdg_2.clearCheck();
                break;
            case R.id.rdb_borrow:
                frag_mbi.rdg_1.clearCheck();
                break;
            case R.id.rdb_invest_recovery:
                frag_mbi.rdg_1.clearCheck();
                break;
            case R.id.rdb_debts_collection:
                frag_mbi.rdg_1.clearCheck();
                break;
            case R.id.rdb_red_envelope:
                frag_mbi.rdg_1.clearCheck();
                break;
        }
    }
}
