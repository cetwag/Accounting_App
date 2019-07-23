package com.example.accounting_app.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.InputFilter;
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
import com.example.accounting_app.function.CashierInputFilter;
import com.example.accounting_app.function.type_or_format_conversion;
import com.example.accounting_app.listener.listener_fragment_make_bill_pay;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import java.util.Date;

/**
 * @Creator cetwag yuebanquan
 * @Version V2.0.0
 * @Time 2019.6.29  2019.7.23
 * @Description 记一笔中的支出碎片
 * 此处用于管控记一笔的支出页面业务逻辑
 */
public class fragment_make_bill_pay extends Fragment {

    listener_fragment_make_bill_pay listener;
    public RadioButton rdb_select_time_pay;
    public TimePickerView pvTime;//时间选择器
    type_or_format_conversion t;//功能类对象
    public Button btn_determine_pay;//最终确认按钮
    public ImageButton Imgbtn_select_from_pay;
    public EditText edt_input_money_pay, edt_remarks_message_pay;
    public TextView tv_from_pay;
    public RadioGroup rdg_1, rdg_2, rdg_3;
    public RadioButton rdb_food, rdb_travel, rdb_shop, rdb_traffic, rdb_communication,
            rdb_hospital, rdb_house, rdb_child, rdb_teach, rdb_play, rdb_pet, rdb_life;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_make_bill_pay, container, false);

        //屏幕适配
        ScreenAdapterTools.getInstance().loadView(view);

        return view;
    }

    /**
     * @parameter
     * @description 根据生命周期相关，控件初始化的封装函数以及其他该碎片的相关封装函数需要放在该方法中
     * @Time 2019/6/29 9:36
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //控件初始化
        init();

        //初始化时间选择器
        initTimePick();

        //监听类函数功能
        listener.listener_Fragment_make_bill_pay();

        //过滤用户输入金额格式，只能输入至小数点后两位
        cashierInputFilter();

    }

    /**
     * @parameter
     * @description 初始化控件
     * @Time 2019/6/29 11:23
     */
    private void init() {
        rdb_select_time_pay = getView().findViewById(R.id.rdb_select_time_pay);
        listener = new listener_fragment_make_bill_pay(this);
        t = new type_or_format_conversion();
        rdg_1 = getView().findViewById(R.id.rdg_one_pay);
        rdg_2 = getView().findViewById(R.id.rdg_two_pay);
        rdg_3 = getView().findViewById(R.id.rdg_three_pay);
        rdb_food = getView().findViewById(R.id.rdb_food);
        rdb_travel = getView().findViewById(R.id.rdb_travel);
        rdb_shop = getView().findViewById(R.id.rdb_shop);
        rdb_traffic = getView().findViewById(R.id.rdb_traffic);
        rdb_communication = getView().findViewById(R.id.rdb_communication);
        rdb_hospital = getView().findViewById(R.id.rdb_hospital);
        rdb_house = getView().findViewById(R.id.rdb_house);
        rdb_child = getView().findViewById(R.id.rdb_child);
        rdb_teach = getView().findViewById(R.id.rdb_teach);
        rdb_play = getView().findViewById(R.id.rdb_play);
        rdb_pet = getView().findViewById(R.id.rdb_pet);
        rdb_life = getView().findViewById(R.id.rdb_life);
        btn_determine_pay = getView().findViewById(R.id.btn_determine_pay);
        Imgbtn_select_from_pay = getView().findViewById(R.id.Imgbtn_select_from_pay);
        tv_from_pay = getView().findViewById(R.id.tv_from_pay);
        edt_input_money_pay = getView().findViewById(R.id.edt_input_money_pay);
        edt_remarks_message_pay = getView().findViewById(R.id.edt_remarks_message_pay);
    }

    /**
     * @parameter
     * @description 初始化时间选择器
     * @Time 2019/6/29 11:10
     */
    public void initTimePick() {
        pvTime = new TimePickerBuilder(getContext(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                rdb_select_time_pay.setText(t.getTimeYMD(date));
            }
        }).build();
    }

    /**
     * @parameter
     * @description 过滤用户输入金额格式，只能输入至小数点后两位
     * @Time 2019/7/23 16:49
     */
    private void cashierInputFilter() {
        InputFilter[] filters = {new CashierInputFilter()};
        edt_input_money_pay.setFilters(filters); //设置金额输入的过滤器，保证只能输入金额类型
    }

}
