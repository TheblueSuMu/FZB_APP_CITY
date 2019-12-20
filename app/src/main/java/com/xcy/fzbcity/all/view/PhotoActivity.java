package com.xcy.fzbcity.all.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.utils.CommonUtil;
import com.xcy.fzbcity.all.utils.ToastUtil;

import java.util.ArrayList;

public class PhotoActivity extends AllActivity {

    RelativeLayout photo_img;
    TabLayout photo_tab_layout;
    ViewPager photo_view_pager;

    ArrayList<String> listTitle;
    //    String[] photoTitle = {"效果图", "实景图", "规划图", "配套图", "样板间", "楼书"};
    ArrayList<Fragment> listFragment;
//    Fragment[] photoFragment = {new EFragment()};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        init_No_Network();
    }

    private void init_No_Network(){
        boolean networkAvailable = CommonUtil.isNetworkAvailable(this);
        if (networkAvailable) {
            initView();
        } else {
            RelativeLayout all_no_network = findViewById(R.id.all_no_network);
            Button all_no_reload = findViewById(R.id.all_no_reload);

            all_no_network.setVisibility(View.VISIBLE);
            all_no_reload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                    startActivity(getIntent());
                }
            });
            ToastUtil.showToast(this, "当前无网络，请检查网络后再进行登录");
        }
    }

    private void initView() {

        StatusBar.makeStatusBarTransparent(this);

        photo_img = findViewById(R.id.photo_img);
        photo_tab_layout = findViewById(R.id.photo_tab_layout);
        photo_view_pager = findViewById(R.id.photo_view_pager);
//        TODO 切换tablayout标题文字
        listTitle = new ArrayList<>();
//        TODO 切换fragment布局
        listFragment = new ArrayList<>();

        photo_view_pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                return listFragment.get(position);
            }

            @Override
            public int getCount() {
                return listFragment.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return listTitle.get(position);
            }
        });

        photo_tab_layout.setupWithViewPager(photo_view_pager);
        photo_tab_layout.getTabAt(0).select();

        photo_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
