package com.xcy.fzbcity.project_attache.view;

import android.content.Intent;
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

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.adapter.MyFragmentPagerAdapter;
import com.xcy.fzbcity.all.api.CityContents;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.api.NewlyIncreased;
import com.xcy.fzbcity.all.fragment.MyFragment1;
import com.xcy.fzbcity.all.fragment.MyFragment2;
import com.xcy.fzbcity.all.modle.CompanyBean;
import com.xcy.fzbcity.all.modle.CompanyDataBean;
import com.xcy.fzbcity.all.persente.Fragnemt_SS;
import com.xcy.fzbcity.all.persente.SingleClick;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.CommonUtil;
import com.xcy.fzbcity.all.utils.MyViewPager;
import com.xcy.fzbcity.all.utils.ToastUtil;
import com.xcy.fzbcity.all.view.AllActivity;
import com.xcy.fzbcity.all.database.FinanceBean;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StoreDetailsActivity extends AllActivity implements View.OnClickListener , MyViewPager.OnSingleTouchListener {

    RelativeLayout store_details_return;
    ImageView store_details_change;
    TextView store_details_call;


    RadioGroup store_details_rg1;
    RadioGroup store_details_rg2;

    RadioButton store_details_rb1;
    RadioButton store_details_rb2;
    RadioButton store_details_rb3;
    RadioButton store_details_rb4;
    RadioButton store_details_rb5;
    RadioButton store_details_rb6;
    RadioButton store_details_rb7;
    RadioButton store_details_rb8;

    TextView store_details_tv1;
    TextView store_details_tv2;
    TextView store_details_tv3;
    TextView store_details_tv4;
    TextView store_details_tv5;
    TextView store_details_tv6;
    TextView store_details_tv7;
    TextView store_details_tv8;
    TextView store_details_tv9;
    TextView store_details_tv10;
    TextView store_details_tv11;
    TextView store_details_tv12;
    TextView store_details_tv13;

    RelativeLayout store_details_rl1;
    RelativeLayout store_details_rl2;

    LinearLayout store_details_ll1;
    LinearLayout store_details_ll2;
    LinearLayout store_details_ll3;
    LinearLayout store_details_ll4;
    LinearLayout store_details_ll5;
    private Intent intent;

    private CompanyBean.DataBean.CompanyDataStatisticsBean companyDataStatistics;
    private CompanyBean.DataBean.CompanyInfoBean companyInfo;

    private MyViewPager vpager_one;
    private ArrayList<Fragment> aList = new ArrayList<>();
    private MyFragmentPagerAdapter mAdapter;

    LinearLayout fragment_ll_1;
    LinearLayout fragment_ll_2;
    private String type;
    private String s;
    private String s1;
    String indextype = "";
    private int year;
    private int month;
    private int dayOfMonth;
    private String string1;
    private String string2;

    public static StoreDetailsActivity storeDetailsActivity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_details);
        storeDetailsActivity = this;
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
            ToastUtil.showLongToast(StoreDetailsActivity.this,"当前无网络，请检查网络后再进行登录");
        }
    }

    private void initView() {

        StatusBar.makeStatusBarTransparent(this);

        CityContents.setStore("1");

        fragment_ll_2 = findViewById(R.id.fragment_llss_2);
        fragment_ll_1 = findViewById(R.id.fragment_llss_1);
        vpager_one = findViewById(R.id.vpager_one_s2);
        vpager_one.setOnSingleTouchListener(this);
        if (FinalContents.getFragmentSSS().equals("0")) {
            mAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
            FinalContents.setFragmentSSS("1");
            aList.add(new MyFragment1());
            aList.add(new MyFragment2());

            mAdapter.setListfragment(aList);
            vpager_one.setAdapter(mAdapter);
            vpager_one.setCurrentItem(0);
        }

        vpager_one.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == 0){
//                    fragment_ll_1.setBackgroundColor(Color.parseColor("#334485"));
//                    fragment_ll_2.setBackgroundColor(Color.parseColor("#EEEEEE"));
                    fragment_ll_1.setBackgroundResource(R.drawable.checkbox_underline_shape_s);
                    fragment_ll_2.setBackgroundResource(R.drawable.checkbox_underline_shape_s_s);
                }else if(position == 1){
//                    fragment_ll_2.setBackgroundColor(Color.parseColor("#334485"));
//                    fragment_ll_1.setBackgroundColor(Color.parseColor("#EEEEEE"));
                    fragment_ll_1.setBackgroundResource(R.drawable.checkbox_underline_shape_s_s);
                    fragment_ll_2.setBackgroundResource(R.drawable.checkbox_underline_shape_s);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        store_details_return = findViewById(R.id.store_details_return);
        store_details_change = findViewById(R.id.store_details_change);
        store_details_tv13 = findViewById(R.id.store_details_tv13);
        store_details_call = findViewById(R.id.store_details_call);

        store_details_rg1 = findViewById(R.id.store_details_rg1);
        store_details_rg2 = findViewById(R.id.store_details_rg2);

        store_details_rb1 = findViewById(R.id.store_details_rb1);
        store_details_rb2 = findViewById(R.id.store_details_rb2);
        store_details_rb3 = findViewById(R.id.store_details_rb3);
        store_details_rb4 = findViewById(R.id.store_details_rb4);
        store_details_rb5 = findViewById(R.id.store_details_rb5);
        store_details_rb6 = findViewById(R.id.store_details_rb6);
        store_details_rb7 = findViewById(R.id.store_details_rb7);
        store_details_rb8 = findViewById(R.id.store_details_rb8);

        store_details_tv1 = findViewById(R.id.store_details_tv1);
        store_details_tv2 = findViewById(R.id.store_details_tv2);
        store_details_tv3 = findViewById(R.id.store_details_tv3);
        store_details_tv4 = findViewById(R.id.store_details_tv4);
        store_details_tv5 = findViewById(R.id.store_details_tv5);
//        store_details_tv6 = findViewById(R.id.store_details_tv6);
//        store_details_tv7 = findViewById(R.id.store_details_tv7);
        store_details_tv8 = findViewById(R.id.store_details_tv8);
        store_details_tv9 = findViewById(R.id.store_details_tv9);
        store_details_tv10 = findViewById(R.id.store_details_tv10);
        store_details_tv11 = findViewById(R.id.store_details_tv11);
        store_details_tv12 = findViewById(R.id.store_details_tv12);
//
//        store_details_rl1 = findViewById(R.id.store_details_rl1);
//        store_details_rl2 = findViewById(R.id.store_details_rl2);

        store_details_ll1 = findViewById(R.id.store_details_ll1);
        store_details_ll2 = findViewById(R.id.store_details_ll2);
        store_details_ll3 = findViewById(R.id.store_details_ll3);
        store_details_ll4 = findViewById(R.id.store_details_ll4);
        store_details_ll5 = findViewById(R.id.store_details_ll5);

        store_details_return.setOnClickListener(this);
        store_details_change.setOnClickListener(this);
        store_details_tv4.setOnClickListener(this);
        store_details_tv5.setOnClickListener(this);
//        store_details_rl1.setOnClickListener(this);
//        store_details_rl2.setOnClickListener(this);
        store_details_tv8.setOnClickListener(this);
        store_details_tv9.setOnClickListener(this);
//        store_details_ll3.setOnClickListener(this);
//        store_details_ll4.setOnClickListener(this);
//        store_details_ll5.setOnClickListener(this);
        store_details_call.setOnClickListener(this);

        store_details_rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.store_details_rb1) {
                    initDataNum("0", "", "");
                    NewlyIncreased.setTag("0");
                    store_details_ll1.setVisibility(View.GONE);
                } else if (i == R.id.store_details_rb2) {
                    initDataNum("1", "", "");
                    NewlyIncreased.setTag("1");
                    store_details_ll1.setVisibility(View.GONE);
                } else if (i == R.id.store_details_rb3) {
                    initDataNum("2", "", "");
                    NewlyIncreased.setTag("2");
                    store_details_ll1.setVisibility(View.GONE);
                } else if (i == R.id.store_details_rb4) {
                    store_details_tv4.setText(string1);
                    store_details_tv5.setText(string2);
                    String s1 = store_details_tv4.getText().toString();
                    String s = store_details_tv5.getText().toString();
                    NewlyIncreased.setStartDate(s1);
                    NewlyIncreased.setEndDate(s);
                    NewlyIncreased.setTag("3");
                    initDataNum("3", s1, s);
                    store_details_ll1.setVisibility(View.VISIBLE);
                }
            }
        });
        store_details_rg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.store_details_rb5) {
                    initFinanceNum("0", "", "");
                    NewlyIncreased.setYJType("0");
                    store_details_ll2.setVisibility(View.GONE);
                } else if (i == R.id.store_details_rb6) {
                    initFinanceNum("1", "", "");
                    NewlyIncreased.setYJType("1");
                    store_details_ll2.setVisibility(View.GONE);
                } else if (i == R.id.store_details_rb7) {
                    initFinanceNum("2", "", "");
                    NewlyIncreased.setYJType("2");
                    store_details_ll2.setVisibility(View.GONE);
                } else if (i == R.id.store_details_rb8) {
                    store_details_tv8.setText(string1);
                    store_details_tv9.setText(string2);
                    String s1 = store_details_tv8.getText().toString();
                    String s = store_details_tv9.getText().toString();
                    NewlyIncreased.setYJstartDate(s1);
                    NewlyIncreased.setYJendDate(s);
                    NewlyIncreased.setYJType("3");
                    initFinanceNum("3", s1, s);
                    store_details_ll2.setVisibility(View.VISIBLE);
                }
            }
        });

        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        string1 = String.format(Locale.getDefault(), "%d.%02d.%02d", year, month, dayOfMonth - 1);
        string2 = String.format(Locale.getDefault(), "%d.%02d.%02d", year, month, dayOfMonth);
        store_details_tv4.setText(string1);
        store_details_tv5.setText(string2);
        store_details_tv8.setText(string1);
        store_details_tv9.setText(string2);
        initData();

    }

    private void initData() {

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        final Observable<CompanyBean> companyDetails = fzbInterface.getCompanyDetails(FinalContents.getUserID(), FinalContents.getCompanyId());
        companyDetails.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CompanyBean>() {

                    private CompanyBean.DataBean.CompanyMoneyDataBean companyMoneyData;

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CompanyBean companyBean) {
//                        数据统计
                        companyDataStatistics = companyBean.getData().getCompanyDataStatistics();
//                        store_details_tv6.setText(companyDataStatistics.getStoreNum() + "");
//                        store_details_tv7.setText(companyDataStatistics.getAgentNum() + "");

                        EventBus.getDefault().post(new Fragnemt_SS(companyDataStatistics.getStoreNum() + "", companyDataStatistics.getAgentNum() + "", "", "", ""));

//                        财务数据
                        companyMoneyData = companyBean.getData().getCompanyMoneyData();
                        store_details_tv10.setText(companyMoneyData.getTotalAmount() + "");
                        store_details_tv11.setText(companyMoneyData.getAlreadyAmount() + "");
                        store_details_tv12.setText(companyMoneyData.getNotAmount() + "");
//                        基本信息
                        companyInfo = companyBean.getData().getCompanyInfo();
                        store_details_tv1.setText(companyInfo.getCompanyName());
                        store_details_tv2.setText(companyInfo.getCompanyAddress());


                        if (companyInfo.getShopownerName().equals("")) {

                        } else {
                            store_details_tv3.setText("公司负责人：" + companyInfo.getShopownerName());
                        }

                        if (companyInfo.getShopownerPhone().equals("")) {

                        } else {
                            store_details_call.setText(companyInfo.getShopownerPhone());
                        }


                        store_details_tv13.setText(companyInfo.getCompanyName());

                        if (companyInfo.getShopownerPhone().equals("")) {
                            store_details_call.setVisibility(View.GONE);
                        } else {
                            store_details_call.setVisibility(View.VISIBLE);
                        }


                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "公司详情错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void initFinanceNum(String type, String startTime, String endTime) {

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        builder.addConverterFactory(GsonConverterFactory.create());
        Retrofit build = builder.build();
        MyService myService = build.create(MyService.class);
        Observable<FinanceBean> financeBean = myService.getFinanceBean(FinalContents.getUserID(), FinalContents.getCompanyId(),"", "", type, startTime, endTime);
        financeBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FinanceBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FinanceBean financeBean) {
                        store_details_tv10.setText(financeBean.getData().getTotalAmount() + "");
                        store_details_tv11.setText(financeBean.getData().getAlreadyAmount() + "");
                        store_details_tv12.setText(financeBean.getData().getNotAmount() + "");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    private void initDataNum(String s, String s1, String s11) {

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<CompanyDataBean> companyData = fzbInterface.getCompanyData(FinalContents.getUserID(), FinalContents.getCompanyId(), s, s1, s11);
        companyData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CompanyDataBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CompanyDataBean companyDataBean) {
                        CompanyDataBean.DataBean data = companyDataBean.getData();
//                        store_details_tv6.setText(data.getStoreNum() + "");
//                        store_details_tv7.setText(data.getAgentNum() + "");

                        EventBus.getDefault().post(new Fragnemt_SS(data.getStoreNum() + "", data.getAgentNum() + "", "", "", ""));

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "公司详情数据统计错误信息：" + e.getMessage());
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

            case R.id.store_details_return:
                FinalContents.setMyAddType("公司");
                finish();
                break;
            case R.id.store_details_call:
                Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + companyInfo.getShopownerPhone()));//跳转到拨号界面，同时传递电话号码
                startActivity(dialIntent);
                break;
            case R.id.store_details_change:
