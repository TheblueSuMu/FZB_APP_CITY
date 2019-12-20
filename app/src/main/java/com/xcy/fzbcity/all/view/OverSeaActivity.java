package com.xcy.fzbcity.all.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.lzj.gallery.library.views.BannerViewPager;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.adapter.CityAdapter;
import com.xcy.fzbcity.all.adapter.IssueAdapter;
import com.xcy.fzbcity.all.adapter.OverseaCityAdapter;
import com.xcy.fzbcity.all.adapter.ProjectLabelAdapter;
import com.xcy.fzbcity.all.adapter.RecyclerAdapter;
import com.xcy.fzbcity.all.api.CityContents;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.application.DemoApplication;
import com.xcy.fzbcity.all.fragment.ComprehensiveFragment;
import com.xcy.fzbcity.all.fragment.CountryFragment;
import com.xcy.fzbcity.all.fragment.HouseTypeFragment;
import com.xcy.fzbcity.all.fragment.PriceFragment;
import com.xcy.fzbcity.all.fragment.ScreeningFragment;
import com.xcy.fzbcity.all.modle.HotBean;
import com.xcy.fzbcity.all.modle.ImgData;
import com.xcy.fzbcity.all.modle.LabelBean;
import com.xcy.fzbcity.all.modle.NationBean;
import com.xcy.fzbcity.all.persente.MyLinearLayoutManager;
import com.xcy.fzbcity.all.persente.SharItOff;
import com.xcy.fzbcity.all.persente.SingleClick;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.CommonUtil;
import com.xcy.fzbcity.all.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OverSeaActivity extends AllActivity implements View.OnClickListener, SensorEventListener, ScreeningFragment.FragmentInteraction, SwipeRefreshLayout.OnRefreshListener {
    private BannerViewPager banner;
    private List<String> list_img;

    private String arrposid = "2"; //新闻类型
    private String arrpos = "4"; //专场新闻类型
    private RecyclerView nationRv;
    private RecyclerView issueRv;
    private RecyclerView hotRv;
    private TextView sort;
    private TextView screen;
    private TextView state;
    private TextView price;
    private TextView house_type;
    private TextView title;
    private RadioButton oversea_rb_1;
    private RadioButton oversea_rb_2s;
    private RadioButton oversea_rb_3;
    private RadioButton oversea_rb_4;
    private RadioButton oversea_rb_5;
    private RadioGroup oversea_rg;
    private LinearLayout oversea_ll;
    private LinearLayout oversea_lll;
    private ScrollView scrollView;
    private EditText search;
    private LinearLayout back;
    private ImageView report;
    FrameLayout oversea_fl;
    private String projectLabel = "";

    private LinearLayout oversea_linear;


    ComprehensiveFragment comprehensiveFragment;
    CountryFragment countryFragment;
    PriceFragment priceFragment;
    HouseTypeFragment houseTypeFragment;
    ScreeningFragment screeningFragment;

    FragmentManager manager;
    FragmentTransaction transaction;
    private List<NationBean.DataBean> nationlist;
    private List<ImgData.DataBean> imglist;
    private List<ImgData.DataBean> imagelist;
    private List<HotBean.DataBean.RowsBean> hotlist;
    private DemoApplication application;
    private RecyclerAdapter recyclerAdapter;
    private Map<Integer, String> LabelMap = new HashMap<>();
    View seview;
    private ImageView all_no_information;

    //TODO     摇一摇
    private SensorManager mSensorManager;
    private Vibrator vibrator;
    private RecyclerView project_lable_rv;
    private SwipeRefreshLayout oversea_ptrclass;
    private ImageView banner_img;
    private ImageView oversea_city_wide_img;
    private ImageView oversea_across_the_city_img;
    private ImageView oversea_map_img;
    private LinearLayout oversea_linear_issue;
    //    private DemoApplication application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_over_sea);
        init_No_Network();

    }

    private void init_No_Network() {
        boolean networkAvailable = CommonUtil.isNetworkAvailable(this);
        if (networkAvailable) {
            title = findViewById(R.id.oversea_title);
            mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
            vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            seview = findViewById(R.id.seview);

            initfvb();
            init();
            initView();
            if (!FinalContents.getProjectType().equals("1")) {
                initissue();
            }
            EventBus.getDefault().register(this);
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

    // 3.2 +实现接口，实现回调
    @Override
    public void process(String str) {
        if (str != null) {
            if (str.equals("重置")) {
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                screeningFragment = new ScreeningFragment();
                transaction.replace(R.id.oversea_fl, screeningFragment);
                transaction.commit();
                Log.i("重置", "yi重置");
            }
        }
    }

    /**
     * 摇一摇
     */

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (FinalContents.getCityID().equals(FinalContents.getOldCityId())) {
            if (FinalContents.getIdentity().equals("63")) {

            }else if (FinalContents.getIdentity().equals("7")) {

            } else {
                int sensortype = event.sensor.getType();
                float[] values = event.values;
                if (sensortype == Sensor.TYPE_ACCELEROMETER) {
                    /*因为一般正常情况下，任意轴数值最大就在9.8~10之间，只有在你突然摇动手机
                     *的时候，瞬时加速度才会突然增大或减少。
                     *所以，经过实际测试，只需监听任一轴的加速度大于14的时候，改变你需要的设置
                     *就OK了~~~
                     */
                    if (Math.abs(values[0]) > 20 || Math.abs(values[1]) > 20 || Math.abs(values[2]) > 20) {

                        if (SharItOff.getShar().equals("隐")) {
                            SharItOff.setShar("显");
                            ToastUtil.showToast(application, "佣金已显示，如需隐藏请摇动");
                        } else if (SharItOff.getShar().equals("显")) {
                            SharItOff.setShar("隐");
                            ToastUtil.showToast(application, "佣金已隐藏，如需显示请摇动");
                        }
                        inithot();

                        vibrator.vibrate(100);
                    }
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    private void init() {
        if (FinalContents.getProjectType().equals("2")) {
            title.setText("海外房产");
            arrposid = "2";
            arrpos = "4";
            oversea_linear.setVisibility(View.GONE);
            nationRv.setVisibility(View.VISIBLE);
            oversea_rb_2s.setText("国家");
            state.setText("国家");
            seview.setVisibility(View.VISIBLE);
            initcity();
            inithot();
        } else if (FinalContents.getProjectType().equals("3")) {
            title.setText("旅居房产");
            arrposid = "5";
            arrpos = "6";
            oversea_linear.setVisibility(View.GONE);
            nationRv.setVisibility(View.VISIBLE);
            oversea_rb_2s.setText("城市");
            state.setText("城市");
            seview.setVisibility(View.VISIBLE);
            initcity();
            inithot();
         }else if (FinalContents.getProjectType().equals("1")) {
            title.setText("城市房产");
            arrposid = "3";
            oversea_linear.setVisibility(View.VISIBLE);
            nationRv.setVisibility(View.GONE);
            oversea_linear_issue.setVisibility(View.GONE);
            oversea_rb_2s.setText("城市");
            state.setText("城市");
            seview.setVisibility(View.VISIBLE);
            inithot();
        }
    }

    private void initvoid() {
        FinalContents.setComprehensiveSorting("");
        FinalContents.setProjectLabel("");
        FinalContents.setNation("");
        FinalContents.setProjectPriceStart("");
        FinalContents.setProjectPriceEnd("");
        FinalContents.setApartment("");
        FinalContents.setAreaSection("");
        FinalContents.setFfProjectTrait("");
        FinalContents.setProcuctType("");
        FinalContents.setFitmentState("");
    }

    private void initfvb() {

        StatusBar.makeStatusBarTransparent(this);

        initvoid();

        //  TODO    城市房产

        oversea_city_wide_img = findViewById(R.id.oversea_city_wide_img);
        oversea_across_the_city_img = findViewById(R.id.oversea_across_the_city_img);
        oversea_map_img = findViewById(R.id.oversea_map_img);
        oversea_linear_issue = findViewById(R.id.oversea_linear_issue);

        oversea_city_wide_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CityContents.setCityType("1");
                FinalContents.setIfCityType("1");
                Intent intent = new Intent(OverSeaActivity.this,CityWideActivity.class);
                startActivity(intent);
            }
        });
        oversea_across_the_city_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CityContents.setCityType("2");
                FinalContents.setIfCityType("2");
                Intent intent = new Intent(OverSeaActivity.this,CityWideActivity.class);
                startActivity(intent);
            }
        });
        oversea_map_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FinalContents.setIfCity(FinalContents.getCityID());
                FinalContents.setIfCityType("");
                Intent intent_overseas = new Intent(view.getContext(), MapHouseActivity.class);
                startActivity(intent_overseas);
            }
        });


        //  TODO    城市房产    结束

        oversea_ptrclass = findViewById(R.id.oversea_ptrclass);

        oversea_ptrclass.setOnRefreshListener(this);

        application = (DemoApplication) getApplication();
        comprehensiveFragment = application.getComprehensiveFragment();
        countryFragment = application.getCountryFragment();
        priceFragment = application.getPriceFragment();
        houseTypeFragment = application.getHouseTypeFragment();
        screeningFragment = application.getScreeningFragment();

        nationlist = application.getNationlist();
        imagelist = application.getImagelist();
        imglist = application.getImglist();
        hotlist = application.getHotlist();

