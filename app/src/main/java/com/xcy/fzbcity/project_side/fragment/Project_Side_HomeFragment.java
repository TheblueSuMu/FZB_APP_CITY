package com.xcy.fzbcity.project_side.fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.bumptech.glide.Glide;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.CityContents;
import com.xcy.fzbcity.all.api.Connector;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.api.NewlyIncreased;
import com.xcy.fzbcity.all.fragment.AllFragment;
import com.xcy.fzbcity.all.modle.HomeBean;
import com.xcy.fzbcity.all.modle.SideHomeBean;
import com.xcy.fzbcity.all.modle.UserBean;
import com.xcy.fzbcity.all.persente.MyLinearLayoutManager;
import com.xcy.fzbcity.all.persente.SingleClick;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.project_side.adapter.Project_Side_HomeRecyclerAdapter;
import com.xcy.fzbcity.project_side.view.CheckPendingTheProjectEndActivity;
import com.xcy.fzbcity.project_side.view.CommissionTheProjectEndActivity;
import com.xcy.fzbcity.project_side.view.InitiatedTheReviewActivity;
import com.xcy.fzbcity.project_side.view.MyClientActivity;
import com.xcy.fzbcity.project_side.view.MyProjectActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
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
public class Project_Side_HomeFragment extends AllFragment implements View.OnClickListener {

    PtrClassicFrameLayout mPtrClassicFrameLayout;

    RecyclerView rv_home_the_project_end;

    ImageView img_home_the_project_end;
    TextView name_home_the_project_end;
    TextView city_home_the_project_end;
    TextView position_home_the_project_end;
    TextView shop_home_the_project_end;

    RadioGroup rg_home_the_project_end;
    RadioButton rb1_home_the_project_end;
    RadioButton rb2_home_the_project_end;
    RadioButton rb3_home_the_project_end;
    RadioButton rb4_home_the_project_end;

    TextView time1_home_the_project_end;
    TextView time2_home_the_project_end;

    TextView tv1_home_the_project_end;
    TextView tv2_home_the_project_end;
    TextView tv3_home_the_project_end;
    TextView tv5_home_the_project_end;
    TextView tv6_home_the_project_end;
    TextView tv7_home_the_project_end;
    TextView tv8_home_the_project_end;
    TextView tv9_home_the_project_end;
    TextView tv10_home_the_project_end;
    TextView tv11_home_the_project_end;
    TextView tv12_home_the_project_end;
    TextView tv13_home_the_project_end;
    TextView tv14_home_the_project_end;



    LinearLayout time1_ll1_home_the_project_end;
    LinearLayout layout1;
    LinearLayout layout2;
    LinearLayout layout3;
    LinearLayout layout4;
    LinearLayout layout5;
    LinearLayout layout6;
    LinearLayout layout7;
    LinearLayout layout8;
    LinearLayout layout9;
    LinearLayout layout10;
    LinearLayout AuditList;
    LinearLayout myproject;
    LinearLayout mypost;


    private Intent intent;
    private View view;

    private String type = "";
    private String beforeDate = "";
    private String afterDate = "";
    private ImageView all_no_information;
    private ScrollView project_side_scrollview;

