package com.xcy.fzbcity.all.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.adapter.SpellingMassTimeAdaptewr;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.modle.SpellingMassTimeBean;
import com.xcy.fzbcity.all.persente.MyLinearLayoutManager;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.CommonUtil;
import com.xcy.fzbcity.all.utils.ToastUtil;

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

public class SpellingMassTimeActivity extends AllActivity {

    private RelativeLayout task_history_back;
    private RecyclerView task_history_rv;
    private List<SpellingMassTimeBean.DataBean.RowsBean> rows;
    private PtrClassicFrameLayout task_history_ptrclass;
    private ImageView all_no_information;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spelling_mass_time);
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
        task_history_ptrclass = findViewById(R.id.task_history_ptrclass);
        task_history_back = findViewById(R.id.task_history_back);
        all_no_information = findViewById(R.id.all_no_information_task_history);
        task_history_rv = findViewById(R.id.task_history_rv);
        task_history_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        initData();

        task_history_ptrclass.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        task_history_ptrclass.refreshComplete();
                        task_history_ptrclass.setLastUpdateTimeKey("2017-2-10");
                        initData();
                    }
                }, 1000);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });

    }

    private void initData() {
        Log.i("MyCL","FinalContents.getProjectID()" + FinalContents.getProjectID());
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<SpellingMassTimeBean> spellingMassTimeBean = fzbInterface.getSpellingMassTimeBean(FinalContents.getProjectID(),"1000");
        spellingMassTimeBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SpellingMassTimeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i("MyCL","onSubscribe");
                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(SpellingMassTimeBean taskListBean) {

                        rows = taskListBean.getData().getRows();
                        Log.i("MyCL", "拼团测试：" + rows.size());
                        if (rows.size() != 0) {
                            task_history_rv.setVisibility(View.VISIBLE);
                            all_no_information.setVisibility(View.GONE);
                            MyLinearLayoutManager layoutManager = new MyLinearLayoutManager(SpellingMassTimeActivity.this);
                            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                            task_history_rv.setLayoutManager(layoutManager);
                            SpellingMassTimeAdaptewr recyclerAdapter = new SpellingMassTimeAdaptewr();
                            recyclerAdapter.setRows(rows);
                            task_history_rv.setAdapter(recyclerAdapter);
                            recyclerAdapter.notifyDataSetChanged();
                        }else {
                            task_history_rv.setVisibility(View.GONE);
                            all_no_information.setVisibility(View.VISIBLE);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        task_history_rv.setVisibility(View.GONE);
                        all_no_information.setVisibility(View.VISIBLE);
                        Log.i("列表数据获取错误", "错误" + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
