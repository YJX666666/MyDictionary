package com.yjx.androidword;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.yjx.androidword.Activity.FirstActivity;
import com.yjx.androidword.Utils.DialogUtils;

public abstract class BaseActivity extends AppCompatActivity {

    protected Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initWindow();
        super.onCreate(savedInstanceState);
        mContext = BaseActivity.this;
        setContentView(initLayout());
        //控件声明
        initView();
        //数据
        initData();

    }

    protected abstract void initData();

    protected void initWindow() {
    }

    protected abstract int initLayout();

    protected abstract void initView();

    @Override
    public void finish() {
        //重写finish事件，在主页面时返回需要确认
        if (mContext instanceof FirstActivity) {
            @SuppressLint("InflateParams") View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_finish, null);
            final Dialog dialog = DialogUtils.show(mContext, view);
            Button btnNo = view.findViewById(R.id.btn_no);
            Button btnYes = view.findViewById(R.id.btn_yes);
            btnNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            btnYes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /**
                     * 退出程序
                     * finish() 结束当前Activity，不会立即释放内存。遵循Android内存管理机制
                     * finish()是Activity类的方法，仅针对Activity，调用时只是把Activity推向后台，没有立刻释放内存
                     * Systen.exit() java的方法：退出当前Activity并释放内存，但是此方法只会结束当前Activity，本APP的其他Activity或Service不会结束
                     * System.exit(0); 参数是把退出原因返回给系统，0表示正常退出 1表示非正常
                     * killProcess() 结束当前组件，并立刻释放当前Activity所占资源
                     * 除了finish()以外，其他方法都不会调用Activity的生命周期
                     * 但是可以手动调用，在方法之前或者之后
                     * */
                    android.os.Process.killProcess(android.os.Process.myPid());
//                        System.exit(0);
                }
            });
        } else
            super.finish();
    }

}
