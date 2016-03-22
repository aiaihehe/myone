package com.onelink.administrator.wlgou.base;

import android.app.Application;
import android.content.Context;
import android.support.v4.util.LruCache;



import org.xutils.x;

/**
 * Created by Administrator on 2015/11/28.
 */
public class BaseApplication extends Application {
    public static Context context;


    @Override
    public void onCreate() {
        super.onCreate();

        this.context = getApplicationContext();
        x.Ext.init(this);
//        x.Ext.setDebug(BuildConfig.DEBUG);


    }


}