package com.xcy.fzb.all.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.nanchen.wavesidebar.WaveSideBarView;
import com.xcy.fzb.R;
import com.xcy.fzb.all.adapter.ContactsAdapter;
import com.xcy.fzb.all.api.FinalContents;
import com.xcy.fzb.all.modle.ClientBean;
import com.xcy.fzb.all.persente.ContactModel;
import com.xcy.fzb.all.persente.LetterComparator;
import com.xcy.fzb.all.persente.MyClientName;
import com.xcy.fzb.all.persente.PinnedHeaderDecoration;
import com.xcy.fzb.all.persente.StatusBar;
import com.xcy.fzb.all.service.MyService;
import com.xcy.fzb.all.view.ClientParticularsActivity;
import com.xcy.fzb.all.view.ReportActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Collections;
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
public class MyClientFragment1 extends Fragment implements ContactsAdapter.ItemOnClick {

    WaveSideBarView mWaveSideBarView;
    RecyclerView mRecyclerView;
    private ContactsAdapter mAdapter;
    private List<ContactModel> mContactModels;
    private PinnedHeaderDecoration decoration;
    private List<ClientBean.DataBean> data;
    private Context context;
    int i = 0;
    public MyClientFragment1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        StatusBar.makeStatusBarTransparent(getActivity());
        context = container.getContext();
        return inflater.inflate(R.layout.fragment_my_client_fragment1, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        EventBus.getDefault().register(this);
        i = 0;
        data = new ArrayList<>();
        mContactModels = new ArrayList<>();
        mWaveSideBarView = getActivity().findViewById(R.id.main_side_bar);
        mRecyclerView = getActivity().findViewById(R.id.main_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        decoration = new PinnedHeaderDecoration();

        initData();
    }

    private void initData() {
        Log.i("MyCL","进入initData");
        data.clear();
        mContactModels.clear();

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<ClientBean> client = fzbInterface.getClient("", FinalContents.getUserID(),"1000");
        client.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClientBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ClientBean clientBean) {
                        data = clientBean.getData();
                        Log.i("MyCL","数据长度：" + data.size());
                        for (int i = 0; i < data.size(); ++i) {
                            ContactModel contactModel = new ContactModel(data.get(i).getName());
                            mContactModels.add(contactModel);
                        }
                        initDatas();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "客户列表错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void initDatas() {
// RecyclerView设置相关
        decoration.registerTypePinnedHeader(1, new PinnedHeaderDecoration.PinnedHeaderCreator() {
            @Override
            public boolean create(RecyclerView parent, int adapterPosition) {
                return true;
            }
        });
        mRecyclerView.addItemDecoration(decoration);
        mAdapter = new ContactsAdapter();
        Collections.sort(mContactModels, new LetterComparator());
        mAdapter.setContacts(mContactModels);
        mAdapter.setItemOnClick(this);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

// 侧边设置相关
        mWaveSideBarView.setOnSelectIndexItemListener(new WaveSideBarView.OnSelectIndexItemListener() {
            @Override
            public void onSelectIndexItem(String letter) {
                for (int i = 0; i < mContactModels.size(); i++) {
                    if (mContactModels.get(i).getIndex().equals(letter)) {
                        ((LinearLayoutManager) mRecyclerView.getLayoutManager()).scrollToPositionWithOffset(i, 0);
                        return;
                    }
                }
            }
        });

    }

    @Subscribe(threadMode = ThreadMode.MAIN, priority = 100, sticky = false) //在ui线程执行，优先级为100
    public void onEvent(MyClientName myClientName) {
        String name = myClientName.getName();
        Log.i("MyCL", "廣播");
        inithot(name);
    }

    private void inithot(String name) {
        mContactModels.clear();
        data.clear();
        Log.i("MyCL", "inithot");
        Retrofit.Builder builder = new Retrofit.Builder();
        Log.i("MyCL", "4");
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        Log.i("MyCL", "3");
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Log.i("MyCL", "2");
        Observable<ClientBean> client = fzbInterface.getClient(name, FinalContents.getUserID() + "","1000");
        client.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClientBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ClientBean clientBean) {
                        Log.i("MyCL", "1");
                        mContactModels.clear();
                        data = clientBean.getData();
                        for (int i = 0; i < data.size(); ++i) {
                            ContactModel contactModel = new ContactModel(data.get(i).getName());
                            mContactModels.add(contactModel);
                        }
                        initDatas();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "客户列表错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    @Override
    public void itemClick(String itemName) {

        for (int i = 0; i < data.size(); ++i) {
            if (data.get(i).getName().equals(itemName)) {
                if (FinalContents.getNUM().equals("1")) {
                    FinalContents.setClientName(data.get(i).getName());
                    FinalContents.setCustomerID(data.get(i).getId());
                    getActivity().finish();
                    FinalContents.setNUM("0");
                    Intent intent = new Intent(context, ReportActivity.class);
                    startActivity(intent);
                } else {
                    FinalContents.setCustomerID(data.get(i).getId());
                    Intent intent = new Intent(getContext(), ClientParticularsActivity.class);
                    startActivity(intent);
                }
            }
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("MyCL","onResume");
        if(i == 0){
            i = 1;
        }else {
            initData();
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        Log.i("MyCL","onPause");
        i = 1;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        EventBus.getDefault().unregister(this);

    }
}
