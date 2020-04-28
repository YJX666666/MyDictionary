package com.yjx.androidword.Activity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;

import com.yjx.androidword.BaseActivity;
import com.yjx.androidword.Bean.WordsBean;
import com.yjx.androidword.R;
import com.yjx.androidword.Utils.WordsUtils;

import java.util.ArrayList;
import java.util.List;

public class Fill2Activity extends BaseActivity implements View.OnClickListener {

    private android.widget.TextView mTxvWord;
    private com.yjx.androidword.MyView.MyEditText mEditAnswer;
    private com.yjx.androidword.MyView.MyFirstButton mBtnNext;
    private android.widget.TextView mTxvGrasp;

    private List<WordsBean> mList = new ArrayList<>();
    private android.widget.TextView mTxvAnswer;

    @Override
    protected void initData() {
        mBtnNext.setOnClickListener(this);
        mBtnNext.setText("确定");

        mList = WordsUtils.getFill(mContext);

        setData();
        
    }

    //答案判断
    @SuppressLint("SetTextI18n")
    private void getJudg(String answer) {

        if (answer.equals(mList.get(0).getEnglish())) {
            mEditAnswer.setTextColor(Color.GREEN);
        } else {
            mEditAnswer.setTextColor(Color.RED);
            mTxvAnswer.setText("正确答案："+mList.get(0).getEnglish());
        }

    }

    //把获取到的单词传到TextView上
    private void setData() {
        mList = WordsUtils.getFill(mContext);
        mTxvWord.setText(mList.get(0).getChinses());
        mTxvAnswer.setText("");
        mEditAnswer.setTextColor(Color.BLACK);
        mEditAnswer.setText("");
    }

    @Override
    protected int initLayout() {
        return R.layout.layout_fill;
    }

    @Override
    protected void initView() {
        mTxvWord = findViewById(R.id.txv_word);
        mEditAnswer = findViewById(R.id.edit_answer);
        mBtnNext = findViewById(R.id.btn_next);
        mTxvGrasp = findViewById(R.id.txv_grasp);
        mTxvAnswer = findViewById(R.id.txv_answer);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btn_next) {
            if (mBtnNext.getText().equals("确定")) {

                if (!TextUtils.isEmpty(mEditAnswer.getText().toString()))
                    getJudg(mEditAnswer.getText().toString());
                else
                    mTxvAnswer.setText("正确答案：" + mList.get(0).getEnglish());

                mBtnNext.setText("下一个");

            } else {
                setData();
                mBtnNext.setText("确定");
            }
        }

    }
}
