package com.xianglei.mvpframe.utils;

import android.util.Log;

/**
 * 简单封装可开关Log日志
 * @author sunxianglei
 * @date 2017/12/24
 */

public class Logger {

    public void v(String tag, String msg) {
        if (Config.isLog) {
            Log.v(tag, msg);
        }
    }

    public void v(String tag, String msg, Throwable e) {
        if (Config.isLog) {
            Log.v(tag, msg, e);
        }
    }

    public void d(String tag, String msg) {
        if (Config.isLog) {
            Log.d(tag, msg);
        }
    }

    public void d(String tag, String msg, Throwable e) {
        if (Config.isLog) {
            Log.d(tag, msg, e);
        }
    }

    public void i(String tag, String msg) {
        if (Config.isLog) {
            Log.i(tag, msg);
        }
    }

    public void i(String tag, String msg, Throwable e) {
        if (Config.isLog) {
            Log.i(tag, msg, e);
        }
    }

    public void w(String tag, String msg) {
        if (Config.isLog) {
            Log.w(tag, msg);
        }
    }

    public void w(String tag, String msg, Throwable e) {
        if (Config.isLog) {
            Log.w(tag, msg, e);
        }
    }

    public void e(String tag, String msg) {
        if (Config.isLog) {
            Log.e(tag, msg);
        }
    }

    public void e(String tag, String msg, Throwable e) {
        if (Config.isLog) {
            Log.e(tag, msg, e);
        }
    }

}
