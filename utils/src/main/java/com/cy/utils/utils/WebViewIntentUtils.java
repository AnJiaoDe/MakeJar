package com.cy.utils.utils;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.webkit.WebView;

import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ************************************************************
 * author：cy
 * version：
 * create：2019/05/13 16:43
 * desc：
 * ************************************************************
 */

public class WebViewIntentUtils {

    private static final String TAG = "IntentUtils";
    /**
     * 系统可以处理的url正则
     */
    private static final Pattern ACCEPTED_URI_SCHEME = Pattern.compile("(?i)"
            + // switch on case insensitive matching
            '('
            + // begin group for scheme
            "(?:http|https|ftp|file)://" + "|(?:inline|data|about|javascript):" + "|(?:.*:.*@)"
            + ')' + "(.*)");

    private Activity mActivity = null;

    public WebViewIntentUtils(Activity activity) {
        this.mActivity = activity;
    }

    public boolean shouldOverrideUrlLoadingByApp(WebView view, String url) {
        return shouldOverrideUrlLoadingByAppInternal(view, url, true);
    }

    private boolean shouldOverrideUrlLoadingByAppInternal(WebView view, String url, boolean interceptExternalProtocol) {
        if (isAcceptedScheme(url)) return false;
        Intent intent;
        try {
            intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME);
        } catch (URISyntaxException e) {
            Log.e(TAG, "URISyntaxException: " + e.getLocalizedMessage());
            return interceptExternalProtocol;
        }

        intent.setComponent(null);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {
            intent.setSelector(null);
        }

        //intent://dangdang://product//pid=23248697#Intent;scheme=dangdang://product//pid=23248697;package=com.dangdang.buy2;end
        //该Intent无法被设备上的应用程序处理
        if (mActivity.getPackageManager().resolveActivity(intent, 0) == null) {
            return tryHandleByMarket(intent) || interceptExternalProtocol;
        }

        try {
            mActivity.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Log.e(TAG, "ActivityNotFoundException: " + e.getLocalizedMessage());
            return interceptExternalProtocol;
        }
        return true;
    }

    private boolean tryHandleByMarket(Intent intent) {
        String packagename = intent.getPackage();
        if (packagename != null) {
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="
                    + packagename));
            try{
                mActivity.startActivity(intent);
            } catch (ActivityNotFoundException e) {
                Log.e(TAG, "tryHandleByMarket ActivityNotFoundException: " + e.getLocalizedMessage());
                return false;
            }
            return true;
        } else {
            return false;
        }
    }


    /**
     * 该url是否属于浏览器能处理的内部协议
     */
    private boolean isAcceptedScheme(String url) {
        //正则中忽略了大小写，保险起见，还是转成小写
        String lowerCaseUrl = url.toLowerCase();
        Matcher acceptedUrlSchemeMatcher = ACCEPTED_URI_SCHEME.matcher(lowerCaseUrl);
        if (acceptedUrlSchemeMatcher.matches()) {
            return true;
        }
        return false;
    }

}
