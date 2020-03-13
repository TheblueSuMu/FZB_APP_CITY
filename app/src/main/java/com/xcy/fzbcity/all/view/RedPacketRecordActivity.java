package com.xcy.fzbcity.all.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.adapter.RedPacketRecordAdapter;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.api.RedEnvelopesAllTalk;
import com.xcy.fzbcity.all.modle.CheckRedbagPayProjectBean;
import com.xcy.fzbcity.all.modle.RedBagSumStatisticsBean;
import com.xcy.fzbcity.all.modle.RedbagStatisticsBean;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.ToastUtil;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RedPacketRecordActivity extends AllActivity{

    private RecyclerView red_packet_record_recycler;
    private RelativeLayout red_packet_record_return;
    private TextView red_packet_record_all_money_get;
    private TextView red_packet_record_all_people;
    private TextView red_packet_record_all_money_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_red_packet_record);
        initfvb();
    }


    private void initfvb(){
        red_packet_record_return = findViewById(R.id.red_packet_record_return);
        red_packet_record_all_money_get = findViewById(R.id.red_packet_record_all_money_get);
        red_packet_record_all_people = findViewById(R.id.red_packet_record_all_people);
        red_packet_record_all_money_send = findViewById(R.id.red_packet_record_all_money_send);
        red_packet_record_recycler = findViewById(R.id.red_packet_record_recycler);

        red_packet_record_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        initData();
        initDataList();
    }

    //      TODO    红包记录统计
    private void initData(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        final Observable<RedBagSumStatisticsBean> code = fzbInterface.getRedbagSumStatistics("1");
        code.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RedBagSumStatisticsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RedBagSumStatisticsBean redBagSumStatisticsBean) {
                        redBagSumStatisticsBean.getData().getListData().getTotalConvertedAmount();// 总领取金额
                        redBagSumStatisticsBean.getData().getListData().getTotalCustomer();// 总获客数
                        redBagSumStatisticsBean.getData().getListData().getTotalProvideAmount();// 总发放金额

                        Log.i("红包记录统计", "总领取金额:" + redBagSumStatisticsBean.getData().getListData().getTotalConvertedAmount());
                        Log.i("红包记录统计", "总获客数:" + redBagSumStatisticsBean.getData().getListData().getTotalCustomer());
                        Log.i("红包记录统计", "总发放金额:" + redBagSumStatisticsBean.getData().getListData().getTotalProvideAmount());

                        red_packet_record_all_money_get.setText(redBagSumStatisticsBean.getData().getListData().getTotalConvertedAmount());
                        red_packet_record_all_people.setText(redBagSumStatisticsBean.getData().getListData().getTotalCustomer());
                        red_packet_record_all_money_send.setText(redBagSumStatisticsBean.getData().getListData().getTotalProvideAmount());

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("红包记录统计", "红包记录统计错误信息:" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //      TODO    红包记录列表
    private void initDataList(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        final Observable<RedbagStatisticsBean> code = fzbInterface.getRedbagStatistics("1","10","1");
        code.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RedbagStatisticsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RedbagStatisticsBean redbagStatisticsBean) {
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(RedPacketRecordActivity.this);
                        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        red_packet_record_recycler.setLayoutManager(linearLayoutManager);
                        final RedPacketRecordAdapter redPacketRecordAdapter = new RedPacketRecordAdapter(redbagStatisticsBean.getData().getRows());
                        red_packet_record_recycler.setAdapter(redPacketRecordAdapter);
                        redPacketRecordAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("红包记录列表", "红包记录列表错误信息:" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}
