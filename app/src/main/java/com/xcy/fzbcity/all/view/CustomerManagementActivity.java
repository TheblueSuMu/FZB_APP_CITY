package com.xcy.fzbcity.all.view;

import android.os.Bundle;

import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.persente.StatusBar;

public class CustomerManagementActivity extends AllActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_management);
        StatusBar.makeStatusBarTransparent(this);

    }
}