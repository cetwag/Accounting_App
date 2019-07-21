package com.example.accounting_app.activity;

import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;

import com.example.accounting_app.R;
import com.example.accounting_app.adapter.adapter_mainactivity;
import com.example.accounting_app.database.AssetAccount;
import com.example.accounting_app.database.Classify;
import com.example.accounting_app.fragment.fragment_bill;
import com.example.accounting_app.fragment.fragment_home;
import com.example.accounting_app.fragment.fragment_statements;
import com.example.accounting_app.fragment.fragment_wish;
import com.example.accounting_app.function.CustomViewPager;
import com.example.accounting_app.listener.listener_mainactivity;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import org.litepal.LitePal;
import org.litepal.tablemanager.Connector;

import java.util.ArrayList;
import java.util.List;


/**
 * @Creator cetwag, yuebanquan
 * @Version V2.0.0
 * @Time 2019.6.27
 * @Description 底部导航栏对碎片控制的切换
 */


public class MainActivity extends AppCompatActivity {

    //下面创建三个华滑动切换四个Fragment碎片必要的组件
    public CustomViewPager viewpager;
    public ArrayList<Fragment> pages = new ArrayList<>();

    //声明底部导航栏的五个选择按钮
    public RadioButton rdb_home;
    public RadioButton rdb_bill;
    public RadioButton rdb_wish;
    public RadioButton rdb_statements;
    public RadioButton rdb_add;

    //本类的监听器和适配器类对象
    listener_mainactivity listener;
    adapter_mainactivity adapter;

    //支出类别名
    String[] typeOut = {"餐饮", "旅行", "购物", "交通", "通讯",
            "医疗", "住房", "育儿", "文教", "娱乐", "宠物", "生活"};
    //收入类别名
    String[] typeIn = {"奖金", "工资", "投资收益", "报销", "借入",
            "投资回收", "收债", "红包"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //屏幕适配
        ScreenAdapterTools.getInstance().reset(this);//如果希望android7.0分屏也适配的话,加上这句
        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        //找到声明的控件
        init();

        //该类对应的监听器
        listener.listener_MainActivity_vp();
        listener.listener_MainActivity_rdb();

        //该类对应的适配器
        adapter.adapter_MainActivity();

        initDb();   //数据库初始化,建表

//        testDb();   //测试数据库
    }

    /**
     * @parameter
     * @description findViewById等控件的初始化
     * @Time 2019/6/27 14:27
     */
    public void init() {
        viewpager = findViewById(R.id.ViewPager);
        rdb_home = findViewById(R.id.rdb_home);
        rdb_bill = findViewById(R.id.rdb_bill);
        rdb_wish = findViewById(R.id.rdb_wish);
        rdb_statements = findViewById(R.id.rdb_statements);
        rdb_add = findViewById(R.id.rdb_add);
        pages.add(new fragment_home());
        pages.add(new fragment_bill());
        pages.add(new fragment_wish());
        pages.add(new fragment_statements());
        listener = new listener_mainactivity(this);
        adapter = new adapter_mainactivity(this);
        //因为滑动监听初始化的时候不生效,所以第一次viewpager的时候加入一次限定
        viewpager.setAllowedSwipeDirection(CustomViewPager.SwipeDirection.right);
    }

    /**
     * @parameter
     * @description 数据库的初始化
     * @Time 2019/7/12 16:00
     */
    public void initDb() {
        SQLiteDatabase db = Connector.getDatabase();
        //初始化类别表里的信息
        if (classifyIsNull()) {
            for (int i = 0; i <= typeOut.length - 1; i++) {
                Classify classify = new Classify();
                classify.setClassifyName(typeOut[i]);
                classify.setClassifyType(0);//支出
                classify.save();
            }
            for (int i = 0; i <= typeIn.length - 1; i++) {
                Classify classify = new Classify();
                classify.setClassifyName(typeIn[i]);
                classify.setClassifyType(1);//收入
                classify.save();
            }
        }
    }

    /**
     * 判断Classify表中有无数据
     */
    private Boolean classifyIsNull() {
        List<Classify> classify = LitePal.findAll(Classify.class);//查询所有值，返回的使一个list集合
        if (classify.size() == 0) {//判断，如果返回集合的大小为0就说明还没有数据
            return true;
        } else {//否则说明有数据
            return false;
        }
    }

    /**
     * @parameter
     * @description 测试数据库
     * @Time 2019/7/12 20:35
     */
//    public void testDb() {
//        AssetAccount assetAccount = new AssetAccount();
//        assetAccount.setAssetAccountType(0);
//        assetAccount.setAssetAccountBankName("中国银行");
//        assetAccount.setAssetAccountCardNum("6222621310030964817");
////        assetAccount.setAssetAccountMoney("10.0");
//        assetAccount.save();
//    }


}
