package com.yjx.androidword.Utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.yjx.androidword.Bean.WordsBean;
import com.yjx.androidword.SQLiteHelper.DictionaryHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WordsUtils {

    /**
     * 用List获取到词库所有单词
     * 然后用 shuffle()方法打乱顺序（达到随机效果）
     * @return 打乱顺序的词库
     */
    public static List<WordsBean> get(Context context) {
        List<WordsBean> list = new ArrayList<>();
        DictionaryHelper helper = new DictionaryHelper(context);
        SQLiteDatabase database = helper.getWritableDatabase();
        WordsBean bean;
        @SuppressLint("Recycle")
        Cursor cursor = database.query(DictionaryHelper.TABLE_NAME, null, null, null, null, null, null);

        //获取总词库
        while (cursor.moveToNext()) {
            bean = new WordsBean();
            String english = cursor.getString(0);
            String chinese = cursor.getString(1);
            bean.setEnglish(english);
            bean.setChinses(chinese);
            list.add(bean);
        }

        //打乱List顺序
        Collections.shuffle(list);

        return list;
    }

}
