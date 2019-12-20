package com.xcy.fzbcity.shopping_guide.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.modle.TaskDetailsBean;
import com.xcy.fzbcity.all.persente.AppBarStateChangeListener;
import com.xcy.fzbcity.all.persente.MyLinearLayoutManager;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.CommonUtil;
import com.xcy.fzbcity.all.utils.ToastUtil;
import com.xcy.fzbcity.all.view.AllActivity;
import com.xcy.fzbcity.shopping_guide.adapter.BaseFragmentAdapter;
import com.xcy.fzbcity.shopping_guide.adapter.TaskDetailsAdapter;
import com.xcy.fzbcity.shopping_guide.fragment.AttentionFragment;
import com.xcy.fzbcity.shopping_guide.fragment.ClientFragment;
import com.xcy.fzbcity.shopping_guide.fragment.JourneyFragment;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TaskDetailsActivity extends AllActivity {

    ViewPager mViewPager;
    List<Fragment> mFragments;
    Toolbar mToolbar;
    RelativeLayout task_details_tool_layout;

    TextView task_details_tool_title;

    TextView task_details_constraintlayout_title;
    TextView task_details_constraintlayout_status;
    TextView task_details_constraintlayout_time;
    TextView task_details_constraintlayout_client;
    TextView task_details_constraintlayout_project_name;
    ImageView task_details_constraintlayout_img;
    LinearLayout task_details_constraintlayout_layout;

    TextView task_details_title;
    TextView task_details_status;
    TextView task_details_time;
    ImageView task_details_img;
    RecyclerView task_details_constraintlayout_listview;

    private AppBarLayout mAppBarLayout;
    private View mTitle;
    private static final String TAG = "ViewPagerParallax2";

    String[] mTitles = new String[]{
            "行程", "注意事项", "客户"
    };
    private LinearLayout appbar_layout;
    private ClientFragment clientFragment;
    private AttentionFragment attentionFragment;
    private JourneyFragment journeyFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_guide_activity_task_details);
        init_No_Network();
    }

    private void init_No_Network(){
        boolean networkAvailable = CommonUtil.isNetworkAvailable(this);
        if (networkAvailable) {
            initView();
            init();
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


    private void initView(){

        StatusBar.makeStatusBarTransparent(this);

        mAppBarLayout = (AppBarLayout) findViewById(R.id.mainappbar);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);

        appbar_layout = findViewById(R.id.appbar_layout);
        mTitle =  findViewById(R.id.rl_title);
        task_details_tool_layout = findViewById(R.id.task_details_tool_layout);
        task_details_tool_title = findViewById(R.id.task_details_tool_title);

        task_details_constraintlayout_title = findViewById(R.id.task_details_constraintlayout_title);
        task_details_constraintlayout_status = findViewById(R.id.task_details_constraintlayout_status);
        task_details_constraintlayout_time = findViewById(R.id.task_details_constraintlayout_time);
        task_details_constraintlayout_client = findViewById(R.id.task_details_constraintlayout_client);
        task_details_constraintlayout_project_name = findViewById(R.id.task_details_constraintlayout_project_name);
        task_details_constraintlayout_img = findViewById(R.id.task_details_constraintlayout_img);
        task_details_constraintlayout_layout = findViewById(R.id.task_details_constraintlayout_layout);
        task_details_constraintlayout_listview = findViewById(R.id.task_details_constraintlayout_listview);

        task_details_title = findViewById(R.id.task_details_title);
        task_details_status = findViewById(R.id.task_details_status);
        task_details_time = findViewById(R.id.task_details_time);
        task_details_img = findViewById(R.id.task_details_img);

        if (FinalContents.getDaoGou().equals("1")) {
            task_details_tool_title.setText("当前任务");
            task_details_status.setVisibility(View.GONE);
        } else if (FinalContents.getDaoGou().equals("60")) {
            task_details_tool_title.setText("历史任务");
            task_details_status.setVisibility(View.VISIBLE);
        }

        task_details_tool_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        initData();
    }


    private void initData(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<TaskDetailsBean> userMessage = fzbInterface.getRoutetimeDetails(FinalContents.getUserID(),FinalContents.getRouteTimeId());
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TaskDetailsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(TaskDetailsBean taskDetailsBean) {
                        task_details_constraintlayout_title.setText(taskDetailsBean.getData().getRouteTimeInfo().getRoute().getRouteName());
                        task_details_title.setText(taskDetailsBean.getData().getRouteTimeInfo().getRoute().getRouteName());
                        Glide.with(TaskDetailsActivity.this).load(FinalContents.getImageUrl() + taskDetailsBean.getData().getRouteTimeInfo().getTravelWayImg()).into(task_details_constraintlayout_img);
                        Glide.with(TaskDetailsActivity.this).load(FinalContents.getImageUrl() + taskDetailsBean.getData().getRouteTimeInfo().getTravelWayImg()).into(task_details_img);

                        task_details_constraintlayout_time.setText(taskDetailsBean.getData().getRouteTimeInfo().getIslandTime()+"-"+taskDetailsBean.getData().getRouteTimeInfo().getEndTime());
                        task_details_time.setText(taskDetailsBean.getData().getRouteTimeInfo().getIslandTime()+"-"+taskDetailsBean.getData().getRouteTimeInfo().getEndTime());

                        task_details_constraintlayout_client.setText(""+ taskDetailsBean.getData().getRouteTimeInfo().getEnrollNumber());

                        task_details_constraintlayout_status.setText(taskDetailsBean.getData().getRouteTimeInfo().getTaskStatusName());

                        if (taskDetailsBean.getData().getProjectSpecialInfo().size() == 0) {
                            task_details_constraintlayout_layout.setVisibility(View.GONE);
                        }else {
                            task_details_constraintlayout_layout.setVisibility(View.VISIBLE);
                            task_details_constraintlayout_project_name.setText(taskDetailsBean.getData().getProjectSpecialInfo().get(0).getName());


                            MyLinearLayoutManager layoutManager = new MyLinearLayoutManager(TaskDetailsActivity.this);
                            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                            task_details_constraintlayout_listview.setLayoutManager(layoutManager);
                            TaskDetailsAdapter recyclerAdapter = new TaskDetailsAdapter(taskDetailsBean.getData().getProjectSpecialInfo().get(0).getBean());
                            task_details_constraintlayout_listview.setAdapter(recyclerAdapter);
                            recyclerAdapter.notifyDataSetChanged();
                        }

                        attentionFragment = new AttentionFragment(taskDetailsBean);
                        journeyFragment = new JourneyFragment(taskDetailsBean);

                        setupViewPager();

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("列表数据获取错误","错误"+e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void init(){
        mTitle.setVisibility(View.GONE);
        appbar_layout.setVisibility(View.VISIBLE);

        AppBarStateChangeListener listener = new AppBarStateChangeListener();
        listener.setOnStateChangedListener(new AppBarStateChangeListener.OnStateChangedListener() {
            @Override
            public void onExpanded() {
                Log.i(TAG, "onExpanded: =");
                mTitle.setVisibility(View.GONE);
            }

            @Override
            public void onCollapsed() {
                mTitle.setVisibility(View.VISIBLE);
            }

            @Override
            public void onInternediateFromExpand() {
                mTitle.setVisibility(View.GONE);
            }

            @Override
            public void onInternediateFromCollapsed() {
                mTitle.setVisibility(View.GONE);
            }

            @Override
            public void onInternediate() {

            }
        });
        mAppBarLayout.addOnOffsetChangedListener(listener);
    }


    private void setupViewPager() {
        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        mFragments = new ArrayList<>();
        clientFragment = new ClientFragment();
        mFragments.add(journeyFragment);
        mFragments.add(attentionFragment);
        mFragments.add(clientFragment);
        BaseFragmentAdapter adapter = new BaseFragmentAdapter(getSupportFragmentManager(), mFragments, mTitles);
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }
}
