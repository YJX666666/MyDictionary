package com.yjx.androidword.Utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.View;

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


}
