package com.onelink.administrator.wlgou.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;


import com.onelink.administrator.wlgou.base.BaseView;

import java.util.List;

/**
 * Created by Administrator on 2015/12/10.
 */
public class ViewPagerSplashAdapter extends PagerAdapter {
    //viewpager所滑动的只是Imageview
    private List<BaseView> imageViews;

    public ViewPagerSplashAdapter (List<BaseView> imageViews){
        this.imageViews = imageViews;
    }
    @Override
    public int getCount() {
        return imageViews.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(imageViews.get(position));
        return imageViews.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(imageViews.get(position));
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