    ImageView project_side_img_1;
    ImageView project_side_img_2;
    ImageView project_side_img_3;
    ImageView project_side_img_4;
    ImageView project_side_img_5;
    ImageView project_side_img_6;
    ImageView project_side_img_7;
    ImageView project_side_img_8;
    private int year;
    private int month;
    private int dayOfMonth;
    private String string;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.project_side_fragment_home_the_project_end, container, false);
        initfvb();
        return view;
    }

    //TODO 声明
    private void initfvb() {
        StatusBar.makeStatusBarTransparent(getActivity());

        mPtrClassicFrameLayout = view.findViewById(R.id.store_house_ptr_frame);
        project_side_scrollview = view.findViewById(R.id.project_side_scrollview);
        project_side_scrollview.smoothScrollTo(0,20);
        FinalContents.setTiaozhuang("");
        all_no_information = view.findViewById(R.id.all_no_information);
        img_home_the_project_end = view.findViewById(R.id.img_home_the_project_end);
        name_home_the_project_end = view.findViewById(R.id.name_home_the_project_end);
        city_home_the_project_end = view.findViewById(R.id.city_home_the_project_end);
        position_home_the_project_end = view.findViewById(R.id.position_home_the_project_end);
        shop_home_the_project_end = view.findViewById(R.id.shop_home_the_project_end);
        rg_home_the_project_end = view.findViewById(R.id.rg_home_the_project_end);
        rb1_home_the_project_end = view.findViewById(R.id.rb1_home_the_project_end);
        rb2_home_the_project_end = view.findViewById(R.id.rb2_home_the_project_end);
        rb3_home_the_project_end = view.findViewById(R.id.rb3_home_the_project_end);
        rb4_home_the_project_end = view.findViewById(R.id.rb4_home_the_project_end);

        //小红点
        project_side_img_1 = view.findViewById(R.id.project_side_img_1);//报备
        project_side_img_2 = view.findViewById(R.id.project_side_img_2);//到访
        project_side_img_3 = view.findViewById(R.id.project_side_img_3);//登岛
        project_side_img_4 = view.findViewById(R.id.project_side_img_4);//认筹
        project_side_img_5 = view.findViewById(R.id.project_side_img_5);//成交
        project_side_img_6 = view.findViewById(R.id.project_side_img_6);//失效
        project_side_img_7 = view.findViewById(R.id.project_side_img_7);//待我审核
        project_side_img_8 = view.findViewById(R.id.project_side_img_8);//我发起的审核

        myproject = view.findViewById(R.id.myproject);
        mypost = view.findViewById(R.id.mypost);

        layout1 = view.findViewById(R.id.layout1);
        layout2 = view.findViewById(R.id.layout2);
        layout3 = view.findViewById(R.id.layout3);
        layout4 = view.findViewById(R.id.layout4);
        layout5 = view.findViewById(R.id.layout5);
        layout6 = view.findViewById(R.id.layout6);
        layout7 = view.findViewById(R.id.layout7);
        layout8 = view.findViewById(R.id.layout8);
        layout9 = view.findViewById(R.id.layout9);
        layout10 = view.findViewById(R.id.layout10);


        AuditList = view.findViewById(R.id.AuditList);

        time1_home_the_project_end = view.findViewById(R.id.time1_home_the_project_end);
        time2_home_the_project_end = view.findViewById(R.id.time2_home_the_project_end);

        tv1_home_the_project_end = view.findViewById(R.id.tv1_home_the_project_end);
        tv2_home_the_project_end = view.findViewById(R.id.tv2_home_the_project_end);
        tv3_home_the_project_end = view.findViewById(R.id.tv3_home_the_project_end);
        tv5_home_the_project_end = view.findViewById(R.id.tv5_home_the_project_end);
        tv6_home_the_project_end = view.findViewById(R.id.tv6_home_the_project_end);
        tv7_home_the_project_end = view.findViewById(R.id.tv7_home_the_project_end);
        tv8_home_the_project_end = view.findViewById(R.id.tv8_home_the_project_end);
        tv9_home_the_project_end = view.findViewById(R.id.tv9_home_the_project_end);
        tv10_home_the_project_end = view.findViewById(R.id.tv10_home_the_project_end);
        tv11_home_the_project_end = view.findViewById(R.id.tv11_home_the_project_end);
        tv12_home_the_project_end = view.findViewById(R.id.tv12_home_the_project_end);
        tv13_home_the_project_end = view.findViewById(R.id.tv13_home_the_project_end);
        tv14_home_the_project_end = view.findViewById(R.id.tv14_home_the_project_end);

        time1_ll1_home_the_project_end = view.findViewById(R.id.time1_ll1_home_the_project_end);

        rv_home_the_project_end = view.findViewById(R.id.rv_home_the_project_end);
        rv_home_the_project_end.setFocusable(false);

        time1_home_the_project_end.setOnClickListener(this);
        time2_home_the_project_end.setOnClickListener(this);


        rb1_home_the_project_end.setOnClickListener(this);
        rb2_home_the_project_end.setOnClickListener(this);
        rb3_home_the_project_end.setOnClickListener(this);
        rb4_home_the_project_end.setOnClickListener(this);

        layout1.setOnClickListener(this);
        layout2.setOnClickListener(this);
        layout3.setOnClickListener(this);
        layout4.setOnClickListener(this);
        layout5.setOnClickListener(this);
        layout6.setOnClickListener(this);
        layout7.setOnClickListener(this);
        layout8.setOnClickListener(this);
        layout9.setOnClickListener(this);
        layout10.setOnClickListener(this);
        AuditList.setOnClickListener(this);
        mypost.setOnClickListener(this);
        myproject.setOnClickListener(this);

        mPtrClassicFrameLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPtrClassicFrameLayout.refreshComplete();
                        mPtrClassicFrameLayout.setLastUpdateTimeKey("2017-2-10");
                        initData();
                    }
                }, 1000);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });
        initData();
    }

    //TODO 赋值
    private void initData() {

        initUser();
        initViewData();
        initList();
    }

    //TODO 首页用户信息赋值
    private void initUser() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<UserBean> userMessage = fzbInterface.getUserBean(FinalContents.getUserID());
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(UserBean userBean) {
                        try {
                            Glide.with(getActivity()).load(FinalContents.getImageUrl() + userBean.getData().getPhoto()).into(img_home_the_project_end);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        name_home_the_project_end.setText(userBean.getData().getName());
                        city_home_the_project_end.setText(userBean.getData().getCity());
//                        shop_home_the_project_end.setText(userBean.getData().getCityName());
                        shop_home_the_project_end.setText(userBean.getData().getCity() + "  " + userBean.getData().getCityName());
                        position_home_the_project_end.setText("专案");
                        FinalContents.setCityID(userBean.getData().getCityId());

                        Connector.setUserBean(userBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("列表数据获取错误", "错误" + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //TODO 首页时间赋值
    private void initDate() {
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        string = String.format(Locale.getDefault(), "%d.%02d.%02d", year, month + 1, dayOfMonth);
        time1_home_the_project_end.setText("<" + string);
        time2_home_the_project_end.setText("-" + string + " >");

        beforeDate = string;
        afterDate = string;

        //            TODO 开始时间
        time1_home_the_project_end.setOnClickListener(new View.OnClickListener() {
            @SingleClick(1000)
            @Override
            public void onClick(View view) {
                initTimePickerView1();
            }
        });
        //                TODO 结束时间
        time2_home_the_project_end.setOnClickListener(new View.OnClickListener() {
            @SingleClick(1000)
            @Override
            public void onClick(View view) {
                initTimePickerView2();
            }
        });
    }

    //TODO 首页运营数据   开始时间选择
    private void initTimePickerView1(){
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(year, month, dayOfMonth-15);
        final Calendar endDate = Calendar.getInstance();
        endDate.set(year, month, dayOfMonth+15);
        TimePickerView pvTime = new TimePickerBuilder(getContext(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                time1_home_the_project_end.setText("<" + getTime2(date));
                beforeDate = getTime2(date);
                NewlyIncreased.setStartDate(getTime2(date));
                NewlyIncreased.setYJstartDate(getTime2(date));
                initViewData();
            }
        })

                .setType(new boolean[]{true, true, true, false, false, false}) //年月日时分秒 的显示与否，不设置则默认全部显示
                .setLabel("年", "月", "日", "", "", "")//默认设置为年月日时分秒
                .isCenterLabel(false)
                .setDate(selectedDate)
                .setLineSpacingMultiplier(1.2f)
                .setTextXOffset(-10, 0,10, 0, 0, 0)//设置X轴倾斜角度[ -90 , 90°]
                .setRangDate(startDate, endDate)
                .build();
        pvTime.show();
    }

    //TODO 首页运营数据   结束时间选择
    private void initTimePickerView2(){
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(year, month, dayOfMonth-15);
        final Calendar endDate = Calendar.getInstance();
        endDate.set(year, month, dayOfMonth+15);
        TimePickerView pvTime = new TimePickerBuilder(getContext(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                time2_home_the_project_end.setText("-" + getTime2(date) + " >");
                afterDate = getTime2(date);
                NewlyIncreased.setEndDate(getTime2(date));
                NewlyIncreased.setYJendDate(getTime2(date));
                initViewData();
            }
        })

                .setType(new boolean[]{true, true, true, false, false, false}) //年月日时分秒 的显示与否，不设置则默认全部显示
                .setLabel("年", "月", "日", "", "", "")//默认设置为年月日时分秒
                .isCenterLabel(false)
                .setDate(selectedDate)
                .setLineSpacingMultiplier(1.2f)
                .setTextXOffset(-10, 0,10, 0, 0, 0)//设置X轴倾斜角度[ -90 , 90°]
                .setRangDate(startDate, endDate)
                .build();
        pvTime.show();
    }

    //TODO 首页运营数据赋值
    private void initViewData() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<HomeBean> userMessage = fzbInterface.getHomeList(FinalContents.getUserID(), beforeDate, afterDate, type,"1000");
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(HomeBean homeBean) {
                        Log.i("运营数据","运营数据：");
                        tv1_home_the_project_end.setText("" + homeBean.getData().getProdjectCount());
                        tv2_home_the_project_end.setText("" + homeBean.getData().getReportNumber());
                        tv3_home_the_project_end.setText("" + homeBean.getData().getAccessingNumber());
                        tv5_home_the_project_end.setText("" + homeBean.getData().getIsIslandNumber());
                        tv6_home_the_project_end.setText("" + homeBean.getData().getEarnestMoneyNumber());
                        tv7_home_the_project_end.setText("" + homeBean.getData().getTradeNumber());
                        tv8_home_the_project_end.setText("" + homeBean.getData().getLoseEfficacy());
                        tv9_home_the_project_end.setText("" + homeBean.getData().getReceivableMoney());
                        tv10_home_the_project_end.setText("" + homeBean.getData().getBackMoney());
                        tv11_home_the_project_end.setText("" + homeBean.getData().getInvoiceMoney());
                        tv12_home_the_project_end.setText("" + homeBean.getData().getSurplusMoney());
                        tv13_home_the_project_end.setText("" + homeBean.getData().getAuditNumber());
                        tv14_home_the_project_end.setText("" + homeBean.getData().getApplyCount());



                        if (homeBean.getData().getReportNoRead() == 1) {//    TODO    报备  未读
                            project_side_img_1.setVisibility(View.VISIBLE);
                        }else {
                            project_side_img_1.setVisibility(View.GONE);
                        }

                        if (homeBean.getData().getAccessingNoRead() == 1) {//    TODO    到访  未读
                            project_side_img_2.setVisibility(View.VISIBLE);
                        }else {
                            project_side_img_2.setVisibility(View.GONE);
                        }

                        if (homeBean.getData().getIsIslandNoRead() == 1) {//    TODO    登岛  未读
                            project_side_img_3.setVisibility(View.VISIBLE);
                        }else {
                            project_side_img_3.setVisibility(View.GONE);
                        }

                        if (homeBean.getData().getEarnestMoneyNoRead() == 1) {//    TODO    认筹  未读
                            project_side_img_4.setVisibility(View.VISIBLE);
                        }else {
                            project_side_img_4.setVisibility(View.GONE);
                        }

                        if (homeBean.getData().getTradeNoRead() == 1) {//    TODO    成交  未读
                            project_side_img_5.setVisibility(View.VISIBLE);
                        }else {
                            project_side_img_5.setVisibility(View.GONE);
                        }

                        if (homeBean.getData().getLoseNoRead() == 1) {//    TODO    失效  未读
                            project_side_img_6.setVisibility(View.VISIBLE);
                        }else {
                            project_side_img_6.setVisibility(View.GONE);
                        }

                        if (homeBean.getData().getAuditNoRead().equals("1")) {//    TODO    待审核  未读
                            project_side_img_7.setVisibility(View.VISIBLE);
                        }else {
                            project_side_img_7.setVisibility(View.GONE);
                        }

                        if (homeBean.getData().getApplyNoRead().equals("1")) {//    TODO    我发起的审核  未读
                            project_side_img_8.setVisibility(View.VISIBLE);
                        }else {
                            project_side_img_8.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("列表数据获取错误", "错误" + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //TODO 首页列表数据赋值
    private void initList() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<SideHomeBean> userMessage = fzbInterface.getHomeBeanList(FinalContents.getUserID(), "","1000");
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SideHomeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(SideHomeBean homeListBean) {
                        if (homeListBean.getCode().equals("1")) {
                            SideHomeBean.DataBean homeListBeanData = homeListBean.getData();
                            List<SideHomeBean.DataBean.RowsBean> homeListBeanDataRows = homeListBeanData.getRows();
                            if (homeListBeanDataRows.size() != 0) {
                                all_no_information.setVisibility(View.GONE);
                                //在此处修改布局排列方向
                                rv_home_the_project_end.setVisibility(View.VISIBLE);
                                MyLinearLayoutManager layoutManager = new MyLinearLayoutManager(view.getContext());
                                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                                layoutManager.setScrollEnabled(false);
                                rv_home_the_project_end.setLayoutManager(layoutManager);
                                Project_Side_HomeRecyclerAdapter recyclerAdapter = new Project_Side_HomeRecyclerAdapter(homeListBeanDataRows);
                                rv_home_the_project_end.setNestedScrollingEnabled(false);
                                rv_home_the_project_end.setAdapter(recyclerAdapter);
                                recyclerAdapter.notifyDataSetChanged();
                            }else {
                                all_no_information.setVisibility(View.VISIBLE);
                                rv_home_the_project_end.setVisibility(View.GONE);
                            }

                        } else {
                            rv_home_the_project_end.setVisibility(View.GONE);
                            all_no_information.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        rv_home_the_project_end.setVisibility(View.GONE);
                        all_no_information.setVisibility(View.VISIBLE);
                        Log.i("列表数据获取错误", "错误" + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //TODO 点击事件
    @SingleClick(1000)
    @Override
    public void onClick(View view) {
        CityContents.setIsRead("1");
        if (NewlyIncreased.getTag().equals("3")) {
            NewlyIncreased.setStartDate(beforeDate);
            NewlyIncreased.setEndDate(afterDate);
        }else {
            NewlyIncreased.setStartDate("");
            NewlyIncreased.setEndDate("");
        }

        if (NewlyIncreased.getYJType().equals("3")) {
            NewlyIncreased.setYJstartDate(beforeDate);
            NewlyIncreased.setYJendDate(afterDate);
        }else {
            NewlyIncreased.setYJstartDate("");
            NewlyIncreased.setYJendDate("");
        }
        switch (view.getId()) {
//                TODO 我的项目
            case R.id.myproject:
                intent = new Intent(getContext(), MyProjectActivity.class);
                startActivity(intent);
                break;
//                TODO  报备
            case R.id.layout1:
                intent = new Intent(getContext(), MyClientActivity.class);
                intent.putExtra("client", "1");
                startActivity(intent);
                break;
//                TODO  到访
            case R.id.layout2:
                intent = new Intent(getContext(), MyClientActivity.class);
                intent.putExtra("client", "2");
                startActivity(intent);
                break;
//               TODO  登岛
            case R.id.layout3:
                intent = new Intent(getContext(), MyClientActivity.class);
                intent.putExtra("client", "3");
                startActivity(intent);
                break;
//                TODO  认筹
            case R.id.layout4:
                intent = new Intent(getContext(), MyClientActivity.class);
                intent.putExtra("client", "4");
                startActivity(intent);
                break;
//                TODO  成交
            case R.id.layout5:
                intent = new Intent(getContext(), MyClientActivity.class);
                intent.putExtra("client", "5");
                startActivity(intent);
                break;
//                TODO  失效
            case R.id.layout6:
                intent = new Intent(getContext(), MyClientActivity.class);
                intent.putExtra("client", "6");
                startActivity(intent);
                break;
//                TODO  应收
            case R.id.layout7:
                intent = new Intent(getContext(), CommissionTheProjectEndActivity.class);
                startActivity(intent);
                break;
//                TODO  已结
            case R.id.layout8:
                intent = new Intent(getContext(), CommissionTheProjectEndActivity.class);
                startActivity(intent);
                break;
//                TODO  待结
            case R.id.layout9:
                intent = new Intent(getContext(), CommissionTheProjectEndActivity.class);
                startActivity(intent);
                break;
//                TODO  未结
            case R.id.layout10:
                intent = new Intent(getContext(), CommissionTheProjectEndActivity.class);
                startActivity(intent);
                break;
//                TODO  待我审核
            case R.id.AuditList:
                FinalContents.setNumS(1);
                intent = new Intent(getContext(), CheckPendingTheProjectEndActivity.class);
                startActivity(intent);
                break;
//                TODO  我发起的审核
            case R.id.mypost:
                intent = new Intent(getContext(), InitiatedTheReviewActivity.class);
                startActivity(intent);
                break;
            case R.id.rb1_home_the_project_end:
                time1_ll1_home_the_project_end.setVisibility(View.GONE);
                beforeDate = "";
                afterDate = "";
                type = "";
                NewlyIncreased.setTag("0");
                NewlyIncreased.setYJType("0");
                initViewData();
                break;
            case R.id.rb2_home_the_project_end:
                time1_ll1_home_the_project_end.setVisibility(View.GONE);
                beforeDate = "";
                afterDate = "";
                type = "1";
                NewlyIncreased.setTag("1");
                NewlyIncreased.setYJType("1");
                initViewData();
                break;
            case R.id.rb3_home_the_project_end:
                time1_ll1_home_the_project_end.setVisibility(View.GONE);
                beforeDate = "";
                afterDate = "";
                type = "2";
                NewlyIncreased.setTag("2");
                NewlyIncreased.setYJType("2");
                initViewData();
                break;
            case R.id.rb4_home_the_project_end:
                time1_ll1_home_the_project_end.setVisibility(View.VISIBLE);
                type = "3";
                time1_home_the_project_end.setText("<" + string);
                time2_home_the_project_end.setText("-" + string + " >");
                NewlyIncreased.setTag("3");
                NewlyIncreased.setYJType("3");
                initDate();
                break;
        }
    }


    private void init(){
        UserBean userBean = Connector.getUserBean();
        Glide.with(getActivity()).load(FinalContents.getImageUrl() + userBean.getData().getPhoto()).into(img_home_the_project_end);
        name_home_the_project_end.setText(userBean.getData().getName());
        city_home_the_project_end.setText(userBean.getData().getCity());
//        shop_home_the_project_end.setText(userBean.getData().getCityName());
        shop_home_the_project_end.setText(userBean.getData().getCity() + "  " + userBean.getData().getCityName());
        position_home_the_project_end.setText("专案");
    }

    @Override
    public void onResume() {
        super.onResume();
        if (NewlyIncreased.getUserMessage().equals("7")){
            init();
        }
//        initViewData();
    }

    public String getTime2(Date date) {//可根据需要自行截取数据显示
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
        return format.format(date);
    }
}
