package com.xcy.fzbcity.captain_team.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.mcxtzhang.indexlib.IndexBar.widget.IndexBar;
import com.mcxtzhang.indexlib.suspension.SuspensionDecoration;
import com.nanchen.wavesidebar.WaveSideBarView;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.adapter.LinkmanAdapter;
import com.xcy.fzbcity.all.api.CityContents;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.api.NewlyIncreased;
import com.xcy.fzbcity.all.database.LinkmanBean;
import com.xcy.fzbcity.all.modle.ClientBean;
import com.xcy.fzbcity.all.persente.ContactModel;
import com.xcy.fzbcity.all.persente.DividerItemDecoration;
import com.xcy.fzbcity.all.persente.MyClientName;
import com.xcy.fzbcity.all.persente.PinnedHeaderDecoration;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.view.ClientParticularsActivity;
import com.xcy.fzbcity.captain_team.adapter.ContactsAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
public class Captain_Team_MyClientFragment1 extends Fragment{

    WaveSideBarView mWaveSideBarView;
    RecyclerView mRecyclerView;
    private ContactsAdapter mAdapter;
    private List<ContactModel> mContactModels;
    private PinnedHeaderDecoration decoration;
    private List<ClientBean.DataBean> data;
    private Context context;
    private ImageView all_no_information;
    private int size = 0;

    /**
     * 右侧边栏导航区域
     */
    private IndexBar main_side_bar;

    /**
     * 显示指示器DialogText
     */
    private TextView main_indexbar;
    private List<LinkmanBean> list = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;
    private SuspensionDecoration mDecoration;
    private LinkmanAdapter linkmanAdapter;

    public Captain_Team_MyClientFragment1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        StatusBar.makeStatusBarTransparent(getActivity());
        EventBus.getDefault().register(this);
        CityContents.setAddClient("");
        context = container.getContext();
        return inflater.inflate(R.layout.fragment_my_client_fragment1, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        main_indexbar = getActivity().findViewById(R.id.main_indexbar);//HintTextView
        main_side_bar = getActivity().findViewById(R.id.test_side_bar);//IndexBar
        mRecyclerView = getActivity().findViewById(R.id.main_recycler);
        all_no_information = getActivity().findViewById(R.id.all_no_information);
        main_side_bar.bringToFront();
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.addItemDecoration(mDecoration = new SuspensionDecoration(context, list));
        //如果add两个，那么按照先后顺序，依次渲染。
        mRecyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL_LIST));
        //indexbar初始化
        main_side_bar.setmPressedShowTextView(main_indexbar)//设置HintTextView
                .setNeedRealIndex(true)//设置需要真实的索引
                .setmLayoutManager(linearLayoutManager);//设置RecyclerView的LayoutManager

        inithot("");

    }

    @Subscribe(threadMode = ThreadMode.MAIN, priority = 100, sticky = false) //在ui线程执行，优先级为100
    public void onEvent(MyClientName myClientName) {
        String name = myClientName.getName();
        Log.i("MyCL", "廣播:" + name);
//        if (NewlyIncreased.isTest()) {
        size++;
        inithot(name);
        NewlyIncreased.setTest(false);
//        }
    }

    private void inithot(String name) {
        list = new ArrayList<>();
        mContactModels = new ArrayList<>();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        decoration = new PinnedHeaderDecoration();
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
        Observable<ClientBean> client = fzbInterface.getClient(name, FinalContents.getUserID() + "", "1000");
        client.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClientBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ClientBean clientBean) {
                        mContactModels.clear();
                        data = clientBean.getData();
                        if (data.size() != 0) {
                            all_no_information.setVisibility(View.GONE);
                            mRecyclerView.setVisibility(View.VISIBLE);
                            for (int i = 0; i < data.size(); i++) {
                                list.add(new LinkmanBean(data.get(i).getCustomerName() + "", data.get(i).getId() + "", data.get(i).getContactsPhone1() + ""));
                            }

                            linkmanAdapter = new LinkmanAdapter(context, list);
                            mRecyclerView.setAdapter(linkmanAdapter);
                            linkmanAdapter.notifyDataSetChanged();

                            main_side_bar.setmSourceDatas(list)//设置数据
                                    .invalidate();
                            mDecoration.setmDatas(list);

                            linkmanAdapter.setItemOnClick(new LinkmanAdapter.ItemOnClick() {
                                                              @Override
                                                              public void itemClick(int position) {
                                                                  Log.i("数据对比", "1客户名" + list.get(position).getCity());
                                                                  if (FinalContents.getNUM().equals("1")) {
                                                                      FinalContents.setClientName(list.get(position).getCity());
                                                                      FinalContents.setCustomerID(list.get(position).getClientId());
                                                                      FinalContents.setClientPhone(list.get(position).getClientPhone());
                                                                      Log.i("数据对比", "1客户名" + list.get(position).getCity());
                                                                      getActivity().finish();
                                                                  } else {
                                                                      FinalContents.setCustomerID(list.get(position).getClientId());
                                                                      Intent intent = new Intent(getContext(), ClientParticularsActivity.class);
                                                                      startActivity(intent);
                                                                      Log.i("团队长", "contacts.get(position).getName()：" + list.get(position).getCity());

                                                                  }
                                                              }
                                                          }
                            );
                        } else {
                            all_no_information.setVisibility(View.VISIBLE);
                            mRecyclerView.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        all_no_information.setVisibility(View.VISIBLE);
                        mRecyclerView.setVisibility(View.GONE);
                        Log.i("MyCL", "客户列表错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    @Override
    public void onResume() {
        super.onResume();
        if (CityContents.getAddClient().equals("1")) {
            Log.i("客户列表","加载成功");
            inithot("");
        }else {
            Log.i("客户列表","加载失败");
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(hidden){
            //TODO now visible to user 不显示fragment
        } else {
            onResume();
            //TODO now invisible to user 显示fragment
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        CityContents.setAddClient("");
    }

}
