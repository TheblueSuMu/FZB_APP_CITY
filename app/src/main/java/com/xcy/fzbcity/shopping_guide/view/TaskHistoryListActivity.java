package com.xcy.fzbcity.shopping_guide.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.modle.TaskListBean;
import com.xcy.fzbcity.all.persente.MyLinearLayoutManager;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.CommonUtil;
import com.xcy.fzbcity.all.utils.ToastUtil;
import com.xcy.fzbcity.all.view.AllActivity;
import com.xcy.fzbcity.shopping_guide.adapter.TaskListAdapter;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TaskHistoryListActivity extends AllActivity {

    private RelativeLayout task_history_back;
    private RecyclerView task_history_rv;
    private ImageView all_no_information;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_guide_activity_task_history_list);
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

    private void initView(){
        all_no_information = findViewById(R.id.all_no_information);

        task_history_back = findViewById(R.id.task_history_back);
        task_history_rv = findViewById(R.id.task_history_rv);
        task_history_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        initData();
    }

    private void initData(){


        StatusBar.makeStatusBarTransparent(this);
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<TaskListBean> userMessage = fzbInterface.getRouteTimeList(FinalContents.getUserID(),"60","1000");
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TaskListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(TaskListBean taskListBean) {
                        if (taskListBean.getCode().equals("1")) {
                            TaskListBean.DataBean dataBean = taskListBean.getData();
                            List<TaskListBean.DataBean.RowsBean> rows = dataBean.getRows();
                            if (rows.size() != 0) {
                                all_no_information.setVisibility(View.GONE);
                                //在此处修改布局排列方向
                                task_history_rv.setVisibility(View.VISIBLE);
                                MyLinearLayoutManager layoutManager = new MyLinearLayoutManager(TaskHistoryListActivity.this);
                                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                                task_history_rv.setLayoutManager(layoutManager);
                                TaskListAdapter recyclerAdapter = new TaskListAdapter(rows);
                                task_history_rv.setAdapter(recyclerAdapter);
                                recyclerAdapter.notifyDataSetChanged();
                            }else {
                                all_no_information.setVisibility(View.VISIBLE);
                                task_history_rv.setVisibility(View.GONE);
                            }


                        }else {
                            task_history_rv.setVisibility(View.GONE);
                            all_no_information.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        task_history_rv.setVisibility(View.GONE);
                        all_no_information.setVisibility(View.VISIBLE);
                        Log.i("列表数据获取错误","错误"+e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
