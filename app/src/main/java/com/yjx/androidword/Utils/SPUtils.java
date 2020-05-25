package com.yjx.androidword.Utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * @Time : 2020/5/18 18:04
 * @Author : Android_小黑
 * @File : SPUtils.java
 * @Software : Android Studio
 */
// 轻量储存工具类
public class SPUtils {

    /**
     * 存储数据
     * <p>
     * 通过判断需要存储对象的类型
     * 来调用适当的put()方法
     *
     * @param context 上下文
     * @param key     键
     * @param object  值
     */
    public static void set(Context context, String key, Object object) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("sp", Context.MODE_PRIVATE);
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor edit = sharedPreferences.edit();

        if (object instanceof String) {
            edit.putString(key, (String) object);
        } else if (object instanceof Integer) {
            edit.putInt(key, (Integer) object);
        } else if (object instanceof Boolean) {
            edit.putInt(key, (Integer) object);
        } else if (object instanceof Float) {
            edit.putFloat(key, (Float) object);
        } else if (object instanceof Long) {
            edit.putLong(key, (Long) object);
        }
//        else if (object instanceof Set) {
//            edit.putStringSet(key, (Set<String>) object);
//        }
        edit.apply();
    }

    /**
     * 获取数据
     * <p>
     * 通过判断需要存储对象默认值的类型
     * 来调用适当的get()方法
     *
     * @param context  上下文
     * @param key      键
     * @param defValue 默认值
     */
    public static Object get(Context context, String key, Object defValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("sp", Context.MODE_PRIVATE);

        if (defValue instanceof String) {
            return sharedPreferences.getString(key, (String) defValue);
        } else if (defValue instanceof Integer) {
            return sharedPreferences.getInt(key, (Integer) defValue);
        } else if (defValue instanceof Boolean) {
            return sharedPreferences.getBoolean(key, (Boolean) defValue);
        } else if (defValue instanceof Float) {
            return sharedPreferences.getFloat(key, (Float) defValue);
        } else if (defValue instanceof Long) {
            return sharedPreferences.getLong(key, (Long) defValue);
        }
//        else if (defValue instanceof Set) {
//            return sharedPreferences.getStringSet(key, (Set<String>) defValue);
//        }
        return null;
    }


}
