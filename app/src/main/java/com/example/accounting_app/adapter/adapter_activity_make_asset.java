package com.example.accounting_app.adapter;

import android.widget.SimpleAdapter;

import com.example.accounting_app.R;
import com.example.accounting_app.activity.activity_make_asset;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Creator cetwag yuebanquan
 * @Version V2.0.0
 * @Time 2019.6.29
 * @Description 记资产的适配器类
 */
public class adapter_activity_make_asset {

    activity_make_asset activity_ma;//声明对应类的对象,通过构造函数传参传入赋值，否则无法获取对应的控件

    //币种图片源
    private int[] country_icon = {R.drawable.country_circle_cny, R.drawable.country_circle_aud, R.drawable.country_circle_mop,
            R.drawable.country_circle_brl, R.drawable.country_circle_cad, R.drawable.country_circle_dkk, R.drawable.country_circle_eur,
            R.drawable.country_circle_gbp, R.drawable.country_circle_hkd, R.drawable.country_circle_inr, R.drawable.country_circle_khr,
            R.drawable.country_circle_nzd, R.drawable.country_circle_thb, R.drawable.country_circle_usd, R.drawable.country_circle_vnd,
            R.drawable.country_circle_sek};

    //币种文字源
    private String[] country_icon_name = {"人民币", "澳币", "澳门币", "巴西雷亚尔", "加拿大元", "丹麦克朗", "欧元"
            , "英镑", "港币", "印度卢比", "柬埔寨瑞尔", "新西兰元", "泰铢", "美元", "越南盾", "瑞典克朗"};

    private List<Map<String, Object>> dataList;
    private SimpleAdapter adapter; //适配器

    /**
     * @parameter
     * @description 两个有参和无参构造函数来获取对应类的控件
     * @Time 2019/6/29 14:15
     */
    public adapter_activity_make_asset() {
    }

    public adapter_activity_make_asset(activity_make_asset aam) {
        activity_ma = aam;
    }

    /**
     * @parameter
     * @description 适配器具体功能函数
     * @Time 2019/6/29 14:18
     */
    public void adapter_Activity_make_asset() {
        //创建数据源,用于填充选择币种的spinner
        dataList = new ArrayList<Map<String, Object>>();
        //创建简单适配器
        adapter = new SimpleAdapter(activity_ma, getData(), R.layout.spinner_make_asset,
                new String[]{"spinner_image", "spinner_text"}, new int[]{R.id.spi_image, R.id.spi_text});
        //adapter设置一个下拉列表样式，参数为自己定义的子布局
        adapter.setDropDownViewResource(R.layout.spinner_make_asset);
        activity_ma.spinner.setAdapter(adapter); //spinner加载适配器
    }

    /**
     * @parameter
     * @description 设置记资产中选择添加币种的数据源
     * @Time 2019/6/29 14:30
     */
    private List<Map<String, Object>> getData() {
        for (int i = 0; i < country_icon.length; i++) {//循环添加图片文字信息  
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("spinner_image", country_icon[i]);//加入图片
            map.put("spinner_text", country_icon_name[i]);//加入文字
            dataList.add(map);//数据源加入
        }
        return dataList;
    }

}
