package com.xcy.fzbcity.all.view;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextPaint;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bumptech.glide.Glide;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.android.material.tabs.TabLayout;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.adapter.CommissionRecycler;
import com.xcy.fzbcity.all.adapter.DetailsAdapter;
import com.xcy.fzbcity.all.adapter.FamilyRecycler;
import com.xcy.fzbcity.all.adapter.ProjectDetailsVillaAdapter;
import com.xcy.fzbcity.all.adapter.ReportAdapter;
import com.xcy.fzbcity.all.adapter.TalktoolRecycler;
import com.xcy.fzbcity.all.api.CityContents;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.modle.BuildingBean;
import com.xcy.fzbcity.all.modle.CollectBean;
import com.xcy.fzbcity.all.modle.HouseBean;
import com.xcy.fzbcity.all.modle.ProjectDetailsBean;
import com.xcy.fzbcity.all.modle.ProjectHousesTrendListBean;
import com.xcy.fzbcity.all.modle.RemindBean;
import com.xcy.fzbcity.all.persente.GradationScrollView;
import com.xcy.fzbcity.all.persente.MyLinearLayoutManager;
import com.xcy.fzbcity.all.persente.SharItOff;
import com.xcy.fzbcity.all.persente.SingleClick;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.CommonUtil;
import com.xcy.fzbcity.all.utils.DetailsData;
import com.xcy.fzbcity.all.utils.ToastUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import co.lujun.androidtagview.ColorFactory;
import co.lujun.androidtagview.TagContainerLayout;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProjectDetails extends AllActivity implements View.OnClickListener, DetailsAdapter.DetailsItem, GradationScrollView.ScrollViewListener {
    private ImageView backimg;
    private RelativeLayout back;
    private ImageView building;

    private TextView number;
    private TextView attention;
    private TextView transmit;
    private TextView amount;
    private TextView salestatus;
    private TagContainerLayout productfeature;
    private TextView name;
    private TextView location;
    private TextView toatl;
    private TextView unit;
    private TextView tary;
    private TextView areainterval;
    private TextView more;
    private TextView award;
    private TextView house_title;
    private TextView house_time;
    private TextView transmit_house;

    private TextView message;

    private TextView report;
    private CheckBox collect;
    private TextView call;

    private TabLayout tabLayout;


    private TextView repast;
    private TagContainerLayout repast_content;
    private TextView recuperate;
    private TextView recuperate_content;
    private TextView business;
    private TextView business_content;

    private RecyclerView report_rv;

    private RecyclerView commissionRv;
    private RecyclerView talktool;
    private RecyclerView family;

    private LinearLayout talktool_layout;
    private LinearLayout linear0;
    private LinearLayout linear3;
    private LinearLayout linear4;
    private LinearLayout linear5;
    private LinearLayout linear6;
    private LinearLayout linear7;

    private TextView group_booking;


    private LinearLayout guowai1;
    private LinearLayout guowai3;
    private View guowai2;

    private TextView share;
    private RecyclerView details_rv;
    private TextView details_tv_S;
    private TextView project_details_group_booking;

    private ProjectDetailsBean.DataBean projectDetailsBeanData;

    private TextView project_details_ding;
    private TextView project_details_kai;


    private CollectBean.DataBean dataBean = new CollectBean.DataBean();
    private LinearLayout project_details_all;
    private TextView project_details_share_all;
    private CheckBox project_details_collect_all;
    private TextView project_details_qt_call;
    private LinearLayout project_details_qt;
    private LinearLayout project_details_linear1;
    private LinearLayout project_details_linear2;
    private LinearLayout project_details_linear3;
    private TextView project_details_discounts_nummer;
    private LinearLayout linear;
    private GradationScrollView project_details_scrollview;
    private TextView project_details_trend;
    private RelativeLayout project_details_relative;
    private LinearLayout project_details_linear_city0;
    private TabLayout project_details_family_tablayout;
    private FamilyRecycler recyclerAdapter;
    private LinearLayout project_details_layout1;
    private TextView project_details_layout1_checkbox;
    private TextView project_details_family_tvs;
    private View project_details_layout1_view;
    private LinearLayout project_details_layout2;
    private TextView project_details_layout2_checkbox;
    private View project_details_layout2_view;
    private LinearLayout project_details_layout3;
    private TextView project_details_layout3_checkbox;
    private View project_details_layout3_view;
    private TextPaint tp;
    private LinearLayout project_details_layout;
    private RecyclerView project_details_villa_rv;
    private TextView project_details_villa_title;

    private LineChart details_chart;
    private TabLayout project_details_tab_layout;

    private List<String> indexList;
    int num = 0;

    List<DetailsData> list;
    private DetailsAdapter adapter;
    private TextView project_details_title;
    private RelativeLayout project_details_toolbar;
    int mAlpha = 0;
    private LinearLayout project_details_villa_linear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_details);
        init_No_Network();
    }

    private void init_No_Network() {
        boolean networkAvailable = CommonUtil.isNetworkAvailable(this);
        if (networkAvailable) {
            initfvb();
            init();
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

    //命名区域
    private void initfvb() {

        StatusBar.makeStatusBarTransparent(this);

        project_details_kai = findViewById(R.id.project_details_kai);
        project_details_family_tvs = findViewById(R.id.project_details_family_tvs);
        project_details_ding = findViewById(R.id.project_details_ding);
        project_details_qt_call = findViewById(R.id.project_details_qt_call);
        details_rv = findViewById(R.id.details_rv);
        details_tv_S = findViewById(R.id.details_tv_S);

        project_details_kai.setOnClickListener(this);
        project_details_ding.setOnClickListener(this);
        details_tv_S.setOnClickListener(this);
        details_rv.setOnClickListener(this);

        //  TODO    城市新增
        project_details_discounts_nummer = findViewById(R.id.project_details_discounts_nummer);
        linear = findViewById(R.id.linear);
        project_details_trend = findViewById(R.id.project_details_trend);
        project_details_relative = findViewById(R.id.project_details_relative);
        project_details_linear_city0 = findViewById(R.id.project_details_linear_city0);
        project_details_villa_rv = findViewById(R.id.project_details_villa_rv);
        project_details_family_tablayout = findViewById(R.id.project_details_family_tablayout);
        project_details_villa_title = findViewById(R.id.project_details_villa_title);

        project_details_title = findViewById(R.id.project_details_title);
        project_details_toolbar = findViewById(R.id.project_details_toolbar);

        project_details_villa_linear = findViewById(R.id.project_details_villa_linear);

        CityContents.setIsReport("1");

        //  TODO    三合一大派对      佣金/报备/奖励
        project_details_layout = findViewById(R.id.project_details_layout);

        project_details_layout1 = findViewById(R.id.project_details_layout1);
        project_details_layout1_checkbox = findViewById(R.id.project_details_layout1_checkbox);
        project_details_layout1_view = findViewById(R.id.project_details_layout1_view);

        project_details_layout2 = findViewById(R.id.project_details_layout2);
        project_details_layout2_checkbox = findViewById(R.id.project_details_layout2_checkbox);
        project_details_layout2_view = findViewById(R.id.project_details_layout2_view);

        project_details_layout3 = findViewById(R.id.project_details_layout3);
        project_details_layout3_checkbox = findViewById(R.id.project_details_layout3_checkbox);
        project_details_layout3_view = findViewById(R.id.project_details_layout3_view);

        details_chart = findViewById(R.id.project_details_chart);

        project_details_tab_layout = findViewById(R.id.project_details_tab_layout);

        project_details_relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                project_details_relative.setVisibility(View.GONE);
            }
        });//  TODO    关闭走势图

        project_details_linear_city0.setOnClickListener(this);

        project_details_layout1.setOnClickListener(this);
        project_details_layout2.setOnClickListener(this);
        project_details_layout3.setOnClickListener(this);

        //  TODO    城市新增    结束

        backimg = findViewById(R.id.project_details_backimg);
        back = findViewById(R.id.project_details_back);
        building = findViewById(R.id.project_details_building);

        number = findViewById(R.id.project_details_number);
        attention = findViewById(R.id.project_details_attention_num);
        transmit = findViewById(R.id.project_details_transmit_num);
        amount = findViewById(R.id.project_details_amount_num);
        salestatus = findViewById(R.id.project_details_salestatus);
        productfeature = findViewById(R.id.project_details_productfeature);
        name = findViewById(R.id.project_details_name);
        location = findViewById(R.id.project_details_location);
        toatl = findViewById(R.id.project_details_toatl);
        unit = findViewById(R.id.project_details_unit);
        tary = findViewById(R.id.project_details_tary);
        areainterval = findViewById(R.id.project_details_areainterval);
        more = findViewById(R.id.project_details_more);
        award = findViewById(R.id.project_details_award);
        house_title = findViewById(R.id.project_details_house_title);
        house_time = findViewById(R.id.project_details_house_time);
        transmit_house = findViewById(R.id.project_details_transmit_house);
        project_details_qt = findViewById(R.id.project_details_qt);

        project_details_group_booking = findViewById(R.id.project_details_group_booking);

        guowai1 = findViewById(R.id.guowai1);
        guowai3 = findViewById(R.id.guowai3);
        guowai2 = findViewById(R.id.guowai2);
        call = findViewById(R.id.project_details_call);
        report = findViewById(R.id.project_details_report);
        collect = findViewById(R.id.project_details_collect);

        commissionRv = findViewById(R.id.project_details_commission);
        talktool = findViewById(R.id.project_details_talktool);
        family = findViewById(R.id.project_details_family);

        report_rv = findViewById(R.id.project_details_report_rv);

        share = findViewById(R.id.project_details_share);

        tabLayout = findViewById(R.id.project_details_tab);
        message = findViewById(R.id.project_details_message);

        repast = findViewById(R.id.project_details_repast);
        repast_content = findViewById(R.id.project_details_repast_content);
        recuperate = findViewById(R.id.project_details_recuperate);
        recuperate_content = findViewById(R.id.project_details_recuperate_content);
        business = findViewById(R.id.project_details_business);
        business_content = findViewById(R.id.project_details_business_content);


        project_details_all = findViewById(R.id.project_details_all);
        project_details_share_all = findViewById(R.id.project_details_share_all);
        project_details_collect_all = findViewById(R.id.project_details_collect_all);


        linear0 = findViewById(R.id.linear0);
        linear3 = findViewById(R.id.linear3);
        linear4 = findViewById(R.id.linear4);
        linear5 = findViewById(R.id.linear5);
        linear6 = findViewById(R.id.linear6);
        linear7 = findViewById(R.id.linear7);

        project_details_scrollview = findViewById(R.id.project_details_scrollview);

        project_details_linear1 = findViewById(R.id.project_details_linear1);

        project_details_linear2 = findViewById(R.id.project_details_linear2);

        project_details_linear3 = findViewById(R.id.project_details_linear3);

        talktool_layout = findViewById(R.id.project_details_talktool_layout);

        linear5.setOnClickListener(this);
        back.setOnClickListener(this);
        backimg.setOnClickListener(this);
        more.setOnClickListener(this);
        transmit_house.setOnClickListener(this);
        project_details_scrollview.setScrollViewListener(this);


        location.setOnClickListener(this);
        project_details_group_booking.setOnClickListener(this);
        project_details_qt_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (projectDetailsBeanData.getProjectListVo().getFfAttacheList().size() != 0) {
                        List<String> arrayList = new ArrayList<>();
                        for (int index = 0; index < projectDetailsBeanData.getProjectListVo().getFfAttacheList().size(); index++) {
                            arrayList.add(projectDetailsBeanData.getProjectListVo().getFfAttacheList().get(index).getName());
                        }
                        //      监听选中
                        OptionsPickerView pvOptions = new OptionsPickerBuilder(ProjectDetails.this, new OnOptionsSelectListener() {
                            @Override
                            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                                //               返回的分别是三个级别的选中位置
                                //              展示选中数据
                                Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + projectDetailsBeanData.getProjectListVo().getFfAttacheList().get(options1).getPhone()));//跳转到拨号界面，同时传递电话号码
                                startActivity(dialIntent);
                            }
                        })
                                .setSelectOptions(0)//设置选择第一个
                                .setOutSideCancelable(false)//点击背的地方不消失
                                .build();//创建
                        //      把数据绑定到控件上面
                        pvOptions.setPicker(arrayList);
                        //      展示
                        pvOptions.show();
                    }else {
                        ToastUtil.showLongToast(ProjectDetails.this,"暂无专案");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        if (FinalContents.getZhuanAn().equals("1")) {
            project_details_qt.setVisibility(View.VISIBLE);
            project_details_all.setVisibility(View.GONE);
        }

        collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit.Builder builder2 = new Retrofit.Builder();
                builder2.baseUrl(FinalContents.getBaseUrl());
                builder2.addConverterFactory(GsonConverterFactory.create());
                builder2.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
                Retrofit build2 = builder2.build();
                MyService fzbInterface2 = build2.create(MyService.class);
                Observable<CollectBean> collectBean1 = fzbInterface2.getCollectBean(FinalContents.getProjectID(), FinalContents.getUserID(), "1");
                collectBean1.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<CollectBean>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(CollectBean collectBean) {
                                dataBean = collectBean.getData();
                                if (dataBean.getStatus().equals("1")) {
                                    collect.setChecked(true);
                                    ToastUtil.showToast(ProjectDetails.this, dataBean.getMessage());
                                } else {
                                    collect.setChecked(false);
                                    ToastUtil.showToast(ProjectDetails.this, dataBean.getMessage());
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.i("MyCL", "项目详情错误信息：" + e.getMessage());
                            }

                            @Override
                            public void onComplete() {

                            }
                        });

            }
        });


        project_details_collect_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit.Builder builder2 = new Retrofit.Builder();
                builder2.baseUrl(FinalContents.getBaseUrl());
                builder2.addConverterFactory(GsonConverterFactory.create());
                builder2.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
                Retrofit build2 = builder2.build();
                MyService fzbInterface2 = build2.create(MyService.class);
                Observable<CollectBean> collectBean1 = fzbInterface2.getCollectBean(FinalContents.getProjectID(), FinalContents.getUserID(), "1");
                collectBean1.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<CollectBean>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(CollectBean collectBean) {
                                dataBean = collectBean.getData();
                                if (dataBean.getStatus().equals("1")) {
                                    collect.setChecked(true);
                                    ToastUtil.showToast(ProjectDetails.this, dataBean.getMessage());
                                } else {
                                    collect.setChecked(false);
                                    ToastUtil.showToast(ProjectDetails.this, dataBean.getMessage());
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.i("MyCL", "项目详情错误信息：" + e.getMessage());
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> arrayList = new ArrayList<>();
                for (int i = 0; i < projectDetailsBeanData.getProjectListVo().getFfAttacheList().size(); i++) {
                    arrayList.add(projectDetailsBeanData.getProjectListVo().getFfAttacheList().get(i).getName());
                }
                //      监听选中
                OptionsPickerView pvOptions = new OptionsPickerBuilder(ProjectDetails.this, new OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int option2, int options3, View v) {
                        //               返回的分别是三个级别的选中位置
                        //              展示选中数据
                        Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + projectDetailsBeanData.getProjectListVo().getFfAttacheList().get(options1).getPhone()));//跳转到拨号界面，同时传递电话号码
                        startActivity(dialIntent);
                    }
                })
                        .setSelectOptions(0)//设置选择第一个
                        .setOutSideCancelable(false)//点击背的地方不消失
                        .build();//创建
                //      把数据绑定到控件上面
                pvOptions.setPicker(arrayList);
                //      展示
                pvOptions.show();
            }
        });
        tabLayout.addTab(tabLayout.newTab().setText("交通出行"), true);
        tabLayout.addTab(tabLayout.newTab().setText("教育教学"));
        tabLayout.addTab(tabLayout.newTab().setText("医疗健康"));
        tabLayout.addTab(tabLayout.newTab().setText("商场购物"));
        tabLayout.addTab(tabLayout.newTab().setText("生活娱乐"));
        tabLayout.addTab(tabLayout.newTab().setText("著名景点"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //                添加选中Tab的逻辑
                if (tab.getText().equals("交通出行")) {
                    message.setText(projectDetailsBeanData.getMatingInformation().getTraffic());
                } else if (tab.getText().equals("教育教学")) {
                    message.setText(projectDetailsBeanData.getMatingInformation().getEducation());
                } else if (tab.getText().equals("医疗健康")) {
                    message.setText(projectDetailsBeanData.getMatingInformation().getMedical());
                } else if (tab.getText().equals("商场购物")) {
                    message.setText(projectDetailsBeanData.getMatingInformation().getShopping());
                } else if (tab.getText().equals("生活娱乐")) {
                    message.setText(projectDetailsBeanData.getMatingInformation().getEntertainment());
                } else if (tab.getText().equals("著名景点")) {
                    message.setText(projectDetailsBeanData.getMatingInformation().getFamousScene());
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //                添加未选中Tab的逻辑
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //                再次选中tab的逻辑
                if (tab.getText().equals("交通出行")) {
                    message.setText(projectDetailsBeanData.getMatingInformation().getTraffic());
                } else if (tab.getText().equals("教育健康")) {
                    message.setText(projectDetailsBeanData.getMatingInformation().getEducation());
                } else if (tab.getText().equals("医疗健康")) {
                    message.setText(projectDetailsBeanData.getMatingInformation().getMedical());
                } else if (tab.getText().equals("商场购物")) {
                    message.setText(projectDetailsBeanData.getMatingInformation().getShopping());
                } else if (tab.getText().equals("生活娱乐")) {
                    message.setText(projectDetailsBeanData.getMatingInformation().getEntertainment());
                } else if (tab.getText().equals("著名景点")) {
                    message.setText(projectDetailsBeanData.getMatingInformation().getFamousScene());
                }
            }
        });

        initDetails();//楼栋查看全部信息

    }

    //点击事件
    @SingleClick(1000)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.project_details_back:
                finish();
                break;
            case R.id.project_details_backimg:
                Intent photointent = new Intent(this, PhotoTileActivity.class);
                startActivity(photointent);
                break;
            case R.id.project_details_more:
                if (projectDetailsBeanData.getProjectListVo().getFfAttacheList().size() != 0) {
                    FinalContents.setIPhone(projectDetailsBeanData.getProjectListVo().getFfAttacheList().get(0).getPhone());
                }
                FinalContents.setProjectName(projectDetailsBeanData.getProjectListVo().getProjectName());
                FinalContents.setProjectSearchID(projectDetailsBeanData.getProjectListVo().getProjectId());
                FinalContents.setGuideRuleId(projectDetailsBeanData.getProjectListVo().getGuideRuleId());
                FinalContents.setProjectID(projectDetailsBeanData.getProjectListVo().getProjectId());
                Intent moreInformationintent = new Intent(this, MoreInformationActivity.class);
                startActivity(moreInformationintent);
                break;
            case R.id.project_details_transmit_house:
                Intent transmitintent = new Intent(this, BuildingDynamicActivity.class);
                startActivity(transmitintent);
                break;
            case R.id.project_details_location:
                Intent mapintent = new Intent(this, MapActivity.class);
                mapintent.putExtra("office", "1");
                startActivity(mapintent);
                break;
            case R.id.project_details_group_booking:
                Intent intents = new Intent(ProjectDetails.this, SpellingMassTimeActivity.class);
                startActivity(intents);
                break;
            case R.id.project_details_ding:
                initRemind(1);
                break;
            case R.id.project_details_kai:
                initRemind(2);
                break;
            case R.id.project_details_layout3:
                project_details_layout3_checkbox.setTextSize(19);
                tp = project_details_layout3_checkbox.getPaint();
                tp.setFakeBoldText(true);
                project_details_layout3_view.setVisibility(View.VISIBLE);

                project_details_layout2_checkbox.setTextSize(15);
                tp = project_details_layout2_checkbox.getPaint();
                tp.setFakeBoldText(false);
                project_details_layout2_view.setVisibility(View.INVISIBLE);
                project_details_layout1_checkbox.setTextSize(15);
                tp = project_details_layout1_checkbox.getPaint();
                tp.setFakeBoldText(false);
                project_details_layout1_view.setVisibility(View.INVISIBLE);
                award.setVisibility(View.VISIBLE);
                report_rv.setVisibility(View.GONE);
                commissionRv.setVisibility(View.GONE);
                Log.i("三合一", "点击事件");
                break;
            case R.id.project_details_layout2:
                project_details_layout2_checkbox.setTextSize(19);
                tp = project_details_layout2_checkbox.getPaint();
                tp.setFakeBoldText(true);
                project_details_layout2_view.setVisibility(View.VISIBLE);

                project_details_layout3_checkbox.setTextSize(15);
                tp = project_details_layout3_checkbox.getPaint();
                tp.setFakeBoldText(false);
                project_details_layout3_view.setVisibility(View.INVISIBLE);
                project_details_layout1_checkbox.setTextSize(15);
                tp = project_details_layout1_checkbox.getPaint();
                tp.setFakeBoldText(false);
                project_details_layout1_view.setVisibility(View.INVISIBLE);
                award.setVisibility(View.GONE);
                report_rv.setVisibility(View.VISIBLE);
                commissionRv.setVisibility(View.GONE);
                Log.i("三合一", "点击事件");
                break;
            case R.id.project_details_layout1:
                project_details_layout1_checkbox.setTextSize(19);
                tp = project_details_layout1_checkbox.getPaint();
                tp.setFakeBoldText(true);
                project_details_layout1_view.setVisibility(View.VISIBLE);

                project_details_layout2_checkbox.setTextSize(15);
                tp = project_details_layout2_checkbox.getPaint();
                tp.setFakeBoldText(false);
                project_details_layout2_view.setVisibility(View.INVISIBLE);
                project_details_layout3_checkbox.setTextSize(15);
                tp = project_details_layout3_checkbox.getPaint();
                tp.setFakeBoldText(false);
                project_details_layout3_view.setVisibility(View.INVISIBLE);
                award.setVisibility(View.GONE);
                report_rv.setVisibility(View.GONE);
                commissionRv.setVisibility(View.VISIBLE);
                Log.i("三合一", "点击事件");
                break;
            case R.id.details_tv_S://查看全部栋楼信息
                Intent buildingInformationintent = new Intent(ProjectDetails.this, BuildingInformationActivity.class);
                buildingInformationintent.putExtra("pic", FinalContents.getImageUrl() + projectDetailsBeanData.getBuildingImg());
                startActivity(buildingInformationintent);
                break;
            case R.id.details_rv://查看全部栋楼信息

                break;
            case R.id.linear5:
