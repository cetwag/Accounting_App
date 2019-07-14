package com.example.accounting_app.listener;

import android.content.Intent;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.widget.PopupMenu;
import android.text.Layout;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.accounting_app.R;
import com.example.accounting_app.activity.MainActivity;
import com.example.accounting_app.activity.activity_make_asset;
import com.example.accounting_app.database.AssetAccount;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import java.lang.reflect.Field;


/**
 * @Creator cetwag yuebanquan
 * @Version V2.0.0
 * @Time 2019.6.29
 * @Description 及资产activity的监听类
 */
public class listener_activity_make_asset implements View.OnClickListener {

    activity_make_asset activity_ma;
    //因为这个动态增加的国旗图片需要在多个方法中使用,所以直接拉到最顶上来定义
    ImageView Img_country;
    int index;//删除按钮的索引
    private static AssetAccount assetAccount;//数据库表对象

    /**
     * @parameter
     * @description 编写两个有参和无餐构造函数用来获取对应类的控件
     * @Time 2019/6/29 13:00
     */
    public listener_activity_make_asset() {
    }

    public listener_activity_make_asset(activity_make_asset ama) {
        activity_ma = ama;
    }

    /**
     * @parameter
     * @description 绑定监听
     * @Time 2019/6/29 13:00
     */
    public void listener_activity_ma() {
        activity_ma.btn_back.setOnClickListener(this);
        activity_ma.tv_select_card.setOnClickListener(this);
        activity_ma.Img_select_bank.setOnClickListener(this);
        listener_activity_ma_spinner();
        activity_ma.save.setOnClickListener(this);
    }

