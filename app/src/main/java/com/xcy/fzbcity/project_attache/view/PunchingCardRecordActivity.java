package com.xcy.fzbcity.project_attache.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.modle.RecordBean;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.project_attache.adapter.PunchingCardAdapter;

public class PunchingCardRecordActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    RelativeLayout punching_card_record_return;
    RecyclerView punching_rv;
    PunchingCardAdapter adapter;
    EditText map_house_search;
    private SwipeRefreshLayout layout;


    int ifnum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_punching_card_record);

        initView();

    }

    private void initView() {

        StatusBar.makeStatusBarTransparent(this);

        punching_card_record_return = findViewById(R.id.punching_card_record_return);
        map_house_search = findViewById(R.id.map_house_search);
        punching_rv = findViewById(R.id.punching_rv);
        layout = findViewById(R.id.home_srl_S);
        layout.setOnRefreshListener(this);

        punching_card_record_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        punching_rv.setLayoutManager(manager);
        adapter = new PunchingCardAdapter();

        map_house_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if ((keyEvent != null && KeyEvent.KEYCODE_ENTER == keyEvent.getKeyCode() && KeyEvent.ACTION_DOWN == keyEvent.getAction())) {
                    if (ifnum == 0) {
                        ifnum = 1;
                        String s = map_house_search.getText().toString();
                        initData(s);
                    }
                    return true;
                }
                return false;
            }
        });

        initData("");
    }

    private void initData(String search) {

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        builder.addConverterFactory(GsonConverterFactory.create());
        Retrofit build = builder.build();
        MyService myService = build.create(MyService.class);
        Observable<RecordBean> financeBean = myService.getRecord(FinalContents.getUserID(), "", search, "2");
        financeBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RecordBean>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {

                    }

                    @Override
                    public void onNext(RecordBean recordBean) {
                        adapter.setRows(recordBean.getData().getRows());
                        punching_rv.setAdapter(adapter);
                        ifnum = 0;
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        Log.i("MyCL", "获取打卡记录错误信息：" + throwable.getMessage());
                        ifnum = 0;
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public void onRefresh() {
        if (layout.isRefreshing()) {//如果正在刷新
            initView();
            layout.setRefreshing(false);//取消刷新
        }
    }
}
