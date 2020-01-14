package com.xcy.fzbcity.project_attache.view;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
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
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.api.NewlyIncreased;
import com.xcy.fzbcity.all.database.BrokerBean;
import com.xcy.fzbcity.all.database.DataNumBean;
import com.xcy.fzbcity.all.database.FinanceBean;
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

public class BrokerActivity extends AllActivity implements View.OnClickListener {

    TextView broker_tv1;
    TextView broker_tv2;
    TextView broker_tv4;
    TextView broker_tv5;
    TextView broker_tv6;
    TextView broker_tv7;
    TextView broker_tv8;
    TextView broker_tv9;
    TextView broker_tv10;
    TextView broker_tv11;
    TextView broker_tv13;
    TextView broker_tv14;
    TextView broker_tv15;
    TextView broker_tv16;
    TextView broker_tv17;
    TextView broker_tv18;
    TextView broker_call;

    LinearLayout broker_ll1;
    LinearLayout broker_ll2;
    LinearLayout broker_ll3;
    LinearLayout broker_ll4;
    LinearLayout broker_ll5;
    LinearLayout broker_ll6;
    LinearLayout broker_ll7;
    LinearLayout broker_ll9;
    LinearLayout broker_ll10;
    LinearLayout broker_ll11;
    LinearLayout broker_ll12;

    LinearLayout project_attache_broker_ll1;
    LinearLayout project_attache_broker_ll2;
    LinearLayout project_attache_broker_ll3;
    LinearLayout project_attache_broker_ll4;

    RelativeLayout broker_return;
    ImageView broker_change;

    RadioGroup broker_rg1;
    RadioGroup broker_rg2;

    RadioButton broker_rb1;
    RadioButton broker_rb2;
    RadioButton broker_rb3;
    RadioButton broker_rb4;
    RadioButton broker_rb5;
    RadioButton broker_rb6;
    RadioButton broker_rb7;
    RadioButton broker_rb8;

    RelativeLayout broker_rl;
    TextView broker_tv;

    private CombinedChart combinedChart;
    private List<Integer> integers;
    private List<String> indexList;
    private Intent intent;

    private BrokerBean.DataBean.AgentInfoBean agentInfo;
    private String string1;
    private String string2;
    private int year;
    private int month;
    private int dayOfMonth;

