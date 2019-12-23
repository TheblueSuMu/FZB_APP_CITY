package com.xcy.fzbcity.all.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.adapter.RecyclerAdapter;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.database.BrokersListBean;
import com.xcy.fzbcity.all.database.BrokersListData;
import com.xcy.fzbcity.all.database.StoreListBean;
import com.xcy.fzbcity.all.database.StoreListData;
import com.xcy.fzbcity.all.modle.HotBean;
import com.xcy.fzbcity.all.persente.ContactModel;
import com.xcy.fzbcity.all.persente.LetterComparator;
import com.xcy.fzbcity.all.persente.PinnedHeaderDecoration;
import com.xcy.fzbcity.all.persente.SingleClick;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.CommonUtil;
import com.xcy.fzbcity.all.utils.ToastUtil;
import com.xcy.fzbcity.project_attache.adapter.ContactsAdapter;
import com.xcy.fzbcity.project_attache.adapter.StoreListAdapter;
import com.xcy.fzbcity.project_attache.view.CompanyDetailsActivity;
import com.xcy.fzbcity.project_attache.view.StoreDetailsActivity;

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

public class SearchInterfaceActivity extends AllActivity implements View.OnClickListener, StoreListAdapter.ItemOnClick {


    private LinearLayout search_img;
    private EditText search_edit_text;
    private LinearLayout search_l1;
    private LinearLayout search_l2;
    private LinearLayout search_l3;
    private LinearLayout search_ll3;
    private LinearLayout search_ll1;
    private LinearLayout search_ll2;
    private RelativeLayout nofound;
    private TextView textView;

    private TextView search_tv_1;
    private TextView search_tv_2;
    private TextView search_tv_3;

    private String hotUrl = FinalContents.getBaseUrl() + "/fangfang/app/v1/commonSelect/projectList?";

    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private String text = "";
    private String url = hotUrl + "&userId=" + FinalContents.getUserID() + "&city=" + FinalContents.getCityID() + "&projectType=3" + "&searchName=" + text;
    private List<HotBean.DataBean.RowsBean> hotlist = new ArrayList<>();
    private ImageView all_no_information;

    private LinearLayout search_l4;
    private LinearLayout search_ll4;

    int SearchIndext = 0;
    //经纪人
    private PinnedHeaderDecoration decoration;
    private ContactsAdapter mAdapter;
    private List<ContactModel> mContactModels;
    private List<BrokersListData> listData;
    private List<BrokersListBean.DataBean.RowsBean> rows;

