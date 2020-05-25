package com.yjx.androidword.Utils;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yjx.androidword.R;

@SuppressLint("StaticFieldLeak")
public class DescriptionUtils {

    private static TextView mTxvModeChoose;
    private static TextView mTxvModeFill;
    private static TextView mTxvDescription;
    private static TextView mTxvDictionary;
    private static TextView mTxvTranslate;



    //软件说明对话框
    public static void showDescription(Context context) {
        @SuppressLint("InflateParams") View view = LayoutInflater.from(context).inflate(R.layout.dialog_description, null);
        //软件说明
        Button btnDismiss = view.findViewById(R.id.btn_dismiss);
        mTxvModeChoose = view.findViewById(R.id.txv_mode_choose);
        mTxvModeFill = view.findViewById(R.id.txv_mode_fill);
        mTxvDescription = view.findViewById(R.id.txv_description);
        mTxvDictionary = view.findViewById(R.id.txv_dictionary);
        mTxvTranslate = view.findViewById(R.id.txv_translate);
        final Dialog dialog = DialogUtils.show(context, view);
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
                mTxvTranslate.setBackground(null);
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
                mTxvTranslate.setBackground(null);
                mTxvDescription.setText(R.string.str_descrip_fill);
            }
        });
        mTxvDictionary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTxvDictionary.setBackgroundResource(R.drawable.txv_stroke);
                mTxvModeChoose.setBackground(null);
                mTxvModeFill.setBackground(null);
                mTxvTranslate.setBackground(null);
                mTxvDescription.setText(R.string.str_descrip_dictionary);
            }
        });
        mTxvTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTxvTranslate.setBackgroundResource(R.drawable.txv_stroke);
                mTxvModeChoose.setBackground(null);
                mTxvModeFill.setBackground(null);
                mTxvDictionary.setBackground(null);
                mTxvDescription.setText(R.string.str_descrip_translation);
            }
        });
    }

}
