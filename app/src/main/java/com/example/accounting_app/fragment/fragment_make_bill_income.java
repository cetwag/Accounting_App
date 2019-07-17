package com.example.accounting_app.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.accounting_app.R;
import com.example.accounting_app.function.type_or_format_conversion;
import com.example.accounting_app.listener.listener_fragment_make_bill_income;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import java.util.Date;

/**
 * @Creator cetwag yuebanquan
 * @Version V2.0.0
 * @Time 2019.6.29
 * @Description 记一笔的收入碎片类
 */
public class fragment_make_bill_income extends Fragment {

    public RadioButton rdb_select_time_income;
    public TimePickerView pvTime;      //时间选择器
    listener_fragment_make_bill_income listener;
    type_or_format_conversion t;//功能类对象
    public RadioGroup rdg_1, rdg_2;
    public RadioButton rdb_bonus, rdb_wages, rdb_invest_profit, rdb_reimbursement,
            rdb_borrow, rdb_invest_recovery, rdb_debts_collection, rdb_red_envelope;
    public EditText edt_input_money_income;//收入金额输入框
    public TextView tv_from_income;//收入到的银行
    public EditText edt_remarks_message_income;
    public ImageButton Imgbtn_select_from_income;
    public Button btn_determine_income;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_make_bill_income, container, false);

        //屏幕适配
        ScreenAdapterTools.getInstance().loadView(view);

        return view;

    }

    /**
     * @parameter
     * @description 根据fragment的生命周期 初始化等相关封装函数需要放在这里面
     * @Time 2019/6/29 11:04
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //控件初始化
        init();

        //时间选择器初始化
        initTimePick();

        //监听类
        listener.listener_fragment_mbi();
    }

    /**
     * @parameter
     * @description 控件初始化
     * @Time 2019/6/29 11:10
     */
    void init() {
        rdb_select_time_income = getView().findViewById(R.id.rdb_select_time_income);
        listener = new listener_fragment_make_bill_income(this);
        t = new type_or_format_conversion();
        rdg_1 = getView().findViewById(R.id.rdg_one_income);
        rdg_2 = getView().findViewById(R.id.rdg_two_income);
        rdb_bonus =getView().findViewById(R.id.rdb_bonus);
        rdb_wages =getView().findViewById(R.id.rdb_wages);
        rdb_invest_profit =getView().findViewById(R.id.rdb_invest_profit);
        rdb_reimbursement =getView().findViewById(R.id.rdb_reimbursement);
        rdb_borrow =getView().findViewById(R.id.rdb_borrow);
        rdb_invest_recovery =getView().findViewById(R.id.rdb_invest_recovery);
        rdb_debts_collection =getView().findViewById(R.id.rdb_debts_collection);
        rdb_red_envelope =getView().findViewById(R.id.rdb_red_envelope);
        edt_input_money_income =getView().findViewById(R.id.edt_input_money_income);
        tv_from_income = getView().findViewById(R.id.tv_from_income);
        edt_remarks_message_income = getView().findViewById(R.id.edt_remarks_message_income);
        Imgbtn_select_from_income = getView().findViewById(R.id.Imgbtn_select_from_income);
        btn_determine_income = getView().findViewById(R.id.btn_determine_income);
    }

    /**
     * @parameter
     * @description 初始化时间选择器
     * @Time 2019/6/29 11:10
     */
    void initTimePick() {
        pvTime = new TimePickerBuilder(getContext(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                rdb_select_time_income.setText(t.getTimeYMD(date));
            }
        }).build();
    }
}
