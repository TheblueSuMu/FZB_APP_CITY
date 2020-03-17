package com.xcy.fzbcity.all.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.adapter.HousingSupermarketAdapter;
import com.xcy.fzbcity.all.adapter.HousingSupermarketAddHousingAdapter;
import com.xcy.fzbcity.all.adapter.HousingSupermarketHotListAdapter;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.api.RedEnvelopesAllTalk;
import com.xcy.fzbcity.all.modle.HotBean;
import com.xcy.fzbcity.all.modle.SupermarketBean;
import com.xcy.fzbcity.all.persente.MyItemTouchHelper;
import com.xcy.fzbcity.all.persente.MyItemTouchHelper2;
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

public class HousingSupermarketHotListActivity extends AllActivity implements View.OnClickListener {

    private LinearLayout housing_supermarket_hot_list_return;
    private RecyclerView housing_supermarket_hot_list_recyclerview;
    private HousingSupermarketHotListAdapter housingSupermarketHotListAdapter;
    private ImageView housing_supermarket_hot_list_all_no_information;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_housing_supermarket_hot_list);
        init_No_Network();
    }

    private void init_No_Network(){
        boolean networkAvailable = CommonUtil.isNetworkAvailable(this);
        if (networkAvailable) {
            initfvb();
        } else {
            RelativeLayout all_no_network = findViewById(R.id.housing_supermarket_hot_list_all_no_network);
            Button all_no_reload = findViewById(R.id.housing_supermarket_hot_list_all_no_reload);

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
        housing_supermarket_hot_list_return = findViewById(R.id.housing_supermarket_hot_list_return);
        housing_supermarket_hot_list_recyclerview = findViewById(R.id.housing_supermarket_hot_list_recyclerview);
        housing_supermarket_hot_list_all_no_information = findViewById(R.id.housing_supermarket_hot_list_all_no_information);
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
//        HousingSupermarketHotList
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
                        if (supermarketBean.getData().getRows().size() != 0) {
                            housing_supermarket_hot_list_all_no_information.setVisibility(View.GONE);
                            housing_supermarket_hot_list_recyclerview.setVisibility(View.VISIBLE);
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(HousingSupermarketHotListActivity.this);
                            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                            housing_supermarket_hot_list_recyclerview.setLayoutManager(linearLayoutManager);
                            housingSupermarketHotListAdapter = new HousingSupermarketHotListAdapter(supermarketBean.getData().getRows());
                            housing_supermarket_hot_list_recyclerview.setAdapter(housingSupermarketHotListAdapter);
                            housingSupermarketHotListAdapter.notifyDataSetChanged();

                            ItemTouchHelper helper = new ItemTouchHelper(new MyItemTouchHelper2(housingSupermarketHotListAdapter));
                            helper.attachToRecyclerView(housing_supermarket_hot_list_recyclerview);
                        }else {
                            housing_supermarket_hot_list_all_no_information.setVisibility(View.VISIBLE);
                            housing_supermarket_hot_list_recyclerview.setVisibility(View.GONE);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        housing_supermarket_hot_list_all_no_information.setVisibility(View.VISIBLE);
                        housing_supermarket_hot_list_recyclerview.setVisibility(View.GONE);
                        Log.i("热推列表", "热推列表错误信息:" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
