package com.acj.pagetransformerdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private ViewPager mViewPager;

    private int[] images = {R.drawable.one, R.drawable.two, R.drawable.three};

    private ArrayList<ImageView> imageViews = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

    }

    /**
     * 初始化数据
     */
    private void init() {
        // 定义一个布局并设置参数
        LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);

        // 初始化引导图片列表
        for (int i = 0; i < images.length; i++) {
            ImageView iv = new ImageView(this);
            iv.setLayoutParams(mParams);
            // 防止图片不能填满屏幕
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            // 加载图片资源
            iv.setImageResource(images[i]);
            imageViews.add(iv);
        }

        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mViewPager.setAdapter(new MyViewPagerAdapter());

        // 设置切换动画

        mViewPager.setPageTransformer(true, new MyPageTransformer());
    }


    class MyViewPagerAdapter extends PagerAdapter {

        // viewpager中的要显示的View的总数量
        @Override
        public int getCount() {
            return imageViews.size();
        }

        // 滑动切换的时候销毁当前的View
        @Override
        public void destroyItem(ViewGroup container, int position,
                                Object object) {
            container.removeView(imageViews.get(position));
        }

        // 每次滑动的时候生成的View
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(imageViews.get(position));
            return imageViews.get(position);
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }
    }

}
