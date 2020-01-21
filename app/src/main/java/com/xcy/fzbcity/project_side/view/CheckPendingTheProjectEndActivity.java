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
import com.xcy.fzbcity.all.api.NewlyIncreased;
import com.xcy.fzbcity.all.modle.CheckPendingBean;
import com.xcy.fzbcity.all.modle.ToAuditNumBean;
import com.xcy.fzbcity.all.persente.SingleClick;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.CommonUtil;
import com.xcy.fzbcity.all.utils.ToastUtil;
import com.xcy.fzbcity.all.view.AllActivity;
import com.xcy.fzbcity.project_side.adapter.CheckPendingTheProjectEndAdapter;

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

//TODO 待我审核
public class CheckPendingTheProjectEndActivity extends AllActivity implements View.OnClickListener{

    PtrClassicFrameLayout mPtrClassicFrameLayout;

    RelativeLayout check_pending_the_project_end_return;
    TextView check_pending_the_project_end_tv;
    LinearLayout check_pending_the_project_end_ll1;
    LinearLayout check_pending_the_project_end_ll2;
    LinearLayout check_pending_the_project_end_ll3;
    LinearLayout check_pending_the_project_end_ll4;
    LinearLayout check_pending_the_project_end_ll5;
    LinearLayout check_pending_the_project_end_ll6;
    LinearLayout check_pending_the_project_end_ll7;
    LinearLayout check_pending_the_project_end_ll8;
    RecyclerView check_pending_the_project_end_rv;
    CheckPendingTheProjectEndAdapter adapter;
    private List<CheckPendingBean.DataBean.RowsBean> rows;
    private Intent intent;
    private ImageView all_no_information;
    private TextView check_pending_the_project_end_tv1;
    private TextView check_pending_the_project_end_tv2;
    private TextView check_pending_the_project_end_tv3;
    private TextView check_pending_the_project_end_tv4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_side_activity_check_pending_the_project_end);
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

        mPtrClassicFrameLayout = findViewById(R.id.store_house_ptr_frame_1);
        all_no_information = findViewById(R.id.all_no_information);
        check_pending_the_project_end_return = findViewById(R.id.check_pending_the_project_end_return);
        check_pending_the_project_end_tv = findViewById(R.id.check_pending_the_project_end_tv);
        check_pending_the_project_end_ll1 = findViewById(R.id.check_pending_the_project_end_ll1);
        check_pending_the_project_end_ll2 = findViewById(R.id.check_pending_the_project_end_ll2);
        check_pending_the_project_end_ll3 = findViewById(R.id.check_pending_the_project_end_ll3);
        check_pending_the_project_end_ll4 = findViewById(R.id.check_pending_the_project_end_ll4);
        check_pending_the_project_end_ll5 = findViewById(R.id.check_pending_the_project_end_ll5);
        check_pending_the_project_end_ll6 = findViewById(R.id.check_pending_the_project_end_ll6);
        check_pending_the_project_end_ll7 = findViewById(R.id.check_pending_the_project_end_ll7);
        check_pending_the_project_end_ll8 = findViewById(R.id.check_pending_the_project_end_ll8);
        check_pending_the_project_end_rv = findViewById(R.id.check_pending_the_project_end_rv);

        check_pending_the_project_end_tv1 = findViewById(R.id.check_pending_the_project_end_tv1);
        check_pending_the_project_end_tv2 = findViewById(R.id.check_pending_the_project_end_tv2);
        check_pending_the_project_end_tv3 = findViewById(R.id.check_pending_the_project_end_tv3);
        check_pending_the_project_end_tv4 = findViewById(R.id.check_pending_the_project_end_tv4);


        check_pending_the_project_end_return.setOnClickListener(this);
        check_pending_the_project_end_tv.setOnClickListener(this);
        check_pending_the_project_end_ll1.setOnClickListener(this);
        check_pending_the_project_end_ll3.setOnClickListener(this);
        check_pending_the_project_end_ll5.setOnClickListener(this);
        check_pending_the_project_end_ll7.setOnClickListener(this);


        mPtrClassicFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPtrClassicFrameLayout.refreshComplete();
                        mPtrClassicFrameLayout.setLastUpdateTimeKey("2017-2-10");
                        if (check_pending_the_project_end_ll2.getVisibility() == View.VISIBLE) {
                            initData(1);
                        } else if (check_pending_the_project_end_ll4.getVisibility() == View.VISIBLE) {
                            initData(2);
                        } else if (check_pending_the_project_end_ll6.getVisibility() == View.VISIBLE) {
                            initData(3);
                        } else if (check_pending_the_project_end_ll8.getVisibility() == View.VISIBLE) {
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
    }

    @SingleClick(1000)
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
//            TODO 返回上一层
            case R.id.check_pending_the_project_end_return:
                finish();
                break;
            //            TODO 拒绝记录
            case R.id.check_pending_the_project_end_tv:
                Intent intent = new Intent(CheckPendingTheProjectEndActivity.this, CheckPendingTheProjectActivity.class);
                FinalContents.setJJ("待我审核");
                startActivity(intent);
                break;
            //            TODO 报备
            case R.id.check_pending_the_project_end_ll1:

                initData(1);

                check_pending_the_project_end_ll2.setVisibility(View.VISIBLE);
                check_pending_the_project_end_ll4.setVisibility(View.GONE);
                check_pending_the_project_end_ll6.setVisibility(View.GONE);
                check_pending_the_project_end_ll8.setVisibility(View.GONE);

                break;
            //            TODO 到访
            case R.id.check_pending_the_project_end_ll3:

                initData(2);

                check_pending_the_project_end_ll2.setVisibility(View.GONE);
                check_pending_the_project_end_ll4.setVisibility(View.VISIBLE);
                check_pending_the_project_end_ll6.setVisibility(View.GONE);
                check_pending_the_project_end_ll8.setVisibility(View.GONE);
                break;
            //            TODO 认筹
            case R.id.check_pending_the_project_end_ll5:

                initData(3);

                check_pending_the_project_end_ll2.setVisibility(View.GONE);
                check_pending_the_project_end_ll4.setVisibility(View.GONE);
                check_pending_the_project_end_ll6.setVisibility(View.VISIBLE);
                check_pending_the_project_end_ll8.setVisibility(View.GONE);

                break;
            //            TODO 成功
            case R.id.check_pending_the_project_end_ll7:

                initData(4);

                check_pending_the_project_end_ll2.setVisibility(View.GONE);
                check_pending_the_project_end_ll4.setVisibility(View.GONE);
                check_pending_the_project_end_ll6.setVisibility(View.GONE);
                check_pending_the_project_end_ll8.setVisibility(View.VISIBLE);

                break;
        }

    }

    @SuppressLint("WrongConstant")
    private void initData(int position) {
        String ProjectID = "";

        if (FinalContents.getDetails().equals("项目详情")) {
            ProjectID = FinalContents.getProjectID();
        }

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        check_pending_the_project_end_rv.setLayoutManager(manager);
        adapter = new CheckPendingTheProjectEndAdapter();

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<CheckPendingBean> userMessage = fzbInterface.getToAuditList(FinalContents.getUserID(),ProjectID,"1",position,"1000", NewlyIncreased.getTag(),NewlyIncreased.getStartDate(),NewlyIncreased.getEndDate());
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CheckPendingBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(CheckPendingBean checkPendingBean) {
                        rows = checkPendingBean.getData().getRows();
                        if (rows.size() != 0) {
                            all_no_information.setVisibility(View.GONE);
                            check_pending_the_project_end_rv.setVisibility(View.VISIBLE);
                            adapter.setRows(rows);
                            adapter.setOnItemClick(new CheckPendingTheProjectEndAdapter.OnItemClick() {
                                @Override
                                public void itemItem(int position) {
                                    intent = new Intent(CheckPendingTheProjectEndActivity.this, CheckPendingActivity.class);
                                    if (check_pending_the_project_end_ll2.getVisibility() == View.VISIBLE) {
                                        FinalContents.setNumS(1);
                                        intent.putExtra("name", "报备");
                                        intent.putExtra("Mycheck", "1");
                                    } else if (check_pending_the_project_end_ll4.getVisibility() == View.VISIBLE) {
                                        FinalContents.setNumS(2);
                                        intent.putExtra("name", "到访");
                                        intent.putExtra("Mycheck", "1");
                                    } else if (check_pending_the_project_end_ll6.getVisibility() == View.VISIBLE) {
                                        FinalContents.setNumS(3);
                                        intent.putExtra("name", "认筹");
                                        intent.putExtra("Mycheck", "2");
                                    } else if (check_pending_the_project_end_ll8.getVisibility() == View.VISIBLE) {
                                        FinalContents.setNumS(4);
                                        intent.putExtra("name", "成交");
                                        intent.putExtra("Mycheck", "3");
                                    }

                                    FinalContents.setPreparationId(rows.get(position).getPreparationId());
                                    FinalContents.setCustomerID(rows.get(position).getCustomerId());
                                    FinalContents.setStatus(rows.get(position).getStatus());
                                    startActivity(intent);
                                }
                            });
                            check_pending_the_project_end_rv.setAdapter(adapter);
                        }else {
                            all_no_information.setVisibility(View.VISIBLE);
                            check_pending_the_project_end_rv.setVisibility(View.GONE);
                        }
                        initRead();
                    }

                    @Override
                    public void onError(Throwable e) {
                        all_no_information.setVisibility(View.VISIBLE);
                        check_pending_the_project_end_rv.setVisibility(View.GONE);
                        Log.i("列表数据获取错误","错误"+e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    private void initRead(){
        String ProjectID = "";

        if (FinalContents.getDetails().equals("项目详情")) {
            ProjectID = FinalContents.getProjectID();
        }

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<ToAuditNumBean> userMessage = fzbInterface.getToAuditNum(FinalContents.getUserID(),ProjectID,"1","1000", NewlyIncreased.getTag(),NewlyIncreased.getStartDate(),NewlyIncreased.getEndDate());
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ToAuditNumBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(ToAuditNumBean toAuditNumBean) {
                        if (toAuditNumBean.getData().getReport() == 0) {
                            check_pending_the_project_end_tv1.setText("报备");
                        }else {
                            check_pending_the_project_end_tv1.setText("报备("+toAuditNumBean.getData().getReport()+")");
                        }
                        if (toAuditNumBean.getData().getAccess() == 0) {
                            check_pending_the_project_end_tv2.setText("到访");
                        }else {
                            check_pending_the_project_end_tv2.setText("到访("+toAuditNumBean.getData().getAccess()+")");
                        }
                        if (toAuditNumBean.getData().getEarnest() == 0) {
                            check_pending_the_project_end_tv3.setText("认筹");
                        }else {
                            check_pending_the_project_end_tv3.setText("认筹("+toAuditNumBean.getData().getEarnest()+")");
                        }
                        if (toAuditNumBean.getData().getTrade() == 0) {
                            check_pending_the_project_end_tv4.setText("成交");
                        }else {
                            check_pending_the_project_end_tv4.setText("成交("+toAuditNumBean.getData().getTrade()+")");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        all_no_information.setVisibility(View.VISIBLE);
                        check_pending_the_project_end_rv.setVisibility(View.GONE);
                        Log.i("列表数据获取错误","错误"+e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        if (FinalContents.getEndStart().equals("成功")) {
//            finish();
        }
        initData(FinalContents.getNumS());
    }
    @Override
    public void onResume() {
        super.onResume();
        FinalContents.setJuJue("待我审核");
        if (FinalContents.getNumS() == 1) {
            initData(1);
        } else if (FinalContents.getNumS() == 2) {
            initData(2);
        } else if (FinalContents.getNumS() == 3) {
            initData(3);
        } else if (FinalContents.getNumS() == 4) {
            initData(4);
        } else {
            initData(1);
        }
        initRead();
    }
}
