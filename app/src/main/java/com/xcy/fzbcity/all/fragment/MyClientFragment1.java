package com.xcy.fzbcity.all.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.mcxtzhang.indexlib.IndexBar.widget.IndexBar;
import com.mcxtzhang.indexlib.suspension.SuspensionDecoration;
import com.nanchen.wavesidebar.WaveSideBarView;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.adapter.ContactsAdapter;
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
public class MyClientFragment1 extends Fragment implements ContactsAdapter.ItemOnClick {

    WaveSideBarView mWaveSideBarView;
    private RecyclerView mRecyclerView;
    private ContactsAdapter mAdapter;
    private List<ContactModel> mContactModels = new ArrayList<>();
    private PinnedHeaderDecoration decoration;
    private List<ClientBean.DataBean> data = new ArrayList<>();
    private Context context;
    int i = 0;
    private ImageView all_no_information;
    private View view;


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

    public MyClientFragment1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_my_client_fragment1, container, false);
        context = container.getContext();
        StatusBar.makeStatusBarTransparent(getActivity());
        init();
        return view;
    }

    private void init() {
        EventBus.getDefault().register(this);
        i = 0;
//        mWaveSideBarView = view.findViewById(R.id.main_side_bar);
        //使用indexBar
        linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        main_indexbar = view.findViewById(R.id.main_indexbar);//HintTextView
        main_side_bar = view.findViewById(R.id.test_side_bar);//IndexBar
        mRecyclerView = view.findViewById(R.id.main_recycler);
        all_no_information = view.findViewById(R.id.all_no_information);

        main_side_bar.bringToFront();
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.addItemDecoration(mDecoration = new SuspensionDecoration(context, list));
        //如果add两个，那么按照先后顺序，依次渲染。
        mRecyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL_LIST));
        //indexbar初始化
        main_side_bar.setmPressedShowTextView(main_indexbar)//设置HintTextView
                .setNeedRealIndex(true)//设置需要真实的索引
                .setmLayoutManager(linearLayoutManager);//设置RecyclerView的LayoutManager

        Log.i("客户列表", "oncreate");
        initData();
    }

    private void initData() {
        list = new ArrayList<>();
        Log.i("客户列表", "进入initData");
        data.clear();
        mContactModels.clear();
        decoration = new PinnedHeaderDecoration();
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<ClientBean> client = fzbInterface.getClient("", FinalContents.getUserID(), "1000");
        client.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClientBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ClientBean clientBean) {
                        data.clear();
                        list.clear();
                        data = clientBean.getData();
                        Log.i("客户列表", "数据长度：" + data.size());
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
                                                                      Log.i("数据对比", "1客户名" + list.get(position).getCity());
                                                                      FinalContents.setClientPhone(list.get(position).getClientPhone());
                                                                      FinalContents.setChecked(true);
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
                            mRecyclerView.setVisibility(View.GONE);
                            all_no_information.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mRecyclerView.setVisibility(View.GONE);
                        all_no_information.setVisibility(View.VISIBLE);
                        Log.i("客户列表", "客户列表错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    @Subscribe(threadMode = ThreadMode.MAIN, priority = 100, sticky = false) //在ui线程执行，优先级为100
    public void onEvent(MyClientName myClientName) {
        String name = myClientName.getName();

        if (NewlyIncreased.isTest()) {
            Log.i("廣播", "廣播1");
            inithot(name);
            NewlyIncreased.setTest(false);
        }
    }

    private void inithot(String name) {
        Log.i("廣播", "廣播2");
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
        Observable<ClientBean> client = fzbInterface.getClient(name, FinalContents.getUserID() + "", "1000");
        client.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClientBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ClientBean clientBean) {
                        data.clear();
                        list.clear();
                        data = clientBean.getData();
                        Log.i("客户列表", "数据长度：" + data.size());
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
                        }else {
                            mRecyclerView.setVisibility(View.GONE);
                            all_no_information.setVisibility(View.VISIBLE);
                        }
                        Log.i("MyCL", "1");
//                        mContactModels.clear();
//                        data = clientBean.getData();
//                        for (int i = 0; i < data.size(); ++i) {
//                            ContactModel contactModel = new ContactModel(data.get(i).getName() + "@" + data.get(i).getId());
//                            mContactModels.add(contactModel);
//                        }
//
//                        mAdapter = new ContactsAdapter();
//                        mAdapter.setContacts(mContactModels);
//                        mRecyclerView.setAdapter(mAdapter);
//                        mAdapter.notifyDataSetChanged();

//                        linkmanAdapter = new LinkmanAdapter(context, list);
//                        mRecyclerView.setAdapter(linkmanAdapter);
//                        linkmanAdapter.notifyDataSetChanged();
//
//                        main_side_bar.setmSourceDatas(list)//设置数据
//                                .invalidate();
//                        mDecoration.setmDatas(list);

                        NewlyIncreased.setTest(true);
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

        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer append = stringBuffer.append(itemName);
        if (FinalContents.getNUM().equals("1")) {
            for (int j = 0; j < append.length(); ++j) {
                if (append.substring(j, j + 1).equals("@")) {
                    FinalContents.setClientName(append.substring(0, j));
                    FinalContents.setCustomerID(append.substring(j + 1));
                    FinalContents.setNUM("0");
                    break;
                }
            }

            for (int i = 0; i < data.size(); i++) {
                if (FinalContents.getCustomerID().equals(data.get(i).getId())) {
                    FinalContents.setClientPhone(data.get(i).getContactsPhone1());
                    getActivity().finish();
                    break;
                }
            }
        } else {
            for (int j = 0; j < append.length(); ++j) {
                if (append.substring(j, j + 1).equals("@")) {
                    FinalContents.setCustomerID(append.substring(j + 1));
                    Intent intent = new Intent(getContext(), ClientParticularsActivity.class);
                    startActivity(intent);
                    Log.i("团队长", "contacts.get(position).getName()：" + append.substring(j + 1));
                    break;
                }
            }

        }

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("客户列表", "加载");
        if (CityContents.getAddClient().equals("1")) {
            Log.i("客户列表", "加载成功");
            initData();
        } else {
            Log.i("客户列表", "加载失败");
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        EventBus.getDefault().unregister(this);
        CityContents.setAddClient("");
    }
}
