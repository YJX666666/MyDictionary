package com.yjx.androidword.MyView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.Button;

import com.yjx.androidword.R;

/**
 * @Time : 2020/5/18 13:24
 * @Author : Android_小黑
 * @File : MyTranslateButton.java
 * @Software : Android Studio
 */
@SuppressLint("AppCompatCustomView")
public class MyTranslateButton extends Button {

    private void set() {
        //背景
        super.setBackgroundResource(R.drawable.btn_first_bg);
        //设置内边距
        super.setPadding(20, 0, 20, 0);
        //字体大小
        super.setTextSize(30);
        //字体格式
        super.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        //字体阴影
        super.setShadowLayer(6, 3, 3, R.color.colorShadowBlack);
        //内容对齐方式
        super.setGravity(Gravity.CENTER);
    }


    public MyTranslateButton(Context context) {
        super(context);
        set();
    }

    public MyTranslateButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        set();
    }

    public MyTranslateButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        set();
    }

    public MyTranslateButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        set();
    }
}
