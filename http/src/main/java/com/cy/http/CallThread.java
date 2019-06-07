package com.cy.http;



import com.cy.http.utils.LogUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Map;

/**
 * ************************************************************
 * author：cy
 * version：
 * create：2019/04/10 17:13
 * desc：
 * ************************************************************
 */
public class CallThread extends Thread {

    private HttpURLConnection httpURLConnection;
    private InputStream inputStream;
    private Request request;
    private Callback callback;

    public CallThread(Request request, Callback callback) {
        this.request = request;
        this.callback = callback;
    }

    @Override

    public void run() {

        try {
            request();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            callback.callOnFail("网络请求失败，MalformedURLException" + e.getMessage());

        } catch (ProtocolException e) {
            try {
                Field methodField = HttpURLConnection.class.getDeclaredField("method");
                methodField.setAccessible(true);
                methodField.set(httpURLConnection, request.getMethod());
            } catch (NoSuchFieldException e1) {
                callback.callOnFail("网络请求失败，NoSuchFieldException" + e1.getMessage());

            } catch (IllegalAccessException e2) {
                callback.callOnFail("网络请求失败，IllegalAccessException" + e2.getMessage());
            }

        } catch (IOException e) {
            callback.callOnFail("网络请求失败，IOException" + e.getMessage());

        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }

    }

    public void request() throws IOException {
        URL url = new URL(request.getUrl());
        httpURLConnection = (HttpURLConnection) url.openConnection();
        Map<String, String> header = request.getHeader();
        if (header != null) {
            for (String key : header.keySet()) {
                httpURLConnection.setRequestProperty(key, header.get(key));
            }
        }
        // 设置请求方式
        httpURLConnection.setRequestMethod(request.getMethod());
        if (request.getMethod() == HttpUtils.METHODS[1]) {
            httpURLConnection.setDoOutput(true);
            StringBuilder stringBuilder = new StringBuilder();
            Map<String, Object> params = request.getParams();
            if (params != null) {

                for (String key : params.keySet()) {
                    stringBuilder.append(key).append("=").append(params.get(key)).append("&");
                }
            }
            OutputStream outputStream;
            if (params != null && params.size() > 0) {
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                outputStream = httpURLConnection.getOutputStream();
                outputStream.write(stringBuilder.toString().getBytes());
                outputStream.flush();
                outputStream.close();
            } else if (request.getByteProto() != null) {
                httpURLConnection.setRequestProperty("Content-Type", "application/x-protobuf");
                httpURLConnection.setRequestProperty("Accept", "application/x-protobuf");
                outputStream = httpURLConnection.getOutputStream();
                outputStream.write(request.getByteProto());
                outputStream.flush();
                outputStream.close();
            }

        }
        long fileLength = 0;
        File file = null;
        if (callback instanceof FileCallbackImpl) {
            file = new File(((FileCallbackImpl) callback).getPathToSave());
            if (file.exists()) {
                fileLength = file.length();
                 /*
                *
                * GET /down.zip HTTP/1.0
                * User-Agent: NetFox
                * RANGE: bytes=2000070-
                 */
                // 设置断点续传的开始位置
                LogUtils.log("filelength", fileLength);
//                try {

                    httpURLConnection.setRequestProperty("Range", "bytes=" +fileLength + "-");
//                } catch (Exception e) {
//                    //说明已经下载完毕
//                    callback.callOnSuccess(file);
//                    return;
//                }
            }

        }
        LogUtils.log("剩余大小", httpURLConnection.getContentLength());
        /**
         *         说明已经下载完毕
         Range设置为最大值会响应416  Requested Range Not Satisfiable
         */
        if (httpURLConnection.getResponseCode() ==416) {
            callback.callOnSuccess(file);
            return;
        }
        if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK ||httpURLConnection.getResponseCode()==HttpURLConnection.HTTP_PARTIAL) {
            inputStream = httpURLConnection.getInputStream();
            callback.convertSuccess(fileLength + httpURLConnection.getContentLength(), inputStream);

        } else {
            LogUtils.log("失败");
            callback.callOnFail(httpURLConnection.getResponseCode()+httpURLConnection.getResponseMessage());
        }
    }
}


