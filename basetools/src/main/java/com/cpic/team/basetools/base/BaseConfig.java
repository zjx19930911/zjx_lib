package com.cpic.team.basetools.base;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.qmuiteam.qmui.arch.QMUISwipeBackActivityManager;


/**
 * Created by Administrator on 2017/2/6 0006.
 */

public class BaseConfig {

    public static Context context;
    public static String MainColor = "#ffffff";
    public static String URL = "";
    public static String User = "";
    public static String Key = "";
    public static String Name = "";
    public static Boolean isrelease = false;

    public static void init(Application appContext, String mainColor) {
        context = appContext;
        MainColor = mainColor;
        QMUISwipeBackActivityManager.init(appContext);
    }

    public static void setAliOss(String url, String user, String key, String name) {
        URL = url;
        User = user;
        Key = key;
        Name = name;
    }
}
