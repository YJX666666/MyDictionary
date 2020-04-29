package com.yjx.androidword.Activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.yjx.androidword.BaseActivity;
import com.yjx.androidword.Bean.WordsBean;
import com.yjx.androidword.R;
import com.yjx.androidword.SQLiteHelper.DictionaryHelper;
import com.yjx.androidword.Utils.DialogUtils;
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
    private SQLiteDatabase mSQLiteDatabase;

    @Override
    protected void initData() {
        mBtnNext.setOnClickListener(this);
        mTxvGrasp.setOnClickListener(this);
        mBtnNext.setText("确定");
        DictionaryHelper SQWordsHelper = new DictionaryHelper(mContext);
        mSQLiteDatabase = SQWordsHelper.getWritableDatabase();

        setData();

    }

    //答案判断
    private void getJudg(String answer) {

        if (answer.equals(mList.get(0).getEnglish())) {
            mEditAnswer.setTextColor(Color.GREEN);
        } else {
            mEditAnswer.setTextColor(Color.RED);
            mTxvAnswer.setText(mList.get(0).getEnglish());
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
        switch (v.getId()) {
            case R.id.btn_next:
                if (mBtnNext.getText().equals("确定")) {
                    if (!TextUtils.isEmpty(mEditAnswer.getText().toString()))
                        getJudg(mEditAnswer.getText().toString());
                    else
                        mTxvAnswer.setText("正确答案：" + mList.get(0).getEnglish());
                    mBtnNext.setText("下一个");
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
                txvEnglish.setText(mList.get(0).getChinses());
                txvChinese.setText(mList.get(0).getEnglish());
                txvDel.setText("掌握（删除）");
                txvModify.setText("取消");
                final Dialog dialog = DialogUtils.show(mContext, view);
                txvDel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        removeData(mList.get(0).getChinses());
                        if (cursorCount() == 0) {
                            finish();
                            ToastUtils.show(mContext, "恭喜你，已经掌握所有单词！");
                        }else{
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

    //数据库同步删除
    private void removeData(String str_del) {
        String clause = DictionaryHelper.ENGLISH + "=?";
        mSQLiteDatabase.delete(DictionaryHelper.TABLE_NAME, clause, new String[]{str_del});

    }

    //判断数据库是否为空 true为不空 false为空
    @SuppressLint("Recycle")
    private int cursorCount() {
        Cursor cursor = mSQLiteDatabase.query(DictionaryHelper.TABLE_NAME, null, null, null, null, null, null);
        return cursor.getCount();
    }

}
