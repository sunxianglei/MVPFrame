package com.xianglei.mvpframe.utils;

import android.util.Log;

/**
 * 简单封装可开关Log日志
 * @author sunxianglei
 * @date 2017/12/24
 */

public class Logger {

    public static void v(String tag, String msg) {
        if (Config.DEBUG) {
            Log.v(tag, msg);
        }
    }

    public static void v(String tag, String msg, Throwable e) {
        if (Config.DEBUG) {
            Log.v(tag, msg, e);
        }
    }

    public static void d(String tag, String msg) {
        if (Config.DEBUG) {
            Log.d(tag, msg);
        }
    }

    public static void d(String tag, String msg, Throwable e) {
        if (Config.DEBUG) {
            Log.d(tag, msg, e);
        }
    }

    public static void i(String tag, String msg) {
        if (Config.DEBUG) {
            Log.i(tag, msg);
        }
    }

    public static void i(String tag, String msg, Throwable e) {
        if (Config.DEBUG) {
            Log.i(tag, msg, e);
        }
    }

    public static void w(String tag, String msg) {
        if (Config.DEBUG) {
            Log.w(tag, msg);
        }
    }

    public static void w(String tag, String msg, Throwable e) {
        if (Config.DEBUG) {
            Log.w(tag, msg, e);
        }
    }

    public static void e(String tag, String msg) {
        if (Config.DEBUG) {
            Log.e(tag, msg);
        }
    }

    public static void e(String tag, String msg, Throwable e) {
        if (Config.DEBUG) {
            Log.e(tag, msg, e);
        }
    }

}
