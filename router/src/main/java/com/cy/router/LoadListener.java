package com.cy.router;


/**
 * Created by cy on 2018/12/8 0008.
 */

public interface LoadListener {

    public void onLoadSuccess(Container container);

    public void onLoadFailed(String errorMsg);
}
