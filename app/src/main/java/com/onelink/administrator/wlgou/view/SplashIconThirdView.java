package com.onelink.administrator.wlgou.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.onelink.administrator.wlgou.R;
import com.onelink.administrator.wlgou.base.BaseApplication;
import com.onelink.administrator.wlgou.base.BaseView;

/**
 * Created by Administrator on 2015/12/11.
 */
public class SplashIconThirdView extends BaseView {
    public SplashIconThirdView(Context context) {
        super(context);
    }

    public SplashIconThirdView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    public void initView() {
        View view = View.inflate(BaseApplication.context, R.layout.view_splash_icon_third,this);
    }
    @Override
    protected void initListener() {

    }


}
