package com.yjx.androidword.Activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.yjx.androidword.BaseActivity;
import com.yjx.androidword.Bean.WordsBean;
import com.yjx.androidword.R;
import com.yjx.androidword.Utils.DialogUtils;
import com.yjx.androidword.Utils.SQLiteUtils;
import com.yjx.androidword.Utils.ToastUtils;
import com.yjx.androidword.Utils.WordsUtils;

import java.util.ArrayList;
import java.util.List;

public class FillC2EActivity extends BaseActivity implements View.OnClickListener {

    private TextView mTxvWord;
    private com.yjx.androidword.MyView.MyEditText mEditAnswer;
    private com.yjx.androidword.MyView.MyFirstButton mBtnNext;
    private TextView mTxvGrasp;
    private List<WordsBean> mList = new ArrayList<>();
    private TextView mTxvAnswer;
    //当前单词标记
    private int index = 0;

    @Override
    protected void initData() {
        mBtnNext.setOnClickListener(this);
        mTxvGrasp.setOnClickListener(this);
        mBtnNext.setText("确定");
        //进入时获取词库
        mList = WordsUtils.get(mContext);
        setData();
    }

    //答案判断
    @SuppressLint("SetTextI18n")
    private void getJudg(String answer) {

        if (answer.equals(mList.get(index).getEnglish())) {
            mEditAnswer.setTextColor(Color.GREEN);
        } else {
            mEditAnswer.setTextColor(Color.RED);
            mTxvAnswer.setText("正确答案：" + mList.get(index).getEnglish());
        }

    }

    //把获取到的单词传到TextView上
    private void setData() {
        //防止背到最后一个单词导致IndexOutOfBoundsException
        if (index == SQLiteUtils.cursorCount(mContext) - 1) {
            index = 0;
        } else {
            index++;
        }
        //传入一个单词
        mTxvWord.setText(mList.get(index).getChinses());
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
        switch (v.getId()) {
            case R.id.btn_next:
                if (mBtnNext.getText().equals("确定")) {
                    if (!TextUtils.isEmpty(mEditAnswer.getText().toString()))
                        getJudg(mEditAnswer.getText().toString());
                    else
                        mTxvAnswer.setText("正确答案：" + mList.get(index).getEnglish());
                    mBtnNext.setText(R.string.str_next);
                } else {
                    mBtnNext.setText("确定");
                    setData();
                }

                break;

            case R.id.txv_grasp://掌握了单词
                View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_words_menu, null);
                TextView txvEnglish = view.findViewById(R.id.edit_english);
                TextView txvChinese = view.findViewById(R.id.edit_chinese);
                TextView txvDel = view.findViewById(R.id.txv_del);
                TextView txvModify = view.findViewById(R.id.txv_modify);
                txvEnglish.setText(mList.get(index).getEnglish());
                txvChinese.setText(mList.get(index).getChinses());
                txvDel.setText("掌握（删除）");
                txvModify.setText("取消");
                final Dialog dialog = DialogUtils.show(mContext, view);
                txvDel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //数据库删除此单词
                        SQLiteUtils.delete(mContext, mList.get(index).getEnglish());
                        //当词库单词量不足1时，返回主页面并提示
                        if (SQLiteUtils.cursorCount(mContext) == 0) {
                            finish();
                            ToastUtils.showLong(mContext, "恭喜你，已经掌握所有单词！");
                        } else {
                            //删除了单词以后需要重新获取词库
                            mList = WordsUtils.get(mContext);
                            setData();
                        }
                        dialog.dismiss();
                    }
                });
                txvModify.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                break;
        }
    }

}