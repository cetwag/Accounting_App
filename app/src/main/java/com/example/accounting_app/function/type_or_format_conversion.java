package com.example.accounting_app.function;

import android.view.View;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Creator cetwag yuebanquan
 * @Version V2.0.0
 * @Time 2019.6.28
 * @Description 这个类包含了数据间的类型转换函数以及日期的提取格式等相关函数
 */
public class type_or_format_conversion {

    /**
     * @parameter date传入的Date型日期
     * @description 将传入的Date型日期数据转换为字符串, 只取月份
     * @Time 2019/6/28 22:32
     */
    public String getTimeMonth(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("M");
        return format.format(date);
    }

    /**
     * @parameter date传入的Date型日期
     * @description 将传入的Date型日期数据转换为字符串并格式化，年月日都取
     * @Time 2019/6/29 11:14
     */
    public String getTimeYMD(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    /**
     * @parameter date传入的Date型日期
     * @description 将传入的Date型日期数据转换为字符串并格式化，年月日时分秒都取
     * @Time 2019/6/29 11:14
     */
    public String getTimeYMDhhmmss(Date date) {
        //这里有个问题，取出的时分秒不一和电脑的不一致，但比较大小够用了，后期更改！
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

}
