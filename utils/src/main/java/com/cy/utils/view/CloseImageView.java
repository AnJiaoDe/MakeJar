package com.cy.utils.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by Administrator on 2018/11/27 0027.关闭按钮
 */

public class CloseImageView extends RecShapeImageView {
    private int colorPaint=0xffeeeeee;
    private int strokeWidth=6;
    private int paddingLeft,paddingTop,paddingRight,paddingBottom;
    public CloseImageView(Context context, int colorPaint, int strokeWidth) {
        super(context);
        this.colorPaint=colorPaint;
        this.strokeWidth=strokeWidth;
    }


    @Override
    public void setPadding(int left, int top, int right, int bottom) {
        this.paddingLeft=left;
        this.paddingTop=top;
        this.paddingRight=right;
        this.paddingBottom=bottom;
    }


//    public void setColorPaint(int colorPaint) {
//        this.colorPaint = colorPaint;
//
//    }
//
//    public void setStrokeWidth(int strokeWidth) {
//        this.strokeWidth = strokeWidth;
//    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint=new Paint();
        paint.setStrokeWidth(strokeWidth);
        paint.setAntiAlias(true);
        paint.setColor(colorPaint);
        canvas.drawLine(paddingLeft,paddingTop,getWidth()-paddingRight,getHeight()-paddingBottom,paint);
        canvas.drawLine(paddingLeft,getHeight()-paddingBottom,getWidth()-paddingRight,paddingTop,paint);
    }
}
