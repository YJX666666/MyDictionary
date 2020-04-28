package com.yjx.androidword.Activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yjx.androidword.BaseActivity;
import com.yjx.androidword.Bean.WordsBean;
import com.yjx.androidword.R;
import com.yjx.androidword.SQLiteHelper.SQWordsHelper;
import com.yjx.androidword.Utils.DialogUtils;
import com.yjx.androidword.Utils.WordsUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Choose2Activity extends BaseActivity implements View.OnClickListener {

    private TextView mTxvWord;
    private Button mBtnA;
    private Button mBtnB;
    private Button mBtnC;
    private Button mBtnD;

    private String mString_English = "";
    private String mString_Chinese = "";

    private Random mRandom = new Random();
    private TextView mTxvGrasp;
    private SQWordsHelper mSQWordsHelper;
    private SQLiteDatabase mSQLiteDatabase;

    private TextView mTxvEnglish;
    private TextView mTxvChinese;
    private TextView mTxvDel;
    private TextView mTxvModify;
    private List<WordsBean> mListWords = new ArrayList<>();
    private Button mBtnNext;


    @Override
    protected void initData() {
        mBtnA.setOnClickListener(this);
        mBtnB.setOnClickListener(this);
        mBtnC.setOnClickListener(this);
        mBtnD.setOnClickListener(this);
        mBtnNext.setOnClickListener(this);
        mTxvGrasp.setOnClickListener(this);
        mSQWordsHelper = new SQWordsHelper(mContext);
        mSQLiteDatabase = mSQWordsHelper.getWritableDatabase();
        mBtnNext.setVisibility(View.GONE);
        //获取需要测试的单词和翻译
        setAnswer();

    }

    private void setAnswer() {
        //获取四组数据
        mListWords = WordsUtils.getChoose(mContext);
        //需要测试的组赋值
        mString_English = mListWords.get(0).getEnglish();
        mString_Chinese = mListWords.get(0).getChinses();
        //单词发送到TextView上
        mTxvWord.setText(mString_Chinese);
        //随机选取一个按钮放中文答案,其他按钮放上获取的随机非正确答案
        switch (mRandom.nextInt(4) % (4) + 1) {
            case 1:
                mBtnA.setText(mString_English);
                mBtnB.setText(mListWords.get(1).getEnglish());
                mBtnC.setText(mListWords.get(2).getEnglish());
                mBtnD.setText(mListWords.get(3).getEnglish());
                break;
            case 2:
                mBtnB.setText(mString_English);
                mBtnA.setText(mListWords.get(1).getEnglish());
                mBtnC.setText(mListWords.get(2).getEnglish());
                mBtnD.setText(mListWords.get(3).getEnglish());
                break;
            case 3:
                mBtnC.setText(mString_English);
                mBtnA.setText(mListWords.get(1).getEnglish());
                mBtnB.setText(mListWords.get(2).getEnglish());
                mBtnD.setText(mListWords.get(3).getEnglish());
                break;
            case 4:
                mBtnD.setText(mString_English);
                mBtnA.setText(mListWords.get(1).getEnglish());
                mBtnB.setText(mListWords.get(2).getEnglish());
                mBtnC.setText(mListWords.get(3).getEnglish());
                break;
        }
    }

    //重置按钮
    private void reSet() {
        mBtnA.setBackground(getResources().getDrawable(R.drawable.btn_background));
        mBtnB.setBackground(getResources().getDrawable(R.drawable.btn_background));
        mBtnC.setBackground(getResources().getDrawable(R.drawable.btn_background));
        mBtnD.setBackground(getResources().getDrawable(R.drawable.btn_background));
        mBtnA.setEnabled(true);
        mBtnB.setEnabled(true);
        mBtnC.setEnabled(true);
        mBtnD.setEnabled(true);
    }

    //选择判断
    @SuppressLint("SetTextI18n")
    private void getJudg(Button btn, String str_btn) {

        if (str_btn.equals(mString_Chinese)) {

            //选择正确
            btn.setBackground(getResources().getDrawable(R.drawable.btn_green));

            if (btn == mBtnA) {
                mBtnB.setText(mListWords.get(1).getChinses() + " ： " + mListWords.get(1).getEnglish());
                mBtnC.setText(mListWords.get(2).getChinses() + " ： " + mListWords.get(2).getEnglish());
                mBtnD.setText(mListWords.get(3).getChinses() + " ： " + mListWords.get(3).getEnglish());
            } else if (btn == mBtnB) {
                mBtnA.setText(mListWords.get(1).getChinses() + " ： " + mListWords.get(1).getEnglish());
                mBtnC.setText(mListWords.get(2).getChinses() + " ： " + mListWords.get(2).getEnglish());
                mBtnD.setText(mListWords.get(3).getChinses() + " ： " + mListWords.get(3).getEnglish());
            } else if (btn == mBtnC) {
                mBtnA.setText(mListWords.get(1).getChinses() + " ： " + mListWords.get(1).getEnglish());
                mBtnB.setText(mListWords.get(2).getChinses() + " ： " + mListWords.get(2).getEnglish());
                mBtnD.setText(mListWords.get(3).getChinses() + " ： " + mListWords.get(3).getEnglish());
            } else if (btn == mBtnD) {
                mBtnA.setText(mListWords.get(1).getChinses() + " ： " + mListWords.get(1).getEnglish());
                mBtnB.setText(mListWords.get(2).getChinses() + " ： " + mListWords.get(2).getEnglish());
                mBtnC.setText(mListWords.get(3).getChinses() + " ： " + mListWords.get(3).getEnglish());
            }

        } else {

            //选择错误
            btn.setBackground(getResources().getDrawable(R.drawable.btn_red));

            if (btn != mBtnA && mBtnA.getText().toString().equals(mString_Chinese)) {
                mBtnA.setBackground(getResources().getDrawable(R.drawable.btn_green2));
                mBtnB.setText(mListWords.get(1).getEnglish() + " ： " + mListWords.get(1).getChinses());
                mBtnC.setText(mListWords.get(2).getEnglish() + " ： " + mListWords.get(2).getChinses());
                mBtnD.setText(mListWords.get(3).getEnglish() + " ： " + mListWords.get(3).getChinses());
            } else if (btn != mBtnB && mBtnB.getText().toString().equals(mString_Chinese)) {
                mBtnB.setBackground(getResources().getDrawable(R.drawable.btn_green2));
                mBtnA.setText(mListWords.get(1).getEnglish() + " ： " + mListWords.get(1).getChinses());
                mBtnC.setText(mListWords.get(2).getEnglish() + " ： " + mListWords.get(2).getChinses());
                mBtnD.setText(mListWords.get(3).getEnglish() + " ： " + mListWords.get(3).getChinses());
            } else if (btn != mBtnC && mBtnC.getText().toString().equals(mString_Chinese)) {
                mBtnC.setBackground(getResources().getDrawable(R.drawable.btn_green2));
                mBtnA.setText(mListWords.get(1).getEnglish() + " ： " + mListWords.get(1).getChinses());
                mBtnB.setText(mListWords.get(2).getEnglish() + " ： " + mListWords.get(2).getChinses());
                mBtnD.setText(mListWords.get(3).getEnglish() + " ： " + mListWords.get(3).getChinses());
            } else if (btn != mBtnD && mBtnD.getText().toString().equals(mString_Chinese)) {
                mBtnD.setBackground(getResources().getDrawable(R.drawable.btn_green2));
                mBtnA.setText(mListWords.get(1).getEnglish() + " ： " + mListWords.get(1).getChinses());
                mBtnB.setText(mListWords.get(2).getEnglish() + " ： " + mListWords.get(2).getChinses());
                mBtnC.setText(mListWords.get(3).getEnglish() + " ： " + mListWords.get(3).getChinses());
            }

        }
        //选择完以后设置按钮不可点击
        mBtnA.setEnabled(false);
        mBtnB.setEnabled(false);
        mBtnC.setEnabled(false);
        mBtnD.setEnabled(false);
        //选择完以后把右下角 下一个 按钮显示出来
        mBtnNext.setVisibility(View.VISIBLE);

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
        mTxvGrasp = findViewById(R.id.txv_grasp);
        mBtnNext = findViewById(R.id.btn_next);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_a:
                getJudg(mBtnA, mBtnA.getText().toString());
                break;
            case R.id.btn_b:
                getJudg(mBtnB, mBtnB.getText().toString());
                break;
            case R.id.btn_c:
                getJudg(mBtnC, mBtnC.getText().toString());
                break;
            case R.id.btn_d:
                getJudg(mBtnD, mBtnD.getText().toString());
                break;
            case R.id.btn_next://下一个
                reSet();
                setAnswer();
                mBtnNext.setVisibility(View.GONE);
                break;
            case R.id.txv_grasp:
                View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_words_menu, null);
                mTxvEnglish = view.findViewById(R.id.txv_english);
                mTxvChinese = view.findViewById(R.id.txv_chinese);
                mTxvDel = view.findViewById(R.id.txv_del);
                mTxvModify = view.findViewById(R.id.txv_modify);
                mTxvEnglish.setText("English：" + mString_English);
                mTxvChinese.setText("中文：" + mString_Chinese);
                mTxvDel.setText("掌握（删除）");
                mTxvModify.setText("取消");
                final Dialog dialog = DialogUtils.show(mContext, view);
                mTxvDel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        removeData(mString_English);
                        reSet();
                        dialog.dismiss();
                    }
                });
                mTxvModify.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                break;
        }
    }

    private void removeData(String str_del) {
        //数据库同步删除
        String clause = SQWordsHelper.WORD + "=?";
        mSQLiteDatabase.delete(SQWordsHelper.TABLE_NAME, clause, new String[]{str_del});
    }

}