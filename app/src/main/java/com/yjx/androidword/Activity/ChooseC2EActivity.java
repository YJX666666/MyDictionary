package com.yjx.androidword.Activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yjx.androidword.BaseActivity;
import com.yjx.androidword.Bean.WordsBean;
import com.yjx.androidword.R;
import com.yjx.androidword.Utils.DialogUtils;
import com.yjx.androidword.Utils.RandomUtils;
import com.yjx.androidword.Utils.SQLiteUtils;
import com.yjx.androidword.Utils.ToastUtils;
import com.yjx.androidword.Utils.WordsUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChooseC2EActivity extends BaseActivity implements View.OnClickListener {

    private TextView mTxvWord;
    private Button mBtnA;
    private Button mBtnB;
    private Button mBtnC;
    private Button mBtnD;
    private TextView mTxvGrasp;
    private Button mBtnNext;
    //词库
    private List<WordsBean> mListWords = new ArrayList<>();
    //当前考核的 单词和翻译
    private String mString_English = "";
    private String mString_Chinese = "";
    //利用随机数把答案放入选项
    private Random mRandom = new Random();
    //背诵标记
    private int index = 0;
    private int index2, index3, index4;

    @Override
    protected void initData() {
        mBtnA.setOnClickListener(this);
        mBtnB.setOnClickListener(this);
        mBtnC.setOnClickListener(this);
        mBtnD.setOnClickListener(this);
        mBtnNext.setOnClickListener(this);
        mTxvGrasp.setOnClickListener(this);

        //获取打乱顺序的词库
        mListWords = WordsUtils.get(mContext);
        //首次进入设置一个单词
        setData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_a://A选项
                getJudg(mBtnA, mBtnA.getText().toString());
                break;
            case R.id.btn_b://B选项
                getJudg(mBtnB, mBtnB.getText().toString());
                break;
            case R.id.btn_c://C选项
                getJudg(mBtnC, mBtnC.getText().toString());
                break;
            case R.id.btn_d://D选项
                getJudg(mBtnD, mBtnD.getText().toString());
                break;
            case R.id.btn_next://下一个
                reSetButton();
                setData();
                break;
            case R.id.txv_grasp://掌握了单词
                View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_words_menu, null);
                TextView txvEnglish = view.findViewById(R.id.edit_english);
                TextView txvChinese = view.findViewById(R.id.edit_chinese);
                TextView txvDel = view.findViewById(R.id.txv_del);
                TextView txvModify = view.findViewById(R.id.txv_modify);
                txvEnglish.setText(mString_English);
                txvChinese.setText(mString_Chinese);
                txvDel.setText("掌握（删除）");
                txvModify.setText("取消");
                final Dialog dialog = DialogUtils.show(mContext, view);
                txvDel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SQLiteUtils.delete(mContext, mString_English);
                        if (SQLiteUtils.cursorCount(mContext) < 5) {
                            finish();
                            ToastUtils.showLong(mContext, "词库中单词数量过低，无法继续此模式，请选择填空模式！");
                        } else {
                            //删除了单词以后，List需要重新获取一下词库数据
                            mListWords = WordsUtils.get(mContext);
                            reSetButton();
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

    //获取需要测试的单词和翻译
    private void setData() {
        //防止背到最后一个单词导致IndexOutOfBoundsException
        if (index == SQLiteUtils.cursorCount(mContext) - 1) {
            index = 0;
        } else {
            index++;
        }
        //需要测试的组赋值
        mString_English = mListWords.get(index).getEnglish();
        mString_Chinese = mListWords.get(index).getChinses();
        //单词发送到TextView上
        mTxvWord.setText(mString_Chinese);
        //获取三个随机非正确答案的下标
        List<Integer> list3Random = RandomUtils.get3Random(mContext,index);
        index2 = list3Random.get(0);
        index3 = list3Random.get(1);
        index4 = list3Random.get(2);
        //随机选取一个按钮放中文答案,其他按钮放上获取的随机非正确答案
        switch (mRandom.nextInt(4) % (4) + 1) {
            case 1:
                mBtnA.setText(mString_English);
                mBtnB.setText(mListWords.get(index2).getEnglish());
                mBtnC.setText(mListWords.get(index3).getEnglish());
                mBtnD.setText(mListWords.get(index4).getEnglish());
                break;
            case 2:
                mBtnB.setText(mString_English);
                mBtnA.setText(mListWords.get(index2).getEnglish());
                mBtnC.setText(mListWords.get(index3).getEnglish());
                mBtnD.setText(mListWords.get(index4).getEnglish());
                break;
            case 3:
                mBtnC.setText(mString_English);
                mBtnA.setText(mListWords.get(index2).getEnglish());
                mBtnB.setText(mListWords.get(index3).getEnglish());
                mBtnD.setText(mListWords.get(index4).getEnglish());
                break;
            case 4:
                mBtnD.setText(mString_English);
                mBtnA.setText(mListWords.get(index2).getEnglish());
                mBtnB.setText(mListWords.get(index3).getEnglish());
                mBtnC.setText(mListWords.get(index4).getEnglish());
                break;
        }
    }

    //选择判断
    @SuppressLint("SetTextI18n")
    private void getJudg(Button btn, String str_btn) {

        if (str_btn.equals(mString_English)) {

            //选择正确
            btn.setBackgroundResource(R.drawable.btn_green);

            if (btn == mBtnA) {
                mBtnB.setText(mListWords.get(index2).getEnglish() + " ： " + mListWords.get(index2).getChinses());
                mBtnC.setText(mListWords.get(index3).getEnglish() + " ： " + mListWords.get(index3).getChinses());
                mBtnD.setText(mListWords.get(index4).getEnglish() + " ： " + mListWords.get(index4).getChinses());
            } else if (btn == mBtnB) {
                mBtnA.setText(mListWords.get(index2).getEnglish() + " ： " + mListWords.get(index2).getChinses());
                mBtnC.setText(mListWords.get(index3).getEnglish() + " ： " + mListWords.get(index3).getChinses());
                mBtnD.setText(mListWords.get(index4).getEnglish() + " ： " + mListWords.get(index4).getChinses());
            } else if (btn == mBtnC) {
                mBtnA.setText(mListWords.get(index2).getEnglish() + " ： " + mListWords.get(index2).getChinses());
                mBtnB.setText(mListWords.get(index3).getEnglish() + " ： " + mListWords.get(index3).getChinses());
                mBtnD.setText(mListWords.get(index4).getEnglish() + " ： " + mListWords.get(index4).getChinses());
            } else if (btn == mBtnD) {
                mBtnA.setText(mListWords.get(index2).getEnglish() + " ： " + mListWords.get(index2).getChinses());
                mBtnB.setText(mListWords.get(index3).getEnglish() + " ： " + mListWords.get(index3).getChinses());
                mBtnC.setText(mListWords.get(index4).getEnglish() + " ： " + mListWords.get(index4).getChinses());
            }

        } else {

            //选择错误
            btn.setBackgroundResource(R.drawable.btn_red);

            if (btn != mBtnA && mBtnA.getText().toString().equals(mString_English)) {
                mBtnA.setBackgroundResource(R.drawable.btn_green2);
                mBtnB.setText(mListWords.get(index2).getEnglish() + " ： " + mListWords.get(index2).getChinses());
                mBtnC.setText(mListWords.get(index3).getEnglish() + " ： " + mListWords.get(index3).getChinses());
                mBtnD.setText(mListWords.get(index4).getEnglish() + " ： " + mListWords.get(index4).getChinses());
            } else if (btn != mBtnB && mBtnB.getText().toString().equals(mString_Chinese)) {
                mBtnB.setBackgroundResource(R.drawable.btn_green2);
                mBtnA.setText(mListWords.get(index2).getEnglish() + " ： " + mListWords.get(index2).getChinses());
                mBtnC.setText(mListWords.get(index3).getEnglish() + " ： " + mListWords.get(index3).getChinses());
                mBtnD.setText(mListWords.get(index4).getEnglish() + " ： " + mListWords.get(index4).getChinses());
            } else if (btn != mBtnC && mBtnC.getText().toString().equals(mString_Chinese)) {
                mBtnC.setBackgroundResource(R.drawable.btn_green2);
                mBtnA.setText(mListWords.get(index2).getEnglish() + " ： " + mListWords.get(index2).getChinses());
                mBtnB.setText(mListWords.get(index3).getEnglish() + " ： " + mListWords.get(index3).getChinses());
                mBtnD.setText(mListWords.get(index4).getEnglish() + " ： " + mListWords.get(index4).getChinses());
            } else if (btn != mBtnD && mBtnD.getText().toString().equals(mString_Chinese)) {
                mBtnD.setBackgroundResource(R.drawable.btn_green2);
                mBtnA.setText(mListWords.get(index2).getEnglish() + " ： " + mListWords.get(index2).getChinses());
                mBtnB.setText(mListWords.get(index3).getEnglish() + " ： " + mListWords.get(index3).getChinses());
                mBtnC.setText(mListWords.get(index4).getEnglish() + " ： " + mListWords.get(index4).getChinses());
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

    //重置按钮
    private void reSetButton() {
        //隐藏“下一个”按钮
        mBtnNext.setVisibility(View.GONE);
        //重置背景
        mBtnA.setBackgroundResource(R.drawable.btn_background);
        mBtnB.setBackgroundResource(R.drawable.btn_background);
        mBtnC.setBackgroundResource(R.drawable.btn_background);
        mBtnD.setBackgroundResource(R.drawable.btn_background);
        //重置状态
        mBtnA.setEnabled(true);
        mBtnB.setEnabled(true);
        mBtnC.setEnabled(true);
        mBtnD.setEnabled(true);
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

}