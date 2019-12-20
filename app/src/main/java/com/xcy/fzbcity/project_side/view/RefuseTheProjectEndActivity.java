package com.xcy.fzbcity.project_side.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.modle.InitiatedBean;
import com.xcy.fzbcity.all.persente.SingleClick;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.CommonUtil;
import com.xcy.fzbcity.all.utils.ToastUtil;
import com.xcy.fzbcity.all.view.AllActivity;
import com.xcy.fzbcity.project_side.adapter.InitiatedAdapter;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RefuseTheProjectEndActivity extends AllActivity implements View.OnClickListener, InitiatedAdapter.OnItemClick {

    RelativeLayout the_project_end_refuse_return;
    RecyclerView the_project_end_refuse_rv;

    LinearLayout the_project_end_refuse_ll1;
    LinearLayout the_project_end_refuse_ll2;
    LinearLayout the_project_end_refuse_ll3;
    LinearLayout the_project_end_refuse_ll4;
    LinearLayout the_project_end_refuse_ll5;
    LinearLayout the_project_end_refuse_ll6;
    LinearLayout the_project_end_refuse_ll7;
    LinearLayout the_project_end_refuse_ll8;

    InitiatedAdapter adapter;
    private List<InitiatedBean.DataBean.RowsBean> rows;
    private ImageView all_no_information;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_side_activity_refuse_the_project_end);
        init_No_Network();
    }

    private void init_No_Network(){
        boolean networkAvailable = CommonUtil.isNetworkAvailable(this);
        if (networkAvailable) {
            initView();
        } else {
            RelativeLayout all_no_network = findViewById(R.id.all_no_network);
            Button all_no_reload = findViewById(R.id.all_no_reload);

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

    private void initView() {

        StatusBar.makeStatusBarTransparent(this);
        all_no_information = findViewById(R.id.all_no_information);
        the_project_end_refuse_return = findViewById(R.id.the_project_end_refuse_return);
        the_project_end_refuse_rv = findViewById(R.id.the_project_end_refuse_rv);

        the_project_end_refuse_ll1 = findViewById(R.id.the_project_end_refuse_ll1);
        the_project_end_refuse_ll2 = findViewById(R.id.the_project_end_refuse_ll2);
        the_project_end_refuse_ll3 = findViewById(R.id.the_project_end_refuse_ll3);
        the_project_end_refuse_ll4 = findViewById(R.id.the_project_end_refuse_ll4);
        the_project_end_refuse_ll5 = findViewById(R.id.the_project_end_refuse_ll5);
        the_project_end_refuse_ll6 = findViewById(R.id.the_project_end_refuse_ll6);
        the_project_end_refuse_ll7 = findViewById(R.id.the_project_end_refuse_ll7);
        the_project_end_refuse_ll8 = findViewById(R.id.the_project_end_refuse_ll8);

        initData(1);

        the_project_end_refuse_return.setOnClickListener(this);
        the_project_end_refuse_ll1.setOnClickListener(this);
        the_project_end_refuse_ll3.setOnClickListener(this);
        the_project_end_refuse_ll5.setOnClickListener(this);
        the_project_end_refuse_ll7.setOnClickListener(this);
    }

    @SingleClick(1000)
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.the_project_end_refuse_return:
                finish();
                break;
            case R.id.the_project_end_refuse_ll1:
                the_project_end_refuse_ll2.setVisibility(View.VISIBLE);
                the_project_end_refuse_ll4.setVisibility(View.GONE);
                the_project_end_refuse_ll6.setVisibility(View.GONE);
                the_project_end_refuse_ll8.setVisibility(View.GONE);
                initData(1);

                break;
            case R.id.the_project_end_refuse_ll3:
                the_project_end_refuse_ll2.setVisibility(View.GONE);
                the_project_end_refuse_ll4.setVisibility(View.VISIBLE);
                the_project_end_refuse_ll6.setVisibility(View.GONE);
                the_project_end_refuse_ll8.setVisibility(View.GONE);
                initData(2);

                break;
            case R.id.the_project_end_refuse_ll5:
                the_project_end_refuse_ll2.setVisibility(View.GONE);
                the_project_end_refuse_ll4.setVisibility(View.GONE);
                the_project_end_refuse_ll6.setVisibility(View.VISIBLE);
                the_project_end_refuse_ll8.setVisibility(View.GONE);
                initData(3);

                break;
            case R.id.the_project_end_refuse_ll7:
                the_project_end_refuse_ll2.setVisibility(View.GONE);
                the_project_end_refuse_ll4.setVisibility(View.GONE);
                the_project_end_refuse_ll6.setVisibility(View.GONE);
                the_project_end_refuse_ll8.setVisibility(View.VISIBLE);
                initData(4);

                break;
        }

    }

    @SuppressLint("WrongConstant")
    private void initData(int position) {
        adapter = new InitiatedAdapter();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        the_project_end_refuse_rv.setLayoutManager(manager);

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<InitiatedBean> examinelistBean = fzbInterface.getExaminelistBean(FinalContents.getUserID(), "2", position + "","1000");
        examinelistBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<InitiatedBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(InitiatedBean examinelistBean) {
                        rows = examinelistBean.getData().getRows();
                        if (rows.size() != 0) {
                            all_no_information.setVisibility(View.GONE);
                            the_project_end_refuse_rv.setVisibility(View.VISIBLE);
                            adapter.setRows(rows);
                            adapter.setOnItemClick(RefuseTheProjectEndActivity.this);
                            the_project_end_refuse_rv.setAdapter(adapter);
                        }else {
                            all_no_information.setVisibility(View.VISIBLE);
                            the_project_end_refuse_rv.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        all_no_information.setVisibility(View.VISIBLE);
                        the_project_end_refuse_rv.setVisibility(View.GONE);
                        Log.i("MyCL", "拒绝记录错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public void Item(int position) {
        FinalContents.setPreparationId(rows.get(position).getPreparationId());
        FinalContents.setCustomerID(rows.get(position).getCustomerId());
        FinalContents.setStatus(rows.get(position).getStatus());
        Intent intent = new Intent(this, InitiatedActivity.class);
        FinalContents.setCustomerID(rows.get(position).getCustomerId());
        startActivity(intent);

    }
}
