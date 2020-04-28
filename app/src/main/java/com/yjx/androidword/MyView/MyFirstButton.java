package com.yjx.androidword.MyView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.Button;

import com.yjx.androidword.R;

@SuppressLint("AppCompatCustomView")
public class MyFirstButton extends Button {

    //字体阴影
    private int mShadowRadius =6;
    private int mShadowDx = 3;
    private int mShadowDy= 3;
    private int mShadowColor= getResources().getColor(R.color.colorShadowBlack);
    //字体样式
    private Typeface mTypeface= Typeface.DEFAULT;
    private int mStyle= Typeface.BOLD;
    //背景
    private Drawable mBackground= getResources().getDrawable(R.drawable.btn_background);
    //字体颜色
    private int mTextColor= getResources().getColor(R.color.colorWhite);

    private void set() {
        //阴影
        super.setShadowLayer(mShadowRadius, mShadowDx, mShadowDy, mShadowColor);
        //字体样式
        super.setTypeface(mTypeface, mStyle);
        //背景
        super.setBackground(mBackground);
        //字体颜色
        super.setTextColor(mTextColor);
    }

    public MyFirstButton(Context context) {
        super(context);
        set();
    }
    public MyFirstButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        set();
    }
    public MyFirstButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        set();
    }
}
