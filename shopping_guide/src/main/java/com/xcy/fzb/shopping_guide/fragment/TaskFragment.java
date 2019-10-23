package com.xcy.fzb.shopping_guide.fragment;

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
import com.xcy.fzb.shopping_guide.R;
import com.xcy.fzb.shopping_guide.adapter.TaskListAdapter;
import com.xcy.fzb.shopping_guide.api.FinalContents;
import com.xcy.fzb.shopping_guide.modle.TaskListBean;
import com.xcy.fzb.shopping_guide.persente.MyLinearLayoutManager;
import com.xcy.fzb.shopping_guide.service.MyService;
import com.xcy.fzb.shopping_guide.view.TaskHistoryListActivity;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TaskFragment extends AllFragment{

    private View view;
    private ImageView task_history_img;
    private RecyclerView task_rv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_task, null);
        initView();
        return view;
    }

    private void initView(){
        task_history_img = view.findViewById(R.id.task_history_img);
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
        Observable<TaskListBean> userMessage = fzbInterface.getRouteTimeList(FinalContents.getUserID(),"1");
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
                            //在此处修改布局排列方向
                            task_rv.setVisibility(View.VISIBLE);
                            MyLinearLayoutManager layoutManager = new MyLinearLayoutManager(view.getContext());
                            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                            task_rv.setLayoutManager(layoutManager);
                            TaskListAdapter recyclerAdapter = new TaskListAdapter(rows);
                            task_rv.setAdapter(recyclerAdapter);
                            recyclerAdapter.notifyDataSetChanged();

                        }else {
                            task_rv.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("列表数据获取错误","错误"+e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
