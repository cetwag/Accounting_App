package com.example.accounting_app.fragment;


import android.content.ContentValues;
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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;


import com.example.accounting_app.R;
import com.example.accounting_app.database.AssetAccount;
import com.example.accounting_app.database.Classify;
import com.example.accounting_app.database.Tally;
import com.example.accounting_app.listener.listener_fragment_bill;
import com.mcxtzhang.swipemenulib.SwipeMenuLayout;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import org.litepal.LitePal;
import org.litepal.crud.callback.FindMultiCallback;

import java.util.LinkedList;
import java.util.List;


/**
 * @Creator cetwag, yuebanquan
 * @Version V2.0.0
 * @Time 2019.6.27
 * @Description 账单模块碎片类
 */
public class fragment_bill extends Fragment {

    public TextView tv_screen;
    public DrawerLayout drawerlayout;
    listener_fragment_bill listener;
    public TextView tv_time_selected;
    //账单碎片中的日期选择弹窗的标志
    public static final int FRAGMENT_BILL_SELECT_TIME = 1;
    //重置功能相关控件
    public Button btn_reset;
    public RadioGroup rdg_one;
    CheckBox ckb_food, ckb_shop, ckb_house, ckb_travel, ckb_play, ckb_communication,
            ckb_child, ckb_hospital, ckb_pay_other, ckb_wages, ckb_reward, ckb_interest, ckb_investment,
            ckb_unexpected, ckb_income_other, ckb_reimbursement, ckb_internal_transfer, ckb_deposit,
            ckb_collect_debts, ckb_return_credit_card, ckb_other_other;
    public CheckBox[] checkboxes;//复选框组
    // 动态生成item屏幕适配相关控件
    LinearLayout Lin_bill_item;
    LinearLayout Lin_example_first_hor, Lin_example_second_ver1, Lin_example_second_ver2;
    ImageView Img_example_icon;
    TextView tv_example_name, tv_example_message, tv_example_bank, tv_example_money;
    View view_example_occupy;
    //动态生成item屏幕适配相关控件结束
    SwipeMenuLayout Swip_menu_bill;//侧滑删除布局
    Button btn_delete_bill;//侧滑删除按钮
    int index;//删除索引
    LinkedList<Button> LinDelBtn;//删除按钮链表
    LinkedList<String> LinStrTime;//删除行的对应的记账时间链表
    LinkedList<String> LinStrName;//银行名称链表
    LinkedList<String> LinStrMoney;//记账表里的金钱
    LinkedList<String> LinStrClassify;//记账里的类别

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //inflater使将xml布局文件转换为视图的一个类,container表示在container里面显示这个视图
        View view = inflater.inflate(R.layout.fragment_bill, container, false);

        //屏幕适配
        ScreenAdapterTools.getInstance().loadView(view);