    //门店/公司
    private StoreListAdapter mAdapterS;
    private Context context;
    private List<StoreListBean.DataBean.RowsBean> rowsStore;
    private List<StoreListData> listDataStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_interface);

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
            ToastUtil.showToast(this, "当前无网络，请检查网络后再进行登录");
        }
    }


    private void initView() {

        StatusBar.makeStatusBarTransparent(this);

        search_img = findViewById(R.id.search_img);
        search_edit_text = findViewById(R.id.search_edit_text);
        search_l1 = findViewById(R.id.search_l1);
        search_l2 = findViewById(R.id.search_l2);
        search_l3 = findViewById(R.id.search_l3);
        search_ll3 = findViewById(R.id.search_ll3);
        search_ll1 = findViewById(R.id.search_ll1);
        search_ll2 = findViewById(R.id.search_ll2);
        nofound = findViewById(R.id.nofound);
        textView = findViewById(R.id.search_text);

        recyclerView = findViewById(R.id.search_recyler);

        all_no_information = findViewById(R.id.all_no_information);

        search_l4 = findViewById(R.id.search_l4);
        search_ll4 = findViewById(R.id.search_ll4);
        search_tv_1 = findViewById(R.id.search_tv_1);
        search_tv_2 = findViewById(R.id.search_tv_2);
        search_tv_3 = findViewById(R.id.search_tv_3);

        search_img.setOnClickListener(this);
        search_l1.setOnClickListener(this);
        search_l2.setOnClickListener(this);
        search_l3.setOnClickListener(this);
        search_l4.setOnClickListener(this);


        search_edit_text.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (KeyEvent.KEYCODE_ENTER == i && KeyEvent.ACTION_DOWN == keyEvent.getAction()) {
//                    if (search_ll1.getVisibility() == View.VISIBLE) {
//                        if(FinalContents.getIdentity().equals("5") || FinalContents.getIdentity().equals("8") || FinalContents.getIdentity().equals("9")){
//                            String s = search_edit_text.getText().toString();
//                            initComPany("", s, "");
//                        }else {
//                            recyclerViewData("3");
//                        }
//                    } else if (search_ll2.getVisibility() == View.VISIBLE) {
//                        if(FinalContents.getIdentity().equals("5") || FinalContents.getIdentity().equals("8") || FinalContents.getIdentity().equals("9")){
//                            String s = search_edit_text.getText().toString();
//                            initStore("", s, "", "", "", "");
//                        }else {
//                            recyclerViewData("2");
//                        }
//                    } else if (search_ll3.getVisibility() == View.VISIBLE) {
//                        if(FinalContents.getIdentity().equals("5") || FinalContents.getIdentity().equals("8") || FinalContents.getIdentity().equals("9")){
//                            String s = search_edit_text.getText().toString();
//                            initBroker("", s, "", "", "", "");
//                        }else {
                            recyclerViewData("1");
////                        }
//                    } else if (search_ll4.getVisibility() == View.VISIBLE) {
//                        if(SearchIndext == 0){
//                            recyclerViewData("");
//                        }else if(SearchIndext == 1){
//                            recyclerViewData("1");
//                        }else if(SearchIndext == 2){
//                            recyclerViewData("2");
//                        }else if(SearchIndext == 3){
//                            recyclerViewData("3");
//                        }
//                    }
                    return true;
                }
                return false;
            }

        });

//        if (FinalContents.getIdentity().equals("5") || FinalContents.getIdentity().equals("8") || FinalContents.getIdentity().equals("9")) {
//            search_l4.setVisibility(View.VISIBLE);
//            search_tv_1.setText("经纪公司");
//            search_tv_2.setText("经纪门店");
//            search_tv_3.setText("经纪人");
//            initComPany("", "", "");
//            FinalContents.setStoreList("2");
//        } else {
//            search_l4.setVisibility(View.GONE);
//            search_tv_1.setText("旅居房产");
//            search_tv_2.setText("海外房产");
//            search_tv_3.setText("城市房产");
            recyclerViewData("1");
