package com.xcy.fzbcity.captain_team.view;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.modle.TeamLeaderAmountBean;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.view.AllActivity;
import com.xcy.fzbcity.captain_team.adapter.CommissionDetailsAdapter;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CommissionDetailsActivity extends AllActivity {

    private RelativeLayout activity_commission_details_return;
    private EditText activity_commission_details_search;
    private RecyclerView activity_commission_details_rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commission_details);
        initfvb();
    }

    private void initfvb(){
        activity_commission_details_return = findViewById(R.id.activity_commission_details_return);
        activity_commission_details_search = findViewById(R.id.activity_commission_details_search);
        activity_commission_details_rv = findViewById(R.id.activity_commission_details_rv);
        activity_commission_details_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        activity_commission_details_search.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (KeyEvent.KEYCODE_ENTER == i && KeyEvent.ACTION_DOWN == keyEvent.getAction()) {
                    initData();
                    Log.i("走","走");
                    return true;
                }
                Log.i("走","没走");
                return false;
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
        Observable<TeamLeaderAmountBean> clientCommissions = fzbInterface.getTeamLeaderAmount(FinalContents.getAgentId(),activity_commission_details_search.getText().toString());
        clientCommissions.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TeamLeaderAmountBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TeamLeaderAmountBean teamLeaderAmountBean) {
                        List<TeamLeaderAmountBean.DataBean> list = teamLeaderAmountBean.getData();
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CommissionDetailsActivity.this);
                        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        activity_commission_details_rv.setLayoutManager(linearLayoutManager);
                        CommissionDetailsAdapter commissionDetailsAdapter = new CommissionDetailsAdapter(list);
                        activity_commission_details_rv.setAdapter(commissionDetailsAdapter);
                        commissionDetailsAdapter.notifyDataSetChanged();
                        Log.i("走","走了");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("走","没走了");
                        Log.i("MyCL", "销售详情页佣金数据:" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
