package com.yjx.androidword.Activity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.yjx.androidword.BaseActivity;
import com.yjx.androidword.R;
import com.yjx.androidword.Utils.DescriptionUtils;
import com.yjx.androidword.Utils.DialogUtils;
import com.yjx.androidword.Utils.JumpUtils;
import com.yjx.androidword.Utils.SQLiteUtils;

public class FirstActivity extends BaseActivity implements View.OnClickListener {

    private com.yjx.androidword.MyView.MyFirstButton mBtnModeChoose;
    private com.yjx.androidword.MyView.MyFirstButton mBtnModeFill;
    private com.yjx.androidword.MyView.MyFirstButton mBtnAddwords;
    private android.widget.TextView mTxvAbout;
    private com.yjx.androidword.MyView.MyFirstButton mBtnModeChoose2;
    private com.yjx.androidword.MyView.MyFirstButton mBtnSearch;
    private com.yjx.androidword.MyView.MyFirstButton mBtnDescription;
    private com.yjx.androidword.MyView.MyFirstButton mBtnModeFill2;
//    private com.yjx.androidword.MyView.MyFirstButton mBtnTranslation;

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
//        mBtnTranslation.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_mode_choose://选择 英-中
                if (SQLiteUtils.cursorCount(mContext) > 4)
                    JumpUtils.To(mContext, ChooseE2CActivity.class);
                else
                    DialogUtils.show(mContext, "词库内单词低于5个，无法进入此模式！请先添加！");
                break;

            case R.id.btn_mode_choose2://选择 中-英
                if (SQLiteUtils.cursorCount(mContext) > 4)
                    JumpUtils.To(mContext, ChooseC2EActivity.class);
                else
                    DialogUtils.show(mContext, "词库内单词低于5个，无法进入此模式！请先添加！");
                break;

            case R.id.btn_mode_fill://填写 英-中
                if (SQLiteUtils.cursorCount(mContext) != 0)
                    JumpUtils.To(mContext, FillE2CActivity.class);
                else
                    DialogUtils.show(mContext, "词库内没有单词，请先添加！");
                break;

            case R.id.btn_mode_fill2://填写 中-英
                if (SQLiteUtils.cursorCount(mContext) != 0)
                    JumpUtils.To(mContext, FillC2EActivity.class);
                else
                    DialogUtils.show(mContext, "词库内没有单词，请先添加！");
                break;

            case R.id.btn_addwords://添加单词
                JumpUtils.To(mContext, AddWordsActivity.class);
                break;

            case R.id.btn_search://查询词库
                if (SQLiteUtils.cursorCount(mContext) != 0)
                    JumpUtils.To(mContext, DictionaryActivity.class);
                else
                    DialogUtils.show(mContext, "词库内没有单词，请先添加！");
                Log.d("Dictionary", "单词数量: " + SQLiteUtils.cursorCount(mContext));
                break;

            case R.id.btn_description://软件说明
                DescriptionUtils.showDescription(mContext);
                break;

            case R.id.txv_about://关于
                View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_about, null);
                DialogUtils.show(mContext, view);
                break;

            case R.id.btn_zh2en:
                JumpUtils.To(mContext, TranslationActivity.class);
                break;

            default:
                Toast.makeText(mContext, "攻城狮正在努力开发中！", Toast.LENGTH_SHORT).show();
        }
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
//        mBtnTranslation = findViewById(R.id.btn_zh2en);
    }

}