package com.yjx.androidword.SQLiteHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DictionaryHelper extends SQLiteOpenHelper {

    public static final String ENGLISH = "word";
    public static final String CHINESE = "chinese";
    public static final String DB_NAME = ENGLISH + "s.db";
    public static final String TABLE_NAME = ENGLISH + "s";

    public DictionaryHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(" + ENGLISH + " text," + CHINESE + " text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}
