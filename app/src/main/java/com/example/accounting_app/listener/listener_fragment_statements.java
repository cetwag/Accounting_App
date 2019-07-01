package com.example.accounting_app.listener;

import android.view.View;

import com.example.accounting_app.R;
import com.example.accounting_app.fragment.fragment_statements;
import com.example.accounting_app.function.Calendar_dialog_Fragment;

/**
 * @Creator cetwag yuebanquan
 * @Version V2.0.0
 * @Time 2019.6.29
 * @Description 报表碎片的监听类
 */
public class listener_fragment_statements implements View.OnClickListener {

    fragment_statements frag_s;//对应类的对象

    /**
     * @parameter
     * @description 写两个有参和无参构造函数，用于获取对应类中的控件
     * @Time 2019/6/29 21:24
     */
    public listener_fragment_statements() {
    }

    public listener_fragment_statements(fragment_statements fs) {
        frag_s = fs;
    }

    /**
     * @parameter
     * @description
     * @Time 2019/6/29 21:26
     */
    public void listener_Fragment_statements() {
        frag_s.tv_time_selected.setOnClickListener(this);
    }


    /**
     * @parameter
     * @description 实现View.OnClickListener接口的监听函数
     * @Time 2019/6/29 21:23
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_time_selected:
                //点击打开日历选择框
                Calendar_dialog_Fragment calendar_dialog_fragment = new Calendar_dialog_Fragment().getInstance(frag_s.FRAGMENT_STATEMENTS_SELECT_TIME);
                if (calendar_dialog_fragment != null) {
                    calendar_dialog_fragment.show(frag_s.getFragmentManager(), "选择日期");
                }
                break;
        }
    }
}
