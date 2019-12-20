package com.xcy.fzbcity.all.fragment;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.adapter.CountryAdapter;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.modle.CountryBean;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.service.MyService;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
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
public class CountryFragment extends Fragment {
    private String projectType;
    private String eventUrl = "";

    private Context context;
    private View view;
    private CheckBox country_all;
    private ImageView country_reset;

    public CountryFragment(String projectType) {
        this.projectType = projectType;
    }

    private List<CountryBean.DataBean> list = new ArrayList<>();

    private RecyclerView recyclerView;
    private ImageView ensure;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_country, null);
        StatusBar.makeStatusBarTransparent(getActivity());
        ensure = view.findViewById(R.id.country_ensure);
        country_reset = view.findViewById(R.id.country_reset);
        recyclerView = view.findViewById(R.id.country_recycler);
        country_all = view.findViewById(R.id.country_all);
        context = container.getContext();
        country_all.setChecked(true);
        init();

        return view;
    }

    @SuppressLint("WrongConstant")
    private void init() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<CountryBean> countryBean = fzbInterface.getCountryBean(FinalContents.getUserID(), FinalContents.getCityID(), FinalContents.getProjectType(),"1000");
        countryBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CountryBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CountryBean countryBean) {
                        list = countryBean.getData();
//
                        if (list.size() == 0) {
                            recyclerView.setVisibility(View.GONE);
                        }else {
                            recyclerView.setVisibility(View.VISIBLE);
                            LinearLayoutManager layoutManager = new LinearLayoutManager(context);
                            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                            recyclerView.setLayoutManager(layoutManager);
                            final CountryAdapter recyclerAdapter = new CountryAdapter(list);
                            recyclerAdapter.setOnItemClickListener(new CountryAdapter.OnItemClickLisenter() {
                                @Override
                                public void onItemClick(int postion) {
                                    FinalContents.setNation(list.get(postion).getNationName());
                                    country_all.setChecked(false);
                                }
                            });

                            recyclerView.setAdapter(recyclerAdapter);

                            country_all.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    FinalContents.setIndex(-1);
                                    FinalContents.setNation("");
                                    recyclerAdapter.notifyDataSetChanged();
                                }
                            });

                            country_reset.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    country_all.setChecked(true);
                                    FinalContents.setIndex(-1);
                                    FinalContents.setNation("");
                                    recyclerAdapter.notifyDataSetChanged();
                                }
                            });
                            recyclerAdapter.notifyDataSetChanged();
                            ensure.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Log.i("event", "");
                                    EventBus.getDefault().post(eventUrl);
                                }
                            });

                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "CountryFragment错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

}
