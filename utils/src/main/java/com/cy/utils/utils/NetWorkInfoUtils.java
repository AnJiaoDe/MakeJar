package com.cy.utils.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.webkit.WebView;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import static android.content.Context.WIFI_SERVICE;

/**
 * ************************************************************
 * author：cy
 * version：
 * create：2019/04/09 09:17
 * desc：
 * ************************************************************
 */

public class NetWorkInfoUtils {
    /**
     * 获取当前网络类型
     **/
    public static String getNetworkType(Context context) {
//        结果返回值
        String netType = "NONE";
//        获取手机所有连接管理对象
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        获取NetworkInfo对象
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
//        NetworkInfo对象为空 则代表没有网络
        if (networkInfo == null) {
            return netType;
        }
//        否则 NetworkInfo对象不为空 则获取该networkInfo的类型
        int nType = networkInfo.getType();
        if (nType == ConnectivityManager.TYPE_WIFI) {
//            WIFI
            netType = "WIFI";
        } else if (nType == ConnectivityManager.TYPE_MOBILE) {
            int nSubType = networkInfo.getSubtype();
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            if (nSubType == TelephonyManager.NETWORK_TYPE_LTE
                    && !telephonyManager.isNetworkRoaming()) {
//                4G 网络
                netType = "4G";
            } else if (nSubType == TelephonyManager.NETWORK_TYPE_UMTS
                    || nSubType == TelephonyManager.NETWORK_TYPE_HSDPA
                    || nSubType == TelephonyManager.NETWORK_TYPE_EVDO_0
                    && !telephonyManager.isNetworkRoaming()) {
//                3G网络   联通的3G为UMTS或HSDPA 电信的3G为EVDO
                netType = "3G";
            } else if (nSubType == TelephonyManager.NETWORK_TYPE_GPRS
                    || nSubType == TelephonyManager.NETWORK_TYPE_EDGE
                    || nSubType == TelephonyManager.NETWORK_TYPE_CDMA
                    && !telephonyManager.isNetworkRoaming()) {
//                2G网络 移动和联通的2G为GPRS或EGDE，电信的2G为CDMA
                netType = "2G";
            } else
                netType = "NO DISPLAY";
        }
        return netType;
    }

    /**
     * 获取设备运营商
     * 运营商：
     * UNACCESSIBLE： 无权获取到运营商类型
     * UNKNOWN：未知的运营商
     * CHINA_MOBILE：中国移动
     * CHINA_TELECOM：中国电信
     * CHINA_UNICOM：中国联通
     * OTHER：其他运营商
     *
     * @return ["中国电信CTCC":"3"]["中国联通CUCC:"2"]["中国移动CMCC":"1"]["other":"0"]
     */
    public static String getOperatorType(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String operator = tm.getNetworkOperator();
        String opeType = "OTHER";
        // 中国联通
        if ("46001".equals(operator) || "46006".equals(operator) || "46009".equals(operator)) {
            opeType = "CHINA_UNICOM";
            // 中国移动
        } else if ("46000".equals(operator) || "46002".equals(operator) || "46004".equals(operator) || "46007".equals(operator)) {
            opeType = "CHINA_MOBILE";
            // 中国电信
        } else if ("46003".equals(operator) || "46005".equals(operator) || "46011".equals(operator)) {
            opeType = "CHINA_TELECOM";
        }
        return opeType;
    }

    /**
     * 获取IP地址
     *
     * @return
     * @throws SocketException
     */
    public static String getIPAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && (inetAddress instanceof Inet4Address)) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return "172.18.1.19";
    }

    /**
     * 获取基站ID
     *
     * @return
     */

    public static int getCid(Activity activity) {
        final TelephonyManager telephonyManager = (TelephonyManager) activity.getSystemService(Context.TELEPHONY_SERVICE);
        int cid = 0;

        //权限未同意

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                (activity.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                        activity.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
//            activity.requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 0);
            return cid;
        } else {
            try {

                if (telephonyManager.getPhoneType() == TelephonyManager.PHONE_TYPE_CDMA) {
                    CdmaCellLocation cdmaCellLocation = (CdmaCellLocation) telephonyManager.getCellLocation();
                    if (cdmaCellLocation != null)
                        cid = cdmaCellLocation.getBaseStationId(); //获取cdma基站识别标号 BID
//            int lac = cdmaCellLocation.getNetworkId(); //获取cdma网络编号NID
//            int sid = cdmaCellLocation.getSystemId(); //用谷歌API的话cdma网络的mnc要用这个getSystemId()取得→SID
                } else {
                    GsmCellLocation gsmCellLocation = (GsmCellLocation) telephonyManager.getCellLocation();
                    if (gsmCellLocation != null) cid = gsmCellLocation.getCid(); //获取gsm基站识别标号
//            int lac = gsmCellLocation.getLac(); //获取gsm网络编号
                }
            } catch (Exception e) {
                LogUtils.log("getCid", e.getMessage());
            }

        }

        return cid;
    }

    /**
     * 获取当前WIFI信息
     **/
    public static WifiInfo fetchSSIDInfo(Context context) {

        WifiManager manager = (WifiManager) context.getSystemService(WIFI_SERVICE);
        return manager.getConnectionInfo();
    }

    /**
     * 获取当前WIFi的MAC地址
     **/
    public static String getWifiMacAddress(Context context) {

        WifiInfo wifiInfo = fetchSSIDInfo(context);
        if (wifiInfo == null) return "";
        return wifiInfo.getMacAddress();
    }

    /**
     * 获取当前Wifi信号强度
     * 0到-100的区间值
     * 0到-50表示信号最好
     * -50到-70表示信号偏差
     * 小于-70表示最差
     **/
    public static int getWifiSignalStrength(Context context) {

        WifiInfo wifiInfo = fetchSSIDInfo(context);
        if (wifiInfo == null) return 0;
        return fetchSSIDInfo(context).getRssi();
    }

    /**
     * 获取当前WIFI名字
     **/
    public static String getWifiSSID(Context context) {

        WifiInfo wifiInfo = fetchSSIDInfo(context);
        if (wifiInfo == null) return "";
        return fetchSSIDInfo(context).getSSID().replace("\"", "");
    }

    /**
     * 获取当前WIFi的BSSID
     **/
    public static String getWifiBSSID(Context context) {

        WifiInfo wifiInfo = fetchSSIDInfo(context);
        if (wifiInfo == null) return "";
        return fetchSSIDInfo(context).getBSSID();
    }


    /**
     * 获取信号最强的wifi
     *
     * @param context
     * @return
     */
    public static ScanResult getBestWifi(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(WIFI_SERVICE);    //获得系统wifi服务
        List<ScanResult> list = (ArrayList<ScanResult>) wifiManager.getScanResults();
        if (list.size() == 0) return null;
        for (int i = 0; i < list.size(); i++)
            for (int j = 1; j < list.size(); j++) {
                //强到弱
                if (list.get(i).level < list.get(j).level)    //level属性即为强度
                {
                    ScanResult temp = null;
                    temp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, temp);
                }
            }
        return list.get(0);

    }

    /**
     * 判断是否连接了wifi
     *
     * @param context
     * @return
     */
    public static boolean isWifiConnected(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(WIFI_SERVICE);
        if (wifiManager.getWifiState() == WifiManager.WIFI_STATE_ENABLED) {
            ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo wifiInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            return wifiInfo.isConnected();
        } else {
            return false;
        }
    }

    /**
     * @param context
     * @return
     */
    public static String getUserAgent(Context context) {
        return new WebView(context).getSettings().getUserAgentString();
    }
}
