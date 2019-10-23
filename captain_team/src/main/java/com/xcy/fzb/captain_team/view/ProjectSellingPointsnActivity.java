package com.xcy.fzb.captain_team.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzb.captain_team.R;
import com.xcy.fzb.captain_team.adapter.SellingPointsAdapter;
import com.xcy.fzb.captain_team.api.FinalContents;
import com.xcy.fzb.captain_team.database.SellingPointsBean;
import com.xcy.fzb.captain_team.persente.StatusBar;
import com.xcy.fzb.captain_team.service.MyService;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


//TODO 项目卖点
public class ProjectSellingPointsnActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView project_selling_rv;
    LinearLayout project_selling_return;
    SellingPointsAdapter sellingPointsAdapter = new SellingPointsAdapter();
    private List<SellingPointsBean.DataBean.RowsBean> rows;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_selling_pointsn);
        StatusBar.makeStatusBarTransparent(this);

        initView();

    }

    @SuppressLint("WrongConstant")
    private void initView() {

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        project_selling_rv = findViewById(R.id.project_selling_rv);
        project_selling_return = findViewById(R.id.project_selling_return);

        project_selling_return.setOnClickListener(this);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        project_selling_rv.setLayoutManager(manager);


        initData();

    }

    private void initData() {

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        builder.addConverterFactory(GsonConverterFactory.create());
        Retrofit build = builder.build();

        MyService myService = build.create(MyService.class);
        final Observable<SellingPointsBean> sellingPoints = myService.getSellingPoints("0", FinalContents.getUserID(), FinalContents.getProjectID());
        sellingPoints.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SellingPointsBean>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SellingPointsBean sellingPointsBean) {
                        rows = sellingPointsBean.getData().getRows();

                        Log.i("MyCL", "集合长度" + rows.size());

                        sellingPointsAdapter.setRows(rows);

                        project_selling_rv.setAdapter(sellingPointsAdapter);

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

        switch (view.getId()) {
            case R.id.project_selling_return:
                finish();
                break;
        }


    }

}
