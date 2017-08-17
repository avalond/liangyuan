package com.xiaan.liangyuan.liangyuanapp;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.xiaan.liangyuan.liangyuanapp.utils.LoggerUtils;

import io.realm.Realm;

/**
 * Created by kevin.
 */

public class LiangYuanApplication extends MultiDexApplication {
    private static final String TAG = LiangYuanApplication.class.getSimpleName();

    private static Context mContext;
    private String versionName;
    private int versionCode;

    private static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LoggerUtils.i(TAG, "application is on created");
        mContext = getApplicationContext();
        versionCode = 0;
        versionName = null;

        Realm.init(this);
    }

}
