package com.cy.utils.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.widget.ImageView;

/**
 * ************************************************************
 * author：cy
 * version：
 * create：2019/04/11 16:10
 * desc：
 * ************************************************************
 */

public class RecShapeImageView extends ImageView {

    //水波纹的颜色,默认是0x66000000，建议自定义水波纹颜色的时候，用argb,rgb都设置为0，a可随意，调整透明度为了水波纹看起来更美观
    private int colorRipple = 0x66000000;
    private int radiusCorner = 0;//圆角半径
    private int colorFill = 0x00000000;//填充色
    //stroke，描边相关
    private int strokeWidth = 0;//描边粗细，宽度
    private int strokeColor = 0x00000000;//描边颜色
    //形状类型,默认矩形
    private int shapeType = GradientDrawable.RECTANGLE;

    public RecShapeImageView(Context context) {
        super(context);
    }

    public void setBg() {
//        //设置了填充色或者设置了渐变色的开始和结束，或者设置了描边颜色，才会设置drawable
        if (colorFill == 0x00000000 && strokeColor == 0x00000000)
            return;
        GradientDrawable gradientDrawable = new GradientDrawable();//创建背景drawable
        //形状类型
        switch (shapeType) {
            case 0:
                gradientDrawable.setShape(GradientDrawable.RECTANGLE);//矩形
                gradientDrawable.setCornerRadius(radiusCorner);
                break;
            case 1:
                gradientDrawable.setShape(GradientDrawable.OVAL);//椭圆
                break;
            case 2:
                gradientDrawable.setShape(GradientDrawable.LINE);//直线
                break;
            case 3:
                gradientDrawable.setShape(GradientDrawable.RING);//圆环
                break;
        }
        gradientDrawable.setColor(colorFill);//设置填充色
        //描边
        gradientDrawable.setStroke(strokeWidth, strokeColor);
        Drawable[] layers = {gradientDrawable};
        LayerDrawable layerDrawable = new LayerDrawable(layers);
        /*
        设置drawable，大功告成
         */
        //水波纹5.0以上才有效,
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            //当控件设置了点击监听器，并且控件点击有效，时，才能产生水波纹
            RippleDrawable rippleDrawable = new RippleDrawable(ColorStateList.valueOf(colorRipple), layerDrawable, null);
            setBackground(rippleDrawable);
            return;
        }
        setBackgroundDrawable(layerDrawable);
        invalidate();
    }

    public RecShapeImageView setColorRipple(int colorRipple) {
        this.colorRipple = colorRipple;
        return this;
    }
    public RecShapeImageView setRadiusCorner(int radiusCorner) {
        this.radiusCorner = radiusCorner;
        return this;
    }
    public RecShapeImageView setColorFill(int colorFill) {
        this.colorFill = colorFill;
        return this;
    }
    public RecShapeImageView setStrokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth;
        return this;
    }
    public RecShapeImageView setStrokeColor(int strokeColor) {
        this.strokeColor = strokeColor;
        return this;
    }
    public RecShapeImageView setShapeType(int shapeType) {
        this.shapeType = shapeType;
        return this;
    }
}