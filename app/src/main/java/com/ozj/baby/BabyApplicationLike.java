package com.ozj.baby;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.multidex.MultiDex;

import com.avos.avoscloud.AVAnalytics;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.PushService;
import com.lzy.okgo.OkGo;
import com.orhanobut.logger.AndroidLogTool;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.ozj.baby.base.User;
import com.ozj.baby.di.component.ApplicationComponent;
import com.ozj.baby.di.component.DaggerApplicationComponent;
import com.ozj.baby.di.module.ApplicationModule;
import com.ozj.baby.mvp.model.bean.Comment;
import com.ozj.baby.mvp.model.bean.Gallery;
import com.ozj.baby.mvp.model.bean.Souvenir;
import com.ozj.baby.mvp.views.home.activity.MainActivity;

import javax.inject.Inject;

import cn.bmob.v3.Bmob;
import cn.jpush.android.api.JPushInterface;
import im.fir.sdk.FIR;

public class BabyApplicationLike extends Application {
    private static ApplicationComponent mAppComponent;



    @Override
    public void onCreate() {
        super.onCreate();
        initThirdService();

        app = this;

        Bmob.initialize(this, BuildConfig.BMOB_KEY);
        JPushInterface.setDebugMode(BuildConfig.DEBUG);
        JPushInterface.init(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    private void initThirdService() {
        AVUser.alwaysUseSubUserClass(User.class);
        AVObject.registerSubclass(User.class);
        AVObject.registerSubclass(Gallery.class);
        AVObject.registerSubclass(Souvenir.class);
        AVObject.registerSubclass(Comment.class);
        AVOSCloud.initialize(this, "GpLTBKYub2ekB1GG2UUDdpmu-gzGzoHsz", "IjkswTLu60dF1rnnAHNoLM98");
        AVAnalytics.enableCrashReport(this, true);
        initComponent();
        mAppComponent.inject(this);
        Logger.init("Baby").logLevel(LogLevel.FULL).logTool(new AndroidLogTool());
        AVOSCloud.setDebugLogEnabled(false);
        FIR.init(this);
        PushService.setDefaultPushCallback(this, MainActivity.class);
        OkGo.getInstance().init(this);
    }


    private void initComponent() {
        if (mAppComponent == null)
            mAppComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
    }

    public static ApplicationComponent getAppComponent() {
        return mAppComponent;
    }

    private static Application app;
    public static Context get() {
        return app;
    }
}
