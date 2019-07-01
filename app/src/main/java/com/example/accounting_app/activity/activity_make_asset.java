package com.example.accounting_app.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.accounting_app.R;
import com.example.accounting_app.adapter.adapter_activity_make_asset;
import com.example.accounting_app.listener.listener_activity_make_asset;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @Creator cetway yuebanquan
 * @Version V2.0.0
 * @Time 2019.6.29
 * @Description 记资产activity
 */
public class activity_make_asset extends AppCompatActivity {

    public Button btn_back;
    listener_activity_make_asset listener;
    adapter_activity_make_asset adapter;
    public Spinner spinner;
    public TextView tv_select_card;
    public ImageButton Img_select_bank;
    public EditText edt_asset_from, edt_remarks_message;
    public LinearLayout Lin_major, Lin_outer;
    public LinkedList<Button> listIBTNDel;
    public Button btn_cut_down;
    public EditText edt_input_balance;
    public ImageView Imgv_country_money;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_asset);

        //屏幕适配
        ScreenAdapterTools.getInstance().reset(this);//如果希望android7.0分屏也适配的话,加上这句
        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        //控件初始化
        init();

        //监听功能
        listener.listener_activity_ma();

        //适配器功能
        adapter.adapter_Activity_make_asset();
    }

    /**
     * @parameter
     * @description 控件初始化
     * @Time 2019/6/29 12:57
     */
    void init() {
        btn_back = findViewById(R.id.btn_back);
        spinner = findViewById(R.id.spi_select_money);
        tv_select_card = findViewById(R.id.tv_select_card);
        Img_select_bank = findViewById(R.id.Img_select_bank);
        edt_asset_from = findViewById(R.id.edt_asset_from);
        edt_asset_from.setText("中国银行");
        edt_remarks_message = findViewById(R.id.edt_remarks_message);
        edt_remarks_message.setText("借记卡");
        Lin_major = findViewById(R.id.Lin_major);
        btn_cut_down = findViewById(R.id.btn_cut_down);
        edt_input_balance = findViewById(R.id.edt_input_balance);
        Imgv_country_money = findViewById(R.id.Imgv_country_money);
        Lin_outer = findViewById(R.id.Lin_outer);
        listener = new listener_activity_make_asset(this);
        adapter = new adapter_activity_make_asset(this);
        listIBTNDel = new LinkedList<Button>();
        listIBTNDel.add(0, btn_cut_down);//先将xml里的人民币加入链表先
    }
}
