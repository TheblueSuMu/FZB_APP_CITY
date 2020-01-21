package com.xcy.fzbcity.project_side.fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
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
import com.xcy.fzbcity.all.adapter.ClientFragmentAdapter;
import com.xcy.fzbcity.all.adapter.ProcessDataAdapter;
import com.xcy.fzbcity.all.api.CityContents;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.api.NewlyIncreased;
import com.xcy.fzbcity.all.modle.ClientFragmentBean;
import com.xcy.fzbcity.all.modle.ProcessDataBean;
import com.xcy.fzbcity.all.persente.MyClientData;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.view.ReviewTheSuccessActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
//TODO 报备
public class MyClientFragment2 extends Fragment implements ClientFragmentAdapter.Click {

    PtrClassicFrameLayout mPtrClassicFrameLayout;

    RecyclerView client_2_rv;

    ClientFragmentAdapter clientFragmentAdapter;
    private List<ClientFragmentBean.DataBean.RowsBean> rows;
    private List<ProcessDataBean.DataBean.RowsBean> rowsList;
    private ProcessDataAdapter processDataAdapter;
    private ImageView all_no_information;


    public MyClientFragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        StatusBar.makeStatusBarTransparent(getActivity());

        return inflater.inflate(R.layout.fragment_my_client_fragment2, container, false);
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        EventBus.getDefault().register(this);
        client_2_rv = getActivity().findViewById(R.id.client_2_rv);
        all_no_information = getActivity().findViewById(R.id.all_no_information);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        client_2_rv.setLayoutManager(manager);

        mPtrClassicFrameLayout = getActivity().findViewById(R.id.store_house_ptr_frame_3);
        mPtrClassicFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPtrClassicFrameLayout.refreshComplete();
                        mPtrClassicFrameLayout.setLastUpdateTimeKey("2017-2-10");

                        if (FinalContents.getZhuanyuan().equals("1")) {
                            initData2("");
                        }else {
                            initData("");
                        }
                        EventBus.getDefault().post("修改了");
                    }
                }, 1000);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });

    }

    private void initData(String name) {

        String ProjectID = "";

        if (FinalContents.getDetails().equals("项目详情")) {
            ProjectID = FinalContents.getProjectID();
        }
        Log.i("专案","10:" + 10);
        Log.i("专案","name:" + name);
        Log.i("专案","FinalContents.getUserID():" + FinalContents.getUserID());
        Log.i("专案"," NewlyIncreased.getTag():" +  NewlyIncreased.getTag());
        Log.i("专案","NewlyIncreased.getStartDate():" + NewlyIncreased.getStartDate());
        Log.i("专案","NewlyIncreased.getEndDate():" + NewlyIncreased.getEndDate());
        clientFragmentAdapter = new ClientFragmentAdapter();
        clientFragmentAdapter.setClick(this);
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<ClientFragmentBean> clientFragment = fzbInterface.getClientFragment(FinalContents.getUserID(), ProjectID, name,"10","1000", NewlyIncreased.getTag(), NewlyIncreased.getStartDate(), NewlyIncreased.getEndDate());
        clientFragment.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClientFragmentBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ClientFragmentBean clientFragmentBean) {
                        rows = clientFragmentBean.getData().getRows();
                        if (rows.size() != 0) {
                            all_no_information.setVisibility(View.GONE);
                            client_2_rv.setVisibility(View.VISIBLE);
                            clientFragmentAdapter.setRows(rows);
                            client_2_rv.setAdapter(clientFragmentAdapter);
                        }else {
                            all_no_information.setVisibility(View.VISIBLE);
                            client_2_rv.setVisibility(View.GONE);
                        }


                    }

                    @Override
                    public void onError(Throwable e) {
                        all_no_information.setVisibility(View.VISIBLE);
                        client_2_rv.setVisibility(View.GONE);
                        Log.i("专案", "我的客户（报备）错误信息" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    private void initData2(String name) {
        processDataAdapter = new ProcessDataAdapter();
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Log.i("MyCL","FinalContents.getStoreId():" + FinalContents.getStoreId());
        Log.i("MyCL","10:" + 10);
        Log.i("MyCL","name:" + name);
        Log.i("MyCL","FinalContents.getUserID():" + FinalContents.getUserID());
        Log.i("MyCL"," NewlyIncreased.getTag():" +  NewlyIncreased.getTag());
        Log.i("MyCL","NewlyIncreased.getStartDate():" + NewlyIncreased.getStartDate());
        Log.i("MyCL","NewlyIncreased.getEndDate():" + NewlyIncreased.getEndDate());
        Observable<ProcessDataBean> clientFragment = fzbInterface.getProcessData(FinalContents.getStoreId(),FinalContents.getAgentId(), "10", name+"",FinalContents.getUserID(),"1000", NewlyIncreased.getTag(), NewlyIncreased.getStartDate(), NewlyIncreased.getEndDate());
        clientFragment.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ProcessDataBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ProcessDataBean processDataBean) {
                        rowsList = processDataBean.getData().getRows();
                        if (rowsList.size() != 0) {
                            all_no_information.setVisibility(View.GONE);
                            client_2_rv.setVisibility(View.VISIBLE);
                            processDataAdapter.setRows(rowsList);
                            client_2_rv.setAdapter(processDataAdapter);
                        }else {
                            all_no_information.setVisibility(View.VISIBLE);
                            client_2_rv.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        all_no_information.setVisibility(View.VISIBLE);
                        client_2_rv.setVisibility(View.GONE);
                        Log.i("MyCL", "我的客户（报备）错误信息" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    @Override
    public void ItemOnClick(int position) {
        CityContents.setReadRecordStatus("10");
        Intent intent = new Intent(getContext(), ReviewTheSuccessActivity.class);
        FinalContents.setPreparationId(rows.get(position).getPreparationId());
        startActivity(intent);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, priority = 102, sticky = false) //在ui线程执行，优先级为100
    public void onEvent(MyClientData myClientName) {
        String name = myClientName.getName();
        String judge = myClientName.getJudge();
        if (FinalContents.getZhuanyuan().equals("1")) {
            if(judge.equals("报备")){
                initData2(name);
            }
        }else {
            if(judge.equals("报备")){
                initData(name);
            }
        }

    }

    @Override
    public void onResume() {
        super.onResume();

        if (FinalContents.getZhuanyuan().equals("1")) {
            initData2("");
        }else {
            initData("");
        }

    }
}
