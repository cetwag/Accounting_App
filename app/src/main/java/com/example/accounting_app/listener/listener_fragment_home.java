package com.example.accounting_app.listener;

import android.app.Dialog;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;

import com.example.accounting_app.R;
import com.example.accounting_app.activity.activity_home_login;
import com.example.accounting_app.activity.activity_house_rate;
import com.example.accounting_app.activity.activity_home_register;
import com.example.accounting_app.fragment.fragment_home;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

/**
 * @Creator cetwag yuebanquan
 * @Version V2.0.0
 * @Time 2019.6.28
 * @Description 首页fragment的监听类
 */
public class listener_fragment_home implements View.OnClickListener {

    fragment_home frag_h;//声明对应类的对象,通过构造函数传参传入赋值，否则无法获取对应的控件


    /**
     * @parameter
     * @description 编写两个有参和无参构造函数，用于获取对应类的控件
     * @Time 2019/6/28 9:02
     */
    public listener_fragment_home() {
    }

    public listener_fragment_home(fragment_home fh) {
        frag_h = fh;
    }

    /**
     * @parameter
     * @description 首页碎片的侧滑菜单的动态控件点击显示事件
     * @Time 2019/6/28 9:12
     */
    public void listener_hf() {
        frag_h.btn_slide.setOnClickListener(this);
        frag_h.btn_login.setOnClickListener(this);
        frag_h.btn_register.setOnClickListener(this);
    }

    /**
     * @parameter
     * @description 实现View.onClickListener接口, 可以采用switch case简化setOnClickListener
     * @Time 2019/6/28 13:10
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_slide:
                //openDrawer()函数用来打开侧滑菜单,Gravity.START表示布局文件里设置的滑出方向
                frag_h.drawerlayout.openDrawer(Gravity.START);
                break;
            case R.id.my_create_btn_login:
                Intent intent_login = new Intent(frag_h.getContext(), activity_home_login.class);
                frag_h.startActivity(intent_login);
                //startActivityForResult(intent,TOLOG);
                break;
            case R.id.my_create_btn_register:
                Intent intent_register = new Intent(frag_h.getContext(), activity_home_register.class);
                frag_h.startActivity(intent_register);
                break;
        }
    }

    /**
     * @parameter
     * @description 首页碎片的侧滑菜单的列表控件点击显示事件
     * @Time 2019/6/28 14:47
     */
    public void listener_hf_lv_item() {
        //对listview里的item设置监听事件
        frag_h.lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch ((int) id) {
                    case 3://汇率计算器弹窗编写
                        DisplayMetrics dm=new DisplayMetrics();
                        frag_h.getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
                        int width =dm.widthPixels;//获取屏幕宽度
                        int height = dm.heightPixels;//获取屏幕高度
                        Dialog dialog = new Dialog(frag_h.getContext());
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        LayoutInflater inflater = LayoutInflater.from(frag_h.getContext());
                        View v = inflater.inflate(R.layout.dialog_exchange_rate, null);//弹窗视图在这里
                        ScreenAdapterTools.getInstance().loadView(v);
                        dialog.setContentView(v);
                        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                        Window window = dialog.getWindow();
                        lp.copyFrom(window.getAttributes());
                        lp.width = width;
                        lp.height = height/2;
                        lp.gravity = Gravity.CENTER;
                        dialog.show();
                        window.setAttributes(lp);
                        break;
                    case 4://跳转到房贷计算器
                        Intent intent = new Intent(frag_h.getContext(), activity_house_rate.class);
                        frag_h.startActivity(intent);
                        break;
                }
            }
        });
    }
}
