package com.yjx.androidword.Activity;

import android.text.TextUtils;
import android.view.View;

import com.yjx.androidword.Base.BaseActivity;
import com.yjx.androidword.R;
import com.yjx.androidword.Utils.SPUtils;
import com.yjx.androidword.Utils.ToastUtils;

/**
 * @Time : 2020/5/21 14:58
 * @Author : Android_小黑
 * @File : PersonActivity.java
 * @Software : Android Studio
 */
public class PersonActivity extends BaseActivity implements View.OnClickListener {

    public static final String PERSON_NAME = "person_name";
    public static final String PERSON_MSG = "person_msg";

    private android.widget.ImageView mImvIcon;
    private android.widget.EditText mEditName;
    private android.widget.EditText mEditMsg;
    private com.yjx.androidword.MyView.MyFirstButton mBtnSave;

    @Override
    protected void initData() {
        getNameMsg();
        mBtnSave.setOnClickListener(this);
        mImvIcon.setOnClickListener(this);
    }

    private void getNameMsg() {
        // 获取设置的姓名和签名，如果没有就设置小黑默认值
        String strName = (String) SPUtils.get(mContext, PERSON_NAME, "Android_小黑");
        String strMsg = (String) SPUtils.get(mContext, PERSON_MSG, "励志做一个月入15K的Android软件攻城狮！");
        mEditName.setText(strName);
        mEditMsg.setText(strMsg);
    }

    @Override
    protected int initLayout() {
        return R.layout.layout_person;
    }

    @Override
    protected void initView() {
        mImvIcon = findViewById(R.id.imv_icon);
        mEditName = findViewById(R.id.edit_name);
        mEditMsg = findViewById(R.id.edit_msg);
        mBtnSave = findViewById(R.id.btn_save);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imv_icon:
                ToastUtils.show(mContext, "更换头像功能还在开发中噢！");
                break;
            case R.id.btn_save:
                if (!TextUtils.isEmpty(mEditName.getText().toString()) && !TextUtils.isEmpty(mEditMsg.getText().toString())) {
                    //保存昵称和签名
                    SPUtils.set(mContext, PERSON_NAME, mEditName.getText().toString());
                    SPUtils.set(mContext, PERSON_MSG, mEditMsg.getText().toString());
                    //重新获取一下
                    getNameMsg();
                    ToastUtils.show(mContext, "保存成功！");
                } else
                    ToastUtils.show(mContext, "您的昵称和签名不能为空哟！");
                break;
        }
    }
}
