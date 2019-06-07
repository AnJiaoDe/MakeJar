package com.cy.utils.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

/**
 * Created by lenovo on 2018/1/5.
 */

public class DeviceInfoUtils {

    public static boolean isXiaoMi3C() {
        String model = Build.MODEL;
        String brand = Build.BRAND;
        if (brand.equals("Xiaomi") && model.trim().contains("MI 3C")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isXiaoMi() {
        String displayStr = Build.DISPLAY;
        String brand = Build.BRAND;

        if ((displayStr != null && displayStr.toLowerCase().contains("miui")) || "Xiaomi".equalsIgnoreCase(brand)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isMeiZu() {
        String brand = Build.BRAND;
        if ("Meizu".equalsIgnoreCase(brand)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isSamsung() {
        String manufacturer = Build.MANUFACTURER;
        int sdkVersion = Build.VERSION.SDK_INT;
        String model = Build.MODEL;
        if ((manufacturer != null && manufacturer.trim().contains("samsung") && sdkVersion >= 9)
                && (model == null || (!model.trim().toLowerCase()
                .contains("google") && !model.trim().toLowerCase()
                .contains("nexus")))) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isLenovo() {
        String model = Build.MODEL;
        if (model != null && (model.startsWith("Lenovo") || model.toLowerCase().contains("lenovo"))) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isHUAWEI() {
        return Build.MANUFACTURER.equalsIgnoreCase("huawei") || Build.USER.equalsIgnoreCase("huawei") || Build.DEVICE.equalsIgnoreCase("huawei");
    }

    /**
     * 获取设备信息
     * 设备信息=Product: 2013022, CPU_ABI: armeabi-v7a, TAGS: release-keys, VERSION_CODES.BASE: 1,
     * MODEL: 2013022, SDK: 17, VERSION.RELEASE: 4.2.1, DEVICE: HM2013022,
     * DISPLAY: HBJ2.0, BRAND: Xiaomi, BOARD: 2013022,
     * FINGERPRINT: Xiaomi/2013022/HM2013022:4.2.1/HM2013022/JHACNBL30.0:user/release-keys,
     * ID: HM2013022, MANUFACTURER: Xiaomi, USER: builder
     */
    public static String getDeviceInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Product: " + Build.PRODUCT)  //产品
                .append(", CPU_ABI: " + Build.CPU_ABI)
                .append(", TAGS: " + Build.TAGS)
                .append(", VERSION_CODES.BASE: " + Build.VERSION_CODES.BASE) //版本代码
                .append(", MODEL: " + Build.MODEL) //设备名
                .append(", SDK: " + Build.VERSION.SDK)
                .append(", VERSION.RELEASE: " + Build.VERSION.RELEASE)
                .append(", DEVICE: " + Build.DEVICE)
                .append(", DISPLAY: " + Build.DISPLAY)
                .append(", BRAND: " + Build.BRAND)
                .append(", BOARD: " + Build.BOARD)
                .append(", FINGERPRINT: " + Build.FINGERPRINT)
                .append(", ID: " + Build.ID)
                .append(", MANUFACTURER: " + Build.MANUFACTURER)
                .append(", USER: " + Build.USER);
        Log.i("tag", "设备信息=" + sb.toString());
        return sb.toString();
    }

    /**
     * 获取厂商
     *
     * @return
     */
    public static String getDeviceFactory() {
        return Build.MANUFACTURER;
    }

    /**
     * 获取型号
     *
     * @return
     */
    public static String getDeviceModel() {
        return Build.MODEL;
    }
    public static String getImei(Context context) {
        String imei = "869758037956276";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //权限未同意
            if (context.checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                return imei;
            }
        }
        try {
            TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            if (Build.VERSION.SDK_INT >= 26) {
                imei = manager.getImei(0);
                if (imei == null || imei.equals("")) imei = manager.getImei(1);
                LogUtils.log("imei", imei);
                return imei;
            }
            Method method = manager.getClass().getMethod("getDeviceId", int.class);
            String IMEI1 = manager.getDeviceId();//获取的是IMEI1
            Object IMEI2 = method.invoke(manager, 1);//获取的是IMEI2
            if (IMEI1 == null || IMEI1.equals("")) {
                LogUtils.log("imei",IMEI2);
                return (String) IMEI2;
            }else {
                LogUtils.log("imei",IMEI1);

                return IMEI1;
            }

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return imei;
    }
    /**
     * @param context
     * @return
     */
//    public static String[] getImeis(Context context) {
////        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
////        String deviceId = tm.getDeviceId();
////        if (deviceId == null) {
////            return "A000008794AB52";
////        } else {
////            return deviceId;
////        }
//        String imeid="A000008794AB52";
//        String imei1 ="869758037956276";
//        String imei2 ="869758037956268";
//
//        try {
//            TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
//            Method method = manager.getClass().getMethod("getDeviceId", int.class);
//            Object IMEI1= method.invoke(manager, 0);//获取的是IMEI1
//            Object IMEI2= method.invoke(manager, 1);//获取的是IMEI2
//            Object IMEID= method.invoke(manager, 2);//获取的是IMEID
//            LogUtils.log("devideid",manager.getDeviceId());
//            LogUtils.log("im1",IMEI1);
//            LogUtils.log("im2",IMEI2);
//            LogUtils.log("imd",IMEID);
//            if(IMEI1!=null)imei1= (String) IMEI1;
//            if(IMEI2!=null)imei2= (String) IMEI2;
//            if(IMEID!=null)imeid= (String) IMEID;
//
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//        return new String[]{imei1,imei2};
//
//
//    }


    /**
     * 通过网络接口取mac地址
     *
     * @return
     */
    public static String getMacAddress() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;

                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return "02:00:00:00:00:00";
                }

                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    res1.append(String.format("%02X:", b));
                }

                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "02:00:00:00:00:00";
    }

    /**
     * 判断是否平板设备
     *
     * @param context
     * @return true:平板,false:手机
     */
    public static boolean isTabletDevice(Context context) {
        return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >=
                Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

}
