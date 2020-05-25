package com.yjx.androidword.Home.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.yjx.androidword.Activity.TranslationEnZhActivity;
import com.yjx.androidword.Activity.TranslationMoreActivity;
import com.yjx.androidword.Base.BaseFragment;
import com.yjx.androidword.R;
import com.yjx.androidword.Utils.DialogUtils;
import com.yjx.androidword.Utils.JumpUtils;
import com.yjx.androidword.Utils.NetWorkUtils;

/**
 * @Time : 2020/5/17 18:06
 * @Author : Android_小黑
 * @File : FragReWords.java
 * @Software : Android Studio
 */
public class FragTranslate extends BaseFragment implements View.OnClickListener {

    private Button mBtnTranslateEnzh;
    private Button mBtnTranslationMore;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_translate, container, false);
        initView(view);
        mBtnTranslateEnzh.setOnClickListener(this);
        mBtnTranslationMore.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_translate_enzh:
                if (NetWorkUtils.check(mContext))
                    JumpUtils.To(mContext, TranslationEnZhActivity.class);
                else
                    DialogUtils.show(mContext, "本功能为在线功能，请先连接网络再进行使用！", "去连接网络");
                break;
            case R.id.btn_translation_more:
                if (NetWorkUtils.check(mContext))
                    JumpUtils.To(mContext, TranslationMoreActivity.class);
                else
                    DialogUtils.show(mContext, "本功能为在线功能，请先连接网络再进行使用！", "去连接网络");
                break;
        }
    }


    private void initView(View view) {
        mBtnTranslateEnzh = view.findViewById(R.id.btn_translate_enzh);
        mBtnTranslationMore = view.findViewById(R.id.btn_translation_more);
    }
}
