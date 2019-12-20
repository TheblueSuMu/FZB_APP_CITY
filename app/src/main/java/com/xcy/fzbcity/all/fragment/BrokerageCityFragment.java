package com.xcy.fzbcity.all.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.adapter.BrokerageAdapter;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.database.BorkerageDownBean;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.service.MyService;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class BrokerageCityFragment extends Fragment {

    RecyclerView brokerage_city_rv;
    BrokerageAdapter adapter;
    private ImageView all_no_information;

    public BrokerageCityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        StatusBar.makeStatusBarTransparent(getActivity());
        return inflater.inflate(R.layout.fragment_brokerage_city, container, false);
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("MyCL", "城市房产进入：");
        brokerage_city_rv = getActivity().findViewById(R.id.brokerage_city_rv);

        all_no_information = getActivity().findViewById(R.id.all_no_information);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        brokerage_city_rv.setLayoutManager(manager);

        initData();

    }

    private void initData() {
        Log.i("MyCL", "城市房产数据类进入：");
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<BorkerageDownBean> brokerageDown = fzbInterface.getBrokerageDown(FinalContents.getUserID() + "", "1","","1000");
        brokerageDown.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BorkerageDownBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BorkerageDownBean borkerageDownBean) {
                        List<BorkerageDownBean.DataBean.RowsBean> rows = borkerageDownBean.getData().getRows();
                        if (rows.size() != 0) {
                            all_no_information.setVisibility(View.GONE);
                            brokerage_city_rv.setVisibility(View.VISIBLE);
                            Log.i("MyCL", "城市房产数据类集合长度：" + rows.size());
                            adapter = new BrokerageAdapter();
                            adapter.setRows(rows);
                            brokerage_city_rv.setAdapter(adapter);
                        }else {
                            all_no_information.setVisibility(View.VISIBLE);
                            brokerage_city_rv.setVisibility(View.GONE);
                        }


                    }

                    @Override
                    public void onError(Throwable e) {
                        all_no_information.setVisibility(View.VISIBLE);
                        brokerage_city_rv.setVisibility(View.GONE);
                        Log.i("MyCL", "我的佣金下半部（城市）错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
