package com.yjx.androidword.MyView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

import com.yjx.androidword.R;

@SuppressLint("AppCompatCustomView")
public class MyBookButton extends Button {

    private void set() {
        //背景  书的图标
        super.setBackgroundResource(R.mipmap.img_book);
        //字体阴影
        super.setShadowLayer(6, 3, 3, R.color.colorShadowBlack);
        //字体样式
        super.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        //字体大小
        super.setTextSize(14);
        //字体颜色
        super.setTextColor(getResources().getColor(R.color.colorWhite));
    }

    public MyBookButton(Context context) {
        super(context);
        set();
    }

    public MyBookButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        set();
    }

    public MyBookButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        set();
    }
}
