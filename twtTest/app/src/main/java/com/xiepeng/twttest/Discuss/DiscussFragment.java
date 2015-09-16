package com.xiepeng.twttest.Discuss;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.xiepeng.twttest.R;

/**
 * Created by dell on 2015/8/21.
 */
public class DiscussFragment extends Fragment {
    private final String url = "http://120.wenjin.in";

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.discuss_fragment, container, false);

        WebView webView = (WebView)view.findViewById(R.id.disscuss_webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webView.loadUrl(url);

        return view;

    }

}
