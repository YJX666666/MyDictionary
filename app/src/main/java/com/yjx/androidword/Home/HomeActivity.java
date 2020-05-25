package com.yjx.androidword.Home;

import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.yjx.androidword.Base.BaseActivity;
import com.yjx.androidword.Home.Adpater.HomeAdapter;
import com.yjx.androidword.Home.Fragment.FragPerson;
import com.yjx.androidword.Home.Fragment.FragReWords;
import com.yjx.androidword.Home.Fragment.FragTranslate;
import com.yjx.androidword.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @Time : 2020/5/17 17:50
 * @Author : Android_小黑
 * @File : HomeActivity.java
 * @Software : Android Studio
 */
public class HomeActivity extends BaseActivity {

    private androidx.viewpager.widget.ViewPager mPagerContent;
    private com.google.android.material.bottomnavigation.BottomNavigationView mBnv;

    @Override
    protected void initData() {
        List<Fragment> list = new ArrayList<>();
        list.add(new FragReWords());
        list.add(new FragTranslate());
        list.add(new FragPerson());

        HomeAdapter adapter = new HomeAdapter(getSupportFragmentManager(), list);

        mPagerContent.setAdapter(adapter);

        //ViewPager和BotNaviView的联动
        setListener();
    }

    private void setListener() {
        // 点击下方按钮的时候，ViewPager跟着动
        mBnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menu_rewords:
                        mPagerContent.setCurrentItem(0);
                        break;
                    case R.id.menu_translate:
                        mPagerContent.setCurrentItem(1);
                        break;
                    case R.id.menu_person:
                        mPagerContent.setCurrentItem(2);
                        break;
                }
                return true;
            }
        });
        // ViewPager滑动的时候，下面按钮跟着动
        mPagerContent.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //滚动的时候
            }

            @Override
            public void onPageSelected(int position) {
                //选择的时候
                switch (position) {
                    case 0:
                        mBnv.setSelectedItemId(R.id.menu_rewords);
                        break;
                    case 1:
                        mBnv.setSelectedItemId(R.id.menu_translate);
                        break;
                    case 2:
                        mBnv.setSelectedItemId(R.id.menu_person);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //状态改变的时候
            }
        });
    }

    @Override
    protected int initLayout() {
        return R.layout.layout_home;
    }

    @Override
    protected void initView() {
        mPagerContent = findViewById(R.id.pager_content);
        mBnv = findViewById(R.id.bnv);
    }
}
