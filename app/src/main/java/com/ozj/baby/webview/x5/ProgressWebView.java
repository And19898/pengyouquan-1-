package com.ozj.baby.webview.x5;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.ozj.baby.R;
import com.ozj.baby.webview.x5.clients.IWebChromeClient;
import com.ozj.baby.webview.x5.clients.IWebViewClient;
import com.ozj.baby.webview.x5.tools.IWebSetting;
import com.tencent.smtt.sdk.WebView;


/**
 * author: Rea.X
 * date: 2017/11/2.
 */

public class ProgressWebView extends RelativeLayout {
    private IWebViewClient webViewClient;
    private PostWebView webView;
    private IWebChromeClient chromeClient;


    public ProgressWebView(Context context) {
        super(context);
        init(context);
    }

    public ProgressWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ProgressWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ProgressWebView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }
    private ProgressBar pbloading, pbCircleLoading;
    private FrameLayout fullFramlayout;
    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_webview, this, true);
        pbloading = (ProgressBar) findViewById(R.id.pbloading);
        pbCircleLoading = (ProgressBar) findViewById(R.id.pbCircleLoading);
        fullFramlayout = (FrameLayout) findViewById(R.id.fullFramlayout);
        webView = (PostWebView) findViewById(R.id.webview);
        IWebSetting.init(webView);
        chromeClient = new IWebChromeClient(this);
        webViewClient = new IWebViewClient(this);
        webView.setWebChromeClient(chromeClient);
        webView.setWebViewClient(webViewClient);
        webView.setDownloadListener(new IDownloadListener(getContext()));
    }

    public PostWebView getWebView() {
        return webView;
    }



    public ProgressBar getlineProgressbar() {
        return pbloading;
    }

    public ProgressBar getcircleProgressbar() {
        return pbCircleLoading;
    }

    public FrameLayout getfullFramlayout() {
        return fullFramlayout;
    }

    public void setIWebChromClient(IWebChromeClient chromClient) {
        webView.setWebChromeClient(chromClient);
    }

    public void setIWebViewClient(IWebViewClient client) {
        webView.setWebViewClient(client);
    }

    /**
     * 使用此webview，必须在Activity或者Fragment的onActivityResult回调中调用该方法，在super前调用，否则选择文件将不起效
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        chromeClient.onActivityResult(requestCode, resultCode, data);
    }
}
