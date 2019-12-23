package com.xcy.fzbcity.all.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.adapter.BrokerageAdapter;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.database.BorkerageDownBean;
import com.xcy.fzbcity.all.database.BorkerageUpBean;
import com.xcy.fzbcity.all.persente.SingleClick;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.CommonUtil;
import com.xcy.fzbcity.all.utils.ToastUtil;

import java.util.ArrayList;
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


//TODO 我的佣金
public class MyBrokerageActivity extends AllActivity implements View.OnClickListener {


    LinearLayout myBrokerageImg;
    TextView myBrokerageTotalText;
    TextView myBrokerageMbText;
    TextView myBrokerageCaText;
    TextView myBrokerageWjText;
    LinearLayout myBrokerageLjText;
    LinearLayout myBrokerageHwText;
    LinearLayout myBrokerageCsText;
    LinearLayout myBrokerageLj;
    LinearLayout myBrokerageHw;
    LinearLayout myBrokerageCs;

    RecyclerView my_brokerage_rv;
    BrokerageAdapter adapter;
    RelativeLayout myBrokerage_rl;

    EditText my_brokerage_et;
    private List<BorkerageDownBean.DataBean.RowsBean> rows;
    private String s;
    int isnum = 0;
    private PtrClassicFrameLayout my_brokerage_ptrclass;
    String prokecttype = "3";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_brokerage);
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

    public void initView() {

        StatusBar.makeStatusBarTransparent(this);
        my_brokerage_ptrclass = findViewById(R.id.my_brokerage_ptrclass);
        myBrokerageImg = findViewById(R.id.my_brokerage_img);
        myBrokerageTotalText = findViewById(R.id.my_brokerage_total_text);
        myBrokerageMbText = findViewById(R.id.my_brokerage_mb_text);
        myBrokerageCaText = findViewById(R.id.my_brokerage_ca_text);
        myBrokerageWjText = findViewById(R.id.my_brokerage_wj_text);
        myBrokerageLjText = findViewById(R.id.my_brokerage_lj_text);
        myBrokerageHwText = findViewById(R.id.my_brokerage_hw_text);
        myBrokerageCsText = findViewById(R.id.my_brokerage_cs_text);
        myBrokerageCs = findViewById(R.id.my_brokerage_cs);
        myBrokerageLj = findViewById(R.id.my_brokerage_lj);
        myBrokerageHw = findViewById(R.id.my_brokerage_hw);

        my_brokerage_et = findViewById(R.id.my_brokerage_et);
        my_brokerage_rv = findViewById(R.id.my_brokerage_rv);
        myBrokerage_rl = findViewById(R.id.myBrokerage_rl);

//        fragmentManager = getSupportFragmentManager();
//        transaction = fragmentManager.beginTransaction();
//        transaction.replace(R.id.my_brokerage_fl, brokerageCityFragment);
//        transaction.commit();

        myBrokerageImg.setOnClickListener(this);
        myBrokerageCsText.setOnClickListener(this);
        myBrokerageLjText.setOnClickListener(this);
        myBrokerageHwText.setOnClickListener(this);

        my_brokerage_et.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (KeyEvent.KEYCODE_ENTER == i && KeyEvent.ACTION_DOWN == keyEvent.getAction()) {
                    //处理事件
                    if (isnum == 0) {
                        s = my_brokerage_et.getText().toString();
//                        if (myBrokerageLj.getVisibility() == View.VISIBLE) {
//                            initDataDown("3", s);
//                        } else if (myBrokerageHw.getVisibility() == View.VISIBLE) {
//                            initDataDown("2", s);
//                        } else if (myBrokerageCs.getVisibility() == View.VISIBLE) {
                            initDataDown("1", s);
//                        }
                        isnum = 1;
                    }
                    return true;
                }
                return false;
            }

        });

        my_brokerage_ptrclass.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        my_brokerage_ptrclass.refreshComplete();
                        my_brokerage_ptrclass.setLastUpdateTimeKey("2017-2-10");
                        initDataUp();
                        my_brokerage_et.setText("");
                        s = my_brokerage_et.getText().toString();
