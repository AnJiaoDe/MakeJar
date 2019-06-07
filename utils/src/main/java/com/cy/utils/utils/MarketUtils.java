package com.cy.utils.utils;//package com.ly.utils.utils;
//
//import android.content.Context;
//import android.content.Intent;
//import android.net.Uri;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Created by Administrator on 2018/12/26 0026.
// */
//
//public class MarketUtils {
//    private static Map<String, String> markets = new HashMap<String, String>();
//
//    static {
//        markets.put("xiaomi", "com.xiaomi.market");
//        markets.put("huawei", "com.huawei.appmarket");
//        markets.put("oppo", "com.oppo.market");
//        markets.put("vivo", "com.bbk.appstore");
//        markets.put("meizu", "com.meizu.mstore");
//        markets.put("jinli", "com.gionee.aora.market");
//        markets.put("samsung", "com.sec.android.app.samsungapps");
//        markets.put("lenovo", "com.lenovo.leos.appstore");
//        markets.put("coolpad", "com.yulong.android.coolmart");
//        markets.put("qq", "com.tencent.android.qqdownloader");
//        markets.put("91market", "com.dragon.android.pandaspace");
//        markets.put("anzhi", "com.hiapk.marketpho");
//        markets.put("appchina", "com.yingyonghui.market");
//        markets.put("360market", "com.qihoo.appstore");
//        markets.put("baidu", "com.baidu.appsearch");
//        markets.put("wandoujia", "com.wandoujia.phoenix2");
//    }
//
//    public static void toPlay(Context ctx, String appPackageName, String marketName) {
////        LogUtils.log("toplay packageName:"+packageName+"    marketName:"+marketName);
//        try {
//            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(getPlayUrl(ctx, appPackageName)));
//            String marketPackageName = markets.get(marketName);
//            if (marketPackageName != null)
//                i.setPackage(marketPackageName);
//            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            ctx.startActivity(i);
//        } catch (Exception e) {
//            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(getPlayUrl(ctx, appPackageName)));
//            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            ctx.startActivity(i);
//        }
//    }
//
//    public static String getPlayUrl(Context ctx, String packageName) {
//        String playUrl = "";
//        if (packageName != null) {
//            playUrl = "market://details?id=" + packageName;
//        }
//        return playUrl;
//    }
//
//}
