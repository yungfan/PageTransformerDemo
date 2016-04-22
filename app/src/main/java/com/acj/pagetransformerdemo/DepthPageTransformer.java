package com.acj.pagetransformerdemo;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

/**
 * Created by yangfan on 2016/4/20.
 */
public class DepthPageTransformer implements ViewPager.PageTransformer {

    private static final float MIN_SCALE = 0.75f;

    /**
     * @param view     滑动中的那个view
     * @param position 指明了给定页面相对于屏幕中心的位置。
     *                 一个页面充满屏幕时，它的position值为0,向左滑动时，它的position值一直在增加，消失时为1,继续往左滑动还会增加；
     *                 而右边那个之前没显示的页面，悄悄相反，position从1变成0
     */
    public void transformPage(View view, float position) {


        Log.e("*************************", view + "-----" + position);

        int pageWidth = view.getWidth();

        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            view.setAlpha(0);

        } else if (position <= 0) { // [-1,0]
            // Use the default slide transition when moving to the left page
            view.setAlpha(1);
            view.setTranslationX(0);
            view.setScaleX(1);
            view.setScaleY(1);

        } else if (position <= 1) { // (0,1]
            // Fade the page out.
            view.setAlpha(1 - position);

            // Counteract the default slide transition
            view.setTranslationX(pageWidth * -position);

            // Scale the page down (between MIN_SCALE and 1)
            float scaleFactor = MIN_SCALE
                    + (1 - MIN_SCALE) * (1 - Math.abs(position));
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);

        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            view.setAlpha(0);
        }
    }
}