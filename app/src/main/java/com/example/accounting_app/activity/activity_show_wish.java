package com.example.accounting_app.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.accounting_app.R;
import com.example.accounting_app.database.Wish;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import org.litepal.LitePal;
import org.litepal.crud.callback.FindMultiCallback;

import java.util.List;

/**
 * @Creator cetwag yuebanquan
 * @Version V2.0.0
 * @Time 2019.6.28
 * @Description 显示心愿activity
 */
public class activity_show_wish extends AppCompatActivity {

    LinearLayout Lin_show;//动态添加心愿的总布局
    LinearLayout Lin_example_first;//每个item的第一层布局
    LinearLayout Lin_example_twice;//每个item的第二层布局(最左边的垂直布局)
    TextView tv_example_wishname;//示例心愿名称
    TextView tv_example_wishmoney;//示例心愿金额
    TextView tv_example_wishmonth;//示例心愿月份
    int[] wish_item_pic;//心愿项背景图


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
                create_wish_item(list);
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
        Lin_example_first.setVisibility(View.GONE);//将示例设置为不占空间的隐藏
    }

    /**
     * @parameter
     * @description 遍历数据库动态添加心愿item
     * @Time 2019/7/17 21:44
     */
    void create_wish_item(List<Wish> list) {

        for (int i = 0; i < list.size(); i++) {
            String stringWishName = list.get(i).getWishName();//获取心愿名称
            String stringWishMoney = list.get(i).getWishMoney();//获取心愿金额
            int intWishMonth = list.get(i).getWishNeedMonth();//获取心愿实现月份

            //第一层布局编写
            ViewGroup.LayoutParams layoutParams_first = Lin_example_first.getLayoutParams();//获取已有的动态第一层布局
            LinearLayout first = new LinearLayout(this);//动态创建第一层的布局
            //设置第一层布局的大小等属性
            LinearLayout.LayoutParams first_Params = new LinearLayout.LayoutParams(layoutParams_first.width, layoutParams_first.height);
            first_Params.setMargins(0, 0, 0, 0);//设置边距
            //随机取一个背景
            int pic = (int) (Math.random() * wish_item_pic.length);
            first.setBackgroundResource(wish_item_pic[pic]);//随机取一个背景
            first.setLayoutParams(first_Params);//将上面的大小和边距属性赋给第一层布局
            //第一层布局编写完毕

            //第二层布局编写
            ViewGroup.LayoutParams layoutParams_twice = Lin_example_twice.getLayoutParams();//获取已有的动态第二层布局
            LinearLayout twice = new LinearLayout(this);//动态创建第二层的布局
            //设置第二层布局的大小等属性
            twice.setOrientation(LinearLayout.VERTICAL);//设置第二层为垂直
            LinearLayout.LayoutParams twice_Params = new LinearLayout.LayoutParams(layoutParams_twice.width, layoutParams_twice.height);
            twice_Params.setMargins(20, 0, 0, 0);//设置边距
            twice.setLayoutParams(twice_Params);//将上面的大小和边距属性赋给第二层布局
            //第二层布局编写完毕

            //心愿名称textview编写
            ViewGroup.LayoutParams layoutParams_wishname = tv_example_wishname.getLayoutParams();//获取已有的心愿名称布局
            TextView wishName = new TextView(this);//动态创建一个心愿名称textview
            LinearLayout.LayoutParams wishName_Params = new LinearLayout.LayoutParams(layoutParams_wishname.width, layoutParams_wishname.height);
            wishName.setLayoutParams(wishName_Params);
            wishName.setText(stringWishName);
            wishName.setTextColor(getResources().getColor(R.color.white, null));//设置颜色为白色
            wishName.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);//字体加粗
            wishName.setTextSize(20);
            //心愿名称textview编写完毕

            //心愿金额编写
            ViewGroup.LayoutParams layoutParams_wishMoney = tv_example_wishmoney.getLayoutParams();//获取已有的心愿名称布局
            TextView wishMoney = new TextView(this);//动态创建一个心愿名称textview
            LinearLayout.LayoutParams wishMoney_Params = new LinearLayout.LayoutParams(layoutParams_wishMoney.width, layoutParams_wishMoney.height);
            wishMoney.setLayoutParams(wishMoney_Params);
            wishMoney.setText("心愿所需金额:"+stringWishMoney);
            wishMoney.setTextColor(getResources().getColor(R.color.white, null));//设置颜色为白色
            wishMoney.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);//字体加粗
            wishMoney.setTextSize(14);
            //心愿金额编写完毕

            //心愿实现月份编写
            ViewGroup.LayoutParams layoutParams_wishMonth = tv_example_wishmonth.getLayoutParams();//获取已有的心愿名称布局
            TextView wishMonth = new TextView(this);//动态创建一个心愿名称textview
            LinearLayout.LayoutParams wishMonth_Params = new LinearLayout.LayoutParams(layoutParams_wishMonth.width, layoutParams_wishMonth.height);
            wishMonth_Params.gravity = Gravity.CENTER;
            wishMonth.setLayoutParams(wishMonth_Params);
            wishMonth.setText("计划需要"+intWishMonth+"个月实现");
            wishMonth.setTextColor(getResources().getColor(R.color.white, null));//设置颜色为白色
            wishMonth.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);//字体加粗
            wishMonth.setTextSize(17);
            //心愿实现月份编写完毕

            twice.addView(wishName);//将心愿名字加入到第二层布局中
            twice.addView(wishMoney);//将心愿金额加入到第二层布局中
            first.addView(twice);//将第二层加入到第一层中
            first.addView(wishMonth);//将心愿月份加入到第一层布局中
            Lin_show.addView(first);//将第一层加入到总布局
        }
    }
}
