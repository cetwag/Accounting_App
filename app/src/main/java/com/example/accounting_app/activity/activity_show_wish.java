package com.example.accounting_app.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.accounting_app.R;
import com.example.accounting_app.database.Wish;
import com.example.accounting_app.listener.listener_activity_show_wish;
import com.mcxtzhang.swipemenulib.SwipeMenuLayout;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import org.litepal.LitePal;
import org.litepal.crud.callback.FindMultiCallback;

import java.util.LinkedList;
import java.util.List;

/**
 * @Creator cetwag yuebanquan
 * @Version V2.0.0
 * @Time 2019.6.28
 * @Description 显示心愿activity
 */
public class activity_show_wish extends AppCompatActivity {

    public LinearLayout Lin_show;//动态添加心愿的总布局
    public LinearLayout Lin_example_first;//每个item的第一层布局
    public LinearLayout Lin_example_twice;//每个item的第二层布局(最左边的垂直布局)
    public TextView tv_example_wishname;//示例心愿名称
    public TextView tv_example_wishmoney;//示例心愿金额
    public TextView tv_example_wishmonth;//示例心愿月份
    public int[] wish_item_pic;//心愿项背景图
    public SwipeMenuLayout Swip_menu;//侧滑菜单布局
    public Button btn_delete;
    public LinkedList<Button> listBtnDel;//删除按钮的链表
    public LinkedList<String> listWishNmae;//心愿名称的链表，用于根据名称删除对应数据库中的内容
    public listener_activity_show_wish listener;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_wish);

        //屏幕适配
        ScreenAdapterTools.getInstance().reset(this);//如果希望android7.0分屏也适配的话,加上这句
        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        //初始化
        init();


        /**
         * 异步取数据库中的数据,并创建资产项
         */
        LitePal.findAllAsync(Wish.class).listen(new FindMultiCallback<Wish>() {
            @Override
            public void onFinish(List<Wish> list) {
                list = LitePal.findAll(Wish.class);//找到所有数据
                listener.create_wish_item(list);
            }
        });
    }

    /**
     * @parameter
     * @description 控件及相关初始化
     * @Time 2019/7/17 21:48
     */
    void init() {
        Lin_show = findViewById(R.id.Lin_show);
        Lin_example_first = findViewById(R.id.Lin_example_first);
        Lin_example_twice = findViewById(R.id.Lin_example_twice);
        tv_example_wishname = findViewById(R.id.tv_example_wishname);
        tv_example_wishmoney = findViewById(R.id.tv_example_wishmoney);
        tv_example_wishmonth = findViewById(R.id.tv_example_wishmonth);
        wish_item_pic = new int[]{R.drawable.background_wish_item1, R.drawable.background_wish_item2,
                R.drawable.background_wish_item3, R.drawable.background_wish_item4,
                R.drawable.background_wish_item5};
        Swip_menu = findViewById(R.id.Swip_menu);
        btn_delete = findViewById(R.id.btn_delete);
        Swip_menu.setVisibility(View.GONE);//将示例设置为不占空间的隐藏
        listBtnDel = new LinkedList<Button>();
        listBtnDel.add(0, null);//链表第一个先放一个null
        listener = new listener_activity_show_wish(this);
        listWishNmae = new LinkedList<String>();
        listWishNmae.add(0, null);//链表第一个先放个null
    }


}
