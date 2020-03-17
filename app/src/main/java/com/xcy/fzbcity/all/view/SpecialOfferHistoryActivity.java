package com.xcy.fzbcity.all.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.adapter.SpecialOfferAdapter;
import com.xcy.fzbcity.all.adapter.SpecialOfferHistoryAdapter;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.modle.PreferentialActListBean;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.CommonUtil;
import com.xcy.fzbcity.all.utils.ToastUtil;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SpecialOfferHistoryActivity extends AllActivity implements View.OnClickListener {

    private RecyclerView special_offer_history_recyclerview;
    private RelativeLayout special_offer_history_return;
    private ImageView special_offer_history_all_no_information;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_offer_history);
        init_No_Network();
    }

    private void init_No_Network(){
        boolean networkAvailable = CommonUtil.isNetworkAvailable(this);
        if (networkAvailable) {
            initfvb();
        } else {
            RelativeLayout all_no_network = findViewById(R.id.special_offer_history_all_no_network);
            Button all_no_reload = findViewById(R.id.special_offer_history_all_no_reload);

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

    private void initfvb(){
        special_offer_history_return = findViewById(R.id.special_offer_history_return);
        special_offer_history_recyclerview = findViewById(R.id.special_offer_history_recyclerview);
        special_offer_history_all_no_information = findViewById(R.id.special_offer_history_all_no_information);
        special_offer_history_return.setOnClickListener(this);
        initData();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.special_offer_history_return :
                //  TODO    返回
                finish();
                break;
        }
    }

    //  TODO    优惠活动历史记录列表
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
                        if (preferentialActListBean.getData().getRows().size() != 0) {
                            special_offer_history_all_no_information.setVisibility(View.GONE);
                            special_offer_history_recyclerview.setVisibility(View.VISIBLE);
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SpecialOfferHistoryActivity.this);
                            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                            special_offer_history_recyclerview.setLayoutManager(linearLayoutManager);
                            SpecialOfferHistoryAdapter specialOfferHistoryAdapter = new SpecialOfferHistoryAdapter(preferentialActListBean.getData().getRows());
                            special_offer_history_recyclerview.setAdapter(specialOfferHistoryAdapter);
                            specialOfferHistoryAdapter.notifyDataSetChanged();
                        }else {
                            special_offer_history_all_no_information.setVisibility(View.VISIBLE);
                            special_offer_history_recyclerview.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        special_offer_history_all_no_information.setVisibility(View.VISIBLE);
                        special_offer_history_recyclerview.setVisibility(View.GONE);
                        Log.i("优惠活动历史记录列表", "优惠活动历史记录列表错误信息:" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
