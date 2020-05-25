package com.yjx.androidword.MyView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.EditText;

import com.yjx.androidword.R;

@SuppressLint("AppCompatCustomView")
public class MyEditText extends EditText {

    private void set() {
        //单行输出
        setSingleLine(true);
        //字体大小
        setTextSize(16);
        //设置内部元素对齐方式
        setGravity(Gravity.CENTER);
        //设置背景
        setBackgroundResource(R.drawable.edit_stroke);
    }


    public MyEditText(Context context) {
        super(context);
        set();
    }

    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        set();
    }

    public MyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        set();
    }
}
