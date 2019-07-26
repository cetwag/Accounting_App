package com.example.accounting_app.fragment;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
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
import com.mcxtzhang.swipemenulib.SwipeMenuLayout;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import org.litepal.LitePal;
import org.litepal.crud.callback.FindMultiCallback;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;


/**
 * @Creator cetwag, yuebanquan
 * @Version V2.0.0
 * @Time 2019.6.27  2019.7.24
 * @Description 首页模块碎片
 */

public class fragment_home extends Fragment {
    private static final String TAG = "fragment_home";

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
    LinearLayout Lin_second_item;
    SwipeMenuLayout Swip_menu_home;
    Button btn_delete_home;

    LinearLayout Lin_example_item;//示例控件的最外围布局,找到后用汉语隐藏示例item

    TextView tv_net_assets;//净资产
    TextView tv_total_assets;//总资产

    //储存 净资产
    private static BigDecimal netAssets;
    //储存 总资产
    private static BigDecimal totalAssets;
    //储存 总负债(取绝对值)
    private static BigDecimal grossLiability;

    LinkedList<Button> linBtnDel;//删除按钮链表
    LinkedList<String> linStrName;//银行名称
    LinkedList<String> linStrMes;//备注信息
    int index;//删除位置索引

    boolean isViewInitiated; //控件是否初始化完成
    boolean isVisibleToUser; //页面是否可见


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

        isViewInitiated = true;//在控件初始化完了之后，设置为可见标志，用于调用setUser...函数

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
        Img_example_bank = getView().findViewById(R.id.Img_example_bank);
        tv_example_bank_name = getView().findViewById(R.id.tv_example_bank_name);
        tv_example_balance = getView().findViewById(R.id.tv_example_balance);
        tv_example_message = getView().findViewById(R.id.tv_example_message);
        tv_net_assets = getView().findViewById(R.id.tv_net_assets);
        tv_total_assets = getView().findViewById(R.id.tv_all_assets);
        Lin_second_item = getView().findViewById(R.id.Lin_second_item);
        Swip_menu_home = getView().findViewById(R.id.Swip_menu_home);
        btn_delete_home = getView().findViewById(R.id.btn_delete_home);
        Swip_menu_home.setVisibility(View.GONE);//让示例控件不占位置的隐藏
        linBtnDel = new LinkedList<Button>();
        linStrName = new LinkedList<String>();
        linStrMes = new LinkedList<String>();
        linBtnDel.add(0, null);
        linStrName.add(0, null);
        linStrMes.add(0, null);
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
    void create_asset_item(List<AssetAccount> assetAccountList) {
        index = -1; //删除索引初始化

        //注：new BigDecimal的时候需要传入String
        netAssets = new BigDecimal("0.00");          //净资产初始化
        totalAssets = new BigDecimal("0.00");        //总资产初始化
        grossLiability = new BigDecimal("0.00");    //总负债初始化

        for (int i = 0; i < assetAccountList.size(); i++) { //遍历对应表里的数据
            index += 1;//每创建一个资产item项，索引自动加1
            Log.d(TAG, "create_asset_item: index = " + index);

            //从数据库中取数据
            String str_bankName = assetAccountList.get(i).getAssetAccountBankName();    //取出银行名称
            String str_message = assetAccountList.get(i).getAssetAccountType();         //取出备注信息
            BigDecimal bd_money = new BigDecimal(assetAccountList.get(i).getAssetAccountMoney());//取出余额
            Log.d(TAG, "create_asset_item: bd_money = " + bd_money);

            //计算净资产、总资产、总负债
            calculateMoney(bd_money);

            //动态加载布局
            dynamicLoadingLayout(str_bankName, str_message, bd_money);
        }
        //循环完毕后，将计算出的最终的金额结果显示出来
        tv_net_assets.setText("净资产￥" + netAssets);
        tv_total_assets.setText("总资产￥" + totalAssets);
    }

