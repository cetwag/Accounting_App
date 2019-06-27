package com.example.accounting_app.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.accounting_app.R;

/**
 * @Creator cetwag, yuebanquan
 * @Version V2.0.0
 * @Time 2019.6.27
 * @Description 心愿模块碎片类
 */
public class fragment_wish extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //inflater使将xml布局文件转换为视图的一个类,container表示在container里面显示这个视图
        View view = inflater.inflate(R.layout.fragment_wish, container, false);
        return view;
    }
}
