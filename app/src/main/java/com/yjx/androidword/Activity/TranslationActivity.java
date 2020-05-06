package com.yjx.androidword.Activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.yjx.androidword.BaseActivity;
import com.yjx.androidword.R;
import com.yjx.androidword.Utils.ToastUtils;
import com.yjx.androidword.Utils.TranslateUtils;

public class TranslationActivity extends BaseActivity implements View.OnClickListener {

    private com.yjx.androidword.MyView.MyEditText mEditChinese;
    private com.yjx.androidword.MyView.MyFirstButton mBtnZh2en;
    private com.yjx.androidword.MyView.MyEditText mEditEnglish;
    private com.yjx.androidword.MyView.MyFirstButton mBtnEn2zh;
    private android.widget.TextView mTxvEnResult;
    private android.widget.TextView mTxvZhResult;

    @Override
    protected void initData() {
        mBtnZh2en.setOnClickListener(this);
        mBtnEn2zh.setOnClickListener(this);
    }

    private void setText(final TextView txv, final String text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                txv.setText(text);
            }
        });
    }

    //英译中
    private void en2zh() {
        if (TextUtils.isEmpty(mEditEnglish.getText().toString()))
            ToastUtils.show(mContext, "需要翻译的内容不能为空噢！");
        else {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String text = TranslateUtils.en2zh(mEditEnglish.getText().toString());
                    setText(mTxvZhResult, text);
                }
            }).start();
        }

    }

    //中译英
    private void zh2en() {
        if (TextUtils.isEmpty(mEditChinese.getText().toString()))
            ToastUtils.show(mContext, "需要翻译的内容不能为空噢！");
        else {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String text = TranslateUtils.zh2en(mEditChinese.getText().toString());
                    setText(mTxvEnResult, text);
                }
            }).start();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_zh2en://中译英
                zh2en();
                break;
            case R.id.btn_en2zh://英译中
                en2zh();
                break;
        }
    }

    @Override
    protected int initLayout() {
        return R.layout.layout_translation;
    }

    @Override
    protected void initView() {
        mEditChinese = findViewById(R.id.edit_chinese);
        mBtnZh2en = findViewById(R.id.btn_zh2en);
        mEditEnglish = findViewById(R.id.edit_english);
        mBtnEn2zh = findViewById(R.id.btn_en2zh);
        mTxvEnResult = findViewById(R.id.txv_en_result);
        mTxvZhResult = findViewById(R.id.txv_zh_result);
    }
}
