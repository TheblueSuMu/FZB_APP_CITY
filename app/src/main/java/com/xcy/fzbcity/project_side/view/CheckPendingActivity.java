package com.xcy.fzbcity.project_side.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.adapter.ReviewTheSuccessPhoneAdapter;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.modle.CBean;
import com.xcy.fzbcity.all.modle.CheckBean;
import com.xcy.fzbcity.all.modle.ReadRecordBean;
import com.xcy.fzbcity.all.modle.ReportProcessDetailsBean;
import com.xcy.fzbcity.all.persente.SingleClick;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.CommonUtil;
import com.xcy.fzbcity.all.utils.ToastUtil;
import com.xcy.fzbcity.all.view.AllActivity;
import com.xcy.fzbcity.project_side.adapter.Project_Side_MakeABargainAdapter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CheckPendingActivity extends AllActivity implements View.OnClickListener {


    RelativeLayout check_pending_return;
    ImageView check_pending_img1;

    TextView check_pending_tv1;
    TextView check_pending_tv2;
    TextView check_pending_tv3;
    RecyclerView check_pending_rv;

    TextView check_pending_bt1;
    TextView check_pending_bt2;

    Project_Side_MakeABargainAdapter adapter;


    private ReportProcessDetailsBean.DataBean.InfoDataBean infoData;
    private List<ReportProcessDetailsBean.DataBean.ProcessDataBean> processData;
    private List<String> list;
    private Intent intent;
    private String mycheck;
    private String name;
    private String url;
    private LinearLayout check_pending_ll;

    int isnum1 = 0;
    int isnum2 = 0;
    private Button check_pending_b1;
    private Button check_pending_b2;
    private LinearLayout check_pending_l;
    private RecyclerView check_pending_nameRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_side_activity_check_pending);
        init_No_Network();
    }

    private void init_No_Network() {
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

        name = getIntent().getStringExtra("name");

        mycheck = getIntent().getStringExtra("Mycheck");
        check_pending_nameRv = findViewById(R.id.check_pending_NameRv);
        check_pending_return = findViewById(R.id.check_pending_return);
        check_pending_img1 = findViewById(R.id.check_pending_img1);
        check_pending_tv1 = findViewById(R.id.check_pending_tv1);
        check_pending_tv2 = findViewById(R.id.check_pending_tv2);
        check_pending_tv3 = findViewById(R.id.check_pending_tv3);
        check_pending_rv = findViewById(R.id.check_pending_rv);
        check_pending_bt1 = findViewById(R.id.check_pending_bt1);
        check_pending_bt2 = findViewById(R.id.check_pending_bt2);
        check_pending_ll = findViewById(R.id.check_pending_ll);

        check_pending_b1 = findViewById(R.id.check_pending_b1);
        check_pending_b2 = findViewById(R.id.check_pending_b2);
        check_pending_l = findViewById(R.id.check_pending_l);

        if (FinalContents.getJuJue().equals("拒绝记录")) {
            check_pending_ll.setVisibility(View.GONE);
        } else if (FinalContents.getJuJue().equals("待我审核")) {
            check_pending_ll.setVisibility(View.VISIBLE);
        }

        check_pending_bt1.setOnClickListener(this);
        check_pending_bt2.setOnClickListener(this);

        initData();
    }

    private void initData() {

        if (mycheck.equals("1")) {
            check_pending_bt2.setText("拒绝");
        } else if (mycheck.equals("2")) {
            check_pending_bt2.setText("修改认筹信息");
        } else if (mycheck.equals("3")) {
            check_pending_bt2.setText("修改成交信息");
        }


        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<ReportProcessDetailsBean> clientFragmentBean = fzbInterface.getReportProcessDetails(FinalContents.getUserID(), FinalContents.getPreparationId());
        clientFragmentBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ReportProcessDetailsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ReportProcessDetailsBean reportProcessDetailsBean) {
                        infoData = reportProcessDetailsBean.getData().getInfoData();
                        if (infoData.getCustomerImg() != null) {
                            if (!infoData.getCustomerImg().equals("")) {
                                Glide.with(CheckPendingActivity.this).load(FinalContents.getImageUrl() + infoData.getCustomerImg()).into(check_pending_img1);
                            }
                        }

                        check_pending_tv1.setText(infoData.getCustomerName());

                        processData = reportProcessDetailsBean.getData().getProcessData();
                        check_pending_tv2.setText(infoData.getProjectName());
                        if (reportProcessDetailsBean.getData().getAttacheList().size() == 0) {
                            check_pending_tv3.setVisibility(View.GONE);
                        }else {
                            GridLayoutManager gridLayoutManager = new GridLayoutManager(CheckPendingActivity.this,2);
                            gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
                            check_pending_nameRv.setLayoutManager(gridLayoutManager);
                            ReviewTheSuccessPhoneAdapter reviewTheSuccessPhoneAdapter = new ReviewTheSuccessPhoneAdapter(reportProcessDetailsBean.getData().getAttacheList());
                            check_pending_nameRv.setAdapter(reviewTheSuccessPhoneAdapter);
                            reviewTheSuccessPhoneAdapter.notifyDataSetChanged();
                            check_pending_tv3.setText("项目负责人：");
                        }
//                        check_pending_tv3.setText(infoData.getCustomerName() + "[" + infoData.getCustomerPhone() + "]");
                        FinalContents.setJJrID(infoData.getAgentId());
                        initRV();

                        check_pending_return.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                finish();
                            }
                        });
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @SuppressLint("WrongConstant")
    private void initRV() {

        list = new ArrayList<>();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        check_pending_rv.setLayoutManager(manager);
        adapter = new Project_Side_MakeABargainAdapter();
        adapter.setListData(processData);

        check_pending_rv.setAdapter(adapter);
    }

    @SingleClick(1000)
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            //            TODO 通过
            case R.id.check_pending_bt1:
                if (isnum1 == 0) {
                    isnum1 = 1;
                    initBTN();
                    isnum1 = 0;
                }
                break;
            //            TODO 拒绝
            case R.id.check_pending_bt2:
                if (isnum2 == 0) {
                    isnum2 = 1;
                    String s = check_pending_bt2.getText().toString();
                    if (s.equals("拒绝")) {
                        intent = new Intent(CheckPendingActivity.this, TheReasonForRefusalActivity.class);
                    } else if (s.equals("修改认筹信息")) {
                        intent = new Intent(CheckPendingActivity.this, ModifyTheRecognitionToRaiseActivity.class);
                    } else if (s.equals("修改成交信息")) {
                        intent = new Intent(CheckPendingActivity.this, VisitingScheduleActivity.class);
                        FinalContents.setTiaodan("调单");
                    }
                    startActivity(intent);
                    finish();
                    isnum2 = 0;
                }

                break;
        }

    }

    @SuppressLint("WrongConstant")
    private void initBTN() {
        if (name.equals("报备") || name.equals("到访")) {
            Retrofit.Builder builder = new Retrofit.Builder();
            builder.baseUrl(FinalContents.getBaseUrl());
            builder.addConverterFactory(GsonConverterFactory.create());
            builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
            Retrofit build = builder.build();
            MyService fzbInterface = build.create(MyService.class);
            Observable<CBean> clientFragmentBean = fzbInterface.getReportAndVisitAudit(FinalContents.getUserID(), FinalContents.getPreparationId(), FinalContents.getStatus(), "1","");
            clientFragmentBean.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<CBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(CBean cBean) {
                            if (cBean.getMsg().equals("成功")) {
                                ToastUtil.showToast(CheckPendingActivity.this, cBean.getData().getMessage());
                                FinalContents.setEndStart("成功");
                                finish();
                            } else {
                                ToastUtil.showToast(CheckPendingActivity.this, cBean.getData().getMessage());
                                finish();
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.i("MyCL", "错误信息：" + e.getMessage());
                        }

                        @Override
                        public void onComplete() {

                        }
                    });

        } else if (name.equals("认筹")) {
            Retrofit.Builder builder = new Retrofit.Builder();
            builder.baseUrl(FinalContents.getBaseUrl());
            builder.addConverterFactory(GsonConverterFactory.create());
            builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
            Retrofit build = builder.build();
            MyService fzbInterface = build.create(MyService.class);
            Observable<CheckBean> clientFragmentBean = fzbInterface.getEarnestMoneyAudit(FinalContents.getUserID(), FinalContents.getPreparationId(), "0");
            clientFragmentBean.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<CheckBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(CheckBean checkBean) {
                            if (checkBean.getMsg().equals("成功")) {
                                ToastUtil.showToast(CheckPendingActivity.this, checkBean.getMsg());
                                finish();
                            } else {
                                ToastUtil.showToast(CheckPendingActivity.this, checkBean.getMsg());
                                finish();
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.i("MyCL", "错误信息：" + e.getMessage());
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        } else if (name.equals("成交")) {

            Retrofit.Builder builder = new Retrofit.Builder();
            builder.baseUrl(FinalContents.getBaseUrl());
            builder.addConverterFactory(GsonConverterFactory.create());
            builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
            Retrofit build = builder.build();
            MyService fzbInterface = build.create(MyService.class);
            Observable<CheckBean> clientFragmentBean = fzbInterface.getTradeAudit(FinalContents.getUserID(), FinalContents.getPreparationId(), "0");
            clientFragmentBean.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<CheckBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(CheckBean checkBean) {
                            if (checkBean.getMsg().equals("成功")) {
                                ToastUtil.showToast(CheckPendingActivity.this, checkBean.getMsg());
                                finish();
                            } else {
                                ToastUtil.showToast(CheckPendingActivity.this, checkBean.getMsg());
                                finish();
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.i("MyCL", "错误信息：" + e.getMessage());
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }

    }

    private void initReadRecord(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<ReadRecordBean> clientFragment = fzbInterface.getReadRecord(FinalContents.getUserID(),FinalContents.getPreparationId(), "");
        clientFragment.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ReadRecordBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ReadRecordBean readRecordBean) {
                        Log.i("MyCL", "项目进度中未浏览信息"+readRecordBean.getData().getMessage());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "项目进度中未浏览错误信息" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        initReadRecord();
    }
}
