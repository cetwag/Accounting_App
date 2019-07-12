package com.example.accounting_app.fragment;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.accounting_app.R;
import com.example.accounting_app.adapter.adapter_fragment_home;
import com.example.accounting_app.database.AssetAccount;
import com.example.accounting_app.listener.listener_fragment_home;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import org.litepal.LitePal;
import org.litepal.crud.callback.FindMultiCallback;

import java.util.List;


/**
 * @Creator cetwag, yuebanquan
 * @Version V2.0.0
 * @Time 2019.6.27
 * @Description 首页模块碎片
 */

public class fragment_home extends Fragment {

    public Button btn_slide;
    public DrawerLayout drawerlayout;

    //侧滑菜单相关
    public ListView lv;//声明侧滑菜单的列表控件
    public ArrayAdapter<String> adapter;//声明一个字符串型的数组适配器
    private LinearLayout layout_second;//汉堡菜单里的侧滑布局
    public Button btn_login, btn_register;

    //该碎片的监听和适配器类
    listener_fragment_home listener_fh;
    adapter_fragment_home adapter_fh;

    LinearLayout Lin_asset_item;//动态生成资产项的最外层总布局
    //隐藏一个资产项例子，用来做屏幕适配的依据
    ImageView Img_example_bank;
    TextView tv_example_bank_name;
    TextView tv_example_balance;
    TextView tv_example_message;

    LinearLayout Lin_example_item;//示例控件的最外围布局,找到后用汉语隐藏示例item

    TextView tv_net_assets;//净资产
    TextView tv_all_assets;//总资产


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //inflater使将xml布局文件转换为视图的一个类,container表示在container里面显示这个视图
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //屏幕适配
        ScreenAdapterTools.getInstance().loadView(view);

