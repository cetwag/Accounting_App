package com.example.accounting_app.adapter;

import android.widget.ArrayAdapter;

import com.example.accounting_app.R;
import com.example.accounting_app.fragment.fragment_statements;

import java.util.ArrayList;
import java.util.List;

/**
 * @Creator cetwag yuebanquan
 * @Version V2.0.0
 * @Time 2019.6.29
 * @Description 报表碎片的适配器
 */
public class adapter_fragment_statements {

    fragment_statements frag_s;
    //创建一个用来对spinner添加内容的list
    List<String> list = new ArrayList<String>();

    /**
     *@parameter
     *@description 写两个有参和无参构造函数，用于获取对应类中的控件
     *@Time 2019/6/29 21:38
     */
    public adapter_fragment_statements(){}
    public adapter_fragment_statements(fragment_statements fs){
        frag_s=fs;
    }

    /**
     *@parameter
     *@description 适配功能方法
     *@Time 2019/6/29 21:39
     */
    public void adapter_Fragment_statements(){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(frag_s.getActivity(), R.layout.spinner_fragment_statements, list);//适配器创建
        list.add("支出");
        list.add("收入");
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        frag_s.spi_income_pay.setAdapter(adapter);//控件绑定适配器
    }

}
