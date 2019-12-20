package com.xcy.fzbcity.all.view;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.fragment.PhotoFragment;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.utils.CommonUtil;
import com.xcy.fzbcity.all.utils.ToastUtil;
import com.xcy.fzbcity.shopping_guide.adapter.BaseFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

public class BigPhotoActivity extends AllActivity {

    List<String> listImage;
    TextView big_tv;
    int j = 0;
    int num = 0;
    private List<Fragment> mFragments;
    private String[] mTitles = new String[]{};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_photo);
        FinalContents.setHidden(false);
        init_No_Network();
    }

    private void init_No_Network(){
        boolean networkAvailable = CommonUtil.isNetworkAvailable(this);
        if (networkAvailable) {
            setupViewPager();
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
            ToastUtil.showToast(this,"当前无网络，请检查网络后再进行登录");
        }
    }


    private void setupViewPager() {
        StatusBar.makeStatusBarTransparent(this);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        listImage = new ArrayList<>();

        big_tv = findViewById(R.id.big_tv);

        String bigPhotoimg = getIntent().getStringExtra("bigPhotoimg");


        String[] a  = bigPhotoimg.split("[|]");
        for (int i = 0; i < a.length; i++){
            listImage.add(FinalContents.getImageUrl()+a[i]);
        }

        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        int index = getIntent().getIntExtra("index", 0);
        mFragments = new ArrayList<>();
        for (int i = 0; i < listImage.size();i++){
            mFragments.add(PhotoFragment.newInstance(listImage.get(i)));
        }
        big_tv.setText( index+1 + "/" + mFragments.size());
        BaseFragmentAdapter adapter = new BaseFragmentAdapter(getSupportFragmentManager(), mFragments, mTitles);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(index);
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                int currentPosition = position;
                big_tv.setText(currentPosition + 1 + "/" + mFragments.size());
            }
        });
    }

}