package com.cy.http;


/**
 * Created by Administrator on 2018/12/21 0021.
 */

public class CallEnqueueImpl<T> extends Call<T> {


    private CallThread callThread;

    public CallEnqueueImpl(Request request) {
        super(request);
    }

    @Override
    public void cancel() {
        if (callback!=null)callback.cancel();
        if (callThread != null) callThread.interrupt();
        HttpUtils.getInstance().removeCall(this);
    }

    @Override
    public void enqueue(final Callback callback) {
        cancel();
        this.callback = callback;
        callThread = new CallThread(request, callback);
        HttpUtils.getInstance().addCall(this);
        callThread.start();
    }

}
