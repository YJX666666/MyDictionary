package com.yjx.androidword.Home.Adpater;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @Time : 2020/5/17 18:14
 * @Author : Android_小黑
 * @File : HomeAdapter.java
 * @Software : Android Studio
 */
public class HomeAdapter extends FragmentPagerAdapter {

    private List<Fragment> mList;

    public HomeAdapter(@NonNull FragmentManager fm, List<Fragment> list) {
        super(fm);
        mList = list;
        notifyDataSetChanged();
    }

    public HomeAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }
}
