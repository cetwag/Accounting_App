package com.example.accounting_app.listener;

import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.accounting_app.R;
import com.example.accounting_app.activity.activity_show_wish;
import com.example.accounting_app.database.Wish;
import com.mcxtzhang.swipemenulib.SwipeMenuLayout;

import org.litepal.LitePal;

import java.util.List;

/**
 * @Creator cetwag yuebanquan
 * @Version V2.0.0
 * @Time 2019.7.18
 * @Description 显示心愿的监听类
 */
public class listener_activity_show_wish {

    activity_show_wish activity_sw;
    int index;//删除按钮的索引


    /**
     * @parameter
     * @description 编写两个有参和无餐构造函数用来获取对应类的控件
     * @Time 2019/6/29 13:00
     */
    public listener_activity_show_wish() {
    }

    public listener_activity_show_wish(activity_show_wish asw) {
        activity_sw = asw;
    }

    /**
     * @parameter
     * @description 遍历数据库动态添加心愿item
     * @Time 2019/7/17 21:44
     */
    public void create_wish_item(List<Wish> list) {

        for (int i = 0; i < list.size(); i++) {

            index += 1;//每创建一个心愿index就+1

            String stringWishName = list.get(i).getWishName();//获取心愿名称
            String stringWishMoney = list.get(i).getWishMoney();//获取心愿金额
            int intWishMonth = list.get(i).getWishNeedMonth();//获取心愿实现月份

            //侧滑删除编写所需布局
            ViewGroup.LayoutParams layoutParams_swipmenu = activity_sw.Swip_menu.getLayoutParams();//找到已有的侧滑菜单布局
            SwipeMenuLayout swipmenu = new SwipeMenuLayout(activity_sw);
            LinearLayout.LayoutParams swipmenu_Params = new LinearLayout.LayoutParams(layoutParams_swipmenu.width, layoutParams_swipmenu.height);
            swipmenu.setLayoutParams(swipmenu_Params);
            //侧滑删除编写完毕

            //侧滑按钮编写
            Button delete = new Button(activity_sw);
            activity_sw.listBtnDel.add(index, delete);
            ViewGroup.LayoutParams layoutParams_delete = activity_sw.btn_delete.getLayoutParams();//找到已有的删除按钮布局
            LinearLayout.LayoutParams delete_Params = new LinearLayout.LayoutParams(layoutParams_delete.width, layoutParams_delete.height);
            delete.setLayoutParams(delete_Params);
            delete.setText("删除");
            delete.setBackgroundColor(activity_sw.getResources().getColor(R.color.red, null));//背景色设置为红色
            delete.setTextSize(18);
            delete.setOnClickListener(new View.OnClickListener() {//删除按钮的监听事件,删除对应的心愿
                @Override
                public void onClick(View v) {
                    delete_btn_wish(v);
                }
            });
            //侧滑按钮编写结束

            //第一层布局编写
            ViewGroup.LayoutParams layoutParams_first = activity_sw.Lin_example_first.getLayoutParams();//获取已有的动态第一层布局
            LinearLayout first = new LinearLayout(activity_sw);//动态创建第一层的布局
            //设置第一层布局的大小等属性
            LinearLayout.LayoutParams first_Params = new LinearLayout.LayoutParams(layoutParams_first.width, layoutParams_first.height);
            first_Params.setMargins(0, 0, 0, 0);//设置边距
            //随机取一个背景
            int pic = (int) (Math.random() * activity_sw.wish_item_pic.length);
            first.setBackgroundResource(activity_sw.wish_item_pic[pic]);//随机取一个背景
            first.setLayoutParams(first_Params);//将上面的大小和边距属性赋给第一层布局
            //第一层布局编写完毕

            //第二层布局编写
            ViewGroup.LayoutParams layoutParams_twice = activity_sw.Lin_example_twice.getLayoutParams();//获取已有的动态第二层布局
            LinearLayout twice = new LinearLayout(activity_sw);//动态创建第二层的布局
            //设置第二层布局的大小等属性
            twice.setOrientation(LinearLayout.VERTICAL);//设置第二层为垂直
            LinearLayout.LayoutParams twice_Params = new LinearLayout.LayoutParams(layoutParams_twice.width, layoutParams_twice.height);
            twice_Params.setMargins(20, 0, 0, 0);//设置边距
            twice.setLayoutParams(twice_Params);//将上面的大小和边距属性赋给第二层布局
            //第二层布局编写完毕

            //心愿名称textview编写
            ViewGroup.LayoutParams layoutParams_wishname = activity_sw.tv_example_wishname.getLayoutParams();//获取已有的心愿名称布局
            TextView wishName = new TextView(activity_sw);//动态创建一个心愿名称textview
            LinearLayout.LayoutParams wishName_Params = new LinearLayout.LayoutParams(layoutParams_wishname.width, layoutParams_wishname.height);
            wishName.setLayoutParams(wishName_Params);
            wishName.setText(stringWishName);
            wishName.setTextColor(activity_sw.getResources().getColor(R.color.white, null));//设置颜色为白色
            wishName.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);//字体加粗
            wishName.setTextSize(20);
            activity_sw.listWishNmae.add(index, stringWishName);
            //心愿名称textview编写完毕

            //心愿金额编写
            ViewGroup.LayoutParams layoutParams_wishMoney = activity_sw.tv_example_wishmoney.getLayoutParams();//获取已有的心愿名称布局
            TextView wishMoney = new TextView(activity_sw);//动态创建一个心愿名称textview
            LinearLayout.LayoutParams wishMoney_Params = new LinearLayout.LayoutParams(layoutParams_wishMoney.width, layoutParams_wishMoney.height);
            wishMoney.setLayoutParams(wishMoney_Params);
            wishMoney.setText("心愿所需金额:" + stringWishMoney);
            wishMoney.setTextColor(activity_sw.getResources().getColor(R.color.white, null));//设置颜色为白色
            wishMoney.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);//字体加粗
            wishMoney.setTextSize(14);
            //心愿金额编写完毕

