package com.ozj.baby.webview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.gyf.barlibrary.BarHide;
import com.gyf.barlibrary.ImmersionBar;
import com.ozj.baby.R;
import com.ozj.baby.webview.tools.GuideTools;

import cn.bingoogolapple.bgabanner.BGABanner;


public class GuideActivity extends AppCompatActivity {
    private Config configModel;
    private BGABanner bannerGuideForeground;

    public static void showGuide(Activity context, Config data) {
        Intent intent = new Intent(context, GuideActivity.class);
        intent.putExtra("data", data);
        context.startActivity(intent);
        context.finish();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        bannerGuideForeground = (BGABanner) findViewById(R.id.banner_guide_foreground);
        init();
    }

    protected void init() {

        configModel = (Config) getIntent().getSerializableExtra("data");
        ImmersionBar.with(this)
                .fitsSystemWindows(false)
                .hideBar(BarHide.FLAG_HIDE_BAR).init();
        bannerGuideForeground.setEnterSkipViewIdAndDelegate(R.id.btn_guide_enter, 0, new BGABanner.GuideDelegate() {
            @Override
            public void onClickEnterOrSkip() {
                GuideTools.guideDismiss();
                if (configModel.isShow()) {
                    String url = configModel.getUrl();
                    WebViewActivity.load(GuideActivity.this, url);
                    finish();
                }
            }
        });
       bannerGuideForeground.setData(R.drawable.guide_1, R.drawable.guide_2, R.drawable.guide_3, R.drawable.guide_4, R.drawable.guide_5);
    }
}
