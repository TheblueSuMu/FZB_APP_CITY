package com.xcy.fzbcity.project_attache.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.CityContents;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.database.StoreListBean;
import com.xcy.fzbcity.all.database.StoreListData;
import com.xcy.fzbcity.all.persente.ContactModel;
import com.xcy.fzbcity.all.persente.LetterComparator;
import com.xcy.fzbcity.all.persente.PinnedHeaderDecoration;
import com.xcy.fzbcity.all.persente.SingleClick;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.CommonUtil;
import com.xcy.fzbcity.all.utils.ToastUtil;
import com.xcy.fzbcity.all.view.AllActivity;
import com.xcy.fzbcity.project_attache.adapter.StoreListAdapter;

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
//, SwipeRefreshLayout.OnRefreshListener
public class StoreListActivity extends AllActivity implements View.OnClickListener, StoreListAdapter.ItemOnClick {

    RelativeLayout store_list_return;
    ImageView store_list_add;

    EditText store_list_et;

    TextView store_list_tv1;
    TextView store_list_tv2;

    LinearLayout store_list_ll1;
    LinearLayout store_list_ll2;
    LinearLayout store_list_ll3;
    LinearLayout store_list_ll4;

    CheckBox store_list_cb;

    RecyclerView store_list_rv;

    private StoreListAdapter mAdapter;
    private List<ContactModel> mContactModels;
    private PinnedHeaderDecoration decoration;
    private Context context;
    private List<StoreListData> listData;
    private List<StoreListBean.DataBean.RowsBean> rows;
    private Intent intent;
    private ImageView all_no_information;
    private LinearLayout store_list_ll0;
    private TextView center_headline;

//    private SwipeRefreshLayout layout;

//    private AVLoadingIndicatorView avi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_attache_activity_store_list);
        init_No_Network();
    }

    private void init_No_Network(){
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
            ToastUtil.showLongToast(StoreListActivity.this,"当前无网络，请检查网络后再进行登录");
        }
    }

    private void initView() {
        StatusBar.makeStatusBarTransparent(this);
        all_no_information = findViewById(R.id.all_no_information);

        store_list_return = findViewById(R.id.store_list_return);
        store_list_add = findViewById(R.id.store_list_add);
        store_list_et = findViewById(R.id.store_list_et);
        center_headline = findViewById(R.id.center_headline);
        store_list_ll0 = findViewById(R.id.store_list_ll0);
        store_list_ll1 = findViewById(R.id.store_list_ll1);
        store_list_ll2 = findViewById(R.id.store_list_ll2);
        store_list_ll3 = findViewById(R.id.store_list_ll3);
        store_list_ll4 = findViewById(R.id.store_list_ll4);
        store_list_rv = findViewById(R.id.store_list_rv);
        store_list_tv2 = findViewById(R.id.store_list_tv2);
        store_list_tv1 = findViewById(R.id.store_list_tv1);
        store_list_cb = findViewById(R.id.store_list_cb);

//        avi = findViewById(R.id.avi);
//
//        avi.show();
//        avi.setVisibility(View.VISIBLE);

//        layout = findViewById(R.id.ssrl_s);

//        layout.setOnRefreshListener(this);

        if (CityContents.getStore().equals("1")) {
            store_list_ll0.setVisibility(View.GONE);
            center_headline.setText("门店列表");
        }else {
            store_list_ll0.setVisibility(View.VISIBLE);
            center_headline.setText("列表");
        }

        store_list_cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (store_list_cb.isChecked()) {
                    if (store_list_ll2.getVisibility() == View.VISIBLE) {
                        initData("", "", "1","","","");
                    } else {
                        initDatam("","", "1");
                    }
                } else {
                    if (store_list_ll2.getVisibility() == View.VISIBLE) {
                        initData("", "", "","","","");
                    } else {
                        initDatam("","", "");
                    }
                }
            }
        });

        store_list_et.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (KeyEvent.KEYCODE_ENTER == i && KeyEvent.ACTION_DOWN == keyEvent.getAction()) {
                    String s = store_list_et.getText().toString();
                    if (store_list_ll2.getVisibility() == View.VISIBLE) {
                        if (store_list_cb.isChecked()) {
                            initData("", s, "1","","","");
                        } else {
                            initData("", s, "","","","");
                        }
                    } else if (store_list_ll4.getVisibility() == View.VISIBLE) {
                        if (store_list_cb.isChecked()) {
                            initDatam("",s, "1");
                        } else {
                            initDatam("",s, "");
                        }
                    }
                    return true;
                }
                return false;
            }

        });
        store_list_ll1.setOnClickListener(this);
        store_list_ll3.setOnClickListener(this);
        store_list_return.setOnClickListener(this);
        store_list_add.setOnClickListener(this);

        if (FinalContents.getMyAddType().equals("公司")) {
            store_list_tv2.setTextColor(Color.parseColor("#FFFFFF"));
            store_list_tv1.setTextColor(Color.parseColor("#FFFFFF"));
            store_list_ll4.setVisibility(View.VISIBLE);
            store_list_ll2.setVisibility(View.INVISIBLE);
            initDatam("","", "");
            FinalContents.setStoreList("2");
        } else if (FinalContents.getMyAddType().equals("门店")) {
            store_list_tv1.setTextColor(Color.parseColor("#FFFFFF"));
            store_list_tv2.setTextColor(Color.parseColor("#FFFFFF"));
            store_list_ll2.setVisibility(View.VISIBLE);
            store_list_ll4.setVisibility(View.INVISIBLE);
            initData(FinalContents.getCompanyManageId(), "", "","","","");
            FinalContents.setStoreList("1");
        } else {

            String types = getIntent().getStringExtra("types");
            String starts = getIntent().getStringExtra("starts");
            String ends = getIntent().getStringExtra("ends");

            initData(FinalContents.getCompanyId(), "", "",types,starts,ends);
            FinalContents.setStoreList("1");
        }
    }

    private void initData(String companyId, String search, String status,String type,String startData,String endData) {

        context = StoreListActivity.this;
        mContactModels = new ArrayList<>();
        listData = new ArrayList<>();
        store_list_rv.setLayoutManager(new LinearLayoutManager(this));
        decoration = new PinnedHeaderDecoration();

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        final Observable<StoreListBean> storeList = fzbInterface.getStoreList(companyId, search, status, FinalContents.getUserID(), "1000",type,startData,endData);
        storeList.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<StoreListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(StoreListBean storeListBean) {
                        listData.clear();
                        mContactModels.clear();
                        rows = storeListBean.getData().getRows();
                        if (rows.size() != 0) {
                            all_no_information.setVisibility(View.GONE);
                            store_list_rv.setVisibility(View.VISIBLE);
                            for (int i = 0; i < rows.size(); ++i) {
                                if (rows.get(i).getCompanyName().equals("")) {

                                } else {
                                    ContactModel contactModel = new ContactModel(rows.get(i).getCompanyName() + "" + rows.get(i).getStoreName());
                                    StoreListData storeListData = new StoreListData();

                                    storeListData.setStoreName(rows.get(i).getStoreName());
                                    storeListData.setStoreId(rows.get(i).getStoreId());
                                    storeListData.setShopownerName(rows.get(i).getShopownerName());
                                    storeListData.setShopownerPhone(rows.get(i).getShopownerPhone());
                                    storeListData.setAgentNum(rows.get(i).getAgentNum());
                                    storeListData.setStatus(rows.get(i).getStatus());
                                    storeListData.setCompanyId(rows.get(i).getCompanyId());
                                    storeListData.setCompanyName(rows.get(i).getCompanyName());
                                    storeListData.setStoreIdCode(rows.get(i).getStoreIdCode());
                                    storeListData.setCompanyAddress(rows.get(i).getCompanyAddress());
                                    storeListData.setStoreNum(rows.get(i).getStoreNum());
                                    storeListData.setAttacheName(rows.get(i).getAttacheName());
                                    storeListData.setAttachePhone(rows.get(i).getAttachePhone());
                                    storeListData.setAttacheIdentity(rows.get(i).getAttacheIdentity());
                                    storeListData.setState(rows.get(i).getState());
                                    storeListData.setIsMy(rows.get(i).getIsMy());

                                    mContactModels.add(contactModel);
                                    listData.add(storeListData);
                                }
                            }
                            store_list_ll1.setClickable(true);
//                            avi.hide();
//                            avi.setVisibility(View.GONE);
                            initDatas();
                        }else {
                            all_no_information.setVisibility(View.VISIBLE);
                            store_list_rv.setVisibility(View.GONE);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
//                        avi.hide();
//                        avi.setVisibility(View.GONE);
                        all_no_information.setVisibility(View.VISIBLE);
                        store_list_rv.setVisibility(View.GONE);
                        Log.i("专员门店或公司列表", "错误信息：" + e.getMessage());
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
        store_list_rv.addItemDecoration(decoration);
        mAdapter = new StoreListAdapter();
        Collections.sort(mContactModels, new LetterComparator());
        mAdapter.setContacts(mContactModels);
        mAdapter.setListData(listData);
        mAdapter.setItemOnClick(this);
        store_list_rv.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

    }

    @SingleClick(1000)
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.store_list_return:
                finish();
                break;
            case R.id.store_list_add:

                if (FinalContents.getStoreList().equals("1")) {
                    intent = new Intent(StoreListActivity.this, AddStoreActivity.class);
                    FinalContents.setMyAddType("门店");
                    startActivity(intent);
                } else if (FinalContents.getStoreList().equals("2")) {
                    intent = new Intent(StoreListActivity.this, AddCompanyActivity.class);
                    FinalContents.setMyAddType("公司");
                    startActivity(intent);
                }

                break;
            case R.id.store_list_ll1:
//                avi.setVisibility(View.VISIBLE);
//                avi.show();
                if(store_list_ll3.isClickable()){
                    store_list_ll1.setClickable(false);
                    store_list_tv1.setTextColor(Color.parseColor("#FFFFFF"));
                    store_list_tv2.setTextColor(Color.parseColor("#FFFFFF"));
                    store_list_ll2.setVisibility(View.VISIBLE);
                    store_list_ll4.setVisibility(View.INVISIBLE);

                    if(listData.size() == 0){

                    }else {
                        store_list_rv.removeAllViews();
                        mContactModels.clear();
                        listData.clear();
                        mAdapter.notifyDataSetChanged();

                    }


                    if(FinalContents.getMyAddType().equals("门店")){
                        initData(FinalContents.getCompanyManageId(), "", "","","","");
                        FinalContents.setStoreList("1");
                    }else {
                        if (store_list_cb.isChecked()) {
                            initData("", "", "1","","","");
                        } else {
                            initData("", "", "","","","");
                        }
                        FinalContents.setStoreList("1");
                    }
                }else {
                    ToastUtil.showLongToast(StoreListActivity.this,"数据未加载完成");
                }


                break;
            case R.id.store_list_ll3:
//                avi.setVisibility(View.VISIBLE);
//                avi.show();
                if(store_list_ll1.isClickable()){
                    store_list_ll3.setClickable(false);
                    store_list_tv2.setTextColor(Color.parseColor("#FFFFFF"));
                    store_list_tv1.setTextColor(Color.parseColor("#FFFFFF"));
                    store_list_ll4.setVisibility(View.VISIBLE);
                    store_list_ll2.setVisibility(View.INVISIBLE);

                    if(listData.size() == 0){

                    }else {
                        store_list_rv.removeAllViews();
                        mContactModels.clear();
                        listData.clear();
                        mAdapter.notifyDataSetChanged();

                    }

                    if(FinalContents.getMyAddType().equals("公司")){
                        initDatam("","", "");
                        FinalContents.setStoreList("2");
                    }else {
                        FinalContents.setStoreList("2");
                        if (store_list_cb.isChecked()) {
                            initDatam("","", "1");
                        } else {
                            initDatam("","", "");
                        }
                    }
                }else {
                    ToastUtil.showLongToast(StoreListActivity.this,"数据未加载完成");
                }

                break;
        }

    }

    private void initDatam(String companyId,String search, String status) {

        context = StoreListActivity.this;
        mContactModels = new ArrayList<>();
        listData = new ArrayList<>();
        store_list_rv.setLayoutManager(new LinearLayoutManager(this));
        decoration = new PinnedHeaderDecoration();

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<StoreListBean> storeList = fzbInterface.getCompanList(companyId,search, status, FinalContents.getUserID(), "1000");
        storeList.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<StoreListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(StoreListBean storeListBean) {
                        listData.clear();
                        mContactModels.clear();
                        rows = storeListBean.getData().getRows();
                        if (rows.size() != 0) {
                            all_no_information.setVisibility(View.GONE);
                            store_list_rv.setVisibility(View.VISIBLE);
                            for (int i = 0; i < StoreListActivity.this.rows.size(); ++i) {
                                if (rows.get(i).getCompanyName().equals("")) {

                                } else {
                                    ContactModel contactModel = new ContactModel(StoreListActivity.this.rows.get(i).getCompanyName() + rows.get(i).getStoreName());
                                    StoreListData storeListData = new StoreListData();

                                    storeListData.setStoreName(StoreListActivity.this.rows.get(i).getStoreName());
                                    storeListData.setStoreId(StoreListActivity.this.rows.get(i).getStoreId());
                                    storeListData.setShopownerName(StoreListActivity.this.rows.get(i).getShopownerName());
                                    storeListData.setShopownerPhone(StoreListActivity.this.rows.get(i).getShopownerPhone());
                                    storeListData.setAgentNum(StoreListActivity.this.rows.get(i).getAgentNum());
                                    storeListData.setStatus(StoreListActivity.this.rows.get(i).getStatus());
                                    storeListData.setCompanyId(StoreListActivity.this.rows.get(i).getCompanyId());
                                    storeListData.setCompanyName(rows.get(i).getCompanyName());
                                    storeListData.setStoreIdCode(rows.get(i).getStoreIdCode());
                                    storeListData.setCompanyAddress(rows.get(i).getCompanyAddress());
                                    storeListData.setStoreNum(rows.get(i).getStoreNum());
                                    storeListData.setAttacheName(rows.get(i).getAttacheName());
                                    storeListData.setAttachePhone(rows.get(i).getAttachePhone());
                                    storeListData.setAttacheIdentity(rows.get(i).getAttacheIdentity());
                                    storeListData.setState(rows.get(i).getState());
                                    storeListData.setIsMy(rows.get(i).getIsMy());

                                    mContactModels.add(contactModel);
                                    listData.add(storeListData);
                                }

                            }
                            store_list_ll3.setClickable(true);
//                            avi.hide();
//                            avi.setVisibility(View.GONE);
                            initDatas();
                        }else {
                            all_no_information.setVisibility(View.VISIBLE);
                            store_list_rv.setVisibility(View.GONE);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
//                        avi.hide();
//                        avi.setVisibility(View.GONE);
                        all_no_information.setVisibility(View.VISIBLE);
                        store_list_rv.setVisibility(View.GONE);
                        Log.i("专员公司列表", "错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void itemClick(String itemName) {

        if (FinalContents.getStoreList().equals("1") && FinalContents.getMyAddType().equals("")) {
//          TODO 门店详情
            for (int i = 0; i < listData.size(); ++i) {
                if (listData.get(i).getStoreId().equals(itemName)) {
                    for (int j = 0; j < listData.size(); ++j) {
                        if (mContactModels.get(i).getName().equals((listData.get(j).getCompanyName() + listData.get(j).getStoreName()))) {
                            FinalContents.setStoreId(listData.get(j).getStoreId());
                            FinalContents.setMyAddType("门店");
                            Intent intent = new Intent(StoreListActivity.this, CompanyDetailsActivity.class);
                            startActivity(intent);
                        }
                    }
                }
            }
        } else if (FinalContents.getStoreList().equals("1") && FinalContents.getMyAddType().equals("门店")) {
            for (int i = 0; i < listData.size(); ++i) {
                if (listData.get(i).getStoreId().equals(itemName)) {
                    for (int j = 0; j < listData.size(); ++j) {
                        if (mContactModels.get(i).getName().equals((listData.get(j).getCompanyName() + listData.get(j).getStoreName()))) {
                            FinalContents.setAddtype1(listData.get(j).getStoreName());
                            FinalContents.setStoreManageId(listData.get(j).getStoreId());
                            FinalContents.setMyAddType("");
                            finish();
                        }
                    }

                }
            }
        } else if (FinalContents.getStoreList().equals("2") && FinalContents.getMyAddType().equals("")) {
//            TODO 公司详情
            for (int i = 0; i < listData.size(); ++i) {
                if (listData.get(i).getCompanyId().equals(itemName)) {
                    for (int j = 0; j < listData.size(); ++j) {
                        if (mContactModels.get(i).getName().equals(listData.get(j).getCompanyName())) {
                            if(FinalContents.getIdentity().equals("9")){
                                FinalContents.setStoreId(listData.get(j).getStoreId());
                                FinalContents.setCompanyId(listData.get(j).getCompanyId());
                                Intent intent = new Intent(StoreListActivity.this, StoreDetailsActivity.class);
                                FinalContents.setMyAddType("公司");
                                startActivity(intent);
                            }else if(FinalContents.getIdentity().equals("5") || FinalContents.getIdentity().equals("8")){
                                ToastUtil.showLongToast(StoreListActivity.this,"当前用户无查看公司详情权限");
                            }
                        }
                    }
                }
            }
        } else if (FinalContents.getStoreList().equals("2") && FinalContents.getMyAddType().equals("公司")) {
//          TODO 选择公司
            for (int i = 0; i < listData.size(); ++i) {
                if (listData.get(i).getCompanyId().equals(itemName)) {
                    for (int j = 0; j < listData.size(); ++j) {
                        if (mContactModels.get(i).getName().equals((listData.get(j).getCompanyName() + listData.get(j).getStoreName()))) {
                            FinalContents.setAddtype2(listData.get(j).getCompanyName());
                            FinalContents.setCompanyManageId(listData.get(j).getCompanyId());
                            FinalContents.setMyAddType("");
                            finish();
                        }
                    }

                }
            }
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        if (FinalContents.getMyAddType().equals("公司")) {
            store_list_tv2.setTextColor(Color.parseColor("#FFFFFF"));
            store_list_tv1.setTextColor(Color.parseColor("#FFFFFF"));
            store_list_ll4.setVisibility(View.VISIBLE);
            store_list_ll2.setVisibility(View.INVISIBLE);
            FinalContents.setStoreList("2");
            if (store_list_cb.isChecked()) {
                initDatam("","", "1");
            } else {
                initDatam("","", "");
            }
            FinalContents.setMyAddType("");
        } else if (FinalContents.getMyAddType().equals("门店")) {
            store_list_tv1.setTextColor(Color.parseColor("#FFFFFF"));
            store_list_tv2.setTextColor(Color.parseColor("#FFFFFF"));
            store_list_ll4.setVisibility(View.INVISIBLE);
            store_list_ll2.setVisibility(View.VISIBLE);
            FinalContents.setStoreList("1");
            FinalContents.setMyAddType("");
            if (store_list_cb.isChecked()) {
                initData("", "", "1","","","");
            } else {
                initData("", "", "","","","");
            }
        } else {
            if (store_list_cb.isChecked()) {
                initData("", "", "1","","","");
            } else {
                initData("", "", "","","","");
            }
            FinalContents.setStoreList("1");
        }

    }


//    @Override
//    public void onRefresh() {
//
//        if (layout.isRefreshing()) {//如果正在刷新
////            initView();
////            initHotList();
//            if(store_list_ll2.getVisibility() == View.VISIBLE){
//                if (store_list_cb.isChecked()) {
//                    initData("", "", "1","","","");
//                } else {
//                    initData("", "", "","","","");
//                }
//                FinalContents.setStoreList("1");
//            }else if(store_list_ll4.getVisibility() == View.VISIBLE){
//                FinalContents.setStoreList("2");
//                if (store_list_cb.isChecked()) {
//                    initDatam("","", "1");
//                } else {
//                    initDatam("","", "");
//                }
//            }
//            layout.setRefreshing(false);//取消刷新
//        }
//
//    }
}
