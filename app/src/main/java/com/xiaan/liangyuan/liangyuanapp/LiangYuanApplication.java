package com.xiaan.liangyuan.liangyuanapp;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

/**
 * Created by kevin on 8/16/17.
 */

public class LiangYuanApplication extends MultiDexApplication {

    private static Context mContext;
    private String versionName;
    private int versionCode;

    private static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = getApplicationContext();
        versionCode = 0;
        versionName = null;
    }

}
