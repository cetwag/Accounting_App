package com.example.accounting_app.listener;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.accounting_app.R;
import com.example.accounting_app.activity.activity_make_wish;
import com.example.accounting_app.activity.activity_show_wish;
import com.example.accounting_app.database.Wish;

import java.util.Date;

/**
 * @Creator cetwag yuebanquan
 * @Version V2.0.0
 * @Time 2019.6.29
 * @Description 记一笔心愿的监听类
 */
public class listener_activity_add_wish implements View.OnClickListener {

    activity_make_wish activity_add_wish;

    /**
     * @parameter
     * @description 编写两个有参和午餐构造函数用来获取对应类的控件
     * @Time 2019/6/28 22:37
     */
    public listener_activity_add_wish() {
    }

    public listener_activity_add_wish(activity_make_wish addw) {
        activity_add_wish = addw;
    }

    /**
     * @parameter
     * @description 监听绑定
     * @Time 2019/6/28 22:37
     */
    public void listener_adw() {
        activity_add_wish.tv_select_month.setOnClickListener(this);
        activity_add_wish.btn_next.setOnClickListener(this);
    }

    /**
     * @parameter
     * @description 实现View.OnClickListener接口的函数
     * @Time 2019/6/28 22:35
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_select_month:
                activity_add_wish.pvTime.show();
                break;
            case R.id.btn_next:
                //点击进入显示心愿页面
                save_wish();//将输入的数据存入数据库,并跳转
                break;
        }
    }

    /**
     * @parameter
     * @description 将输入的心愿存入数据库
     * @Time 2019/7/17 1:27
     */
    void save_wish() {
        //心愿名称，不可为空
        //心愿金额，不可为空
        //心愿实现需要的月份，不可为空
        //心愿添加日期，不可为空
        //心愿添加地点，可为空（暂未实现，需借入百度sdk后在进行逻辑编写）
        String wishName = activity_add_wish.edt_write_wish.getText().toString();//获取输入的心愿名称
        String wishMoney = activity_add_wish.edt_write_money.getText().toString();//获取输入的金额
        Date date = new Date();//获取添加心愿的时间
        String wishMonth = activity_add_wish.tv_select_month.getText().toString();//获取需要实现的月份
        if (!TextUtils.isEmpty(wishName)) {//如果心愿名称不为空
            if (!TextUtils.isEmpty(wishMoney)) {//如果心愿金额不为空
                if (!wishMonth.equals("选择所需的月份数▼")) {//如果心愿实现月份不为未选择状态
                    // 先用正则表达式将月份数字提取出来
                    String regex = "[^0123456789]";
                    wishMonth = wishMonth.replaceAll(regex, "");//不满足的地方直接用空代替
                    //提取完后在存入时做个类型转换
                    Wish wish = new Wish();
                    wish.setWishName(wishName);
                    wish.setWishMoney(wishMoney);
                    wish.setWishDate(date);
                    wish.setWishNeedMonth(Integer.parseInt(wishMonth));
                    wish.save();
                    Intent intent_to_show_wish = new Intent(activity_add_wish, activity_show_wish.class);
                    activity_add_wish.startActivity(intent_to_show_wish);
                } else {
                    Toast.makeText(activity_add_wish, "请输入需要实现的月份", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(activity_add_wish, "请输入需要实现的金额", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(activity_add_wish, "请输入需要实现的心愿", Toast.LENGTH_SHORT).show();
        }
    }
}
