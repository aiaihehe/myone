package com.onelink.administrator.wlgou.fragment;

import android.view.View;

import com.onelink.administrator.wlgou.R;
import com.onelink.administrator.wlgou.base.BaseFragment;

import org.xutils.view.annotation.ContentView;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2015/12/10.
 */
@ContentView(R.layout.fragment_home_pager)
public class HomePagerFragment extends BaseFragment implements View.OnClickListener {

    @Override
    protected void init() {
//        EventBus.getDefault().register(this);
    }

    @Override
    protected void initListener() {
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {}

    private void goToLogin() {}

    //用户登录成功后，会发送用户信息过来，同时更新用户信息界面

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
