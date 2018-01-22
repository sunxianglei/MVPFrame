package com.xianglei.mvpframe.module.detail;

import android.os.Bundle;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.xianglei.mvpframe.R;
import com.xianglei.mvpframe.base.BaseActivity;
import com.xianglei.mvpframe.utils.Const;

/**
 * @author sunxianglei
 * @date 2018/1/19
 */

public class DetailActivity extends BaseActivity {

    private WebView mWebView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_detail;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        mWebView = new WebView(this);
        LinearLayout linearLayout = findViewById(R.id.linear_layout);
        linearLayout.addView(mWebView);
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        //支持屏幕缩放
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        //不显示webview缩放按钮
        settings.setDisplayZoomControls(false);
    }

    @Override
    protected void initParams() {
        String url = getIntent().getExtras().getString(Const.URL);
        mWebView.loadUrl(url);
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    @Override
    protected void recycleRes() {
        if(mWebView != null){
            ViewParent parent = mWebView.getParent();
            if(parent != null){
                ((ViewGroup)parent).removeView(mWebView);
            }
            mWebView.getSettings().setJavaScriptEnabled(false);
            mWebView.clearHistory();
            mWebView.removeAllViews();
            mWebView.destroy();
        }
    }
}
