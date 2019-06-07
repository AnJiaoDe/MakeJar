package com.cy.router;

/**
 * Created by Administrator on 2018/11/30 0030.
 */

public interface VideoListener extends LoadListener {
    public void onCompleted();
    public void onSkip();
}
