package com.yjx.androidword.Activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.yjx.androidword.BaseActivity;
import com.yjx.androidword.R;
import com.yjx.androidword.SQLiteHelper.DictionaryHelper;
import com.yjx.androidword.Utils.DialogUtils;
import com.yjx.androidword.Utils.JumpUtils;

public class FirstActivity extends BaseActivity implements View.OnClickListener {

    private com.yjx.androidword.MyView.MyFirstButton mBtnModeChoose;
    private com.yjx.androidword.MyView.MyFirstButton mBtnModeFill;
    private com.yjx.androidword.MyView.MyFirstButton mBtnAddwords;
    private android.widget.TextView mTxvAbout;
    private com.yjx.androidword.MyView.MyFirstButton mBtnModeChoose2;
    private com.yjx.androidword.MyView.MyFirstButton mBtnSearch;
    private com.yjx.androidword.MyView.MyFirstButton mBtnDescription;
    private com.yjx.androidword.MyView.MyFirstButton mBtnModeFill2;
    private SQLiteDatabase mSQLiteDatabase;
    private TextView mTxvModeChoose;
    private TextView mTxvModeFill;
    private TextView mTxvDescription;
    private TextView mTxvDictionary;


    @Override
    protected void initData() {
        mBtnAddwords.setOnClickListener(this);
        mBtnModeChoose.setOnClickListener(this);
        mBtnModeChoose2.setOnClickListener(this);
        mBtnModeFill.setOnClickListener(this);
        mBtnModeFill2.setOnClickListener(this);
        mTxvAbout.setOnClickListener(this);
        mBtnDescription.setOnClickListener(this);
        mBtnSearch.setOnClickListener(this);
        mTxvAbout.setOnClickListener(this);
        //词库数据库
        DictionaryHelper SQHelper = new DictionaryHelper(mContext);
        mSQLiteDatabase = SQHelper.getWritableDatabase();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_mode_choose://选择 英-中
                if (cursorCount() > 4)
                    JumpUtils.To(mContext, ChooseE2CActivity.class);
                else
                    Toast.makeText(mContext, "词库内单词低于5个，无法进入此模式！请先添加！", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btn_mode_choose2://选择 中-英
                if (cursorCount() > 4)
                    JumpUtils.To(mContext, ChooseC2EActivity.class);
                else
                    Toast.makeText(mContext, "词库内单词低于5个，无法进入此模式！请先添加！", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btn_mode_fill://填写 英-中
                if (cursorCount() != 0)
                    JumpUtils.To(mContext, FillE2CActivity.class);
                else
                    Toast.makeText(mContext, "词库内没有单词，请先添加！", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btn_mode_fill2://填写 中-英
                if (cursorCount() != 0)
                    JumpUtils.To(mContext, FillC2EActivity.class);
                else
                    Toast.makeText(mContext, "词库内没有单词，请先添加！", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btn_addwords://添加单词
                JumpUtils.To(mContext, AddWordsActivity.class);
                break;

            case R.id.btn_search://查询词库
                if (cursorCount() != 0)
                    JumpUtils.To(mContext, DictionaryActivity.class);
                else
                    Toast.makeText(mContext, "词库内没有单词，请先添加！", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btn_description://软件说明
                showDescription();
                break;

            case R.id.txv_about://关于
                View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_about, null);
                Dialog dialog = DialogUtils.show(mContext, view);
                break;

            default:
                Toast.makeText(mContext, "攻城狮正在努力开发中！", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    //获取词库数量
    @SuppressLint("Recycle")
    private int cursorCount() {
        Cursor cursor = mSQLiteDatabase.query(DictionaryHelper.TABLE_NAME, null, null, null, null, null, null);
        return cursor.getCount();
    }


    //软件说明对话框
    public void showDescription() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_description, null);
        //软件说明
        Button btnDismiss = view.findViewById(R.id.btn_dismiss);
        mTxvModeChoose = view.findViewById(R.id.txv_mode_choose);
        mTxvModeFill = view.findViewById(R.id.txv_mode_fill);
        mTxvDescription = view.findViewById(R.id.txv_description);
        mTxvDictionary = view.findViewById(R.id.txv_dictionary);
        final Dialog dialog = DialogUtils.show(mContext, view);
        btnDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击右上角 × 关闭弹窗
                dialog.dismiss();
            }
        });
        mTxvModeChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击选择模式
                mTxvModeChoose.setBackgroundResource(R.drawable.txv_stroke);
                mTxvModeFill.setBackground(null);
                mTxvDictionary.setBackground(null);
                mTxvDescription.setText(R.string.str_descrip_choose);
            }
        });
        mTxvModeFill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击填空模式
                mTxvModeFill.setBackgroundResource(R.drawable.txv_stroke);
                mTxvModeChoose.setBackground(null);
                mTxvDictionary.setBackground(null);
                mTxvDescription.setText(R.string.str_descrip_fill);
            }
        });
        mTxvDictionary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTxvDictionary.setBackgroundResource(R.drawable.txv_stroke);
                mTxvModeChoose.setBackground(null);
                mTxvModeFill.setBackground(null);
                mTxvDescription.setText(R.string.str_descrip_dictionary);
            }
        });

    }

    @Override
    protected int initLayout() {
        return R.layout.layout_first;
    }

    @Override
    protected void initView() {
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