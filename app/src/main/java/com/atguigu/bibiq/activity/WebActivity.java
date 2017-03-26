package com.atguigu.bibiq.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.atguigu.bibiq.R;
import com.atguigu.bibiq.utils.ClipboardUtil;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class WebActivity extends AppCompatActivity {

    @InjectView(R.id.webview)
    WebView webview;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.app_bar_layout)
    AppBarLayout appBarLayout;
    @InjectView(R.id.progressbar)
    ProgressBar progressbar;
    @InjectView(R.id.activity_web)
    LinearLayout activityWeb;
    String[] split;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.inject(this);
        String web_json = getIntent().getStringExtra("web_json");
        split = web_json.split(",,");
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
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                progressbar.setVisibility(View.GONE);
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
        //设置头名字
        toolbar.setTitle(TextUtils.isEmpty(split[1]) ? "详情" : split[1]);
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    /**
     * 记载menu
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_browser, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;

            case R.id.menu_share://分享
                share();
                break;

            case R.id.menu_open: //浏览器打开
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(split[0]));
                startActivity(intent);
                break;

            case R.id.menu_copy:
                ClipboardUtil.setText(WebActivity.this, split[0]);
                Toast.makeText(WebActivity.this, "已复制", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //分享
    private void share() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
        intent.putExtra(Intent.EXTRA_TEXT, "来自「哔哩哔哩」的分享:" + split[0]); //传输网页
        startActivity(Intent.createChooser(intent, ""));   //传出名字
    }

    @Override
    public void onBackPressed() {

        if (webview.canGoBack() && webview.copyBackForwardList().getSize() > 0
                && !webview.getUrl().equals(webview.copyBackForwardList()
                .getItemAtIndex(0).getOriginalUrl())) {
            webview.goBack();
        } else {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        webview.destroy();
        super.onDestroy();
    }
}