    /**
     * @parameter
     * @description 实现View.OnClickListener的监听方法, 包含了对币种spinner的币种项添加选择
     * @Time 2019/6/29 12:56
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                activity_ma.finish();
                break;
            case R.id.tv_select_card:
                //选择卡类别的pop_menu
                PopupMenu popup_card = new PopupMenu(activity_ma, activity_ma.tv_select_card);//java创建一个弹出菜单
                popup_card.getMenuInflater().inflate(R.menu.menu_spinner_make_asset, popup_card.getMenu());//找到布局
                popup_card.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {//当点击弹出菜单里的子项的点击事件
                        switch (item.getItemId()) {
                            case R.id.credit_card:
                                activity_ma.tv_select_card.setText("信用卡");//选项按钮文字变更
                                activity_ma.edt_remarks_message.setText("信用卡");//备注信息自动填充文字变更
                                break;
                            case R.id.debit_card:
                                activity_ma.tv_select_card.setText("借记卡");//选项按钮文字变更
                                activity_ma.edt_remarks_message.setText("借记卡");//备注信息自动填充文字变更
                                break;
                            case R.id.other:
                                activity_ma.tv_select_card.setText("其他");//选项按钮文字变更
                                activity_ma.edt_remarks_message.setText("");//备注信息自动填充文字清空
                                break;
                        }
                        return true;
                    }
                });
                popup_card.show();//弹出菜单的而显示
                break;
            case R.id.Img_select_bank:
                //选择银行的pop_menu
                PopupMenu popup_bank = new PopupMenu(activity_ma, activity_ma.Img_select_bank);//java创建一个弹出菜单
                popup_bank.getMenuInflater().inflate(R.menu.pop_menu_make_asset, popup_bank.getMenu());//找到布局
                popup_bank.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {//当点击弹出菜单里的子项的点击事件
                        switch (item.getItemId()) {
                            case R.id.icbc:
                                activity_ma.Img_select_bank.setBackgroundResource(R.drawable.bank_icbc);//图片变更
                                activity_ma.edt_asset_from.setText("工商银行");// 同时底下的银行名称也变
                                break;
                            case R.id.spdb:
                                activity_ma.Img_select_bank.setBackgroundResource(R.drawable.bank_spdb);
                                activity_ma.edt_asset_from.setText("浦发银行");
                                break;
                            case R.id.cmb:
                                activity_ma.Img_select_bank.setBackgroundResource(R.drawable.bank_cmb);
                                activity_ma.edt_asset_from.setText("招商银行");
                                break;
                            case R.id.bcm:
                                activity_ma.Img_select_bank.setBackgroundResource(R.drawable.bank_bcm);
                                activity_ma.edt_asset_from.setText("交通银行");
                                break;
                            case R.id.ccb:
                                activity_ma.Img_select_bank.setBackgroundResource(R.drawable.bank_ccb);
                                activity_ma.edt_asset_from.setText("建设银行");
                                break;
                            case R.id.boc:
                                activity_ma.Img_select_bank.setBackgroundResource(R.drawable.bank_boc);
                                activity_ma.edt_asset_from.setText("中国银行");
                                break;
                            case R.id.abc:
                                activity_ma.Img_select_bank.setBackgroundResource(R.drawable.bank_abc);
                                activity_ma.edt_asset_from.setText("农业银行");
                                break;
                            case R.id.wechat:
                                activity_ma.Img_select_bank.setBackgroundResource(R.drawable.bank_wechat);
                                activity_ma.edt_asset_from.setText("微信");
                                break;
                            case R.id.alipay:
                                activity_ma.Img_select_bank.setBackgroundResource(R.drawable.bank_alipay);
                                activity_ma.edt_asset_from.setText("支付宝");
                                break;
                        }
                        return true;
                    }
                });
                try {//利用反射机制强制显示PopMenu里的icon
                    Field field = popup_bank.getClass().getDeclaredField("mPopup");
                    field.setAccessible(true);
                    MenuPopupHelper mHelper = (MenuPopupHelper) field.get(popup_bank);
                    mHelper.setForceShowIcon(true);
                } catch (IllegalAccessException | NoSuchFieldException e) {
                    e.printStackTrace();
                }
                popup_bank.show();//弹出菜单的而显示
                break;
            case R.id.save:
                save_asset();//将输入的数据存入数据库
                break;
        }
    }

    /**
     * @parameter
     * @description 对spinner的item编写动态添加币种的功能函数——spinner的item监听
     * @Time 2019/6/30 9:00
     */
    void listener_activity_ma_spinner() {
        activity_ma.spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //这里需要注意，spinner默认是选中position0的item的,所以直接从case1开始
                switch (position) {
                    case 1:
                        //当item被选中的时候
                        listener_activity_ma_spinner_add_item();
                        Img_country.setBackgroundResource(R.drawable.country_circle_aud);
                        break;
                    case 2:
                        //当item被选中的时候
                        listener_activity_ma_spinner_add_item();
                        Img_country.setBackgroundResource(R.drawable.country_circle_mop);
                        break;
                    case 3:
                        //当item被选中的时候
                        listener_activity_ma_spinner_add_item();
                        Img_country.setBackgroundResource(R.drawable.country_circle_brl);
                        break;
                    case 4:
                        //当item被选中的时候
                        listener_activity_ma_spinner_add_item();
                        Img_country.setBackgroundResource(R.drawable.country_circle_cad);
                        break;
                    case 5:
                        //当item被选中的时候
                        listener_activity_ma_spinner_add_item();
                        Img_country.setBackgroundResource(R.drawable.country_circle_dkk);
                        break;
                    case 6:
                        //当item被选中的时候
                        listener_activity_ma_spinner_add_item();
                        Img_country.setBackgroundResource(R.drawable.country_circle_eur);
                        break;
                    case 7:
                        //当item被选中的时候
                        listener_activity_ma_spinner_add_item();
                        Img_country.setBackgroundResource(R.drawable.country_circle_gbp);
                        break;
                    case 8:
                        //当item被选中的时候
                        listener_activity_ma_spinner_add_item();
                        Img_country.setBackgroundResource(R.drawable.country_circle_hkd);
                        break;
                    case 9:
                        //当item被选中的时候
                        listener_activity_ma_spinner_add_item();
                        Img_country.setBackgroundResource(R.drawable.country_circle_inr);
                        break;
                    case 10:
                        //当item被选中的时候
                        listener_activity_ma_spinner_add_item();
                        Img_country.setBackgroundResource(R.drawable.country_circle_khr);
                        break;
                    case 11:
                        //当item被选中的时候
                        listener_activity_ma_spinner_add_item();
                        Img_country.setBackgroundResource(R.drawable.country_circle_nzd);
                        break;
                    case 12:
                        //当item被选中的时候
                        listener_activity_ma_spinner_add_item();
                        Img_country.setBackgroundResource(R.drawable.country_circle_thb);
                        break;
                    case 13:
                        //当item被选中的时候
                        listener_activity_ma_spinner_add_item();
                        Img_country.setBackgroundResource(R.drawable.country_circle_usd);
                        break;
                    case 14:
                        //当item被选中的时候
                        listener_activity_ma_spinner_add_item();
                        Img_country.setBackgroundResource(R.drawable.country_circle_vnd);
                        break;
                    case 15:
                        //当item被选中的时候
                        listener_activity_ma_spinner_add_item();
                        Img_country.setBackgroundResource(R.drawable.country_circle_sek);
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * @parameter
     * @description 对spinner的item编写动态添加币种的功能函数——动态增加控件的函数封装
     * @Time 2019/6/30 9:34
     */
    void listener_activity_ma_spinner_add_item() {

        //先要确定好增加的币种上面有几个币种，确定好索引位置
        index = activity_ma.listIBTNDel.size() - 1;
        //然后再从下一个index开始增加币种
        index += 1;

        //获取已有外围布局控件的LayoutParams
        ViewGroup.LayoutParams layoutParams_layout = activity_ma.Lin_outer.getLayoutParams();
        //每一个币种item最外围布局的编写
        LinearLayout layout = new LinearLayout(activity_ma);//构造动态添加的最外围的布局
        layout.setOrientation(LinearLayout.HORIZONTAL);//设为水平排放
        LinearLayout.LayoutParams layout_Params = new LinearLayout.LayoutParams(layoutParams_layout.width, layoutParams_layout.height);
        layout.setLayoutParams(layout_Params);//将最外围布局加入布局属性
        activity_ma.Lin_major.addView(layout);//将最外围布局加入xml的总布局中
        //每一个币种item最外围布局的编写完毕


        //获取已有删除按钮控件的LayoutParams
        ViewGroup.LayoutParams layoutParams_btn_delete = activity_ma.btn_cut_down.getLayoutParams();
        //删除按钮编写
        Button btn_delete = new Button(activity_ma);//新建构造删除按钮
        btn_delete.setId(index);//不同按钮用id确定
        LinearLayout.LayoutParams btn_delete_Params = new LinearLayout.LayoutParams(layoutParams_btn_delete.width, layoutParams_btn_delete.height);
        btn_delete.setLayoutParams(btn_delete_Params);//将button加到它自己的布局中
        btn_delete.setBackgroundResource(R.drawable.ic_remove_circle_black_24dp);//给button引用资源图片
        layout.addView(btn_delete);//将删除按钮装入最外围布局中
        activity_ma.listIBTNDel.add(index, btn_delete);//index为索引
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete_btn_money(v);
            }
        });
        //删除按钮编写完毕

