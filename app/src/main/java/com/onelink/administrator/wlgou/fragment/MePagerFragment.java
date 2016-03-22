package com.onelink.administrator.wlgou.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.onelink.administrator.wlgou.R;
import com.onelink.administrator.wlgou.base.BaseFragment;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2015/12/10.
 */
@ContentView(R.layout.fragment_me_pager)
public class MePagerFragment extends BaseFragment implements View.OnClickListener {

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
