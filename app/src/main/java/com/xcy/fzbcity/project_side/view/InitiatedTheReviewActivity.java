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
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.modle.InitiatedBean;
import com.xcy.fzbcity.all.modle.MyExamineNumBean;
import com.xcy.fzbcity.all.persente.SingleClick;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.CommonUtil;
import com.xcy.fzbcity.all.utils.ToastUtil;
import com.xcy.fzbcity.all.view.AllActivity;
import com.xcy.fzbcity.project_side.adapter.InitiatedAdapter;

import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InitiatedTheReviewActivity extends AllActivity implements View.OnClickListener {
    PtrClassicFrameLayout ptrClassicFrameLayout;
    RelativeLayout initiated_the_review_return;
    TextView initiated_the_review_tv;
    LinearLayout initiated_the_review_ll1;
    LinearLayout initiated_the_review_ll2;
    LinearLayout initiated_the_review_ll3;
    LinearLayout initiated_the_review_ll4;
    LinearLayout initiated_the_review_ll5;
    LinearLayout initiated_the_review_ll6;
    LinearLayout initiated_the_review_ll7;
    LinearLayout initiated_the_review_ll8;
    RecyclerView initiated_the_review_rv;

    InitiatedAdapter adapter;
    private List<InitiatedBean.DataBean.RowsBean> rows;
    private ImageView all_no_information;
    private TextView initiated_the_review_tv1;
    private TextView initiated_the_review_tv2;
    private TextView initiated_the_review_tv3;
    private TextView initiated_the_review_tv4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_side_activity_initiated_the_review);
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
        initiated_the_review_return = findViewById(R.id.initiated_the_review_return);
        initiated_the_review_tv = findViewById(R.id.initiated_the_review_tv);
        initiated_the_review_ll1 = findViewById(R.id.initiated_the_review_ll1);
        initiated_the_review_ll2 = findViewById(R.id.initiated_the_review_ll2);
        initiated_the_review_ll3 = findViewById(R.id.initiated_the_review_ll3);
        initiated_the_review_ll4 = findViewById(R.id.initiated_the_review_ll4);
        initiated_the_review_ll5 = findViewById(R.id.initiated_the_review_ll5);
        initiated_the_review_ll6 = findViewById(R.id.initiated_the_review_ll6);
        initiated_the_review_ll7 = findViewById(R.id.initiated_the_review_ll7);
        initiated_the_review_ll8 = findViewById(R.id.initiated_the_review_ll8);
        initiated_the_review_rv = findViewById(R.id.initiated_the_review_rv);

        initiated_the_review_tv1 = findViewById(R.id.initiated_the_review_tv1);
        initiated_the_review_tv2 = findViewById(R.id.initiated_the_review_tv2);
        initiated_the_review_tv3 = findViewById(R.id.initiated_the_review_tv3);
        initiated_the_review_tv4 = findViewById(R.id.initiated_the_review_tv4);

        initiated_the_review_return.setOnClickListener(this);
        initiated_the_review_tv.setOnClickListener(this);
        initiated_the_review_ll1.setOnClickListener(this);
        initiated_the_review_ll3.setOnClickListener(this);
        initiated_the_review_ll5.setOnClickListener(this);
        initiated_the_review_ll7.setOnClickListener(this);

        ptrClassicFrameLayout = (PtrClassicFrameLayout) findViewById(R.id.store_house_ptr_frame_15);
        ptrClassicFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ptrClassicFrameLayout.refreshComplete();
                        ptrClassicFrameLayout.setLastUpdateTimeKey("2017-2-10");
                        if (initiated_the_review_ll2.getVisibility() == View.VISIBLE) {
                            initData(1);
                        } else if (initiated_the_review_ll4.getVisibility() == View.VISIBLE) {
                            initData(2);
                        } else if (initiated_the_review_ll6.getVisibility() == View.VISIBLE) {
                            initData(3);
                        } else if (initiated_the_review_ll8.getVisibility() == View.VISIBLE) {
                            initData(4);
                        }
                    }
                }, 1000);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });

        initRead();
    }

    @SingleClick(1000)
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
//            TODO 返回上一层
            case R.id.initiated_the_review_return:
                finish();
                break;
            //TODO 拒绝记录
            case R.id.initiated_the_review_tv:
                Intent intent = new Intent(InitiatedTheReviewActivity.this, RefuseTheProjectEndActivity.class);
                FinalContents.setJJ("我发起的审核");
                startActivity(intent);
                break;
            //TODO 退筹
            case R.id.initiated_the_review_ll1:

                initData(1);
                initiated_the_review_ll2.setVisibility(View.VISIBLE);
                initiated_the_review_ll4.setVisibility(View.GONE);
                initiated_the_review_ll6.setVisibility(View.GONE);
                initiated_the_review_ll8.setVisibility(View.GONE);
                break;
            //            TODO 调单
            case R.id.initiated_the_review_ll3:

                initData(2);
                initiated_the_review_ll2.setVisibility(View.GONE);
                initiated_the_review_ll4.setVisibility(View.VISIBLE);
                initiated_the_review_ll6.setVisibility(View.GONE);
                initiated_the_review_ll8.setVisibility(View.GONE);
                break;
            //            TODO 退单
            case R.id.initiated_the_review_ll5:

                initData(3);
                initiated_the_review_ll2.setVisibility(View.GONE);
                initiated_the_review_ll4.setVisibility(View.GONE);
                initiated_the_review_ll6.setVisibility(View.VISIBLE);
                initiated_the_review_ll8.setVisibility(View.GONE);
                break;
            //            TODO 成交
            case R.id.initiated_the_review_ll7:

                initData(4);
                initiated_the_review_ll2.setVisibility(View.GONE);
                initiated_the_review_ll4.setVisibility(View.GONE);
                initiated_the_review_ll6.setVisibility(View.GONE);
                initiated_the_review_ll8.setVisibility(View.VISIBLE);
                break;
        }

    }

    @SuppressLint("WrongConstant")
    private void initData(int position) {

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        initiated_the_review_rv.setLayoutManager(manager);
        adapter = new InitiatedAdapter();

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<InitiatedBean> userMessage = fzbInterface.getMyExaminelist(FinalContents.getUserID(), "1",position+"","1000");
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<InitiatedBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(InitiatedBean initiatedBean) {
                        rows = initiatedBean.getData().getRows();
                        if (rows.size() != 0) {
                            all_no_information.setVisibility(View.GONE);
                            initiated_the_review_rv.setVisibility(View.VISIBLE);
                            adapter.setRows(rows);
                            adapter.setOnItemClick(new InitiatedAdapter.OnItemClick() {
                                @Override
                                public void Item(int position) {
                                    Intent intent = new Intent(InitiatedTheReviewActivity.this, InitiatedActivity.class);
                                    FinalContents.setPreparationId(rows.get(position).getPreparationId());
                                    startActivity(intent);
                                }
                            });
                            initiated_the_review_rv.setAdapter(adapter);
                        }else {
                            all_no_information.setVisibility(View.VISIBLE);
                            initiated_the_review_rv.setVisibility(View.GONE);
                        }
                        initRead();
                    }

                    @Override
                    public void onError(Throwable e) {
                        all_no_information.setVisibility(View.VISIBLE);
                        initiated_the_review_rv.setVisibility(View.GONE);
                        Log.i("列表数据获取错误", "错误" + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initRead(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<MyExamineNumBean> userMessage = fzbInterface.getMyExamineNum(FinalContents.getUserID(), "1","1000");
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MyExamineNumBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(MyExamineNumBean initiatedBean) {
                        if (initiatedBean.getData().getWithdraw() == 0) {
                            initiated_the_review_tv1.setText("退筹");
                        }else {
                            initiated_the_review_tv1.setText("退筹("+initiatedBean.getData().getWithdraw()+")");
                        }
                        if (initiatedBean.getData().getOrderSheet() == 0) {
                            initiated_the_review_tv2.setText("调单");
                        }else {
                            initiated_the_review_tv2.setText("调单("+initiatedBean.getData().getOrderSheet()+")");
                        }
                        if (initiatedBean.getData().getDocuments() == 0) {
                            initiated_the_review_tv3.setText("退单");
                        }else {
                            initiated_the_review_tv3.setText("退单("+initiatedBean.getData().getDocuments()+")");
                        }
                        if (initiatedBean.getData().getTrade() == 0) {
                            initiated_the_review_tv4.setText("成交");
                        }else {
                            initiated_the_review_tv4.setText("成交("+initiatedBean.getData().getTrade()+")");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        all_no_information.setVisibility(View.VISIBLE);
                        initiated_the_review_rv.setVisibility(View.GONE);
                        Log.i("列表数据获取错误", "错误" + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (initiated_the_review_ll2.getVisibility() == View.VISIBLE) {
            initData(1);
        } else if (initiated_the_review_ll4.getVisibility() == View.VISIBLE) {
            initData(2);
        } else if (initiated_the_review_ll6.getVisibility() == View.VISIBLE) {
            initData(3);
        } else if (initiated_the_review_ll8.getVisibility() == View.VISIBLE) {
            initData(4);
        }
    }
}
