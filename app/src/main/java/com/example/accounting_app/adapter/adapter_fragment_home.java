package com.example.accounting_app.adapter;

import android.widget.ArrayAdapter;

import com.example.accounting_app.fragment.fragment_home;

/**
 * @Creator cetwag yuebanquan
 * @Version V2.0.0
 * @Time 2019.6.28
 * @Description 首页碎片的适配器类
 */
public class adapter_fragment_home {

    fragment_home fragment_home;//声明对应类的对象,通过构造函数传参传入赋值，否则无法获取对应的控件
    //侧滑菜单的内容字符串数组
    public String[] lv_menu;


    /**
     * @parameter
     * @description 编写两个有参和无参构造函数，用于获取对应类的控件
     * @Time 2019/6/28 10:58
     */
    public adapter_fragment_home() {
    }

    public adapter_fragment_home(fragment_home fh) {
        fragment_home = fh;
    }

    /**
     * @parameter
     * @description 首页碎片侧滑栏内容填充适配器
     * @Time 2019/6/28 10:58
     */
    public void adapter_Frgment_Home() {
        String[] lv_menu = new String[]{"消息中心", "个性皮肤", "同步", "汇率计算器", "房贷计算器",
                "帮助与反馈", "关于小账本", "清空缓存", "设置"};
        //创建适配器,并加载对应的布局资源
        fragment_home.adapter = new ArrayAdapter<String>(fragment_home.getActivity(), android.R.layout.simple_expandable_list_item_1, lv_menu);
        fragment_home.lv.setAdapter(fragment_home.adapter);//listview与适配器的绑定
    }

}
