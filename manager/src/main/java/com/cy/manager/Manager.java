package com.cy.manager;

import android.app.Activity;

import com.cy.router.LoadListener;
import com.cy.router.RouterBuilder;
import com.cy.utils.utils.ReflexUtils;


/**
 * ************************************************************
 * author：cy
 * version：
 * create：2019/04/24 18:12
 * desc：
 * ************************************************************
 */

public class Manager {


    //???????????????????????????????????????????????????
    public static void load(final Activity activity, Object[] config, final LoadListener loadListener) {
        load(activity, "",0, config[0], loadListener);
    }

    private static void load(final Activity activity, final String dataSource, final int ad_type, Object config, final LoadListener loadListener) {
        String className = "com.cy.amodule.ARouterBuilderImpl";

        RouterBuilder routerBuilder = (RouterBuilder) ReflexUtils.invokeConstructor(className, new Class[]{}, new Object[]{});
        if (routerBuilder == null) {
            loadListener.onLoadFailed(dataSource + "未配置RouterBuilder接口的实现类");
            return;
        }
        routerBuilder.buildAdRequest().request(activity, ad_type, config, loadListener);
    }
}
