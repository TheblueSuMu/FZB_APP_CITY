package com.xcy.fzbcity.all.view;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.adapter.SpecialOfferHistoryAdapter;
import com.xcy.fzbcity.all.adapter.VisitorsToRecordAdapter;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.api.RedEnvelopesAllTalk;
import com.xcy.fzbcity.all.modle.CustomerVisitorStatisticsBean;
import com.xcy.fzbcity.all.modle.CustomerVisitorSumStatisticsBean;
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

public class VisitorsToRecordActivity extends AllActivity implements View.OnClickListener {

    private RelativeLayout visitors_to_record_return;
    private EditText visitors_to_record_search;
    private ImageView visitors_to_record_search_img;
    private TextView visitors_to_record_all_money_get;
    private TextView visitors_to_record_all_people;
    private TextView visitors_to_record_all_money_send;
    private RecyclerView visitors_to_record_recycler;
    private String searchName = "";     //  搜索使用
    private ImageView visitors_to_record_all_no_information;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitors_to_record);
        init_No_Network();
    }

    private void init_No_Network(){
        boolean networkAvailable = CommonUtil.isNetworkAvailable(this);
        if (networkAvailable) {
            initfvb();
        } else {
            RelativeLayout all_no_network = findViewById(R.id.visitors_to_record_all_no_network);
            Button all_no_reload = findViewById(R.id.visitors_to_record_all_no_reload);

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
        visitors_to_record_return = findViewById(R.id.visitors_to_record_return);
        visitors_to_record_search = findViewById(R.id.visitors_to_record_search);
        visitors_to_record_search_img = findViewById(R.id.visitors_to_record_search_img);
        visitors_to_record_all_money_get = findViewById(R.id.visitors_to_record_all_money_get);
        visitors_to_record_all_people = findViewById(R.id.visitors_to_record_all_people);
        visitors_to_record_all_money_send = findViewById(R.id.visitors_to_record_all_money_send);
        visitors_to_record_recycler = findViewById(R.id.visitors_to_record_recycler);
        visitors_to_record_all_no_information = findViewById(R.id.visitors_to_record_all_no_information);

        visitors_to_record_return.setOnClickListener(this);
        visitors_to_record_search_img.setOnClickListener(this);
        initData();
        initDataList();

        visitors_to_record_search.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (KeyEvent.KEYCODE_ENTER == i && KeyEvent.ACTION_DOWN == keyEvent.getAction()) {
                    searchName = visitors_to_record_search.getText().toString();
                    initDataList();
                    return true;
                }
                return false;
            }

        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.visitors_to_record_return :
                //  TODO    返回
                finish();
                break;
            case R.id.visitors_to_record_search_img :
                //  TODO    搜索
                searchName = visitors_to_record_search.getText().toString();
                initDataList();
                break;
        }
    }

    //  TODO    访客记录列表
    private void initData(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        final Observable<CustomerVisitorSumStatisticsBean> code = fzbInterface.getCustomerVisitorSumStatistics(FinalContents.getUserID(), RedEnvelopesAllTalk.getWebshopId());
        code.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CustomerVisitorSumStatisticsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CustomerVisitorSumStatisticsBean customerVisitorSumStatisticsBean) {
                        visitors_to_record_all_money_get.setText(customerVisitorSumStatisticsBean.getData().getListData().getToday()+"");
                        visitors_to_record_all_people.setText(customerVisitorSumStatisticsBean.getData().getListData().getWeek()+"");
                        visitors_to_record_all_money_send.setText(customerVisitorSumStatisticsBean.getData().getListData().getAll()+"");
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

    //  TODO    访客记录列表
    private void initDataList(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        final Observable<CustomerVisitorStatisticsBean> code = fzbInterface.getCustomerVisitorStatistics(FinalContents.getUserID(), RedEnvelopesAllTalk.getWebshopId(),searchName,"10","1");
        code.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CustomerVisitorStatisticsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CustomerVisitorStatisticsBean customerVisitorStatisticsBean) {
                        if (customerVisitorStatisticsBean.getData().getRows().size() != 0) {
                            visitors_to_record_all_no_information.setVisibility(View.GONE);
                            visitors_to_record_recycler.setVisibility(View.VISIBLE);
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(VisitorsToRecordActivity.this);
                            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                            visitors_to_record_recycler.setLayoutManager(linearLayoutManager);
                            VisitorsToRecordAdapter visitorsToRecordAdapter = new VisitorsToRecordAdapter(customerVisitorStatisticsBean.getData().getRows());
                            visitors_to_record_recycler.setAdapter(visitorsToRecordAdapter);
                            visitorsToRecordAdapter.notifyDataSetChanged();
                        }else {
                            visitors_to_record_all_no_information.setVisibility(View.VISIBLE);
                            visitors_to_record_recycler.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        visitors_to_record_all_no_information.setVisibility(View.VISIBLE);
                        visitors_to_record_recycler.setVisibility(View.GONE);

                        Log.i("访客记录列表", "访客记录列表错误信息:" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
