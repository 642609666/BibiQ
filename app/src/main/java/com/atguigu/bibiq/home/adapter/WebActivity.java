package com.atguigu.bibiq.home.adapter;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.bibiq.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class WebActivity extends AppCompatActivity {

    @InjectView(R.id.iv_web_back)
    ImageView ivWebBack;
    @InjectView(R.id.tv_web_name)
    TextView tvWebName;
    @InjectView(R.id.iv_web_more)
    ImageView ivWebMore;
    @InjectView(R.id.webview)
    WebView webview;

    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.inject(this);
        String web_json = getIntent().getStringExtra("web_json");
        String[] split = web_json.split(",,");
        mProgressBar = new ProgressBar(this);
        final AlertDialog dialog = new AlertDialog.Builder(this)
                .setMessage("加载中......")
                .setView(mProgressBar)
                .show();
        WebSettings webSettings = webview.getSettings();
        //设置支持js
        webSettings.setJavaScriptEnabled(true);
        //设置添加缩放按钮
        webSettings.setBuiltInZoomControls(true);
        //设置双击单击
        webSettings.setUseWideViewPort(true);
        //设置WebViewClient,如果没有设置，调起系统的浏览器打开新的连接
        webview.setWebViewClient(new WebViewClient() {
            /**
             * 加成web页面完成,可把进度条之类的关闭
             * @param view
             * @param url
             */
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                dialog.dismiss();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    view.loadUrl(request.getUrl().toString());
                }
                return true;
            }
        });
        webview.loadUrl(split[0]);
        tvWebName.setText(split[1]);
    }

    @OnClick({R.id.iv_web_back, R.id.iv_web_more})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_web_back:
                finish();
                break;
            case R.id.iv_web_more:
                Toast.makeText(WebActivity.this, "分享", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
