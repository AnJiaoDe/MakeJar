package com.cy.router;

import android.app.Activity;

/**
 * ************************************************************
 * author：cy
 * version：
 * create：2019/04/29 14:54
 * desc：类似于建造者模式，此处router使用接口是为了反射方便，只需反射实现类的构造方法，调用method时无需反射
 * ************************************************************
 */

public interface AdRequest<T> {
    public void request(Activity activity, int ad_type, T config, LoadListener loadListener);
}
