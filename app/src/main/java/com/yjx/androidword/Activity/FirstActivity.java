package com.yjx.androidword.Activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Toast;

import com.yjx.androidword.BaseActivity;
import com.yjx.androidword.R;
import com.yjx.androidword.SQLiteHelper.SQWordsHelper;
import com.yjx.androidword.Utils.JumpUtils;

public class FirstActivity extends BaseActivity implements View.OnClickListener {

    private com.yjx.androidword.MyView.MyFirstButton mBtnWordselect;
    private com.yjx.androidword.MyView.MyFirstButton mBtnModeChoose;
    private com.yjx.androidword.MyView.MyFirstButton mBtnModeFill;
    private com.yjx.androidword.MyView.MyFirstButton mBtnAddwords;
    private android.widget.TextView mTxvAbout;
    private com.yjx.androidword.MyView.MyFirstButton mBtnModeChoose2;

    private SQWordsHelper mSQHelper;
    private SQLiteDatabase mSQLiteDatabase;
    private Cursor mCursor;
    private com.yjx.androidword.MyView.MyFirstButton mBtnSearch;
    private com.yjx.androidword.MyView.MyFirstButton mBtnDescription;
    private com.yjx.androidword.MyView.MyFirstButton mBtnModeFill2;

    @Override
    protected void initData() {
        mBtnAddwords.setOnClickListener(this);
        mBtnWordselect.setOnClickListener(this);
        mBtnModeChoose.setOnClickListener(this);
        mBtnModeChoose2.setOnClickListener(this);
        mBtnModeFill.setOnClickListener(this);
        mBtnModeFill2.setOnClickListener(this);
        mTxvAbout.setOnClickListener(this);
        mBtnDescription.setOnClickListener(this);
        mBtnSearch.setOnClickListener(this);
        mSQHelper = new SQWordsHelper(mContext);
        mSQLiteDatabase = mSQHelper.getWritableDatabase();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_mode_choose://选择 英-中

                if (isEmpty4()) {
                    JumpUtils.To(mContext, ChooseActivity.class);
                } else {
                    Toast.makeText(mContext, "词库内单词低于4个，无法进入此模式！请先添加！", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btn_mode_choose2://选择 中-英

                if (isEmpty4()) {
                    JumpUtils.To(mContext, Choose2Activity.class);
                } else {
                    Toast.makeText(mContext, "词库内单词低于4个，无法进入此模式！请先添加！", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btn_mode_fill://填写 英-中

                if (isEmpty()) {
                    JumpUtils.To(mContext, FillActivity.class);
                } else {
                    Toast.makeText(mContext, "词库内没有单词，请先添加！", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btn_mode_fill2://填写 中-英

                if (isEmpty()) {
                    JumpUtils.To(mContext, Fill2Activity.class);
                } else {
                    Toast.makeText(mContext, "词库内没有单词，请先添加！", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btn_addwords://添加单词
                JumpUtils.To(mContext, AddWordsActivity.class);
                break;
            case R.id.btn_search://查询词库

                if (isEmpty()) {
                    JumpUtils.To(mContext, WordsActivity.class);
                } else {
                    Toast.makeText(mContext, "词库内没有单词，请先添加！", Toast.LENGTH_SHORT).show();
                }

                break;
            default:
                Toast.makeText(mContext, "攻城狮正在努力开发中！", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    //判断数据库是否为空 true为不空 false为空
    private boolean isEmpty() {
        mCursor = mSQLiteDatabase.query(SQWordsHelper.TABLE_NAME, null, null, null, null, null, null);
        return mCursor.getCount() != 0;
    }


    //判断数据库是否超过4个
    private boolean isEmpty4() {
        mCursor = mSQLiteDatabase.query(SQWordsHelper.TABLE_NAME, null, null, null, null, null, null);
        return mCursor.getCount() >= 4;
    }

    @Override
    protected int initLayout() {
        return R.layout.layout_first;
    }

    @Override
    protected void initView() {
        mBtnWordselect = findViewById(R.id.btn_wordselect);
        mBtnModeChoose = findViewById(R.id.btn_mode_choose);
        mBtnModeFill = findViewById(R.id.btn_mode_fill);
        mBtnAddwords = findViewById(R.id.btn_addwords);
        mTxvAbout = findViewById(R.id.txv_about);
        mBtnModeChoose2 = findViewById(R.id.btn_mode_choose2);
        mBtnSearch = findViewById(R.id.btn_search);
        mBtnDescription = findViewById(R.id.btn_description);
        mBtnModeFill2 = findViewById(R.id.btn_mode_fill2);
    }

}