//                        if (myBrokerageLj.getVisibility() == View.VISIBLE) {
//                            initDataDown("3", s);
//                        } else if (myBrokerageHw.getVisibility() == View.VISIBLE) {
//                            initDataDown("2", s);
//                        } else if (myBrokerageCs.getVisibility() == View.VISIBLE) {
                            initDataDown("1", s);
//                        }
                    }
                }, 1000);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });

        initDataUp();
        s = my_brokerage_et.getText().toString();
        initDataDown("1", s);

    }

    private void initDataUp() {

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<BorkerageUpBean> test = fzbInterface.getBrokerageUp(FinalContents.getUserID());
        test.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BorkerageUpBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BorkerageUpBean borkerageUpBean) {
                        BorkerageUpBean.DataBean data = borkerageUpBean.getData();
                        myBrokerageTotalText.setText(data.getTotalAmount() + "");
                        myBrokerageMbText.setText(data.getSumCount() + "");
                        myBrokerageCaText.setText(data.getAlreadyAmount() + "");
                        myBrokerageWjText.setText(data.getNotAmount() + "");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "我的佣金上部" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    @SingleClick(1000)
    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.my_brokerage_img) {//返回上一级
            finish();
        } else if (id == R.id.my_brokerage_cs_text) {//城市房产
            Log.i("MyCL", "1");
            s = my_brokerage_et.getText().toString();
            myBrokerageLj.setVisibility(View.GONE);
            myBrokerageHw.setVisibility(View.GONE);
            myBrokerageCs.setVisibility(View.VISIBLE);
            initDataDown("1", s);
//            fragmentManager = getSupportFragmentManager();
//            transaction = fragmentManager.beginTransaction();
//            transaction.replace(R.id.my_brokerage_fl, brokerageCityFragment);
//            transaction.commit();
        } else if (id == R.id.my_brokerage_lj_text) {//旅居房产
            s = my_brokerage_et.getText().toString();
            myBrokerageLj.setVisibility(View.VISIBLE);
            myBrokerageHw.setVisibility(View.GONE);
            myBrokerageCs.setVisibility(View.GONE);
            Log.i("MyCL", "3");
            initDataDown("3", s);
            prokecttype = "3";
//
//            fragmentManager = getSupportFragmentManager();
//            transaction = fragmentManager.beginTransaction();
//            transaction.replace(R.id.my_brokerage_fl, sojournFragment);
//            transaction.commit();
        } else if (id == R.id.my_brokerage_hw_text) {//海外房产
            s = my_brokerage_et.getText().toString();
            Log.i("MyCL", "2");
            myBrokerageHw.setVisibility(View.VISIBLE);
            myBrokerageLj.setVisibility(View.GONE);
            myBrokerageCs.setVisibility(View.GONE);
            initDataDown("2", s);
            prokecttype = "2";
//            fragmentManager = getSupportFragmentManager();
//            transaction = fragmentManager.beginTransaction();
//            transaction.replace(R.id.my_brokerage_fl, overseasFragment);
//            transaction.commit();
        }

    }


    @SuppressLint("WrongConstant")
    private void initDataDown(String projectType, String str) {

        adapter = new BrokerageAdapter();
        rows = new ArrayList<>();
//        rows.clear();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        my_brokerage_rv.setLayoutManager(manager);

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Log.i("MyCL", "projectType：" + projectType + "str：" + str);
        Observable<BorkerageDownBean> test = fzbInterface.getBrokerageDown(FinalContents.getUserID(), projectType, str,"1000");
        test.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BorkerageDownBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BorkerageDownBean borkerageDownBean) {
                        try {
                            rows = borkerageDownBean.getData().getRows();
                            if (rows.size() != 0) {
                                my_brokerage_rv.setVisibility(View.VISIBLE);
                                myBrokerage_rl.setVisibility(View.GONE);
                                adapter.setRows(rows);
                                my_brokerage_rv.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                            }else {
                                my_brokerage_rv.setVisibility(View.GONE);
                                myBrokerage_rl.setVisibility(View.VISIBLE);
                            }
                            isnum = 0;
                            hideInput();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "我的佣金下半部（海外）" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
