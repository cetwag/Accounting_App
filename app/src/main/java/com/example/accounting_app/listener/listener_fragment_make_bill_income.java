package com.example.accounting_app.listener;

import android.content.ContentValues;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.widget.PopupMenu;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.bigkoo.pickerview.view.TimePickerView;
import com.example.accounting_app.R;
import com.example.accounting_app.database.AssetAccount;
import com.example.accounting_app.database.Classify;
import com.example.accounting_app.database.Tally;
import com.example.accounting_app.fragment.fragment_make_bill_income;
import com.example.accounting_app.function.type_or_format_conversion;

import org.litepal.LitePal;
import org.litepal.crud.callback.FindMultiCallback;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

/**
 * @Creator cetwag yuebanquan
 * @Version V2.0.0
 * @ 2019.6.29
 * @Description 记一笔收入碎片的监听
 */
public class listener_fragment_make_bill_income implements View.OnClickListener {
    fragment_make_bill_income frag_mbi;
    String incomeType = "奖金";
    type_or_format_conversion function = new type_or_format_conversion();

    /**
     * @parameter
     * @description 编写两个有参和无参构造函数，用于获取对应类的控件
     * @Time 2019/6/29 11:00
     */
    public listener_fragment_make_bill_income() {
    }

    public listener_fragment_make_bill_income(fragment_make_bill_income fmbi) {
        frag_mbi = fmbi;
    }

    /**
     * @parameter
     * @description 监听绑定
     * @Time 2019/6/29 11:04
     */
    public void listener_fragment_mbi() {
        frag_mbi.rdb_select_time_income.setOnClickListener(this);
        frag_mbi.rdb_bonus.setOnClickListener(this);
        frag_mbi.rdb_wages.setOnClickListener(this);
        frag_mbi.rdb_invest_profit.setOnClickListener(this);
        frag_mbi.rdb_reimbursement.setOnClickListener(this);
        frag_mbi.rdb_borrow.setOnClickListener(this);
        frag_mbi.rdb_invest_recovery.setOnClickListener(this);
        frag_mbi.rdb_debts_collection.setOnClickListener(this);
        frag_mbi.rdb_red_envelope.setOnClickListener(this);
        frag_mbi.Imgbtn_select_from_income.setOnClickListener(this);
        frag_mbi.btn_determine_income.setOnClickListener(this);
    }


