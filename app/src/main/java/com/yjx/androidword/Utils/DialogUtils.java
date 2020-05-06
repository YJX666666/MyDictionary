package com.yjx.androidword.Utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;

import com.yjx.androidword.Activity.AddWordsActivity;
import com.yjx.androidword.R;

public class DialogUtils {

    //自定义View对话框
    public static Dialog show(Context context, View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setView(view)
                .setCancelable(true);
        Dialog dialog = builder.show();
        dialog.getWindow().getDecorView().setBackground(null);
        return dialog;
    }

    //简单提示对话框
    public static void show(final Context context, String msg) {
        new AlertDialog.Builder(context)
                .setMessage(msg)
                .setTitle("温馨提示")
                .setIcon(R.mipmap.img_icon)
                .setCancelable(false)
                .setPositiveButton("关闭", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) { }})
                .setNegativeButton("去添加单词", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        JumpUtils.To(context, AddWordsActivity.class);
                    }
                })
                .show();
    }

}
