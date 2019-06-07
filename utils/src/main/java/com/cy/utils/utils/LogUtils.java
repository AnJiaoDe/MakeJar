package com.cy.utils.utils;

import android.util.Log;

/**
 * Created by lenovo on 2017/8/20.
 */

public class LogUtils {
    public static void log(String tag, Object content) {
        if (tag==null)tag="LOG_E";

        Log.e(tag, "----------------------------------->>>>" + content);
    }
    public static void log(Object tag, Object content) {
        if (tag==null)tag="LOG_E";

        Log.e(String.valueOf(tag), "----------------------------------->>>>" + content);
    }

    public static void log(String tag) {
        if (tag==null)tag="LOG_E";

        Log.e(tag, "----------------------------------->>>>" );

    }

    public static void log(Object tag) {
        if (tag==null)tag="LOG_E";
        Log.e(String.valueOf(tag), "---------------------------------->>>>" );

    }
}
