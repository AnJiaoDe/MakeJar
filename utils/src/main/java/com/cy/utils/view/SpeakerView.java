package com.cy.utils.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * ************************************************************
 * author：cy
 * version：
 * create：2019/05/13 09:45
 * desc：
 * ************************************************************
 */

public class SpeakerView extends View {
    private Paint paint;
    private float width_stroke=4;
    private float width_stroke_close_line=4;
    private int color_paint=0xffffffff;
    private int color_paint_close_line=0xffffffff;
    private int width_view;
    private int height_view;
    private int paddingLeft, paddingTop, paddingRight, paddingBottom;
    private boolean close;

    public SpeakerView(Context context) {
        this(context, null);
    }

    public SpeakerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setPadding(int left, int top, int right, int bottom) {
        this.paddingLeft = left;
        this.paddingTop = top;
        this.paddingRight = right;
        this.paddingBottom = bottom;
    }

    public void setPadding(int padding) {
        this.paddingLeft = padding;
        this.paddingTop = padding;
        this.paddingRight = padding;
        this.paddingBottom = padding;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint = new Paint();
        // 你可以绘制很多任意多边形，比如下面画六连形
        paint.setColor(color_paint);
        paint.setStyle(Paint.Style.STROKE);//设置空心
        paint.setStrokeWidth(width_stroke);
        paint.setAntiAlias(true);
        Path path = new Path();
        int width_draw = width_view - paddingLeft - paddingRight;
        int height_draw = height_view - paddingTop - paddingBottom;
        path.moveTo(paddingLeft + width_draw * 1f / 5, paddingTop + height_draw * 1f / 3);
        path.lineTo(paddingLeft + width_draw * 3f / 7, paddingTop + height_draw * 1f / 3);
        path.lineTo(paddingLeft + width_draw * 4f / 6, paddingTop + height_draw * 1 / 9);
        path.quadTo(paddingLeft + width_draw * 5f / 6, paddingTop + height_draw * 1f / 2, paddingLeft + width_draw * 4f / 6, paddingTop + height_draw * 8f / 9);
        path.lineTo(paddingLeft + width_draw * 3f / 7, paddingTop + height_draw * 2f / 3);
        path.lineTo(paddingLeft + width_draw * 1f / 5, paddingTop + height_draw * 2f / 3);
        path.close();//封闭
        canvas.drawPath(path, paint);
        if (close) {
            paint.setStrokeWidth(width_stroke_close_line);
            paint.setColor(color_paint_close_line);
            canvas.drawLine(paddingLeft + width_draw * 1f / 6, paddingTop + height_draw * 1f / 6,
                    width_view - paddingRight - width_draw * 1f / 6, height_view - paddingBottom - height_draw * 1f / 6, paint);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width_view = MeasureSpec.getSize(widthMeasureSpec);
        height_view = MeasureSpec.getSize(heightMeasureSpec);
    }

    public SpeakerView setClose(boolean close) {
        this.close = close;
        return this;
    }

    public SpeakerView setWidth_stroke(float width_stroke) {
        this.width_stroke = width_stroke;
        return this;
    }

    public SpeakerView setColor_paint_close_line(int color_paint_close_line) {
        this.color_paint_close_line = color_paint_close_line;
        return this;
    }

    public SpeakerView setWidth_stroke_close_line(float width_stroke_close_line) {
        this.width_stroke_close_line = width_stroke_close_line;
        return this;
    }

    public SpeakerView setColor_paint(int color_paint) {
        this.color_paint = color_paint;
        return this;
    }
}