    /**
     * @parameter
     * @description 实现view.OnClickListener接口的监听函数
     * @Time 2019/6/29 11:06
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rdb_select_time_income:
                frag_mbi.pvTime.show();//显示出时间选择器
                break;
            case R.id.rdb_bonus:
                frag_mbi.rdg_2.clearCheck();
                incomeType = "奖金";
                break;
            case R.id.rdb_wages:
                incomeType = "工资";
                frag_mbi.rdg_2.clearCheck();
                break;
            case R.id.rdb_invest_profit:
                incomeType = "投资收益";
                frag_mbi.rdg_2.clearCheck();
                break;
            case R.id.rdb_reimbursement:
                incomeType = "报销";
                frag_mbi.rdg_2.clearCheck();
                break;
            case R.id.rdb_borrow:
                incomeType = "借入";
                frag_mbi.rdg_1.clearCheck();
                break;
            case R.id.rdb_invest_recovery:
                incomeType = "投资回收";
                frag_mbi.rdg_1.clearCheck();
                break;
            case R.id.rdb_debts_collection:
                incomeType = "收债";
                frag_mbi.rdg_1.clearCheck();
                break;
            case R.id.rdb_red_envelope:
                incomeType = "红包";
                frag_mbi.rdg_1.clearCheck();
                break;
            case R.id.Imgbtn_select_from_income:
                select_from_income();
                break;
            case R.id.btn_determine_income:
                save_income();
                break;
        }
    }

    /**
     * @parameter
     * @description 记收入中将输入数据存入数据库功能函数
     * @Time 2019/7/17 23:08
     */
    void save_income() {
        //edt_input_money_income,不可为空
        //type类别信息,不可为空
        //date记账日期,不可为空
        //edt_remarks_message_income,能为空
        //tv_from_income 收入到的具体银行名称
        String incomeMoney = frag_mbi.edt_input_money_income.getText().toString();//获取输入的收入金额
        Date date = new Date();//获取收入的时间
        String string_time = function.getTimeYMDhhmmss(date);
        String incomeMessage = frag_mbi.edt_remarks_message_income.getText().toString();//获取输入的备注信息
        String incomeBank = frag_mbi.tv_from_income.getText().toString();//获取最终收入的银行
        if (!TextUtils.isEmpty(incomeMoney)) {//如果输入的收入金额不为空
            Tally tally = new Tally();
            tally.setTallyMoney(incomeMoney);//将收入金额存入
            tally.setTallyDate(string_time);//将收入日期存入
            tally.setTallyComment(incomeMessage);//将备注信息存入
            Classify classify = LitePal.where("classifyName  == ?", incomeType).findFirst(Classify.class);//找到满足条件的第一个数据
            classify.getTallyList().add(tally);//关联类别表
            //关联资产表
            AssetAccount assetAccount = LitePal.where
                    ("assetAccountBankName  == ?", incomeBank)
                    .findFirst(AssetAccount.class);
            assetAccount.getTallyList().add(tally);//关联资产表
            getInformationincome(incomeMoney);
            tally.save();
            classify.save();
            assetAccount.save();
            frag_mbi.getActivity().finish();
        } else {
            Toast.makeText(frag_mbi.getContext(), "请输入收入金额", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * @parameter 参数为输入的记账金额
     * @description 遍历获取数据库内容
     * @Time 2019/7/16 19:39
     */
    void getInformationincome(final String tallyMoney) {
        /**
         * 异步取数据库中的数据，用于计算资产
         */
        LitePal.findAllAsync(Tally.class).listen(new FindMultiCallback<Tally>() {
            @Override
            public void onFinish(List<Tally> list) {
                list = LitePal.findAll(Tally.class, true);//找到所有数据,其中的参数ture要注意

                for (int i = 0; i < list.size(); i++) {
                    String bankNmae = list.get(i).getAssetAccount().getAssetAccountBankName();//获取资产银行名称
                    String assetMessage = list.get(i).getAssetAccount().getAssetAccountType();//获取资产备注信息
                    String assetMoney = list.get(i).getAssetAccount().getAssetAccountMoney();//获取对应资产中的金额
                    String nowMoney = Double.parseDouble(assetMoney) + Double.parseDouble(tallyMoney) + "";
                    ContentValues values = new ContentValues();
                    values.put("assetaccountmoney", nowMoney);
                    LitePal.updateAll(AssetAccount.class, values, "assetaccountbankname = ? and assetaccounttype = ?", bankNmae, assetMessage);
                }
            }
        });
    }

    void select_from_income() {
        PopupMenu popup_from_pay = new PopupMenu(frag_mbi.getContext(), frag_mbi.Imgbtn_select_from_income);//java创建一个弹出菜单
        popup_from_pay.getMenuInflater().inflate(R.menu.pop_img_make_bill_pay, popup_from_pay.getMenu());//找到布局
        popup_from_pay.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {//当点击弹出菜单里的子项的点击事件
                switch (item.getItemId()) {
                    case R.id.wechat:
                        frag_mbi.Imgbtn_select_from_income.setBackgroundResource(R.drawable.bank_wechat);//图片变更
                        frag_mbi.tv_from_income.setText("微信");//文字变更
                        break;
                    case R.id.alipay:
                        frag_mbi.Imgbtn_select_from_income.setBackgroundResource(R.drawable.bank_alipay);
                        frag_mbi.tv_from_income.setText("支付宝");
                        break;
                    case R.id.icbc:
                        frag_mbi.Imgbtn_select_from_income.setBackgroundResource(R.drawable.bank_icbc);
                        frag_mbi.tv_from_income.setText("工商银行");
                        break;
                    case R.id.spdb:
                        frag_mbi.Imgbtn_select_from_income.setBackgroundResource(R.drawable.bank_spdb);
                        frag_mbi.tv_from_income.setText("浦发银行");
                        break;
                    case R.id.cmb:
                        frag_mbi.Imgbtn_select_from_income.setBackgroundResource(R.drawable.bank_cmb);
                        frag_mbi.tv_from_income.setText("招商银行");
                        break;
                    case R.id.bcm:
                        frag_mbi.Imgbtn_select_from_income.setBackgroundResource(R.drawable.bank_bcm);
                        frag_mbi.tv_from_income.setText("交通银行");
                        break;
                    case R.id.ccb:
                        frag_mbi.Imgbtn_select_from_income.setBackgroundResource(R.drawable.bank_ccb);
                        frag_mbi.tv_from_income.setText("建设银行");
                        break;
                    case R.id.boc:
                        frag_mbi.Imgbtn_select_from_income.setBackgroundResource(R.drawable.bank_boc);
                        frag_mbi.tv_from_income.setText("中国银行");
                        break;
                    case R.id.abc:
                        frag_mbi.Imgbtn_select_from_income.setBackgroundResource(R.drawable.bank_abc);
                        frag_mbi.tv_from_income.setText("农业银行");
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

}
