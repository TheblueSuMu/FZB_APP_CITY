package com.xcy.fzbcity.all.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
import com.xcy.fzbcity.all.modle.AddCashBean;
import com.xcy.fzbcity.all.modle.PreferentialActListBean;
import com.xcy.fzbcity.all.modle.SupermarketBean;
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

public class SpecialOfferActivity extends AllActivity implements View.OnClickListener {

    private RelativeLayout special_offer_return;
    private TextView special_offer_history_list;
    private RecyclerView special_offer_recyclerview;
    private SpecialOfferAdapter specialOfferAdapter;
    private ImageView special_offer_all_no_information;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_offer);
        init_No_Network();
    }

    private void init_No_Network(){
        boolean networkAvailable = CommonUtil.isNetworkAvailable(this);
        if (networkAvailable) {
            initfvb();
        } else {
            RelativeLayout all_no_network = findViewById(R.id.special_offer_all_no_network);
            Button all_no_reload = findViewById(R.id.red_packet_record_all_no_reload);

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
        special_offer_return = findViewById(R.id.special_offer_return);
        special_offer_history_list = findViewById(R.id.special_offer_history_list);
        special_offer_recyclerview = findViewById(R.id.special_offer_recyclerview);
        special_offer_all_no_information = findViewById(R.id.special_offer_all_no_information);
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
                        if (preferentialActListBean.getData().getRows().size() != 0) {
                            special_offer_all_no_information.setVisibility(View.GONE);
                            special_offer_recyclerview.setVisibility(View.VISIBLE);
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SpecialOfferActivity.this);
                            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                            special_offer_recyclerview.setLayoutManager(linearLayoutManager);
                            specialOfferAdapter = new SpecialOfferAdapter(preferentialActListBean.getData().getRows());
                            special_offer_recyclerview.setAdapter(specialOfferAdapter);
                            specialOfferAdapter.notifyDataSetChanged();
                            specialOfferAdapter.setOnItemClickListener(new SpecialOfferAdapter.OnItemClickLisenter() {
                                @Override
                                public void onItemClick(int postion) {
                                    Log.i("奖券","奖券ID："+preferentialActListBean.getData().getRows().get(postion).getLotteryId());
                                    initAddCash(preferentialActListBean.getData().getRows().get(postion).getLotteryId());
                                }
                            });
                        }else {
                            special_offer_all_no_information.setVisibility(View.VISIBLE);
                            special_offer_recyclerview.setVisibility(View.GONE);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        special_offer_all_no_information.setVisibility(View.VISIBLE);
                        special_offer_recyclerview.setVisibility(View.GONE);
                        Log.i("优惠活动列表", "优惠活动列表错误信息:" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //      TODO    经纪人兑现奖券
    private void initAddCash(String lotteryUseId){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        final Observable<AddCashBean> code = fzbInterface.getAddCash(FinalContents.getUserID(), lotteryUseId);
        code.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AddCashBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AddCashBean addCashBean) {
                        ToastUtil.showLongToast(SpecialOfferActivity.this,addCashBean.getData().getMessage());
                        specialOfferAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("经纪人兑现奖券", "经纪人兑现奖券错误信息:" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
