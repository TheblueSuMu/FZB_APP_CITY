package com.xcy.fzb.all.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.R;
import com.xcy.fzb.all.adapter.PointsAdapter;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.modle.SellingPointsBean;
import com.xcy.fzb.all.persente.StatusBar;
import com.xcy.fzb.all.service.MyService;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


//TODO 项目海报
public class ProjectPointsActivity extends AllActivity implements View.OnClickListener {

    RecyclerView points_rv;
    LinearLayout points_return;
    PointsAdapter pointsAdapter;
    private List<SellingPointsBean.DataBean.RowsBean> rows;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_points);


        initView();

    }

    @SuppressLint("WrongConstant")
    private void initView() {

        StatusBar.makeStatusBarTransparent(this);

        points_return = findViewById(R.id.points_return);
        points_rv = findViewById(R.id.points_rv);

        points_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        points_rv.setLayoutManager(manager);
        initData();
    }

    private void initData() {

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        builder.addConverterFactory(GsonConverterFactory.create());
        Retrofit build = builder.build();
        MyService myService = build.create(MyService.class);
        final Observable<SellingPointsBean> sellingPoints = myService.getSellingPoints("1", FinalContents.getUserID(), FinalContents.getProjectID());
        sellingPoints.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SellingPointsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SellingPointsBean sellingPointsBean) {
                        rows = sellingPointsBean.getData().getRows();
                        pointsAdapter = new PointsAdapter();
                        pointsAdapter.setRows(rows);
                        points_rv.setAdapter(pointsAdapter);

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "项目卖点错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public void onClick(View view) {

    }

}