    public static BrokerActivity brokerActivity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_attache_activity_broker);

        brokerActivity = this;

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
            ToastUtil.showLongToast(BrokerActivity.this, "当前无网络，请检查网络后再进行登录");
        }
    }

    private void initView() {
        Log.i("销毁", "数1：" + FinalContents.getStoreId());
        StatusBar.makeStatusBarTransparent(this);

        broker_rb1 = findViewById(R.id.broker_rb1);
        broker_rb2 = findViewById(R.id.broker_rb2);
        broker_rb3 = findViewById(R.id.broker_rb3);
        broker_rb4 = findViewById(R.id.broker_rb4);
        broker_rb5 = findViewById(R.id.broker_rb5);
        broker_rb6 = findViewById(R.id.broker_rb6);
        broker_rb7 = findViewById(R.id.broker_rb7);
        broker_rb8 = findViewById(R.id.broker_rb8);
        broker_rl = findViewById(R.id.broker_rl);
        broker_tv = findViewById(R.id.broker_tv);

        project_attache_broker_ll1 = findViewById(R.id.project_attache_broker_ll1);
        project_attache_broker_ll2 = findViewById(R.id.project_attache_broker_ll2);
        project_attache_broker_ll3 = findViewById(R.id.project_attache_broker_ll3);
        project_attache_broker_ll4 = findViewById(R.id.project_attache_broker_ll4);

        broker_rg1 = findViewById(R.id.broker_rg1);
        broker_rg2 = findViewById(R.id.broker_rg2);

        broker_return = findViewById(R.id.broker_return);
        broker_change = findViewById(R.id.broker_change);

        broker_ll1 = findViewById(R.id.broker_ll1);
        broker_ll2 = findViewById(R.id.broker_ll2);
        broker_ll3 = findViewById(R.id.broker_ll3);
        broker_ll4 = findViewById(R.id.broker_ll4);
        broker_ll5 = findViewById(R.id.broker_ll5);
        broker_ll6 = findViewById(R.id.broker_ll6);
        broker_ll7 = findViewById(R.id.broker_ll7);
        broker_ll9 = findViewById(R.id.broker_ll9);
        broker_ll10 = findViewById(R.id.broker_ll10);
        broker_ll11 = findViewById(R.id.broker_ll11);
        broker_ll12 = findViewById(R.id.broker_ll12);

        broker_tv1 = findViewById(R.id.broker_tv1);
        broker_tv2 = findViewById(R.id.broker_tv2);
        broker_tv4 = findViewById(R.id.broker_tv4);
        broker_tv5 = findViewById(R.id.broker_tv5);
        broker_tv6 = findViewById(R.id.broker_tv6);
        broker_tv7 = findViewById(R.id.broker_tv7);
        broker_tv8 = findViewById(R.id.broker_tv8);
        broker_tv9 = findViewById(R.id.broker_tv9);
        broker_tv10 = findViewById(R.id.broker_tv10);
        broker_tv11 = findViewById(R.id.broker_tv11);
        broker_tv13 = findViewById(R.id.broker_tv13);
        broker_tv14 = findViewById(R.id.broker_tv14);
        broker_tv15 = findViewById(R.id.broker_tv15);
        broker_tv16 = findViewById(R.id.broker_tv16);
        broker_tv17 = findViewById(R.id.broker_tv17);
        broker_tv18 = findViewById(R.id.broker_tv18);
        broker_call = findViewById(R.id.broker_call);

        combinedChart = findViewById(R.id.lc_broker);

        project_attache_broker_ll1.setOnClickListener(this);
        project_attache_broker_ll3.setOnClickListener(this);

        broker_return.setOnClickListener(this);
        broker_change.setOnClickListener(this);
        broker_ll2.setOnClickListener(this);
        broker_ll3.setOnClickListener(this);
        broker_ll4.setOnClickListener(this);
        broker_ll5.setOnClickListener(this);
        broker_ll6.setOnClickListener(this);
        broker_ll7.setOnClickListener(this);
//        broker_ll10.setOnClickListener(this);
//        broker_ll11.setOnClickListener(this);
//        broker_ll12.setOnClickListener(this);

        initData();

        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        string1 = String.format(Locale.getDefault(), "%d.%02d.%02d", year, month + 1, dayOfMonth);
        string2 = String.format(Locale.getDefault(), "%d.%02d.%02d", year, month + 1, dayOfMonth);
        broker_tv4.setText(string1);
        broker_tv5.setText(string2);
        broker_tv13.setText(string1);
        broker_tv14.setText(string2);

        broker_tv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initTime1_Date1();
            }
        });
        broker_tv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initTime1_Date2();
            }
        });
        broker_tv13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initTime2_Date1();
            }
        });
        broker_tv14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initTime2_Date2();
            }
        });

        broker_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (agentInfo.getAgentPhone().equals("")) {
                    ToastUtil.showLongToast(BrokerActivity.this, "暂无电话信息，无法拨打");
                } else {
                    Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + agentInfo.getAgentPhone()));//跳转到拨号界面，同时传递电话号码
                    startActivity(dialIntent);
                }

            }
        });

        broker_rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.broker_rb1) {
                    if (project_attache_broker_ll2.getVisibility() == View.VISIBLE) {
                        initDataNum("0", "", "", "1");
                    } else if (project_attache_broker_ll4.getVisibility() == View.VISIBLE) {
                        initDataNum("0", "", "", "2");
                    }
                    NewlyIncreased.setTag("0");
                    broker_ll1.setVisibility(View.GONE);
                } else if (i == R.id.broker_rb2) {
                    if (project_attache_broker_ll2.getVisibility() == View.VISIBLE) {
                        initDataNum("1", "", "", "1");
                    } else if (project_attache_broker_ll4.getVisibility() == View.VISIBLE) {
                        initDataNum("1", "", "", "2");
                    }

                    NewlyIncreased.setTag("1");
                    broker_ll1.setVisibility(View.GONE);
                } else if (i == R.id.broker_rb3) {
                    if (project_attache_broker_ll2.getVisibility() == View.VISIBLE) {
                        initDataNum("2", "", "", "1");
                    } else if (project_attache_broker_ll4.getVisibility() == View.VISIBLE) {
                        initDataNum("2", "", "", "2");
                    }

                    NewlyIncreased.setTag("2");
                    broker_ll1.setVisibility(View.GONE);
                } else if (i == R.id.broker_rb4) {
                    broker_tv4.setText(string1);
                    broker_tv5.setText(string2);
                    String s = broker_tv4.getText().toString();
                    String s1 = broker_tv5.getText().toString();
                    NewlyIncreased.setStartDate(s);
                    NewlyIncreased.setEndDate(s1);
                    if (project_attache_broker_ll2.getVisibility() == View.VISIBLE) {
                        initDataNum("3", s, s1, "1");
                    } else if (project_attache_broker_ll4.getVisibility() == View.VISIBLE) {
                        initDataNum("3", s, s1, "2");
                    }
                    NewlyIncreased.setTag("3");
                    broker_ll1.setVisibility(View.VISIBLE);
                }
            }
        });
        broker_rg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.broker_rb5) {
                    initFinanceNum("0", "", "");
                    NewlyIncreased.setYJType("0");
                    broker_ll9.setVisibility(View.GONE);
                } else if (i == R.id.broker_rb6) {
                    initFinanceNum("1", "", "");
                    NewlyIncreased.setYJType("1");
                    broker_ll9.setVisibility(View.GONE);
                } else if (i == R.id.broker_rb7) {
                    initFinanceNum("2", "", "");
                    NewlyIncreased.setYJType("2");
                    broker_ll9.setVisibility(View.GONE);
                } else if (i == R.id.broker_rb8) {
                    broker_tv13.setText(string1);
                    broker_tv14.setText(string2);
                    String s = broker_tv13.getText().toString();
                    String s1 = broker_tv14.getText().toString();
                    NewlyIncreased.setYJstartDate(s);
                    NewlyIncreased.setYJendDate(s1);
                    NewlyIncreased.setYJType("3");
                    initFinanceNum("3", s, s1);
                    broker_ll9.setVisibility(View.VISIBLE);
                }
            }
        });

