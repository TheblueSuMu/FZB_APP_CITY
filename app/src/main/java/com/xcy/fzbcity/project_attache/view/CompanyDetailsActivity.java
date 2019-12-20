package com.xcy.fzbcity.project_attache.view;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
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
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.api.NewlyIncreased;
import com.xcy.fzbcity.all.database.DataNumBean;
import com.xcy.fzbcity.all.database.FinanceBean;
import com.xcy.fzbcity.all.modle.CompanyDetailsBean;
import com.xcy.fzbcity.all.persente.SingleClick;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.CommonUtil;
import com.xcy.fzbcity.all.utils.ToastUtil;
import com.xcy.fzbcity.all.view.AllActivity;
import com.xcy.fzbcity.project_side.view.MyClientActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CompanyDetailsActivity extends AllActivity implements View.OnClickListener {

    RelativeLayout company_details_return;
    TextView company_details_call;
    ImageView details_change;

    LinearLayout company_details_ll1;
    LinearLayout company_details_ll2;
    LinearLayout company_details_ll3;
    LinearLayout company_details_ll4;
    LinearLayout company_details_ll5;

    TextView company_details_tv1;
    TextView company_details_tv2;
    TextView company_details_tv3;
    TextView company_details_tv4;
    TextView company_details_tv5;
    TextView company_details_tv8;
    TextView company_details_tv9;
    TextView company_details_tv10;
    TextView company_details_tv11;
    TextView company_details_tv12;

    RadioButton company_details_rb1;
    RadioButton company_details_rb2;
    RadioButton company_details_rb3;
    RadioButton company_details_rb4;
    RadioButton company_details_rb5;
    RadioButton company_details_rb6;
    RadioButton company_details_rb7;
    RadioButton company_details_rb8;

    LinearLayout details_ll1;
    LinearLayout details_ll2;
    LinearLayout details_ll3;
    LinearLayout details_ll4;
    LinearLayout details_ll5;
    LinearLayout details_ll6;
    LinearLayout details_ll7;
    LinearLayout ll1;

    LinearLayout project_attache_ll1;
    LinearLayout project_attache_ll2;
    LinearLayout project_attache_ll3;
    LinearLayout project_attache_ll4;

    TextView details_tv1;
    TextView details_tv2;
    TextView details_tv3;
    TextView details_tv4;
    TextView details_tv5;
    TextView details_tv6;
    TextView details_tv7;
    TextView details_tv8;
    TextView tv1;
    TextView company_details_new_tv1;
    TextView company_details_new_tv2;
    TextView company_details_ttv;
    TextView company_details_ttcall;

    private LineChart details_chart;
    private List<Integer> integers;
    private List<String> indexList;

    RadioGroup details_rg1;
    RadioGroup details_rg2;
    private Intent intent;

    private CompanyDetailsBean.DataBean.StoreInfoBean storeInfo;
    private int year;
    private int month;
    private int dayOfMonth;
    private String string1;
    private String string2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_attache_activity_company_details);

        init_No_Network();
    }

    private void init_No_Network() {
        boolean networkAvailable = CommonUtil.isNetworkAvailable(this);
        if (networkAvailable) {
            NewlyIncreased.setTag("");
            NewlyIncreased.setStartDate("");
            NewlyIncreased.setEndDate("");
            NewlyIncreased.setYJType("");
            NewlyIncreased.setYJstartDate("");
            NewlyIncreased.setYJendDate("");
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
            ToastUtil.showLongToast(CompanyDetailsActivity.this, "当前无网络，请检查网络后再进行登录");
        }
    }

    private void initView() {

        StatusBar.makeStatusBarTransparent(this);

        company_details_return = findViewById(R.id.company_details_return);
        company_details_call = findViewById(R.id.company_details_call);
        details_change = findViewById(R.id.details_change);
        company_details_ttcall = findViewById(R.id.company_details_ttcall);
        company_details_ttv = findViewById(R.id.company_details_ttv);

        company_details_new_tv1 = findViewById(R.id.company_details_new_tv1);
        company_details_new_tv2 = findViewById(R.id.company_details_new_tv2);

        details_rg1 = findViewById(R.id.details_rg1);
        details_rg2 = findViewById(R.id.details_rg2);

        details_chart = findViewById(R.id.lc_details);
        ll1 = findViewById(R.id.ll1);
        tv1 = findViewById(R.id.tv1);

        details_ll1 = findViewById(R.id.details_ll1);
        details_ll2 = findViewById(R.id.details_ll2);
        details_ll3 = findViewById(R.id.details_ll3);
        details_ll4 = findViewById(R.id.details_ll4);
        details_ll5 = findViewById(R.id.details_ll5);
        details_ll6 = findViewById(R.id.details_ll6);
        details_ll7 = findViewById(R.id.details_ll7);

        details_tv1 = findViewById(R.id.details_tv1);
        details_tv2 = findViewById(R.id.details_tv2);
        details_tv3 = findViewById(R.id.details_tv3);
        details_tv4 = findViewById(R.id.details_tv4);
        details_tv5 = findViewById(R.id.details_tv5);
        details_tv6 = findViewById(R.id.details_tv6);
        details_tv7 = findViewById(R.id.details_tv7);
        details_tv8 = findViewById(R.id.details_tv8);

        company_details_ll1 = findViewById(R.id.company_details_ll1);
        company_details_ll2 = findViewById(R.id.company_details_ll2);
        company_details_ll3 = findViewById(R.id.company_details_ll3);
        company_details_ll4 = findViewById(R.id.company_details_ll4);
        company_details_ll5 = findViewById(R.id.company_details_ll5);

        company_details_tv1 = findViewById(R.id.company_details_tv1);
        company_details_tv2 = findViewById(R.id.company_details_tv2);
        company_details_tv3 = findViewById(R.id.company_details_tv3);
        company_details_tv4 = findViewById(R.id.company_details_tv4);
        company_details_tv5 = findViewById(R.id.company_details_tv5);
        company_details_tv8 = findViewById(R.id.company_details_tv8);
        company_details_tv9 = findViewById(R.id.company_details_tv9);
        company_details_tv10 = findViewById(R.id.company_details_tv10);
        company_details_tv11 = findViewById(R.id.company_details_tv11);
        company_details_tv12 = findViewById(R.id.company_details_tv12);

        company_details_rb1 = findViewById(R.id.company_details_rb1);
        company_details_rb2 = findViewById(R.id.company_details_rb2);
        company_details_rb3 = findViewById(R.id.company_details_rb3);
        company_details_rb4 = findViewById(R.id.company_details_rb4);
        company_details_rb5 = findViewById(R.id.company_details_rb5);
        company_details_rb6 = findViewById(R.id.company_details_rb6);
        company_details_rb7 = findViewById(R.id.company_details_rb7);
        company_details_rb8 = findViewById(R.id.company_details_rb8);

        project_attache_ll1 = findViewById(R.id.project_attache_ll1);
        project_attache_ll2 = findViewById(R.id.project_attache_ll2);
        project_attache_ll3 = findViewById(R.id.project_attache_ll3);
        project_attache_ll4 = findViewById(R.id.project_attache_ll4);

        project_attache_ll1.setOnClickListener(this);
        project_attache_ll3.setOnClickListener(this);
        company_details_return.setOnClickListener(this);
        company_details_call.setOnClickListener(this);
//        company_details_ll3.setOnClickListener(this);
//        company_details_ll4.setOnClickListener(this);
//        company_details_ll5.setOnClickListener(this);

        company_details_new_tv1.setOnClickListener(this);
        company_details_new_tv2.setOnClickListener(this);
        details_change.setOnClickListener(this);
        details_ll1.setOnClickListener(this);
        details_ll2.setOnClickListener(this);
        details_ll3.setOnClickListener(this);
        details_ll4.setOnClickListener(this);
        details_ll5.setOnClickListener(this);
        details_ll6.setOnClickListener(this);
        details_ll7.setOnClickListener(this);
        company_details_ttcall.setOnClickListener(this);

        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        string1 = String.format(Locale.getDefault(), "%d.%02d.%02d", year, month + 1, dayOfMonth - 1);
        string2 = String.format(Locale.getDefault(), "%d.%02d.%02d", year, month + 1, dayOfMonth);
        company_details_tv4.setText(string1);
        company_details_tv5.setText(string2);
        company_details_tv8.setText(string1);
        company_details_tv9.setText(string2);

        company_details_tv4.setOnClickListener(new View.OnClickListener() {
            @SingleClick(1000)
            @Override
            public void onClick(View view) {
                initTime1_Date1();
            }
        });
        company_details_tv5.setOnClickListener(new View.OnClickListener() {
            @SingleClick(1000)
            @Override
            public void onClick(View view) {
                initTime1_Date2();
            }
        });
        company_details_tv8.setOnClickListener(new View.OnClickListener() {
            @SingleClick(1000)
            @Override
            public void onClick(View view) {
                initTime2_Date1();
            }
        });
        company_details_tv9.setOnClickListener(new View.OnClickListener() {
            @SingleClick(1000)
            @Override
            public void onClick(View view) {
                initTime2_Date2();
            }
        });
        initData();

        details_rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.company_details_rb1) {
                    if (project_attache_ll2.getVisibility() == View.VISIBLE) {
                        initDataNum("0", "", "", "1");
                    } else if (project_attache_ll4.getVisibility() == View.VISIBLE) {
                        initDataNum("0", "", "", "2");
                    }
                    NewlyIncreased.setTag("0");
                    company_details_ll1.setVisibility(View.GONE);
                } else if (i == R.id.company_details_rb2) {
                    if (project_attache_ll2.getVisibility() == View.VISIBLE) {
                        initDataNum("1", "", "", "1");
                    } else if (project_attache_ll4.getVisibility() == View.VISIBLE) {
                        initDataNum("1", "", "", "2");
                    }
                    NewlyIncreased.setTag("1");
                    company_details_ll1.setVisibility(View.GONE);
                } else if (i == R.id.company_details_rb3) {
                    if (project_attache_ll2.getVisibility() == View.VISIBLE) {
                        initDataNum("2", "", "", "1");
                    } else if (project_attache_ll4.getVisibility() == View.VISIBLE) {
                        initDataNum("2", "", "", "2");
                    }
                    NewlyIncreased.setTag("2");
                    company_details_ll1.setVisibility(View.GONE);
                } else if (i == R.id.company_details_rb4) {
                    company_details_tv4.setText(string1);
                    company_details_tv5.setText(string2);
                    String s = company_details_tv4.getText().toString();
                    String s1 = company_details_tv5.getText().toString();
                    NewlyIncreased.setStartDate(s);
                    NewlyIncreased.setEndDate(s1);
                    if (project_attache_ll2.getVisibility() == View.VISIBLE) {
                        initDataNum("3", s, s1, "1");
                    } else if (project_attache_ll4.getVisibility() == View.VISIBLE) {
                        initDataNum("3", s, s1, "2");
                    }
                    NewlyIncreased.setTag("3");
                    company_details_ll1.setVisibility(View.VISIBLE);
                }
            }
        });
        details_rg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.company_details_rb5) {
                    initFinanceNum("0", "", "");
                    NewlyIncreased.setYJType("0");
                    company_details_ll2.setVisibility(View.GONE);
                } else if (i == R.id.company_details_rb6) {
                    initFinanceNum("1", "", "");
                    NewlyIncreased.setYJType("1");
                    company_details_ll2.setVisibility(View.GONE);
                } else if (i == R.id.company_details_rb7) {
                    initFinanceNum("2", "", "");
                    NewlyIncreased.setYJType("2");
                    company_details_ll2.setVisibility(View.GONE);
                } else if (i == R.id.company_details_rb8) {
                    company_details_tv8.setText(string1);
                    company_details_tv9.setText(string2);
                    String s = company_details_tv8.getText().toString();
                    String s1 = company_details_tv9.getText().toString();
                    NewlyIncreased.setYJstartDate(s);
                    NewlyIncreased.setYJendDate(s1);
                    initFinanceNum("3", s, s1);
                    NewlyIncreased.setYJType("3");
                    company_details_ll2.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    //            TODO 数据统计 时间选择 开始时间
    private void initTime1_Date1(){
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(year, month, dayOfMonth-15);
        final Calendar endDate = Calendar.getInstance();
        endDate.set(year, month, dayOfMonth+15);
        TimePickerView pvTime = new TimePickerBuilder(CompanyDetailsActivity.this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                company_details_tv4.setText(getTime2(date));
                NewlyIncreased.setStartDate(getTime2(date));
                if (project_attache_ll2.getVisibility() == View.VISIBLE) {
                    initDataNum("3", company_details_tv4.getText().toString(), company_details_tv5.getText().toString(), "1");
                } else if (project_attache_ll4.getVisibility() == View.VISIBLE) {
                    initDataNum("3", company_details_tv4.getText().toString(), company_details_tv5.getText().toString(), "2");
                }
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

    //            TODO 数据统计 时间选择 结束时间
    private void initTime1_Date2(){
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(year, month, dayOfMonth-15);
        final Calendar endDate = Calendar.getInstance();
        endDate.set(year, month, dayOfMonth+15);
        TimePickerView pvTime = new TimePickerBuilder(CompanyDetailsActivity.this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                company_details_tv5.setText(getTime2(date));
                NewlyIncreased.setEndDate(getTime2(date));
                if (project_attache_ll2.getVisibility() == View.VISIBLE) {
                    initDataNum("3", company_details_tv4.getText().toString(), company_details_tv5.getText().toString(), "1");
                } else if (project_attache_ll4.getVisibility() == View.VISIBLE) {
                    initDataNum("3", company_details_tv4.getText().toString(), company_details_tv5.getText().toString(), "2");
                }
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

    //            TODO 财务数据 时间选择 开始时间
    private void initTime2_Date1(){
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(year, month, dayOfMonth-15);
        final Calendar endDate = Calendar.getInstance();
        endDate.set(year, month, dayOfMonth+15);
        TimePickerView pvTime = new TimePickerBuilder(CompanyDetailsActivity.this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                company_details_tv8.setText(getTime2(date));
                NewlyIncreased.setYJstartDate(getTime2(date));
                initFinanceNum("3", company_details_tv8.getText().toString(), company_details_tv9.getText().toString());
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

    //            TODO 财务数据 时间选择 结束时间
    private void initTime2_Date2(){
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(year, month, dayOfMonth-15);
        final Calendar endDate = Calendar.getInstance();
        endDate.set(year, month, dayOfMonth+15);
        TimePickerView pvTime = new TimePickerBuilder(CompanyDetailsActivity.this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                company_details_tv9.setText(getTime2(date));
                NewlyIncreased.setYJendDate(getTime2(date));
                initFinanceNum("3", company_details_tv8.getText().toString(), company_details_tv9.getText().toString());
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

    private void initFinanceNum(String type, String startTime, String endTime) {

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        builder.addConverterFactory(GsonConverterFactory.create());
        Retrofit build = builder.build();
        MyService myService = build.create(MyService.class);
        Observable<FinanceBean> financeBean = myService.getFinanceBean(FinalContents.getUserID(), "", FinalContents.getStoreId(), "", type, startTime, endTime);
        financeBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FinanceBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FinanceBean financeBean) {
                        company_details_tv10.setText(financeBean.getData().getTotalAmount() + "");
                        company_details_tv11.setText(financeBean.getData().getAlreadyAmount() + "");
                        company_details_tv12.setText(financeBean.getData().getNotAmount() + "");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    private void initDataNum(String type, String startTime, String endTime, String tag) {

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        builder.addConverterFactory(GsonConverterFactory.create());
        Retrofit build = builder.build();
        MyService myService = build.create(MyService.class);
        Observable<DataNumBean> dataNum = myService.getDataNum(FinalContents.getUserID(), FinalContents.getStoreId(), "", tag, type, startTime, endTime);
        dataNum.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DataNumBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DataNumBean dataNumBean) {
                        details_tv2.setText(dataNumBean.getData().getReportNumber() + "");
                        details_tv3.setText(dataNumBean.getData().getAccessingNumber() + "");
                        details_tv4.setText(dataNumBean.getData().getIsIslandNumber() + "");
                        details_tv5.setText(dataNumBean.getData().getEarnestMoneyNumber() + "");
                        details_tv6.setText(dataNumBean.getData().getTradeNumber() + "");
                        details_tv7.setText(dataNumBean.getData().getLandingNumber() + "");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void initData() {

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        builder.addConverterFactory(GsonConverterFactory.create());
        Retrofit build = builder.build();
        MyService myService = build.create(MyService.class);
        Observable<CompanyDetailsBean> companyDetailsBean1 = myService.getCompanyDetailsBean(FinalContents.getStoreId(), FinalContents.getUserID());
        companyDetailsBean1.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CompanyDetailsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CompanyDetailsBean companyDetailsBean) {
                        storeInfo = companyDetailsBean.getData().getStoreInfo();

                        details_tv8.setText(storeInfo.getStoreName());
                        company_details_tv1.setText(storeInfo.getCompanyName() + "-" + storeInfo.getStoreName());
                        company_details_tv2.setText(storeInfo.getStoreIdCode());
                        if (storeInfo.getShopownerName().equals("")) {
                            company_details_tv3.setVisibility(View.GONE);
                            company_details_call.setVisibility(View.GONE);
                        } else {
                            company_details_tv3.setVisibility(View.VISIBLE);
                            company_details_tv3.setText("店长：" + storeInfo.getShopownerName());
                            if (storeInfo.getShopownerPhone().equals("")) {
                                company_details_call.setVisibility(View.GONE);
                            } else {
                                company_details_call.setVisibility(View.VISIBLE);
                                company_details_call.setText(storeInfo.getShopownerPhone());
                            }
                        }
                        if (storeInfo.getIsMy().equals("1")) {
                            company_details_ttv.setVisibility(View.GONE);
                            company_details_ttcall.setVisibility(View.GONE);
                        } else {
                            if (storeInfo.getAttacheName().equals("")) {
                                company_details_ttv.setVisibility(View.GONE);
                                company_details_ttcall.setVisibility(View.GONE);
                            } else {
                                company_details_ttv.setVisibility(View.VISIBLE);
                                company_details_ttcall.setVisibility(View.VISIBLE);
                                if (storeInfo.getAttacheIdentity().equals("5")) {
                                    company_details_ttv.setText("负责专员:" + storeInfo.getAttacheName());
                                } else if (storeInfo.getAttacheIdentity().equals("8")) {
                                    company_details_ttv.setText("负责经理:" + storeInfo.getAttacheName());
                                } else if (storeInfo.getAttacheIdentity().equals("9")) {
                                    company_details_ttv.setText("负责总监:" + storeInfo.getAttacheName());
                                }
                                company_details_ttcall.setText(storeInfo.getAttachePhone());
                            }
                        }
//                        TODO 数据统计
                        CompanyDetailsBean.DataBean.StoreDataStatisticsBean storeDataStatistics = companyDetailsBean.getData().getStoreDataStatistics();
                        details_tv1.setText(storeDataStatistics.getAgentNum() + "");
                        details_tv2.setText(storeDataStatistics.getReportNumber() + "");
                        details_tv3.setText(storeDataStatistics.getAccessingNumber() + "");
                        details_tv4.setText(storeDataStatistics.getIsIslandNumber() + "");
                        details_tv5.setText(storeDataStatistics.getEarnestMoneyNumber() + "");
                        details_tv6.setText(storeDataStatistics.getTradeNumber() + "");
                        details_tv7.setText(storeDataStatistics.getInvalidNum() + "");

                        FinalContents.setStoreId(companyDetailsBean.getData().getStoreInfo().getStoreId());

//                        TODO 近七天活动度
                        integers = companyDetailsBean.getData().getGsonOption().getSeries().get(0).getData();
                        indexList = companyDetailsBean.getData().getGsonOption().getXAxis().getData();
                        init(integers);

//                        TODO 佣金
                        CompanyDetailsBean.DataBean.StoreMoneyDataBean storeMoneyData = companyDetailsBean.getData().getStoreMoneyData();
                        company_details_tv10.setText(storeMoneyData.getTotalAmount() + "");
                        company_details_tv11.setText(storeMoneyData.getAlreadyAmount() + "");
                        company_details_tv12.setText(storeMoneyData.getNotAmount() + "");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });


//        String url = FinalContents.getBaseUrl() + "commissionerSelect/storeDetails?storeId=" + FinalContents.getStoreId() + "&userId=" + FinalContents.getUserID();
//
//        Log.i("MyCL", "FinalContents.getStoreId()：" + FinalContents.getStoreId());
//
//        OkHttpPost okHttpPost = new OkHttpPost(url);
//        String post = okHttpPost.post();
//
//        Gson gson = new Gson();
//        CompanyDetailsBean companyDetailsBean = gson.fromJson(post, CompanyDetailsBean.class);
////                        TODO 头部信息


    }

    @SingleClick(1000)
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.company_details_return:
                FinalContents.setMyAddType("门店");
                finish();
                break;
            case R.id.company_details_call:
                if (storeInfo.getShopownerPhone().equals("")) {
                    ToastUtil.showLongToast(CompanyDetailsActivity.this, "暂无电话信息，无法拨打");
                } else {
                    Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + storeInfo.getShopownerPhone()));//跳转到拨号界面，同时传递电话号码
                    startActivity(dialIntent);
                }
                break;
            case R.id.company_details_ll3:
                intent = new Intent(CompanyDetailsActivity.this, CommissionActivity.class);
                FinalContents.setCompanyId("");
                FinalContents.setStoreId(storeInfo.getStoreId());
                FinalContents.setAgentId("");
                startActivity(intent);
                break;
            case R.id.company_details_ll4:
                FinalContents.setCompanyId("");
                FinalContents.setStoreId(storeInfo.getStoreId());
                FinalContents.setAgentId("");
                intent = new Intent(CompanyDetailsActivity.this, CommissionActivity.class);
                startActivity(intent);
                break;
            case R.id.company_details_ll5:
                FinalContents.setCompanyId("");
                FinalContents.setStoreId(storeInfo.getStoreId());
                FinalContents.setAgentId("");
                intent = new Intent(CompanyDetailsActivity.this, CommissionActivity.class);
                startActivity(intent);
                break;
            case R.id.details_ll1:
                intent = new Intent(CompanyDetailsActivity.this, BrokersListActivity.class);
                FinalContents.setStoreId(storeInfo.getStoreId());
                startActivity(intent);
                break;
            case R.id.details_ll2:
                intent = new Intent(CompanyDetailsActivity.this, MyClientActivity.class);
                FinalContents.setStoreId(storeInfo.getStoreId());
                intent.putExtra("client", "1");
                startActivity(intent);
                break;
            case R.id.details_ll3:
                intent = new Intent(CompanyDetailsActivity.this, MyClientActivity.class);
                FinalContents.setStoreId(storeInfo.getStoreId());
                intent.putExtra("client", "2");
                startActivity(intent);
                break;
            case R.id.details_ll4:
                intent = new Intent(CompanyDetailsActivity.this, MyClientActivity.class);
                FinalContents.setStoreId(storeInfo.getStoreId());
                intent.putExtra("client", "3");
                startActivity(intent);
                break;
            case R.id.details_ll5:
                intent = new Intent(CompanyDetailsActivity.this, MyClientActivity.class);
                FinalContents.setStoreId(storeInfo.getStoreId());
                intent.putExtra("client", "4");
                startActivity(intent);
                break;
            case R.id.details_ll6:
                intent = new Intent(CompanyDetailsActivity.this, MyClientActivity.class);
                FinalContents.setStoreId(storeInfo.getStoreId());
                intent.putExtra("client", "5");
                startActivity(intent);
                break;
            case R.id.details_ll7:
                intent = new Intent(CompanyDetailsActivity.this, MyClientActivity.class);
                FinalContents.setStoreId(storeInfo.getStoreId());
                intent.putExtra("client", "6");
                startActivity(intent);
                break;
            case R.id.details_change:
                finish();
                intent = new Intent(CompanyDetailsActivity.this, AddStoreActivity.class);
                FinalContents.setStoreId(storeInfo.getStoreId());
                FinalContents.setStoreChange("修改");
                startActivity(intent);
                finish();
                break;
            case R.id.company_details_ttcall:
                Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + storeInfo.getAttachePhone()));//跳转到拨号界面，同时传递电话号码
                startActivity(callIntent);
                break;
            case R.id.company_details_new_tv1://电话拜访
                if (storeInfo.getShopownerPhone().equals("")) {
                    ToastUtil.showLongToast(CompanyDetailsActivity.this, "暂无电话信息，无法拨打");
                } else {
                    Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + storeInfo.getShopownerPhone()));//跳转到拨号界面，同时传递电话号码
                    startActivity(dialIntent);
                }
                break;
            case R.id.company_details_new_tv2://门店打卡
                try {
                    if (storeInfo.getLocation().equals("")) {
                        ToastUtil.showLongToast(CompanyDetailsActivity.this, "门店暂不支持打卡");
                    } else {
                        Intent intent = new Intent(CompanyDetailsActivity.this, ClockStoresActivity.class);
                        intent.putExtra("MyStoreRise", storeInfo.getStoreRise());
                        intent.putExtra("MyStoreName", storeInfo.getStoreName());
                        intent.putExtra("MyLocation", storeInfo.getLocation());
                        intent.putExtra("MyStoreId", storeInfo.getStoreId());
                        startActivity(intent);
                    }
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            case R.id.project_attache_ll1://实时
                project_attache_ll2.setVisibility(View.VISIBLE);
                project_attache_ll4.setVisibility(View.INVISIBLE);
                details_ll2.setClickable(true);
                details_ll3.setClickable(true);
                details_ll4.setClickable(true);
                details_ll5.setClickable(true);
                details_ll6.setClickable(true);
                details_ll7.setClickable(true);
                if (company_details_rb1.isChecked() == true) {
                    initDataNum("0", "", "", "1");
                    NewlyIncreased.setTag("0");
                    company_details_ll1.setVisibility(View.GONE);
                } else if (company_details_rb2.isChecked() == true) {
                    initDataNum("1", "", "", "1");
                    NewlyIncreased.setTag("1");
                    company_details_ll1.setVisibility(View.GONE);
                } else if (company_details_rb3.isChecked() == true) {
                    initDataNum("2", "", "", "1");
                    NewlyIncreased.setTag("2");
                    company_details_ll1.setVisibility(View.GONE);
                } else if (company_details_rb4.isChecked() == true) {
                    String s = company_details_tv4.getText().toString();
                    String s1 = company_details_tv5.getText().toString();
                    NewlyIncreased.setStartDate(s);
                    NewlyIncreased.setEndDate(s1);
                    initDataNum("3", s, s1, "1");
                    NewlyIncreased.setTag("3");
                    company_details_ll1.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.project_attache_ll3://总体
                project_attache_ll2.setVisibility(View.INVISIBLE);
                project_attache_ll4.setVisibility(View.VISIBLE);
                details_ll2.setClickable(false);
                details_ll3.setClickable(false);
                details_ll4.setClickable(false);
                details_ll5.setClickable(false);
                details_ll6.setClickable(false);
                details_ll7.setClickable(false);
                if (company_details_rb1.isChecked() == true) {
                    initDataNum("0", "", "", "2");
                    NewlyIncreased.setTag("0");
                    company_details_ll1.setVisibility(View.GONE);
                } else if (company_details_rb2.isChecked() == true) {
                    initDataNum("1", "", "", "2");
                    NewlyIncreased.setTag("1");
                    company_details_ll1.setVisibility(View.GONE);
                } else if (company_details_rb3.isChecked() == true) {
                    initDataNum("2", "", "", "2");
                    NewlyIncreased.setTag("2");
                    company_details_ll1.setVisibility(View.GONE);
                } else if (company_details_rb4.isChecked() == true) {
                    String s = company_details_tv4.getText().toString();
                    String s1 = company_details_tv5.getText().toString();
                    NewlyIncreased.setStartDate(s);
                    NewlyIncreased.setEndDate(s1);
                    initDataNum("3", s, s1, "2");
                    NewlyIncreased.setTag("3");
                    company_details_ll1.setVisibility(View.VISIBLE);
                }
                break;
        }

    }


    //TODO 详情页趋势图绘制
    private void init(final List<Integer> list) {

        //显示边界
        details_chart.setDrawBorders(false);
        //无数据时显示的文字
        details_chart.setNoDataText("暂无数据");
        //折线图不显示数值
//        data.setDrawValues(false);
        //得到X轴
        XAxis xAxis = details_chart.getXAxis();
        //设置X轴的位置（默认在上方)
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //设置X轴坐标之间的最小间隔
        xAxis.setGranularity(0f);
        //设置X轴的刻度数量，第二个参数为true,将会画出明确数量（带有小数点），但是可能值导致不均匀，默认（6，false）
        xAxis.setLabelCount(indexList.size(), false);
        //设置X轴的值（最小值、最大值、然后会根据设置的刻度数量自动分配刻度显示）
        xAxis.setAxisMinimum(0f);
        xAxis.setAxisMaximum((float) list.size());
        //不显示网格线
        xAxis.setDrawGridLines(false);
        // 标签倾斜
        xAxis.setLabelRotationAngle(45);
        //设置X轴值为字符串
        xAxis.setValueFormatter(new IndexAxisValueFormatter(indexList));
        //得到Y轴
        YAxis yAxis = details_chart.getAxisLeft();
        YAxis rightYAxis = details_chart.getAxisRight();
        //设置Y轴是否显示
        rightYAxis.setEnabled(false); //右侧Y轴不显示
//        yAxis.setEnabled(false);
        //设置y轴坐标之间的最小间隔
        //不显示网格线
        yAxis.setDrawGridLines(false);
//        //设置Y轴坐标之间的最小间隔
//        yAxis.setGranularity(1);
        //设置y轴的刻度数量
        //+2：最大值n就有n+1个刻度，在加上y轴多一个单位长度，为了好看，so+2
        yAxis.setLabelCount(Collections.max(list) + 2, false);
        //设置从Y轴值
        yAxis.setAxisMinimum(0f);
        //+1:y轴多一个单位长度，为了好看
        yAxis.setAxisMaximum(Collections.max(list) + 1);
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

        if (details_chart.getData() != null &&
                details_chart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) details_chart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            details_chart.getData().notifyDataChanged();
            details_chart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(values, "DataSet");

            set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
            set1.setCubicIntensity(0.2f);
            set1.setDrawFilled(true);
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
            Drawable drawable = getResources().getDrawable(R.drawable.line_back);
            set1.setFillDrawable(drawable);
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
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NewlyIncreased.setTag("");
        NewlyIncreased.setStartDate("");
        NewlyIncreased.setEndDate("");
        NewlyIncreased.setYJType("");
        NewlyIncreased.setYJstartDate("");
        NewlyIncreased.setYJendDate("");
    }
}
