package com.onelink.administrator.wlgou.fragment;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;


import com.onelink.administrator.wlgou.R;
import com.onelink.administrator.wlgou.base.BaseApplication;
import com.onelink.administrator.wlgou.base.BaseFragment;
import com.onelink.administrator.wlgou.util.AppUtils;
import com.onelink.administrator.wlgou.widget.QuickFragmentTabHost;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/22.
 */
@ContentView(R.layout.fragment_home_tab)
public class HomeTabsFragment extends BaseFragment {
    @ViewInject(android.R.id.tabhost)
    private QuickFragmentTabHost tabHost;

    private String[] titles = {"首页","购物车","我的"};
    private String[] tags = {"quality","explore","me"};
    private Class[] fragmentsClazz = {HomePagerFragment.class,ShoppingCartFragment.class,MePagerFragment.class};

    private int[] icons = {R.drawable.selector_tabs_quality,R.drawable.selector_tab_explore,R.drawable.selector_tab_me};
    private List<ViewHolder> viewHolders = new ArrayList<ViewHolder>();


    @Override
    protected void init() {
        tabHost.setup(BaseApplication.context, getChildFragmentManager(), R.id.realtabcontent);
        initTabs();
    }

    private void initTabs() {
        for(int i = 0 ; i < titles.length ; i++){
            View view = View.inflate(AppUtils.getContext(), R.layout.view_tab_icon, null);
            ViewHolder holder = new ViewHolder();

            holder.iconTabTv = (TextView) view.findViewById(R.id.icon_tab_tv);
            holder.iconTabTv.setText(titles[i]);
            holder.tilte = titles[i];
            holder.tag = tags[i];
            Drawable drawable = getResources().getDrawable(icons[i]);
            drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
            holder.iconTabTv.setCompoundDrawables(null, drawable, null, null);
            viewHolders.add(holder);
            tabHost.addTab(tabHost.newTabSpec(holder.tag).setIndicator(view),fragmentsClazz[i],null);
        }

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    class ViewHolder{
        TextView iconTabTv;
        String tilte;
        String tag;
    }
}
