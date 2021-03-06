package com.example.accounting_app.function;

import android.graphics.Color;

import com.example.accounting_app.database.Tally;
import com.example.accounting_app.fragment.fragment_statements;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.litepal.LitePal;

import java.util.ArrayList;

/**
 * @Creator cetwag yuebanquan
 * @Version V2.0.0
 * @Time 2019.7.1
 * @Description Github上的第三方饼状图库
 */
public class Pie_Chart {

    /**
     * 收入变量
     */
    Double sum_kind_pay[] = new Double[12];
    Double sum_other_pay;       //其他

    /**
     * 支出变量
     */
    Double sum_kind_income[] = new Double[8];
    Double sum_other_income;       //其他

    fragment_statements frag_s = new fragment_statements();


    /**
     * @parameter
     * @description 两个构造函数
     * @Time 2019/7/1 9:51
     */
    public Pie_Chart() {
    }

    public Pie_Chart(fragment_statements fs) {
        frag_s = fs;
    }

    /**
     * @parameter
     * @description 对类别的支出或收入进行求和
     * @Time 2019/7/16 19:52
     */
    void facilitateDatabase() {
        /**
         * 支出大类计算
         */
        sum_kind_pay[0] = LitePal.where("classify_id == ?", "1").sum(Tally.class, "tallyMoney", double.class);
        sum_kind_pay[1] = LitePal.where("classify_id == ?", "2").sum(Tally.class, "tallyMoney", double.class);
        sum_kind_pay[2] = LitePal.where("classify_id == ?", "3").sum(Tally.class, "tallyMoney", double.class);
        sum_kind_pay[3] = LitePal.where("classify_id == ?", "4").sum(Tally.class, "tallyMoney", double.class);
        sum_kind_pay[4] = LitePal.where("classify_id == ?", "5").sum(Tally.class, "tallyMoney", double.class);
        sum_kind_pay[5] = LitePal.where("classify_id == ?", "6").sum(Tally.class, "tallyMoney", double.class);
        sum_kind_pay[6] = LitePal.where("classify_id == ?", "7").sum(Tally.class, "tallyMoney", double.class);
        sum_kind_pay[7] = LitePal.where("classify_id == ?", "8").sum(Tally.class, "tallyMoney", double.class);
        sum_kind_pay[8] = LitePal.where("classify_id == ?", "9").sum(Tally.class, "tallyMoney", double.class);
        sum_kind_pay[9] = LitePal.where("classify_id == ?", "10").sum(Tally.class, "tallyMoney", double.class);
        sum_kind_pay[10] = LitePal.where("classify_id == ?", "11").sum(Tally.class, "tallyMoney", double.class);
        sum_kind_pay[11] = LitePal.where("classify_id == ?", "12").sum(Tally.class, "tallyMoney", double.class);
        sum_other_pay = sum_kind_pay[3] + sum_kind_pay[4] + sum_kind_pay[5] + sum_kind_pay[6] + sum_kind_pay[7] +
                sum_kind_pay[8] + sum_kind_pay[9] + sum_kind_pay[10] + sum_kind_pay[11];

        /**
         * 收入大类计算
         */
        sum_kind_income[0] = LitePal.where("classify_id == ?", "13").sum(Tally.class, "tallyMoney", double.class);
        sum_kind_income[1] = LitePal.where("classify_id == ?", "14").sum(Tally.class, "tallyMoney", double.class);
        sum_kind_income[2] = LitePal.where("classify_id == ?", "15").sum(Tally.class, "tallyMoney", double.class);
        sum_kind_income[3] = LitePal.where("classify_id == ?", "16").sum(Tally.class, "tallyMoney", double.class);
        sum_kind_income[4] = LitePal.where("classify_id == ?", "17").sum(Tally.class, "tallyMoney", double.class);
        sum_kind_income[5] = LitePal.where("classify_id == ?", "18").sum(Tally.class, "tallyMoney", double.class);
        sum_kind_income[6] = LitePal.where("classify_id == ?", "19").sum(Tally.class, "tallyMoney", double.class);
        sum_kind_income[7] = LitePal.where("classify_id == ?", "20").sum(Tally.class, "tallyMoney", double.class);
        sum_other_income = sum_kind_income[3]
                + sum_kind_income[4] + sum_kind_income[5] + sum_kind_income[6]
                + sum_kind_income[7];
    }

    /**
     * @parameter
     * @description //饼状图加入数据函数,在支出情况下的图表
     * @Time 2019/7/5 1:01
     */

