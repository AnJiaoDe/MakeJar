package com.cy.utils.utils;

/**
 * ************************************************************
 * author：cy
 * version：
 * create：2019/04/13 14:46
 * desc：
 * ************************************************************
 */

public class GpsInfoBean {

    private double longitude=0.0;
    private double latitude=0.0;
    private long time=0;

    public GpsInfoBean() {
    }

    public GpsInfoBean(double longitude, double latitude, long time) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.time = time;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