    /**
     * @parameter bd_money  余额
     * @description 计算净资产、总资产、总负债
     * @Time 2019/7/24 10:13
     */
    public void calculateMoney(BigDecimal bd_money) {
        if (bd_money.compareTo(BigDecimal.ZERO) == -1) {   //若余额为负，取绝对值计入总负债中
            //总负债 = 之前的总负债 + |余额|   （总负债中存绝对值）
            grossLiability = grossLiability.add(bd_money.abs());
            Log.d(TAG, "create_asset_item: grossLiability = " + grossLiability);
        } else {    //若余额非负，计入总资产中
            //总资产 = 之前资产 + 余额
            totalAssets = totalAssets.add(bd_money);
            Log.d(TAG, "create_asset_item: totalAssets = " + totalAssets);
        }
        //净资产 = 总资产 - 总负债
        netAssets = totalAssets.subtract(grossLiability);
        Log.d(TAG, "create_asset_item: netAssets = " + netAssets);
    }

    /**
     * @parameter str_bankName  银行名称
     * @parameter str_message   备注信息
     * @parameter bd_money      余额
     * @description 动态加载布局
     * @Time 2019/7/24 11:21
     */
    public void dynamicLoadingLayout(String str_bankName, String str_message, BigDecimal bd_money) {
        //侧滑删除编写所需布局
        ViewGroup.LayoutParams layoutParams_swipmenu = Swip_menu_home.getLayoutParams();//找到已有的侧滑菜单布局
        SwipeMenuLayout swipmenu = new SwipeMenuLayout(getContext());
        swipmenu.setBackgroundResource(R.drawable.line_block);//第一层布局设置为白色带边框背景
        LinearLayout.LayoutParams swipmenu_Params = new LinearLayout.LayoutParams(layoutParams_swipmenu.width, layoutParams_swipmenu.height);
        swipmenu.setLayoutParams(swipmenu_Params);
        //侧滑删除编写完毕

        //动态编写第一层布局，水平方向
        ViewGroup.LayoutParams layoutParams_first = Lin_example_item.getLayoutParams();//获取已有的动态第一层布局
        LinearLayout first = new LinearLayout(getContext());//动态创建第一层的布局
        //设置第一层布局的大小等属性
        LinearLayout.LayoutParams first_Params = new LinearLayout.LayoutParams(layoutParams_first.width, layoutParams_first.height);
        first_Params.setMargins(0, 16, 0, 0);//设置边距
        first.setLayoutParams(first_Params);//将上面的大小和边距属性赋给第一层布局
        //第一层布局编写完毕

        //第二层布局，垂直的放置银行图案和银行文字的布局
        ViewGroup.LayoutParams layoutParams_second = Lin_second_item.getLayoutParams();//获取已有的动态第一层布局
        LinearLayout twice = new LinearLayout(getContext());//动态创建第二层的布局
        //设置第二层布局的大小等属性
        LinearLayout.LayoutParams twice_Params = new LinearLayout.LayoutParams(layoutParams_second.width, layoutParams_second.height);
        twice.setOrientation(LinearLayout.VERTICAL);//设置为垂直方向布局
        twice.setLayoutParams(twice_Params);//将上面的大小和边距属性赋给第二层布局
        //第二层布局编写完毕

        //银行图案编写
        ImageButton bank = new ImageButton(getContext());
        ViewGroup.LayoutParams layoutParams_bank = Img_example_bank.getLayoutParams();//获取已有的银行图案布局
        LinearLayout.LayoutParams bank_Params = new LinearLayout.LayoutParams(layoutParams_bank.width, layoutParams_bank.height);
        bank_Params.gravity = Gravity.CENTER;
        bank.setLayoutParams(bank_Params);//将银行图案加到它自己的布局中
        switch (str_bankName) {
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
        bank_name.setText(str_bankName);//给银行名称设置文字内容
        bank_name.setTextColor(getResources().getColor(R.color.black, null));
        bank_name.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);//字体加粗
        bank_name.setTextSize(11);
        linStrName.add(index, str_bankName);
        //银行名称编写完毕

        //余额文字显示编写
        TextView balance = new TextView(getContext());
        ViewGroup.LayoutParams layoutParams_balance = tv_example_balance.getLayoutParams();//获取已有的余额名称布局
        LinearLayout.LayoutParams balance_Params = new LinearLayout.LayoutParams(layoutParams_balance.width, layoutParams_balance.height);
        balance_Params.gravity = Gravity.CENTER;
        balance.setLayoutParams(balance_Params);//将余额加到它自己的布局中
        balance.setText("￥" + bd_money);//给余额设置文字内容
        balance.setTextColor(getResources().getColor(R.color.black, null));
        balance.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);//字体加粗
        balance.setTextSize(20);
        //余额编写完毕

