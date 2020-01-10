package com.xcy.fzbcity.all.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.tuacy.azlist.LettersComparator;
import com.tuacy.fuzzysearchlibrary.FuzzySearchBaseAdapter;
import com.tuacy.fuzzysearchlibrary.PinyinUtil;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.adapter.FuzzySearchAdapter;
import com.xcy.fzbcity.all.adapter.ReportItemAdapter;
import com.xcy.fzbcity.all.api.CityContents;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.modle.ChangePhoneBean;
import com.xcy.fzbcity.all.modle.ClientBean;
import com.xcy.fzbcity.all.modle.IdNumberBean;
import com.xcy.fzbcity.all.modle.ItemEntity;
import com.xcy.fzbcity.all.modle.ReportBean;
import com.xcy.fzbcity.all.persente.SingleClick;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.CommonUtil;
import com.xcy.fzbcity.all.utils.MatcherUtils;
import com.xcy.fzbcity.all.utils.ToastUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReportActivity extends AllActivity implements View.OnClickListener {

    Button report_btn_s;
    Button report_btn_f;
    LinearLayout report_tv_timer;
    TextView report_tv_sq;
    TextView report_start;
    TextView report_end;
    EditText IDcard;
    EditText price_start;
    EditText price_end;
    EditText report_remarks;

    RelativeLayout back;

    LinearLayout report_ll_start_sq;


    private CheckBox area1;
    private CheckBox area2;
    private CheckBox area3;
    private CheckBox area4;
    private CheckBox area5;
    private CheckBox area6;
    private CheckBox area7;
    private CheckBox area8;


    private CheckBox type1;
    private CheckBox type2;
    private CheckBox type3;
    private CheckBox type4;
    private CheckBox type5;

    private CheckBox goal1;
    private CheckBox goal2;
    private CheckBox goal3;
    private CheckBox goal4;
    private CheckBox goal5;
    private CheckBox goal6;
    private CheckBox goal7;
    private CheckBox goal8;

    private TextView ensure;

    private ImageView report_client_name_img;
    private EditText report_client_name_et;
    private TextView project_name;

    private String areaSection = "";
    private String ffProjectTrait = "";
    private String procuctType = "";
    private String fitmentState = "";

    private String url = FinalContents.getImageUrl() + "/fangfang/app/v1/commonSelect/projectList?" + "&userId=" + FinalContents.getUserID() + "&city=" + FinalContents.getCityID();
    private String eventUrl;

    private Map<Integer, String> areaMap = new HashMap<>();
    private Map<Integer, String> traitMap = new HashMap<>();
    private Map<Integer, String> typeMap = new HashMap<>();
    private Map<Integer, String> goalMap = new HashMap<>();
    private List<ReportBean.DataBean.AreaSectionListBean> areaSectionListBean = new ArrayList<>();
    private List<ReportBean.DataBean.ProductTypeListBean> productTypeListBeans = new ArrayList<>();
    private List<ReportBean.DataBean.ProjectLabelListBean> projectLabelListBeans = new ArrayList<>();
    private List<ReportBean.DataBean.PurposeListBean> purposeListBeans = new ArrayList<>();
    private String isIsland = "0";
    private String timeStart = "";
    private String timeEnd = "";
    private boolean type_1 = true;
    private boolean type_2 = true;
    private boolean type_3 = true;
    private boolean type_4 = true;
    private RecyclerView report_rv;
    private LinearLayout report_ll_start_sq1;
    private LinearLayout report_ll_start_sq2;

    int ifnum1 = 0;
    private LinearLayout report_linear;
    private EditText report_client_phone;
    private FuzzySearchAdapter fuzzySearchAdapter;
    private RecyclerView report_associating_inputing_rv;
    private RelativeLayout report_relative;
    private List<ClientBean.DataBean> list;
    private String customerID = "";
    private LinearLayout report_nosearch;
    private LinearLayout report_issure;
    private List<ItemEntity> dateList;
    private int year;
    private int month;
    private int dayOfMonth;
    private Date select;
    private Date endselect;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        init_No_Network();
    }

    private void init_No_Network() {
        boolean networkAvailable = CommonUtil.isNetworkAvailable(this);
        if (networkAvailable) {
            initDataReport();

            initData();
            initView();
            initDate();

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
            ToastUtil.showLongToast(this, "当前无网络，请检查网络后再进行登录");
        }
    }

    private void initView() {
        if (CityContents.getIsReport().equals("")) {
            Log.i("数据", "1走一走:" + FinalContents.getProjectType());
            FinalContents.setProjectType("");
        }
        Log.i("数据", "2走一走:" + FinalContents.getProjectType());
        CityContents.setAddClient("");
        StatusBar.makeStatusBarTransparent(this);
        report_linear = findViewById(R.id.report_linear);

        report_nosearch = findViewById(R.id.report_nosearch);
        report_associating_inputing_rv = findViewById(R.id.report_associating_inputing_rv);
        report_relative = findViewById(R.id.report_relative);

        //  TODO    城市版
        report_issure = findViewById(R.id.report_issure);

        //  TODO    城市版

        report_btn_s = findViewById(R.id.report_btn_yes);
        report_btn_f = findViewById(R.id.report_btn_no);
        report_tv_timer = findViewById(R.id.report_tv_timer);
        report_tv_sq = findViewById(R.id.report_tv_sq);

        report_ll_start_sq1 = findViewById(R.id.report_ll_start_sq1);

        report_ll_start_sq2 = findViewById(R.id.report_ll_start_sq2);

        report_ll_start_sq = findViewById(R.id.report_ll_start_sq);
        report_start = findViewById(R.id.report_start);
        report_end = findViewById(R.id.report_end);
        IDcard = findViewById(R.id.report_IDcard);
        price_start = findViewById(R.id.report_price_start);
        price_end = findViewById(R.id.report_price_end);
        report_remarks = findViewById(R.id.report_remarks);
        report_client_name_img = findViewById(R.id.report_client_name_img);
        report_client_name_et = findViewById(R.id.report_client_name_et);
        report_client_phone = findViewById(R.id.report_client_phone);
        project_name = findViewById(R.id.report_project_name);
        report_client_name_img.setOnClickListener(this);
        project_name.setOnClickListener(this);

        back = findViewById(R.id.report_back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FinalContents.setChecked(false);
                FinalContents.setChecked2(false);
                finish();
            }
        });


        report_client_name_et.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (hasFocus) {
                    // 获得焦点
                } else {
                    // 失去焦点
                    report_associating_inputing_rv.setVisibility(View.GONE);
                }

            }


        });

        report_relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                report_associating_inputing_rv.setVisibility(View.GONE);
            }
        });

        report_nosearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                report_associating_inputing_rv.setVisibility(View.GONE);
            }
        });

        report_client_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                report_associating_inputing_rv.setVisibility(View.GONE);
            }
        });

        if (FinalContents.isChecked()) {
            if (!FinalContents.getClientName().equals("")) {
                report_client_name_et.setText(FinalContents.getClientName());
                report_client_phone.setText(FinalContents.getClientPhone());
            }
        } else {
            project_name.setText("");
        }
        if (FinalContents.isChecked2()) {
            project_name.setText(FinalContents.getProjectName());
        } else {
            project_name.setText("");
        }


        report_btn_s.setOnClickListener(this);
        report_btn_f.setOnClickListener(this);
        report_tv_sq.setOnClickListener(this);

        report_rv = findViewById(R.id.report_rv);


        area1 = findViewById(R.id.report_area_1);
        area2 = findViewById(R.id.report_area_2);
        area3 = findViewById(R.id.report_area_3);
        area4 = findViewById(R.id.report_area_4);
        area5 = findViewById(R.id.report_area_5);
        area6 = findViewById(R.id.report_area_6);
        area7 = findViewById(R.id.report_area_7);
        area8 = findViewById(R.id.report_area_8);

        type1 = findViewById(R.id.report_type_1);
        type2 = findViewById(R.id.report_type_2);
        type3 = findViewById(R.id.report_type_3);
        type4 = findViewById(R.id.report_type_4);
        type5 = findViewById(R.id.report_type_5);

        goal1 = findViewById(R.id.report_goal_1);
        goal2 = findViewById(R.id.report_goal_2);
        goal3 = findViewById(R.id.report_goal_3);
        goal4 = findViewById(R.id.report_goal_4);
        goal5 = findViewById(R.id.report_goal_5);
        goal6 = findViewById(R.id.report_goal_6);
        goal7 = findViewById(R.id.report_goal_7);
        goal8 = findViewById(R.id.report_goal_8);

        ensure = findViewById(R.id.report_ensure);

        Log.i("报备跳转", "数据zhon：" + FinalContents.getClientName());
        Log.i("报备跳转", "数据zhon：" + FinalContents.getCustomerID());

        area1.setOnClickListener(this);
        area2.setOnClickListener(this);
        area3.setOnClickListener(this);
        area4.setOnClickListener(this);
        area5.setOnClickListener(this);
        area6.setOnClickListener(this);
        area7.setOnClickListener(this);
        area8.setOnClickListener(this);
        type1.setOnClickListener(this);
        type2.setOnClickListener(this);
        type3.setOnClickListener(this);
        type4.setOnClickListener(this);
        type5.setOnClickListener(this);
        goal1.setOnClickListener(this);
        goal2.setOnClickListener(this);
        goal3.setOnClickListener(this);
        goal4.setOnClickListener(this);
        goal5.setOnClickListener(this);
        goal6.setOnClickListener(this);
        goal7.setOnClickListener(this);
        goal8.setOnClickListener(this);
        ensure.setOnClickListener(this);

    }

    private void initData() {
        areaMap.put(0, "");
        areaMap.put(1, "");
        areaMap.put(2, "");
        areaMap.put(3, "");
        areaMap.put(4, "");
        areaMap.put(5, "");
        areaMap.put(6, "");
        areaMap.put(7, "");

        typeMap.put(0, "");
        typeMap.put(1, "");
        typeMap.put(2, "");
        typeMap.put(3, "");
        typeMap.put(4, "");

        goalMap.put(0, "");
        goalMap.put(1, "");
        goalMap.put(2, "");
        goalMap.put(3, "");
        goalMap.put(4, "");
        goalMap.put(5, "");
        goalMap.put(6, "");
        goalMap.put(7, "");

    }

    private void initDate() {
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        select = calendar.getTime();
        endselect = calendar.getTime();


        String string = String.format(Locale.getDefault(), "%d.%02d.%02d", year, month + 1, dayOfMonth);
        String string1 = String.format(Locale.getDefault(), "%d.%02d.%02d", year, month + 1, dayOfMonth + 1);

        report_start.setText("<" + string);
        report_end.setText("-" + string1 + " >");

        report_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initTimePickerView1();

            }
        });

        report_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initTimePickerView2();
            }
        });

    }

    private void initTimePickerView1() {
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        final Calendar endDate = Calendar.getInstance();
        endDate.set(year + 3, month, dayOfMonth);
        TimePickerView pvTime = new TimePickerBuilder(ReportActivity.this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                select = date;
                timeStart = getTime2(date);
                report_start.setText("<" + getTime2(date));
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

    private void initTimePickerView2() {
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        final Calendar endDate = Calendar.getInstance();
        endDate.set(year + 3, month, dayOfMonth);
        TimePickerView pvTime = new TimePickerBuilder(ReportActivity.this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                if (select.after(date)) {
                    ToastUtil.showLongToast(ReportActivity.this,"开始时间不能大于结束时间");
                } else {
                    endselect = date;
                    timeEnd = getTime2(date);
                    report_end.setText("-" + getTime2(date) + " >");
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

    private void initDataReport() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<ReportBean> userMessage = fzbInterface.getReportBean(FinalContents.getUserID());
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ReportBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ReportBean reportBean) {
                        areaSectionListBean = reportBean.getData().getAreaSectionList();
                        productTypeListBeans = reportBean.getData().getProductTypeList();
                        projectLabelListBeans = reportBean.getData().getProjectLabelList();
                        purposeListBeans = reportBean.getData().getPurposeList();

                        for (int i = 0; i < projectLabelListBeans.size(); i++) {
                            traitMap.put(i, "");
                        }

                        GridLayoutManager gridLayoutManager = new GridLayoutManager(ReportActivity.this, 4);
                        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
                        report_rv.setLayoutManager(gridLayoutManager);
                        ReportItemAdapter reportItemAdapter = new ReportItemAdapter(projectLabelListBeans);
                        report_rv.setAdapter(reportItemAdapter);
                        reportItemAdapter.notifyDataSetChanged();
                        reportItemAdapter.setOnItemClickListener(new ReportItemAdapter.OnItemClickLisenter() {
                            @Override
                            public void onItemClick(CheckBox checkBox, int postion) {
                                if (checkBox.isChecked()) {
                                    traitMap.put(postion, "," + projectLabelListBeans.get(postion).getId());
                                    Log.i("楼盘特色", "来来来：" + traitMap.get(postion) + "名字:" + projectLabelListBeans.get(postion).getLable());
                                } else {
                                    traitMap.put(postion, "");
                                }
                                Log.i("楼盘特色", "数据：" + traitMap.get(postion));
                            }
                        });
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("wsw", "返回的数据" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initIdNumber() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<IdNumberBean> userMessage = fzbInterface.getIdNumber(FinalContents.getProjectID());
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<IdNumberBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(IdNumberBean idNumberBean) {
                        if (idNumberBean.getData().getIsPapers() == 0) {
                            report_linear.setVisibility(View.GONE);
                        } else if (idNumberBean.getData().getIsPapers() == 1) {
                            report_linear.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("wsw", "报备的数据" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //点击事件
    @SingleClick(1000)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.report_btn_yes:
                isIsland = "1";
                report_tv_timer.setVisibility(View.VISIBLE);
                break;
            case R.id.report_btn_no:
                isIsland = "0";
                report_tv_timer.setVisibility(View.GONE);
                break;
            case R.id.report_tv_sq:
                if (report_ll_start_sq.getVisibility() == View.VISIBLE) {
                    report_tv_sq.setText("展开");
                    report_ll_start_sq.setVisibility(View.GONE);
                    report_ll_start_sq1.setVisibility(View.GONE);
                    report_ll_start_sq2.setVisibility(View.GONE);
                } else if (report_ll_start_sq.getVisibility() == View.GONE) {
                    report_tv_sq.setText("收起");
                    report_ll_start_sq.setVisibility(View.VISIBLE);
                    report_ll_start_sq1.setVisibility(View.VISIBLE);
                    report_ll_start_sq2.setVisibility(View.VISIBLE);
                }
                break;
            //面积
            case R.id.report_area_1:
                if (area1.isChecked()) {
                    areaMap.put(0, ",-50");
                } else {
                    areaMap.put(0, "");
                }
                break;
            case R.id.report_area_2:
                if (area2.isChecked()) {
                    areaMap.put(1, ",50-70");
                } else {
                    areaMap.put(1, "");
                }
                break;
            case R.id.report_area_3:
                if (area3.isChecked()) {
                    areaMap.put(2, ",70-90");
                } else {
                    areaMap.put(2, "");
                }
                break;
            case R.id.report_area_4:
                if (area4.isChecked()) {
                    areaMap.put(3, ",90-110");
                } else {
                    areaMap.put(3, "");
                }
                break;
            case R.id.report_area_5:
                if (area5.isChecked()) {
                    areaMap.put(4, ",110-130");
                } else {
                    areaMap.put(4, "");
                }
                break;
            case R.id.report_area_6:
                if (area6.isChecked()) {
                    areaMap.put(5, ",130-150");
                } else {
                    areaMap.put(5, "");
                }
                break;
            case R.id.report_area_7:
                if (area7.isChecked()) {
                    areaMap.put(6, ",150-200");
                } else {
                    areaMap.put(6, "");
                }
                break;
            case R.id.report_area_8:
                if (area8.isChecked()) {
                    areaMap.put(7, ",200-");
                } else {
                    areaMap.put(7, "");
                }
                break;
            //类型
            case R.id.report_type_1:
                if (type1.isChecked()) {
                    typeMap.put(0, ",1");
                } else {
                    typeMap.put(0, "");
                }

                break;
            case R.id.report_type_2:
                if (type2.isChecked()) {
                    typeMap.put(1, ",2");
                } else {
                    typeMap.put(1, "");
                }

                break;
            case R.id.report_type_3:
                if (type3.isChecked()) {
                    typeMap.put(2, ",3");
                } else {
                    typeMap.put(2, "");
                }

                break;
            case R.id.report_type_4:
                if (type4.isChecked()) {
                    typeMap.put(3, ",4");
                } else {
                    typeMap.put(3, "");
                }

                break;
            case R.id.report_type_5:
                if (type5.isChecked()) {
                    typeMap.put(4, ",5");
                } else {
                    typeMap.put(4, "");
                }

                break;
            //目的
            case R.id.report_goal_1:
                if (goal1.isChecked()) {
                    goalMap.put(0, ",0");
                } else {
                    goalMap.put(0, "");
                }

                break;
            case R.id.report_goal_2:
                if (goal2.isChecked()) {
                    goalMap.put(1, ",1");
                } else {
                    goalMap.put(1, "");
                }

                break;
            case R.id.report_goal_3:
                if (goal3.isChecked()) {
                    goalMap.put(2, ",2");
                } else {
                    goalMap.put(2, "");
                }

                break;
            case R.id.report_goal_4:
                if (goal4.isChecked()) {
                    goalMap.put(3, ",3");
                } else {
                    goalMap.put(3, "");
                }

                break;
            case R.id.report_goal_5:
                if (goal5.isChecked()) {
                    goalMap.put(4, ",4");
                } else {
                    goalMap.put(4, "");
                }

                break;
            case R.id.report_goal_6:
                if (goal6.isChecked()) {
                    goalMap.put(5, ",5");
                } else {
                    goalMap.put(5, "");
                }

                break;
            case R.id.report_goal_7:
                if (goal7.isChecked()) {
                    goalMap.put(6, ",6");
                } else {
                    goalMap.put(6, "");
                }

                break;
            case R.id.report_goal_8:
                if (goal8.isChecked()) {
                    goalMap.put(7, ",7");
                } else {
                    goalMap.put(7, "");
                }
                break;
            case R.id.report_client_name_img:
                Intent clientIntent = new Intent(ReportActivity.this, MyClientActivity.class);
                FinalContents.setNUM("1");
                startActivity(clientIntent);
                break;
            case R.id.report_project_name:
                Intent projectIntent = new Intent(ReportActivity.this, SearchInterfaceActivity.class);
                FinalContents.setProject("1");
                startActivity(projectIntent);
                FinalContents.setChecked2(true);
                break;
            case R.id.report_ensure:
                for (int i = 0; i < areaMap.size(); i++) {
                    areaSection = areaSection + areaMap.get(i);
                }
                for (int i = 0; i < traitMap.size(); i++) {
                    ffProjectTrait = ffProjectTrait + traitMap.get(i);
                }
                for (int i = 0; i < typeMap.size(); i++) {
                    procuctType = procuctType + typeMap.get(i);
                }
                for (int i = 0; i < goalMap.size(); i++) {
                    fitmentState = fitmentState + goalMap.get(i);
                }

                if (areaSection.equals("") || ffProjectTrait.equals("") || procuctType.equals("") || fitmentState.equals("")) {

                } else {
                    areaSection = areaSection.substring(1, areaSection.length());
                    ffProjectTrait = ffProjectTrait.substring(1, ffProjectTrait.length());
                    procuctType = procuctType.substring(1, procuctType.length());
                    fitmentState = fitmentState.substring(1, fitmentState.length());
                }

                eventUrl = url + "&areaSection=" + areaSection + "&ffProjectTrait=" + ffProjectTrait + "&procuctType=" + procuctType + "&fitmentState=" + fitmentState;

                Log.i("楼盘特色", "数据：" + ffProjectTrait);

                if (select.after(endselect)) {
                    ToastUtil.showLongToast(ReportActivity.this,"开始时间不能大于结束时间");
                    return;
                }

                if (report_linear.getVisibility() == View.VISIBLE) {
                    if (IDcard.getText().length() == 18) {
                        initReport();
                    } else if (IDcard.getText().length() == 0) {
                        ToastUtil.showLongToast(ReportActivity.this, "请输入身份证号码");
                    } else if (IDcard.getText().length() < 18) {
                        ToastUtil.showLongToast(ReportActivity.this, "请输入正确的身份证号码");
                    }
                } else if (report_linear.getVisibility() == View.GONE) {
                    initReport();
                }

                areaSection = "";
                ffProjectTrait = "";
                procuctType = "";
                fitmentState = "";

                break;
        }
    }

    private void initAssociatingInputing() {
        list = new ArrayList<>();
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<ClientBean> client = fzbInterface.getClient("", FinalContents.getUserID(), "1000");
        client.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClientBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ClientBean clientBean) {
                        list = clientBean.getData();
                        if (list.size() != 0) {
                            initSearch();
                        } else {
                            report_associating_inputing_rv.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "客户列表错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initSearch() {
        dateList = fillData(list);
        if (dateList.size() != 0) {
            Collections.sort(dateList, new LettersComparator<ItemEntity>());
            report_associating_inputing_rv.setLayoutManager(new LinearLayoutManager(ReportActivity.this));
            fuzzySearchAdapter = new FuzzySearchAdapter(dateList);
            fuzzySearchAdapter.setRecyclerView(report_associating_inputing_rv);
            report_associating_inputing_rv.setAdapter(fuzzySearchAdapter);
            fuzzySearchAdapter.notifyDataSetChanged();

            report_client_name_et.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    fuzzySearchAdapter.getFilter().filter(s);
                }

                @Override
                public void afterTextChanged(Editable s) {
                    //长度发生变化，监听到输入的长度为 editText.getText().length()
                    if (dateList != null) {
                        Log.i("判断", "1");
                        if (report_client_name_et.getText().toString().length() > 0) {
                            Log.i("判断", "2");
                            if (dateList.size() != 0) {
                                Log.i("判断", "3");
                                if (fuzzySearchAdapter.getCount() != 0) {
                                    Log.i("判断", "4");
                                    Log.i("判断", "判断走了");
                                    report_associating_inputing_rv.setVisibility(View.VISIBLE);
                                } else {
                                    Log.i("判断", "a");
                                    report_associating_inputing_rv.setVisibility(View.GONE);
                                    Log.i("判断", "判断走零陵");
                                }
                            } else {
                                Log.i("判断", "b");
                                report_associating_inputing_rv.setVisibility(View.GONE);
                            }
                        } else {
                            Log.i("判断", "c");
                            report_associating_inputing_rv.setVisibility(View.GONE);
                        }
                    } else {
                        Log.i("判断", "d");
                        report_associating_inputing_rv.setVisibility(View.GONE);
                    }
                }
            });


            fuzzySearchAdapter.setItemOnClick(new FuzzySearchAdapter.ItemOnClick() {
                @Override
                public void itemClick(int position, String phone) {
                    Log.i("显示", "模糊：" + phone);
                    for (int i = 0; i < list.size(); i++) {
                        if (phone.equals(list.get(i).getContactsPhone1())) {
                            report_associating_inputing_rv.setVisibility(View.GONE);
                            FinalContents.setClientPhone(list.get(i).getContactsPhone1());
                            FinalContents.setClientName(list.get(i).getCustomerName());
                            FinalContents.setCustomerID(list.get(i).getId());
                            customerID = list.get(i).getId();
                            if (!FinalContents.getClientName().equals("")) {
                                report_client_name_et.setText(FinalContents.getClientName());
                                report_client_phone.setText(FinalContents.getClientPhone());
                            }
                            break;
                        }
                    }
                    report_associating_inputing_rv.setVisibility(View.GONE);
                }
            });
        } else {
            report_associating_inputing_rv.setVisibility(View.GONE);
        }
    }

    private void initReport() {

        if (report_client_name_et.getText().toString().equals("")) {
            ToastUtil.showLongToast(this, "请选择客户");
            return;
        }

        if (project_name.getText().toString().equals("")) {
            ToastUtil.showLongToast(this, "请选择项目");
            return;
        }

        if (!MatcherUtils.isMobile(report_client_phone.getText().toString())) {
            ToastUtil.showLongToast(this, "请输入正确的手机号");
            return;
        }

        if (FinalContents.getClientPhone().equals(report_client_phone.getText().toString()) && FinalContents.getClientName().equals(report_client_name_et.getText().toString())) {
            customerID = FinalContents.getCustomerID();
        } else {
            customerID = "";
        }
        FinalContents.setCustomerID(customerID);
        FinalContents.setClientPhone(report_client_phone.getText().toString());
        FinalContents.setClientName(report_client_name_et.getText().toString());

        Log.i("报备", "客户" + FinalContents.getCustomerID());

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<ChangePhoneBean> userMessage = fzbInterface.getReportPostBean(FinalContents.getCustomerID(), procuctType, areaSection, fitmentState, ffProjectTrait, price_start.getText().toString(), price_end.getText().toString(), FinalContents.getProjectID(), "", FinalContents.getGuideRuleId(), isIsland, IDcard.getText().toString(), report_remarks.getText().toString(), timeStart, timeEnd, FinalContents.getUserID(), FinalContents.getClientName(), FinalContents.getClientPhone());
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ChangePhoneBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(final ChangePhoneBean changePhoneBean) {
                        Log.i("报备", "客户" + FinalContents.getCustomerID());
                        Log.i("报备", "" + FinalContents.getProjectID());
                        if (changePhoneBean.getData().getStatus().equals("1")) {//报备成功
                            ToastUtil.showLongToast(ReportActivity.this, changePhoneBean.getData().getMessage());
                            finish();
                        } else if (changePhoneBean.getData().getStatus().equals("2")) {//已有报备
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(ReportActivity.this);
                            View inflate = LayoutInflater.from(ReportActivity.this).inflate(R.layout.binding_report, null, false);
                            builder1.setView(inflate);
                            final AlertDialog show = builder1.show();
                            show.getWindow().setBackgroundDrawableResource(R.drawable.report_shape);

                            WindowManager m = getWindowManager();
                            Display d = m.getDefaultDisplay();
                            WindowManager.LayoutParams attributes = show.getWindow().getAttributes();
                            attributes.width = (int) (d.getWidth() - 200);
                            show.getWindow().setAttributes(attributes);
                            TextView report_binding_title = inflate.findViewById(R.id.report_binding_title);
                            TextView report_binding_confirm_tv = inflate.findViewById(R.id.report_binding_confirm_tv);
                            TextView report_binding_cancel_tv = inflate.findViewById(R.id.report_binding_cancel_tv);
                            RelativeLayout report_binding_cancel = inflate.findViewById(R.id.report_binding_cancel);
                            RelativeLayout report_binding_confirm = inflate.findViewById(R.id.report_binding_confirm);
                            report_binding_title.setText(changePhoneBean.getData().getMessage());//内容
                            report_binding_confirm_tv.setText("前去看看");
                            report_binding_cancel_tv.setText("我知道了");
                            report_binding_cancel.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    finish();
                                    show.dismiss();
                                }
                            });
                            report_binding_confirm.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(ReportActivity.this, ReviewTheSuccessActivity.class);
//                                    FinalContents.setCustomerID(rows.get(position).getCustomerId());
                                    FinalContents.setPreparationId(changePhoneBean.getData().getBusiness());
                                    startActivity(intent);
                                    finish();
                                    show.dismiss();
                                }
                            });
                        } else if (changePhoneBean.getData().getStatus().equals("3")) {//提示信息

                            AlertDialog.Builder builder1 = new AlertDialog.Builder(ReportActivity.this);
                            View inflate = LayoutInflater.from(ReportActivity.this).inflate(R.layout.binding_report, null, false);
                            builder1.setView(inflate);
                            final AlertDialog show = builder1.show();
                            show.getWindow().setBackgroundDrawableResource(R.drawable.report_shape);

                            WindowManager m = getWindowManager();
                            Display d = m.getDefaultDisplay();
                            WindowManager.LayoutParams attributes = show.getWindow().getAttributes();
                            attributes.width = (int)(d.getWidth() - 200);
                            show.getWindow().setAttributes(attributes);
                            TextView report_binding_title = inflate.findViewById(R.id.report_binding_title);
                            RelativeLayout report_binding_cancel = inflate.findViewById(R.id.report_binding_cancel);
                            TextView report_binding_cancel_tv = inflate.findViewById(R.id.report_binding_cancel_tv);
                            RelativeLayout report_binding_confirm = inflate.findViewById(R.id.report_binding_confirm);
                            LinearLayout report_binding_confirm_LinearLayout = inflate.findViewById(R.id.report_binding_confirm_LinearLayout);
                            report_binding_title.setText(changePhoneBean.getData().getMessage());//内容
                            report_binding_confirm.setVisibility(View.GONE);
                            report_binding_confirm_LinearLayout.setVisibility(View.GONE);
                            report_binding_cancel_tv.setText("我知道了");
                            report_binding_cancel.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    finish();
                                    show.dismiss();
                                }
                            });

                        } else if (changePhoneBean.getData().getStatus().equals("4")) {//联系管理员(前去致电)

                            AlertDialog.Builder builder1 = new AlertDialog.Builder(ReportActivity.this);
                            View inflate = LayoutInflater.from(ReportActivity.this).inflate(R.layout.binding_report, null, false);
                            builder1.setView(inflate);
                            final AlertDialog show = builder1.show();
                            show.getWindow().setBackgroundDrawableResource(R.drawable.report_shape);

                            WindowManager m = getWindowManager();
                            Display d = m.getDefaultDisplay();
                            WindowManager.LayoutParams attributes = show.getWindow().getAttributes();
                            attributes.width = (int)(d.getWidth() - 200);
                            show.getWindow().setAttributes(attributes);
                            TextView report_binding_title = inflate.findViewById(R.id.report_binding_title);
                            TextView report_binding_confirm_tv = inflate.findViewById(R.id.report_binding_confirm_tv);
                            TextView report_binding_cancel_tv = inflate.findViewById(R.id.report_binding_cancel_tv);
                            RelativeLayout report_binding_cancel = inflate.findViewById(R.id.report_binding_cancel);
                            RelativeLayout report_binding_confirm = inflate.findViewById(R.id.report_binding_confirm);
                            report_binding_title.setText(changePhoneBean.getData().getMessage());//内容
                            report_binding_confirm_tv.setText("前去致电");
                            report_binding_cancel_tv.setText("我知道了");
                            report_binding_cancel.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    finish();
                                    show.dismiss();
                                }
                            });
                            report_binding_confirm.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + changePhoneBean.getData().getBusiness()));//跳转到拨号界面，同时传递电话号码
                                    startActivity(dialIntent);
                                    finish();
                                    show.dismiss();
                                }
                            });

                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("wsw", "返回的数据" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private List<ItemEntity> fillData(List<ClientBean.DataBean> list) {
        List<ItemEntity> sortList = new ArrayList<>();
        for (ClientBean.DataBean item : list) {
            String letter;
            //汉字转换成拼音
            List<String> pinyinList = PinyinUtil.getPinYinList(item.getCustomerName() + "@" + item.getContactsPhone1());
            if (pinyinList != null && !pinyinList.isEmpty()) {
                // A-Z导航
                String letters = pinyinList.get(0).substring(0, 1).toUpperCase();
                // 正则表达式，判断首字母是否是英文字母
                if (letters.matches("[A-Z]")) {
                    letter = letters.toUpperCase();
                } else {
                    letter = "#";
                }
            } else {
                letter = "#";
            }
            sortList.add(new ItemEntity(item.getCustomerName() + "@" + item.getContactsPhone1(), letter, pinyinList));
        }
        return sortList;

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }

    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);

            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("报备", "恢复");
        if (FinalContents.getProjectName().equals("")) {
        } else {
            initIdNumber();
            if (FinalContents.getProjectType().equals("1")) {
                report_issure.setVisibility(View.GONE);
            } else if (FinalContents.getProjectType().equals("2") || FinalContents.getProjectType().equals("3")) {
                report_issure.setVisibility(View.VISIBLE);
            } else {
                report_issure.setVisibility(View.GONE);
            }
            if (FinalContents.getCustomerID().equals("")) {
                report_associating_inputing_rv.setVisibility(View.VISIBLE);
            } else {
                report_associating_inputing_rv.setVisibility(View.GONE);
            }
            if (FinalContents.getProjectID().equals("")) {
                report_associating_inputing_rv.setVisibility(View.VISIBLE);
            } else {
                report_associating_inputing_rv.setVisibility(View.GONE);
            }
        }
        if (FinalContents.isChecked()) {
            if (!FinalContents.getClientName().equals("")) {
                report_client_name_et.setText(FinalContents.getClientName());
                report_client_phone.setText(FinalContents.getClientPhone());
            }
            report_associating_inputing_rv.setVisibility(View.GONE);
        } else {
            project_name.setText("");
        }
        if (FinalContents.isChecked2()) {
            if (FinalContents.getProjectType().equals("1")) {
                report_issure.setVisibility(View.GONE);
            } else if (FinalContents.getProjectType().equals("2") || FinalContents.getProjectType().equals("3")) {
                report_issure.setVisibility(View.VISIBLE);
            } else {
                report_issure.setVisibility(View.GONE);
            }
            project_name.setText(FinalContents.getProjectName());
        } else {
            project_name.setText("");
        }
        initAssociatingInputing();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (CityContents.getIsReport().equals("")) {
            Log.i("数据", "1走一走:" + FinalContents.getProjectType());
            FinalContents.setProjectType("");
            FinalContents.setProjectName("");
            FinalContents.setGuideRuleId("");
            FinalContents.setProjectID("");
        }
        FinalContents.setClientName("");
        FinalContents.setClientPhone("");
        FinalContents.setCustomerID("");
        FinalContents.setProjectSearchID("");
        FinalContents.setNUM("");
        FinalContents.setProject("");
    }
}
