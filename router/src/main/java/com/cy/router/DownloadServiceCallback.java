package com.cy.router;

/**
 * ************************************************************
 * author：cy
 * version：
 * create：2019/05/06 10:33
 * desc：
 * ************************************************************
 */

public interface DownloadServiceCallback extends Callback {
    public void onDownloading(int percent);

    public void onDownloadFinished();
}
