package com.xcy.fzbcity.project_side.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.modle.ReadRecordBean;
import com.xcy.fzbcity.all.modle.ReportProcessDetailsBean;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.service.MyService;
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

public class InitiatedActivity extends AllActivity {

    RelativeLayout initiated_return;
    ImageView initiated_img1;
    ImageView initiated_img2;

    TextView initiated_tv1;
    TextView initiated_tv2;
    TextView initiated_tv3;

    RecyclerView initiated_rv;
    Project_Side_MakeABargainAdapter adapter;
    private ReportProcessDetailsBean.DataBean.InfoDataBean infoData;
    private List<ReportProcessDetailsBean.DataBean.ProcessDataBean> processData;
    private List<String> list;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_side_activity_initiated);

        initView();

    }

    private void initView() {
        StatusBar.makeStatusBarTransparent(this);
        initiated_return = findViewById(R.id.initiated_return);
        initiated_img1 = findViewById(R.id.initiated_img1);
        initiated_img2 = findViewById(R.id.initiated_img2);
        initiated_tv1 = findViewById(R.id.initiated_tv1);
        initiated_tv2 = findViewById(R.id.initiated_tv2);
        initiated_tv3 = findViewById(R.id.initiated_tv3);
        initiated_rv = findViewById(R.id.initiated_rv);

        initData();

        initiated_img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void initData() {

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
                                Glide.with(InitiatedActivity.this).load(FinalContents.getImageUrl() + infoData.getCustomerImg()).into(initiated_img1);
                            }
                        }
                        initiated_tv1.setText(infoData.getCustomerName());
                        processData = reportProcessDetailsBean.getData().getProcessData();

                        initiated_tv2.setText(infoData.getProjectName());
                        initiated_tv3.setText(infoData.getCustomerName() + "[" + infoData.getCustomerPhone() + "]");
                        initiated_return.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                finish();
                            }
                        });

                        initRV();
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
        initiated_rv.setLayoutManager(manager);
        adapter = new Project_Side_MakeABargainAdapter();
        adapter.setListData(processData);
        initiated_rv.setAdapter(adapter);
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
