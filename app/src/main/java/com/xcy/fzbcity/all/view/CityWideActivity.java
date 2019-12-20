package com.xcy.fzbcity.all.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.adapter.RecyclerAdapter;
import com.xcy.fzbcity.all.api.CityContents;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.modle.HotBean;
import com.xcy.fzbcity.all.service.MyService;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CityWideActivity extends AllActivity {

    private LinearLayout city_wide_return;
    private EditText city_wide_search;
    private LinearLayout city_wide_location;
    private RecyclerView city_wide_rv;
    private ImageView all_city_wide_no_information;
    String searchName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_wide);
        initfvb();
    }

    private void initfvb(){
        city_wide_return = findViewById(R.id.city_wide_return);
        city_wide_search = findViewById(R.id.city_wide_search);
        city_wide_location = findViewById(R.id.city_wide_location);
        city_wide_rv = findViewById(R.id.city_wide_Rv);
        all_city_wide_no_information = findViewById(R.id.all_city_wide_no_information);
        initData();
        initClick();
    }

    private void initClick(){
        city_wide_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });     //      TODO    退出

        city_wide_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FinalContents.setIfCity(FinalContents.getCityID());
                Intent intent_overseas = new Intent(view.getContext(), MapHouseActivity.class);
                startActivity(intent_overseas);
            }
        });     //      TODO    地图定位


        city_wide_search.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (KeyEvent.KEYCODE_ENTER == i && KeyEvent.ACTION_DOWN == keyEvent.getAction()) {
                    searchName = city_wide_search.getText().toString();
                    Log.i("运行次数","呀：");
                    initData();
                    return true;
                }
                return false;
            }

        });
    }

    //      TODO    列表数据
    private void initData(){
        Log.i("运行次数","次数：");
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<HotBean> userMessage = fzbInterface.getList(FinalContents.getUserID(), FinalContents.getCityID(), FinalContents.getComprehensiveSorting(), FinalContents.getProjectLabel(), FinalContents.getProjectType(), CityContents.getCityType(), FinalContents.getNation(), FinalContents.getProjectPriceStart(), FinalContents.getProjectPriceEnd(), FinalContents.getApartment(), FinalContents.getAreaSection(), FinalContents.getFfProjectTrait(), FinalContents.getProcuctType(), FinalContents.getFitmentState(), searchName,"1000");
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HotBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(HotBean hotBean) {
                        HotBean.DataBean hotBeanData = hotBean.getData();
                        List<HotBean.DataBean.RowsBean> hotlist = hotBeanData.getRows();
                        Log.i("运行次数","加载：");
                        if (hotlist.size() != 0) {
                            all_city_wide_no_information.setVisibility(View.GONE);
                            city_wide_rv.setVisibility(View.VISIBLE);
                            //在此处修改布局排列方向
                            LinearLayoutManager layoutManager = new LinearLayoutManager(CityWideActivity.this);
                            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                            city_wide_rv.setLayoutManager(layoutManager);
                            RecyclerAdapter recyclerAdapter = new RecyclerAdapter(hotlist);
                            city_wide_rv.setAdapter(recyclerAdapter);
                            recyclerAdapter.notifyDataSetChanged();
                        } else {
                            all_city_wide_no_information.setVisibility(View.VISIBLE);
                            city_wide_rv.setVisibility(View.GONE);
                        }


                    }

                    @Override
                    public void onError(Throwable e) {
                        all_city_wide_no_information.setVisibility(View.VISIBLE);
                        city_wide_rv.setVisibility(View.GONE);
                        Log.i("列表数据获取错误", "错误" + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
