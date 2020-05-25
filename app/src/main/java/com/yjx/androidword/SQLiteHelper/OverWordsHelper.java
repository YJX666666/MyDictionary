package com.yjx.androidword.SQLiteHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @Time : 2020/5/18 18:40
 * @Author : Android_小黑
 * @File : OverWordsHelper.java
 * @Software : Android Studio
 */
// 每日背诵单词数 数据库
public class OverWordsHelper extends SQLiteOpenHelper {

    // 库名
    private static final String DB = "OverWordNum.db";
    // 表名
    public static final String TBNAME = "tbname";
    // 日期
    public static final String DATE = "date";
    // 数量
    public static final String NUMBER = "number";


    public OverWordsHelper(Context context) {
        super(context, DB, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TBNAME + "(" + DATE + " text," + NUMBER + " text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
