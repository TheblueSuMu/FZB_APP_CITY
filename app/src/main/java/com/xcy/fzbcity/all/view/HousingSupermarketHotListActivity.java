package com.xcy.fzbcity.all.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.adapter.HousingSupermarketAdapter;
import com.xcy.fzbcity.all.adapter.HousingSupermarketAddHousingAdapter;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.api.RedEnvelopesAllTalk;
import com.xcy.fzbcity.all.modle.HotBean;
import com.xcy.fzbcity.all.modle.SupermarketBean;
import com.xcy.fzbcity.all.service.MyService;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HousingSupermarketHotListActivity extends AllActivity implements View.OnClickListener {

    private LinearLayout housing_supermarket_hot_list_return;
    private RecyclerView housing_supermarket_hot_list_recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_housing_supermarket_hot_list);
        initfvb();
    }

    private void initfvb(){
        housing_supermarket_hot_list_return = findViewById(R.id.housing_supermarket_hot_list_return);
        housing_supermarket_hot_list_recyclerview = findViewById(R.id.housing_supermarket_hot_list_recyclerview);

        housing_supermarket_hot_list_return.setOnClickListener(this);
        initData();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.housing_supermarket_hot_list_return :
                //      TODO    返回
                finish();
                break;
        }
    }

    //  TODO    热推列表
    private void initData(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        final Observable<SupermarketBean> code = fzbInterface.getSupermarket(FinalContents.getUserID(), RedEnvelopesAllTalk.getWebshopId(),"1");
        code.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SupermarketBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SupermarketBean supermarketBean) {
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(HousingSupermarketHotListActivity.this);
                        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        housing_supermarket_hot_list_recyclerview.setLayoutManager(linearLayoutManager);
                        HousingSupermarketAdapter housingSupermarketAdapter = new HousingSupermarketAdapter(supermarketBean.getData().getRows());
                        housing_supermarket_hot_list_recyclerview.setAdapter(housingSupermarketAdapter);
                        housingSupermarketAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("热推列表", "热推列表错误信息:" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
