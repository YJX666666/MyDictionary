package com.yjx.androidword.Utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {

    private static Toast sToast;

    //即时消息
    public static void show(Context context, String msg) {
        //判断是否第一次弹窗
        if (sToast==null)
        sToast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        else
            sToast.setText(msg);
        sToast.show();
    }


}
