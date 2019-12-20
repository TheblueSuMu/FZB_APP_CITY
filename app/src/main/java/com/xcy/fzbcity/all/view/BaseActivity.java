package com.xcy.fzbcity.all.view;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

public class BaseActivity extends AllActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);      //  TODO    始终竖屏

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


    public void exit(){
        //将所有的Activity全部销毁
        finish();
    }
}