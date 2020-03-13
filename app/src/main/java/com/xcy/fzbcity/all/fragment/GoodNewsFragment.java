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
import com.xcy.fzbcity.all.adapter.GoodNewsAdapter;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.modle.GoodNewsBean;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.service.MyService;

import java.util.List;

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

/**
 * A simple {@link Fragment} subclass.
 */
//TODO 消息喜报
public class GoodNewsFragment extends Fragment {
    PtrClassicFrameLayout ptrClassicFrameLayout;
    RecyclerView good_news_rv;
    String url;
    GoodNewsAdapter adapter;
    private ImageView all_no_information;

    public GoodNewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        StatusBar.makeStatusBarTransparent(getActivity());

        return inflater.inflate(R.layout.fragment_good_news, container, false);
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        all_no_information = getActivity().findViewById(R.id.all_no_information_good_news);

        good_news_rv = getActivity().findViewById(R.id.good_news_rv);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        good_news_rv.setLayoutManager(manager);
        ptrClassicFrameLayout = (PtrClassicFrameLayout) getActivity().findViewById(R.id.store_house_ptr_frame_14);
        ptrClassicFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ptrClassicFrameLayout.refreshComplete();
                        ptrClassicFrameLayout.setLastUpdateTimeKey("2017-2-10");
                        initData();
                    }
                }, 1000);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });


    }
    private void initData() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<GoodNewsBean> userMessage = fzbInterface.getGoodNewsBeanList(FinalContents.getUserID(),FinalContents.getOldCityId(),"5","1000");
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GoodNewsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i("喜报获取错误","错误+");
                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(GoodNewsBean goodNewsBean) {
                        GoodNewsBean.DataBean data1 = goodNewsBean.getData();
                        List<GoodNewsBean.DataBean.RowsBean> rows = data1.getRows();
                        try {
                            if (rows.size() != 0) {
                                all_no_information.setVisibility(View.GONE);
                                good_news_rv.setVisibility(View.VISIBLE);
                                adapter = new GoodNewsAdapter();
                                adapter.setRows(rows);
                                good_news_rv.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                                Log.i("喜报获取","数据1"+rows.size());
                            }else {
                                Log.i("喜报获取","数据2"+rows.size());
                                all_no_information.setVisibility(View.VISIBLE);
                                good_news_rv.setVisibility(View.GONE);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        Log.i("喜报获取","数据"+rows.size());
                    }

                    @Override
                    public void onError(Throwable e) {
                        all_no_information.setVisibility(View.VISIBLE);
                        good_news_rv.setVisibility(View.GONE);
                        Log.i("喜报获取错误","错误"+e);
                    }

                    @Override
                    public void onComplete() {
                        Log.i("喜报获取错误","错误-");
                    }
                });
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(hidden){
            //TODO now visible to user 不显示fragment
        } else {
            initData();
            //TODO now invisible to user 显示fragment
        }
    }
}
