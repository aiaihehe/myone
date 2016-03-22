package com.onelink.administrator.wlgou.base;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.xutils.x;

/**
 * Created by Administrator on 2015/11/28.
 */
public abstract class BaseFragment extends Fragment {

    private int fId = 0 ;
    private View view;
    public BaseFragment(){
        fId++;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //xutils 的注入，写在BaseFragment,以便其子类复用
        view = x.view().inject(this,inflater,container);
        init();
        initListener();
        onGetBundle(getArguments());
        return view;
    }
    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }
    protected abstract void init();
    //初始化监听事件
    protected abstract void initListener();

    //获得传过来的bundle数据
    public void onGetBundle(Bundle bundle){

    }
    //初始化数据
    protected abstract void initData();
    //获得Fragment标签，
    public String getMTag(){

        return BaseFragment.class.getName()+fId;
    }
    //获得一个view
    public View getMView(){
        return view;
    }
    //控制Fragment的生命周期
    public boolean finish(){
        return false;
    }
    //Fragment管理返回键
    public boolean onBack(){
        return false;
    }
}
