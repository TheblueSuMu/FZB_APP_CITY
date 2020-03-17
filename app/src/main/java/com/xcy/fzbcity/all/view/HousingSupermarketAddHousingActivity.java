package com.xcy.fzbcity.all.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
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
import com.xcy.fzbcity.all.adapter.HousingSupermarketAddHousingAdapter;
import com.xcy.fzbcity.all.adapter.RecyclerAdapter;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.api.RedEnvelopesAllTalk;
import com.xcy.fzbcity.all.modle.HotBean;
import com.xcy.fzbcity.all.modle.ProjectAddBean;
import com.xcy.fzbcity.all.persente.MyLinearLayoutManager;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.CommonUtil;
import com.xcy.fzbcity.all.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HousingSupermarketAddHousingActivity extends AllActivity implements View.OnClickListener {

    private RelativeLayout housing_supermarket_add_housing_return;
    private EditText housing_supermarket_add_housing_search;
    private TextView housing_supermarket_add_housing_sure;
    private RecyclerView housing_supermarket_add_housing_recyclerview;
    private HousingSupermarketAddHousingAdapter housingSupermarketAddHousingAdapter;
    private List<String> arrayList;
    private String projectId = "";
    private ImageView housing_supermarket_add_housing_all_no_information;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_housing_supermarket_add_housing);
        init_No_Network();
    }

    private void init_No_Network(){
        boolean networkAvailable = CommonUtil.isNetworkAvailable(this);
        if (networkAvailable) {
            initfvb();
        } else {
            RelativeLayout all_no_network = findViewById(R.id.housing_supermarket_add_housing_all_no_network);
            Button all_no_reload = findViewById(R.id.housing_supermarket_add_housing_all_no_reload);

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
        housing_supermarket_add_housing_return = findViewById(R.id.housing_supermarket_add_housing_return);
        housing_supermarket_add_housing_search = findViewById(R.id.housing_supermarket_add_housing_search);
        housing_supermarket_add_housing_sure = findViewById(R.id.housing_supermarket_add_housing_sure);
        housing_supermarket_add_housing_recyclerview = findViewById(R.id.housing_supermarket_add_housing_recyclerview);
        housing_supermarket_add_housing_all_no_information = findViewById(R.id.housing_supermarket_add_housing_all_no_information);
        housing_supermarket_add_housing_return.setOnClickListener(this);
        housing_supermarket_add_housing_sure.setOnClickListener(this);
        initDataList();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.housing_supermarket_add_housing_return :
                //      TODO    返回
                finish();
                break;
            case R.id.housing_supermarket_add_housing_sure :
                //      TODO    添加完成
                projectId = "";
                arrayList = housingSupermarketAddHousingAdapter.getArrayList();
                boolean flag = false;
                for (int index = 0;index < arrayList.size();index++){
                    if (!arrayList.get(index).equals("")) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    ToastUtil.showLongToast(HousingSupermarketAddHousingActivity.this,"请选择要添加的项目");
                    return;
                }

                for (int i = 0;i < arrayList.size();i++){
                    if (i == 0) {
                        projectId = arrayList.get(i);
                    }else {
                        projectId = projectId + "," + arrayList.get(i);
                    }
                }
                initDataAdd();
                break;

        }
    }

    //      TODO    待添加的列表
    private void initDataList(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<HotBean> userMessage = fzbInterface.getHotList(FinalContents.getUserID(), FinalContents.getCityID(), "1", "1000");
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HotBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(HotBean hotBean) {
                        if (hotBean.getData().getRows().size() != 0) {
                            housing_supermarket_add_housing_all_no_information.setVisibility(View.GONE);
                            housing_supermarket_add_housing_recyclerview.setVisibility(View.VISIBLE);
                            arrayList = new ArrayList<>();
                            for (int i = 0;i < hotBean.getData().getRows().size();i++){
                                arrayList.add("");
                            }
                            LinearLayoutManager layoutManager = new LinearLayoutManager(HousingSupermarketAddHousingActivity.this);
                            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                            housing_supermarket_add_housing_recyclerview.setLayoutManager(layoutManager);
                            housingSupermarketAddHousingAdapter = new HousingSupermarketAddHousingAdapter(hotBean.getData().getRows());
                            housing_supermarket_add_housing_recyclerview.setAdapter(housingSupermarketAddHousingAdapter);
                            housingSupermarketAddHousingAdapter.setArrayList(arrayList);
                            housingSupermarketAddHousingAdapter.notifyDataSetChanged();
                        }else {
                            housing_supermarket_add_housing_all_no_information.setVisibility(View.VISIBLE);
                            housing_supermarket_add_housing_recyclerview.setVisibility(View.GONE);
                        }


                    }

                    @Override
                    public void onError(Throwable e) {
                        housing_supermarket_add_housing_all_no_information.setVisibility(View.VISIBLE);
                        housing_supermarket_add_housing_recyclerview.setVisibility(View.GONE);
                        Log.i("添加房源列表", "添加房源列表错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //      TODO    网店添加项目
    private void initDataAdd(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<ProjectAddBean> userMessage = fzbInterface.getProjectAdd(FinalContents.getUserID(),projectId, RedEnvelopesAllTalk.getWebshopId());
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ProjectAddBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(ProjectAddBean projectAddBean) {
                        ToastUtil.showLongToast(HousingSupermarketAddHousingActivity.this,projectAddBean.getData().getMessage());
                        if (projectAddBean.getData().getStatus().equals("1")) {
                            finish();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("添加房源按钮", "添加房源按钮的错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
