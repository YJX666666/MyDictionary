package com.yjx.androidword.Utils;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.yjx.androidword.Bean.WordsBean;
import com.yjx.androidword.SQLiteHelper.DictionaryHelper;

import java.util.List;

public class SQLiteUtils {

    private static DictionaryHelper sHelper;
    private static SQLiteDatabase sDatabase;

    //用事务插入数据库（批量）
    public static void insert(Context context, List<WordsBean> list) {
        DictionaryHelper sHelper = new DictionaryHelper(context);
        sDatabase = sHelper.getWritableDatabase();
        //开始事务
        sDatabase.beginTransaction();
        Log.d("SQL", "insert: " + list.size());
        try {
            //多次插入
            for (int i = 0; i < list.size(); i++) {
                //原生语句执行效率更高
                sDatabase.execSQL("insert into words(word,chinese) values('" + list.get(i).getWord() + "','" + list.get(i).getChinese() + "')");
//                ContentValues cv = new ContentValues();
//                cv.put(DictionaryHelper.ENGLISH, list.get(i).getWord());
//                cv.put(DictionaryHelper.CHINESE, list.get(i).getChinese());
//                database.insert(DictionaryHelper.TABLE_NAME, null, cv);
            }
            //事务开始成功
            sDatabase.setTransactionSuccessful();
        } catch (SQLException e) {
            e.printStackTrace();
            Log.d("SQLiteUtils", "insert: IO错误");
        } finally {
            //最后要结束事务
            sDatabase.endTransaction();
            Log.d("SQLiteUtils", "插入成功");
        }

    }

    //数据库数据个数
    public static int cursorCount(Context context) {
        DictionaryHelper sHelper = new DictionaryHelper(context);
        sDatabase = sHelper.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = sDatabase.query(DictionaryHelper.TABLE_NAME, null, null, null, null, null, null);
        return cursor.getCount();
    }


    //删除数据
    public static void delete(Context context, String strDel) {
        DictionaryHelper sHelper = new DictionaryHelper(context);
        sDatabase = sHelper.getWritableDatabase();
        String clause = DictionaryHelper.ENGLISH + "=?";
        sDatabase.delete(DictionaryHelper.TABLE_NAME, clause, new String[]{strDel});
    }

    //清空数据
    public static int calern(Context context) {
        sHelper = new DictionaryHelper(context);
        sDatabase = sHelper.getWritableDatabase();
        return sDatabase.delete(DictionaryHelper.TABLE_NAME, null, null);
    }

    public static void upData(ContentValues cv, String oldEnglish) {
        sDatabase.update(DictionaryHelper.TABLE_NAME, cv, "word=?", new String[]{oldEnglish});
    }

}