        //获取已有输入框控件的LayoutParams
        ViewGroup.LayoutParams layoutParams_edt_input_balance = activity_ma.edt_input_balance.getLayoutParams();
        //输入框编写
        EditText edt_input_balance = new EditText(activity_ma);//定义输入余额的输入框
        LinearLayout.LayoutParams edt_input_balance_Params = new LinearLayout.LayoutParams(layoutParams_edt_input_balance.width, layoutParams_edt_input_balance.height);
        edt_input_balance.setLayoutParams(edt_input_balance_Params);//加入自身的布局属性
        edt_input_balance.setHint("￥0.00");
        edt_input_balance.setInputType(8194);//设置输入类型为带小数点的数字（代码直接输入8194即可）
        edt_input_balance.setMaxLines(1);//限制输入的最大行数为1行
        layout.addView(edt_input_balance);//将输入框加入总布局
        //输入框编写完毕

        //获取已有国旗图片控件的LayoutParams
        ViewGroup.LayoutParams layoutParams_Img_country = activity_ma.Imgv_country_money.getLayoutParams();
        //国旗图片编写
        Img_country = new ImageView(activity_ma);//定义ImageView
        Img_country.setBackgroundResource(R.drawable.country_circle_aud);//图片资源填充
        RelativeLayout.LayoutParams Img_country_Param = new RelativeLayout.LayoutParams(layoutParams_Img_country.width, layoutParams_Img_country.height);//国旗图标的自身布局
        Img_country.setLayoutParams(Img_country_Param);//装入自身布局
        layout.addView(Img_country);//装入总布局
        //国旗图片编写完毕

    }

    /**
     * @parameter
     * @description 删除币种函数，判断第几个币种需要被删除
     * @Time 2019/6/30 10:13
     */
    void delete_btn_money(View v) {
        if (v == null) {
            return;
        }
        for (int i = 0; i <= activity_ma.listIBTNDel.size() - 1; i++) {//遍历循环删除按钮list
            if (v.equals(activity_ma.listIBTNDel.get(i))) {
                index = i;//确定是第几个删除按钮发生事件
                activity_ma.listIBTNDel.remove(index);
                activity_ma.Lin_major.removeViewAt(index);//删除对应的币种行
                break;
            }
        }
    }


    /**
     * @parameter
     * @description 将输入数据存入数据库
     * @Time 2019/7/12 22:30
     */
    void save_asset() {
        //edt_remarks_message备注信息框,不能为空
        //edt_card_number卡号输入框,可为空
        //edt_asset_from资产来源框,不可为空
        //edt_input_balance余额框,不能为空
        String string_remarks_message = activity_ma.edt_remarks_message.getText().toString();//获取备注信息
        String string_card_number = activity_ma.edt_card_number.getText().toString();//获取卡号
        String string_asset_from = activity_ma.edt_asset_from.getText().toString();//获取资产来源
        String string_input_balance = activity_ma.edt_input_balance.getText().toString();//获取余额
        assetAccount = new AssetAccount();//创建资产表对象
        if (!TextUtils.isEmpty(string_remarks_message)) {//如果输入的备注信息不为空
            if (!TextUtils.isEmpty(string_input_balance)) {//如果输入的余额不为空
                if (!TextUtils.isEmpty(string_asset_from)) {//如果资产来源不为空
                    assetAccount.setAssetAccountType(string_remarks_message);//存入备注信息
                    assetAccount.setAssetAccountMoney(string_input_balance);//存入余额
                    assetAccount.setAssetAccountBankName(string_asset_from);//存入资产来源
                    assetAccount.setAssetAccountCardNum(string_card_number);//卡号可为空，所以放在最后不用判断是否为空
                    assetAccount.save();
                    activity_ma.finish();
                } else {
                    Toast.makeText(activity_ma,
                            "请选择资产来源", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(activity_ma,
                        "请输入余额", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(activity_ma,
                    "请输入备注信息", Toast.LENGTH_SHORT).show();
        }
    }
}

