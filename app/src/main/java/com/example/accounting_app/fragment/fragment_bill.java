package com.example.accounting_app.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;


import com.example.accounting_app.R;
import com.example.accounting_app.listener.listener_fragment_bill;
import com.yatoooon.screenadaptation.ScreenAdapterTools;


/**
 * @Creator cetwag, yuebanquan
 * @Version V2.0.0
 * @Time 2019.6.27
 * @Description 账单模块碎片类
 */
public class fragment_bill extends Fragment {

    public TextView tv_screen;
    public DrawerLayout drawerlayout;
    listener_fragment_bill listener;
    public TextView tv_time_selected;
    //账单碎片中的日期选择弹窗的标志
    public static final int FRAGMENT_BILL_SELECT_TIME = 1;
    //重置功能相关控件
    public Button btn_reset;
    public RadioGroup rdg_one;
    CheckBox ckb_food, ckb_shop, ckb_house, ckb_travel, ckb_play, ckb_communication,
            ckb_child, ckb_hospital, ckb_pay_other, ckb_wages, ckb_reward, ckb_interest, ckb_investment,
            ckb_unexpected, ckb_income_other, ckb_reimbursement, ckb_internal_transfer, ckb_deposit,
            ckb_collect_debts, ckb_return_credit_card, ckb_other_other;
    public CheckBox[] checkboxes;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //inflater使将xml布局文件转换为视图的一个类,container表示在container里面显示这个视图
        View view = inflater.inflate(R.layout.fragment_bill, container, false);

        //屏幕适配
        ScreenAdapterTools.getInstance().loadView(view);

        return view;
    }

    /**
     * @parameter
     * @description 根据fragment的生命周期，onActivityCreated在onCreateView后执行
     * @Time 2019/6/28 21:58
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
        listener.listener_fb();
    }

    /**
     * @parameter
     * @description view控件初始化
     * @Time 2019/6/28 21:10
     */
    void init() {
        tv_screen = getView().findViewById(R.id.tv_screen);
        drawerlayout = getView().findViewById(R.id.DrawerLayout);
        tv_time_selected = getView().findViewById(R.id.tv_time_selected);
        btn_reset = getView().findViewById(R.id.btn_reset);
        rdg_one = getView().findViewById(R.id.rdg_one);
        ckb_food = getView().findViewById(R.id.ckb_food);
        ckb_shop = getView().findViewById(R.id.ckb_shop);
        ckb_house = getView().findViewById(R.id.ckb_house);
        ckb_travel = getView().findViewById(R.id.ckb_travel);
        ckb_play = getView().findViewById(R.id.ckb_play);
        ckb_communication = getView().findViewById(R.id.ckb_communication);
        ckb_child = getView().findViewById(R.id.ckb_child);
        ckb_hospital = getView().findViewById(R.id.ckb_hospital);
        ckb_pay_other = getView().findViewById(R.id.ckb_pay_other);
        ckb_wages = getView().findViewById(R.id.ckb_wages);
        ckb_reward = getView().findViewById(R.id.ckb_reward);
        ckb_interest = getView().findViewById(R.id.ckb_interest);
        ckb_investment = getView().findViewById(R.id.ckb_investment);
        ckb_unexpected = getView().findViewById(R.id.ckb_unexpected);
        ckb_income_other = getView().findViewById(R.id.ckb_income_other);
        ckb_reimbursement = getView().findViewById(R.id.ckb_reimbursement);
        ckb_internal_transfer = getView().findViewById(R.id.ckb_internal_transfer);
        ckb_deposit = getView().findViewById(R.id.ckb_deposit);
        ckb_collect_debts = getView().findViewById(R.id.ckb_collect_debts);
        ckb_return_credit_card = getView().findViewById(R.id.ckb_return_credit_card);
        ckb_other_other = getView().findViewById(R.id.ckb_other_other);
        //这里用一个checkbox数组装，在写重置功能的时候就会轻松一些
        checkboxes = new CheckBox[]{ckb_food, ckb_shop, ckb_house, ckb_travel, ckb_play, ckb_communication,
                ckb_child, ckb_hospital, ckb_pay_other, ckb_wages, ckb_reward, ckb_interest, ckb_investment,
                ckb_unexpected, ckb_income_other, ckb_reimbursement, ckb_internal_transfer, ckb_deposit,
                ckb_collect_debts, ckb_return_credit_card, ckb_other_other};
        listener = new listener_fragment_bill(this);
    }


}
