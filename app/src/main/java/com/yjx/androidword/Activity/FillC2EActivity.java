package com.yjx.androidword.Activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.yjx.androidword.Base.BaseActivity;
import com.yjx.androidword.Bean.WordsBean;
import com.yjx.androidword.R;
import com.yjx.androidword.Utils.DialogUtils;
import com.yjx.androidword.Utils.NotificationUtils;
import com.yjx.androidword.Utils.OverWordsNumUtils;
import com.yjx.androidword.Utils.SQLiteUtils;
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

        // 每次选完就可以加一个数量
        OverWordsNumUtils.add(mContext);

        if (answer.equals(mList.get(index).getWord())) {
            mEditAnswer.setTextColor(Color.GREEN);
        } else {
            mEditAnswer.setTextColor(Color.RED);
            mTxvAnswer.setText("正确答案：" + mList.get(index).getWord());
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
        mTxvWord.setText(mList.get(index).getChinese());
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
                        mTxvAnswer.setText("正确答案：" + mList.get(index).getWord());
                    mBtnNext.setText(R.string.str_next);
                } else {
                    mBtnNext.setText("确定");
                    setData();
                }

                break;

            case R.id.txv_grasp://掌握了单词
                @SuppressLint("InflateParams") View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_dictionary_menu, null);
                TextView txvEnglish = view.findViewById(R.id.edit_english);
                TextView txvChinese = view.findViewById(R.id.edit_from);
                TextView txvDel = view.findViewById(R.id.txv_del);
                TextView txvModify = view.findViewById(R.id.txv_modify);
                txvEnglish.setText(mList.get(index).getWord());
                txvChinese.setText(mList.get(index).getChinese());
                txvDel.setText("掌握（删除）");
                txvModify.setText("取消");
                final Dialog dialog = DialogUtils.show(mContext, view);
                txvDel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //数据库删除此单词
                        SQLiteUtils.delete(mContext, mList.get(index).getWord());
                        //当词库单词量不足1时，返回主页面并提示
                        if (SQLiteUtils.cursorCount(mContext) == 0) {
                            NotificationUtils.show(mContext,
                                    "温馨提示",
                                    "词库中没有单词，无法继续此模式，请添加单词！"
                                    , AddWordsActivity.class);
                            finish();
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