        return view;
    }

    /**
     * @parameter
     * @description 根据fragment的生命周期，onActivityCreated在onCreateView后执行
     * @Time 2019/6/28 9:30
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //控件的初始化
        init();

        //首页碎片的监听
        listener_fh.listener_hf();

        //首页碎片侧滑菜单列表项的监听
        listener_fh.listener_hf_lv_item();

        //首页碎片的侧滑栏适配器
        adapter_fh.adapter_Frgment_Home();

        change_state_unlog();//未登录状态
        //change_state_haveloged();//登录状态


    }


    /**
     * @parameter
     * @description view控件的初始化
     * @Time 2019/6/28 9:49
     */
    void init() {

        btn_slide = getView().findViewById(R.id.btn_slide);
        drawerlayout = getView().findViewById(R.id.DrawerLayout);
        lv = getView().findViewById(R.id.lv_slide_menu);
        layout_second = getView().findViewById(R.id.Lin_slide);
        listener_fh = new listener_fragment_home(this);
        adapter_fh = new adapter_fragment_home(this);
        btn_login = new Button(getContext());
        btn_register = new Button(getContext());
        Lin_asset_item = getView().findViewById(R.id.Lin_asset_item);
        Lin_example_item = getView().findViewById(R.id.Lin_example_item);
        Lin_example_item.setVisibility(View.GONE);//让示例控件不占位置的隐藏
        Img_example_bank = getView().findViewById(R.id.Img_example_bank);
        tv_example_bank_name = getView().findViewById(R.id.tv_example_bank_name);
        tv_example_balance = getView().findViewById(R.id.tv_example_balance);
        tv_example_message = getView().findViewById(R.id.tv_example_message);
        tv_net_assets = getView().findViewById(R.id.tv_net_assets);
        tv_all_assets = getView().findViewById(R.id.tv_all_assets);

    }

    /**
     * @parameter
     * @description 未登录状态的动态生成控件
     * @Time 2019/6/28 11:23
     */
    public void change_state_unlog() {
        //头像图片编写
        ImageView head_icon = new ImageView(getContext());
        head_icon.setBackgroundResource(R.drawable.head_icon);
        LinearLayout.LayoutParams head_icon_Params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        head_icon_Params.weight = 1;//设置权重
        head_icon.setLayoutParams(head_icon_Params);
        //头像图片编写完毕

        //登录按钮编写
        btn_login.setId(R.id.my_create_btn_login);
        btn_login.setBackgroundResource(R.drawable.line_block);
        btn_login.setText("登录");
        btn_login.setTextSize(16);
        btn_login.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);//加粗
        LinearLayout.LayoutParams login_Params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        login_Params.weight = 1;//设置权重
        btn_login.setLayoutParams(login_Params);
        //登录按钮编写完毕

        //注册按钮编写
        btn_register.setId(R.id.my_create_btn_register);
        btn_register.setBackgroundResource(R.drawable.line_block);
        btn_register.setText("注册");
        btn_register.setTextSize(16);
        btn_register.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);//加粗
        LinearLayout.LayoutParams register_Params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        register_Params.weight = 1;//设置权重
        btn_register.setLayoutParams(register_Params);
        //注册按钮编写完毕

        layout_second.addView(head_icon);//将头像图片加入布局
        layout_second.addView(btn_login);//将登录按钮加入布局
        layout_second.addView(btn_register);//将注册按钮加入布局
    }

    /**
     * @parameter
     * @description 动态生成控件
     * 首页fragment侧滑菜单的顶部登录注册模块的登录状态编写(图片变更,信息变更)
     */
    public void change_state_haveloged() {
        //头像图片编写
        ImageView head_icon = new ImageView(getContext());
        head_icon.setBackgroundResource(R.drawable.head_icon);
        LinearLayout.LayoutParams head_icon_Params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        head_icon_Params.weight = 1;//设置权重
        head_icon.setLayoutParams(head_icon_Params);
        //头像图片编写完毕


        //用户名TextView编写
        TextView user_name = new TextView(getContext());
        user_name.setText("111");//用static偷机取巧的做法！
        user_name.setTextSize(16);
        user_name.setTextColor(Color.BLACK);//黑色字体
        user_name.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);//加粗
        LinearLayout.LayoutParams user_name_Params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        user_name_Params.setMargins(0, 40, 0, 0);
        user_name_Params.weight = 1;//设置权重
        user_name.setLayoutParams(user_name_Params);
        //用户名TextView编写完毕

        //注册按钮编写
        Button sign_out = new Button(getContext());
        sign_out.setBackgroundResource(R.drawable.line_block);
        sign_out.setText("退出");
        sign_out.setTextSize(16);
        sign_out.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);//加粗
        LinearLayout.LayoutParams sign_out_Params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        sign_out_Params.weight = 1;//设置权重
        sign_out.setLayoutParams(sign_out_Params);
        sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_second.removeAllViews();
                //change_state_unlog();//回到未登录状态
            }
        });
        //注册按钮编写完毕

        layout_second.addView(head_icon);//将头像图片加入布局
        layout_second.addView(user_name);//将登录按钮加入布局
        layout_second.addView(sign_out);//将注册按钮加入布局
    }


    /**
     * @parameter
     * @description 遍历数据库创建资产列表项
     * @Time 2019/7/12 22:29
     */
    void create_asset_item(List<AssetAccount> list) {
        double own_asset_number = 0;//净资产初始化
        double all_asset_number = 0;//总资产初始化
        for (int i = 0; i < list.size(); i++) {//遍历对应表里的数据
            String BankName = list.get(i).getAssetAccountBankName();//取出银行名称
            String Balance = list.get(i).getAssetAccountMoney();//取出余额
            String Message = list.get(i).getAssetAccountType();//取出备注信息

            //先对净资产和总资产的数字进行更改显示
            own_asset_number += Double.parseDouble(Balance);
            all_asset_number += Double.parseDouble(Balance);

            //动态编写第一层布局，水平方向
            LinearLayout first = new LinearLayout(getContext());//动态创建第一层的布局
            //设置第一层布局的大小等属性
            LinearLayout.LayoutParams first_Params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 160);
            first_Params.setMargins(0, 16, 0, 0);//设置边距
            first.setBackgroundResource(R.drawable.line_block);//第一层布局设置为白色带边框背景
            first.setLayoutParams(first_Params);//将上面的大小和边距属性赋给第一层布局
            //第一层布局编写完毕

            //第二层布局，垂直的放置银行图案和银行文字的布局
            LinearLayout twice = new LinearLayout(getContext());//动态创建第二层的布局
            //设置第二层布局的大小等属性
            LinearLayout.LayoutParams twice_Params = new LinearLayout.LayoutParams(185, ViewGroup.LayoutParams.MATCH_PARENT);
            twice.setOrientation(LinearLayout.VERTICAL);//设置为垂直方向布局
            twice.setLayoutParams(twice_Params);//将上面的大小和边距属性赋给第二层布局
            //第二层布局编写完毕

            //银行图案编写
            ImageButton bank = new ImageButton(getContext());
            ViewGroup.LayoutParams layoutParams_bank = Img_example_bank.getLayoutParams();//获取已有的银行图案布局
            LinearLayout.LayoutParams bank_Params = new LinearLayout.LayoutParams(layoutParams_bank.width, layoutParams_bank.height);
            bank_Params.gravity = Gravity.CENTER;
            bank.setLayoutParams(bank_Params);//将银行图案加到它自己的布局中
            switch (BankName) {
                case "交通银行":
                    bank.setBackgroundResource(R.drawable.bank_bcm);//给银行图案引用资源图片
                    break;
                case "工商银行":
                    bank.setBackgroundResource(R.drawable.bank_icbc);//给ImageView加上图片资源
                    break;
                case "招商银行":
                    bank.setBackgroundResource(R.drawable.bank_cmb);//给ImageView加上图片资源
                    break;
                case "中国银行":
                    bank.setBackgroundResource(R.drawable.bank_boc);//给ImageView加上图片资源
                    break;
                case "农业银行":
                    bank.setBackgroundResource(R.drawable.bank_abc);//给ImageView加上图片资源
                    break;
                case "浦发银行":
                    bank.setBackgroundResource(R.drawable.bank_spdb);//给ImageView加上图片资源
                    break;
                case "建设银行":
                    bank.setBackgroundResource(R.drawable.bank_ccb);//给ImageView加上图片资源
                    break;
                case "微信":
                    bank.setBackgroundResource(R.drawable.bank_wechat);//给ImageView加上图片资源
                    break;
                case "支付宝":
                    bank.setBackgroundResource(R.drawable.bank_alipay);//给ImageView加上图片资源
                    break;
            }
            //银行按钮编写完毕

            //银行名称编写
            TextView bank_name = new TextView(getContext());
            ViewGroup.LayoutParams layoutParams_bank_name = tv_example_bank_name.getLayoutParams();//获取已有的银行名称布局
            LinearLayout.LayoutParams bank_name_Params = new LinearLayout.LayoutParams(layoutParams_bank_name.width, layoutParams_bank_name.height);
            bank_name_Params.gravity = Gravity.CENTER;
            bank_name.setLayoutParams(bank_name_Params);//将银行名称加到它自己的布局中
            bank_name.setText(BankName);//给银行名称设置文字内容
            bank_name.setTextColor(getResources().getColor(R.color.black));
            bank_name.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);//字体加粗
            bank_name.setTextSize(11);
            //银行名称编写完毕

            //余额文字显示编写
            TextView balance = new TextView(getContext());
            ViewGroup.LayoutParams layoutParams_balance = tv_example_balance.getLayoutParams();//获取已有的余额名称布局
            LinearLayout.LayoutParams balance_Params = new LinearLayout.LayoutParams(layoutParams_balance.width, layoutParams_balance.height);
            balance_Params.gravity = Gravity.CENTER;
            balance.setLayoutParams(balance_Params);//将余额加到它自己的布局中
            balance.setText("￥" + Balance);//给余额设置文字内容
            balance.setTextColor(getResources().getColor(R.color.black));
            balance.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);//字体加粗
            balance.setTextSize(20);
            //余额编写完毕

            //备注信息编写
            TextView message = new TextView(getContext());
            ViewGroup.LayoutParams layoutParams_message = tv_example_balance.getLayoutParams();//获取已有的余额名称布局
            LinearLayout.LayoutParams message_Params = new LinearLayout.LayoutParams(layoutParams_message.width, layoutParams_message.height);
            message_Params.gravity = Gravity.CENTER;
            message.setLayoutParams(message_Params);//将余额加到它自己的布局中
            message.setText(Message);//给余额设置文字内容
            message.setTextColor(getResources().getColor(R.color.black));
            message.setTextSize(15);
            //备注信息编写完毕

            twice.addView(bank);//将银行图案加到第二层布局中
            twice.addView(bank_name);//将银行名称加入到第二层布局中
            first.addView(twice);//将第二层加入到第一层中
            first.addView(balance);//将余额加入到第一层中
            first.addView(message);//将备注信息加入到第一层中
            Lin_asset_item.addView(first);//第一层加入总布局中
        }
        //循环完毕后，将计算出的最终的金额结果显示出来
        tv_net_assets.setText("净资产￥"+own_asset_number);
        tv_all_assets.setText("总资产￥"+all_asset_number);
    }

    @Override
    public void onPause() {
        super.onPause();
        Lin_asset_item.removeAllViews();//当暂停的时候就清空所有动态加载的资产item控件
    }

    @Override
    public void onResume() {//当重新激活的时候，重新遍历数据库内容并创建资产item
        super.onResume();
        /**
         * 异步取数据库中的数据,并创建资产项
         */
        LitePal.findAllAsync(AssetAccount.class).listen(new FindMultiCallback<AssetAccount>() {
            @Override
            public void onFinish(List<AssetAccount> list) {
                list = LitePal.findAll(AssetAccount.class);//找到所有数据
                create_asset_item(list);
            }
        });
    }
}//Fragment类结束
