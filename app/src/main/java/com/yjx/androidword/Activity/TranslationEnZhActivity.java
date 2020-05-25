package com.yjx.androidword.Activity;

import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.yjx.androidword.Base.BaseActivity;
import com.yjx.androidword.R;
import com.yjx.androidword.Utils.BaiDuUtils;
import com.yjx.androidword.Utils.CopyTextUtils;
import com.yjx.androidword.Utils.DialogUtils;
import com.yjx.androidword.Utils.NetWorkUtils;
import com.yjx.androidword.Utils.ToastUtils;

public class TranslationEnZhActivity extends BaseActivity implements View.OnClickListener {

    private EditText mEditFrom;
    private TextView mTxvTo;
    private com.yjx.androidword.MyView.MyFirstButton mBtnTranslate;

    @Override
    protected void initData() {
        mBtnTranslate.setOnClickListener(this);
        mTxvTo.setOnClickListener(this);
    }

    private void setText(final TextView txv, final String text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                txv.setText(text);
            }
        });
    }

    //中译英
    private void translate() {
        if (TextUtils.isEmpty(mEditFrom.getText().toString()))
            ToastUtils.show(mContext, "需要翻译的内容不能为空噢！", Gravity.CENTER);
        else {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String text = BaiDuUtils.translate(mEditFrom.getText().toString());
                    setText(mTxvTo, text);
                }
            }).start();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_translate:
                if (NetWorkUtils.check(mContext))
                    translate();
                else
                    DialogUtils.show(mContext, "本功能为在线功能，请先连接网络再进行使用！", "去连接网络");
                break;
            case R.id.txv_to:// 点击翻译结果框后复制内容到剪切板
                if (!TextUtils.isEmpty(mTxvTo.getText().toString()))
                    CopyTextUtils.copy(mContext, mTxvTo);
                break;
        }

    }

    @Override
    protected int initLayout() {
        return R.layout.layout_translate_enzh;
    }

    @Override
    protected void initView() {
        mEditFrom = findViewById(R.id.edit_from);
        mTxvTo = findViewById(R.id.txv_to);
        mBtnTranslate = findViewById(R.id.btn_translate);
    }
}