//        broker_tv1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (agentInfo.getAgentPhone().equals("")) {
//                    ToastUtil.showToast(BrokerActivity.this, "暂无电话");
//                } else {
//                    Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + agentInfo.getAgentPhone()));//跳转到拨号界面，同时传递电话号码
//                    startActivity(dialIntent);
//                }
//            }
//        });

    }

    //            TODO 数据统计 时间选择 开始时间
    private void initTime1_Date1() {
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(year - 3, month, dayOfMonth);
        Calendar endDate = Calendar.getInstance();
        TimePickerView pvTime = new TimePickerBuilder(BrokerActivity.this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                broker_tv4.setText(getTime2(date));
                NewlyIncreased.setStartDate(getTime2(date));
//                if (project_attache_broker_ll2. getVisibility() == View.VISIBLE) {
//                    initDataNum("3", broker_tv4.getText().toString(), broker_tv5.getText().toString(), "1");
//                } else if (project_attache_broker_ll4.getVisibility() == View.VISIBLE) {
//                    initDataNum("3", broker_tv4.getText().toString(), broker_tv5.getText().toString(), "2");
//                }
            }
        })
                .setType(new boolean[]{true, true, true, false, false, false}) //年月日时分秒 的显示与否，不设置则默认全部显示
                .setLabel("年", "月", "日", "", "", "")//默认设置为年月日时分秒
                .isCenterLabel(false)
                .setDate(selectedDate)
                .setLineSpacingMultiplier(1.5f)
                .setTextXOffset(-10, 0, 10, 0, 0, 0)//设置X轴倾斜角度[ -90 , 90°]
                .setRangDate(startDate, endDate)
                .build();
        pvTime.show();
    }

    //            TODO 数据统计 时间选择 结束时间
    private void initTime1_Date2() {
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(year - 3, month, dayOfMonth);
        Calendar endDate = Calendar.getInstance();
        TimePickerView pvTime = new TimePickerBuilder(BrokerActivity.this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                broker_tv5.setText(getTime2(date));
                NewlyIncreased.setEndDate(getTime2(date));
                if (project_attache_broker_ll2.getVisibility() == View.VISIBLE) {
                    initDataNum("3", broker_tv4.getText().toString(), broker_tv5.getText().toString(), "1");
                } else if (project_attache_broker_ll4.getVisibility() == View.VISIBLE) {
                    initDataNum("3", broker_tv4.getText().toString(), broker_tv5.getText().toString(), "2");
                }
            }
        })
                .setType(new boolean[]{true, true, true, false, false, false}) //年月日时分秒 的显示与否，不设置则默认全部显示
                .setLabel("年", "月", "日", "", "", "")//默认设置为年月日时分秒
                .isCenterLabel(false)
                .setDate(selectedDate)
                .setLineSpacingMultiplier(1.5f)
                .setTextXOffset(-10, 0, 10, 0, 0, 0)//设置X轴倾斜角度[ -90 , 90°]
                .setRangDate(startDate, endDate)
                .build();
        pvTime.show();
    }

    //            TODO 财务数据 时间选择 开始时间
    private void initTime2_Date1() {
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(year - 3, month, dayOfMonth);
        Calendar endDate = Calendar.getInstance();
        TimePickerView pvTime = new TimePickerBuilder(BrokerActivity.this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                broker_tv13.setText(getTime2(date));
                NewlyIncreased.setYJstartDate(getTime2(date));
//                initFinanceNum("3", broker_tv13.getText().toString(), broker_tv14.getText().toString());
            }
        })
                .setType(new boolean[]{true, true, true, false, false, false}) //年月日时分秒 的显示与否，不设置则默认全部显示
                .setLabel("年", "月", "日", "", "", "")//默认设置为年月日时分秒
                .isCenterLabel(false)
                .setDate(selectedDate)
                .setLineSpacingMultiplier(1.5f)
                .setTextXOffset(-10, 0, 10, 0, 0, 0)//设置X轴倾斜角度[ -90 , 90°]
                .setRangDate(startDate, endDate)
                .build();
        pvTime.show();
    }

    //            TODO 财务数据 时间选择 结束时间
    private void initTime2_Date2() {
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(year - 3, month, dayOfMonth);
        Calendar endDate = Calendar.getInstance();
        TimePickerView pvTime = new TimePickerBuilder(BrokerActivity.this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                broker_tv14.setText(getTime2(date));
                NewlyIncreased.setYJendDate(getTime2(date));
                initFinanceNum("3", broker_tv13.getText().toString(), broker_tv14.getText().toString());
            }
        })
                .setType(new boolean[]{true, true, true, false, false, false}) //年月日时分秒 的显示与否，不设置则默认全部显示
                .setLabel("年", "月", "日", "", "", "")//默认设置为年月日时分秒
                .isCenterLabel(false)
                .setDate(selectedDate)
                .setLineSpacingMultiplier(1.5f)
                .setTextXOffset(-10, 0, 10, 0, 0, 0)//设置X轴倾斜角度[ -90 , 90°]
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
        Observable<FinanceBean> financeBean = myService.getFinanceBean(FinalContents.getUserID(), "", "", FinalContents.getAgentId(), type, startTime, endTime);
        financeBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FinanceBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FinanceBean financeBean) {
                        broker_tv15.setText(financeBean.getData().getTotalAmount() + "");
                        broker_tv16.setText(financeBean.getData().getAlreadyAmount() + "");
                        broker_tv17.setText(financeBean.getData().getNotAmount() + "");
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
        Observable<DataNumBean> dataNum = myService.getDataNum(FinalContents.getUserID(), "", FinalContents.getAgentId(), tag, type, startTime, endTime);
        dataNum.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DataNumBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DataNumBean dataNumBean) {
                        broker_tv6.setText(dataNumBean.getData().getReportNumber() + "");
                        broker_tv7.setText(dataNumBean.getData().getAccessingNumber() + "");
                        broker_tv8.setText(dataNumBean.getData().getIsIslandNumber() + "");
                        broker_tv9.setText(dataNumBean.getData().getEarnestMoneyNumber() + "");
                        broker_tv10.setText(dataNumBean.getData().getTradeNumber() + "");
                        broker_tv11.setText(dataNumBean.getData().getInvalidNum() + "");

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
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<BrokerBean> clientCommissions = fzbInterface.getEAgentDetails(FinalContents.getAgentId(), FinalContents.getUserID());
        clientCommissions.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BrokerBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BrokerBean brokerBean) {
                        //        TODO 基本信息
                        agentInfo = brokerBean.getData().getAgentInfo();
                        if (agentInfo.getStatus().equals("0") || agentInfo.getStatus().equals("1")) {
                            broker_rl.setVisibility(View.GONE);
                        } else if (agentInfo.getStatus().equals("2")) {
                            broker_rl.setVisibility(View.VISIBLE);
                            if (agentInfo.getReason().equals("")) {
                                broker_tv.setText("异常原因：公司取消合作");
                            } else {
                                broker_tv.setText("异常原因：" + agentInfo.getReason());
                            }
                        } else if (agentInfo.getStatus().equals("3")) {
                            broker_rl.setVisibility(View.VISIBLE);
                            if (agentInfo.getReason().equals("")) {
                                broker_tv.setText("异常原因：公司倒闭");
                            } else {
                                broker_tv.setText("异常原因：" + agentInfo.getReason());
                            }
                        }
                        broker_tv18.setText(agentInfo.getAgentName());
                        broker_tv1.setText(agentInfo.getAgentName());
                        broker_call.setText(agentInfo.getAgentPhone());
                        /**
                         * 修改 20191023
                         */
                        if (agentInfo.getStoreName().equals("")) {
                            broker_tv2.setText(agentInfo.getCompanyName());
                        } else {
                            broker_tv2.setText(agentInfo.getCompanyName() + "-" + agentInfo.getStoreName());
                        }

//        TODO 数据统计
                        BrokerBean.DataBean.AgentDataStatisticsBean agentDataStatistics = brokerBean.getData().getAgentDataStatistics();
                        broker_tv6.setText(agentDataStatistics.getReportNumber() + "");
                        broker_tv7.setText(agentDataStatistics.getAccessingNumber() + "");
                        broker_tv8.setText(agentDataStatistics.getIsIslandNumber() + "");
                        broker_tv9.setText(agentDataStatistics.getEarnestMoneyNumber() + "");
                        broker_tv10.setText(agentDataStatistics.getTradeNumber() + "");
                        broker_tv11.setText(agentDataStatistics.getInvalidNum() + "");
//        TODO 近七天活动度
                        BrokerBean.DataBean.GsonOptionBean gsonOption = brokerBean.getData().getGsonOption();
                        integers = brokerBean.getData().getGsonOption().getSeries().get(0).getData();
                        indexList = brokerBean.getData().getGsonOption().getXAxis().getData();
                        if (integers.size() != 0) {
                            setData(integers);
                        }
//        TODO 佣金
                        BrokerBean.DataBean.AgentMoneyDataBean agentMoneyData = brokerBean.getData().getAgentMoneyData();
                        broker_tv15.setText(agentMoneyData.getTotalAmount() + "");
                        broker_tv16.setText(agentMoneyData.getAlreadyAmount() + "");
                        broker_tv17.setText(agentMoneyData.getNotAmount() + "");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "经纪人列表:" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    @SingleClick(1000)
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.broker_return:
                finish();
                FinalContents.setStoreId("");
                break;
            case R.id.broker_ll2:
                intent = new Intent(BrokerActivity.this, MyClientActivity.class);
                FinalContents.setStoreId(agentInfo.getStoreId());
                FinalContents.setAgentId(agentInfo.getAgentId());
                intent.putExtra("client", "1");
                startActivity(intent);
                break;
            case R.id.broker_ll3:
                intent = new Intent(BrokerActivity.this, MyClientActivity.class);
                FinalContents.setStoreId(agentInfo.getStoreId());
                FinalContents.setAgentId(agentInfo.getAgentId());
                intent.putExtra("client", "2");
                startActivity(intent);
                break;
            case R.id.broker_ll4:
                intent = new Intent(BrokerActivity.this, MyClientActivity.class);
                FinalContents.setStoreId(agentInfo.getStoreId());
                FinalContents.setAgentId(agentInfo.getAgentId());
                intent.putExtra("client", "3");
                startActivity(intent);
                break;
            case R.id.broker_ll5:
                intent = new Intent(BrokerActivity.this, MyClientActivity.class);
                FinalContents.setStoreId(agentInfo.getStoreId());
                FinalContents.setAgentId(agentInfo.getAgentId());
                intent.putExtra("client", "4");
                startActivity(intent);
                break;
            case R.id.broker_ll6:
                intent = new Intent(BrokerActivity.this, MyClientActivity.class);
                FinalContents.setStoreId(agentInfo.getStoreId());
                FinalContents.setAgentId(agentInfo.getAgentId());
                intent.putExtra("client", "5");
                startActivity(intent);
                break;
            case R.id.broker_ll7:
                intent = new Intent(BrokerActivity.this, MyClientActivity.class);
                FinalContents.setStoreId(agentInfo.getStoreId());
                FinalContents.setAgentId(agentInfo.getAgentId());
                intent.putExtra("client", "6");
                startActivity(intent);
                break;
            case R.id.broker_ll10:
                intent = new Intent(BrokerActivity.this, CommissionActivity.class);
                FinalContents.setCompanyId("");
                FinalContents.setStoreId("");
                FinalContents.setAgentId(agentInfo.getAgentId());
                startActivity(intent);
                break;
            case R.id.broker_ll11:
                intent = new Intent(BrokerActivity.this, CommissionActivity.class);
                FinalContents.setCompanyId("");
                FinalContents.setStoreId("");
                FinalContents.setAgentId(agentInfo.getAgentId());
                startActivity(intent);
                break;
            case R.id.broker_ll12:
                FinalContents.setCompanyId("");
                FinalContents.setStoreId("");
                FinalContents.setAgentId(agentInfo.getAgentId());
                intent = new Intent(BrokerActivity.this, CommissionActivity.class);
                startActivity(intent);
                break;
            case R.id.broker_change:
//                finish();
                intent = new Intent(BrokerActivity.this, AddBrokerActivity.class);
                FinalContents.setAgentId(agentInfo.getAgentId());
                FinalContents.setBorkerChange("修改");
                startActivity(intent);
                break;
            case R.id.project_attache_broker_ll1:
                project_attache_broker_ll2.setVisibility(View.VISIBLE);
                project_attache_broker_ll4.setVisibility(View.INVISIBLE);
                broker_ll2.setClickable(true);
                broker_ll3.setClickable(true);
                broker_ll4.setClickable(true);
                broker_ll5.setClickable(true);
                broker_ll6.setClickable(true);
                broker_ll7.setClickable(true);
//                if (broker_ll1.getVisibility() == View.VISIBLE) {
                if (broker_rb1.isChecked() == true) {
                    initDataNum("0", "", "", "1");
                    NewlyIncreased.setTag("0");
                    broker_ll1.setVisibility(View.GONE);
                } else if (broker_rb2.isChecked() == true) {
                    initDataNum("1", "", "", "1");
                    NewlyIncreased.setTag("1");
                    broker_ll1.setVisibility(View.GONE);
                } else if (broker_rb3.isChecked() == true) {
                    initDataNum("2", "", "", "1");
                    NewlyIncreased.setTag("2");
                    broker_ll1.setVisibility(View.GONE);
                } else if (broker_rb4.isChecked() == true) {
                    String s = broker_tv4.getText().toString();
                    String s1 = broker_tv4.getText().toString();
                    NewlyIncreased.setStartDate(s);
                    NewlyIncreased.setEndDate(s1);
                    initDataNum("3", s, s1, "1");
                    NewlyIncreased.setTag("3");
                    broker_ll1.setVisibility(View.VISIBLE);
                }
//                } else {
//                    initDataNum("", "", "", "1");
//                }
                break;
            case R.id.project_attache_broker_ll3:
                project_attache_broker_ll2.setVisibility(View.INVISIBLE);
                project_attache_broker_ll4.setVisibility(View.VISIBLE);
                broker_ll2.setClickable(false);
                broker_ll3.setClickable(false);
                broker_ll4.setClickable(false);
                broker_ll5.setClickable(false);
                broker_ll6.setClickable(false);
                broker_ll7.setClickable(false);
//                if (broker_ll1.getVisibility() == View.VISIBLE) {
                if (broker_rb1.isChecked() == true) {
                    initDataNum("0", "", "", "2");
                    NewlyIncreased.setTag("0");
                    broker_ll1.setVisibility(View.GONE);
                } else if (broker_rb2.isChecked() == true) {
                    initDataNum("1", "", "", "2");
                    NewlyIncreased.setTag("1");
                    broker_ll1.setVisibility(View.GONE);
                } else if (broker_rb3.isChecked() == true) {
                    initDataNum("2", "", "", "2");
                    NewlyIncreased.setTag("2");
                    broker_ll1.setVisibility(View.GONE);
                } else if (broker_rb4.isChecked() == true) {
                    String s = broker_tv4.getText().toString();
                    String s1 = broker_tv4.getText().toString();
                    NewlyIncreased.setStartDate(s);
                    NewlyIncreased.setEndDate(s1);
                    initDataNum("3", s, s1, "2");
                    NewlyIncreased.setTag("3");
                    broker_ll1.setVisibility(View.VISIBLE);
                }
//                }else {
//                    initDataNum("", "", "", "2");
//                }
                break;
        }

    }


    //TODO 详情页趋势图数据填充
    private void setData(final List<Integer> list) {

        ArrayList<Entry> values = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            values.add(new Entry(i, list.get(i)));
        }


        {
            combinedChart.setDrawBorders(false); // 显示边界
            combinedChart.getDescription().setEnabled(false);  // 不显示备注信息
            combinedChart.setPinchZoom(false); // 比例缩放
            combinedChart.animateY(1500);
            combinedChart.setTouchEnabled(true);
            combinedChart.setDragEnabled(true);
            combinedChart.setExtraTopOffset(10);
            combinedChart.getLegend().setEnabled(false);
            combinedChart.setDoubleTapToZoomEnabled(false);
            combinedChart.setHighlightPerTapEnabled(false);
            combinedChart.getAxisRight().setEnabled(false);

            XAxis xAxis = combinedChart.getXAxis();
            xAxis.setDrawGridLines(false);
            /*解决左右两端柱形图只显示一半的情况 只有使用CombinedChart时会出现，如果单独使用BarChart不会有这个问题*/
            xAxis.setAxisMinimum(-0.2f);
            Log.i("长度", "values.size()" + values.size());
            Log.i("长度", "list.size()" + list.size());
            Log.i("长度", "indexList.size()" + indexList.size());
            xAxis.setAxisMaximum(values.size() - 0.5f);
            xAxis.setGranularity(1f);
            xAxis.setTextColor(Color.parseColor("#666666"));
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); // 设置X轴标签位置，BOTTOM在底部显示，TOP在顶部显示
            xAxis.setValueFormatter(new ValueFormatter() {
                @Override
                public String getFormattedValue(float value) {
                    if (indexList.size() != 0) {
                        return indexList.get((int) value % indexList.size());
                    } else {
                        return "";
                    }
                }
            });

            int max = 0;

            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) > max) {
                    max = list.get(i);
                }
            }

            YAxis axisLeft = combinedChart.getAxisLeft(); // 获取左边Y轴操作类
            axisLeft.setAxisMinimum(0); // 设置最小值
