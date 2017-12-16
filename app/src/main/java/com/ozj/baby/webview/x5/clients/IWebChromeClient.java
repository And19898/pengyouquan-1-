package com.ozj.baby.webview.x5.clients;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.ozj.baby.webview.tools.LogUtil;
import com.ozj.baby.webview.x5.ProgressWebView;
import com.tencent.smtt.export.external.interfaces.ConsoleMessage;
import com.tencent.smtt.export.external.interfaces.IX5WebChromeClient;
import com.tencent.smtt.sdk.WebView;


/**
 * author: Rea.X
 * date: 2017/8/8.
 */

public class IWebChromeClient extends IWebChromeFileClient {
    private WebView webView;
    private FrameLayout fullFramlayout;
    private ProgressBar lineProgressbar;
    private ProgressBar circleProgressbar;
    private IX5WebChromeClient.CustomViewCallback callback;
    private boolean isShowLineProgress, isShowCircleProgress;


    public IWebChromeClient(ProgressWebView view) {
        super(view);
        this.webView = view.getWebView();
        this.fullFramlayout = view.getfullFramlayout();
        this.lineProgressbar = view.getlineProgressbar();
        this.circleProgressbar = view.getlineProgressbar();
        this.isShowLineProgress = false;
        this.isShowCircleProgress = true;
    }


    @Override
    public void onProgressChanged(WebView webView, int newProgress) {
        super.onProgressChanged(webView, newProgress);
        if (isShowLineProgress) {
            this.lineProgressbar.setVisibility(View.VISIBLE);
            this.lineProgressbar.setProgress(newProgress);
        }
        if (newProgress >= 80) {
            this.circleProgressbar.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        StringBuffer sb = new StringBuffer();
        sb.append("\n|");
        sb.append("|------------------------------------------------------------------------------------------------------------------|");
        sb.append("\n|");
        sb.append("|\tmessage->" + consoleMessage.message());
        sb.append("\n|");
        sb.append("|\tsourceId->" + consoleMessage.sourceId());
        sb.append("\n|");
        sb.append("|\tlineNumber->" + consoleMessage.lineNumber());
        sb.append("\n|");
        sb.append("|\tmessageLevel->" + consoleMessage.messageLevel());
        sb.append("\n|");
        sb.append("|----------------------------------------------------------------------------------------------------------------|");
        switch (consoleMessage.messageLevel()) {
            case ERROR:
                LogUtil.e("consoleMessage:" + sb.toString());
                break;
            case WARNING:
                LogUtil.w("consoleMessage:" + sb.toString());
                break;
            default:
                LogUtil.d("consoleMessage:" + sb.toString());
                break;
        }
        return super.onConsoleMessage(consoleMessage);
    }

    @Override
    public void onHideCustomView() {
        if (callback != null) {
            callback.onCustomViewHidden();
        }
        webView.setVisibility(View.VISIBLE);
        fullFramlayout.removeAllViews();
        fullFramlayout.setVisibility(View.GONE);
        super.onHideCustomView();
    }


    @Override
    public void onShowCustomView(View view, IX5WebChromeClient.CustomViewCallback customViewCallback) {
        webView.setVisibility(View.GONE);
        fullFramlayout.setVisibility(View.VISIBLE);
        fullFramlayout.removeAllViews();
        fullFramlayout.addView(view);
        callback = customViewCallback;
        super.onShowCustomView(view, customViewCallback);
    }

    @Override
    public void onShowCustomView(View view, int i, IX5WebChromeClient.CustomViewCallback customViewCallback) {
        webView.setVisibility(View.GONE);
        fullFramlayout.setVisibility(View.VISIBLE);
        fullFramlayout.removeAllViews();
        fullFramlayout.addView(view);
        callback = customViewCallback;
        super.onShowCustomView(view, i, customViewCallback);
    }
}
