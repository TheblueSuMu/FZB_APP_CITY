package com.netease.nim.uikit.common.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.netease.nim.uikit.R;
import com.netease.nim.uikit.common.fragment.TFragment;
import com.netease.nim.uikit.common.util.log.LogUtil;
import com.netease.nim.uikit.common.util.sys.ReflectionUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class UI extends AppCompatActivity {

    public static boolean exit = false;
    private static final int REQUEST_EXTERNAL_STORAGE = 2;
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE",
            "android.permission.READ_CONTACTS",
            "android.permission.ACCESS_FINE_LOCATION",
            "android.permission.CAMERA"
    };


    private boolean destroyed = false;

    private static Handler handler;

    private Toolbar toolbar;

    @Override
    protected void onStart() {
        super.onStart();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LogUtil.ui("activity: " + getClass().getSimpleName() + " onCreate()");


        setContentView(R.layout.activity_all);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);      //  TODO    始终竖屏
        initView();

        if (!this.isTaskRoot()) {
            Intent mainIntent = getIntent();
            String action = mainIntent.getAction();
            if (mainIntent.hasCategory(Intent.CATEGORY_LAUNCHER) && Intent.ACTION_MAIN.equals(action)) {
                finish();
                return;
            }
        }
    }

    @Override
    public void onBackPressed() {
        invokeFragmentManagerNoteStateNotSaved();
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        LogUtil.ui("activity: " + getClass().getSimpleName() + " onDestroy()");
        destroyed = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onNavigateUpClicked();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

//    public void setToolBarNew(int toolBarId,int toolBartv, ToolBarOptions options) {
//        toolbar = findViewById(toolBarId);
//        TextView textView = findViewById(toolBartv);
//        if (options.titleId != 0) {
//            toolbar.setTitle(options.titleId);
//        }
//        if (!TextUtils.isEmpty(options.titleString)) {
//            toolbar.setTitle(options.titleString);
//            textView.setText(options.titleString);
//        }
//        if (options.logoId != 0) {
//            toolbar.setLogo(options.logoId);
//        }
//        setSupportActionBar(toolbar);
//
//        if (options.isNeedNavigate) {
//            toolbar.setNavigationIcon(options.navigateId);
//            toolbar.setContentInsetStartWithNavigation(0);
//            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    onNavigateUpClicked();
//                }
//            });
//        }
//    }

    public void setToolBar(int toolBarId, ToolBarOptions options) {
        toolbar = findViewById(toolBarId);
        if (options.titleId != 0) {
            toolbar.setTitle(options.titleId);
        }
        if (!TextUtils.isEmpty(options.titleString)) {
            Log.i("标题","options.titleString：" + options.titleString);
            toolbar.setTitle(options.titleString);
        }
        if (options.logoId != 0) {
            toolbar.setLogo(options.logoId);
        }
        setSupportActionBar(toolbar);

        if (options.isNeedNavigate) {
            toolbar.setNavigationIcon(options.navigateId);
            toolbar.setContentInsetStartWithNavigation(0);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onNavigateUpClicked();
                }
            });
        }
    }

    public void setToolBar(int toolbarId, int titleId, int logoId) {
        toolbar = findViewById(toolbarId);
        toolbar.setTitle(titleId);
        toolbar.setLogo(logoId);
        setSupportActionBar(toolbar);
    }

    public Toolbar getToolBar() {
        return toolbar;
    }

    public int getToolBarHeight() {
        if (toolbar != null) {
            return toolbar.getHeight();
        }

        return 0;
    }

    public void onNavigateUpClicked() {
        onBackPressed();
    }

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
        if (toolbar != null) {
            toolbar.setTitle(title);
        }
    }

    public void setSubTitle(String subTitle) {
        if (toolbar != null) {
            toolbar.setSubtitle(subTitle);
        }
    }

    protected final Handler getHandler() {
        if (handler == null) {
            handler = new Handler(getMainLooper());
        }
        return handler;
    }

    protected void showKeyboard(boolean isShow) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (isShow) {
            if (getCurrentFocus() == null) {
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
            } else {
                imm.showSoftInput(getCurrentFocus(), 0);
            }
        } else {
            if (getCurrentFocus() != null) {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    /**
     * 延时弹出键盘
     *
     * @param focus 键盘的焦点项
     */
    protected void showKeyboardDelayed(View focus) {
        final View viewToFocus = focus;
        if (focus != null) {
            focus.requestFocus();
        }

        getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (viewToFocus == null || viewToFocus.isFocused()) {
                    showKeyboard(true);
                }
            }
        }, 200);
    }


    public boolean isDestroyedCompatible() {
        if (Build.VERSION.SDK_INT >= 17) {
            return isDestroyedCompatible17();
        } else {
            return destroyed || super.isFinishing();
        }
    }

    @TargetApi(17)
    private boolean isDestroyedCompatible17() {
        return super.isDestroyed();
    }

    /**
     * fragment management
     */
    public TFragment addFragment(TFragment fragment) {
        List<TFragment> fragments = new ArrayList<>(1);
        fragments.add(fragment);

        List<TFragment> fragments2 = addFragments(fragments);
        return fragments2.get(0);
    }

    public List<TFragment> addFragments(List<TFragment> fragments) {
        List<TFragment> fragments2 = new ArrayList<>(fragments.size());

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();

        boolean commit = false;
        for (int i = 0; i < fragments.size(); i++) {
            // install
            TFragment fragment = fragments.get(i);
            int id = fragment.getContainerId();

            // exists
            TFragment fragment2 = (TFragment) fm.findFragmentById(id);

            if (fragment2 == null) {
                fragment2 = fragment;
                transaction.add(id, fragment);
                commit = true;
            }

            fragments2.add(i, fragment2);
        }

        if (commit) {
            try {
                transaction.commitAllowingStateLoss();
            } catch (Exception e) {

            }
        }

        return fragments2;
    }

    public TFragment switchContent(TFragment fragment) {
        return switchContent(fragment, false);
    }

    protected TFragment switchContent(TFragment fragment, boolean needAddToBackStack) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(fragment.getContainerId(), fragment);
        if (needAddToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        try {
            fragmentTransaction.commitAllowingStateLoss();
        } catch (Exception e) {

        }

        return fragment;
    }

    protected boolean displayHomeAsUpEnabled() {
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_MENU:
                return onMenuKeyDown();

            default:
                return super.onKeyDown(keyCode, event);
        }
    }

    protected boolean onMenuKeyDown() {
        return false;
    }

    private void invokeFragmentManagerNoteStateNotSaved() {
        FragmentManager fm = getSupportFragmentManager();
        ReflectionUtil.invokeMethod(fm, "noteStateNotSaved", null);
    }

    protected void switchFragmentContent(TFragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(fragment.getContainerId(), fragment);
        try {
            transaction.commitAllowingStateLoss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected boolean isCompatible(int apiLevel) {
        return Build.VERSION.SDK_INT >= apiLevel;
    }

    protected <T extends View> T findView(int resId) {
        return (T) (findViewById(resId));
    }



    private void initView() {
        Log.i("MyCL", "进入initView");
        if (Build.VERSION.SDK_INT >= 23) {
            //检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(UI.this, "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {// 没有写的权限，去申请写的权限，
                ActivityCompat.requestPermissions(UI.this, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
            }
        }

        if (Build.VERSION.SDK_INT >= 23) {
            //判断是否有权限
            int permission = ActivityCompat.checkSelfPermission(UI.this, "android.permission.READ_CONTACTS");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(UI.this, new String[]{Manifest.permission.READ_CONTACTS}, 1);
            } else {
                //已经开启权限
            }
            if (ContextCompat.checkSelfPermission(UI.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {//未开启定位权限
                //开启定位权限,200是标识码
                ActivityCompat.requestPermissions(UI.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 200);
            }
            //权限不够
            if (ContextCompat.checkSelfPermission(UI.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(UI.this, new String[]{Manifest.permission.CAMERA}, 3);
            } else {
                // 已经申请到相机权限

            }
        }

        PackageManager pkgManager= getPackageManager();

        boolean audioSatePermission =

                pkgManager.checkPermission(Manifest.permission.RECORD_AUDIO, getPackageName()) == PackageManager.PERMISSION_GRANTED;

        if(Build.VERSION.SDK_INT>=23&&!audioSatePermission) {
            requestPermission();
        }

    }

    private void requestPermission() {

//        ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.RECORD_AUDIO},
//
//                REQUEST_PERMISSION);


    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (UI.exit) {
            finish();
            Log.i("王", "123");
        } else {
            Log.i("王", "321");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults != null) {
            if (grantResults.length != 0) {
                switch (requestCode) {
                    case 200://刚才的识别码
                        try {
                            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {//用户同意权限,执行我们的操作
                            } else {//用户拒绝之后,当然我们也可以弹出一个窗口,直接跳转到系统设置页面
                                //                            ToastUtil.showLongToast(AllActivity.this, "未开启定位权限,请手动到设置去开启权限");
                                Log.i("权限获取", "未开启定位权限,请手动到设置去开启权限");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 3://刚才的识别码
                        try {
                            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {//用户同意权限,执行我们的操作
                            } else {//用户拒绝之后,当然我们也可以弹出一个窗口,直接跳转到系统设置页面
                                //                            ToastUtil.showLongToast(AllActivity.this, "未开启相机权限,请手动到设置去开启相机权限");
                                Log.i("权限获取", "未开启相机权限,请手动到设置去开启相机权限");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 2://刚才的识别码
                        try {
                            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {//用户同意权限,执行我们的操作
                            } else {//用户拒绝之后,当然我们也可以弹出一个窗口,直接跳转到系统设置页面
                                //                            ToastUtil.showLongToast(AllActivity.this, "未开启存储权限,请手动到设置去开启权限");
                                Log.i("权限获取", "未开启存储权限,请手动到设置去开启权限");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 1://刚才的识别码
                        try {
                            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {//用户同意权限,执行我们的操作
                            } else {//用户拒绝之后,当然我们也可以弹出一个窗口,直接跳转到系统设置页面
                                //                            ToastUtil.showLongToast(AllActivity.this, "未开启读取通讯录权限,请手动到设置去开启读取通讯录权限");
                                Log.i("权限获取", "未开启读取通讯录权限,请手动到设置去开启读取通讯录权限");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    default:
                        break;
                }
            }
        }
    }

    @Override
    public boolean moveTaskToBack(boolean nonRoot) {
        return super.moveTaskToBack(true);
    }

    /**
     * 隐藏键盘
     */
    protected void hideInput() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        View v = getWindow().peekDecorView();
        if (null != v) {
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

    public String getTime1(Date date) {//可根据需要自行截取数据显示
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        return format.format(date);
    }

    public String getTime2(Date date) {//可根据需要自行截取数据显示
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
        return format.format(date);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideKeyboard(v, ev)) {
                boolean res = hideKeyboard(v.getWindowToken());
                if (res) {
                    //隐藏了输入法，则不再分发事件
                    return true;
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时则不能隐藏
     *
     * @param v
     * @param event
     * @return
     */
    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0],
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击EditText的事件，忽略它。
                return false;
            } else {
                return true;
            }
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditText上，和用户用轨迹球选择其他的焦点
        return false;
    }

    /**
     * 获取InputMethodManager，隐藏软键盘
     *
     * @param token
     */
    private boolean hideKeyboard(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            return im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
        return false;
    }

}