    public void pie_chart_data_pay() {

        facilitateDatabase();//首先计算数据

        frag_s.piechart.setUsePercentValues(true);
        frag_s.piechart.getDescription().setEnabled(false);
        frag_s.piechart.setExtraOffsets(5, 10, 5, 5);

        frag_s.piechart.setDragDecelerationFrictionCoef(0.95f);
//        //设置中间的内容,只有当下面的setDrawHoleEnabled为true的时候才编写，否则不美观
//        table_chart.setCenterText("报表");
//        table_chart.setCenterTextSize(15);

        frag_s.piechart.setDrawHoleEnabled(false);//是否绘制饼状图中间的圆
        //table_chart.setHoleColor(Color.WHITE);//设置中间的颜色,因为上面为false，所以这一步不必要

        //table_chart.setTransparentCircleColor(Color.WHITE);//设置中间圆环的颜色

        //table_chart.setHoleRadius(58f);//饼状图中间的圆的半径大小
        frag_s.piechart.setTransparentCircleRadius(60);

        frag_s.piechart.setDrawCenterText(true);

        frag_s.piechart.setRotationAngle(0);
        // 触摸旋转
        frag_s.piechart.setRotationEnabled(true);//设置饼状图是否可以旋转(默认为true)
        frag_s.piechart.setHighlightPerTapEnabled(true);//设置旋转的时候点中的tab是否高亮(默认为true)


        //显示的数据源
        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
        entries.add(new PieEntry(sum_kind_pay[0].intValue(), "餐饮"));
        entries.add(new PieEntry(sum_kind_pay[1].intValue(), "旅行"));
        entries.add(new PieEntry(sum_kind_pay[2].intValue(), "购物"));
        entries.add(new PieEntry(sum_other_pay.intValue(), "其他"));

        //设置数据
        setData_pay(entries);
        Legend l = frag_s.piechart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setTextSize(15);//右边竖列类别文字的大小
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        // 输入标签样式
        frag_s.piechart.setEntryLabelColor(Color.BLACK);
        //下面这个文字大小是饼状图中的类别文字的大小
        frag_s.piechart.setEntryLabelTextSize(0);
    }

    //设置数据
    private void setData_pay(ArrayList<PieEntry> entries) {
        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        //数据和颜色
        ArrayList<Integer> colors = new ArrayList<Integer>();
        //ColorTemplate可以更改每个区域的颜色
        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);
        colors.add(ColorTemplate.getHoloBlue());
        dataSet.setColors(colors);
        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(13);
        data.setValueTextColor(Color.BLACK);
        frag_s.piechart.setData(data);
        //点击突出效果
        frag_s.piechart.highlightValues(null);
        //刷新
        frag_s.piechart.invalidate();
    }

    /**
     * @parameter
     * @description //饼状图加入数据函数,在收入情况下的图表
     * @Time 2019/7/5 1:01
     */
    public void pie_chart_data_income() {
        frag_s.piechart.setUsePercentValues(true);
        frag_s.piechart.getDescription().setEnabled(false);
        frag_s.piechart.setExtraOffsets(5, 10, 5, 5);

        frag_s.piechart.setDragDecelerationFrictionCoef(0.95f);
//        //设置中间的内容,只有当下面的setDrawHoleEnabled为true的时候才编写，否则不美观
//        table_chart.setCenterText("报表");
//        table_chart.setCenterTextSize(15);

        frag_s.piechart.setDrawHoleEnabled(false);//是否绘制饼状图中间的圆
        //table_chart.setHoleColor(Color.WHITE);//设置中间的颜色,因为上面为false，所以这一步不必要

        //table_chart.setTransparentCircleColor(Color.WHITE);//设置中间圆环的颜色

        //table_chart.setHoleRadius(58f);//饼状图中间的圆的半径大小
        frag_s.piechart.setTransparentCircleRadius(60);

        frag_s.piechart.setDrawCenterText(true);

        frag_s.piechart.setRotationAngle(0);
        // 触摸旋转
        frag_s.piechart.setRotationEnabled(true);//设置饼状图是否可以旋转(默认为true)
        frag_s.piechart.setHighlightPerTapEnabled(true);//设置旋转的时候点中的tab是否高亮(默认为true)


        //显示的数据源
        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
        entries.add(new PieEntry(sum_kind_income[0].intValue(), "奖金"));
        entries.add(new PieEntry(sum_kind_income[1].intValue(), "工资"));
        entries.add(new PieEntry(sum_kind_income[2].intValue(), "投资收益"));
        entries.add(new PieEntry(sum_other_income.intValue(), "其他"));

        //设置数据
        setData_income(entries);
        Legend l = frag_s.piechart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setTextSize(15);//右边竖列类别文字的大小
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        // 输入标签样式
        frag_s.piechart.setEntryLabelColor(Color.BLACK);
        //下面这个文字大小是饼状图中的类别文字的大小
        frag_s.piechart.setEntryLabelTextSize(0);
    }

    //设置数据
    private void setData_income(ArrayList<PieEntry> entries) {
        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        //数据和颜色
        ArrayList<Integer> colors = new ArrayList<Integer>();
        //ColorTemplate可以更改每个区域的颜色
        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);
        colors.add(ColorTemplate.getHoloBlue());
        dataSet.setColors(colors);
        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(13);
        data.setValueTextColor(Color.BLACK);
        frag_s.piechart.setData(data);
        //点击突出效果
        frag_s.piechart.highlightValues(null);
        //刷新
        frag_s.piechart.invalidate();
    }

}
