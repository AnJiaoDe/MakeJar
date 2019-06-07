package com.cy.router;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by cy on 2018/11/27 0027.
 */

public interface Container<T> {

    public Object getTag();

    public void setTag(Object tag);

    public ViewGroup getRootView();

    public void initView(T response, LoadListener loadListener);

    public void show();

    public void close();

    public void destroy();

    public void cancelNet();

    public void onShowed();

    public void onClosed();

    public void onClicked(View view);

//    public void onReward();

}
