package com.yjx.androidword.Activity;

import com.yjx.androidword.BaseActivity;
import com.yjx.androidword.R;

public class Choose2Activity extends BaseActivity {
    private android.widget.TextView mTxvWord;
    private android.widget.Button mBtnA;
    private android.widget.Button mBtnB;
    private android.widget.Button mBtnC;
    private android.widget.Button mBtnD;

    @Override
    protected void initData() {

    }

    @Override
    protected int initLayout() {
        return R.layout.layout_choose;
    }

    @Override
    protected void initView() {
        mTxvWord = findViewById(R.id.txv_word);
        mBtnA = findViewById(R.id.btn_a);
        mBtnB = findViewById(R.id.btn_b);
        mBtnC = findViewById(R.id.btn_c);
        mBtnD = findViewById(R.id.btn_d);
    }
}
