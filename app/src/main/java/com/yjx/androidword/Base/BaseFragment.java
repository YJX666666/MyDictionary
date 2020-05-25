package com.yjx.androidword.Base;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

/**
 * @Time : 2020/5/17 18:02
 * @Author : Android_小黑
 * @File : BaseFragment.java
 * @Software : Android Studio
 */
// Fragment 基类
public abstract class BaseFragment extends Fragment {

    public Context mContext;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

}
