package com.xcy.fzbcity.all.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.adapter.GetTheRecordAdapter;
import com.xcy.fzbcity.all.adapter.HousingSupermarketAdapter;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.modle.AppletWechatImageBean;
import com.xcy.fzbcity.all.modle.RedbagReceiveRecordBean;
import com.xcy.fzbcity.all.service.MyService;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetTheRecordActivity extends AllActivity implements View.OnClickListener {

    private RecyclerView get_the_record_recyclerview;
    private RelativeLayout get_the_record_return;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_the_record);
        initfvb();
    }

    private void initfvb(){
        get_the_record_recyclerview = findViewById(R.id.get_the_record_recyclerview);
        get_the_record_return = findViewById(R.id.get_the_record_return);
        get_the_record_return.setOnClickListener(this);
        initData();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.get_the_record_return :
                //  TODO    返回
                finish();
                break;
        }
    }

    //      TODO    领取记录列表
    private void initData(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        final Observable<RedbagReceiveRecordBean> code = fzbInterface.getRedbagReceiveRecord(FinalContents.getUserID(),"1","7","","10","1");
        code.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RedbagReceiveRecordBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RedbagReceiveRecordBean redbagReceiveRecordBean) {
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(GetTheRecordActivity.this);
                        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        get_the_record_recyclerview.setLayoutManager(linearLayoutManager);
                        GetTheRecordAdapter getTheRecordAdapter = new GetTheRecordAdapter(redbagReceiveRecordBean.getData().getRows());
                        get_the_record_recyclerview.setAdapter(getTheRecordAdapter);
                        getTheRecordAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("领取记录列表", "领取记录列表错误信息:" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
