package com.xcy.fzbcity.project_attache.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.api.NewlyIncreased;
import com.xcy.fzbcity.all.database.CommissionListBean;
import com.xcy.fzbcity.all.modle.CommissionUpBean;
import com.xcy.fzbcity.all.persente.SingleClick;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.CommonUtil;
import com.xcy.fzbcity.all.utils.KeyUtils;
import com.xcy.fzbcity.all.utils.ToastUtil;
import com.xcy.fzbcity.all.view.AllActivity;
import com.xcy.fzbcity.project_attache.adapter.CommissionListAdapter;

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

public class CommissionActivity extends AllActivity implements View.OnClickListener {

    LinearLayout commission_return;

    TextView commission_tv1;
    TextView commission_tv2;
    TextView commission_tv3;

    RecyclerView commission_rv;

    LinearLayout commission_ll1;
    LinearLayout commission_ll2;
    LinearLayout commission_ll3;
    LinearLayout commission_ll4;
    LinearLayout commission_ll5;
    LinearLayout commission_ll6;

    CheckBox commission_cb;
    String ifCheckBox = "";

    EditText commission_et;
    private List<CommissionListBean.DataBean.RowsBean> rows;
    CommissionListAdapter adapter;
    private String s = "";
    private RelativeLayout myBrokerage_rl;
    private PtrClassicFrameLayout commission_ptrclass;
    String projecttype = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_attache_activity_commission);
        init_No_Network();
    }

    private void init_No_Network() {
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
            ToastUtil.showLongToast(CommissionActivity.this,"当前无网络，请检查网络后再进行登录");

        }
    }

    @SuppressLint("WrongConstant")
    private void initView() {
        StatusBar.makeStatusBarTransparent(this);
        commission_ptrclass = findViewById(R.id.commission_ptrclass);
        commission_return = findViewById(R.id.commission_return);
        commission_tv1 = findViewById(R.id.commission_tv1);
        commission_tv2 = findViewById(R.id.commission_tv2);
        commission_tv3 = findViewById(R.id.commission_tv3);
        commission_rv = findViewById(R.id.commission_rv);
        commission_ll1 = findViewById(R.id.commission_ll1);
        commission_ll2 = findViewById(R.id.commission_ll2);
        commission_ll3 = findViewById(R.id.commission_ll3);
        commission_ll4 = findViewById(R.id.commission_ll4);
        commission_ll5 = findViewById(R.id.commission_ll5);
        commission_ll6 = findViewById(R.id.commission_ll6);
        commission_et = findViewById(R.id.commission_et);
        commission_cb = findViewById(R.id.commission_cb);
        myBrokerage_rl = findViewById(R.id.myBrokerage_rl);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        commission_rv.setLayoutManager(manager);

        commission_return.setOnClickListener(this);
        commission_ll1.setOnClickListener(this);
        commission_ll3.setOnClickListener(this);
        commission_ll5.setOnClickListener(this);
        initDataUp();
        initData("1", s);
        commission_et.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                //当actionId == XX_SEND 或者 XX_DONE时都触发
                //或者event.getKeyCode == ENTER 且 event.getAction == ACTION_DOWN时也触发
                //注意，这是一定要判断event != null。因为在某些输入法上会返回null。
                if (actionId == EditorInfo.IME_ACTION_SEND || actionId == EditorInfo.IME_ACTION_DONE || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                    //处理事件
                    KeyUtils.hideKeyboard(commission_et);
                    s = commission_et.getText().toString();
                    initData(projecttype, s);
                    return true;
                }
                return false;
            }
        });

        commission_ptrclass.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        commission_ptrclass.refreshComplete();
                        commission_ptrclass.setLastUpdateTimeKey("2017-2-10");
                        commission_et.setText("");
                        s = commission_et.getText().toString();
                        initDataUp();
                        initData(projecttype, s);
                    }
                }, 1000);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });

        commission_cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (commission_cb.isChecked()) {
                    ifCheckBox = "0";
                    if (commission_ll2.getVisibility() == View.VISIBLE) {
                        initData("3", s);
                    } else if (commission_ll4.getVisibility() == View.VISIBLE) {
                        initData("2", s);
                    } else if (commission_ll6.getVisibility() == View.VISIBLE) {
                        initData("1", s);
                    }
                } else {
                    ifCheckBox = "";
                    if (commission_ll2.getVisibility() == View.VISIBLE) {
                        initData("3", s);
                    } else if (commission_ll4.getVisibility() == View.VISIBLE) {
                        initData("2", s);
                    } else if (commission_ll6.getVisibility() == View.VISIBLE) {
                        initData("1", s);
                    }
                }
            }
        });

    }

    private void initDataUp() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        builder.addConverterFactory(GsonConverterFactory.create());
        Retrofit build = builder.build();
        MyService myService = build.create(MyService.class);
        Observable<CommissionUpBean> commissionUpBeanObservable = myService.getcommissionUpBean(FinalContents.getUserID(), FinalContents.getCompanyId(), FinalContents.getStoreId(), FinalContents.getAgentId(), NewlyIncreased.getYJType(), NewlyIncreased.getYJstartDate(), NewlyIncreased.getYJendDate());
        commissionUpBeanObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CommissionUpBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CommissionUpBean commissionUpBean) {
                        commission_tv1.setText(commissionUpBean.getData().getTotalAmount());
                        commission_tv2.setText(commissionUpBean.getData().getAlreadyAmount());
                        commission_tv3.setText(commissionUpBean.getData().getNotAmount());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @SingleClick(1000)
    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.commission_return:
                finish();
                break;
            case R.id.commission_ll1:
                s = commission_et.getText().toString();
                commission_ll2.setVisibility(View.VISIBLE);
                commission_ll4.setVisibility(View.INVISIBLE);
                commission_ll6.setVisibility(View.INVISIBLE);
                projecttype = "3";
                initData("3", s);

                break;
            case R.id.commission_ll3:
                s = commission_et.getText().toString();
                commission_ll2.setVisibility(View.INVISIBLE);
                commission_ll4.setVisibility(View.VISIBLE);
                commission_ll6.setVisibility(View.INVISIBLE);
                projecttype = "2";
                initData("2", s);

                break;
            case R.id.commission_ll5:
                s = commission_et.getText().toString();
                commission_ll2.setVisibility(View.INVISIBLE);
                commission_ll4.setVisibility(View.INVISIBLE);
                commission_ll6.setVisibility(View.VISIBLE);
                projecttype = "1";
                initData("1", s);

                break;

        }

    }

    private void initData(String projectType, String search) {
        Log.i("专员佣金","佣金列表信息"+projectType);

        adapter = new CommissionListAdapter();

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        builder.addConverterFactory(GsonConverterFactory.create());
        Retrofit build = builder.build();
        MyService myService = build.create(MyService.class);
        Observable<CommissionListBean> commissionListBean = myService.getCommissionListBean(FinalContents.getUserID(), projectType, FinalContents.getCompanyId(), FinalContents.getStoreId(), FinalContents.getAgentId(), search, ifCheckBox, "1000", NewlyIncreased.getYJType(), NewlyIncreased.getYJstartDate(), NewlyIncreased.getYJendDate());
        commissionListBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CommissionListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CommissionListBean commissionListBean) {
                        rows = commissionListBean.getData().getRows();
                        if (rows.size() != 0) {
                            myBrokerage_rl.setVisibility(View.GONE);
                            commission_rv.setVisibility(View.VISIBLE);
                            adapter.setRows(rows);
                            commission_rv.setAdapter(adapter);
                        } else {
                            commission_rv.setVisibility(View.GONE);
                            myBrokerage_rl.setVisibility(View.VISIBLE);
                        }
                        hideInput();
                    }

                    @Override
                    public void onError(Throwable e) {
                        commission_rv.setVisibility(View.GONE);
                        myBrokerage_rl.setVisibility(View.VISIBLE);
                        Log.i("专员佣金","佣金列表错误信息"+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
