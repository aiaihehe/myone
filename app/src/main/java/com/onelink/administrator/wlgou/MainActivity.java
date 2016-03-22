package com.onelink.administrator.wlgou;



        import android.net.Uri;
        import android.os.SystemClock;

        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.support.v4.app.FragmentManager;
        import android.support.v7.app.AppCompatActivity;
        import android.view.KeyEvent;
        import android.widget.Toast;

        import com.google.android.gms.appindexing.Action;
        import com.google.android.gms.appindexing.AppIndex;
        import com.google.android.gms.common.api.GoogleApiClient;
        import com.onelink.administrator.wlgou.base.BaseFragment;
        import com.onelink.administrator.wlgou.event.StartFragmentEvent;
        import com.onelink.administrator.wlgou.fragment.HomeTabsFragment;
        import com.onelink.administrator.wlgou.fragment.SplashFragment;
        import com.onelink.administrator.wlgou.util.AppUtils;

        import java.util.LinkedList;
        import org.xutils.x;

        import de.greenrobot.event.EventBus;

public class MainActivity extends AppCompatActivity {

    private LinkedList<String> fragmentTags = new LinkedList<String>();

    private long lastClickTime;
    private long EXIT_GAP = 2000;
    private FragmentManager fm;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        x.view().inject(this);
        fm = getSupportFragmentManager();
        //by hou 注册EventBus
//        EventBus.getDefault().register(this);
        BaseFragment baseFragment;

        if (AppUtils.isFirstVisit()) {
            baseFragment = new SplashFragment();

            //设置第一登录
            AppUtils.setIsFirstVisit();
        } else {
            baseFragment = new HomeTabsFragment();
        }

