package com.example.accounting_app.function;

import android.text.InputFilter;
import android.widget.EditText;

/**
 * @Creator yuebanquan
 * @Version V2.0.0
 * @Time 2019.7.23
 * @Description 使用金额输入过滤器(CashierInputFilter)
 * 注：
 *      EditText的InputType需要设置为“numberDecimal”
 *
 * 使用时:
 *      UseCashierInputFilter useCashierInputFilter;
 *      useCashierInputFilter = new UseCashierInputFilter();
 *      useCashierInputFilter.cashierInputFilter(editTextName);
 */

public class UseCashierInputFilter {

    /**
     *
     * @param editText 需要过滤的金额输入框
     */
    public void cashierInputFilter(EditText editText){
        InputFilter[] filters={new CashierInputFilter()};
        editText.setFilters(filters); //设置金额输入的过滤器，保证只能输入金额类型
    }
}
