package com.yjx.androidword.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.yjx.androidword.SQLiteHelper.OverWordsHelper;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Time : 2020/5/18 18:38
 * @Author : Android_小黑
 * @File : OverWordsNumUtils.java
 * @Software : Android Studio
 */
// 统计今日已背单词数量
public class OverWordsNumUtils {


    public static void add(Context context) {
        // 今天和昨天的日期
        String strTo = getDateStr(0);
        String strYest = getDateStr(-1);
        // 判断是否是 新的一天（需要保存昨天的数量到数据库）
        if ((int) SPUtils.get(context, strTo, 0) == 0) {
            SPUtils.set(context, strTo, 1);
            int number = (int) SPUtils.get(context, strYest, 0);
            insertSQL(context, strYest, number + "");
        } else {
            int number = (int) SPUtils.get(context, strTo, 0);
            SPUtils.set(context, strTo, (number + 1));
        }
    }


    /**
     * 通过传入amount来获取日期
     *
     * @param amount 0=今天 -1=昨天 +1=明天
     */
    private static String getDateStr(int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, amount);
        Date toDay = calendar.getTime();
        return getDate(toDay);
    }


    // 将Date数据转化成 xxxx.xx.xx 模式
    public static String getDate(Date date) {
        //Sun May 17 20:49:31 CST 2020
        String strDate = date.toString();
        // 由于每次Date的格式都一样，所以可以通过截取获取年月日
        String year = strDate.substring(24, 28);
        String mouth = getMonth(strDate.substring(4, 7));
        String day = strDate.substring(8, 10);
        return year + "." + mouth + "." + day;
    }

    // 添加数据进入数据库
    private static void insertSQL(Context context, String date, String number) {
        OverWordsHelper sHelper = new OverWordsHelper(context);
        SQLiteDatabase sDatabase = sHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(OverWordsHelper.DATE, date);
        cv.put(OverWordsHelper.NUMBER, number);

        sDatabase.insert(OverWordsHelper.TBNAME, null, cv);
    }

    //通过月份缩写获取对应的数据
    private static String getMonth(String month) {
        Map<String, String> map = new HashMap<>();
        map.put("Jan", "01");
        map.put("Feb", "02");
        map.put("Mar", "03");
        map.put("Apr", "04");
        map.put("May", "05");
        map.put("Jun", "06");
        map.put("Jul", "07");
        map.put("Aug", "08");
        map.put("Sep", "09");
        map.put("Oct", "10");
        map.put("Nov", "11");
        map.put("Dec", "12");
        return map.get(month);
    }

}
