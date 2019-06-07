package com.cy.http;//package com.ly.http;
//
//import android.graphics.Bitmap;
//
//import BitmapUtils;
//import IOListener;
//import IOUtils;
//import SSLUtils;
//
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStream;
//import java.lang.reflect.Field;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.ProtocolException;
//import java.net.URL;
//
///**
// * ************************************************************
// * author：cy
// * version：
// * create：2018/12/28 17:16
// * desc：
// * ************************************************************
// */
//
//public class CallBlockImpl<T> extends Call<T> {
//
//    private Request request;
//    private IOUtils ioUtils;
//
//    private Callback callback;
//
//    public CallBlockImpl(Request request) {
//        this.request = request;
//    }
//
//    @Override
//    public void cancel() {
//        if (ioUtils == null) return;
//        ioUtils.stop();
//    }
//
//    @Override
//    public Request getRequest() {
//        return request;
//    }
//
//
//    protected void callSuccess(final Object response) {
//
//        callback.onSuccess(response);
//
//    }
//
//    @Override
//    public void block(final Callback<T> callback) {
//
//        if (callback == null) return;
//        this.callback = callback;
//        this.ioUtils = new IOUtils();
//
//        HttpURLConnection httpURLConnection = null;
//        InputStream inputStream = null;
//        try {
//            URL url = new URL(request.getUrl());
//            httpURLConnection = (HttpURLConnection) url.openConnection();
//            SSLUtils.trustAllSSL(httpURLConnection);
//
//            //设置出入可用
//            httpURLConnection.setDoInput(true);
//            // 设置输出可用
//            httpURLConnection.setRequestMethod(request.getMethod());
//
//            // 开始连接
//            httpURLConnection.connect();
//
//            if (httpURLConnection.getResponseCode() == 200) {
//                inputStream = httpURLConnection.getInputStream();
//
//                if (callback instanceof StringCallbackImpl) {
//
//
//                    ioUtils.read2String(httpURLConnection.getContentLength(), inputStream, new IOListener<String>() {
//                        @Override
//                        public void onCompleted(final String str) {
//
//                            callSuccess(str);
//
//                        }
//
//                        @Override
//                        public void onLoading(long current, long length) {
//
//                            callback.onLoading(current, length);
//                        }
//
//                        @Override
//                        public void onInterrupted() {
//
//                            callback.onFail("网络请求失败，线程被取消");
//
//                        }
//
//                        @Override
//                        public void onFail(String errorMsg) {
//                            callback.onFail(errorMsg);
//
//                        }
//                    });
//                } else if (callback instanceof BitmapCallbackImpl) {
//                    final BitmapCallbackImpl bitmapCallback = (BitmapCallbackImpl) callback;
//
//
//                    ioUtils.read2ByteArray(httpURLConnection.getContentLength(), inputStream, new IOListener<byte[]>() {
//                        @Override
//                        public void onCompleted(final byte[] result) {
//
//
//                            Bitmap bitmap = BitmapUtils.decodeBitmapFromBytes(result, bitmapCallback.getReqWidth(), bitmapCallback.getReqHeight());
//                            if (bitmap != null && bitmap.getWidth() > 0) {
//                                if (bitmapCallback.getCachePath() != null) {
//                                    File file = new File(((BitmapCallbackImpl) callback).getCachePath());
//                                    if (file != null) {
//                                        BitmapUtils.saveBitmapToFile(bitmap, file);
//                                        callSuccess(bitmap);
//
//                                        return;
//
//                                    }
//                                    callback.onFail("图片下载成功，但缓存失败");
//
//                                }
//                                callSuccess(bitmap);
//
//
//                            } else {
//                                callback.onFail("图片下载失败");
//
//                            }
//
//
//                        }
//
//                        @Override
//                        public void onLoading(long current, long length) {
//
//                            callback.onLoading(current, length);
//
//
//                        }
//
//                        @Override
//                        public void onInterrupted() {
//                            callback.onFail("网络请求失败，线程被取消");
//
//
//                        }
//
//                        @Override
//                        public void onFail(String errorMsg) {
//                            callback.onFail(errorMsg);
//
//                        }
//                    });
//                }
//
//
//            } else {
//                callback.onFail(httpURLConnection.getResponseMessage());
//
//            }
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//            callback.onFail("网络请求失败" + e.getMessage());
//
//
//        } catch (ProtocolException e) {
//            try {
//                Field methodField = HttpURLConnection.class.getDeclaredField("method");
//                methodField.setAccessible(true);
//                methodField.set(httpURLConnection, request.getMethod());
//            } catch (NoSuchFieldException e1) {
//                e1.printStackTrace();
//                callback.onFail("网络请求失败" + e1.getMessage());
//
//            } catch (IllegalAccessException e1) {
//                e1.printStackTrace();
//                callback.onFail("网络请求失败" + e1.getMessage());
//
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//
//            callback.onFail("网络请求失败" + e.getMessage());
//
//
//        } finally {
//            if (httpURLConnection != null) {
//                httpURLConnection.disconnect();
//            }
//            IOUtils.close(inputStream);
//
//            HttpUtils.getInstance().removeCall(CallBlockImpl.this);
//
//        }
//    }
//
//
//}
