package com.xcy.fzbcity.shopping_guide.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.Connector;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.fragment.AllFragment;
import com.xcy.fzbcity.all.modle.CustomerListBean;
import com.xcy.fzbcity.all.persente.MyLinearLayoutManager;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.KeyUtils;
import com.xcy.fzbcity.shopping_guide.adapter.ClientAdapter;

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

public class ClientFragment extends AllFragment {
    private View view;
    private RecyclerView client_rv;
    private EditText client_search;
    private String search = "";
    int isnum = 0;
    private ImageView all_no_information;
    private PtrClassicFrameLayout client_ptrclass;

    private CustomerListBean customerList;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.shopping_guide_fragment_client, null);
        client_rv = view.findViewById(R.id.client_rv);
        all_no_information = view.findViewById(R.id.all_no_information);
        client_search = view.findViewById(R.id.client_search);
        client_ptrclass = view.findViewById(R.id.client_ptrclass);
        client_search.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (KeyEvent.KEYCODE_ENTER == i && KeyEvent.ACTION_DOWN == keyEvent.getAction()) {
                    KeyUtils.hideKeyboard(client_search);
                    search = client_search.getText().toString();
                    if (isnum == 0) {
                        initData();
                        isnum = 1;
                    }

                    return true;
                }
                return false;
            }
        });

        client_ptrclass.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        client_ptrclass.refreshComplete();
                        client_ptrclass.setLastUpdateTimeKey("2017-2-10");
                        client_search.setText("");
                        search = client_search.getText().toString();
                        initData();
                    }
                }, 1000);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });

        customerList = Connector.getCustomerListBean();

        search = "";
        client_search.setText("");

        return view;
    }

    private void initData() {

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<CustomerListBean> userMessage = fzbInterface.getcustomerList(FinalContents.getUserID(), FinalContents.getRouteTimeId(), search,"1000");
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CustomerListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(CustomerListBean customerListBean) {
                        Log.i("列表数据查询", "次数");
                        if (customerListBean.getData().getRows().size() != 0) {
                            all_no_information.setVisibility(View.GONE);
                            client_rv.setVisibility(View.VISIBLE);
                            MyLinearLayoutManager layoutManager = new MyLinearLayoutManager(view.getContext());
                            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                            client_rv.setLayoutManager(layoutManager);
                            ClientAdapter recyclerAdapter = new ClientAdapter(customerListBean.getData().getRows());
                            client_rv.setAdapter(recyclerAdapter);
                            recyclerAdapter.notifyDataSetChanged();
                        }else {
                            all_no_information.setVisibility(View.VISIBLE);
                            client_rv.setVisibility(View.GONE);

                        }
                        Connector.setCustomerListBean(customerListBean);
                        customerList = Connector.getCustomerListBean();
                        isnum = 0;
                    }

                    @Override
                    public void onError(Throwable e) {
                        all_no_information.setVisibility(View.VISIBLE);
                        client_rv.setVisibility(View.GONE);
                        Log.i("列表数据获取错误", "错误" + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }
}
