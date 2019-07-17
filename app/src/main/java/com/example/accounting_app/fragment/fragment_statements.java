package com.example.accounting_app.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.accounting_app.R;
import com.example.accounting_app.adapter.adapter_fragment_statements;
import com.example.accounting_app.function.Pie_Chart;
import com.example.accounting_app.listener.listener_fragment_statements;
import com.yatoooon.screenadaptation.ScreenAdapterTools;


/**
 * @Creator cetwag, yuebanquan
 * @Version V2.0.0
 * @Time 2019.6.27
 * @Description 报表模块碎片
 */
public class fragment_statements extends Fragment {

     public Pie_Chart piec;//图表功能类

    //报表碎片中的日期选择弹窗的标志
    public static final int FRAGMENT_STATEMENTS_SELECT_TIME = 2;
    listener_fragment_statements listener;
    public TextView tv_time_selected;
    public Spinner spi_income_pay;
    adapter_fragment_statements adapter;
    public com.github.mikephil.charting.charts.PieChart piechart;

    public LinearLayout Lin_statements_item;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //inflater使将xml布局文件转换为视图的一个类,container表示在container里面显示这个视图
        View view = inflater.inflate(R.layout.fragment_statements, container, false);

        //屏幕适配
        ScreenAdapterTools.getInstance().loadView(view);

        return view;
    }

    /**
     * @parameter
     * @description 根据fragment的生命周期，onActivityCreated在onCreateView后执行
     * @Time 2019/6/29 21:29
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //控件初始化
        init();

        //监听功能函数
        listener.listener_Fragment_statements();

        //适配器功能函数
        adapter.adapter_Fragment_statements();

        //饼状图绘制
        piec.pie_chart_data_pay();

        //饼状图监听绘制
        listener.select_pay_income();
    }

    /**
     * @parameter
     * @description 控件初始化
     * @Time 2019/6/29 21:26
     */
    void init() {
        tv_time_selected = getView().findViewById(R.id.tv_time_selected);
        spi_income_pay = getView().findViewById(R.id.spi_income_pay);
        piechart = getView().findViewById(R.id.pie_chart);
        listener = new listener_fragment_statements(this);
        adapter = new adapter_fragment_statements(this);
        piec = new Pie_Chart(this);
        Lin_statements_item = getView().findViewById(R.id.Lin_statements_item);
    }
}
