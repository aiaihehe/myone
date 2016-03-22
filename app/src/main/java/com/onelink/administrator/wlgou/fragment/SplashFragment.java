package com.onelink.administrator.wlgou.fragment;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.ViewPager;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;


import com.onelink.administrator.wlgou.R;
import com.onelink.administrator.wlgou.adapter.ViewPagerSplashAdapter;
import com.onelink.administrator.wlgou.base.BaseApplication;
import com.onelink.administrator.wlgou.base.BaseFragment;
import com.onelink.administrator.wlgou.base.BaseView;
import com.onelink.administrator.wlgou.util.DisplayUtils;
import com.onelink.administrator.wlgou.view.SplashDialogView;
import com.onelink.administrator.wlgou.view.SplashIconFirstView;
import com.onelink.administrator.wlgou.view.SplashIconFourthView;
import com.onelink.administrator.wlgou.view.SplashIconSecondView;
import com.onelink.administrator.wlgou.view.SplashIconThirdView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/12/10.
 *
 * 引导页，只是在第一安装的时候出现显示
 *
 */
@ContentView(R.layout.fragment_splash)
public class SplashFragment extends BaseFragment implements ViewPager.OnPageChangeListener {

    @ViewInject(R.id.viewpager_splash)
    private ViewPager viewPagerSplash;
    @ViewInject(R.id.tv)
    private TextView tv;

    private Class[] views = {SplashIconFirstView.class, SplashIconSecondView.class, SplashIconThirdView.class, SplashIconFourthView.class};
    private List<BaseView> baseViews ;
    //imageView集合
    @Override
    protected void init() {
        //初始化viewpager
        initViewPager();
        ViewPagerSplashAdapter adapter = new ViewPagerSplashAdapter(baseViews);
        viewPagerSplash.setAdapter(adapter);
    }

    private void initViewPager() {
        baseViews = new ArrayList<BaseView>();
        for(int i = 0 ; i < views.length ; i++){
            try {
                Constructor constructor = views[i].getConstructor(Context.class);
                BaseView baseView = (BaseView) constructor.newInstance(BaseApplication.context);
                baseViews.add(baseView);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void initListener() {
        viewPagerSplash.addOnPageChangeListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public boolean finish() {
        return true;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }
    PopupWindow window;
    @Override
    public void onPageSelected(int position) {
        if(position == views.length - 1){

           /* AlertDialog.Builder builder_radio = new AlertDialog.Builder(getActivity());
            View view = new SplashDialogView(BaseApplication.context);
            builder_radio.setView(view);
            alertDialog = builder_radio.create();
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.show();*/
            if(window == null){
                window = new PopupWindow(getContext());
                View view = new SplashDialogView(BaseApplication.context);
                window.setContentView(view);
                window.setWidth(DisplayUtils.dip2px(getContext(), 150));
                window.setHeight(DisplayUtils.dip2px(getContext(), 310));

                window.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                window.setFocusable(false);
//                window.setOutsideTouchable(false);
            }
            window.showAtLocation(tv, Gravity.CENTER, 0, 0);
        }
        else{
            if(window != null){
                Log.d("1000p","*******");
//                alertDialog.dismiss();
                window.dismiss();
            }
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(window != null){
            window.dismiss();
        }

    }
}
