package com.cy.router;

/**
 * ************************************************************
 * author：cy
 * version：
 * create：2019/05/16 10:56
 * desc：
 * ************************************************************
 */

public interface VideoPlayCallback extends Callback {
    public void onStart();

    public void onCompleted();

    public void onPlay(int progress);

    public void onSkip();

}
