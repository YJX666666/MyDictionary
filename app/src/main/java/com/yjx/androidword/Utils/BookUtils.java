package com.yjx.androidword.Utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.yjx.androidword.Bean.WordsBean;
import com.yjx.androidword.SQLiteHelper.DictionaryHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * 将某书的单词全部加入到数据库中的工具类
 */
public class BookUtils {

    private static List<WordsBean> mList;

    public static void add(final Context context, String bookName) {
        //单词本名称转成对应内置数据库名称
        switch (bookName) {
            case "中考":
                bookName = "MiddleSchool.db";
                break;
            case "高考":
                bookName = "HighSchool.db";
                break;
            case "大学四级":
                bookName = "UniLv4.db";
                break;
            case "大学六级":
                bookName = "UniLv6.db";
                break;
        }
        //首先获取文件目录
        final String path = Assets2SQLiteUtils.getPath(context, bookName);
        //线程中做数据库获取等耗时操作
        new Thread(new Runnable() {
            @Override
            public void run() {
                SQLiteDatabase sqLiteDatabase = SQLiteDatabase.openOrCreateDatabase(path, null);
                mList = new ArrayList<>();
                WordsBean bean;
                @SuppressLint("Recycle") Cursor cursor = sqLiteDatabase.query(DictionaryHelper.TABLE_NAME, null, null, null, null, null, null);
                while (cursor.moveToNext()) {
                    bean = new WordsBean();
                    bean.setWord(cursor.getString(0));
                    bean.setChinese(cursor.getString(1));
                    mList.add(bean);
                }
                //整书单词插入数据库
                SQLiteUtils.insert(context, mList);
            }
        }).start();
    }

}
