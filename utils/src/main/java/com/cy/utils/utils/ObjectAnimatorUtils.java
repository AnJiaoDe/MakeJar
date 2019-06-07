package com.cy.utils.utils;//package com.ly.utils.utils;
//
//import android.animation.AnimatorSet;
//import android.animation.ObjectAnimator;
//import android.view.View;
//
///**
// * Created by Administrator on 2018/12/11 0011.
// */
//
//public class ObjectAnimatorUtils {
//    public static void startObjAnimation(View viewContainer, float scale) {
//        startObjAnimation(viewContainer, scale, 500);
//    }
//
//    public static void startObjAnimation(View viewContainer, float scale, int duration) {
//        ObjectAnimator oa_alpha = ObjectAnimator.ofFloat(viewContainer, "alpha", 0f, 1f);
//        ObjectAnimator oa = ObjectAnimator.ofFloat(viewContainer, "scaleX", 0f, scale);
//        oa.setDuration(duration);
//        ObjectAnimator oa2 = ObjectAnimator.ofFloat(viewContainer, "scaleY", 0f, scale);
//        oa2.setDuration(duration);
//        AnimatorSet animatorSet = new AnimatorSet();
//        animatorSet.playTogether(oa_alpha, oa, oa2);
//        animatorSet.start();
//    }
//
//    public static void startObjAnimation(View viewContainer, String propertyName, int duration, float... values) {
//        ObjectAnimator oa_alpha = ObjectAnimator.ofFloat(viewContainer, propertyName, values);
//        oa_alpha.setDuration(duration);
//        oa_alpha.start();
//    }
//}