//            axisLeft.setAxisMaximum((float) (max * 1.1)); // 设置最大值
            axisLeft.setAxisLineColor(Color.parseColor("#00000000"));
            axisLeft.setTextColor(Color.parseColor("#999999"));
            axisLeft.setGridColor(Color.parseColor("#999999"));


            List<Entry> lineEntries = new ArrayList<>();
            List<BarEntry> barEntries = new ArrayList<>();
            for (int i = 0; i < indexList.size(); i++) {
                lineEntries.add(new Entry(i, list.get(i)));
                barEntries.add(new BarEntry(i, list.get(i)));
            }

            /**
             * 初始化柱形图的数据
             * 此处用suppliers的数量做循环，因为总共所需要的数据源数量应该和标签个数一致
             * 其中BarEntry是柱形图数据源的实体类，包装xy坐标数据
             */
            /******************BarData start********************/

            BarDataSet barDataSet = new BarDataSet(barEntries, "LAR");  // 新建一组柱形图，"LAR"为本组柱形图的Label
            barDataSet.setColor(Color.parseColor("#6596ba")); // 设置柱形图颜色
            barDataSet.setValueTextColor(Color.parseColor("#666666")); //  设置线形图顶部文本颜色
            barDataSet.setDrawValues(true);
            barDataSet.setValueFormatter(new ValueFormatter() {
    @Override
    public String getFormattedValue(float value) {
        int n = (int) value;
        return n+"";
    }
});
            BarData barData = new BarData();
            barData.addDataSet(barDataSet);// 添加一组柱形图，如果有多组柱形图数据，则可以多次addDataSet来设置
            barData.setBarWidth(0.1f);

            /******************BarData end********************/

            /**
             * 初始化折线图数据
             * 说明同上
             */
            /******************LineData start********************/

            LineDataSet lineDataSet = new LineDataSet(lineEntries, "不良率");
            lineDataSet.setColor(Color.parseColor("#ce7951"));
            lineDataSet.setCircleColor(Color.parseColor("#ce7951"));
            lineDataSet.setCircleHoleColor(Color.parseColor("#FFFFFF"));
            lineDataSet.setLineWidth(1);
            lineDataSet.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
            lineDataSet.setHighlightEnabled(false);
            lineDataSet.setCubicIntensity(0);
            lineDataSet.setValueTextSize(10);
            lineDataSet.setDrawValues(false);
            LineData lineData = new LineData();
            lineData.addDataSet(lineDataSet);
            /******************LineData end********************/

            CombinedData combinedData = new CombinedData(); // 创建组合图的数据
            combinedData.setData(barData);  // 添加柱形图数据源
            combinedData.setData(lineData); // 添加折线图数据源
            if (indexList.size() > 5) {
                combinedChart.setVisibleXRange(0, 5);
            }
            combinedChart.setData(combinedData); // 为组合图设置数据源
//            combinedChart.setVisibleXRangeMaximum(12);
//            combinedChart.setVisibleXRangeMinimum(6);
        }
        combinedChart.animateXY(2000, 2000);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("销毁", "数：" + FinalContents.getStoreId());
        FinalContents.setStoreId("");
        FinalContents.setAgentId("");
        NewlyIncreased.setTag("0");
        NewlyIncreased.setStartDate("");
        NewlyIncreased.setEndDate("");
        NewlyIncreased.setYJType("");
        NewlyIncreased.setYJstartDate("");
        NewlyIncreased.setYJendDate("");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            FinalContents.setStoreId("");
            FinalContents.setAgentId("");
            finish();
            // your code
            return true;// true 事件不继续传递， false 事件继续传递
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }
}
