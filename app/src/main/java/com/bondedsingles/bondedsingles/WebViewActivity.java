package com.bondedsingles.bondedsingles;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
/**
 * Created by OBINNA on 10/29/2017.
 */

public class WebViewActivity extends Activity implements View.OnTouchListener,Handler.Callback{

    //FOR ONTOUCH SUPPORT
    private static final int CLICK_ON_WEBVIEW = 1;
    private static final int CLICK_ON_URL = 2;
    private final Handler handler = new Handler(this);
    private WebView webView;
    private WebViewClient client;

    //FOR SWIPE REFRESH
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        webView = (WebView) findViewById(R.id.web1);
        webView.setOnTouchListener(this);

        client = new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                handler.sendEmptyMessage(CLICK_ON_URL);
                return
                        false;
            }
        };
        webView.setWebViewClient(client);
        webView.setVerticalScrollBarEnabled(true);
        webView.setHorizontalScrollBarEnabled(true);
        WebSettings webSettings = webView.getSettings();
        webSettings.setBuiltInZoomControls(true);

        //optional javascript
        webSettings.setJavaScriptEnabled(true);

        //handling the swipe refresh
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.webview_swipe_refresh);
        mSwipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        webView.reload();
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }
        );



        //optional progress bar

        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setMax(100);
        // WebChromeClient reports in range 0-100
        final TextView titleTextView = (TextView) findViewById(R.id.titleTextView);

        webView.setWebChromeClient(new WebChromeClient() {
                                       public void onProgressChanged(WebView webView, int progress) {
                                           if (progress == 100){
                                               progressBar.setVisibility(View.INVISIBLE);
                                           } else

                                           {
                                               progressBar.setVisibility(View.VISIBLE);
                                               progressBar.setProgress(progress);
                                           }
                                       }

                                       public void onReceivedTitle(WebView webView, String title) {
                                           titleTextView.setText(title);
                                       }
                                   }
        );


       // webView.loadUrl("https://www.bondedsingles-members.online");
        webView.loadUrl("https://www.avicii.ca");
    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if
                (v.getId() == R.id.web1 && event.getAction() == MotionEvent.ACTION_DOWN) {
            handler.sendEmptyMessageDelayed(CLICK_ON_WEBVIEW, 500);
        }
        return
                false;
    }
    @Override
    public boolean handleMessage(Message msg){
        if
                (msg.what==CLICK_ON_URL) {
            handler.removeMessages(CLICK_ON_WEBVIEW);
            return
                    true;
        }
        return
                false;

    }
    //FOR BACK KEY
    @Override
    public void onBackPressed(){
        if (webView != null && webView.canGoBack()) {
            webView.goBack();
        }
        else{
            finish();
        }
    }


}