        return view;
    }

    /**
     * @parameter
     * @description 根据fragment的生命周期，onActivityCreated在onCreateView后执行
     * @Time 2019/6/28 21:58
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //该碎片控件等初始化
        init();

        //监听类功能函数
        listener.listener_fb();
    }

    /**
     * @parameter
     * @description view控件初始化
     * @Time 2019/6/28 21:10
     */
    void init() {
        tv_screen = getView().findViewById(R.id.tv_screen);
        drawerlayout = getView().findViewById(R.id.DrawerLayout);
        tv_time_selected = getView().findViewById(R.id.tv_time_selected);
        btn_reset = getView().findViewById(R.id.btn_reset);
        rdg_one = getView().findViewById(R.id.rdg_one);
        ckb_food = getView().findViewById(R.id.ckb_food);
        ckb_shop = getView().findViewById(R.id.ckb_shop);
        ckb_house = getView().findViewById(R.id.ckb_house);
        ckb_travel = getView().findViewById(R.id.ckb_travel);
        ckb_play = getView().findViewById(R.id.ckb_play);
        ckb_communication = getView().findViewById(R.id.ckb_communication);
        ckb_child = getView().findViewById(R.id.ckb_child);
        ckb_hospital = getView().findViewById(R.id.ckb_hospital);
        ckb_pay_other = getView().findViewById(R.id.ckb_pay_other);
        ckb_wages = getView().findViewById(R.id.ckb_wages);
        ckb_reward = getView().findViewById(R.id.ckb_reward);
        ckb_interest = getView().findViewById(R.id.ckb_interest);
        ckb_investment = getView().findViewById(R.id.ckb_investment);
        ckb_unexpected = getView().findViewById(R.id.ckb_unexpected);
        ckb_income_other = getView().findViewById(R.id.ckb_income_other);
        ckb_reimbursement = getView().findViewById(R.id.ckb_reimbursement);
        ckb_internal_transfer = getView().findViewById(R.id.ckb_internal_transfer);
        ckb_deposit = getView().findViewById(R.id.ckb_deposit);
        ckb_collect_debts = getView().findViewById(R.id.ckb_collect_debts);
        ckb_return_credit_card = getView().findViewById(R.id.ckb_return_credit_card);
        ckb_other_other = getView().findViewById(R.id.ckb_other_other);
        //这里用一个checkbox数组装，在写重置功能的时候就会轻松一些
        checkboxes = new CheckBox[]{ckb_food, ckb_shop, ckb_house, ckb_travel, ckb_play, ckb_communication,
                ckb_child, ckb_hospital, ckb_pay_other, ckb_wages, ckb_reward, ckb_interest, ckb_investment,
                ckb_unexpected, ckb_income_other, ckb_reimbursement, ckb_internal_transfer, ckb_deposit,
                ckb_collect_debts, ckb_return_credit_card, ckb_other_other};
        listener = new listener_fragment_bill(this);
        Lin_bill_item = getView().findViewById(R.id.Lin_bill_item);
        Lin_example_first_hor = getView().findViewById(R.id.Lin_example_first_hor);
        Lin_example_second_ver1 = getView().findViewById(R.id.Lin_example_second_ver1);
        Lin_example_second_ver2 = getView().findViewById(R.id.Lin_example_second_ver2);
        Img_example_icon = getView().findViewById(R.id.Img_example_icon);
        tv_example_name = getView().findViewById(R.id.tv_example_name);
        tv_example_message = getView().findViewById(R.id.tv_example_message);
        tv_example_bank = getView().findViewById(R.id.tv_example_bank);
        tv_example_money = getView().findViewById(R.id.tv_example_money);
        view_example_occupy = getView().findViewById(R.id.view_example_occupy);
        Swip_menu_bill = getView().findViewById(R.id.Swip_menu_bill);
        btn_delete_bill = getView().findViewById(R.id.btn_delete_bill);
        Swip_menu_bill.setVisibility(View.GONE);//隐藏示例item
        LinDelBtn = new LinkedList<Button>();
        LinStrTime = new LinkedList<String>();
        LinStrName = new LinkedList<String>();
        LinStrMoney = new LinkedList<String>();
        LinStrClassify = new LinkedList<String>();
        LinDelBtn.add(0, null);
        LinStrTime.add(0, null);
        LinStrName.add(0, null);
        LinStrMoney.add(0, null);
        LinStrClassify.add(0, null);
    }

    /**
     * @parameter
     * @description 重写暂停事件，暂停的时候就清空内容
     * @Time 2019/7/15 1:16
     */
    @Override
    public void onPause() {
        super.onPause();
        Lin_bill_item.removeAllViews();//清空所有item项
    }

    /**
     * @parameter
     * @description 重写onResume恢复的时候重写异步取数据
     * @Time 2019/7/15 1:18
     */
    @Override
    public void onResume() {
        super.onResume();
        /**
         * 异步取数据库中的数据,并创建资产项
         */
        LitePal.findAllAsync(Tally.class).listen(new FindMultiCallback<Tally>() {
            @Override
            public void onFinish(List<Tally> list) {
                list = LitePal.findAll(Tally.class, true);//找到所有数据,其中的参数ture要注意
                create_bill_item(list);
            }
        });
    }

    /**
     * @parameter
     * @description 动态加载账单item功能函数
     * @Time 2019/7/15 1:48
     */
    void create_bill_item(List<Tally> list) {

        index = -1;

        for (int i = 0; i < list.size(); i++) {//遍历对应表里的数据

            index += 1;

            String typeName = list.get(i).getClassify().getClassifyName();//取出类别名称
            String tallyMessage = list.get(i).getTallyComment();//取出备注信息
            String accountFrom = list.get(i).getAssetAccount().getAssetAccountBankName();//取出资产来源
            String tallyMoney = list.get(i).getTallyMoney();//取出支出或收入的金额
            int type = list.get(i).getClassify().getClassifyType();//获取是支出还是收入项
            String tallyTime = list.get(i).getTallyDate();//获取记账的时间

            //侧滑布局编写
            ViewGroup.LayoutParams layoutParams_swipmenu = Swip_menu_bill.getLayoutParams();//找到已有的侧滑菜单布局
            SwipeMenuLayout swipmenu = new SwipeMenuLayout(getContext());
            swipmenu.setBackgroundResource(R.drawable.line_block);//第一层布局设置为白色带边框背景
            LinearLayout.LayoutParams swipmenu_Params = new LinearLayout.LayoutParams(layoutParams_swipmenu.width, layoutParams_swipmenu.height);
            swipmenu.setLayoutParams(swipmenu_Params);
            //侧滑布局编写完毕

            //删除按钮编写
            Button delete = new Button(getContext());
            LinDelBtn.add(index, delete);
            ViewGroup.LayoutParams layoutParams_delete = btn_delete_bill.getLayoutParams();//找到已有的删除按钮布局
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

            //动态编写第一层布局，水平方向
            ViewGroup.LayoutParams layoutParams_first = Lin_example_first_hor.getLayoutParams();//获取已有的动态第一层布局
            LinearLayout first = new LinearLayout(getContext());//动态创建第一层的布局
            //设置第一层布局的大小等属性
            LinearLayout.LayoutParams first_Params = new LinearLayout.LayoutParams(layoutParams_first.width, layoutParams_first.height);
            first_Params.setMargins(0, 25, 0, 0);//设置边距
            first.setLayoutParams(first_Params);//将上面的大小和边距属性赋给第一层布局
            //第一层布局编写完毕

            //第二层布局(垂直1)
            ViewGroup.LayoutParams layoutParams_second = Lin_example_second_ver1.getLayoutParams();//获取已有的动态第二层布局
            LinearLayout twice = new LinearLayout(getContext());//动态创建第二层的布局
            //设置第二层布局的大小等属性
            LinearLayout.LayoutParams twice_Params = new LinearLayout.LayoutParams(layoutParams_second.width, layoutParams_second.height);
            twice_Params.setMargins(10, 0, 00, 0);
            twice.setOrientation(LinearLayout.VERTICAL);//设置为垂直方向布局
            twice.setLayoutParams(twice_Params);//将上面的大小和边距属性赋给第二层布局
            //第二层布局编写完毕

            //类别图案编写
            ImageView type_icon = new ImageView(getContext());
            ViewGroup.LayoutParams layoutParams_bank = Img_example_icon.getLayoutParams();//获取已有的类别图案布局
            LinearLayout.LayoutParams bank_Params = new LinearLayout.LayoutParams(layoutParams_bank.width, layoutParams_bank.height);
            bank_Params.setMargins(20, 0, 0, 0);
            bank_Params.gravity = Gravity.CENTER;
            type_icon.setLayoutParams(bank_Params);//将类别图案加到它自己的布局中
            switch (typeName) {
                case "餐饮":
                    type_icon.setBackgroundResource(R.drawable.ic_local_dining_black_24dp2);
                    break;
                case "旅行":
                    type_icon.setBackgroundResource(R.drawable.ic_airplanemode_active_black_24dp2);
                    break;
                case "购物":
                    type_icon.setBackgroundResource(R.drawable.ic_shopping_cart_black_24dp2);
                    break;
                case "交通":
                    type_icon.setBackgroundResource(R.drawable.ic_subway_black_24dp2);
                    break;
                case "通讯":
                    type_icon.setBackgroundResource(R.drawable.ic_phone_in_talk_black_24dp2);
                    break;
                case "医疗":
                    type_icon.setBackgroundResource(R.drawable.ic_local_hospital_black_24dp2);
                    break;
                case "住房":
                    type_icon.setBackgroundResource(R.drawable.ic_home_black_24dp_big2);
                    break;
                case "育儿":
                    type_icon.setBackgroundResource(R.drawable.ic_child_care_black_24dp2);
                    break;
                case "文教":
                    type_icon.setBackgroundResource(R.drawable.ic_school_black_24dp2);
                    break;
                case "娱乐":
                    type_icon.setBackgroundResource(R.drawable.ic_rowing_black_24dp2);
                    break;
                case "宠物":
                    type_icon.setBackgroundResource(R.drawable.ic_pets_black_24dp2);
                    break;
                case "生活":
                    type_icon.setBackgroundResource(R.drawable.ic_headset_mic_black_24dp2);
                    break;
                case "奖金":
                    type_icon.setBackgroundResource(R.drawable.ic_monetization_on_black_24dp2);
                    break;
                case "工资":
                    type_icon.setBackgroundResource(R.drawable.ic_local_atm_black_24dp2);
                    break;
                case "投资收益":
                    type_icon.setBackgroundResource(R.drawable.ic_trending_up_black_24dp2);
                    break;
                case "报销":
                    type_icon.setBackgroundResource(R.drawable.ic_add_shopping_cart_black_24dp2);
                    break;
                case "借入":
                    type_icon.setBackgroundResource(R.drawable.ic_play_for_work_black_24dp2);
                    break;
                case "投资回收":
                    type_icon.setBackgroundResource(R.drawable.ic_rotate_left_black_24dp2);
                    break;
                case "收债":
                    type_icon.setBackgroundResource(R.drawable.ic_person_add_black_24dp2);
                    break;
                case "红包":
                    type_icon.setBackgroundResource(R.drawable.ic_format_indent_increase_black_24dp2);
                    break;
            }

            //类别图案编写完毕

            //类别名称编写
            TextView type_name = new TextView(getContext());
            ViewGroup.LayoutParams layoutParams_type_name = tv_example_name.getLayoutParams();//获取已有的类别名称布局
            LinearLayout.LayoutParams type_name_Params = new LinearLayout.LayoutParams(layoutParams_type_name.width, layoutParams_type_name.height);
            type_name_Params.setMargins(0, 0, 0, 0);//设置边距
            type_name_Params.gravity = Gravity.CENTER;
            type_name_Params.gravity = Gravity.START;
            type_name.setLayoutParams(type_name_Params);//将类别名称加到它自己的布局中
            type_name.setText(typeName);//给类别名称设置文字内容
            type_name.setTextColor(getResources().getColor(R.color.black));
            type_name.setTextSize(17);
            LinStrClassify.add(index, typeName);
            //类别名称编写完毕

            //备注信息文字显示编写
            TextView message = new TextView(getContext());
            ViewGroup.LayoutParams layoutParams_message = tv_example_message.getLayoutParams();//获取已有的备注信息名称布局
            LinearLayout.LayoutParams message_Params = new LinearLayout.LayoutParams(layoutParams_message.width, layoutParams_message.height);
            message_Params.setMargins(0, 0, 0, 0);//设置边距
            message_Params.gravity = Gravity.CENTER;
            message_Params.gravity = Gravity.START;
            message.setLayoutParams(message_Params);//将备注信息加到它自己的布局中
            message.setText(tallyMessage);//给备注信息设置文字内容
            //备注信息编写完毕

            //占位view编写
            //view_example_occupy
            View occupy_view = new View(getContext());
            ViewGroup.LayoutParams layoutParams_view_example_occupy = view_example_occupy.getLayoutParams();//获取已有的占位view布局
            LinearLayout.LayoutParams occupy_view_Params = new LinearLayout.LayoutParams(layoutParams_view_example_occupy.width, layoutParams_view_example_occupy.height);
            occupy_view.setLayoutParams(occupy_view_Params);//将占位布局给到自己

            //第三层布局(垂直2)
            ViewGroup.LayoutParams layoutParams_third = Lin_example_second_ver2.getLayoutParams();//获取已有的动态第三层布局
            LinearLayout third = new LinearLayout(getContext());//动态创建第三层的布局
            //设置第三层布局的大小等属性
            LinearLayout.LayoutParams third_Params = new LinearLayout.LayoutParams(layoutParams_third.width, layoutParams_third.height);
            third.setOrientation(LinearLayout.VERTICAL);//设置为垂直方向布局
            third.setLayoutParams(third_Params);//将上面的大小和边距属性赋给第三层布局
            //第三层布局编写完毕

            //银行名称编写
            TextView banke_name = new TextView(getContext());
            ViewGroup.LayoutParams layoutParams_banke_name = tv_example_bank.getLayoutParams();//获取已有的银行名称布局
            LinearLayout.LayoutParams banke_name_Params = new LinearLayout.LayoutParams(layoutParams_banke_name.width, layoutParams_banke_name.height);
            banke_name_Params.gravity = Gravity.CENTER;
            banke_name_Params.gravity = Gravity.END;
            banke_name.setLayoutParams(banke_name_Params);//将银行名称加到它自己的布局中
            banke_name.setText(accountFrom);//给银行名称设置文字内容
            banke_name.setTextColor(getResources().getColor(R.color.black, null));
            banke_name.setTextSize(17);
            if (type == 0) {//支出颜色为红色
                banke_name.setTextColor(getResources().getColor(R.color.red, null));
            } else {//收入颜色为绿色
                banke_name.setTextColor(getResources().getColor(R.color.green, null));
            }
            LinStrName.add(index, accountFrom);
            //银行名称编写完毕

            //金额编写
            TextView money = new TextView(getContext());
            ViewGroup.LayoutParams layoutParams_money = tv_example_money.getLayoutParams();//获取已有的金额布局
            LinearLayout.LayoutParams money_Params = new LinearLayout.LayoutParams(layoutParams_money.width, layoutParams_money.height);
            message_Params.gravity = Gravity.CENTER;
            message_Params.gravity = Gravity.END;
            money.setLayoutParams(money_Params);//将金额加到它自己的布局中
            money.setText(tallyMoney);//给金额设置文字内容
            if (type == 0) {//支出颜色为红色
                money.setTextColor(getResources().getColor(R.color.red, null));
            } else {//收入颜色为绿色
                money.setTextColor(getResources().getColor(R.color.green, null));
            }
            LinStrMoney.add(index, tallyMoney);
            //金额编写完毕

            //编写时间(不占空间)
            TextView time = new TextView(getContext());
            LinearLayout.LayoutParams time_Params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            time.setLayoutParams(time_Params);
            time.setText(tallyTime);
            time.setVisibility(View.GONE);//不占空间的隐藏
            LinStrTime.add(index, tallyTime);
            //时间编写完毕


            Lin_bill_item.addView(swipmenu);//将第一层降入到xml总布局中
            swipmenu.addView(first);//将第一层加入到总布局中
            first.addView(type_icon);//将类别图案加入到第一层中
            first.addView(time);//将不占空间的时间内容加到第一层中
            first.addView(twice);//将第二层加入到第一层中
            twice.addView(type_name);//将类别名字加入到第二层布局中
            twice.addView(message);//将备注信息加入到第二层布局中
            first.addView(occupy_view);//将占位view加入到第一层布局中
            first.addView(third);//将第三层加入到第一层中
            third.addView(banke_name);//将银行名称加入到第三层布局中
            third.addView(money);//将金额放入第三层布局中
            swipmenu.addView(delete);

        }
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
        for (int i = 0; i <= LinDelBtn.size() - 1; i++) {//遍历循环删除按钮list
            if (v.equals(LinDelBtn.get(i))) {

                index = i;//确定是第几个删除按钮发生事件

                //先计算
                String tallyBanName = LinStrName.get(i);//获取删除行的银行名称
                AssetAccount assetaccount = LitePal.where("assetAccountBankName == ?", tallyBanName).findFirst(AssetAccount.class);
                String assetMoney = assetaccount.getAssetAccountMoney();//获取对应的钱

                //需要判断一下是支出还是收入
                Classify classify = LitePal.where("classifyName == ?", LinStrClassify.get(i)).findFirst(Classify.class);
                int result = classify.getClassifyType();
                String nowMoney;
                if (result == 0) {  //0表示支出
                    nowMoney = Double.parseDouble(assetMoney) + Double.parseDouble(LinStrMoney.get(i)) + "";//删除一行后的金额计算
                } else {  //1表示收入
                    nowMoney = Double.parseDouble(assetMoney) - Double.parseDouble(LinStrMoney.get(i)) + "";//删除一行后的金额计算
                }
                ContentValues values = new ContentValues();
                values.put("assetaccountmoney", nowMoney);
                LitePal.updateAll(AssetAccount.class, values, "assetaccountbankname = ?", tallyBanName);

                //再删除
                LinDelBtn.remove(index);
                Lin_bill_item.removeViewAt(index);//删除对应的资产行
                LitePal.deleteAll(Tally.class, "tallyDate=? ", LinStrTime.get(i));//根据记账的时间寻找并删除对应行
                LinStrTime.remove(index);
                break;
            }
        }
    }
}

