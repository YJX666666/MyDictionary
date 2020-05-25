package com.yjx.androidword.Home.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yjx.androidword.Activity.PersonActivity;
import com.yjx.androidword.Base.BaseFragment;
import com.yjx.androidword.Home.Adpater.PersonMenuAdapter;
import com.yjx.androidword.Home.Bean.MenuBean;
import com.yjx.androidword.R;
import com.yjx.androidword.Utils.JumpUtils;
import com.yjx.androidword.Utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Time : 2020/5/17 18:06
 * @Author : Android_小黑
 * @File : FragReWords.java
 * @Software : Android Studio
 */
public class FragPerson extends BaseFragment {

    private RecyclerView mRecycleMenu;
    private List<MenuBean> mList;
    private TextView mTxvName;
    private TextView mTxvMsg;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_person, container, false);
        initView(view);
        initMenuData();
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        PersonMenuAdapter adapter = new PersonMenuAdapter(mContext, mList);
        mRecycleMenu.setAdapter(adapter);
        mRecycleMenu.setLayoutManager(layoutManager);
        //子项动画
        mRecycleMenu.setItemAnimator(new DefaultItemAnimator());
        //子项分割线
        mRecycleMenu.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        return view;
    }

    @Override
    public void onStart() {
        // 获取设置的姓名和签名，如果没有就设置小黑默认值
        String strName = (String) SPUtils.get(mContext, PersonActivity.PERSON_NAME, "Android_小黑");
        String strMsg = (String) SPUtils.get(mContext, PersonActivity.PERSON_MSG, "励志做一个月入15K的Android软件攻城狮！");
        mTxvName.setText(strName);
        mTxvMsg.setText(strMsg);
        super.onStart();
    }

    private void initMenuData() {
        mList = new ArrayList<>();

        MenuBean bean = new MenuBean();
        bean.setIcon(R.mipmap.img_modify);
        bean.setText("修改每日目标");
        mList.add(bean);

        bean = new MenuBean();
        bean.setIcon(R.mipmap.img_add);
        bean.setText("添加单词");
        mList.add(bean);

        bean = new MenuBean();
        bean.setIcon(R.mipmap.img_suggest);
        bean.setText("意见反馈和功能建议");
        mList.add(bean);

        bean = new MenuBean();
        bean.setIcon(R.mipmap.img_help);
        bean.setText("软件使用帮助");
        mList.add(bean);

        bean = new MenuBean();
        bean.setIcon(R.mipmap.img_about);
        bean.setText("关于");
        mList.add(bean);
    }

    private void initView(View view) {
        mTxvName = view.findViewById(R.id.txv_name);
        mTxvMsg = view.findViewById(R.id.txv_msg);
        mRecycleMenu = view.findViewById(R.id.recycle_menu);
        LinearLayout llPerson = view.findViewById(R.id.ll_person);
        llPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtils.To(mContext, PersonActivity.class);
            }
        });
    }

}