//                Intent buildingInformationintentS = new Intent(ProjectDetails.this, BuildingInformationActivity.class);
//                buildingInformationintentS.putExtra("pic", FinalContents.getImageUrl() + projectDetailsBeanData.getBuildingImg());
//                startActivity(buildingInformationintentS);
                break;
        }
    }

    //楼栋查看全部信息
    private void initDetails() {

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        details_rv.setLayoutManager(manager);
        list = new ArrayList<>();

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        final Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<BuildingBean> buildingBean = fzbInterface.getBuildingBean(FinalContents.getUserID(), FinalContents.getProjectID());
        buildingBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BuildingBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BuildingBean buildingBean) {
                        List<BuildingBean.DataBean> data = buildingBean.getData();
                        Log.i("MyCL","data:" + data.size());
                        list.clear();
                        if (data.size() == 0) {
                            linear5.setVisibility(View.GONE);
                            details_tv_S.setVisibility(View.GONE);
                            details_rv.setVisibility(View.GONE);
                        } else if (data.size() == 1) {
                            linear5.setVisibility(View.VISIBLE);
                            details_tv_S.setVisibility(View.VISIBLE);
                            details_rv.setVisibility(View.VISIBLE);
                            adapter = new DetailsAdapter();
                            list.add(new DetailsData(data.get(0).getBuildingName() + "", data.get(0).getElementNumber() + "", data.get(0).getHouseNumber() + "", "1"));
                            details_tv_S.setText("查看全部" + data.size() + "栋楼信息");
                            adapter.setList(list);
                            details_rv.setAdapter(adapter);
                            adapter.setDetailsItem(ProjectDetails.this);
                        } else if (data.size() > 1) {
                            linear5.setVisibility(View.VISIBLE);
                            details_tv_S.setVisibility(View.VISIBLE);
                            details_rv.setVisibility(View.VISIBLE);
                            adapter = new DetailsAdapter();
                            list.add(new DetailsData(data.get(0).getBuildingName() + "", data.get(0).getElementNumber() + "", data.get(0).getHouseNumber() + "", "1"));
                            list.add(new DetailsData(data.get(1).getBuildingName() + "", data.get(1).getElementNumber() + "", data.get(1).getHouseNumber() + "", "2"));
                            details_tv_S.setText("查看全部" + data.size() + "栋楼信息");
                            adapter.setList(list);
                            details_rv.setAdapter(adapter);
                            adapter.setDetailsItem(ProjectDetails.this);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "楼栋信息错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        details_rv.setAdapter(adapter);

    }

    // 定价通知
    private void initRemind(final int i) {

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        final Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<RemindBean> remindBean = fzbInterface.getRemindBean(FinalContents.getProjectID(), i + "", FinalContents.getUserID());
        remindBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RemindBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(final RemindBean remindBean) {

                        if (remindBean.getMsg().equals("成功")) {
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(ProjectDetails.this);
                            if (i == 1) {
                                builder1.setTitle("变价提醒");
                            } else {
                                builder1.setTitle("开盘提醒");
                            }
                            builder1.setMessage(remindBean.getData().getMessage());
                            builder1.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @SuppressLint("ResourceAsColor")
                                @Override
                                public void onClick(DialogInterface dialogInterface, int posotion) {
                                    if (i == 1) {
                                        if (remindBean.getData().getMessage().equals("取消订阅")) {
                                            project_details_ding.setText(" 变价通知");
                                        } else if (remindBean.getData().getMessage().equals("订阅成功")) {
                                            project_details_ding.setText(" 已订阅变价通知");
                                        }
                                    } else {
                                        if (remindBean.getData().getMessage().equals("取消订阅")) {
                                            project_details_kai.setText(" 开盘通知");
                                        } else if (remindBean.getData().getMessage().equals("订阅成功")) {
                                            project_details_kai.setText(" 已订阅开盘通知");
                                        }
                                    }
                                }
                            });
                            builder1.show();
                        } else {
                            ToastUtil.showToast(ProjectDetails.this, remindBean.getData().getMessage());
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "提醒通知错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @SuppressLint({"CheckResult", "WrongConstant"})
    private void init() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<ProjectDetailsBean> projectDetailsBean = fzbInterface.getProjectDetailsBean(FinalContents.getUserID(), FinalContents.getProjectID());
        projectDetailsBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ProjectDetailsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("ResourceAsColor")
                    @Override
                    public void onNext(final ProjectDetailsBean projectDetailsBean) {
                        project_details_scrollview.setVisibility(View.VISIBLE);
                        try {
                            projectDetailsBeanData = projectDetailsBean.getData();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        if (projectDetailsBeanData == null) {
                            return;
                        }

                        Log.i("wsm", "fdsafd:" + projectDetailsBeanData.getProjectListVo().getProjectImg());
                        if (projectDetailsBean.getData().getProjectListVo().getProjectImg().equals("")) {
                            backimg.setImageResource(R.mipmap.banner_img);
                        }else {
                            Glide.with(ProjectDetails.this).load(FinalContents.getImageUrl() + projectDetailsBean.getData().getProjectListVo().getProjectImg()).into(backimg);
                        }

                        project_details_trend.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                try {
                                    if (projectDetailsBeanData.getProjectListVo().getIsPriceTrend() != null) {
                                        if (projectDetailsBeanData.getProjectListVo().getIsPriceTrend().equals("0")) {
                                            ToastUtil.showLongToast(ProjectDetails.this,"暂无数据");
                                        }else if (projectDetailsBeanData.getProjectListVo().getIsPriceTrend().equals("1")){
                                            project_details_relative.setVisibility(View.VISIBLE);
                                        }else{
                                            ToastUtil.showLongToast(ProjectDetails.this,"暂无数据");
                                        }
                                    }else {
                                        ToastUtil.showLongToast(ProjectDetails.this,"暂无数据");
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });//  TODO    打开走势图


                        if (projectDetailsBeanData.getProjectListVo().getReferenceToatlPrice().equals("")) {
                            guowai1.setVisibility(View.GONE);
                            guowai2.setVisibility(View.GONE);
                            guowai3.setGravity(Gravity.CENTER);
                        } else {
                            guowai1.setVisibility(View.VISIBLE);
                            guowai2.setVisibility(View.VISIBLE);
                            guowai3.setGravity(Gravity.RIGHT);
                        }

//                        if (FinalContents.getZyHome().equals("1")) {
//                            project_details_group_booking.setVisibility(View.GONE);
//                        } else {
//                        if (projectDetailsBeanData.getProjectListVo().getIsgroup().equals("1")) {
//                            project_details_group_booking.setVisibility(View.VISIBLE);
//                        } else {
                            project_details_group_booking.setVisibility(View.GONE);
//                        }
//                        }

                        message.setText(projectDetailsBeanData.getMatingInformation().getTraffic());

                        report.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                FinalContents.setChecked2(true);
                                FinalContents.setGuideRuleId(projectDetailsBeanData.getProjectListVo().getGuideRuleId());
                                FinalContents.setProjectName(projectDetailsBeanData.getProjectListVo().getProjectName());
                                FinalContents.setProjectID(projectDetailsBeanData.getProjectListVo().getProjectId());
                                Intent intent = new Intent(ProjectDetails.this, ReportActivity.class);
                                startActivity(intent);
                            }
                        });

                        if (projectDetailsBean.getData().getProjectListVo().getIsCollection().equals("0")) {
                            collect.setChecked(false);
                            project_details_collect_all.setChecked(false);
                        } else if (projectDetailsBean.getData().getProjectListVo().getIsCollection().equals("1")) {
                            collect.setChecked(true);
                            project_details_collect_all.setChecked(true);
                        }

                        if (projectDetailsBean.getData().getProjectListVo().getRemindstatus1().equals("1")) {
                            project_details_ding.setText(" 已订阅变价通知");
                        } else if (projectDetailsBean.getData().getProjectListVo().getRemindstatus1().equals("0")) {
                            project_details_ding.setText(" 变价通知");
                        }

                        if (projectDetailsBean.getData().getProjectListVo().getRemindstatus2().equals("1")) {
                            project_details_kai.setText(" 已订阅开盘通知");
                        } else if (projectDetailsBean.getData().getProjectListVo().getRemindstatus2().equals("0")) {
                            project_details_kai.setText(" 开盘通知");
                        }

                        number.setText(projectDetailsBeanData.getHouseImgCountNum() + "张");
                        attention.setText(projectDetailsBeanData.getProjectListVo().getBrowseNum());
                        transmit.setText(projectDetailsBeanData.getProjectListVo().getForwardingAmount());
                        amount.setText(projectDetailsBeanData.getProjectListVo().getReportAmount());


                        //分享
                        share.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                FinalContents.showShare(projectDetailsBean.getData().getProjectListVo().getProjectName(), FinalContents.getAdminUrl() + "/?userId=" + FinalContents.getUserID() + "&id=" + FinalContents.getProjectID()+"&type=1", projectDetailsBean.getData().getProjectListVo().getProductFeature(), FinalContents.getImageUrl() + projectDetailsBean.getData().getProjectListVo().getProjectImg(), FinalContents.getAdminUrl() + "/?userId=" + FinalContents.getUserID() + "&id=" + FinalContents.getProjectID()+"&type=1", ProjectDetails.this);
                            }
                        });

                        project_details_share_all.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                FinalContents.showShare(projectDetailsBean.getData().getProjectListVo().getProjectName(), FinalContents.getAdminUrl() + "/?userId=" + FinalContents.getUserID() + "&id=" + FinalContents.getProjectID()+"&type=1", projectDetailsBean.getData().getProjectListVo().getProductFeature(), FinalContents.getImageUrl() + projectDetailsBean.getData().getProjectListVo().getProjectImg(), FinalContents.getAdminUrl() + "/?userId=" + FinalContents.getUserID() + "&id=" + FinalContents.getProjectID()+"&type=1", ProjectDetails.this);
                            }
                        });

                        //     TODO     优惠活动
                        if (!projectDetailsBean.getData().getProjectListVo().getSaleDiscounts().equals("")) {
                            linear.setVisibility(View.VISIBLE);
                            project_details_discounts_nummer.setText(projectDetailsBean.getData().getProjectListVo().getSaleDiscounts());
                        } else {
                            linear.setVisibility(View.GONE);
                        }


                        //地图
                        String ids = projectDetailsBeanData.getProjectListVo().getLocation();//从pd里取出字符串
                        List tags = Arrays.asList(ids.split(","));//根据逗号分隔转化为list
                        double d = Double.parseDouble(tags.get(0).toString());
                        double o = Double.parseDouble(tags.get(1).toString());
                        if (!projectDetailsBeanData.getProjectListVo().getSalesOfficeLocation().equals("")) {
                            String ids1 = projectDetailsBeanData.getProjectListVo().getSalesOfficeLocation();//从pd里取出字符串
                            List tags1 = Arrays.asList(ids1.split(","));//根据逗号分隔转化为list
                            double d1 = Double.parseDouble(tags1.get(0).toString());
                            double o1 = Double.parseDouble(tags1.get(1).toString());
                            FinalContents.setO1(o1);
                            FinalContents.setD1(d1);
                            Log.i("经纬度", "查看经纬度" + FinalContents.getO1() + "---" + FinalContents.getD1());
                        }
                        FinalContents.setO(o);
                        FinalContents.setD(d);
                        Log.i("经纬度", "查看项目详情经纬度" + FinalContents.getO() + "---" + FinalContents.getD());

                        //导客规则
                        if (projectDetailsBeanData.getGuideRules().size() == 0) {
                            project_details_layout2.setVisibility(View.GONE);
                        } else {
                            project_details_layout2.setVisibility(View.VISIBLE);
                            MyLinearLayoutManager layout = new MyLinearLayoutManager(ProjectDetails.this);
                            layout.setOrientation(LinearLayoutManager.VERTICAL);
                            layout.setScrollEnabled(false);
                            report_rv.setLayoutManager(layout);
                            ReportAdapter reportAdapter = new ReportAdapter(projectDetailsBeanData);
                            report_rv.setAdapter(reportAdapter);
                        }

                        String ids2 = projectDetailsBeanData.getProjectListVo().getProductFeature();//从pd里取出字符串
                        List tags2 = Arrays.asList(ids2.split(","));//根据逗号分隔转化为list
                        if (projectDetailsBeanData.getProjectListVo().getProductFeature().equals("")) {
                            productfeature.setVisibility(View.GONE);
                        } else {
                            productfeature.setVisibility(View.VISIBLE);
                            productfeature.setTheme(ColorFactory.NONE);
                            productfeature.setTags(tags2);
                        }

                        name.setText(projectDetailsBeanData.getProjectListVo().getProjectName());
                        location.setText(projectDetailsBeanData.getProjectListVo().getDetailAddress());
                        toatl.setText(projectDetailsBeanData.getProjectListVo().getProductTotalPrice());

                        unit.setText(projectDetailsBeanData.getProjectListVo().getProductUnitPrice());

                        tary.setText(projectDetailsBeanData.getProjectListVo().getMonetaryUnit());
                        areainterval.setText(projectDetailsBeanData.getProjectListVo().getAreaInterval());

                        if (FinalContents.getIdentity().equals("63")) {
                            project_details_layout1.setVisibility(View.GONE);
                        } else {
                            //佣金规则
                            if (projectDetailsBeanData.getAmountIncentiveList().size() == 0) {
                                project_details_layout1.setVisibility(View.GONE);
                            } else {
                                project_details_layout1_checkbox.setTextSize(19);
                                tp = project_details_layout1_checkbox.getPaint();
                                tp.setFakeBoldText(true);
                                project_details_layout1_view.setVisibility(View.VISIBLE);
                                commissionRv.setVisibility(View.VISIBLE);
                                project_details_layout1.setVisibility(View.VISIBLE);
                                LinearLayoutManager layoutManager = new LinearLayoutManager(ProjectDetails.this);
                                layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                                commissionRv.setLayoutManager(layoutManager);
                                CommissionRecycler commissionRecycler = new CommissionRecycler(projectDetailsBeanData);
                                commissionRv.setAdapter(commissionRecycler);

                                if (SharItOff.getShar().equals("显")) {
                                    project_details_layout1.setVisibility(View.VISIBLE);
                                    commissionRv.setVisibility(View.VISIBLE);
                                } else if (SharItOff.getShar().equals("隐")) {
                                    project_details_layout1.setVisibility(View.GONE);
                                    commissionRv.setVisibility(View.GONE);
                                }
                            }
                        }


