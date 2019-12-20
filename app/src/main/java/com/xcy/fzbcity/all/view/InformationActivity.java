package com.xcy.fzbcity.all.view;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.fragment.DynamicFragment;
import com.xcy.fzbcity.all.fragment.GoodNewsFragment;
import com.xcy.fzbcity.all.fragment.GuestRoomFragment;
import com.xcy.fzbcity.all.fragment.NoticeFragment;
import com.xcy.fzbcity.all.persente.SingleClick;
import com.xcy.fzbcity.all.persente.StatusBar;

public class InformationActivity extends AllActivity implements View.OnClickListener {

    ImageView information_return;
    LinearLayout information_ll_1;
    LinearLayout information_ll_2;
    LinearLayout information_ll_3;
    LinearLayout information_ll_4;
    LinearLayout information_ll_5;
    LinearLayout information_ll_6;
    LinearLayout information_ll_7;
    LinearLayout information_ll_8;

    FrameLayout information_fl;

    FragmentManager manager;
    FragmentTransaction transaction;
    DynamicFragment dynamicFragment = new DynamicFragment();
    NoticeFragment noticeFragment = new NoticeFragment();
    GuestRoomFragment guestRoomFragment = new GuestRoomFragment();
    GoodNewsFragment goodNewsFragment = new GoodNewsFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);


        initView();

    }

    private void initView() {
        StatusBar.makeStatusBarTransparent(this);
        information_fl = findViewById(R.id.information_fl);
        information_return = findViewById(R.id.information_return);

        information_ll_1 = findViewById(R.id.information_ll_1);
        information_ll_2 = findViewById(R.id.information_ll_2);
        information_ll_3 = findViewById(R.id.information_ll_3);
        information_ll_4 = findViewById(R.id.information_ll_4);
        information_ll_5 = findViewById(R.id.information_ll_5);
        information_ll_6 = findViewById(R.id.information_ll_6);
        information_ll_7 = findViewById(R.id.information_ll_7);
        information_ll_8 = findViewById(R.id.information_ll_8);

        information_return.setOnClickListener(this);
        information_ll_1.setOnClickListener(this);
        information_ll_2.setOnClickListener(this);
        information_ll_3.setOnClickListener(this);
        information_ll_4.setOnClickListener(this);

        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.replace(R.id.information_fl, dynamicFragment);
        transaction.commit();

    }

    @SingleClick(1000)
    @Override
    public void onClick(View view) {
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        switch (view.getId()) {
            case R.id.information_return:
                finish();
                break;
            case R.id.information_ll_1:
                information_ll_5.setVisibility(View.VISIBLE);
                information_ll_6.setVisibility(View.INVISIBLE);
                information_ll_7.setVisibility(View.INVISIBLE);
                information_ll_8.setVisibility(View.INVISIBLE);
                transaction.replace(R.id.information_fl, dynamicFragment);
                break;
            case R.id.information_ll_2:
                information_ll_5.setVisibility(View.INVISIBLE);
                information_ll_6.setVisibility(View.VISIBLE);
                information_ll_7.setVisibility(View.INVISIBLE);
                information_ll_8.setVisibility(View.INVISIBLE);
                transaction.replace(R.id.information_fl, noticeFragment);
                break;
            case R.id.information_ll_3:
                information_ll_5.setVisibility(View.INVISIBLE);
                information_ll_6.setVisibility(View.INVISIBLE);
                information_ll_7.setVisibility(View.VISIBLE);
                information_ll_8.setVisibility(View.INVISIBLE);
                transaction.replace(R.id.information_fl, guestRoomFragment);
                break;
            case R.id.information_ll_4:
                information_ll_5.setVisibility(View.INVISIBLE);
                information_ll_6.setVisibility(View.INVISIBLE);
                information_ll_7.setVisibility(View.INVISIBLE);
                information_ll_8.setVisibility(View.VISIBLE);
                transaction.replace(R.id.information_fl, goodNewsFragment);
                break;
        }
        transaction.commit();
    }
}