//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        banner = findViewById(R.id.banner_2);

        nationRv = findViewById(R.id.overseas_nation);
        issueRv = findViewById(R.id.overseas_issue);
        hotRv = findViewById(R.id.overseas_hot);

        oversea_linear = findViewById(R.id.oversea_linear);

        sort = findViewById(R.id.overseas_sort);
        screen = findViewById(R.id.overseas_screen);
        state = findViewById(R.id.overseas_state);
        price = findViewById(R.id.overseas_price);
        house_type = findViewById(R.id.overseas_house_type);

        oversea_fl = findViewById(R.id.oversea_fl);
        oversea_rb_1 = findViewById(R.id.oversea_rb_1);
        oversea_rb_2s = findViewById(R.id.oversea_rb_2s);
        oversea_rb_3 = findViewById(R.id.oversea_rb_3);
        oversea_rb_4 = findViewById(R.id.oversea_rb_4);
        oversea_rb_5 = findViewById(R.id.oversea_rb_5);
        oversea_rg = findViewById(R.id.oversea_rg);
        oversea_ll = findViewById(R.id.oversea_ll);
        oversea_lll = findViewById(R.id.oversea_lll);

        banner_img = findViewById(R.id.banner_img);

        all_no_information = findViewById(R.id.all_no_information);

        project_lable_rv = findViewById(R.id.project_Lable_rv);

        search = findViewById(R.id.oversea_search);
        search.setFocusable(false);
        back = findViewById(R.id.oversea_back);
        report = findViewById(R.id.oversea_report);

        if (FinalContents.getCityID().equals(FinalContents.getOldCityId())) {
            report.setVisibility(View.VISIBLE);
            if (FinalContents.getIdentity().equals("63") || FinalContents.getIdentity().equals("4") || FinalContents.getIdentity().equals("5") || FinalContents.getIdentity().equals("7") || FinalContents.getIdentity().equals("8") || FinalContents.getIdentity().equals("9") ) {
                report.setVisibility(View.GONE);
            }else {
                report.setVisibility(View.VISIBLE);
            }
        }else {
            report.setVisibility(View.GONE);
        }

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OverSeaActivity.this, SearchInterfaceActivity.class);
                startActivity(intent);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OverSeaActivity.this, ReportActivity.class);
                startActivity(intent);
            }
        });

        oversea_fl.setOnClickListener(null);

        scrollView = findViewById(R.id.oversea_scroll);

        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.replace(R.id.oversea_fl, comprehensiveFragment);
        transaction.commit();

        oversea_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (FinalContents.getCityID().equals(FinalContents.getOldCityId())) {
                    report.setVisibility(View.VISIBLE);
                }else {
                    report.setVisibility(View.GONE);
                }
                if (FinalContents.getIdentity().equals("63") || FinalContents.getIdentity().equals("4") || FinalContents.getIdentity().equals("5") || FinalContents.getIdentity().equals("7")  || FinalContents.getIdentity().equals("8") || FinalContents.getIdentity().equals("9")) {
                    report.setVisibility(View.GONE);
                }
                oversea_ll.setVisibility(View.GONE);
            }
        });

        oversea_lll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (FinalContents.getCityID().equals(FinalContents.getOldCityId())) {
                    report.setVisibility(View.VISIBLE);
                }else {
                    report.setVisibility(View.GONE);
                }
                if (FinalContents.getIdentity().equals("63") || FinalContents.getIdentity().equals("4") || FinalContents.getIdentity().equals("5") || FinalContents.getIdentity().equals("7") || FinalContents.getIdentity().equals("8") || FinalContents.getIdentity().equals("9") ) {
                    report.setVisibility(View.GONE);
                }
                oversea_ll.setVisibility(View.GONE);
            }
        });

        oversea_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                manager = getSupportFragmentManager();
                transaction = manager.beginTransaction();
                if (oversea_rb_1.isChecked() == true) {
                    transaction.replace(R.id.oversea_fl, comprehensiveFragment);
                } else if (oversea_rb_2s.isChecked() == true) {
                    transaction.replace(R.id.oversea_fl, countryFragment);
                } else if (oversea_rb_3.isChecked() == true) {
                    transaction.replace(R.id.oversea_fl, priceFragment);
                } else if (oversea_rb_4.isChecked() == true) {
                    transaction.replace(R.id.oversea_fl, houseTypeFragment);
                } else if (oversea_rb_5.isChecked() == true) {
                    transaction.replace(R.id.oversea_fl, screeningFragment);
                }
                transaction.commit();
            }
        });

        initProjectLabel();
        sort.setOnClickListener(this);
        screen.setOnClickListener(this);
        state.setOnClickListener(this);
        price.setOnClickListener(this);
        house_type.setOnClickListener(this);
    }


    //点击事件
    @SingleClick(1000)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.overseas_sort:
                report.setVisibility(View.GONE);
                oversea_rb_1.setChecked(true);
                oversea_ll.setVisibility(View.VISIBLE);
                scrollView.setOnClickListener(null);
                break;
            case R.id.overseas_screen:
                report.setVisibility(View.GONE);
                oversea_rb_5.setChecked(true);
                oversea_ll.setVisibility(View.VISIBLE);
                scrollView.setOnClickListener(null);
                break;
            case R.id.overseas_state:
                report.setVisibility(View.GONE);
                oversea_rb_2s.setChecked(true);
                oversea_ll.setVisibility(View.VISIBLE);
                scrollView.setOnClickListener(null);
                break;
            case R.id.overseas_price:
                report.setVisibility(View.GONE);
                oversea_rb_3.setChecked(true);
                oversea_ll.setVisibility(View.VISIBLE);
                scrollView.setOnClickListener(null);
                break;
            case R.id.overseas_house_type:
                report.setVisibility(View.GONE);
                oversea_rb_4.setChecked(true);
                oversea_ll.setVisibility(View.VISIBLE);
                scrollView.setOnClickListener(null);
                break;
        }
    }

    private void initProjectLabel() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<LabelBean> nationBean = fzbInterface.getLabel(FinalContents.getProjectType(),FinalContents.getUserID(),"1");
        nationBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LabelBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(final LabelBean labelBean) {
                        if (labelBean.getData().size() != 0) {
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(OverSeaActivity.this);
                            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                            project_lable_rv.setLayoutManager(linearLayoutManager);
                            ProjectLabelAdapter reportItemAdapter = new ProjectLabelAdapter(labelBean.getData());
                            project_lable_rv.setAdapter(reportItemAdapter);
                            reportItemAdapter.notifyDataSetChanged();
                            reportItemAdapter.setOnItemClickListener(new ProjectLabelAdapter.OnItemClickLisenter() {
                                @Override
                                public void onItemClick(CheckBox checkBox, int postion) {
                                    projectLabel = "";
                                    if (checkBox.isChecked()) {
                                        LabelMap.put(postion, "," + labelBean.getData().get(postion).getLable());
                                        Log.i("项目卖点", "来来来：" + labelBean.getData().get(postion).getId() + "名字:" + labelBean.getData().get(postion).getLable());
                                    } else {
                                        LabelMap.put(postion, "");
                                    }
                                    for (int i = 0; i < LabelMap.size(); i++) {
                                        projectLabel = projectLabel + LabelMap.get(i);
                                    }

                                    Log.i("项目卖点", "数据：" + projectLabel);
                                    FinalContents.setProjectLabel(projectLabel);
                                    inithot();
                                }
                            });
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("海外筛选", "楼盘特色/项目标签错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initcity() {

        if (nationlist.size() == 0) {
            Retrofit.Builder builder = new Retrofit.Builder();
            builder.baseUrl(FinalContents.getBaseUrl());
            builder.addConverterFactory(GsonConverterFactory.create());
            builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
            Retrofit build = builder.build();
            MyService fzbInterface = build.create(MyService.class);
            Observable<NationBean> nationBean = fzbInterface.getNationBean(FinalContents.getUserID(), FinalContents.getCityID(), FinalContents.getProjectType());
            nationBean.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<NationBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(NationBean nationBean) {
                            nationlist = nationBean.getData();
                            if (FinalContents.getProjectType().equals("2")) {
                                LinearLayoutManager layoutManager = new LinearLayoutManager(OverSeaActivity.this);
                                layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                                nationRv.setLayoutManager(layoutManager);
                                OverseaCityAdapter recyclerAdapter = new OverseaCityAdapter(nationlist);
                                nationRv.setAdapter(recyclerAdapter);
                                recyclerAdapter.notifyDataSetChanged();
                            } else if (FinalContents.getProjectType().equals("3")) {
                                LinearLayoutManager layoutManager = new LinearLayoutManager(OverSeaActivity.this);
                                layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                                nationRv.setLayoutManager(layoutManager);
                                CityAdapter recyclerAdapter = new CityAdapter(nationlist);
                                nationRv.setAdapter(recyclerAdapter);
                                recyclerAdapter.notifyDataSetChanged();
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.i("MyCL", "OverSeaActivity错误信息：" + e.getMessage());
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        } else {
            if (FinalContents.getProjectType().equals("2")) {
                LinearLayoutManager layoutManager = new LinearLayoutManager(this);
                layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                nationRv.setLayoutManager(layoutManager);
                OverseaCityAdapter recyclerAdapter = new OverseaCityAdapter(nationlist);
                nationRv.setAdapter(recyclerAdapter);
                recyclerAdapter.notifyDataSetChanged();
            } else if (FinalContents.getProjectType().equals("3")) {
                LinearLayoutManager layoutManager = new LinearLayoutManager(OverSeaActivity.this);
                layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                nationRv.setLayoutManager(layoutManager);
                CityAdapter recyclerAdapter = new CityAdapter(nationlist);
                nationRv.setAdapter(recyclerAdapter);
                recyclerAdapter.notifyDataSetChanged();
            }
        }


    }


    //首页轮播图
    private void initView() {
        list_img = new ArrayList<>();
        list_img.clear();
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Log.i("轮播图","FinalContents.getUserID()：" + FinalContents.getUserID());
        Log.i("轮播图","FinalContents.getCityID()：" + FinalContents.getCityID());
        Log.i("轮播图","FinalContents.getProjectType()：" + FinalContents.getProjectType());
        Log.i("轮播图","arrposid：" + arrposid);
        Observable<ImgData> userMessage = fzbInterface.getBannerList(FinalContents.getUserID(), FinalContents.getCityID(), FinalContents.getProjectType(), arrposid);
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ImgData>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(ImgData imgData) {
                        imglist = imgData.getData();

                        if (imglist.size() != 0) {
                            banner_img.setVisibility(View.GONE);
                            banner.setVisibility(View.VISIBLE);
                            for (int i = 0; i < imglist.size(); i++) {
                                list_img.add(FinalContents.getImageUrl() + imglist.get(i).getCoverImg());
                            }
                            banner.initBanner(list_img, false)//关闭3D画廊效果
                                    .addPageMargin(10, 50)//参数1page之间的间距,参数2中间item距离边界的间距
                                    .addStartTimer(8)//自动轮播5秒间隔
                                    .addPointBottom(7)
                                    .addRoundCorners(12)//圆角
                                    .finishConfig()//这句必须加
                                    .addBannerListener(new BannerViewPager.OnClickBannerListener() {
                                        @Override
                                        public void onBannerClick(int position) {
                                            FinalContents.setProjectID(imglist.get(position).getProject().getId());
                                            FinalContents.setNewID(imglist.get(position).getId());
                                            //点击item
                                            Intent intent = new Intent(OverSeaActivity.this, WebViewActivity.class);
                                            intent.putExtra("title", "新闻详情");
                                            intent.putExtra("webview", imglist.get(position).getContent());
                                            startActivity(intent);
                                        }
                                    });
                        }else {
                            banner_img.setVisibility(View.VISIBLE);
                            banner.setVisibility(View.GONE);
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

    private void initissue() {

        list_img = new ArrayList<>();

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<ImgData> userMessage = fzbInterface.getBannerList(FinalContents.getUserID(), FinalContents.getCityID(), FinalContents.getProjectType(), arrpos);
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ImgData>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(ImgData imgData) {
                        imagelist = imgData.getData();
                        if (imagelist.size() != 0) {
                            oversea_linear_issue.setVisibility(View.VISIBLE);
                            issueRv.setVisibility(View.VISIBLE);
                            LinearLayoutManager layoutManager = new LinearLayoutManager(OverSeaActivity.this);
                            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

                            issueRv.setLayoutManager(layoutManager);
                            IssueAdapter recyclerAdapter = new IssueAdapter(imagelist);
                            issueRv.setAdapter(recyclerAdapter);
                            recyclerAdapter.notifyDataSetChanged();
                        }else {
                            oversea_linear_issue.setVisibility(View.GONE);
                            issueRv.setVisibility(View.GONE);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        oversea_linear_issue.setVisibility(View.GONE);
                        issueRv.setVisibility(View.GONE);
                        Log.i("列表数据获取错误", "错误" + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    @SuppressLint("WrongConstant")
    private void inithot() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<HotBean> userMessage = fzbInterface.getList(FinalContents.getUserID(), FinalContents.getCityID(), FinalContents.getComprehensiveSorting(), FinalContents.getProjectLabel(), FinalContents.getProjectType(),"", FinalContents.getNation(), FinalContents.getProjectPriceStart(), FinalContents.getProjectPriceEnd(), FinalContents.getApartment(), FinalContents.getAreaSection(), FinalContents.getFfProjectTrait(), FinalContents.getProcuctType(), FinalContents.getFitmentState(),"","1000");
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HotBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(HotBean hotBean) {
                        HotBean.DataBean hotBeanData = hotBean.getData();
                        hotlist = hotBeanData.getRows();

                        if (hotlist.size() != 0) {
                            all_no_information.setVisibility(View.GONE);
                            hotRv.setVisibility(View.VISIBLE);
                            //在此处修改布局排列方向
                            MyLinearLayoutManager layoutManager = new MyLinearLayoutManager(OverSeaActivity.this);
                            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                            layoutManager.setScrollEnabled(false);
                            hotRv.setLayoutManager(layoutManager);
                            recyclerAdapter = new RecyclerAdapter(hotlist);
                            hotRv.setAdapter(recyclerAdapter);
                            recyclerAdapter.notifyDataSetChanged();
                        } else {
                            all_no_information.setVisibility(View.VISIBLE);
                            hotRv.setVisibility(View.GONE);
                        }


                    }

                    @Override
                    public void onError(Throwable e) {
                        all_no_information.setVisibility(View.VISIBLE);
                        hotRv.setVisibility(View.GONE);
                        Log.i("列表数据获取错误", "错误" + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @Subscribe(threadMode = ThreadMode.MAIN, priority = 100, sticky = false) //在ui线程执行，优先级为100
    public void onEvent(String url) {
        if (FinalContents.getComprehensiveSorting().equals("10")) {
            sort.setText("默认排序");
        } else if (FinalContents.getComprehensiveSorting().equals("20")) {
            sort.setText("价格降序");
        } else if (FinalContents.getComprehensiveSorting().equals("30")) {
            sort.setText("价格升序");
        } else if (FinalContents.getComprehensiveSorting().equals("40")) {
            sort.setText("成交排序");
        }
        inithot();
        oversea_ll.setVisibility(View.GONE);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        FinalContents.setIndex(-1);
    }

    @Override
    protected void onResume() {
        super.onResume();

        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    protected void onPause() {
        super.onPause();

        //        TODO 摇一摇
        mSensorManager.unregisterListener(this);

    }

    @Override
    protected void onStop() {
        super.onStop();

        mSensorManager.unregisterListener(this);

    }

    @Override
    public void onRefresh() {

        if (oversea_ptrclass.isRefreshing()) {//如果正在刷新
            initfvb();
            initView();
            if (!FinalContents.getProjectType().equals("1")) {
                initissue();
            }
            init();
            oversea_ptrclass.setRefreshing(false);//取消刷新
        }

    }
}
