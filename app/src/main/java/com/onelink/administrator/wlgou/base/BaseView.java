package com.onelink.administrator.wlgou.base;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import org.xutils.x;

/**
 * Created by Administrator on 2015/11/28.
 */
public abstract class BaseView extends RelativeLayout {

    public BaseView(Context context) {
        super(context);
        initView();//先初始化view
        //在注入view
        x.view().inject(this);
        //初始化动画
        initAnim();
        initListener();
    }

    public void initAnim() {

    }

    protected abstract void initListener();

    public abstract void initView();

    public BaseView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();//先初始化view
        //在注入view
        x.view().inject(this);
        //初始化动画
        initAnim();
        initListener();
    }

    public void startAnim() {

    }
}
