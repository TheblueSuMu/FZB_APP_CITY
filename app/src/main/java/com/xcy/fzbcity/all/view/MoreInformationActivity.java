package com.xcy.fzbcity.all.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.fragment.MoreInformationFragment;
import com.xcy.fzbcity.all.fragment.MoreProjectFragment;
import com.xcy.fzbcity.all.fragment.MoreTypeFragment;
import com.xcy.fzbcity.all.modle.MoreBean;
import com.xcy.fzbcity.all.persente.SingleClick;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.CommonUtil;
import com.xcy.fzbcity.all.utils.ToastUtil;
import com.xcy.fzbcity.shopping_guide.adapter.BaseFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoreInformationActivity extends AllActivity implements View.OnClickListener {
    private LinearLayout more_img;

    MoreInformationFragment moreInformationFragment = new MoreInformationFragment();
    MoreTypeFragment moreTypeFragment= new MoreTypeFragment();
    MoreProjectFragment moreProjectFragment = new MoreProjectFragment();
    private Button more_report;
    private Button more_call;
    private List<Fragment> mFragments;
    private String[] mTitles = new String[]{
            "基本信息", "产品类型", "项目规划"
    };
    private RelativeLayout information_relative1;
    private RelativeLayout information_relative2;
    private TextView more_qt_call;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_information);
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
        more_img = findViewById(R.id.more_img);
        more_report = findViewById(R.id.more_report);
        more_call = findViewById(R.id.more_call);
        information_relative1 = findViewById(R.id.information_relative1);
        information_relative2 = findViewById(R.id.information_relative2);
        more_qt_call = findViewById(R.id.more_qt_call);



        if (!FinalContents.getCityID().equals(FinalContents.getOldCityId())) {
            information_relative1.setVisibility(View.GONE);
            information_relative2.setVisibility(View.VISIBLE);
        }else {
            information_relative1.setVisibility(View.VISIBLE);
            information_relative2.setVisibility(View.GONE);
            if (FinalContents.getIdentity().equals("4") || FinalContents.getIdentity().equals("5")|| FinalContents.getIdentity().equals("63")|| FinalContents.getIdentity().equals("7") || FinalContents.getIdentity().equals("8") || FinalContents.getIdentity().equals("9")) {
                Log.i("身份验证","是团助");
                information_relative1.setVisibility(View.GONE);
                information_relative2.setVisibility(View.VISIBLE);
            }else {
                Log.i("身份验证","不是团助");
                information_relative1.setVisibility(View.VISIBLE);
                information_relative2.setVisibility(View.GONE);
            }
        }

        initData();

        more_qt_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (FinalContents.getIPhone().equals("")) {
                    ToastUtil.showLongToast(MoreInformationActivity.this,"暂无专案");
                }else {
                    Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + FinalContents.getIPhone()));//跳转到拨号界面，同时传递电话号码
                    startActivity(dialIntent);
                }
                FinalContents.setIPhone("");
            }
        });

        more_img.setOnClickListener(this);

        more_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (FinalContents.getIPhone().equals("")) {
                    ToastUtil.showLongToast(MoreInformationActivity.this,"暂无专案");
                }else {
                    Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + FinalContents.getIPhone()));//跳转到拨号界面，同时传递电话号码
                    startActivity(dialIntent);
                }
                FinalContents.setIPhone("");
            }
        });

        more_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FinalContents.setChecked2(true);
                Intent intent = new Intent(MoreInformationActivity.this, ReportActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initData(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<MoreBean> clientFragmentBean = fzbInterface.getProjectMoreInfo(FinalContents.getProjectID());
        clientFragmentBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MoreBean>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MoreBean moreBean) {
                        List<MoreBean.DataBean> list = moreBean.getData();
                        if (list.size() == 0) {
                        }else {
                            moreInformationFragment = new MoreInformationFragment(list);
                            moreTypeFragment = new MoreTypeFragment(list);
                            moreProjectFragment = new MoreProjectFragment(list);
                            setupViewPager();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("更多信息", "错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @SingleClick(1000)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.more_img:
                finish();
                break;
        }
    }


    private void setupViewPager() {
        final ViewPager viewPager = (ViewPager) findViewById(R.id.more_viewpager);
        setupViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.more_tablayout);
        tabLayout.setSelectedTabIndicator(R.drawable.tablayout_select);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        mFragments = new ArrayList<>();
        mFragments.add(moreInformationFragment);
        mFragments.add(moreTypeFragment);
        mFragments.add(moreProjectFragment);
        BaseFragmentAdapter adapter = new BaseFragmentAdapter(getSupportFragmentManager(), mFragments, mTitles);
        viewPager.setAdapter(adapter);
    }
}
