package com.xcy.fzbcity.project_side.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
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
import com.bumptech.glide.Glide;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.CityContents;
import com.xcy.fzbcity.all.api.DynamicLineChartManager;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.api.NewlyIncreased;
import com.xcy.fzbcity.all.modle.BusinessBean;
import com.xcy.fzbcity.all.modle.DetailsBean;
import com.xcy.fzbcity.all.modle.FinanceBean;
import com.xcy.fzbcity.all.modle.OperationBean;
import com.xcy.fzbcity.all.persente.SingleClick;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.CommonUtil;
import com.xcy.fzbcity.all.utils.ToastUtil;
import com.xcy.fzbcity.all.view.AllActivity;
import com.xcy.fzbcity.all.view.MapActivity;
import com.xcy.fzbcity.all.view.ProjectDetails;
import com.xcy.fzbcity.all.view.ReportActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

//TODO 项目详情
public class DetailsTheProjectEndActivity extends AllActivity implements View.OnClickListener {

    ImageView details_the_project_end_img;
    RelativeLayout details_the_project_end_return;

    TextView details_the_project_end_tv1;
    TextView details_the_project_end_tv2;
    TextView details_the_project_end_tv3;

    TextView details_the_project_end_tv4;
    TextView details_the_project_end_tv5;
    TextView details_the_project_end_tv6;

    TextView details_the_project_end_tv8;
    TextView details_the_project_end_tv9;
    TextView details_the_project_end_tv10;
    TextView details_the_project_end_tv11;

    TextView details_the_project_end_tv12;
    TextView details_the_project_end_tv13;
    TextView details_the_project_end_tv14;
    TextView details_the_project_end_tv15;

    RelativeLayout details_the_project_end_rl1;
    RelativeLayout details_the_project_end_rl2;

    LinearLayout details_the_project_end_ll1;
    LinearLayout details_the_project_end_ll2;
    LinearLayout details_the_project_end_ll3;
    LinearLayout details_the_project_end_ll5;
    LinearLayout details_the_project_end_ll6;
    LinearLayout details_the_project_end_ll7;
    LinearLayout details_the_project_end_ll8;
    LinearLayout details_the_project_end_ll9;
    LinearLayout details_the_project_end_ll10;
    LinearLayout details_the_project_end_ll11;
    LinearLayout details_the_project_end_ll12;

    LinearLayout details_the_project_end_time_ll1;
    LinearLayout details_the_project_end_time_ll2;
    LinearLayout details_the_project_end_time_ll3;

    TextView details_the_project_end_time1;
    TextView details_the_project_end_time2;
    TextView details_the_project_end_time3;
    TextView details_the_project_end_time4;
    TextView details_the_project_end_time5;
    TextView details_the_project_end_time6;

    RadioGroup details_the_project_end_rg1;
    RadioGroup details_the_project_end_rg2;
    RadioGroup details_the_project_end_rg3;
    RadioGroup details_the_project_end_rg4;

    RadioButton details_the_project_end_rb1;
    RadioButton details_the_project_end_rb2;
    RadioButton details_the_project_end_rb3;
    RadioButton details_the_project_end_rb4;
    RadioButton details_the_project_end_rb5;
    RadioButton details_the_project_end_rb6;
    RadioButton details_the_project_end_rb7;
    RadioButton details_the_project_end_rb8;
    RadioButton details_the_project_end_rb9;
    RadioButton details_the_project_end_rb10;
    RadioButton details_the_project_end_rb11;
    RadioButton details_the_project_end_rb13;
    RadioButton details_the_project_end_rb14;
    RadioButton details_the_project_end_rb15;

    Intent intent;

    private String type1 = "";
    private String beforeDate1 = "";
    private String afterDate1 = "";

    private String type2 = "";
    private String beforeDate2 = "";
    private String afterDate2 = "";

    private String type3 = "1";
    private String beforeDate3 = "";
    private String afterDate3 = "";

    private String status = "10";
    private CombinedChart combinedChart;


    private DynamicLineChartManager dynamicLineChartManager;
    private List<String> names = new ArrayList<>(); //折线名字集合
    private List<Integer> colour = new ArrayList<>();//折线颜色集合
    private List<String> indexList;
    private int year3;
    private int month3;
    private int dayOfMonth3;
    private int year;
    private int month;
    private int dayOfMonth;
    String type = "";

