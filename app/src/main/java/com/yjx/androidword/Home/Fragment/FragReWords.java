package com.yjx.androidword.Home.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.yjx.androidword.Activity.ChooseC2EActivity;
import com.yjx.androidword.Activity.ChooseE2CActivity;
import com.yjx.androidword.Activity.DictionaryActivity;
import com.yjx.androidword.Activity.FillC2EActivity;
import com.yjx.androidword.Activity.FillE2CActivity;
import com.yjx.androidword.Base.BaseFragment;
import com.yjx.androidword.MyView.MyFirstButton;
import com.yjx.androidword.R;
import com.yjx.androidword.Utils.DialogUtils;
import com.yjx.androidword.Utils.JumpUtils;
import com.yjx.androidword.Utils.OverWordsNumUtils;
import com.yjx.androidword.Utils.SPUtils;
import com.yjx.androidword.Utils.SQLiteUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * @Time : 2020/5/17 18:06
 * @Author : Android_小黑
 * @File : FragReWords.java
 * @Software : Android Studio
 */
public class FragReWords extends BaseFragment implements View.OnClickListener {

    private TextView mTxvTodayAims;
    private TextView mTxvTodayCompleted;
    private ProgressBar mProgressRate;
    private TextView mTxvDictionaryWordsNumber;
    private MyFirstButton mBtnModeChoose;
    private MyFirstButton mBtnModeChoose2;
    private MyFirstButton mBtnModeFill;
    private MyFirstButton mBtnModeFill2;
    private LinearLayout mLlDictionary;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_rewords, container, false);
        initView(view);
        mBtnModeChoose.setOnClickListener(this);
        mBtnModeChoose2.setOnClickListener(this);
        mBtnModeFill.setOnClickListener(this);
        mBtnModeFill2.setOnClickListener(this);
        mLlDictionary.setOnClickListener(this);
        return view;
    }


    // 根据Fragment的声明周期、这些需要实时更新的数据，放在onCreateView中不会刷新
    @SuppressLint("SetTextI18n")
    @Override
    public void onStart() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 0);
        Date date = calendar.getTime();
        mTxvTodayCompleted.setText(SPUtils.get(mContext, OverWordsNumUtils.getDate(date), 0) + "");
        // 获取每日目标
        mTxvTodayAims.setText(String.valueOf(SPUtils.get(mContext, "everyday_aims", 0)));
        // 进度条比例计算
        int bili = (int) ((Float.valueOf(mTxvTodayCompleted.getText().toString()) / Float.valueOf(mTxvTodayAims.getText().toString())) * 100);
        mProgressRate.setProgress(bili);
        // 词库单词数获取
        mTxvDictionaryWordsNumber.setText(String.valueOf(SQLiteUtils.cursorCount(mContext)));
        super.onStart();
    }

    private void initView(View view) {
        mTxvTodayAims = view.findViewById(R.id.txv_today_aims);
        mTxvTodayCompleted = view.findViewById(R.id.txv_today_completed);
        mProgressRate = view.findViewById(R.id.progress_rate);
        mTxvDictionaryWordsNumber = view.findViewById(R.id.txv_dictionary_words_number);
        mBtnModeChoose = view.findViewById(R.id.btn_mode_choose);
        mBtnModeChoose2 = view.findViewById(R.id.btn_mode_choose2);
        mBtnModeFill = view.findViewById(R.id.btn_mode_fill);
        mBtnModeFill2 = view.findViewById(R.id.btn_mode_fill2);
        mLlDictionary = view.findViewById(R.id.ll_dictionary);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //词库
            case R.id.ll_dictionary://查询词库
                if (SQLiteUtils.cursorCount(mContext) != 0)
                    JumpUtils.To(mContext, DictionaryActivity.class);
                else
                    DialogUtils.show(mContext, "词库内没有单词，请先添加！", "去添加单词");
                break;
            //四种模式
            case R.id.btn_mode_choose://选择 英-中
                if (SQLiteUtils.cursorCount(mContext) > 4)
                    JumpUtils.To(mContext, ChooseE2CActivity.class);
                else
                    DialogUtils.show(mContext, "词库内单词低于5个，无法进入此模式！请先添加！", "去添加单词");
                break;

            case R.id.btn_mode_choose2://选择 中-英
                if (SQLiteUtils.cursorCount(mContext) > 4)
                    JumpUtils.To(mContext, ChooseC2EActivity.class);
                else
                    DialogUtils.show(mContext, "词库内单词低于5个，无法进入此模式！请先添加！", "去添加单词");
                break;

            case R.id.btn_mode_fill://填写 英-中
                if (SQLiteUtils.cursorCount(mContext) != 0)
                    JumpUtils.To(mContext, FillE2CActivity.class);
                else
                    DialogUtils.show(mContext, "词库内没有单词，请先添加！", "去添加单词");
                break;

            case R.id.btn_mode_fill2://填写 中-英
                if (SQLiteUtils.cursorCount(mContext) != 0)
                    JumpUtils.To(mContext, FillC2EActivity.class);
                else
                    DialogUtils.show(mContext, "词库内没有单词，请先添加！", "去添加单词");
                break;
        }
    }
}
