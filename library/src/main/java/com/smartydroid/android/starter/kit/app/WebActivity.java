package com.smartydroid.android.starter.kit.app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.webkit.DownloadListener;

import com.smartydroid.android.starter.kit.R;
import com.smartydroid.android.starter.kit.widget.ProgressWebView;

/**
 * Author: meyu
 * Date:   16/4/2
 * Email:  627655140@qq.com
 */
public class WebActivity extends StarterActivity{

    public final static String PARAM_NAME = "name";
    public final static String PARAM_URL = "url";

    private ProgressWebView webview;
    private String url;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // ~~~ 获取参数
        url = getIntent().getStringExtra(PARAM_URL);
        name = getIntent().getStringExtra(PARAM_NAME);
        setContentView(R.layout.comment_web);
    }

    @Override
    protected void setupViews() {

        // ~~~ 绑定控件
        webview = (ProgressWebView) findViewById(R.id.web_view);

        // ~~~ 设置数据
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                if (url != null && url.startsWith("http://"))
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            }
        });

        webview.loadUrl(url);
    }

    @Override
    public void onCreateCustomToolBar(Toolbar toolbar) {
        super.onCreateCustomToolBar(toolbar);
        toolbar.setTitle(name);
    }
}
