package com.example.accounting_app.listener;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.widget.PopupMenu;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.accounting_app.R;
import com.example.accounting_app.activity.MainActivity;
import com.example.accounting_app.database.AssetAccount;
import com.example.accounting_app.database.Classify;
import com.example.accounting_app.database.Tally;
import com.example.accounting_app.fragment.fragment_make_bill_pay;
import com.example.accounting_app.function.type_or_format_conversion;

import org.litepal.LitePal;
import org.litepal.crud.callback.FindMultiCallback;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;


/**
 * @Creator cetwag yuebanquan
 * @Version V2.0.0
 * @Time 2019.6.29
 * @Description 记一笔支出类的监听类
 */
public class listener_fragment_make_bill_pay implements View.OnClickListener {

    fragment_make_bill_pay frag_mbp;
    Tally tally;
    String type_name = "餐饮";//用来存放点击类别的名字
    type_or_format_conversion function = new type_or_format_conversion();

    /**
     * @parameter
     * @description 编写两个有参和无参构造函数，用于获取对应类的控件
     * @Time 2019/6/29 11:21
     */
    public listener_fragment_make_bill_pay() {
    }

    public listener_fragment_make_bill_pay(fragment_make_bill_pay fmbp) {
        frag_mbp = fmbp;
    }

    /**
     * @parameter
     * @description 监听绑定
     * @Time 2019/6/29 11:23
     */
    public void listener_Fragment_make_bill_pay() {
        frag_mbp.rdb_select_time_pay.setOnClickListener(this);
        frag_mbp.rdb_food.setOnClickListener(this);
        frag_mbp.rdb_travel.setOnClickListener(this);
        frag_mbp.rdb_shop.setOnClickListener(this);
        frag_mbp.rdb_traffic.setOnClickListener(this);
        frag_mbp.rdb_communication.setOnClickListener(this);
        frag_mbp.rdb_hospital.setOnClickListener(this);
        frag_mbp.rdb_house.setOnClickListener(this);
        frag_mbp.rdb_child.setOnClickListener(this);
        frag_mbp.rdb_teach.setOnClickListener(this);
        frag_mbp.rdb_play.setOnClickListener(this);
        frag_mbp.rdb_pet.setOnClickListener(this);
        frag_mbp.rdb_life.setOnClickListener(this);
        frag_mbp.btn_determine_pay.setOnClickListener(this);
        frag_mbp.Imgbtn_select_from_pay.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rdb_select_time_pay:
                frag_mbp.pvTime.show();//显示出时间选择器
                break;
            case R.id.rdb_food:
                frag_mbp.rdg_2.clearCheck();
                frag_mbp.rdg_3.clearCheck();
                break;
            case R.id.rdb_travel:
                frag_mbp.rdg_2.clearCheck();
                frag_mbp.rdg_3.clearCheck();
                type_name = "旅行";
                break;
            case R.id.rdb_shop:
                frag_mbp.rdg_2.clearCheck();
                frag_mbp.rdg_3.clearCheck();
                type_name = "购物";
                break;
            case R.id.rdb_traffic:
                frag_mbp.rdg_2.clearCheck();
                frag_mbp.rdg_3.clearCheck();
                type_name = "交通";
                break;
            case R.id.rdb_communication:
                frag_mbp.rdg_1.clearCheck();
                frag_mbp.rdg_3.clearCheck();
                type_name = "通讯";
                break;
            case R.id.rdb_hospital:
                frag_mbp.rdg_1.clearCheck();
                frag_mbp.rdg_3.clearCheck();
                type_name = "医疗";
                break;
            case R.id.rdb_house:
                frag_mbp.rdg_1.clearCheck();
                frag_mbp.rdg_3.clearCheck();
                type_name = "住房";
                break;
            case R.id.rdb_child:
                frag_mbp.rdg_1.clearCheck();
                frag_mbp.rdg_3.clearCheck();
                type_name = "育儿";
                break;
            case R.id.rdb_teach:
                frag_mbp.rdg_1.clearCheck();
                frag_mbp.rdg_2.clearCheck();
                type_name = "文教";
                break;
            case R.id.rdb_play:
                frag_mbp.rdg_1.clearCheck();
                frag_mbp.rdg_2.clearCheck();
                type_name = "娱乐";
                break;
            case R.id.rdb_pet:
                frag_mbp.rdg_1.clearCheck();
                frag_mbp.rdg_2.clearCheck();
                type_name = "宠物";
                break;
            case R.id.rdb_life:
                frag_mbp.rdg_1.clearCheck();
                frag_mbp.rdg_2.clearCheck();
                type_name = "生活";
                break;
            case R.id.Imgbtn_select_from_pay:
                select_from_pay();
                break;
            case R.id.btn_determine_pay:
                save_pay();
                break;
        }
    }

    /**
     * @parameter
     * @description 点击资产来源产生可选择项的功能函数
     * @Time 2019/7/14 23:14
     */
    void select_from_pay() {
        PopupMenu popup_from_pay = new PopupMenu(frag_mbp.getContext(), frag_mbp.Imgbtn_select_from_pay);//java创建一个弹出菜单
        popup_from_pay.getMenuInflater().inflate(R.menu.pop_img_make_bill_pay, popup_from_pay.getMenu());//找到布局
        popup_from_pay.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {//当点击弹出菜单里的子项的点击事件
                switch (item.getItemId()) {
                    case R.id.wechat:
                        frag_mbp.Imgbtn_select_from_pay.setBackgroundResource(R.drawable.bank_wechat);//图片变更
                        frag_mbp.tv_from_pay.setText("微信");//文字变更
                        break;
                    case R.id.alipay:
                        frag_mbp.Imgbtn_select_from_pay.setBackgroundResource(R.drawable.bank_alipay);
                        frag_mbp.tv_from_pay.setText("支付宝");
                        break;
                    case R.id.icbc:
                        frag_mbp.Imgbtn_select_from_pay.setBackgroundResource(R.drawable.bank_icbc);
                        frag_mbp.tv_from_pay.setText("工商银行");
                        break;
                    case R.id.spdb:
                        frag_mbp.Imgbtn_select_from_pay.setBackgroundResource(R.drawable.bank_spdb);
                        frag_mbp.tv_from_pay.setText("浦发银行");
                        break;
                    case R.id.cmb:
                        frag_mbp.Imgbtn_select_from_pay.setBackgroundResource(R.drawable.bank_cmb);
                        frag_mbp.tv_from_pay.setText("招商银行");
                        break;
                    case R.id.bcm:
                        frag_mbp.Imgbtn_select_from_pay.setBackgroundResource(R.drawable.bank_bcm);
                        frag_mbp.tv_from_pay.setText("交通银行");
                        break;
                    case R.id.ccb:
                        frag_mbp.Imgbtn_select_from_pay.setBackgroundResource(R.drawable.bank_ccb);
                        frag_mbp.tv_from_pay.setText("建设银行");
                        break;
                    case R.id.boc:
                        frag_mbp.Imgbtn_select_from_pay.setBackgroundResource(R.drawable.bank_boc);
                        frag_mbp.tv_from_pay.setText("中国银行");
                        break;
                    case R.id.abc:
                        frag_mbp.Imgbtn_select_from_pay.setBackgroundResource(R.drawable.bank_abc);
                        frag_mbp.tv_from_pay.setText("农业银行");
                        break;
                }
                return true;
            }
        });
        try {//利用反射机制强制显示PopMenu里的icon
            Field field = popup_from_pay.getClass().getDeclaredField("mPopup");
            field.setAccessible(true);
            MenuPopupHelper mHelper = (MenuPopupHelper) field.get(popup_from_pay);
            mHelper.setForceShowIcon(true);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        popup_from_pay.show();//弹出菜单的而显示
    }

    /**
     * @parameter
     * @description 记支出中将输入数据存入数据库功能函数
     * @Time 2019/7/14 23:13
     */
    void save_pay() {
        //edt_input_money_pay备输入金额框,不可为空
        //type类别信息,不可为空
        //date记账日期,不可为空
        //edt_remarks_message_pay备注信息框,能为空
        String string_input_money_pay = frag_mbp.edt_input_money_pay.getText().toString();//获取输入的金额
        Date date = new Date();//获取记录的时间(具体到年月日时分秒)
        String string_time = function.getTimeYMDhhmmss(date);//存入的数据进行类型转换,转换为String
        String string_remarks_message_pay = frag_mbp.edt_remarks_message_pay.getText().toString();//获取备注信息
        if (!TextUtils.isEmpty(string_input_money_pay)) {//只用判断一个输入金额是否为空就行了
            tally = new Tally();
            tally.setTallyMoney(string_input_money_pay);//将输入的金额存入数据库
            tally.setTallyComment(string_remarks_message_pay);//将输入的备注存入数据库
            tally.setTallyDate(string_time);
            Classify classify = LitePal.where("classifyName  == ?", type_name).findFirst(Classify.class);//找到满足条件的第一个数据
            classify.getTallyList().add(tally);//关联类别表
            AssetAccount assetAccount = LitePal.where
                    ("assetAccountBankName  == ?", frag_mbp.tv_from_pay.getText().toString())
                    .findFirst(AssetAccount.class);
            assetAccount.getTallyList().add(tally);//关联资产表
            assetAccount.save();
            classify.save();
            tally.save();
            getInformationpay(frag_mbp.tv_from_pay.getText().toString(), string_input_money_pay);//计算
            frag_mbp.getActivity().finish();
        } else {
            Toast.makeText(frag_mbp.getContext(), "请输入支出金额", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * @parameter 参数为输入的记账银行和金额
     * @description 遍历获取数据库内容
     * @Time 2019/7/16 19:39
     */
    void getInformationpay(String tallyBank, String tallyMoney) {
        //先根据记账银行找到对应的资产项
        List<AssetAccount> result = LitePal.where("assetAccountBankName = ?", tallyBank)
                .find(AssetAccount.class);
        String assetMoney = result.get(0).getAssetAccountMoney();//获取原有的金额
        //相减
        String nowMoney = Double.parseDouble(assetMoney) - Double.parseDouble(tallyMoney) + "";
        //开始更新
        AssetAccount updateassetAccount = new AssetAccount();
        updateassetAccount.setAssetAccountMoney(nowMoney);
        updateassetAccount.updateAll("assetAccountBankName = ?", tallyBank);
    }
}
