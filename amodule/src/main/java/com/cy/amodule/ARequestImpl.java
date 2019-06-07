package com.cy.amodule;

import android.app.Activity;
import android.os.Build;

import com.cy.http.HttpUtils;
import com.cy.http.StringCallbackImpl;
import com.cy.http.utils.ToastUtils;
import com.cy.router.AdRequest;
import com.cy.router.LoadListener;
import com.cy.utils.utils.LogUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * ************************************************************
 * author：cy
 * version：
 * create：2019/04/11 11:16
 * desc：
 * ************************************************************
 */

public class ARequestImpl implements AdRequest<Object> {
    @Override
    public void request(Activity activity, int ad_type, Object config, final LoadListener loadListener) {
        HttpUtils.getInstance().get("https://www.baidu.com/")
                .enqueue(new StringCallbackImpl() {
                    @Override
                    protected void onSuccess(String response) {
                        LogUtils.log("amodule请求陈宫");
                    }

                    @Override
                    protected void onLoading(Object readedPart, int percent, long current, long length) {

                    }

                    @Override
                    protected void onCancel(Object readedPart, int percent, long current, long length) {

                    }

                    @Override
                    protected void onFail(String errorMsg) {

                    }
                });
        ToastUtils.showToast(activity,"amodule请求成功");
    }

}