        //备注信息编写
        TextView message = new TextView(getContext());
        ViewGroup.LayoutParams layoutParams_message = tv_example_balance.getLayoutParams();//获取已有的余额名称布局
        LinearLayout.LayoutParams message_Params = new LinearLayout.LayoutParams(layoutParams_message.width, layoutParams_message.height);
        message_Params.gravity = Gravity.CENTER;
        message.setLayoutParams(message_Params);//将余额加到它自己的布局中
        message.setText(str_message);//给余额设置文字内容
        message.setTextColor(getResources().getColor(R.color.black, null));
        message.setTextSize(15);
        linStrMes.add(index, str_message);
        //备注信息编写完毕

        //侧滑按钮编写
        Button delete = new Button(getContext());
        linBtnDel.add(index, delete);
        ViewGroup.LayoutParams layoutParams_delete = btn_delete_home.getLayoutParams();//找到已有的删除按钮布局
        LinearLayout.LayoutParams delete_Params = new LinearLayout.LayoutParams(layoutParams_delete.width, layoutParams_delete.height);
        delete.setLayoutParams(delete_Params);
        delete.setText("删除");
        delete.setBackgroundColor(getResources().getColor(R.color.red, null));//背景色设置为红色
        delete.setTextSize(18);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete_btn_wish(v);
            }
        });
        //删除按钮编写完毕

        twice.addView(bank);//将银行图案加到第二层布局中
        twice.addView(bank_name);//将银行名称加入到第二层布局中
        first.addView(twice);//将第二层加入到第一层中
        first.addView(balance);//将余额加入到第一层中
        first.addView(message);//将备注信息加入到第一层中
        swipmenu.addView(first);//将第一层加入到侧滑层中
        swipmenu.addView(delete);//将删除按钮加入到侧滑层
        Lin_asset_item.addView(swipmenu);//将侧滑层加入总布局中
    }

    /**
     * @parameter 第二个参数为swip布局
     * @description 删除资产，判断第几个资产item需要被删除
     * @Time 2019/6/30 10:13
     */
    void delete_btn_wish(View v) {
        if (v == null) {
            return;
        }
        for (int i = 0; i <= linBtnDel.size() - 1; i++) {//遍历循环删除按钮list
            if (v.equals(linBtnDel.get(i))) {
                index = i;//确定是第几个删除按钮发生事件
                linBtnDel.remove(index);
                //同时删除数据库里的对应行信息(根据银行名称和备注信息删除,控制心愿名称不能重复)
                String bn = linStrName.get(index); //银行名称
                String bm = linStrMes.get(index); //银行名称
                LitePal.deleteAll(AssetAccount.class, "assetAccountBankName=? " +
                        "and assetAccountType=?", bn, bm);
                Lin_asset_item.removeViewAt(index);//删除对应的资产行
                linStrName.remove(index);
                linStrMes.remove(index);
                double money = LitePal.sum(AssetAccount.class, "AssetAccountMoney", double.class);
                tv_net_assets.setText("净资产￥" + money);
                tv_total_assets.setText("总资产￥" + money);
                break;
            }
        }
    }


    @Override
    public void onResume() {//当重新激活的时候，重新遍历数据库内容并创建资产item
        super.onResume();
        Lin_asset_item.removeAllViews();
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


    /**
     * @parameter
     * @description 判断fragment是否可见的函数
     * @Time 2019/7/20 23:17
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        //调用函数，刷新页面
        refresh();
    }


    void refresh() {
        if (isVisibleToUser && isViewInitiated) {
            Lin_asset_item.removeAllViews();//当暂停的时候就清空所有动态加载的资产item控件
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
    }
}//Fragment类结束
