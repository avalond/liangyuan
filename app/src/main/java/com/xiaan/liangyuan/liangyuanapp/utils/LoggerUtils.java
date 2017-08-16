package com.xiaan.liangyuan.liangyuanapp.utils;

import com.orhanobut.logger.AndroidLogAdapter;
import com.xiaan.liangyuan.liangyuanapp.BuildConfig;

/**
 * Created by kevin.
 */

public class LoggerUtils {
    static {
        com.orhanobut.logger.Logger.addLogAdapter(new AndroidLogAdapter());
    }

    public static void v(String tag, String msg) {

        if (BuildConfig.DEBUG) {
            com.orhanobut.logger.Logger.t(tag).v(msg);
        }
    }


    public static void i(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            com.orhanobut.logger.Logger.t(tag).i(msg);
        }
    }

    public static void w(String tag, String msg) {

        if (BuildConfig.DEBUG) {
            com.orhanobut.logger.Logger.t(tag).w(msg);
        }
    }


    public static void d(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            com.orhanobut.logger.Logger.t(tag).d(msg);
        }
    }

    public static void e(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            com.orhanobut.logger.Logger.t(tag).e(msg);
        }
    }

    public static void e(String tag, String msg, Throwable tr) {
        if (BuildConfig.DEBUG) {
            com.orhanobut.logger.Logger.t(tag).e(msg, tr);
        }
    }

    public static void wtf(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            com.orhanobut.logger.Logger.t(tag).wtf(msg);
        }
    }


    public static void json(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            com.orhanobut.logger.Logger.t(tag).json(msg);
        }
    }


    public static void xml(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            com.orhanobut.logger.Logger.t(tag).xml(msg);
        }
    }
}
