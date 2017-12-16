package com.ozj.baby.mvp.views.home.activity;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.ozj.baby.R;
import com.ozj.baby.util.ProgressDialogUtils;
import com.ozj.baby.webview.tools.SoftKeyboardUtils;

/**
 * author: Rea.X
 * date: 2017/7/18.
 */

public abstract class UIActivity extends AppCompatActivity {
    protected Toolbar toolbar;
    protected TextView tvBack;
    protected TextView tvTitle;
    protected AppCompatActivity context;
    protected ImmersionBar immersionBar;
    protected Handler handler;

    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(getLayoutId());
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvBack = (TextView) findViewById(R.id.tv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        if (toolbar != null) {
            toolbar.setTitle("");
            toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            setSupportActionBar(toolbar);
            tvBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
        handler = new Handler();
        initScreen();
        init();
    }

    protected final void initScreen() {
        immersionBar = ImmersionBar
                .with(this)
                .statusBarColor(R.color.colorPrimary)
                .navigationBarColor(R.color.colorPrimary)
                .fitsSystemWindows(true)
                .keyboardEnable(true)
        ;
        immersionBar.init();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (immersionBar != null)
            immersionBar.destroy();
    }


    protected abstract
    @LayoutRes
    int getLayoutId();

    protected abstract void init();

    protected void showProgress(){
        ProgressDialogUtils.showProgress(this);
    }

    protected void dismissProgress(){
        ProgressDialogUtils.dismissProgress();
    }
    @Override
    protected void onStop() {
        super.onStop();
        SoftKeyboardUtils.hideSoftKeyboard(context);
        dismissProgress();
    }
}
