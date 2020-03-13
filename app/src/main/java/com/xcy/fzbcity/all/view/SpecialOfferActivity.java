package com.xcy.fzbcity.all.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.adapter.HousingSupermarketAdapter;
import com.xcy.fzbcity.all.adapter.SpecialOfferAdapter;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.api.RedEnvelopesAllTalk;
import com.xcy.fzbcity.all.modle.PreferentialActListBean;
import com.xcy.fzbcity.all.modle.SupermarketBean;
import com.xcy.fzbcity.all.service.MyService;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SpecialOfferActivity extends AllActivity implements View.OnClickListener {

    private RelativeLayout special_offer_return;
    private TextView special_offer_history_list;
    private RecyclerView special_offer_recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_offer);
        initfvb();
    }

    private void initfvb(){
        special_offer_return = findViewById(R.id.special_offer_return);
        special_offer_history_list = findViewById(R.id.special_offer_history_list);
        special_offer_recyclerview = findViewById(R.id.special_offer_recyclerview);

        special_offer_return.setOnClickListener(this);
        special_offer_history_list.setOnClickListener(this);
        initData();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.special_offer_return :
                //  TODO    返回
                finish();
                break;
            case R.id.special_offer_history_list :
                //  TODO    历史记录
                Intent intent = new Intent(SpecialOfferActivity.this,SpecialOfferHistoryActivity.class);
                startActivity(intent);
                break;
        }
    }

    //  TODO    优惠活动列表
    private void initData(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        final Observable<PreferentialActListBean> code = fzbInterface.getPreferentialActList(FinalContents.getUserID(), "new");
        code.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PreferentialActListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PreferentialActListBean preferentialActListBean) {
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SpecialOfferActivity.this);
                        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        special_offer_recyclerview.setLayoutManager(linearLayoutManager);
                        SpecialOfferAdapter specialOfferAdapter = new SpecialOfferAdapter(preferentialActListBean.getData().getRows());
                        special_offer_recyclerview.setAdapter(specialOfferAdapter);
                        specialOfferAdapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("优惠活动列表", "优惠活动列表错误信息:" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