    LinearLayout project_attache_ll1;
    LinearLayout project_attache_ll2;
    LinearLayout project_attache_ll3;
    LinearLayout project_attache_ll4;
    String tag = "1";
    private String string;
    private Date select;
    private Date select1;
    private Date select2;
    private Date select3;
    private Date endselect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_side_activity_details_the_project_end);
        init_No_Network();
    }

    private void init_No_Network() {
        boolean networkAvailable = CommonUtil.isNetworkAvailable(this);
        if (networkAvailable) {
            NewlyIncreased.setTag("0");
            NewlyIncreased.setStartDate("");
            NewlyIncreased.setEndDate("");
            NewlyIncreased.setYJType("0");
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
            ToastUtil.showToast(this, "当前无网络，请检查网络后再进行登录");
        }
    }

    //TODO 声明
    private void initView() {
        FinalContents.setDetails("项目详情");
        StatusBar.makeStatusBarTransparent(this);
        CityContents.setIsRead("");

        project_attache_ll1 = findViewById(R.id.project_attache_ll1);
        project_attache_ll2 = findViewById(R.id.project_attache_ll2);
        project_attache_ll3 = findViewById(R.id.project_attache_ll3);
        project_attache_ll4 = findViewById(R.id.project_attache_ll4);

        project_attache_ll1.setOnClickListener(this);
        project_attache_ll3.setOnClickListener(this);

        details_the_project_end_img = findViewById(R.id.details_the_project_end_img);
        details_the_project_end_return = findViewById(R.id.details_the_project_end_return);

        details_the_project_end_tv1 = findViewById(R.id.details_the_project_end_tv1);
        details_the_project_end_tv2 = findViewById(R.id.details_the_project_end_tv2);
        details_the_project_end_tv3 = findViewById(R.id.details_the_project_end_tv3);

        details_the_project_end_tv4 = findViewById(R.id.details_the_project_end_tv4);
        details_the_project_end_tv5 = findViewById(R.id.details_the_project_end_tv5);
        details_the_project_end_tv6 = findViewById(R.id.details_the_project_end_tv6);

        details_the_project_end_tv8 = findViewById(R.id.details_the_project_end_tv8);
        details_the_project_end_tv9 = findViewById(R.id.details_the_project_end_tv9);
        details_the_project_end_tv10 = findViewById(R.id.details_the_project_end_tv10);
        details_the_project_end_tv11 = findViewById(R.id.details_the_project_end_tv11);

        details_the_project_end_tv12 = findViewById(R.id.details_the_project_end_tv12);
        details_the_project_end_tv13 = findViewById(R.id.details_the_project_end_tv13);
        details_the_project_end_tv14 = findViewById(R.id.details_the_project_end_tv14);
        details_the_project_end_tv15 = findViewById(R.id.details_the_project_end_tv15);

        details_the_project_end_rl1 = findViewById(R.id.details_the_project_end_rl1);
        details_the_project_end_rl2 = findViewById(R.id.details_the_project_end_rl2);

        details_the_project_end_ll1 = findViewById(R.id.details_the_project_end_ll1);
        details_the_project_end_ll2 = findViewById(R.id.details_the_project_end_ll2);
        details_the_project_end_ll3 = findViewById(R.id.details_the_project_end_ll3);
        details_the_project_end_ll5 = findViewById(R.id.details_the_project_end_ll5);
        details_the_project_end_ll6 = findViewById(R.id.details_the_project_end_ll6);
        details_the_project_end_ll7 = findViewById(R.id.details_the_project_end_ll7);
        details_the_project_end_ll8 = findViewById(R.id.details_the_project_end_ll8);
        details_the_project_end_ll9 = findViewById(R.id.details_the_project_end_ll9);
        details_the_project_end_ll10 = findViewById(R.id.details_the_project_end_ll10);
        details_the_project_end_ll11 = findViewById(R.id.details_the_project_end_ll11);
        details_the_project_end_ll12 = findViewById(R.id.details_the_project_end_ll12);

        details_the_project_end_time1 = findViewById(R.id.details_the_project_end_time1);
        details_the_project_end_time2 = findViewById(R.id.details_the_project_end_time2);
        details_the_project_end_time3 = findViewById(R.id.details_the_project_end_time3);
        details_the_project_end_time4 = findViewById(R.id.details_the_project_end_time4);
        details_the_project_end_time5 = findViewById(R.id.details_the_project_end_time5);
        details_the_project_end_time6 = findViewById(R.id.details_the_project_end_time6);

        details_the_project_end_rg1 = findViewById(R.id.details_the_project_end_rg1);
        details_the_project_end_rg2 = findViewById(R.id.details_the_project_end_rg2);
        details_the_project_end_rg3 = findViewById(R.id.details_the_project_end_rg3);
        details_the_project_end_rg4 = findViewById(R.id.details_the_project_end_rg4);

        details_the_project_end_rb1 = findViewById(R.id.details_the_project_end_rb1);
        details_the_project_end_rb2 = findViewById(R.id.details_the_project_end_rb2);
        details_the_project_end_rb3 = findViewById(R.id.details_the_project_end_rb3);
        details_the_project_end_rb4 = findViewById(R.id.details_the_project_end_rb4);
        details_the_project_end_rb5 = findViewById(R.id.details_the_project_end_rb5);
        details_the_project_end_rb6 = findViewById(R.id.details_the_project_end_rb6);
        details_the_project_end_rb7 = findViewById(R.id.details_the_project_end_rb7);
        details_the_project_end_rb8 = findViewById(R.id.details_the_project_end_rb8);
        details_the_project_end_rb9 = findViewById(R.id.details_the_project_end_rb9);
        details_the_project_end_rb10 = findViewById(R.id.details_the_project_end_rb10);
        details_the_project_end_rb11 = findViewById(R.id.details_the_project_end_rb11);
        details_the_project_end_rb13 = findViewById(R.id.details_the_project_end_rb13);
        details_the_project_end_rb14 = findViewById(R.id.details_the_project_end_rb14);
        details_the_project_end_rb15 = findViewById(R.id.details_the_project_end_rb15);

        details_the_project_end_time_ll1 = findViewById(R.id.details_the_project_end_time_ll1);
        details_the_project_end_time_ll2 = findViewById(R.id.details_the_project_end_time_ll2);
        details_the_project_end_time_ll3 = findViewById(R.id.details_the_project_end_time_ll3);

        combinedChart = findViewById(R.id.details_chart);

        details_the_project_end_return.setOnClickListener(this);
        details_the_project_end_rl1.setOnClickListener(this);
        details_the_project_end_rl2.setOnClickListener(this);

        details_the_project_end_time1.setOnClickListener(this);
        details_the_project_end_time2.setOnClickListener(this);
        details_the_project_end_time3.setOnClickListener(this);
        details_the_project_end_time4.setOnClickListener(this);
        details_the_project_end_time5.setOnClickListener(this);
        details_the_project_end_time6.setOnClickListener(this);

        details_the_project_end_tv4.setOnClickListener(this);
        details_the_project_end_tv5.setOnClickListener(this);
        details_the_project_end_tv6.setOnClickListener(this);
        details_the_project_end_tv8.setOnClickListener(this);
        details_the_project_end_tv9.setOnClickListener(this);
        details_the_project_end_tv10.setOnClickListener(this);
        details_the_project_end_tv11.setOnClickListener(this);
        details_the_project_end_tv12.setOnClickListener(this);
        details_the_project_end_tv13.setOnClickListener(this);
        details_the_project_end_tv14.setOnClickListener(this);
        details_the_project_end_tv15.setOnClickListener(this);

        details_the_project_end_rb1.setOnClickListener(this);
        details_the_project_end_rb2.setOnClickListener(this);
        details_the_project_end_rb3.setOnClickListener(this);
        details_the_project_end_rb4.setOnClickListener(this);
        details_the_project_end_rb5.setOnClickListener(this);
        details_the_project_end_rb6.setOnClickListener(this);
        details_the_project_end_rb7.setOnClickListener(this);
        details_the_project_end_rb8.setOnClickListener(this);
        details_the_project_end_rb9.setOnClickListener(this);
        details_the_project_end_rb10.setOnClickListener(this);
        details_the_project_end_rb11.setOnClickListener(this);
        details_the_project_end_rb13.setOnClickListener(this);
        details_the_project_end_rb14.setOnClickListener(this);
        details_the_project_end_rb15.setOnClickListener(this);

        details_the_project_end_ll1.setOnClickListener(this);
        details_the_project_end_ll2.setOnClickListener(this);
        details_the_project_end_ll3.setOnClickListener(this);
        details_the_project_end_ll5.setOnClickListener(this);
        details_the_project_end_ll6.setOnClickListener(this);
        details_the_project_end_ll7.setOnClickListener(this);
        details_the_project_end_ll8.setOnClickListener(this);
        details_the_project_end_ll9.setOnClickListener(this);
        details_the_project_end_ll10.setOnClickListener(this);
        details_the_project_end_ll11.setOnClickListener(this);
        details_the_project_end_ll12.setOnClickListener(this);


        initDate();
        initData();
    }

    //TODO 详情页时间赋值
    private void initDate() {
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        select = calendar.getTime();
        select1 = calendar.getTime();
        select2 = calendar.getTime();
        select3 = calendar.getTime();
        endselect = calendar.getTime();
        string = String.format(Locale.getDefault(), "%d.%02d.%02d", year, month + 1, dayOfMonth);
        details_the_project_end_time1.setText("<" + string);
        details_the_project_end_time2.setText("-" + string + " >");
        details_the_project_end_time3.setText("<" + string);
        details_the_project_end_time4.setText("-" + string + " >");
        details_the_project_end_time5.setText("<" + string);
        details_the_project_end_time6.setText("-" + string + " >");

        beforeDate1 = string;
        afterDate1 = string;
        beforeDate2 = string;
        afterDate2 = string;
        beforeDate3 = string;
        afterDate3 = string;

        //                TODO 开始时间
        details_the_project_end_time1.setOnClickListener(new View.OnClickListener() {
            @SingleClick(1000)
            @Override
            public void onClick(View view) {
                initTime1_Date1();
            }
        });
        //                TODO 结束时间
        details_the_project_end_time2.setOnClickListener(new View.OnClickListener() {
            @SingleClick(1000)
            @Override
            public void onClick(View view) {
                initTime1_Date2();
            }
        });

        //                TODO 开始时间
        details_the_project_end_time3.setOnClickListener(new View.OnClickListener() {
            @SingleClick(1000)
            @Override
            public void onClick(View view) {
                initTime2_Date1();
            }
        });
        //                TODO 结束时间
        details_the_project_end_time4.setOnClickListener(new View.OnClickListener() {
            @SingleClick(1000)
            @Override
            public void onClick(View view) {
                initTime2_Date2();
            }
        });

        //                TODO 开始时间
        details_the_project_end_time5.setOnClickListener(new View.OnClickListener() {
            @SingleClick(1000)
            @Override
            public void onClick(View view) {
                initTime3_Date1();
            }
        });

        //                TODO 结束时间
        details_the_project_end_time6.setOnClickListener(new View.OnClickListener() {
            @SingleClick(1000)
            @Override
            public void onClick(View view) {
                initTime3_Date2();
            }
        });
    }

    //            TODO  项目详情    运营数据   开始时间
    private void initTime1_Date1() {
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(year - 3, month, dayOfMonth);
        Calendar endDate = Calendar.getInstance();
        TimePickerView pvTime = new TimePickerBuilder(DetailsTheProjectEndActivity.this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                select2 = date;
                details_the_project_end_time1.setText("<" + getTime2(date));
                beforeDate1 = getTime2(date);
                NewlyIncreased.setStartDate(getTime2(date));
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

    //            TODO  项目详情    运营数据   结束时间
    private void initTime1_Date2() {
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(year - 3, month, dayOfMonth);
        Calendar endDate = Calendar.getInstance();
        TimePickerView pvTime = new TimePickerBuilder(DetailsTheProjectEndActivity.this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                if (select2.after(date)) {
                    ToastUtil.showLongToast(DetailsTheProjectEndActivity.this, "开始时间不能大于结束时间");
                } else {
                    afterDate1 = getTime2(date);
                    details_the_project_end_time2.setText("-" + getTime2(date) + " >");
                    NewlyIncreased.setEndDate(getTime2(date));
                    initViewData2();
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

    //            TODO  项目详情    财务数据   开始时间
    private void initTime2_Date1() {
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(year - 3, month, dayOfMonth);
        Calendar endDate = Calendar.getInstance();
        TimePickerView pvTime = new TimePickerBuilder(DetailsTheProjectEndActivity.this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                select3 = date;
                beforeDate2 = getTime2(date);
                details_the_project_end_time3.setText("<" + getTime2(date));
                NewlyIncreased.setYJstartDate(getTime2(date));
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

    //            TODO  项目详情    财务数据   结束时间
    private void initTime2_Date2() {
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(year - 3, month, dayOfMonth);
        Calendar endDate = Calendar.getInstance();
        TimePickerView pvTime = new TimePickerBuilder(DetailsTheProjectEndActivity.this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                afterDate2 = getTime2(date);
                if (select3.after(date)) {
                    ToastUtil.showLongToast(DetailsTheProjectEndActivity.this, "开始时间不能大于结束时间");
                } else {
                    details_the_project_end_time4.setText("-" + getTime2(date) + " >");
                    NewlyIncreased.setYJendDate(getTime2(date));
                    initViewData1();
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

    //            TODO  项目详情    业务趋势   开始时间
    private void initTime3_Date1() {
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(year - 3, month, dayOfMonth);
        Calendar endDate = Calendar.getInstance();
        TimePickerView pvTime = new TimePickerBuilder(DetailsTheProjectEndActivity.this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                select = date;
                select1 = date;
                beforeDate3 = getTime2(date);
                details_the_project_end_time5.setText("<" + getTime2(date));
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

    //            TODO  项目详情    业务趋势   结束时间
    private void initTime3_Date2() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(select);
        int selectyear = calendar.get(Calendar.YEAR);
        int selectmonth = calendar.get(Calendar.MONTH);
        int selectdayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        final Calendar selected = Calendar.getInstance();
        selected.set(selectyear, selectmonth, selectdayOfMonth + 100);
        select = selected.getTime();

        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(year - 3, month, dayOfMonth);
        Calendar endDate = Calendar.getInstance();
        TimePickerView pvTime = new TimePickerBuilder(DetailsTheProjectEndActivity.this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                if (select1.after(date)) {
                    ToastUtil.showLongToast(DetailsTheProjectEndActivity.this, "开始时间不能大于结束时间");
                } else if (select.before(date)) {
                    ToastUtil.showLongToast(DetailsTheProjectEndActivity.this, "时间间隔不能大于100天");
                } else {
                    afterDate3 = getTime2(date);
                    details_the_project_end_time6.setText("-" + getTime2(date) + " >");
                    initViewData3();
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


    //TODO 详情页财务数据赋值
    private void initData() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<DetailsBean> userMessage = fzbInterface.getDetailsBeanList(FinalContents.getUserID(), FinalContents.getProjectID());
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DetailsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(DetailsBean detailsBean) {
                        FinalContents.setProjectID(detailsBean.getData().getProject().getId());
                        Glide.with(DetailsTheProjectEndActivity.this).load(FinalContents.getImageUrl() + detailsBean.getData().getProject().getProjectImg()).into(details_the_project_end_img);
                        details_the_project_end_tv1.setText("[" + detailsBean.getData().getProject().getCityName() + "]" + detailsBean.getData().getProject().getProjectName());
                        details_the_project_end_tv2.setText("项目地址：" + detailsBean.getData().getProject().getAddress());
                        details_the_project_end_tv3.setText(Html.fromHtml("报备(" + "<font color='#A52A2A'>" + detailsBean.getData().getProject().getReportAmount() + "</font>" + ")  " + "关注(" + "<font color='#A52A2A'>" + detailsBean.getData().getProject().getBrowseNum() + "</font>" + ")  " + "收藏(" + "<font color='#A52A2A'>" + detailsBean.getData().getProject().getCollectionNum() + "</font>" + ")  " + "转发(" + "<font color='#A52A2A'>" + detailsBean.getData().getProject().getForwardingAmount() + "</font>" + ")  "));

                        details_the_project_end_tv12.setText("" + detailsBean.getData().getReceivableMoneyMap().getReceivableMoney());
                        details_the_project_end_tv13.setText("" + detailsBean.getData().getReceivableMoneyMap().getBackMoney());
                        details_the_project_end_tv14.setText("" + detailsBean.getData().getReceivableMoneyMap().getInvoiceMoney());
                        details_the_project_end_tv15.setText("" + detailsBean.getData().getReceivableMoneyMap().getSurplusMoney());

                        details_the_project_end_tv4.setText("" + detailsBean.getData().getOperation().getReportNumber());
                        details_the_project_end_tv5.setText("" + detailsBean.getData().getOperation().getReportOk());
                        details_the_project_end_tv6.setText("" + detailsBean.getData().getOperation().getAccessingNumber());
                        details_the_project_end_tv8.setText("" + detailsBean.getData().getOperation().getIsIslandNumber());
                        details_the_project_end_tv9.setText("" + detailsBean.getData().getOperation().getEarnestMoneyNumber());
                        details_the_project_end_tv10.setText("" + detailsBean.getData().getOperation().getTradeNumber());
                        details_the_project_end_tv11.setText("" + detailsBean.getData().getOperation().getInvalidNum());
                        if (detailsBean.getData().getGsonOption().getSeries().get(0).getData().size() != 0) {
                            List<Integer> integers = detailsBean.getData().getGsonOption().getSeries().get(0).getData();
                            indexList = detailsBean.getData().getGsonOption().getXAxis().getData();
                            setData(integers);
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

    //TODO 详情页财务数据赋值
    private void initViewData1() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<OperationBean> userMessage = fzbInterface.getOperationList(FinalContents.getUserID(), FinalContents.getProjectID(), beforeDate2, afterDate2, type2);
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<OperationBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(OperationBean operationBean) {
                        details_the_project_end_tv12.setText("" + operationBean.getData().getReceivableMoney());
                        details_the_project_end_tv13.setText("" + operationBean.getData().getBackMoney());
                        details_the_project_end_tv14.setText("" + operationBean.getData().getInvoiceMoney());
                        details_the_project_end_tv15.setText("" + operationBean.getData().getSurplusMoney());
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

    //TODO 详情页运营数据赋值
    private void initViewData2() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<FinanceBean> userMessage = fzbInterface.getFinanceList(FinalContents.getUserID(), FinalContents.getProjectID(), beforeDate1, afterDate1, type1, tag);
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FinanceBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(FinanceBean financeBean) {
                        details_the_project_end_tv4.setText("" + financeBean.getData().getReportNumber());
                        details_the_project_end_tv5.setText("" + financeBean.getData().getReportOk());
                        details_the_project_end_tv6.setText("" + financeBean.getData().getAccessingNumber());
                        details_the_project_end_tv8.setText("" + financeBean.getData().getIsIslandNumber());
                        details_the_project_end_tv9.setText("" + financeBean.getData().getEarnestMoneyNumber());
                        details_the_project_end_tv10.setText("" + financeBean.getData().getTradeNumber());
                        details_the_project_end_tv11.setText("" + financeBean.getData().getInvalidNum());

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

    //TODO 详情页趋势数据赋值
    private void initViewData3() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<BusinessBean> userMessage = fzbInterface.getBusinesseList(FinalContents.getUserID(), FinalContents.getProjectID(), beforeDate3, afterDate3, type3, status);
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BusinessBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(BusinessBean businessBean) {
                        if (businessBean.getData().getSeries().get(0).getData().size() != 0) {
                            List<Integer> integers = businessBean.getData().getSeries().get(0).getData();
                            indexList = businessBean.getData().getXAxis().getData();
                            setData(integers);
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
//            combinedChart.setVisibleXRangeMinimum(5);

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

            float max = 0;

            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) > max) {
                    max = list.get(i);
                }
            }

            YAxis axisLeft = combinedChart.getAxisLeft(); // 获取左边Y轴操作类
            axisLeft.setAxisMinimum(0); // 设置最小值
//            axisLeft.setAxisMaximum(max+5); // 设置最大值
//            axisLeft.setLabelCount(5); // 设置最大值
            axisLeft.setAxisLineColor(Color.parseColor("#00000000"));
            axisLeft.setTextColor(Color.parseColor("#999999"));

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
                    return n + "";
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
            combinedChart.setVisibleXRangeMaximum(12);
            combinedChart.setVisibleXRangeMinimum(5);
        }
        combinedChart.animateXY(2000, 2000);

    }

    //TODO 点击事件
    @SingleClick(1000)
    @Override
    public void onClick(View view) {
        if (NewlyIncreased.getTag().equals("3")) {
            NewlyIncreased.setStartDate(beforeDate1);
            NewlyIncreased.setEndDate(afterDate1);
        } else {
            NewlyIncreased.setStartDate("");
            NewlyIncreased.setEndDate("");
        }

        if (NewlyIncreased.getYJType().equals("3")) {
            NewlyIncreased.setYJstartDate(beforeDate2);
            NewlyIncreased.setYJendDate(afterDate2);
        } else {
            NewlyIncreased.setYJstartDate("");
            NewlyIncreased.setYJendDate("");
        }
        switch (view.getId()) {
//                TODO 返回上一层
            case R.id.details_the_project_end_return:
                finish();
                break;
//                TODO 头部
            case R.id.details_the_project_end_rl1:
                intent = new Intent(DetailsTheProjectEndActivity.this, ProjectDetails.class);
                startActivity(intent);
                break;
//                TODO 项目定位
            case R.id.details_the_project_end_rl2:
                intent = new Intent(DetailsTheProjectEndActivity.this, MapActivity.class);
                startActivity(intent);
                break;
            case R.id.details_the_project_end_rb1:
                details_the_project_end_time_ll1.setVisibility(View.GONE);
                beforeDate1 = "";
                afterDate1 = "";
                type1 = "";
                NewlyIncreased.setTag("0");
                initViewData2();
                break;
            case R.id.details_the_project_end_rb2:
                details_the_project_end_time_ll1.setVisibility(View.GONE);
                beforeDate1 = "";
                afterDate1 = "";
                type1 = "1";
                NewlyIncreased.setTag("1");
                initViewData2();
                break;
            case R.id.details_the_project_end_rb3:
                details_the_project_end_time_ll1.setVisibility(View.GONE);
                beforeDate1 = "";
                afterDate1 = "";
                type1 = "2";
                NewlyIncreased.setTag("2");
                initViewData2();
                break;
            case R.id.details_the_project_end_rb4:
                details_the_project_end_time_ll1.setVisibility(View.VISIBLE);
                type1 = "3";
                beforeDate1 = string;
                afterDate1 = string;
                details_the_project_end_time1.setText("<" + string);
                details_the_project_end_time2.setText("-" + string + " >");
                NewlyIncreased.setTag("3");
                initViewData2();
                initDate();
                break;
            case R.id.details_the_project_end_rb5:
                details_the_project_end_time_ll2.setVisibility(View.GONE);
                beforeDate2 = "";
                afterDate2 = "";
                type2 = "";
                NewlyIncreased.setYJType("0");
                initViewData1();
                break;
            case R.id.details_the_project_end_rb6:
                details_the_project_end_time_ll2.setVisibility(View.GONE);
                beforeDate2 = "";
                afterDate2 = "";
                type2 = "1";
                NewlyIncreased.setYJType("1");
                initViewData1();
                break;
            case R.id.details_the_project_end_rb7:
                details_the_project_end_time_ll2.setVisibility(View.GONE);
                beforeDate2 = "";
                afterDate2 = "";
                type2 = "2";
                NewlyIncreased.setYJType("2");
                initViewData1();
                break;
            case R.id.details_the_project_end_rb8:
                details_the_project_end_time_ll2.setVisibility(View.VISIBLE);
                type2 = "3";
                beforeDate2 = string;
                afterDate2 = string;
                details_the_project_end_time3.setText("<" + string);
                details_the_project_end_time4.setText("-" + string + " >");
                NewlyIncreased.setYJType("3");
                initViewData1();
                initDate();
                break;
//                    TODO 报备
            case R.id.details_the_project_end_rb9:
                status = "10";
                initViewData3();
                break;
//                        TODO 认筹
            case R.id.details_the_project_end_rb10:
                status = "50";
                initViewData3();
                break;
//                        TODO 成交
            case R.id.details_the_project_end_rb11:
                status = "60";
                initViewData3();
                break;
//                        TODO 昨日
            case R.id.details_the_project_end_rb13:
                details_the_project_end_time_ll3.setVisibility(View.GONE);
                beforeDate3 = "";
                afterDate3 = "";
                type3 = "1";
                initViewData3();
                break;
//                        TODO 近七日
            case R.id.details_the_project_end_rb14:
                details_the_project_end_time_ll3.setVisibility(View.GONE);
                beforeDate3 = "";
                afterDate3 = "";
                type3 = "2";
                initViewData3();
                break;
//                        TODO 自定义
            case R.id.details_the_project_end_rb15:
                details_the_project_end_time_ll3.setVisibility(View.VISIBLE);
                type3 = "3";
                beforeDate3 = string;
                afterDate3 = string;
                details_the_project_end_time5.setText("<" + string);
                details_the_project_end_time6.setText("-" + string + " >");
                initDate();
                initViewData3();
                break;
//                TODO  报备审核
            case R.id.details_the_project_end_ll1:
                intent = new Intent(DetailsTheProjectEndActivity.this, CheckPendingTheProjectEndActivity.class);
                intent.putExtra("client", "1");
                startActivity(intent);
                break;
//                TODO  报备
            case R.id.details_the_project_end_ll2:
                intent = new Intent(DetailsTheProjectEndActivity.this, MyClientActivity.class);
                intent.putExtra("client", "1");
                startActivity(intent);
                break;
//                TODO  到访
            case R.id.details_the_project_end_ll3:
                intent = new Intent(DetailsTheProjectEndActivity.this, MyClientActivity.class);
                intent.putExtra("client", "2");
                startActivity(intent);
                break;
//               TODO  登岛
            case R.id.details_the_project_end_ll5:
                intent = new Intent(DetailsTheProjectEndActivity.this, MyClientActivity.class);
                intent.putExtra("client", "3");
                startActivity(intent);
                break;
//                TODO  认筹
            case R.id.details_the_project_end_ll6:
                intent = new Intent(DetailsTheProjectEndActivity.this, MyClientActivity.class);
                intent.putExtra("client", "4");
                startActivity(intent);
                break;
//                TODO  成交
            case R.id.details_the_project_end_ll7:
                intent = new Intent(DetailsTheProjectEndActivity.this, MyClientActivity.class);
                intent.putExtra("client", "5");
                startActivity(intent);
                break;
//                TODO  失效
            case R.id.details_the_project_end_ll8:
                intent = new Intent(DetailsTheProjectEndActivity.this, MyClientActivity.class);
                intent.putExtra("client", "6");
                startActivity(intent);
                break;
//                TODO  应收
            case R.id.details_the_project_end_ll9:
                intent = new Intent(DetailsTheProjectEndActivity.this, CommissionTheProjectEndActivity.class);
                startActivity(intent);
                break;
//                TODO  已结
            case R.id.details_the_project_end_ll10:
                intent = new Intent(DetailsTheProjectEndActivity.this, CommissionTheProjectEndActivity.class);
                startActivity(intent);
                break;
//                TODO  待结
            case R.id.details_the_project_end_ll11:
                intent = new Intent(DetailsTheProjectEndActivity.this, CommissionTheProjectEndActivity.class);
                startActivity(intent);
                break;
//                TODO  未结
            case R.id.details_the_project_end_ll12:
                intent = new Intent(DetailsTheProjectEndActivity.this, CommissionTheProjectEndActivity.class);
                startActivity(intent);
                break;
            case R.id.project_attache_ll1://实时
                tag = "1";
                project_attache_ll2.setVisibility(View.VISIBLE);
                project_attache_ll4.setVisibility(View.INVISIBLE);
                details_the_project_end_ll1.setClickable(true);
                details_the_project_end_ll2.setClickable(true);
                details_the_project_end_ll3.setClickable(true);
                details_the_project_end_ll5.setClickable(true);
                details_the_project_end_ll6.setClickable(true);
                details_the_project_end_ll7.setClickable(true);
                details_the_project_end_ll8.setClickable(true);
                if (details_the_project_end_rb1.isChecked() == true) {
                    beforeDate1 = "";
                    afterDate1 = "";
                    type1 = "0";
                    NewlyIncreased.setTag("0");
                    initViewData2();
                    details_the_project_end_time_ll1.setVisibility(View.GONE);
                } else if (details_the_project_end_rb2.isChecked() == true) {
                    beforeDate1 = "";
                    afterDate1 = "";
                    type1 = "1";
                    NewlyIncreased.setTag("1");
                    initViewData2();
                    details_the_project_end_time_ll1.setVisibility(View.GONE);
                } else if (details_the_project_end_rb3.isChecked() == true) {
                    beforeDate1 = "";
                    afterDate1 = "";
                    type1 = "2";
                    NewlyIncreased.setTag("2");
                    initViewData2();
                    details_the_project_end_time_ll1.setVisibility(View.GONE);
                } else if (details_the_project_end_rb4.isChecked() == true) {
                    type1 = "3";
                    NewlyIncreased.setTag("3");
                    initViewData2();
                    details_the_project_end_time_ll1.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.project_attache_ll3://总体
                tag = "2";
                project_attache_ll2.setVisibility(View.INVISIBLE);
                project_attache_ll4.setVisibility(View.VISIBLE);
                details_the_project_end_ll1.setClickable(false);
                details_the_project_end_ll2.setClickable(false);
                details_the_project_end_ll3.setClickable(false);
                details_the_project_end_ll5.setClickable(false);
                details_the_project_end_ll6.setClickable(false);
                details_the_project_end_ll7.setClickable(false);
                details_the_project_end_ll8.setClickable(false);
                if (details_the_project_end_rb1.isChecked() == true) {
                    beforeDate1 = "";
                    afterDate1 = "";
                    type1 = "0";
                    NewlyIncreased.setTag("0");
                    initViewData2();
                    details_the_project_end_time_ll1.setVisibility(View.GONE);
                } else if (details_the_project_end_rb2.isChecked() == true) {
                    beforeDate1 = "";
                    afterDate1 = "";
                    type1 = "1";
                    NewlyIncreased.setTag("1");
                    initViewData2();
                    details_the_project_end_time_ll1.setVisibility(View.GONE);
                } else if (details_the_project_end_rb3.isChecked() == true) {
                    beforeDate1 = "";
                    afterDate1 = "";
                    type1 = "2";
                    NewlyIncreased.setTag("2");
                    initViewData2();
                    details_the_project_end_time_ll1.setVisibility(View.GONE);
                } else if (details_the_project_end_rb4.isChecked() == true) {
                    type1 = "3";
                    NewlyIncreased.setTag("3");
                    initViewData2();
                    details_the_project_end_time_ll1.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FinalContents.setProjectID("");
        FinalContents.setDetails("");
        NewlyIncreased.setTag("0");
        NewlyIncreased.setStartDate("");
        NewlyIncreased.setEndDate("");
        NewlyIncreased.setYJType("0");
        NewlyIncreased.setYJstartDate("");
        NewlyIncreased.setYJendDate("");
    }

    /**
     * 比较当前时间和服务器返回时间大小
     *
     * @param nowDate
     * @param compareDate
     * @return
     */
    public boolean compareDate(String nowDate, String compareDate) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try {
            Date now = df.parse(nowDate);
            Date compare = df.parse(compareDate);
            if (now.before(compare)) {
                return true;
            } else {
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

}