//                        if (FinalContents.getIdentity().equals("63")) {
//                            linear3.setVisibility(View.GONE);
//                        } else {
                        if (projectDetailsBeanData.getHousesDynamics().size() == 0) {
                            linear3.setVisibility(View.GONE);
                        } else {
                            linear3.setVisibility(View.VISIBLE);
                            house_title.setText(projectDetailsBeanData.getHousesDynamics().get(0).getContent());
                            house_time.setText(projectDetailsBeanData.getHousesDynamics().get(0).getCreateDate());
                        }
//                        }

                        //户型信息
                        if (projectDetailsBeanData.getFamilyInfomations().size() == 0) {
                            linear4.setVisibility(View.GONE);
                        } else {
                            linear4.setVisibility(View.VISIBLE);

                            for (int i = 0; i < projectDetailsBeanData.getFamilyInfomations().size(); i++) {
                                project_details_family_tablayout.addTab(project_details_family_tablayout.newTab().setText(projectDetailsBeanData.getFamilyInfomations().get(i).getKey()));
                                num = num + projectDetailsBeanData.getFamilyInfomations().get(i).getValue().size();
                            }

                            project_details_family_tvs.setText("户型介绍(" + num + ")");

                            project_details_family_tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                                @Override
                                public void onTabSelected(TabLayout.Tab tab) {
                                    for (int i = 0; i < projectDetailsBeanData.getFamilyInfomations().size(); i++) {
                                        if (projectDetailsBeanData.getFamilyInfomations().get(i).getKey().equals(tab.getText().toString())) {
                                            LinearLayoutManager layoutManager_family = new LinearLayoutManager(ProjectDetails.this);
                                            layoutManager_family.setOrientation(LinearLayoutManager.HORIZONTAL);
                                            family.setLayoutManager(layoutManager_family);
                                            recyclerAdapter = new FamilyRecycler(projectDetailsBeanData.getFamilyInfomations().get(i).getValue());
                                            Log.i("户型信息", "户型信息" + projectDetailsBeanData.getFamilyInfomations().get(i).getKey() + "::" + tab.getText().toString());
                                            Log.i("户型信息", "户型信息走向1");
                                            switch (projectDetailsBeanData.getProjectListVo().getSaleStatus()) {
                                                case "1":
                                                    salestatus.setText("待售");
                                                    recyclerAdapter.setSales("待售");
                                                    break;
                                                case "2":
                                                    salestatus.setText("认筹");
                                                    recyclerAdapter.setSales("认筹");
                                                    break;
                                                case "3":
                                                    salestatus.setText("在售");
                                                    recyclerAdapter.setSales("在售");
                                                    break;
                                                case "4":
                                                    salestatus.setText("尾盘");
                                                    recyclerAdapter.setSales("尾盘");
                                                    break;
                                                case "5":
                                                    salestatus.setText("特房");
                                                    recyclerAdapter.setSales("特房");
                                                    break;
                                            }
                                            recyclerAdapter.setPrice("" + projectDetailsBeanData.getProjectListVo().getProductTotalPrice());
                                            family.setAdapter(recyclerAdapter);
                                            recyclerAdapter.notifyDataSetChanged();
                                            Log.i("户型信息", "户型信息走向1");
                                            return;
                                        }
                                    }
                                }

                                @Override
                                public void onTabUnselected(TabLayout.Tab tab) {
                                    //                添加未选中Tab的逻辑
                                }

                                @Override
                                public void onTabReselected(TabLayout.Tab tab) {
                                    for (int i = 0; i < projectDetailsBeanData.getFamilyInfomations().size(); i++) {
                                        if (projectDetailsBeanData.getFamilyInfomations().get(i).getKey().equals(tab.getText().toString())) {
                                            LinearLayoutManager layoutManager_family = new LinearLayoutManager(ProjectDetails.this);
                                            layoutManager_family.setOrientation(LinearLayoutManager.HORIZONTAL);
                                            family.setLayoutManager(layoutManager_family);
                                            recyclerAdapter = new FamilyRecycler(projectDetailsBeanData.getFamilyInfomations().get(i).getValue());
                                            Log.i("户型信息", "户型信息" + projectDetailsBeanData.getFamilyInfomations().get(i).getKey() + "::" + tab.getText().toString());
                                            Log.i("户型信息", "户型信息走向1");
                                            switch (projectDetailsBeanData.getProjectListVo().getSaleStatus()) {
                                                case "1":
                                                    salestatus.setText("待售");
                                                    recyclerAdapter.setSales("待售");
                                                    break;
                                                case "2":
                                                    salestatus.setText("认筹");
                                                    recyclerAdapter.setSales("认筹");
                                                    break;
                                                case "3":
                                                    salestatus.setText("在售");
                                                    recyclerAdapter.setSales("在售");
                                                    break;
                                                case "4":
                                                    salestatus.setText("尾盘");
                                                    recyclerAdapter.setSales("尾盘");
                                                    break;
                                                case "5":
                                                    salestatus.setText("特房");
                                                    recyclerAdapter.setSales("特房");
                                                    break;
                                            }
                                            recyclerAdapter.setPrice("" + projectDetailsBeanData.getProjectListVo().getProductTotalPrice());
                                            family.setAdapter(recyclerAdapter);
                                            recyclerAdapter.notifyDataSetChanged();
                                            Log.i("户型信息", "户型信息走向1");
                                            return;
                                        }
                                    }
                                }
                            });

                            LinearLayoutManager layoutManager_family = new LinearLayoutManager(ProjectDetails.this);
                            layoutManager_family.setOrientation(LinearLayoutManager.HORIZONTAL);
                            family.setLayoutManager(layoutManager_family);
                            recyclerAdapter = new FamilyRecycler(projectDetailsBeanData.getFamilyInfomations().get(0).getValue());
                            switch (projectDetailsBeanData.getProjectListVo().getSaleStatus()) {
                                case "1":
                                    salestatus.setText("待售");
                                    recyclerAdapter.setSales("待售");
                                    break;
                                case "2":
                                    salestatus.setText("认筹");
                                    recyclerAdapter.setSales("认筹");
                                    break;
                                case "3":
                                    salestatus.setText("在售");
                                    recyclerAdapter.setSales("在售");
                                    break;
                                case "4":
                                    salestatus.setText("尾盘");
                                    recyclerAdapter.setSales("尾盘");
                                    break;
                                case "5":
                                    salestatus.setText("特房");
                                    recyclerAdapter.setSales("特房");
                                    break;
                            }
                            recyclerAdapter.setPrice("" + projectDetailsBeanData.getProjectListVo().getProductTotalPrice());
                            family.setAdapter(recyclerAdapter);
                            recyclerAdapter.notifyDataSetChanged();

                        }

                        //奖励规则
                        if (projectDetailsBeanData.getProjectListVo().getAwardRules().equals("")) {
                            project_details_layout3.setVisibility(View.GONE);
                        } else {
                            project_details_layout3.setVisibility(View.VISIBLE);
                            award.setText(projectDetailsBeanData.getProjectListVo().getAwardRules());
                        }

                        //拓客工具
                        if (projectDetailsBeanData.getIsTalkTool().equals("0")) {
                            talktool_layout.setVisibility(View.GONE);
                        } else {
                            talktool_layout.setVisibility(View.VISIBLE);
                            LinearLayoutManager layoutManager_talktool = new LinearLayoutManager(ProjectDetails.this);
                            layoutManager_talktool.setOrientation(LinearLayoutManager.VERTICAL);
                            talktool.setLayoutManager(layoutManager_talktool);
                            TalktoolRecycler talktoolRecycler = new TalktoolRecycler(projectDetailsBeanData.getTalkToolData());
                            talktool.setAdapter(talktoolRecycler);
                        }


                        if (projectDetailsBeanData.getBuildingInfoStatus().equals("0")) {
                            linear5.setVisibility(View.GONE);
                        } else if (projectDetailsBeanData.getBuildingInfoStatus().equals("1")) {
                            linear5.setVisibility(View.VISIBLE);
                            Glide.with(ProjectDetails.this).load(FinalContents.getImageUrl() + projectDetailsBeanData.getBuildingImg()).into(building);
                        }

                        if (project_details_layout1.getVisibility() == View.GONE) {
                            if (project_details_layout2.getVisibility() == View.GONE) {
                                if (project_details_layout3.getVisibility() == View.GONE) {
                                    project_details_layout.setVisibility(View.GONE);
                                } else {
                                    project_details_layout3_checkbox.setTextSize(19);
                                    tp = project_details_layout3_checkbox.getPaint();
                                    tp.setFakeBoldText(true);
                                    project_details_layout3_view.setVisibility(View.VISIBLE);
                                    award.setVisibility(View.VISIBLE);
                                }
                            } else {
                                project_details_layout2_checkbox.setTextSize(19);
                                tp = project_details_layout2_checkbox.getPaint();
                                tp.setFakeBoldText(true);
                                project_details_layout2_view.setVisibility(View.VISIBLE);
                                report_rv.setVisibility(View.VISIBLE);
                            }
                        }

                        if (!FinalContents.getCityID().equals(FinalContents.getOldCityId())) {
                            if (project_details_layout3.getVisibility() == View.GONE) {
                                project_details_layout.setVisibility(View.GONE);
                            } else {
                                project_details_layout3_checkbox.setTextSize(19);
                                tp = project_details_layout3_checkbox.getPaint();
                                tp.setFakeBoldText(true);
                                project_details_layout3_view.setVisibility(View.VISIBLE);
                                award.setVisibility(View.VISIBLE);
                            }
                            project_details_layout2.setVisibility(View.GONE);
                            report_rv.setVisibility(View.GONE);
                            project_details_qt.setVisibility(View.VISIBLE);
                            project_details_all.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        project_details_scrollview.setVisibility(View.GONE);
                        Log.i("error", "项目详情内数据错误信息1：" + e.getMessage());
                        Log.i("error", "项目详情内数据错误信息2：" + e.getLocalizedMessage());
                        Log.i("error", "项目详情内数据错误信息3：" + e.getCause());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        Retrofit.Builder builder1 = new Retrofit.Builder();
        builder1.baseUrl(FinalContents.getBaseUrl());
        builder1.addConverterFactory(GsonConverterFactory.create());
        builder1.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build1 = builder1.build();
        MyService fzbInterface1 = build1.create(MyService.class);
        Observable<HouseBean> houseBean = fzbInterface1.getHouseBean(FinalContents.getUserID(), FinalContents.getProjectID(), "0", "1000");
        houseBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HouseBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HouseBean houseBean) {
                        final HouseBean.DataBean houseDataData = houseBean.getData();
//
                        if (FinalContents.getProjectType().equals("1")) {
                            linear6.setVisibility(View.GONE);
                        } else if (FinalContents.getProjectType().equals("2") || FinalContents.getProjectType().equals("3")) {
                            linear6.setVisibility(View.VISIBLE);
                            if (houseDataData.getPropertyHouseList().size() == 0) {
                                linear6.setVisibility(View.GONE);
                            } else {
                                linear6.setVisibility(View.VISIBLE);
                                String ids = houseDataData.getPropertyHouseList().get(0).getContent();//从pd里取出字符串
                                List tags = Arrays.asList(ids.split(","));//根据逗号分隔转化为list

                                repast_content.setTheme(ColorFactory.NONE);
                                repast_content.setTags(tags);
                                if (houseDataData.getPropertyHouseList().size() == 1) {
                                    project_details_linear1.setVisibility(View.VISIBLE);
                                    project_details_linear2.setVisibility(View.GONE);
                                    project_details_linear3.setVisibility(View.GONE);
                                    repast.setText(houseDataData.getPropertyHouseList().get(0).getType());
                                    repast.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Intent intent = new Intent(ProjectDetails.this, WebViewActivity.class);
                                            intent.putExtra("title", houseDataData.getPropertyHouseList().get(0).getType());
                                            intent.putExtra("time", houseDataData.getPropertyHouseList().get(0).getCreateDate());
                                            intent.putExtra("content", houseDataData.getPropertyHouseList().get(0).getContent());
                                            startActivity(intent);
                                        }
                                    });
                                } else if (houseDataData.getPropertyHouseList().size() == 2) {
                                    project_details_linear1.setVisibility(View.VISIBLE);
                                    project_details_linear2.setVisibility(View.VISIBLE);
                                    project_details_linear3.setVisibility(View.GONE);
                                    repast.setText(houseDataData.getPropertyHouseList().get(0).getType());
                                    repast.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Intent intent = new Intent(ProjectDetails.this, WebViewActivity.class);
                                            intent.putExtra("title", houseDataData.getPropertyHouseList().get(0).getType());
                                            intent.putExtra("time", houseDataData.getPropertyHouseList().get(0).getCreateDate());
                                            intent.putExtra("content", houseDataData.getPropertyHouseList().get(0).getContent());
                                            startActivity(intent);
                                        }
                                    });

                                    recuperate.setText(houseDataData.getPropertyHouseList().get(1).getType());
                                    recuperate.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Intent intent = new Intent(ProjectDetails.this, WebViewActivity.class);
                                            intent.putExtra("title", houseDataData.getPropertyHouseList().get(1).getType());
                                            intent.putExtra("time", houseDataData.getPropertyHouseList().get(1).getCreateDate());
                                            intent.putExtra("content", houseDataData.getPropertyHouseList().get(1).getContent());
                                            startActivity(intent);
                                        }
                                    });
                                    recuperate_content.setText(houseDataData.getPropertyHouseList().get(1).getContent());
                                } else if (houseDataData.getPropertyHouseList().size() == 3) {
                                    project_details_linear1.setVisibility(View.VISIBLE);
                                    project_details_linear2.setVisibility(View.VISIBLE);
                                    project_details_linear3.setVisibility(View.VISIBLE);
                                    repast.setText(houseDataData.getPropertyHouseList().get(0).getType());
                                    repast.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Intent intent = new Intent(ProjectDetails.this, WebViewActivity.class);
                                            intent.putExtra("title", houseDataData.getPropertyHouseList().get(0).getType());
                                            intent.putExtra("time", houseDataData.getPropertyHouseList().get(0).getCreateDate());
                                            intent.putExtra("content", houseDataData.getPropertyHouseList().get(0).getContent());
                                            startActivity(intent);
                                        }
                                    });

                                    recuperate.setText(houseDataData.getPropertyHouseList().get(1).getType());
                                    recuperate.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Intent intent = new Intent(ProjectDetails.this, WebViewActivity.class);
                                            intent.putExtra("title", houseDataData.getPropertyHouseList().get(1).getType());
                                            intent.putExtra("time", houseDataData.getPropertyHouseList().get(1).getCreateDate());
                                            intent.putExtra("content", houseDataData.getPropertyHouseList().get(1).getContent());
                                            startActivity(intent);
                                        }
                                    });
                                    recuperate_content.setText(houseDataData.getPropertyHouseList().get(1).getContent());
                                    business.setText(houseDataData.getPropertyHouseList().get(2).getType());
                                    business.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Intent intent = new Intent(ProjectDetails.this, WebViewActivity.class);
                                            intent.putExtra("title", houseDataData.getPropertyHouseList().get(2).getType());
                                            intent.putExtra("time", houseDataData.getPropertyHouseList().get(2).getCreateDate());
                                            intent.putExtra("content", houseDataData.getPropertyHouseList().get(2).getContent());
                                            startActivity(intent);
                                        }
                                    });
                                    business_content.setText(houseDataData.getPropertyHouseList().get(2).getContent());
                                }
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "项目详情错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });


        Retrofit.Builder builder2 = new Retrofit.Builder();
        builder2.baseUrl(FinalContents.getBaseUrl());
        builder2.addConverterFactory(GsonConverterFactory.create());
        builder2.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build2 = builder2.build();
        MyService fzbInterface2 = build2.create(MyService.class);
        Observable<ProjectHousesTrendListBean> projectHousesTrendListBeanObservable = fzbInterface2.getProjectHousesTrendListBean(FinalContents.getUserID(), FinalContents.getProjectID());
        projectHousesTrendListBeanObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ProjectHousesTrendListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("ResourceAsColor")
                    @Override
                    public void onNext(final ProjectHousesTrendListBean projectHousesTrendListBean) {
                        project_details_villa_title.setText(projectHousesTrendListBean.getData().getHouseTrendResult().getProjectName());
                        if (projectHousesTrendListBean.getData().getHouseTrendResult().getHouseTrendRatioVoList().size() != 0) {
                            project_details_villa_rv.setVisibility(View.VISIBLE);
                            project_details_villa_linear.setVisibility(View.VISIBLE);
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ProjectDetails.this);
                            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                            project_details_villa_rv.setLayoutManager(linearLayoutManager);
                            ProjectDetailsVillaAdapter projectDetailsVillaAdapter = new ProjectDetailsVillaAdapter(projectHousesTrendListBean.getData().getHouseTrendResult().getHouseTrendRatioVoList());
                            project_details_villa_rv.setAdapter(projectDetailsVillaAdapter);
                            projectDetailsVillaAdapter.notifyDataSetChanged();
                        } else {
                            project_details_villa_rv.setVisibility(View.GONE);
                            project_details_villa_linear.setVisibility(View.GONE);
                        }

                        if (projectHousesTrendListBean.getData().getHouseTrendResult().getHouseTrendVoList().size() != 0) {
                            project_details_tab_layout.setSelectedTabIndicator(R.drawable.tab_indicator);
                            for (int i = 0; i < projectHousesTrendListBean.getData().getHouseTrendResult().getHouseTrendVoList().size(); i++) {
                                switch (projectHousesTrendListBean.getData().getHouseTrendResult().getHouseTrendVoList().get(i).getProcuctType()) {
                                    case "1":
                                        project_details_tab_layout.addTab(project_details_tab_layout.newTab().setText("住宅"));
                                        break;
                                    case "2":
                                        project_details_tab_layout.addTab(project_details_tab_layout.newTab().setText("公寓"));
                                        break;
                                    case "3":
                                        project_details_tab_layout.addTab(project_details_tab_layout.newTab().setText("写字楼"));
                                        break;
                                    case "4":
                                        project_details_tab_layout.addTab(project_details_tab_layout.newTab().setText("商铺"));
                                        break;
                                    case "5":
                                        project_details_tab_layout.addTab(project_details_tab_layout.newTab().setText("别墅"));
                                        break;
                                }
                            }

                            indexList = projectHousesTrendListBean.getData().getHouseTrendResult().getHouseTrendVoList().get(0).getMonthList();
                            initChart(projectHousesTrendListBean.getData().getHouseTrendResult().getHouseTrendVoList().get(0).getMonthPriceList());


                            project_details_tab_layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                                @Override
                                public void onTabSelected(TabLayout.Tab tab) {
                                    //                添加选中Tab的逻辑
                                    String tabName = "";
                                    for (int i = 0; i < projectHousesTrendListBean.getData().getHouseTrendResult().getHouseTrendVoList().size(); i++) {
                                        switch (projectHousesTrendListBean.getData().getHouseTrendResult().getHouseTrendVoList().get(i).getProcuctType()) {
                                            case "1":
                                                tabName = "住宅";
                                                break;
                                            case "2":
                                                tabName = "公寓";
                                                break;
                                            case "3":
                                                tabName = "写字楼";
                                                break;
                                            case "4":
                                                tabName = "商铺";
                                                break;
                                            case "5":
                                                tabName = "别墅";
                                                break;
                                        }
                                        if (tabName.equals(tab.getText().toString())) {
                                            indexList = projectHousesTrendListBean.getData().getHouseTrendResult().getHouseTrendVoList().get(i).getMonthList();
                                            initChart(projectHousesTrendListBean.getData().getHouseTrendResult().getHouseTrendVoList().get(i).getMonthPriceList());
                                        }
                                    }

                                }

                                @Override
                                public void onTabUnselected(TabLayout.Tab tab) {
                                    //                添加未选中Tab的逻辑
                                }

                                @Override
                                public void onTabReselected(TabLayout.Tab tab) {
                                    //                再次选中tab的逻辑
                                    String tabName = "";
                                    for (int i = 0; i < projectHousesTrendListBean.getData().getHouseTrendResult().getHouseTrendVoList().size(); i++) {
                                        switch (projectHousesTrendListBean.getData().getHouseTrendResult().getHouseTrendVoList().get(i).getProcuctType()) {
                                            case "1":
                                                tabName = "住宅";
                                                break;
                                            case "2":
                                                tabName = "公寓";
                                                break;
                                            case "3":
                                                tabName = "写字楼";
                                                break;
                                            case "4":
                                                tabName = "商铺";
                                                break;
                                            case "5":
                                                tabName = "别墅";
                                                break;
                                        }
                                        if (tabName.equals(tab.getText().toString())) {
                                            indexList = projectHousesTrendListBean.getData().getHouseTrendResult().getHouseTrendVoList().get(i).getMonthList();
                                            initChart(projectHousesTrendListBean.getData().getHouseTrendResult().getHouseTrendVoList().get(i).getMonthPriceList());
                                        }
                                    }
                                }
                            });

                        } else {

                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "项目详情楼盘走势错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //TODO 详情页趋势图绘制
    private void initChart(final List<Integer> list) {

        //显示边界
        details_chart.setDrawBorders(false);
        //无数据时显示的文字
        details_chart.setNoDataText("暂无数据");
        details_chart.setBorderColor(R.color.GridColor);
        //折线图不显示数值
//        data.setDrawValues(false);
        //得到X轴
        XAxis xAxis = details_chart.getXAxis();
        //设置X轴的位置（默认在上方)
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //设置X轴坐标之间的最小间隔
        xAxis.setGranularity(1);
        //设置X轴的刻度数量，第二个参数为true,将会画出明确数量（带有小数点），但是可能值导致不均匀，默认（6，false）
        xAxis.setLabelCount(list.size(), false);
        //设置X轴的值（最小值、最大值、然后会根据设置的刻度数量自动分配刻度显示）
        xAxis.setAxisMinimum(0f);
        xAxis.setAxisMaximum(list.size());
        xAxis.setTextColor(R.color.LableColor);
        xAxis.setGridColor(R.color.GridColor);
        //不显示网格线
        xAxis.setDrawGridLines(false);
        // 标签倾斜
        xAxis.setLabelRotationAngle(0);
        //设置X轴值为字符串
        xAxis.setValueFormatter(new IndexAxisValueFormatter(indexList));

        //得到Y轴
        YAxis yAxis = details_chart.getAxisLeft();
        YAxis rightYAxis = details_chart.getAxisRight();
        //设置Y轴是否显示
        rightYAxis.setEnabled(false); //右侧Y轴不显示
        // yAxis.setEnabled(false);
        //设置y轴坐标之间的最小间隔
        //不显示网格线
        yAxis.setGridColor(R.color.GridColor);
        yAxis.setTextColor(R.color.LableColor);
        yAxis.setDrawGridLines(true);
        //设置Y轴坐标之间的最小间隔
        yAxis.setGranularity(1);
        //设置y轴的刻度数量
        //+2：最大值n就有n+1个刻度，在加上y轴多一个单位长度，为了好看，so+2
        yAxis.setLabelCount(4, false);
        //设置从Y轴值
        yAxis.setAxisMinimum(0);
        yAxis.setAxisMaximum(list.get(0)+10000);
        //图例：得到Lengend
        Legend legend = details_chart.getLegend();
        //隐藏Lengend
        legend.setEnabled(false);
        //隐藏描述
        Description description = new Description();
        description.setEnabled(false);
        details_chart.setDescription(description);
        //图标刷新
        details_chart.invalidate();
        details_chart.animateXY(2000, 2000);
        setData(list);

        // don't forget to refresh the drawing
        details_chart.invalidate();
    }

    //TODO 详情页趋势图数据填充
    private void setData(final List<Integer> list) {

        ArrayList<Entry> values = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            values.add(new Entry(i, list.get(i)));
        }

        LineDataSet set1;

        if (details_chart.getData() != null && details_chart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) details_chart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            details_chart.getData().notifyDataChanged();
            details_chart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(values, "DataSet");

            set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
            set1.setCubicIntensity(0.2f);

            set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
            set1.setCubicIntensity(0.2f);
            set1.setDrawFilled(false);
            set1.setDrawCircles(true);
            set1.setLineWidth(1.8f);
            set1.setCircleRadius(3f);
            set1.setValueTextSize(9f);
            set1.setHighlightEnabled(!set1.isHighlightEnabled());
            set1.setCircleColor(Color.parseColor("#FFFFFF"));
            set1.setCircleHoleColor(Color.parseColor("#5484FF"));
            set1.setHighLightColor(Color.BLACK);
            set1.setColor(Color.parseColor("#5484FF"));
//            set1.setFillColor(R.color.mian);
            set1.setFillAlpha(20);
            set1.setDrawValues(!set1.isDrawValuesEnabled());
            set1.setDrawHorizontalHighlightIndicator(false);
            set1.setFillFormatter(new IFillFormatter() {
                @Override
                public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                    return details_chart.getAxisLeft().getAxisMinimum();
                }
            });


            // create a data object with the data sets
            LineData data = new LineData(set1);
            data.setValueTextSize(9f);
            data.setDrawValues(false);

            // set data
            details_chart.setData(data);
            // 设置放大限制
            details_chart.getViewPortHandler().setMaximumScaleX(1.0f); // 限制X轴放大限制
            details_chart.getViewPortHandler().setMaximumScaleY(1.0f); // 限制Y轴放大限制
        }
        details_chart.animateXY(2000, 2000);

    }

    @Override
    public void MyItems(int position) {
        Intent buildingInformationintent = new Intent(ProjectDetails.this, BuildingInformationActivity.class);
        buildingInformationintent.putExtra("pic", FinalContents.getImageUrl() + projectDetailsBeanData.getBuildingImg());
        startActivity(buildingInformationintent);
    }

    @Override
    public void onScrollChanged(GradationScrollView scrollView, int x, final int y, int oldx, int oldy) {
        int minHeight = 50;
        int maxHeight = (int) (backimg.getMeasuredHeight()*0.5);
        if (maxHeight == 0) {
            maxHeight = 500;
        }
        int color = Color.parseColor("#334485");
        final int red = (color & 0xff0000) >> 16;
        final int green = (color & 0x00ff00) >> 8;
        final int blue = (color & 0x0000ff);
        if (scrollView.getScrollY() <= minHeight) {
            mAlpha = 0;
        } else if (scrollView.getScrollY() > maxHeight) {
            mAlpha = 255;
        } else {
            mAlpha = (scrollView.getScrollY() - minHeight) * 255 / (maxHeight - minHeight);
        }

        if (mAlpha <= 0) {
            project_details_title.setText("");
            project_details_toolbar.setBackgroundColor(Color.argb((int) 0, red, green, blue));
        } else if (mAlpha >= 255) {
            project_details_title.setText("项目详情");
            project_details_toolbar.setBackgroundColor(Color.argb((int) 225, red, green, blue));
        } else {
            float scale = (float) y / maxHeight;
            float alpha = (255 * scale);
            project_details_title.setText("项目详情");
            project_details_toolbar.setBackgroundColor(Color.argb((int) alpha, red, green, blue));
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("数据","走一走:"+CityContents.getIsReport());
        CityContents.setIsReport("");
    }
}
