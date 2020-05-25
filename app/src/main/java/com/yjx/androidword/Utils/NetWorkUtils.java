package com.yjx.androidword.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @Time : 2020/5/17 13:32
 * @Author : Android_小黑
 * @File : NetWorkUtils.java
 * @Software : Android Studio
 */
//网络连接判断工具类
public class NetWorkUtils {

    //返回一个boolean值，true代表有网络连接，false代表无网络连接
    public static boolean check(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info != null)
            return info.isConnected();
        return false;
    }

}
