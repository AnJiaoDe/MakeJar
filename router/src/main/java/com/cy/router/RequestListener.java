package com.cy.router;

/**
 * ************************************************************
 * author：cy
 * version：
 * create：2019/04/12 11:22
 * desc：
 * ************************************************************
 */

public interface RequestListener<T> {

    public void onSuccess(T response);

    public void onFail(String errorMsg);
}
