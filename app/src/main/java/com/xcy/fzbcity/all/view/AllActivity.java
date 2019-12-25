package com.xcy.fzbcity.all.view;

import android.Manifest;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.persente.StatusBar;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AllActivity extends AppCompatActivity {

    public static boolean exit = false;
    private static final int REQUEST_EXTERNAL_STORAGE = 2;
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE",
            "android.permission.READ_CONTACTS",
            "android.permission.ACCESS_FINE_LOCATION",
            "android.permission.CAMERA"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);      //  TODO    始终竖屏
        initView();


    }

    private void initView() {
        StatusBar.makeStatusBarTransparent(this);



        Log.i("MyCL","进入initView");
        if (Build.VERSION.SDK_INT >= 23) {
            //检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(AllActivity.this, "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {// 没有写的权限，去申请写的权限，
                ActivityCompat.requestPermissions(AllActivity.this, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
            }
        }

        if (Build.VERSION.SDK_INT >= 23) {
            //判断是否有权限
            int permission = ActivityCompat.checkSelfPermission(AllActivity.this, "android.permission.READ_CONTACTS");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(AllActivity.this, new String[]{Manifest.permission.READ_CONTACTS}, 1);
            } else {
                //已经开启权限
            }
            if (ContextCompat.checkSelfPermission(AllActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {//未开启定位权限
                //开启定位权限,200是标识码
                ActivityCompat.requestPermissions(AllActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 200);
            }
            //权限不够
            if (ContextCompat.checkSelfPermission(AllActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(AllActivity.this, new String[]{Manifest.permission.CAMERA}, 3);
            } else {
                // 已经申请到相机权限

            }
        }


    }

    @Override
    protected void onRestart() {
        super.onRestart();

        if (AllActivity.exit) {
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
                                Log.i("权限获取","未开启定位权限,请手动到设置去开启权限");
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
                                Log.i("权限获取","未开启相机权限,请手动到设置去开启相机权限");
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
                                Log.i("权限获取","未开启存储权限,请手动到设置去开启权限");
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
                                Log.i("权限获取","未开启读取通讯录权限,请手动到设置去开启读取通讯录权限");
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
                boolean res=hideKeyboard(v.getWindowToken());
                if(res){
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
