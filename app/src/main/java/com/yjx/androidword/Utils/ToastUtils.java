package com.yjx.androidword.Utils;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

public class ToastUtils {

    private static Toast sToast;

    //即时消息
    public static void show(Context context, String msg) {
        //判断是否第一次弹窗
        if (sToast == null)
            sToast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        else
            sToast.setText(msg);
        sToast.show();
    }

    //重要的即时消息 自定义位置
    public static void show(Context context, String msg, int gravity) {
        //判断是否第一次弹窗
        if (sToast == null)
            sToast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
        else
            sToast.setText(msg);
        sToast.setGravity(gravity, 0, 0);
        sToast.show();
    }

    //长时间消息
    public static void showLong(Context context, String msg) {
        //判断是否第一次弹窗
        sToast.setDuration(Toast.LENGTH_LONG);
        if (sToast == null)
            sToast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
        else
            sToast.setText(msg);
        sToast.show();
    }

    public static void show(Context context, View view) {
        if (sToast == null)
            sToast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
        sToast.setView(view);
        sToast.show();
    }

}
