package com.xcy.fzbcity.all.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.fragment.All_AttentionFragment;
import com.xcy.fzbcity.all.fragment.All_JourneyFragment;
import com.xcy.fzbcity.all.modle.SpellingDataBean;
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

public class SpellingMassActivity extends AllActivity {

    RelativeLayout imageView;


    RelativeLayout item_task_back;
    ImageView item_task_icon;
    TextView item_task_routeName;
    TextView item_task_islandTime;
    TextView item_task_tv1;
    TextView item_task_tv2;
    private SpellingDataBean.DataBean data;
    private List<SpellingDataBean.DataBean.PlanningBean> planning;
    private List<Fragment> mFragments;
    String[] mTitles = new String[]{
            "行程", "注意事项"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spelling_mass);
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

        imageView = findViewById(R.id.task_back);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        item_task_back = findViewById(R.id.item_back);
        item_task_icon = findViewById(R.id.item_icon);
        item_task_routeName = findViewById(R.id.item_routeName);
        item_task_islandTime = findViewById(R.id.item_islandTime);
        item_task_tv1 = findViewById(R.id.item_tv1);
        item_task_tv2 = findViewById(R.id.item_tv2);
        initData();

    }

    @SuppressLint("WrongConstant")
    private void initData() {

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<SpellingDataBean> spellingDataBean = fzbInterface.getSpellingDataBean(FinalContents.getRouteTimeId());
        spellingDataBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SpellingDataBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SpellingDataBean spellingDataBean) {
                        data = spellingDataBean.getData();

                        Glide.with(SpellingMassActivity.this).load(FinalContents.getImageUrl() + data.getTravelWayImg()).into(item_task_icon);
                        Log.i("MyCL", "拼团测试：" + data.getIslandTime() + "-" + data.getEndTime());
                        item_task_islandTime.setText(data.getIslandTime() + "-" + data.getEndTime());
                        item_task_routeName.setText(data.getRoute().getRouteName());
                        item_task_tv1.setText("截至：" + data.getEndCloseTime() + "");
                        item_task_tv2.setText(data.getExpenses());
                        setupViewPager();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("打印拼团详情数据", "错误" + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void setupViewPager() {
        final ViewPager viewPager = (ViewPager) findViewById(R.id.spelling_mass_framelayout);
        setupViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.spelling_mass_tablayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        mFragments = new ArrayList<>();

        All_AttentionFragment all_attentionFragment = new All_AttentionFragment();
        All_JourneyFragment all_journeyFragment = new All_JourneyFragment();
        mFragments.add(all_journeyFragment);
        mFragments.add(all_attentionFragment);
        BaseFragmentAdapter adapter = new BaseFragmentAdapter(getSupportFragmentManager(), mFragments, mTitles);
        viewPager.setAdapter(adapter);
    }
}
