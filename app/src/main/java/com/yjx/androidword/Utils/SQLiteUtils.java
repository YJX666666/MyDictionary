package com.yjx.androidword.Utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.yjx.androidword.SQLiteHelper.DictionaryHelper;

public class SQLiteUtils {

    //数据库数据个数
    public static int cursorCount(Context context) {
        DictionaryHelper helper = new DictionaryHelper(context);
        SQLiteDatabase database = helper.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = database.query(DictionaryHelper.TABLE_NAME, null, null, null, null, null, null);
        return cursor.getCount();
    }


    //删除数据
    public static void delete(Context context, String strDel) {
        DictionaryHelper helper = new DictionaryHelper(context);
        SQLiteDatabase database = helper.getWritableDatabase();
        String clause = DictionaryHelper.ENGLISH + "=?";
        database.delete(DictionaryHelper.TABLE_NAME, clause, new String[]{strDel});
    }

}
