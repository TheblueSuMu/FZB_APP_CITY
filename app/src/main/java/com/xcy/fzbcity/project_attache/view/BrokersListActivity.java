package com.xcy.fzbcity.project_attache.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.database.BrokersListBean;
import com.xcy.fzbcity.all.database.BrokersListData;
import com.xcy.fzbcity.all.persente.ContactModel;
import com.xcy.fzbcity.all.persente.LetterComparator;
import com.xcy.fzbcity.all.persente.PinnedHeaderDecoration;
import com.xcy.fzbcity.all.persente.SingleClick;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.CommonUtil;
import com.xcy.fzbcity.all.utils.ToastUtil;
import com.xcy.fzbcity.all.view.AllActivity;
import com.xcy.fzbcity.project_attache.adapter.ContactsAdapter;

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

public class BrokersListActivity extends AllActivity implements View.OnClickListener {

    RelativeLayout brokers_list_return;
    ImageView brokers_list_add;
    EditText brokers_list_et;
    RecyclerView brokers_list_rv;

//    RelativeLayout brokers_tv_rl;

    TextView brokers_tv;

    private ContactsAdapter mAdapter;
    private List<ContactModel> mContactModels;
    private PinnedHeaderDecoration decoration;
    private Context context;
    private List<BrokersListData> listData;
    private List<BrokersListBean.DataBean.RowsBean> rows;

