package com.yjx.androidword.Utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.widget.TextView;

/**
 * @Time : 2020/5/18 17:01
 * @Author : Android_小黑
 * @File : CopyTextUtils.java
 * @Software : Android Studio
 */
// 复制内容到剪切板工具类
public class CopyTextUtils {

    // 复制内容至剪切板
    public static void copy(Context context, TextView txv) {
        //剪切板管理器
        ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);

        cm.setPrimaryClip(ClipData.newPlainText(null, String.valueOf(txv.getText())));

        ToastUtils.show(context, "复制成功！");
    }

}