//                finish();
                intent = new Intent(StoreDetailsActivity.this, AddCompanyActivity.class);
                FinalContents.setCompanyId(companyInfo.getCompanyId());
                FinalContents.setStoreChange("修改");
                startActivity(intent);
//                finish();
                break;
            case R.id.store_details_tv4:
                initTime1_Date1();
                break;
            case R.id.store_details_tv5:
                initTime1_Date2();
                break;
            case R.id.store_details_tv8:
                initTime2_Date1();
                break;
            case R.id.store_details_tv9:
                initTime2_Date2();
                break;
            case R.id.store_details_ll3:
                intent = new Intent(StoreDetailsActivity.this, CommissionActivity.class);
                FinalContents.setCompanyId(companyInfo.getCompanyId());
                FinalContents.setStoreId("");
                FinalContents.setAgentId("");
                startActivity(intent);
                break;
            case R.id.store_details_ll4:
                intent = new Intent(StoreDetailsActivity.this, CommissionActivity.class);
                FinalContents.setCompanyId(companyInfo.getCompanyId());
                FinalContents.setStoreId("");
                FinalContents.setAgentId("");
                startActivity(intent);
                break;
            case R.id.store_details_ll5:
                FinalContents.setCompanyId(companyInfo.getCompanyId());
                FinalContents.setStoreId("");
                FinalContents.setAgentId("");
                intent = new Intent(StoreDetailsActivity.this, CommissionActivity.class);
                startActivity(intent);

                break;
        }
    }

    //            TODO 数据统计 时间选择 开始时间
    private void initTime1_Date1(){
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(year, month, dayOfMonth-15);
        final Calendar endDate = Calendar.getInstance();
        endDate.set(year, month, dayOfMonth+15);
        TimePickerView pvTime = new TimePickerBuilder(StoreDetailsActivity.this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                store_details_tv4.setText(getTime2(date));
                NewlyIncreased.setStartDate(getTime2(date));
                initDataNum("3", store_details_tv4.getText().toString(), store_details_tv5.getText().toString());
            }
        })
                .setType(new boolean[]{true, true, true, false, false, false}) //年月日时分秒 的显示与否，不设置则默认全部显示
                .setLabel("年", "月", "日", "", "", "")//默认设置为年月日时分秒
                .isCenterLabel(false)
                .setDate(selectedDate)
                .setLineSpacingMultiplier(1.5f)
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
        TimePickerView pvTime = new TimePickerBuilder(StoreDetailsActivity.this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                store_details_tv5.setText(getTime2(date));
                NewlyIncreased.setEndDate(getTime2(date));
                initDataNum("3", store_details_tv4.getText().toString(), store_details_tv5.getText().toString());
            }
        })
                .setType(new boolean[]{true, true, true, false, false, false}) //年月日时分秒 的显示与否，不设置则默认全部显示
                .setLabel("年", "月", "日", "", "", "")//默认设置为年月日时分秒
                .isCenterLabel(false)
                .setDate(selectedDate)
                .setLineSpacingMultiplier(1.5f)
                .setTextXOffset(-10, 0,10, 0, 0, 0)//设置X轴倾斜角度[ -90 , 90°]
                .setRangDate(startDate, endDate)
                .build();
        pvTime.show();
    }

    //            TODO 数据统计 时间选择 开始时间
    private void initTime2_Date1(){
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(year, month, dayOfMonth-15);
        final Calendar endDate = Calendar.getInstance();
        endDate.set(year, month, dayOfMonth+15);
        TimePickerView pvTime = new TimePickerBuilder(StoreDetailsActivity.this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                store_details_tv8.setText(getTime2(date));
                NewlyIncreased.setYJstartDate(getTime2(date));
                initFinanceNum("3", store_details_tv8.getText().toString(), store_details_tv9.getText().toString());
            }
        })
                .setType(new boolean[]{true, true, true, false, false, false}) //年月日时分秒 的显示与否，不设置则默认全部显示
                .setLabel("年", "月", "日", "", "", "")//默认设置为年月日时分秒
                .isCenterLabel(false)
                .setDate(selectedDate)
                .setLineSpacingMultiplier(1.5f)
                .setTextXOffset(-10, 0,10, 0, 0, 0)//设置X轴倾斜角度[ -90 , 90°]
                .setRangDate(startDate, endDate)
                .build();
        pvTime.show();
    }

    //            TODO 数据统计 时间选择 开始时间
    private void initTime2_Date2(){
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(year, month, dayOfMonth-15);
        final Calendar endDate = Calendar.getInstance();
        endDate.set(year, month, dayOfMonth+15);
        TimePickerView pvTime = new TimePickerBuilder(StoreDetailsActivity.this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                store_details_tv9.setText(getTime2(date));
                NewlyIncreased.setYJendDate(getTime2(date));
                initFinanceNum("3", store_details_tv8.getText().toString(), store_details_tv9.getText().toString());
            }
        })
                .setType(new boolean[]{true, true, true, false, false, false}) //年月日时分秒 的显示与否，不设置则默认全部显示
                .setLabel("年", "月", "日", "", "", "")//默认设置为年月日时分秒
                .isCenterLabel(false)
                .setDate(selectedDate)
                .setLineSpacingMultiplier(1.5f)
                .setTextXOffset(-10, 0,10, 0, 0, 0)//设置X轴倾斜角度[ -90 , 90°]
                .setRangDate(startDate, endDate)
                .build();
        pvTime.show();
    }

    private void initDataS() {

        intent = new Intent(StoreDetailsActivity.this, BrokersListActivity.class);

        FinalContents.setCompanyId(companyInfo.getCompanyId());

        intent.putExtra("types",type);
        intent.putExtra("starts",s);
        intent.putExtra("ends",s1);

        startActivity(intent);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FinalContents.setFragmentSSS("0");
        FinalContents.setMyAddType("");
        FinalContents.setCompanyId("");
        NewlyIncreased.setTag("");
        NewlyIncreased.setStartDate("");
        NewlyIncreased.setEndDate("");
        NewlyIncreased.setYJType("");
        NewlyIncreased.setYJstartDate("");
        NewlyIncreased.setYJendDate("");
        CityContents.setStore("");
    }

    @Override
    public void onSingleTouch() {
        if(store_details_rb1.isChecked()){
            type = "0";
            s = "";
            s1 = "";
        }else if(store_details_rb2.isChecked()){
            type = "1";
            s = "";
            s1 = "";
        }else if(store_details_rb3.isChecked()){
            type = "2";
            s = "";
            s1 = "";
        }else if(store_details_rb4.isChecked()){
            type = "3";
            s = store_details_tv4.getText().toString();
            s1 = store_details_tv5.getText().toString();
        }
        int currentItem = vpager_one.getCurrentItem();
        if(currentItem == 0){
            intent = new Intent(StoreDetailsActivity.this, StoreListActivity.class);
            intent.putExtra("types",type);
            intent.putExtra("starts",s);
            intent.putExtra("ends",s1);

            FinalContents.setMyAddType("");
            FinalContents.setCompanyId(companyInfo.getCompanyId());
            startActivity(intent);
        }else if(currentItem == 1){
            initDataS();
        }
    }


}
