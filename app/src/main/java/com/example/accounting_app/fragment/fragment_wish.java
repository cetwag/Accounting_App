package com.example.accounting_app.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.accounting_app.R;
import com.example.accounting_app.listener.listener_fragment_wish;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

/**
 * @Creator cetwag, yuebanquan
 * @Version V2.0.0
 * @Time 2019.6.27
 * @Description 心愿模块碎片类
 */
public class fragment_wish extends Fragment {

    public Button btn_save_money;
    public Button btn_wish_list;

    listener_fragment_wish listener;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //inflater使将xml布局文件转换为视图的一个类,container表示在container里面显示这个视图
        View view = inflater.inflate(R.layout.fragment_wish, container, false);
        ScreenAdapterTools.getInstance().loadView(view);//屏幕适配
        return view;
    }

    /**
     * @parameter
     * @description 根据fragment的生命周期，onActivityCreated在onCreateView后执行
     * @Time 2019/6/28 14:40
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        init();
        listener.listener_fw();//该类的监听调用

    }

    /**
     * @parameter
     * @description view控件初始化
     * @Time 2019/6/28 14:12
     */
    void init() {
        btn_save_money = getView().findViewById(R.id.btn_save_money);
        btn_wish_list = getView().findViewById(R.id.btn_wish_list);
        listener = new listener_fragment_wish(this);//该类的监听
    }
}
