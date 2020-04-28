package com.yjx.androidword.SQLiteHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQWordsHelper extends SQLiteOpenHelper {

    public static final String WORD = "word";
    public static final String DB_NAME = WORD + "s.db";
    public static final String TABLE_NAME = WORD + "s";
    public static final String CHINESE = "chinese";


    public SQWordsHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(" + WORD + " text," + CHINESE + " text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}
