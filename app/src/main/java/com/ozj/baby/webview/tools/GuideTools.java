package com.ozj.baby.webview.tools;

import android.text.TextUtils;

import com.ozj.baby.BabyApplicationLike;

import static com.ozj.baby.webview.tools.Cons.PrefrencesKeys.VERSION;


/**
 * author: Rea.X
 * date: 2017/12/2.
 */

public class GuideTools {

    public static boolean needShowGuide() {
        String saveVersion = PreferencesHelper.getString(VERSION);
        String version = CommonUtils.getVersionNameOriginal(BabyApplicationLike.get());
        if (TextUtils.isEmpty(saveVersion)) return true;
        if (!saveVersion.equalsIgnoreCase(version)) return true;
        return false;
    }

    public static void guideDismiss() {
        PreferencesHelper.saveString(VERSION, CommonUtils.getVersionNameOriginal(BabyApplicationLike.get()));
    }
}
