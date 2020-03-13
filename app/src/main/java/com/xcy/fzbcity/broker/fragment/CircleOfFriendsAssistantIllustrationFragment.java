package com.xcy.fzbcity.broker.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.adapter.CircleOfFriendsAssistantCaptionBarAdapter;
import com.xcy.fzbcity.all.adapter.CircleOfFriendsAssistantCaptionListAdapter;
import com.xcy.fzbcity.all.adapter.CircleOfFriendsAssistantIllustrationListAdapter;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.fragment.AllFragment;
import com.xcy.fzbcity.all.modle.FriendsAssistantBean;
import com.xcy.fzbcity.all.service.MyService;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CircleOfFriendsAssistantIllustrationFragment extends AllFragment {

    private View view;
    private RecyclerView fragment_circle_of_friends_assistant_illustration_bar;
    private RecyclerView fragment_circle_of_friends_assistant_illustration_list;
    private Context context;
    private int index = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_circle_of_friends_assistant_illustration, null);
        context = container.getContext();
        initfvb();
        return view;
    }

    private void initfvb(){
        fragment_circle_of_friends_assistant_illustration_bar = view.findViewById(R.id.fragment_circle_of_friends_assistant_illustration_bar);
        fragment_circle_of_friends_assistant_illustration_list = view.findViewById(R.id.fragment_circle_of_friends_assistant_illustration_list);
        initDataTitle();
        initDataList();
    }


    //  TODO    朋友圈助手配图导航栏
    private void initDataTitle(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        final Observable<FriendsAssistantBean> code = fzbInterface.getFriendsAssistant(FinalContents.getUserID(),"0");
        code.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FriendsAssistantBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FriendsAssistantBean friendsAssistantBean) {
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        fragment_circle_of_friends_assistant_illustration_bar.setLayoutManager(linearLayoutManager);
                        CircleOfFriendsAssistantCaptionBarAdapter circleOfFriendsAssistantCaptionBarAdapter = new CircleOfFriendsAssistantCaptionBarAdapter(friendsAssistantBean.getData());
                        fragment_circle_of_friends_assistant_illustration_bar.setAdapter(circleOfFriendsAssistantCaptionBarAdapter);
                        circleOfFriendsAssistantCaptionBarAdapter.notifyDataSetChanged();
                        circleOfFriendsAssistantCaptionBarAdapter.setOnItemClickListener(new CircleOfFriendsAssistantCaptionBarAdapter.OnItemClickLisenter() {
                            @Override
                            public void onItemClick(int postion) {
                                index = postion;
                                initDataList();
                            }
                        });
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("朋友圈助手配图导航栏", "朋友圈助手配图导航栏错误信息:" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //  TODO    朋友圈助手配图列表
    private void initDataList(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        final Observable<FriendsAssistantBean> code = fzbInterface.getFriendsAssistant(FinalContents.getUserID(),"0");
        code.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FriendsAssistantBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FriendsAssistantBean friendsAssistantBean) {
                        GridLayoutManager gridLayoutManager = new GridLayoutManager(context,2);
                        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
                        fragment_circle_of_friends_assistant_illustration_list.setLayoutManager(gridLayoutManager);
                        CircleOfFriendsAssistantIllustrationListAdapter circleOfFriendsAssistantIllustrationListAdapter = new CircleOfFriendsAssistantIllustrationListAdapter(friendsAssistantBean.getData().get(index).getListDate());
                        fragment_circle_of_friends_assistant_illustration_list.setAdapter(circleOfFriendsAssistantIllustrationListAdapter);
                        circleOfFriendsAssistantIllustrationListAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("朋友圈助手配图列表", "朋友圈助手配图列表错误信息:" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onPause() {
        super.onPause();
        index = 0;
    }
}
