package com.yjx.androidword.Activity;

import android.annotation.SuppressLint;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.yjx.androidword.Base.BaseActivity;
import com.yjx.androidword.R;

/**
 * @Time : 2020/5/18 16:20
 * @Author : Android_小黑
 * @File : TxBugActivity.java
 * @Software : Android Studio
 */
public class TxBugActivity extends BaseActivity {

    private android.webkit.WebView mWebview;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void initData() {
        String url = "https://support.qq.com/product/154802";
        mWebview.getSettings().setJavaScriptEnabled(true);//js支持
        mWebview.getSettings().setDomStorageEnabled(true);
        mWebview.loadUrl(url);
        mWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                mWebview.loadUrl(url);
                return true;
            }
        });

    }

    @Override
    protected int initLayout() {
        return R.layout.layout_tx_bug;
    }

    @Override
    protected void initView() {

        mWebview = findViewById(R.id.webview);
    }
}
