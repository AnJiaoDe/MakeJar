package com.cy.http;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.cy.http.utils.LogUtils;


/**
 * ************************************************************
 * author：cy
 * version：
 * create：2019/04/11 16:32
 * desc：
 * ************************************************************
 */

public class Imageloader extends BaseRequestGenerator<Imageloader> {
    private String cachePath;
    private boolean cache=true;
    private int reqWidth;
    private int reqHeight;
    private ImageView iv;
    private Context context;
    private ImageView.ScaleType scaleType= ImageView.ScaleType.CENTER_INSIDE ;
    public Imageloader(Context context) {
        this.context = context;
        this.method(HttpUtils.METHODS[0]);
    }

    public Imageloader scaleType(ImageView.ScaleType scaleType) {
        this.scaleType = scaleType;
        return this;
    }

    public Imageloader cachePath(String cachePath) {
        this.cachePath = cachePath;
        return this;
    }

    public Imageloader cache(boolean cache) {
        this.cache = cache;
        return this;
    }

    public Imageloader width(int reqWidth) {
        this.reqWidth = reqWidth;
        return this;
    }

    public Imageloader height(int reqHeight) {
        this.reqHeight = reqHeight;
        return this;
    }

    public Imageloader into(ImageView iv) {
        this.iv = iv;
        return this;
    }

    public void load() {
        if (iv == null) {
            LogUtils.log("请配置对应的Imageview");
            return;
        }
        if (cachePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(cachePath);
            if (bitmap != null && bitmap.getWidth() > 0) {
                iv.setImageBitmap(bitmap);
                iv.setScaleType(scaleType);
                return;
            }
        }

        BitmapCallbackImpl bitmapCallback = new BitmapCallbackImpl(context) {
            @Override
            protected void onSuccess(Bitmap response) {
                iv.setImageBitmap(response);
                iv.setScaleType(scaleType);

            }

            @Override
            protected void onLoading(Object readedPart, int percent, long current, long length) {

            }

            @Override
            protected void onCancel(Object readedPart, int percent, long current, long length) {

            }

            @Override
            protected void onFail(String errorMsg) {
                LogUtils.log(errorMsg);
            }
        };
        bitmapCall(bitmapCallback);

    }

    public void load(BitmapCallbackImpl bitmapCallback) {
        if (iv == null) {
            LogUtils.log("请配置对应的Imageview");
            return;
        }
        if (cachePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(cachePath);
            if (bitmap != null && bitmap.getWidth() > 0) {
                iv.setImageBitmap(bitmap);
                iv.setScaleType(scaleType);

                return;
            }
        }
        bitmapCall(bitmapCallback);

    }

    private void bitmapCall(BitmapCallbackImpl bitmapCallback) {
        bitmapCallback.url(url)
                .cache(cache)
                .cachePath(cachePath)
                .reqWidth(reqWidth)
                .reqHeight(reqHeight);
        Call call = new CallEnqueueImpl(generateRequest(tag));
        HttpUtils.getInstance().addCall(call);
        call.enqueue(bitmapCallback);
    }

    @Override
    public Request generateRequest(Object tag) {
        Request.Builder builder = new Request.Builder();
        return builder.setTag(tag)
                .setUrl(url)
                .setMethod(method)
                .build();
    }


}
