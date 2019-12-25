package com.xcy.fzbcity.project_side.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.CityContents;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.modle.SideHomeBean;
import com.xcy.fzbcity.all.persente.MyLinearLayoutManager;
import com.xcy.fzbcity.all.persente.SingleClick;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.CommonUtil;
import com.xcy.fzbcity.all.utils.ToastUtil;
import com.xcy.fzbcity.all.view.AllActivity;
import com.xcy.fzbcity.project_side.adapter.HomeRecyclerAdapter;

import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyProjectActivity extends AllActivity implements View.OnClickListener {

    PtrClassicFrameLayout ptrClassicFrameLayout;

    RelativeLayout my_project_img;
    LinearLayout my_project_l1;
    LinearLayout my_project_l2;
    LinearLayout my_project_ll1;
    LinearLayout my_project_ll2;
    LinearLayout my_project_l3;
    LinearLayout my_project_ll3;

    RecyclerView my_project_recyler;
    String projectType = "1";
    private ImageView all_no_information;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_side_activity_my_project);
        StatusBar.makeStatusBarTransparent(this);
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
        all_no_information = findViewById(R.id.all_no_information);
        my_project_img = findViewById(R.id.my_project_img);
        my_project_l1 = findViewById(R.id.my_project_l1);
        my_project_l2 = findViewById(R.id.my_project_l2);
        my_project_ll1 = findViewById(R.id.my_project_ll1);
        my_project_ll2 = findViewById(R.id.my_project_ll2);
        my_project_l3 = findViewById(R.id.my_project_l3);
        my_project_ll3 = findViewById(R.id.my_project_ll3);
        my_project_recyler = findViewById(R.id.my_project_recyler);

        my_project_img.setOnClickListener(this);
        my_project_l1.setOnClickListener(this);
        my_project_l2.setOnClickListener(this);
        my_project_l3.setOnClickListener(this);

        ptrClassicFrameLayout = (PtrClassicFrameLayout) findViewById(R.id.store_house_ptr_frame_10);
        ptrClassicFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ptrClassicFrameLayout.refreshComplete();
                        ptrClassicFrameLayout.setLastUpdateTimeKey("2017-2-10");
//                        if(my_project_ll1.getVisibility() == View.VISIBLE){
//                            projectType = "2";
//                            initData();
//                        }else if(my_project_ll2.getVisibility() == View.VISIBLE){
                            projectType = "1";
                            initData();
//                        }
                    }
                }, 1000);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });
        initData();


    }

    @SingleClick(1000)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.my_project_img:
                finish();
                break;
            case R.id.my_project_l1:
                my_project_ll1.setVisibility(View.VISIBLE);
                my_project_ll2.setVisibility(View.GONE);
                my_project_ll3.setVisibility(View.GONE);
                //TODO 海外
                projectType = "2";
                initData();
                break;
            case R.id.my_project_l2:
                my_project_ll1.setVisibility(View.GONE);
                my_project_ll2.setVisibility(View.VISIBLE);
                my_project_ll3.setVisibility(View.GONE);
                //TODO 旅居
                projectType = "3";
                initData();
                break;
            case R.id.my_project_l3:
                my_project_ll1.setVisibility(View.GONE);
                my_project_ll2.setVisibility(View.GONE);
                my_project_ll3.setVisibility(View.VISIBLE);
                //TODO 城市
                projectType = "1";
                initData();
                break;
        }
    }


    @SuppressLint("WrongConstant")
    private void initData() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<SideHomeBean> userMessage = fzbInterface.getHomeBeanList(FinalContents.getUserID(),projectType,"1000");
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SideHomeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(SideHomeBean homeListBean) {
                        if (homeListBean.getCode().equals("1") || homeListBean.getData().getRows().size() != 0) {
                            all_no_information.setVisibility(View.GONE);
                            SideHomeBean.DataBean homeListBeanData = homeListBean.getData();
                            final List<SideHomeBean.DataBean.RowsBean> homeListBeanDataRows = homeListBeanData.getRows();
                            if (homeListBeanDataRows.size() != 0) {
                                all_no_information.setVisibility(View.GONE);
                                //在此处修改布局排列方向
                                my_project_recyler.setVisibility(View.VISIBLE);
                                MyLinearLayoutManager layoutManager = new MyLinearLayoutManager(MyProjectActivity.this);
                                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                                my_project_recyler.setLayoutManager(layoutManager);
                                final HomeRecyclerAdapter recyclerAdapter = new HomeRecyclerAdapter(homeListBeanDataRows);
                                my_project_recyler.setAdapter(recyclerAdapter);
                                recyclerAdapter.setOnItemClickListener(new HomeRecyclerAdapter.OnItemClickLisenter() {
                                    @Override
                                    public void onItemClick(int postion) {
                                        if(CityContents.getOneKey().equals("一键成交")){
                                            FinalContents.setProjectID(homeListBeanDataRows.get(postion).getProjectId());
                                            FinalContents.setProjectName(homeListBeanDataRows.get(postion).getProjectName());
                                            finish();
                                        }else {
                                            if (FinalContents.getMessageIssueNum().equals("1")) {
                                                FinalContents.setProjectID(homeListBeanDataRows.get(postion).getProjectId());
                                                FinalContents.setProjectName(homeListBeanDataRows.get(postion).getProjectName());
                                                finish();
                                            }
                                        }

                                    }
                                });
                                recyclerAdapter.notifyDataSetChanged();
                            }else {
                                all_no_information.setVisibility(View.VISIBLE);
                                my_project_recyler.setVisibility(View.GONE);
                            }

                        }else {
                            my_project_recyler.setVisibility(View.GONE);
                            all_no_information.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        my_project_recyler.setVisibility(View.GONE);
                        all_no_information.setVisibility(View.VISIBLE);
                        Log.i("列表数据获取错误","错误"+e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
