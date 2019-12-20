package com.xcy.fzbcity.all.fragment;


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
 * 旅居
 */
public class SojournFragment extends Fragment {

    RecyclerView sojourn_rv;
    BrokerageAdapter adapter;
    private ImageView all_no_information;

    public SojournFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sojourn, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        sojourn_rv = getActivity().findViewById(R.id.sojourn_rv);
        all_no_information = getActivity().findViewById(R.id.all_no_information);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        sojourn_rv.setLayoutManager(linearLayoutManager);
        initData();

    }

    private void initData() {

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<BorkerageDownBean> test = fzbInterface.getBrokerageDown(FinalContents.getUserID(), "3","","1000");
        test.subscribeOn(Schedulers.io())
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
                            sojourn_rv.setVisibility(View.VISIBLE);
                            adapter = new BrokerageAdapter();
                            adapter.setRows(rows);
                            sojourn_rv.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        }else {
                            all_no_information.setVisibility(View.VISIBLE);
                            sojourn_rv.setVisibility(View.GONE);
                        }


                    }

                    @Override
                    public void onError(Throwable e) {
                        all_no_information.setVisibility(View.VISIBLE);
                        sojourn_rv.setVisibility(View.GONE);
                        Log.i("MyCL", "我的佣金下半部（旅居）" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

}
