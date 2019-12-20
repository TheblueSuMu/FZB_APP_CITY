package com.xcy.fzbcity.all.fragment;

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
import com.xcy.fzbcity.all.adapter.SpellingMassAdapter;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.modle.SpellingDataBean;
import com.xcy.fzbcity.all.persente.MyLinearLayoutManager;
import com.xcy.fzbcity.all.service.MyService;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class All_JourneyFragment extends AllFragment {
    private RecyclerView journey_rv;
    private View view;
    private ImageView all_no_information;
    private PtrClassicFrameLayout journey_ptrclass;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.shopping_guide_fragment_journey, null);
        journey_rv = view.findViewById(R.id.journey_rv);
        all_no_information = view.findViewById(R.id.all_no_information);

        journey_ptrclass = view.findViewById(R.id.journey_ptrclass);

        journey_ptrclass.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        journey_ptrclass.refreshComplete();
                        journey_ptrclass.setLastUpdateTimeKey("2017-2-10");
                        initData();
                    }
                }, 1000);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });

        initData();
        return view;
    }


    private void initData(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<SpellingDataBean> spellingDataBean = fzbInterface.getSpellingDataBean(FinalContents.getRouteTimeId());
        spellingDataBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SpellingDataBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SpellingDataBean spellingDataBean) {
                        if (spellingDataBean.getData().getPlanning().size() != 0) {
                            all_no_information.setVisibility(View.GONE);
                            journey_rv.setVisibility(View.VISIBLE);
                            MyLinearLayoutManager layoutManager = new MyLinearLayoutManager(getContext());
                            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                            journey_rv.setLayoutManager(layoutManager);
                            SpellingMassAdapter recyclerAdapter = new SpellingMassAdapter();
                            recyclerAdapter.setPlanning(spellingDataBean.getData().getPlanning());
                            journey_rv.setAdapter(recyclerAdapter);
                            recyclerAdapter.notifyDataSetChanged();
                        }else {
                            all_no_information.setVisibility(View.VISIBLE);
                            journey_rv.setVisibility(View.GONE);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        all_no_information.setVisibility(View.VISIBLE);
                        journey_rv.setVisibility(View.GONE);
                        Log.i("打印拼团详情数据", "错误" + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
