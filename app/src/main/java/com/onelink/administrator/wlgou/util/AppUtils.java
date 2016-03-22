package com.onelink.administrator.wlgou.util;

import android.content.Context;
import android.support.v4.util.LruCache;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.onelink.administrator.wlgou.base.BaseApplication;


/**
 * Created by Administrator on 2015/12/10.
 */
public class AppUtils {

    private static final String IS_FIRST_VISIT = "IS_FIRST_VISIT";
    private static final String IS_LOGIN = "IS_LOGIN";
    private static final String USER_ID = "USER_ID";


    public static Context getContext() {
        return BaseApplication.context;
    }



    //方法是否是第一次访问
    public static boolean isFirstVisit() {
        return !PreferencesUtils.getInstance().getBoolean(getContext(), IS_FIRST_VISIT);
    }

    public static void setIsFirstVisit() {
        PreferencesUtils.getInstance().putBoolean(getContext(), IS_FIRST_VISIT, true);
    }

    //是否已登录
    public static boolean isLogin() {
        return PreferencesUtils.getInstance("login").getBoolean(BaseApplication.context, IS_LOGIN);
    }

    //设置以登录
    public static void setIsLogin(boolean isLogin) {
        PreferencesUtils.getInstance("login").putBoolean(BaseApplication.context, "IS_LOGIN", isLogin);
    }

    //获取用户id
    public static int getUserId() {
        return PreferencesUtils.getInstance("userId").getInt(BaseApplication.context, USER_ID);
    }
    //设置用户登录后的id
    public static void setUserId(int uId){
        PreferencesUtils.getInstance("userId").putInt(BaseApplication.context, USER_ID, uId);
    }






}
