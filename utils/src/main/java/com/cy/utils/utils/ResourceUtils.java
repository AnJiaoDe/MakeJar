package com.cy.utils.utils;

import android.content.Context;

/**
 * ************************************************************
 * author：cy
 * version：
 * create：2019/05/08 11:02
 * desc：
 * ************************************************************
 */

public class ResourceUtils {
    public static int getId(Context context, String name, String defType) {
        return context.getResources().getIdentifier(name, defType, context.getPackageName());
    }
}
