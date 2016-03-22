package com.onelink.administrator.wlgou.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;


import com.onelink.administrator.wlgou.R;
import com.onelink.administrator.wlgou.base.BaseApplication;
import com.onelink.administrator.wlgou.base.BaseView;
import com.onelink.administrator.wlgou.event.StartFragmentEvent;
import com.onelink.administrator.wlgou.fragment.HomeTabsFragment;

import org.xutils.view.annotation.ViewInject;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2015/12/11.
 */
public class SplashDialogView extends BaseView implements View.OnClickListener {
    @ViewInject(R.id.splash_dialog_fifteen)
    private LinearLayout splashDialogFifteen;
    @ViewInject(R.id.splash_dialog_nineteen)
    private LinearLayout splashDialogNineteen;
    @ViewInject(R.id.splash_dialog_twenty_four)
    private LinearLayout splashDialogTwentyFour;
    @ViewInject(R.id.splash_dialog_twenty_nine)
    private LinearLayout splashDialogTwentyNine;
    @ViewInject(R.id.splash_dialog_thirty_six)
    private LinearLayout splashDialogThirtySix;

    public SplashDialogView(Context context) {
        super(context);
    }

    public SplashDialogView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    public void initView() {
        View view = View.inflate(BaseApplication.context, R.layout.view_splash_dialog,this);

    }
    @Override
    protected void initListener() {
        splashDialogFifteen.setOnClickListener(this);
        splashDialogNineteen.setOnClickListener(this);
        splashDialogTwentyFour.setOnClickListener(this);
        splashDialogTwentyNine.setOnClickListener(this);
        splashDialogThirtySix.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        HomeTabsFragment homeTabsFragment = new HomeTabsFragment();
        StartFragmentEvent event = new StartFragmentEvent(homeTabsFragment,null);
        EventBus.getDefault().post(event);
    }
}
