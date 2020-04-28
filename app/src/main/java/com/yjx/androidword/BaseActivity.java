package com.yjx.androidword;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    protected Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initWindow();
        super.onCreate(savedInstanceState);
        mContext = BaseActivity.this;
        setContentView(initLayout());
        //控件声明
        initView();
        //数据
        initData();

    }

    protected abstract void initData();

    protected void initWindow() {
    }

    protected abstract int initLayout();

    protected abstract void initView();

}
