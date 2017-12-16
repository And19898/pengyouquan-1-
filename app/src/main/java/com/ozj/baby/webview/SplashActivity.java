package com.ozj.baby.webview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.gyf.barlibrary.BarHide;
import com.gyf.barlibrary.ImmersionBar;
import com.ozj.baby.BuildConfig;
import com.ozj.baby.webview.tools.GuideTools;
import com.ozj.baby.webview.tools.LogUtil;
import com.ozj.baby.webview.tools.ResourceUtil;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * author: Rea.X
 * date: 2017/12/16.
 */

public class SplashActivity extends AppCompatActivity {
    private long timeStamp = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImageView im = new ImageView(this);
        setContentView(im);
        ImmersionBar.with(this)
                .fitsSystemWindows(false)
                .hideBar(BarHide.FLAG_HIDE_BAR).init();
        im.setBackgroundResource(ResourceUtil.getDrawableId(this, BuildConfig.SPLASH_PIC));
        timeStamp = System.currentTimeMillis();
        query();
    }

    private void query() {
        BmobQuery<Config> query = new BmobQuery<>();
        query.findObjects(new FindListener<Config>() {
            @Override
            public void done(List<Config> list, BmobException e) {
                if (e == null) {
                    if (list != null && list.size() != 0) {
                        for (Config config : list) {
                            if (config.getAppid().equals(getPackageName())) {
                                deal(config);
                                return;
                            }
                        }
                    }
                }
                if (e != null) {
                    LogUtil.e("e:::::::::::" + e.getMessage());
                }
                toMain();
            }
        });
    }

    private void deal(final Config configModel) {
        final String url = configModel.getUrl();
        if (configModel.isShow()) {
            if (GuideTools.needShowGuide()) {
                SplashTools.checkTime(timeStamp, new SplashTools.SplashCallback() {
                    @Override
                    public void done() {
                        GuideActivity.showGuide(SplashActivity.this, configModel);
                        finish();
                    }
                });
                return;
            } else {
                SplashTools.checkTime(timeStamp, new SplashTools.SplashCallback() {
                    @Override
                    public void done() {
                        WebViewActivity.load(SplashActivity.this, url);
                        finish();
                    }
                });
                return;
            }
        }
        toMain();
    }

    private void toMain() {
        startActivity(new Intent(this, com.ozj.baby.mvp.views.login.activity.SplashActivity.class));
        finish();
    }
}