//        }

    }

    //项目列表数据
    @SuppressLint("WrongConstant")
    private void recyclerViewData(String projecttype) {
        text = search_edit_text.getText().toString();
        Log.i("bbb", "用户名ID：" + FinalContents.getUserID());
        Log.i("bbb", "城市ID：" + FinalContents.getCityID());
        Log.i("bbb", "城市公司ID：" + FinalContents.getCityID());

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<HotBean> hotBean = fzbInterface.getHotBean(FinalContents.getUserID(), FinalContents.getCityID(), projecttype, text, "1000");
        hotBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HotBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HotBean hotBean) {
                        HotBean.DataBean hotBeanData = hotBean.getData();
                        hotlist = hotBeanData.getRows();
                        Log.i("调接口", "接收值" + hotlist.size());
                        //在此处修改布局排列方向
                        if (hotlist.size() == 0) {
                            all_no_information.setVisibility(View.VISIBLE);
                            recyclerView.setVisibility(View.GONE);
//                            recyclerAdapter.notifyDataSetChanged();
                        } else {
                            all_no_information.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.VISIBLE);
                            nofound.setVisibility(View.GONE);
                            LinearLayoutManager layoutManager = new LinearLayoutManager(SearchInterfaceActivity.this);
                            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerAdapter = new RecyclerAdapter(hotlist);
                            recyclerAdapter.setProject(FinalContents.getProject());
                            recyclerView.setAdapter(recyclerAdapter);
                            recyclerAdapter.notifyDataSetChanged();

                            recyclerAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickLisenter() {
                                @Override
                                public void onItemClick(int postion) {
                                    if (FinalContents.getProject().equals("1")) {
                                        FinalContents.setProjectType(hotlist.get(postion).getProjectType());
                                        FinalContents.setProjectName(hotlist.get(postion).getProjectName());
                                        FinalContents.setProjectSearchID(hotlist.get(postion).getProjectId());
                                        FinalContents.setGuideRuleId(hotlist.get(postion).getGuideRuleId());
                                        FinalContents.setProjectID(hotlist.get(postion).getProjectId());
                                        finish();
                                    }
                                }
                            });
                        }


                        hideInput();
                    }

                    @Override
                    public void onError(Throwable e) {
                        all_no_information.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                        Log.i("MyCL", "SearchInterfaceActivity错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

//        textView.setVisibility(View.GONE);
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            //用来标记是否正在向最后一个滑动
            boolean isSlidingToLast = false;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                // 当不滚动时
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    //获取最后一个完全显示的ItemPosition
                    int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
                    int totalItemCount = manager.getItemCount();

                    // 判断是否滚动到底部
                    if (lastVisibleItem == (totalItemCount - 1)) {
                        //加载更多功能的代码
                        textView.setVisibility(View.VISIBLE);
                    }
                } else {
                    textView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //dx用来判断横向滑动方向，dy用来判断纵向滑动方向
                if (dx > 0) {
                    //大于0表示正在向右滚动
                    isSlidingToLast = true;
                } else {
                    //小于等于0表示停止或向左滚动
                    isSlidingToLast = false;
                }
            }
        });


    }

    //点击事件
    @SingleClick(1000)
    @Override
    public void onClick(View view) {


        switch (view.getId()) {
            case R.id.search_img:
                finish();
                break;
            case R.id.search_l1:
                search_ll1.setVisibility(View.VISIBLE);
                search_ll2.setVisibility(View.INVISIBLE);
                search_ll3.setVisibility(View.INVISIBLE);
                search_ll4.setVisibility(View.INVISIBLE);
//                if (FinalContents.getIdentity().equals("5") || FinalContents.getIdentity().equals("8") || FinalContents.getIdentity().equals("9")) {
//                    if (listDataStore.size() == 0) {
//
//                    } else {
//                        recyclerView.removeAllViews();
//                        mContactModels.clear();
//                        listDataStore.clear();
//                        mAdapterS.notifyDataSetChanged();
//
//                    }
//                    initComPany("", "", "");
//                    FinalContents.setStoreList("2");
//                } else {
//                    recyclerViewData("3");
//                }
                break;
            case R.id.search_l2:
                search_ll2.setVisibility(View.VISIBLE);
                search_ll1.setVisibility(View.INVISIBLE);
                search_ll3.setVisibility(View.INVISIBLE);
                search_ll4.setVisibility(View.INVISIBLE);
//                if (FinalContents.getIdentity().equals("5") || FinalContents.getIdentity().equals("8") || FinalContents.getIdentity().equals("9")) {
//                    if (listDataStore.size() == 0) {
//
//                    } else {
//                        recyclerView.removeAllViews();
//                        mContactModels.clear();
//                        listDataStore.clear();
//                        mAdapterS.notifyDataSetChanged();
//                    }
//                    initStore("", "", "", "", "", "");
//                    FinalContents.setStoreList("1");
//                } else {
//                    recyclerViewData("2");
//                }
                break;
            case R.id.search_l3:
                search_ll2.setVisibility(View.INVISIBLE);
                search_ll1.setVisibility(View.INVISIBLE);
                search_ll3.setVisibility(View.VISIBLE);
                search_ll4.setVisibility(View.INVISIBLE);
//                if (FinalContents.getIdentity().equals("5") || FinalContents.getIdentity().equals("8") || FinalContents.getIdentity().equals("9")) {
////                    if(listData.size() == 0){
////
////                    }else {
////                        recyclerView.removeAllViews();
////                        mContactModels.clear();
////                        listData.clear();
////                        mAdapter.notifyDataSetChanged();
////
////                    }
//                    initBroker("", "", "", "", "", "");
//                } else {
//                    recyclerViewData("1");
//                }
                break;
            case R.id.search_l4:
                initPopWindow();
                break;
        }

    }

    //弹窗
    private void initPopWindow() {

        final List<String> list = new ArrayList<>();
        list.add("全部房产");
        list.add("城市房产");
        list.add("海外房产");
        list.add("旅居房产");


        //      监听选中
        OptionsPickerView pvOptions = new OptionsPickerBuilder(SearchInterfaceActivity.this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //               返回的分别是三个级别的选中位置
                //              展示选中数据
                if (options1 == 0) {
                    SearchIndext = 0;
                    recyclerViewData("");
                } else if (options1 == 1) {
                    SearchIndext = 1;
                    recyclerViewData("1");
                } else if (options1 == 2) {
                    SearchIndext = 2;
                    recyclerViewData("2");
                } else if (options1 == 3) {
                    SearchIndext = 3;
                    recyclerViewData("3");
                }
                search_ll2.setVisibility(View.INVISIBLE);
                search_ll1.setVisibility(View.INVISIBLE);
                search_ll3.setVisibility(View.INVISIBLE);
                search_ll4.setVisibility(View.VISIBLE);
            }
        }).setOutSideCancelable(false)//点击背的地方不消失
                .build();//创建
        pvOptions.setSelectOptions(SearchIndext);
        //      把数据绑定到控件上面
        pvOptions.setPicker(list);
        //      展示
        pvOptions.show();
    }

    //经纪人列表数据
    private void initBroker(String storeId, String search, String status, String type, String startData, String endData) {
        listData = new ArrayList<>();
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
        Log.i("MyCL", "--1-- FinalContents.getUserID()：" + FinalContents.getUserID());
        Observable<BrokersListBean> brokersListBean = fzbInterface.getBrokersListBean(FinalContents.getCompanyId(), storeId, search, status, FinalContents.getUserID(), "1000", type, startData, endData);
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
                            recyclerView.setVisibility(View.VISIBLE);
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
                            initBrokers();
                        } else {
                            all_no_information.setVisibility(View.VISIBLE);
                            recyclerView.setVisibility(View.GONE);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("经纪人列表", "错误信息:" + e.getMessage());
                        recyclerView.setVisibility(View.GONE);
                        all_no_information.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void initBrokers() {

        decoration.registerTypePinnedHeader(1, new PinnedHeaderDecoration.PinnedHeaderCreator() {
            @Override
            public boolean create(RecyclerView parent, int adapterPosition) {
                return true;
            }
        });
        recyclerView.addItemDecoration(decoration);
        mAdapter = new ContactsAdapter();
        Collections.sort(mContactModels, new LetterComparator());
        mAdapter.setContacts(mContactModels);
        mAdapter.setListData(listData);
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

    }

    //公司列表数据
    private void initComPany(String companyId, String search, String status) {

        context = SearchInterfaceActivity.this;
        mContactModels = new ArrayList<>();
        listDataStore = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        decoration = new PinnedHeaderDecoration();

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<StoreListBean> storeList = fzbInterface.getCompanList(companyId, search, status, FinalContents.getUserID(), "1000");
        storeList.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<StoreListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(StoreListBean storeListBean) {
                        listDataStore.clear();
                        mContactModels.clear();
                        rowsStore = storeListBean.getData().getRows();
                        if (rowsStore.size() != 0) {
                            all_no_information.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.VISIBLE);
                            for (int i = 0; i < rowsStore.size(); ++i) {
                                if (rowsStore.get(i).getCompanyName().equals("")) {

                                } else {
                                    ContactModel contactModel = new ContactModel(SearchInterfaceActivity.this.rowsStore.get(i).getCompanyName() + rowsStore.get(i).getStoreName());
                                    StoreListData storeListData = new StoreListData();

                                    storeListData.setStoreName(rowsStore.get(i).getStoreName());
                                    storeListData.setStoreId(rowsStore.get(i).getStoreId());
                                    storeListData.setShopownerName(rowsStore.get(i).getShopownerName());
                                    storeListData.setShopownerPhone(rowsStore.get(i).getShopownerPhone());
                                    storeListData.setAgentNum(rowsStore.get(i).getAgentNum());
                                    storeListData.setStatus(rowsStore.get(i).getStatus());
                                    storeListData.setCompanyId(rowsStore.get(i).getCompanyId());
                                    storeListData.setCompanyName(rowsStore.get(i).getCompanyName());
                                    storeListData.setStoreIdCode(rowsStore.get(i).getStoreIdCode());
                                    storeListData.setCompanyAddress(rowsStore.get(i).getCompanyAddress());
                                    storeListData.setStoreNum(rowsStore.get(i).getStoreNum());
                                    storeListData.setAttacheName(rowsStore.get(i).getAttacheName());
                                    storeListData.setAttachePhone(rowsStore.get(i).getAttachePhone());
                                    storeListData.setAttacheIdentity(rowsStore.get(i).getAttacheIdentity());
                                    storeListData.setState(rowsStore.get(i).getState());
                                    storeListData.setIsMy(rowsStore.get(i).getIsMy());

                                    mContactModels.add(contactModel);
                                    listDataStore.add(storeListData);
                                }

                            }
//                            search_l2.setClickable(true);
//                            avi.hide();
//                            avi.setVisibility(View.GONE);
                            initDataStore();
                        } else {
                            all_no_information.setVisibility(View.VISIBLE);
                            recyclerView.setVisibility(View.GONE);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
//                        avi.setVisibility(View.GONE);
//                        initDatas();
                        all_no_information.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                        Log.i("专员公司列表", "错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    //门店列表数据
    private void initStore(String companyId, String search, String status, String type, String startData, String endData) {

        context = SearchInterfaceActivity.this;
        mContactModels = new ArrayList<>();
        listDataStore = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        decoration = new PinnedHeaderDecoration();

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        final Observable<StoreListBean> storeList = fzbInterface.getStoreList(companyId, search, status, FinalContents.getUserID(), "1000", type, startData, endData);
        storeList.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<StoreListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(StoreListBean storeListBean) {
                        listDataStore.clear();
                        mContactModels.clear();
                        rowsStore = storeListBean.getData().getRows();
                        if (rowsStore.size() != 0) {
                            all_no_information.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.VISIBLE);
                            for (int i = 0; i < rowsStore.size(); ++i) {
                                if (rowsStore.get(i).getCompanyName().equals("")) {

                                } else {
                                    ContactModel contactModel = new ContactModel(rowsStore.get(i).getCompanyName() + "" + rowsStore.get(i).getStoreName());
                                    StoreListData storeListData = new StoreListData();

                                    storeListData.setStoreName(rowsStore.get(i).getStoreName());
                                    storeListData.setStoreId(rowsStore.get(i).getStoreId());
                                    storeListData.setShopownerName(rowsStore.get(i).getShopownerName());
                                    storeListData.setShopownerPhone(rowsStore.get(i).getShopownerPhone());
                                    storeListData.setAgentNum(rowsStore.get(i).getAgentNum());
                                    storeListData.setStatus(rowsStore.get(i).getStatus());
                                    storeListData.setCompanyId(rowsStore.get(i).getCompanyId());
                                    storeListData.setCompanyName(rowsStore.get(i).getCompanyName());
                                    storeListData.setStoreIdCode(rowsStore.get(i).getStoreIdCode());
                                    storeListData.setCompanyAddress(rowsStore.get(i).getCompanyAddress());
                                    storeListData.setStoreNum(rowsStore.get(i).getStoreNum());
                                    storeListData.setAttacheName(rowsStore.get(i).getAttacheName());
                                    storeListData.setAttachePhone(rowsStore.get(i).getAttachePhone());
                                    storeListData.setAttacheIdentity(rowsStore.get(i).getAttacheIdentity());
                                    storeListData.setState(rowsStore.get(i).getState());
                                    storeListData.setIsMy(rowsStore.get(i).getIsMy());

                                    mContactModels.add(contactModel);
                                    listDataStore.add(storeListData);
                                }
                            }
//                            search_l2.setClickable(true);
//                            avi.hide();
//                            avi.setVisibility(View.GONE);
                            initDataStore();
                        } else {
                            all_no_information.setVisibility(View.VISIBLE);
                            recyclerView.setVisibility(View.GONE);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
//                        avi.setVisibility(View.GONE);
//                        initDatas();
                        all_no_information.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                        Log.i("专员门店或公司列表", "错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void initDataStore() {

        decoration.registerTypePinnedHeader(1, new PinnedHeaderDecoration.PinnedHeaderCreator() {
            @Override
            public boolean create(RecyclerView parent, int adapterPosition) {
                return true;
            }
        });
        recyclerView.addItemDecoration(decoration);
        mAdapterS = new StoreListAdapter();
        Collections.sort(mContactModels, new LetterComparator());
        mAdapterS.setContacts(mContactModels);
        mAdapterS.setListData(listDataStore);
        mAdapterS.setItemOnClick(this);
        recyclerView.setAdapter(mAdapterS);
        mAdapterS.notifyDataSetChanged();

    }

    //公司/门店item点击事件
    @Override
    public void itemClick(String itemName) {

        if (FinalContents.getStoreList().equals("1") && FinalContents.getMyAddType().equals("")) {
//          TODO 门店详情
            for (int i = 0; i < listDataStore.size(); ++i) {
                if (listDataStore.get(i).getStoreId().equals(itemName)) {
                    for (int j = 0; j < listDataStore.size(); ++j) {
                        if (mContactModels.get(i).getName().equals((listDataStore.get(j).getCompanyName() + listDataStore.get(j).getStoreName()))) {
                            FinalContents.setStoreId(listDataStore.get(j).getStoreId());
                            FinalContents.setMyAddType("门店");
                            Intent intent = new Intent(SearchInterfaceActivity.this, CompanyDetailsActivity.class);
                            startActivity(intent);
                        }
                    }
                }
            }
        }
//        else if (FinalContents.getStoreList().equals("1") && FinalContents.getMyAddType().equals("门店")) {
//            for (int i = 0; i < listDataStore.size(); ++i) {
//                if (listDataStore.get(i).getStoreId().equals(itemName)) {
//                    for (int j = 0; j < listDataStore.size(); ++j) {
//                        if (mContactModels.get(i).getName().equals((listDataStore.get(j).getCompanyName() + listDataStore.get(j).getStoreName()))) {
//                            FinalContents.setAddtype1(listDataStore.get(j).getStoreName());
//                            FinalContents.setStoreManageId(listDataStore.get(j).getStoreId());
//                            FinalContents.setMyAddType("");
//                            finish();
//                        }
//                    }
//
//                }
//            }
//        }
        else if (FinalContents.getStoreList().equals("2") && FinalContents.getMyAddType().equals("")) {
//            TODO 公司详情
            for (int i = 0; i < listDataStore.size(); ++i) {
                if (listDataStore.get(i).getCompanyId().equals(itemName)) {
                    for (int j = 0; j < listDataStore.size(); ++j) {
                        if (mContactModels.get(i).getName().equals(listDataStore.get(j).getCompanyName())) {
                            if (FinalContents.getIdentity().equals("9")) {
                                FinalContents.setStoreId(listDataStore.get(j).getStoreId());
                                FinalContents.setCompanyId(listDataStore.get(j).getCompanyId());
                                Intent intent = new Intent(SearchInterfaceActivity.this, StoreDetailsActivity.class);
                                FinalContents.setMyAddType("公司");
                                startActivity(intent);
                            } else if (FinalContents.getIdentity().equals("5") || FinalContents.getIdentity().equals("8")) {
                                ToastUtil.showLongToast(SearchInterfaceActivity.this, "当前用户无查看公司详情权限");
                            }
                        }
                    }
                }
            }
        }
//        else if (FinalContents.getStoreList().equals("2") && FinalContents.getMyAddType().equals("公司")) {
////          TODO 选择公司
//            for (int i = 0; i < listDataStore.size(); ++i) {
//                if (listDataStore.get(i).getCompanyId().equals(itemName)) {
//                    for (int j = 0; j < listDataStore.size(); ++j) {
//                        if (mContactModels.get(i).getName().equals((listDataStore.get(j).getCompanyName() + listDataStore.get(j).getStoreName()))) {
//                            FinalContents.setAddtype2(listDataStore.get(j).getCompanyName());
//                            FinalContents.setCompanyManageId(listDataStore.get(j).getCompanyId());
//                            FinalContents.setMyAddType("");
//                            finish();
//                        }
//                    }
//
//                }
//            }
//        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        FinalContents.setMyAddType("");

    }
}