        String tag = baseFragment.getMTag();
        fm.beginTransaction().add(R.id.main_layout, baseFragment, tag).addToBackStack(tag).commit();
        fragmentTags.add(tag);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {

            if (!backCurrentFragment()) {
                goBack();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    //fragment控制返回键
    public boolean backCurrentFragment() {
        BaseFragment baseFragment = (BaseFragment) getCurrentFragment();
        if (baseFragment != null) {
            return baseFragment.onBack();
        }
        return false;
    }

    private void goBack() {
        //获取返回栈中的总数
        int count = fm.getBackStackEntryCount();
        if (count == 1) {
            if (SystemClock.uptimeMillis() - lastClickTime > EXIT_GAP) {
                Toast.makeText(AppUtils.getContext(), "再点一次推出", Toast.LENGTH_LONG).show();
                lastClickTime = SystemClock.uptimeMillis();
            } else {
                AppUtils.setIsLogin(false);//退出后设置为不登录
                AppUtils.setUserId(-1);//退出后设置用户的id为“-1”说明用户已退出
                MainActivity.this.finish();
            }
        } else {
            if (fragmentTags.size() > 0) {
                fragmentTags.pollLast();
            }
            fm.popBackStack();
        }

    }

    private Fragment getCurrentFragment() {
        return fm.getBackStackEntryCount() > 0 ? fm.findFragmentByTag(fragmentTags.peekLast()) : null;
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.onelink.administrator.wlgou/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.onelink.administrator.wlgou/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }


//    public void onEventMainThread(StartFragmentEvent event) {
//        BaseFragment fragment = event.baseFragment;
//        Bundle bundle = event.bundle;
//        startFragment(fragment, bundle);
//    }


//    /**
//     * 监听返回键事件
//     *
//     * @param event
//     */
//    public void onEventMainThread(GoBackEvent event) {
//        goBack();
//    }
//
//    //   by hou  把starFragment改为startFragment
//    public void startFragment(BaseFragment fragment, Bundle bundle) {
//        if (fragment == null) {
//            throw new IllegalArgumentException("fragment is null");
//        }
//        FragmentTransaction ft = fm.beginTransaction();
//        BaseFragment currentFragment = (BaseFragment) getCurrentFragment();
//        //如果此Fragment已经添加到事物中，则不需要再添加
//        if (currentFragment != null) {
//            if (currentFragment.finish()) {
//                fragmentTags.pollLast();
//                fm.popBackStack();
//                //在获取一次当前的Fragment,因为此当前角色如果弹出去，将发生角色改变，它的前一个变为当前角色
//                currentFragment = (BaseFragment) getCurrentFragment();
//                if (currentFragment != null) {
//                    ft.hide(currentFragment);
//                }
//            } else {
//                ft.hide(currentFragment);
//            }
//        }
//        if (bundle != null) {
//            fragment.setArguments(bundle);
//        }
//        String tag = fragment.getMTag();
//        ft.add(R.id.main_layout, fragment, tag);
//        fragmentTags.add(tag);
//        ft.addToBackStack(tag);
//        ft.commit();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        EventBus.getDefault().unregister(this);
//    }
//
//    //在登录成功后，调用此方法，更新之前没有登录后的信息
//    public void onEventMainThread(LoginEvent event) {
//        Bundle bundle = event.bundle;
//        login(bundle);
//    }
//
//    //登录成功
//    public void login(Bundle bundle) {
//        if (bundle != null) {
//            //弹栈，---销毁登录界面，显示用户登录的界面
//            fragmentTags.pollLast();
//            fm.popBackStack();
//            SendUserMessageEvent event = new SendUserMessageEvent(bundle);
//            EventBus.getDefault().post(event);
//        }
//    }
//
//    //by hou  订阅者，用EventBus 来导航，其他的fragment中含发布者-->二者结合实现fragment的跳转
//    public void onEventMainThread(ToDetailEvent event) {
//        BaseFragment fragment = event.fragment;
//        Bundle bundle = event.bundle;
//        startFragment(fragment, bundle);
//    }
//
//    //订阅修改用户信息事件
//    public void onEventMainThread(EditUserInformationEvent event) {
//        Bundle bundle = event.bundle;
//        if (bundle != null) {
//            //弹栈，---销毁登录界面，显示用户修改后界面
//            fragmentTags.pollLast();
//            fm.popBackStack();
//            UserInformationEvent userInformationEvent = new UserInformationEvent(bundle);
//            EventBus.getDefault().post(userInformationEvent);
//        }
//    }
//    //接收对话框显示 事件
//    public void onEventMainThread(GroupShowDialogEvent event) {
//        String imagIcon = event.getImagIcon();
//        List<GroupCategoryBean3.DataEntity.ItemsEntity.secondMenuEntity> list = event.getList();
//        if (imagIcon != null && list != null) {
//            showDialog(imagIcon, list); //显示对话框
//        }
//    }
//    //显示对话框
//    public void showDialog(String imagIcon, List<GroupCategoryBean3.DataEntity.ItemsEntity.secondMenuEntity> list) {
//        //找到对话框的布局
//        View v = LayoutInflater.from(AppUtils.getContext()).inflate(R.layout.view_group_pop, null);
//        //新建对话框buidler
//        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//        //给对话框设置布局
//        builder.setView(v);
//        //找到对话框中的imageview和gridview
//        ImageView imag = (ImageView) v.findViewById(R.id.imag_group_03);
//        GridView gv = (GridView) v.findViewById(R.id.gridView_group_03);
//        //话框中的imageview设置图片
//        x.image().bind(imag, imagIcon);
//        //话框中的gridview设置适配器
//        GroupGVAdapter adapter3 = new GroupGVAdapter(list);
//        gv.setAdapter(adapter3);
//        gv.setOnItemClickListener(new ItemClickListener());
//        //新建对话框
//        dialog = builder.create();
//        //显示对话框
//        dialog.show();
//    }
//    //对话框
//    private AlertDialog dialog;
//    //GroupPagerFragment 对话框中 gridview的监听者  by  yang
//    class ItemClickListener implements AdapterView.OnItemClickListener {
//
//        @Override
//        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//            GroupCategoryBean3.DataEntity.ItemsEntity.secondMenuEntity secondMenuEntity = (GroupCategoryBean3.DataEntity.ItemsEntity.secondMenuEntity) parent.getItemAtPosition(position);
//            int classifyId = secondMenuEntity.getClassify_id();
//            String name = secondMenuEntity.getClassify_name();
//            gotoDetailFragmentClick(view, classifyId, name);
//            dialog.dismiss();
//        }
//
//        //eventbus  点击事件 跳转至DetailFragment， 传值 path  name
//        public void gotoDetailFragmentClick(View view, int classifyId, String name) {
//            GroupDetailFragment fragment = new GroupDetailFragment();
//            Bundle bundle = new Bundle();
//            bundle.putInt("path", classifyId);
//            bundle.putString("name", name);
//            bundle.putBoolean("flag",false);
//            fragment.setArguments(bundle);
//            ToDetailEvent event = new ToDetailEvent(fragment);
//            EventBus.getDefault().post(event);
//        }
//    }
}
