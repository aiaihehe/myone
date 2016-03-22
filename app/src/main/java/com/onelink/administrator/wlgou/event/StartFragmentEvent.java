package com.onelink.administrator.wlgou.event;

import android.os.Bundle;

import com.onelink.administrator.wlgou.base.BaseFragment;


/**
 * Created by Administrator on 2015/12/11.
 */
public class StartFragmentEvent {
    //此类是，在点应用下面的标题的时候的事件，如点“发现”
    public BaseFragment baseFragment;
    public Bundle bundle;

    public StartFragmentEvent(BaseFragment baseFragment, Bundle bundle) {
        this.baseFragment = baseFragment;
        this.bundle = bundle;
    }
}