            //心愿实现月份编写
            ViewGroup.LayoutParams layoutParams_wishMonth = activity_sw.tv_example_wishmonth.getLayoutParams();//获取已有的心愿名称布局
            TextView wishMonth = new TextView(activity_sw);//动态创建一个心愿名称textview
            LinearLayout.LayoutParams wishMonth_Params = new LinearLayout.LayoutParams(layoutParams_wishMonth.width, layoutParams_wishMonth.height);
            wishMonth_Params.gravity = Gravity.CENTER;
            wishMonth.setLayoutParams(wishMonth_Params);
            wishMonth.setText("计划需要" + intWishMonth + "个月实现");
            wishMonth.setTextColor(activity_sw.getResources().getColor(R.color.white, null));//设置颜色为白色
            wishMonth.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);//字体加粗
            wishMonth.setTextSize(17);
            //心愿实现月份编写完毕

            twice.addView(wishName);//将心愿名字加入到第二层布局中
            twice.addView(wishMoney);//将心愿金额加入到第二层布局中
            first.addView(twice);//将第二层加入到第一层中
            first.addView(wishMonth);//将心愿月份加入到第一层布局中
            swipmenu.addView(first);//将第一层加入到侧滑布局中
            swipmenu.addView(delete);//将删除按钮加入侧滑布局
            activity_sw.Lin_show.addView(swipmenu);//将侧滑布局加入到总布局
        }
    }

    /**
     * @parameter 第二个参数为swip布局
     * @description 删除心愿，判断第几个心愿需要被删除
     * @Time 2019/6/30 10:13
     */
    void delete_btn_wish(View v) {
        if (v == null) {
            return;
        }
        for (int i = 0; i <= activity_sw.listBtnDel.size() - 1; i++) {//遍历循环删除按钮list
            if (v.equals(activity_sw.listBtnDel.get(i))) {
                index = i;//确定是第几个删除按钮发生事件
                activity_sw.listBtnDel.remove(index);
                activity_sw.Lin_show.removeViewAt(index);//删除对应的心愿行
                //同时删除数据库里的对应行信息(根据心愿名称删除,控制心愿名称不能重复)
                String wn = activity_sw.listWishNmae.get(index);
                LitePal.deleteAll(Wish.class, "wishName=?", wn);
                activity_sw.listWishNmae.remove(index);
                break;
            }
        }
    }

}
