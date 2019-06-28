package com.example.accounting_app.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.accounting_app.R;
import com.example.accounting_app.adapter.adapter_fragment_home;
import com.example.accounting_app.listener.listener_fragment_home;
import com.yatoooon.screenadaptation.ScreenAdapterTools;


/**
 * @Creator cetwag, yuebanquan
 * @Version V2.0.0
 * @Time 2019.6.27
 * @Description 首页模块碎片
 */

public class fragment_home extends Fragment {

    public Button btn_slide;
    public DrawerLayout drawerlayout;

    //侧滑菜单相关
    public ListView lv;//声明侧滑菜单的列表控件
    public ArrayAdapter<String> adapter;//声明一个字符串型的数组适配器
    private LinearLayout layout_second;//汉堡菜单里的侧滑布局
    public Button btn_login, btn_register;

    //该碎片的监听和适配器类
    listener_fragment_home listener_fh;
    adapter_fragment_home adapter_fh;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //inflater使将xml布局文件转换为视图的一个类,container表示在container里面显示这个视图
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ScreenAdapterTools.getInstance().loadView(view);//屏幕适配
        return view;
    }

    /**
     * @parameter
     * @description 根据fragment的生命周期，onActivityCreated在onCreateView后执行
     * @Time 2019/6/28 9:30
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        init();//控件的初始化
        listener_fh.listener_hf();//首页碎片的监听
        listener_fh.listener_hf_lv_item();//首页碎片侧滑菜单列表项的监听
        adapter_fh.adapter_Frgment_Home();//首页碎片的侧滑栏适配器

        change_state_unlog();//未登录状态
        //change_state_haveloged();//登录状态
    }


    /**
     * @parameter
     * @description view控件的初始化
     * @Time 2019/6/28 9:49
     */
    void init() {

        btn_slide = getView().findViewById(R.id.btn_slide);
        drawerlayout = getView().findViewById(R.id.DrawerLayout);
        lv = getView().findViewById(R.id.lv_slide_menu);
        layout_second = getView().findViewById(R.id.Lin_slide);
        listener_fh = new listener_fragment_home(this);
        adapter_fh = new adapter_fragment_home(this);
        btn_login = new Button(getContext());
        btn_register = new Button(getContext());
    }

    /**
     * @parameter
     * @description 未登录状态的动态生成控件
     * @Time 2019/6/28 11:23
     */
    public void change_state_unlog() {
        //头像图片编写
        ImageView head_icon = new ImageView(getContext());
        head_icon.setBackgroundResource(R.drawable.head_icon);
        LinearLayout.LayoutParams head_icon_Params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        head_icon_Params.weight = 1;//设置权重
        head_icon.setLayoutParams(head_icon_Params);
        //头像图片编写完毕

        //登录按钮编写
        btn_login.setId(R.id.my_create_btn_login);
        btn_login.setBackgroundResource(R.drawable.line_block);
        btn_login.setText("登录");
        btn_login.setTextSize(16);
        btn_login.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);//加粗
        LinearLayout.LayoutParams login_Params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        login_Params.weight = 1;//设置权重
        btn_login.setLayoutParams(login_Params);
        //登录按钮编写完毕

        //注册按钮编写
        btn_register.setId(R.id.my_create_btn_register);
        btn_register.setBackgroundResource(R.drawable.line_block);
        btn_register.setText("注册");
        btn_register.setTextSize(16);
        btn_register.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);//加粗
        LinearLayout.LayoutParams register_Params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        register_Params.weight = 1;//设置权重
        btn_register.setLayoutParams(register_Params);
        //注册按钮编写完毕

        layout_second.addView(head_icon);//将头像图片加入布局
        layout_second.addView(btn_login);//将登录按钮加入布局
        layout_second.addView(btn_register);//将注册按钮加入布局
    }

    /**
     * @parameter
     * @description 动态生成控件
     * 首页fragment侧滑菜单的顶部登录注册模块的登录状态编写(图片变更,信息变更)
     */
    public void change_state_haveloged() {
        //头像图片编写
        ImageView head_icon = new ImageView(getContext());
        head_icon.setBackgroundResource(R.drawable.head_icon);
        LinearLayout.LayoutParams head_icon_Params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        head_icon_Params.weight = 1;//设置权重
        head_icon.setLayoutParams(head_icon_Params);
        //头像图片编写完毕


        //用户名TextView编写
        TextView user_name = new TextView(getContext());
        user_name.setText("111");//用static偷机取巧的做法！
        user_name.setTextSize(16);
        user_name.setTextColor(Color.BLACK);//黑色字体
        user_name.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);//加粗
        LinearLayout.LayoutParams user_name_Params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        user_name_Params.setMargins(0, 40, 0, 0);
        user_name_Params.weight = 1;//设置权重
        user_name.setLayoutParams(user_name_Params);
        //用户名TextView编写完毕

        //注册按钮编写
        Button sign_out = new Button(getContext());
        sign_out.setBackgroundResource(R.drawable.line_block);
        sign_out.setText("退出");
        sign_out.setTextSize(16);
        sign_out.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);//加粗
        LinearLayout.LayoutParams sign_out_Params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        sign_out_Params.weight = 1;//设置权重
        sign_out.setLayoutParams(sign_out_Params);
        sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_second.removeAllViews();
                //change_state_unlog();//回到未登录状态
            }
        });
        //注册按钮编写完毕

        layout_second.addView(head_icon);//将头像图片加入布局
        layout_second.addView(user_name);//将登录按钮加入布局
        layout_second.addView(sign_out);//将注册按钮加入布局
    }


}
