package com.yjx.androidword.Utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.view.View;

import com.yjx.androidword.Activity.AddWordsActivity;
import com.yjx.androidword.R;

public class DialogUtils {

    //自定义View对话框
    public static AlertDialog show(Context context, View view) {
        AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.setView(view);
        dialog.setCancelable(true);
        //设置外部背景，dialog外面的那部分黑色
        dialog.getWindow().getDecorView().setBackground(null);
        dialog.show();
        return dialog;
    }

    //简单提示对话框（去添加单词）
    public static void show(final Context context, String msg, final String btn_str) {
        new AlertDialog.Builder(context)
                .setMessage(msg)
                .setTitle("温馨提示")
                .setIcon(R.mipmap.img_icon)
                .setCancelable(false)
                .setPositiveButton("关闭", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setNegativeButton(btn_str, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (btn_str.equals("去添加单词"))
                            JumpUtils.To(context, AddWordsActivity.class);
                        else if (btn_str.equals("去连接网络"))
                            context.startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
                    }
                })
                .show();
    }

}
