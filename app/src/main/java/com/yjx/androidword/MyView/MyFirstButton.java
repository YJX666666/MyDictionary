package com.yjx.androidword.MyView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;
import com.yjx.androidword.R;

@SuppressLint("AppCompatCustomView")
public class MyFirstButton extends Button {

    private void set() {
        //字体阴影
        super.setShadowLayer(6, 3, 3, R.color.colorShadowBlack);
        //字体样式
        super.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        //背景
        super.setBackgroundResource(R.drawable.btn_background);
        //字体颜色
        super.setTextColor(getResources().getColor(R.color.colorWhite));
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
