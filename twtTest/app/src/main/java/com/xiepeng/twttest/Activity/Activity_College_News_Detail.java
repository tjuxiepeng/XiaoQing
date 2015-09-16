package com.xiepeng.twttest.Activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;


import com.xiepeng.twttest.R;


public class Activity_College_News_Detail extends Activity{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_detail);

        ActionBar actionBar = getActionBar();
        actionBar.setIcon(R.mipmap.back);
        actionBar.setTitle("校庆新闻");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(false);



        Intent intent = getIntent();
        String html = (String)intent.getStringExtra("html");

        WebView web = (WebView)this.findViewById(R.id.news_detail_webview);


        web.getSettings().setJavaScriptEnabled(true);


        web.loadDataWithBaseURL(null, html, "text/html", "utf-8", null);



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:// 点击返回图标事件
                this.finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