    private PopupWindow popupWindow;
    private View inflate;
    private ImageView all_no_information;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_attache_activity_brokers_list);
        init_No_Network();
    }

    private void init_No_Network() {
        boolean networkAvailable = CommonUtil.isNetworkAvailable(this);
        if (networkAvailable) {
            initView();
        } else {
            RelativeLayout all_no_network = findViewById(R.id.all_no_network);
            Button all_no_reload = findViewById(R.id.all_no_reload);

            all_no_network.setVisibility(View.VISIBLE);
            all_no_reload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                    startActivity(getIntent());
                }
            });
            ToastUtil.showLongToast(BrokersListActivity.this,"当前无网络，请检查网络后再进行登录");

        }
    }

    private void initView() {

        StatusBar.makeStatusBarTransparent(this);
        all_no_information = findViewById(R.id.all_no_information_j);
        brokers_list_return = findViewById(R.id.brokers_list_return);
        brokers_list_add = findViewById(R.id.brokers_list_add);
        brokers_list_et = findViewById(R.id.brokers_list_et);
        brokers_list_rv = findViewById(R.id.brokers_list_rv);
        brokers_tv = findViewById(R.id.brokers_tv);
//        brokers_tv_rl = findViewById(R.id.brokers_tv_rl);
        context = BrokersListActivity.this;
        mContactModels = new ArrayList<>();
        listData = new ArrayList<>();
        brokers_list_rv.setLayoutManager(new LinearLayoutManager(this));
        decoration = new PinnedHeaderDecoration();

        brokers_list_return.setOnClickListener(this);
        brokers_list_add.setOnClickListener(this);
        brokers_tv.setOnClickListener(this);

        brokers_list_et.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (KeyEvent.KEYCODE_ENTER == i && KeyEvent.ACTION_DOWN == keyEvent.getAction()) {
                    //处理事件
                    String s = brokers_list_et.getText().toString();
                    String s1 = brokers_tv.getText().toString();
                    if (s1.equals("全部")) {
                        initData(FinalContents.getStoreId(), s, "","","","");
                        return true;
                    } else if (s1.equals("只看异常经纪人")) {
                        initData(FinalContents.getStoreId(), s, "2","","","");
                        return true;
                    } else {
                        initData(FinalContents.getStoreId(), s, "","","","");
                        return true;
                    }
                }
                return false;
            }
        });

    }

    private void initData(String storeId, String search, String status,String type,String startData,String endData) {

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Log.i("MyCL", "--1--FinalContents.getCompanyId()：" + FinalContents.getCompanyId());
        Log.i("MyCL", "--1--storeId：" + storeId);
        Log.i("MyCL", "--1--search：" + search);
        Log.i("MyCL", "--1--status：" + status);
        Log.i("MyCL", "--1-- FinalContents.getUserID()：" +  FinalContents.getUserID());
        Observable<BrokersListBean> brokersListBean = fzbInterface.getBrokersListBean(FinalContents.getCompanyId(), storeId, search, status, FinalContents.getUserID(), "1000",type,startData,endData);
        brokersListBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BrokersListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BrokersListBean brokersListBean) {
                        listData.clear();
                        mContactModels.clear();
                        rows = brokersListBean.getData().getRows();
                        if (rows.size() != 0) {
                            all_no_information.setVisibility(View.GONE);
                            brokers_list_rv.setVisibility(View.VISIBLE);
                            for (int i = 0; i < rows.size(); ++i) {
                                ContactModel contactModel = new ContactModel(rows.get(i).getAgentName() + "@" + rows.get(i).getAgentId());
                                BrokersListData brokersListData = new BrokersListData();
                                brokersListData.setAgentPhone(rows.get(i).getAgentPhone());
                                brokersListData.setAgentName(rows.get(i).getAgentName());
                                brokersListData.setCompanyName(rows.get(i).getCompanyName());
                                brokersListData.setStoreName(rows.get(i).getStoreName());
                                brokersListData.setStatus(rows.get(i).getStatus());
                                brokersListData.setAgentId(rows.get(i).getAgentId());
                                mContactModels.add(contactModel);
                                listData.add(brokersListData);

                            }
                            initDatas();
                        } else {
                            all_no_information.setVisibility(View.VISIBLE);
                            brokers_list_rv.setVisibility(View.GONE);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("经纪人列表", "错误信息:" + e.getMessage());
                        brokers_list_rv.setVisibility(View.GONE);
                        all_no_information.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initDatas() {

        decoration.registerTypePinnedHeader(1, new PinnedHeaderDecoration.PinnedHeaderCreator() {
            @Override
            public boolean create(RecyclerView parent, int adapterPosition) {
                return true;
            }
        });
        brokers_list_rv.addItemDecoration(decoration);
        mAdapter = new ContactsAdapter();
        Collections.sort(mContactModels, new LetterComparator());
        mAdapter.setContacts(mContactModels);
        mAdapter.setListData(listData);
        brokers_list_rv.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("销毁","数2："+FinalContents.getStoreId());
        if(FinalContents.getCompanyId().equals("")){
            String s = brokers_list_et.getText().toString();
            String s1 = brokers_tv.getText().toString();
            if (s1.equals("全部")) {
                initData(FinalContents.getStoreId(), s, "","","","");
            } else if (s1.equals("只看异常经纪人")) {
                initData(FinalContents.getStoreId(), s, "2","","","");
            }
//            initData(FinalContents.getStoreId(), "", "","","","");
        }else {
            String types = getIntent().getStringExtra("types");
            String starts = getIntent().getStringExtra("starts");
            String ends = getIntent().getStringExtra("ends");

            String s = brokers_list_et.getText().toString();
            String s1 = brokers_tv.getText().toString();
            if (s1.equals("全部")) {
                initData(FinalContents.getStoreId(), s, "",types,starts,ends);
            } else if (s1.equals("只看异常经纪人")) {
                initData(FinalContents.getStoreId(), s, "2",types,starts,ends);
            }

//            initData(FinalContents.getStoreId(), "", "",types,starts,ends);
        }
    }

    @SingleClick(1000)
    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.brokers_list_return:
                finish();
                break;
            case R.id.brokers_list_add:
                Intent intent = new Intent(BrokersListActivity.this, AddBrokerActivity.class);
                startActivity(intent);
                break;
            case R.id.brokers_tv:
                initAll();
                break;

        }

    }

    private void initAll() {

        final List<String> list1 = new ArrayList<>();
        list1.add("全部");
        list1.add("只看异常经纪人");
        OptionsPickerView pvOptions = new OptionsPickerBuilder(BrokersListActivity.this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                brokers_tv.setText(list1.get(options1));
                String string = brokers_list_et.getText().toString();
                if (options1 == 0) {
                    initData(FinalContents.getStoreId(), string, "","","","");
                } else if (options1 == 1) {
                    initData(FinalContents.getStoreId(), string, "2","","","");
                }
            }
        }).setSelectOptions(0)
                .setOutSideCancelable(false)//点击背的地方不消失
                .build();//创建
        //      把数据绑定到控件上面
        pvOptions.setPicker(list1);
        //      展示
        pvOptions.show();


    }
}
