package com.example.accounting_app.listener;

import android.view.Gravity;
import android.view.View;

import com.example.accounting_app.R;
import com.example.accounting_app.fragment.fragment_bill;
import com.example.accounting_app.function.Calendar_dialog_Fragment;

/**
 * @Creator cetwag yuebanquan
 * @Version V2.0.0
 * @Time 2019.6.28
 * @Description 账单fragment的监听类
 */
public class listener_fragment_bill implements View.OnClickListener {

    fragment_bill fragment_bill;//声明对应类的对象,通过构造函数传参传入赋值，否则无法获取对应的控件

    /**
     * @parameter
     * @description 编写两个有参和无参构造函数，用于获取对应类的控件
     * @Time 2019/6/28 21:09
     */
    public listener_fragment_bill() {
    }

    public listener_fragment_bill(fragment_bill fb) {
        fragment_bill = fb;
    }

    /**
     * @parameter
     * @description 监听绑定
     * @Time 2019/6/28 21:13
     */
    public void listener_fb() {
        fragment_bill.tv_screen.setOnClickListener(this);
        fragment_bill.tv_time_selected.setOnClickListener(this);
        fragment_bill.btn_reset.setOnClickListener(this);
    }


    /**
     * @parameter
     * @description 实现view.OnClickListener接口去编写监听事件
     * @Time 2019/6/28 21:41
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_screen:
                //openDrawer()函数用来打开侧滑菜单,Gravity.START表示布局文件里设置的滑出方向
                fragment_bill.drawerlayout.openDrawer(Gravity.END);
                break;
            case R.id.tv_time_selected:
                //点击打开日历选择框
                Calendar_dialog_Fragment calendar_dialog_fragment = new Calendar_dialog_Fragment().getInstance(fragment_bill.FRAGMENT_BILL_SELECT_TIME);
                if (calendar_dialog_fragment != null) {
                    calendar_dialog_fragment.show(fragment_bill.getFragmentManager(), "选择日期");
                }
                break;
            case R.id.btn_reset:
                for(int i=0;i<fragment_bill.checkboxes.length;i++) {
                    fragment_bill.checkboxes[i].setChecked(false);
                }
                fragment_bill.rdg_one.clearCheck();
                break;
        }
    }
}
