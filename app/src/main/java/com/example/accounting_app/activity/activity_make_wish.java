package com.example.accounting_app.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.accounting_app.R;
import com.example.accounting_app.function.type_or_format_conversion;
import com.example.accounting_app.listener.listener_activity_add_wish;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import org.w3c.dom.Text;

import java.util.Date;

/**
 * @Creator cetwag yuebanquan
 * @Version V2.0.0
 * @Time 2019.6.28
 * @Description 添加心愿activity
 */
public class activity_make_wish extends AppCompatActivity {

    public TimePickerView pvTime;//月份选择器
    public TextView tv_select_month;
    type_or_format_conversion t;//功能类对象
    listener_activity_add_wish listener;//监听类对象
    public Button btn_next;//下一步按钮
    public EditText edt_write_wish,edt_write_money;//心愿名称、金额输入框


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_wish);

        //屏幕适配
        ScreenAdapterTools.getInstance().reset(this);//如果希望android7.0分屏也适配的话,加上这句
        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        init();//初始化控件

        //初始化月份选择器
        initTimePickerView();

        //监听功能
        listener.listener_adw();

    }

    /**
     * @parameter
     * @description 初始化函数
     * @Time 2019/6/28 22:47
     */
    void init() {
        tv_select_month = findViewById(R.id.tv_select_month);
        btn_next = findViewById(R.id.btn_next);
        listener = new listener_activity_add_wish(this);
        t = new type_or_format_conversion();
        edt_write_wish = findViewById(R.id.edt_write_wish);
        edt_write_money = findViewById(R.id.edt_write_money);
    }

    /**
     * @parameter
     * @description 初始化月份选择器
     * @Time 2019/6/28 22:27
     */
    private void initTimePickerView() {
        //时间选择器
        pvTime = new TimePickerBuilder(activity_make_wish.this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                //选中事件回调
                // 这里回调过来的v,就是show()方法里面所添加的 View 参数，如果show的时候没有添加参数，v则为null
                /*btn_Time.setText(getTime(date));*/
                tv_select_month.setText(t.getTimeMonth(date) + "个月");
            }
        })
                .setType(new boolean[]{false, true, false, false, false, false})    //只显示月份
                //.setTitleSize()               //标题文字大小
                .setTitleText("选择所需的月份数")//标题文字
                .setContentTextSize(20)        //滚轮文字大小
                .setOutSideCancelable(true)    //点击控件外部范围时，取消显示
                .isCyclic(true)                //是否循环滚动
                .isDialog(true)                //是否显示为对话框样式
                .build();
    }

}
