package com.cy.utils.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;

/**
 * ************************************************************
 * author：cy
 * version：
 * create：2019/04/09 11:48
 * desc：
 * ************************************************************
 */

public class GpsUtils {
    /**
     * 获取GPS信息，经度，纬度，时间戳,
     * 需要权限   <uses-permission android:name="android.permission.INTERNET"/>
     <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
     <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
     *
     * @return
     */
    public static GpsInfoBean getGpsInfo(final Activity activity) {

        final GpsInfoBean gpsInfoBean=new GpsInfoBean();

            //权限未同意

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M&&
                    (activity.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                    activity.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
                return gpsInfoBean;
            } else {
                LocationManager locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
                Location location = null;
                if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

                    location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                } else {
                    location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                }
                if (location != null) {
                    gpsInfoBean.setLongitude(location.getLongitude());
                    gpsInfoBean.setLatitude(location.getLatitude());
                    gpsInfoBean.setTime(location.getTime());
                }
            }


        return gpsInfoBean;
    }

}
