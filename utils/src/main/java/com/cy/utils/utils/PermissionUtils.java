package com.cy.utils.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

/**
 * ************************************************************
 * author：cy
 * version：
 * create：2019/01/18 16:14
 * desc：
 * ************************************************************
 */

public class PermissionUtils {

    /*
          6.0权限检查存储权限
           */
    public static void checkWrite(Activity activity, PermissonListener permissonListener) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            //权限未同意

            if (activity.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                permissonListener.onPermissonNo();
                activity.requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
                return;
            } else {
                permissonListener.onPermissonHave();
            }

        }

    }

    /*
          6.0权限检查phonestate权限
           */
    public static void checkReadPhone(Activity activity, PermissonListener permissonListener) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            //权限未同意

            if (activity.checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                permissonListener.onPermissonNo();
                activity.requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE}, 0);
                return;
            } else {
                permissonListener.onPermissonHave();
            }

        }

    }

    /*
               6.0权限检查定位权限
                */
    public static void checkLocation(Activity activity, PermissonListener permissonListener) {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            //权限未同意

            if (activity.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                    activity.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                permissonListener.onPermissonNo();
                activity.requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 0);
                return;
            } else {
                permissonListener.onPermissonHave();
            }

        }
    }

    /*
          6.0权限检查p
           */
    public static void checkPermissions(Activity activity, String[] permissions, PermissonListener permissonListener) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            for (String permission : permissions) {

                if (activity.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                    permissonListener.onPermissonNo();
                    activity.requestPermissions(permissions, 0);
                    return;
                }
            }
            permissonListener.onPermissonHave();

        }

    }

    public static interface PermissonListener {
        public void onPermissonHave();

        public void onPermissonNo();
    }
}
