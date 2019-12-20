package com.xcy.fzbcity.shopping_guide.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.fragment.AllFragment;
import com.xcy.fzbcity.all.modle.TaskListBean;
import com.xcy.fzbcity.all.persente.MyLinearLayoutManager;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.shopping_guide.adapter.TaskListAdapter;
import com.xcy.fzbcity.shopping_guide.view.TaskHistoryListActivity;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TaskFragment extends AllFragment {

    private View view;
    private ImageView task_history_img;
    private RecyclerView task_rv;
    private ImageView all_no_information;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.shopping_guide_fragment_task, null);
        return view;
    }

    private void initView(){
        task_history_img = view.findViewById(R.id.task_history_img);
        all_no_information = view.findViewById(R.id.all_no_information);
        task_rv = view.findViewById(R.id.task_rv);
        task_history_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), TaskHistoryListActivity.class);
                startActivity(intent);
            }
        });
        initData();
    }

    private void initData(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<TaskListBean> userMessage = fzbInterface.getRouteTimeList(FinalContents.getUserID(),"1","1000");
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
                                task_rv.setVisibility(View.VISIBLE);
                                MyLinearLayoutManager layoutManager = new MyLinearLayoutManager(view.getContext());
                                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                                task_rv.setLayoutManager(layoutManager);
                                TaskListAdapter recyclerAdapter = new TaskListAdapter(rows);
                                task_rv.setAdapter(recyclerAdapter);
                                recyclerAdapter.notifyDataSetChanged();
                            }else {
                                all_no_information.setVisibility(View.VISIBLE);
                                task_rv.setVisibility(View.GONE);
                            }
                        }else {
                            all_no_information.setVisibility(View.VISIBLE);
                            task_rv.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        all_no_information.setVisibility(View.VISIBLE);
                        task_rv.setVisibility(View.GONE);
                        Log.i("列表数据获取错误","错误"+e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
        initView();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(hidden){
            //TODO now visible to user 不显示fragment
        } else {
            onResume();
            //TODO now invisible to user 显示fragment
        }
    }
}
