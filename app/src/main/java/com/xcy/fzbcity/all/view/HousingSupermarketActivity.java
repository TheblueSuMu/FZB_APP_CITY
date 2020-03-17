package com.xcy.fzbcity.all.view;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.adapter.HousingSupermarketAdapter;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.api.RedEnvelopesAllTalk;
import com.xcy.fzbcity.all.modle.CustomerVisitorSumStatisticsBean;
import com.xcy.fzbcity.all.modle.HotPushBean;
import com.xcy.fzbcity.all.modle.ProjectSortBean;
import com.xcy.fzbcity.all.modle.RedBagSumStatisticsBean;
import com.xcy.fzbcity.all.modle.SupermarketBean;
import com.xcy.fzbcity.all.persente.MyItemTouchHelper;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.ToastUtil;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HousingSupermarketActivity extends AllActivity implements View.OnClickListener {

    private LinearLayout housing_supermarket_return;
    private TextView housing_supermarket_hot_list;
    private RecyclerView housing_supermarket_recycler;
    private Button housing_supermarket_add_housing;
    private HousingSupermarketAdapter housingSupermarketAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_housing_supermarket);
        initfvb();
    }

    private void initfvb(){
        housing_supermarket_return = findViewById(R.id.housing_supermarket_return);
        housing_supermarket_hot_list = findViewById(R.id.housing_supermarket_hot_list);
        housing_supermarket_recycler = findViewById(R.id.housing_supermarket_recycler);
        housing_supermarket_add_housing = findViewById(R.id.housing_supermarket_add_housing);

        housing_supermarket_return.setOnClickListener(this);
        housing_supermarket_hot_list.setOnClickListener(this);
        housing_supermarket_add_housing.setOnClickListener(this);
        initData();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.housing_supermarket_return:
                //     TODO     返回
                finish();
                break;
            case R.id.housing_supermarket_hot_list:
                //     TODO     热推列表
                Intent hotintent = new Intent(HousingSupermarketActivity.this,HousingSupermarketHotListActivity.class);
                startActivity(hotintent);
                break;
            case R.id.housing_supermarket_add_housing:
                //     TODO     新增楼盘
                Intent intent = new Intent(HousingSupermarketActivity.this,HousingSupermarketAddHousingActivity.class);
                startActivity(intent);
                break;
        }
    }


    //  TODO    房源超市列表
    private void initData(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        final Observable<SupermarketBean> code = fzbInterface.getSupermarket(FinalContents.getUserID(), RedEnvelopesAllTalk.getWebshopId(),"");
        code.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SupermarketBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SupermarketBean supermarketBean) {
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(HousingSupermarketActivity.this);
                        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        housing_supermarket_recycler.setLayoutManager(linearLayoutManager);
                        housingSupermarketAdapter = new HousingSupermarketAdapter(supermarketBean.getData().getRows());
                        housing_supermarket_recycler.setAdapter(housingSupermarketAdapter);
                        housingSupermarketAdapter.notifyDataSetChanged();

                        housingSupermarketAdapter.setOnItemClickListener(new HousingSupermarketAdapter.OnItemClickLisenter() {
                            @Override
                            public void onItemClick(int postion) {
                                initHotPush(supermarketBean.getData().getRows().get(postion).getProjectId());
                            }
                        });

                        ItemTouchHelper helper = new ItemTouchHelper(new MyItemTouchHelper(housingSupermarketAdapter));
                        helper.attachToRecyclerView(housing_supermarket_recycler);

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("房源超市列表", "房源超市列表错误信息:" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //  TODO    房源超市--热推/取消热推/删除
    private void initHotPush(String projectId){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        final Observable<HotPushBean> code = fzbInterface.getHotPush(FinalContents.getUserID(), RedEnvelopesAllTalk.getWebshopId(),projectId,RedEnvelopesAllTalk.getType());
        code.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HotPushBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HotPushBean hotPushBean) {
                        ToastUtil.showLongToast(HousingSupermarketActivity.this,hotPushBean.getData().getMessage());
                        initData();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("房源超市--热推/取消热推/删除", "房源超市--热推/取消热推/删除的错误信息:" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initProjectSort(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        final Observable<ProjectSortBean> code = fzbInterface.getProjectSort(FinalContents.getUserID(), RedEnvelopesAllTalk.getWebshopId(),"","");
        code.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ProjectSortBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ProjectSortBean projectSortBean) {
                        ToastUtil.showLongToast(HousingSupermarketActivity.this,projectSortBean.getData().getMessage());

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("访客记录列表", "访客记录列表错误信息:" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
