package com.xcy.fzbcity.project_side.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.text.InputFilter;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.adapter.ConfessAdapter;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.database.TradeAuditBean;
import com.xcy.fzbcity.all.modle.BrokerBean;
import com.xcy.fzbcity.all.modle.DictListBean;
import com.xcy.fzbcity.all.modle.FindTradeBean;
import com.xcy.fzbcity.all.modle.TradeSaveBean;
import com.xcy.fzbcity.all.persente.MyLinearLayoutManager;
import com.xcy.fzbcity.all.persente.SingleClick;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.CommonUtil;
import com.xcy.fzbcity.all.utils.GetInt;
import com.xcy.fzbcity.all.utils.KeyUtils;
import com.xcy.fzbcity.all.utils.MatcherUtils;
import com.xcy.fzbcity.all.utils.MoneyValueFilter;
import com.xcy.fzbcity.all.utils.ToastUtil;
import com.xcy.fzbcity.project_side.adapter.TimeRangeAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import top.defaults.view.DateTimePickerView;

//TODO 填写成交信息
public class VisitingScheduleActivity extends AppCompatActivity implements View.OnClickListener {

    RelativeLayout fill_in_transaction_information_return;
    Button fill_in_transaction_information_btn;
    RadioGroup fill_in_transaction_information_rg;
    RadioButton fill_in_transaction_information_rb1;
    RadioButton fill_in_transaction_information_rb2;

    TextView fill_in_transaction_information_title;

    TextView project_type;
    TextView project_relation;
    TextView house_type;
    TextView payment_way;
    TextView project_time;
    TextView project_brokerage;

    EditText fang_hao_et1;
    EditText fang_hao_et2;
    EditText fang_hao_et3;


    RelativeLayout fill_in_transaction_information_rl1;
    RelativeLayout fill_in_transaction_information_rl2;
    RelativeLayout fill_in_transaction_information_rl3;
    RelativeLayout fill_in_transaction_information_rl4;
    RelativeLayout fill_in_transaction_information_rl5;
    RelativeLayout fill_in_transaction_information_rl6;

    String type = "";

    RelativeLayout transition_layout;
    RecyclerView transition_recycler;


    boolean whether = true;
    private String apartment = "";  //  TODO    户型
    private EditText fill_in_transaction_information_et1;
    private EditText fill_in_transaction_information_et2;
    private TextView fill_in_transaction_information_et6;
    private EditText fill_in_transaction_information_et5;
    private EditText fill_in_transaction_information_et4;
    private EditText fill_in_transaction_information_et3;
    private String gender = "";     //  TODO    性别
    private String sum = "";
    private String projecttype;
    private RecyclerView fill_in_transaction_information_rv;
    private TextView fill_in_transaction_information_tishi;
    private String str = "";
    String id = "";
    int ifnum1 = 0;
    int ifnum2 = 0;
    int ifnum3 = 0;
    int ifnum4 = 0;
    int ifnum5 = 0;

    int isnum1 = 0;
    private int year;
    private int month;
    private int dayOfMonth;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visiting_schedule);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);      //  TODO    始终竖屏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().setStatusBarColor(Color.parseColor("#ff546da6"));
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

        fill_in_transaction_information_return = findViewById(R.id.fill_in_transaction_information_return);
        fill_in_transaction_information_btn = findViewById(R.id.fill_in_transaction_information_btn);
        fill_in_transaction_information_rg = findViewById(R.id.fill_in_transaction_information_rg);
        fill_in_transaction_information_rb1 = findViewById(R.id.fill_in_transaction_information_rb1);
        fill_in_transaction_information_rb2 = findViewById(R.id.fill_in_transaction_information_rb2);

        fill_in_transaction_information_title = findViewById(R.id.fill_in_transaction_information_title);

        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        project_type = findViewById(R.id.project_type);
        project_relation = findViewById(R.id.project_relation);
        house_type = findViewById(R.id.house_type);
        payment_way = findViewById(R.id.payment_way);
        project_time = findViewById(R.id.project_time);
        project_brokerage = findViewById(R.id.project_brokerage);

        transition_layout = findViewById(R.id.transition_layout);
        transition_recycler = findViewById(R.id.transition_recycler);
        transition_layout.setOnClickListener(this);


        fill_in_transaction_information_rl1 = findViewById(R.id.fill_in_transaction_information_rl1);
        fill_in_transaction_information_rl2 = findViewById(R.id.fill_in_transaction_information_rl2);
        fill_in_transaction_information_rl3 = findViewById(R.id.fill_in_transaction_information_rl3);
        fill_in_transaction_information_rl4 = findViewById(R.id.fill_in_transaction_information_rl4);
        fill_in_transaction_information_rl5 = findViewById(R.id.fill_in_transaction_information_rl5);
        fill_in_transaction_information_rl6 = findViewById(R.id.fill_in_transaction_information_rl6);

        fill_in_transaction_information_et1 = findViewById(R.id.fill_in_transaction_information_et1);
        fill_in_transaction_information_et2 = findViewById(R.id.fill_in_transaction_information_et2);
        fill_in_transaction_information_et3 = findViewById(R.id.fill_in_transaction_information_et3);
        fill_in_transaction_information_et4 = findViewById(R.id.fill_in_transaction_information_et4);
        fill_in_transaction_information_et5 = findViewById(R.id.fill_in_transaction_information_et5);
        fill_in_transaction_information_et6 = findViewById(R.id.fill_in_transaction_information_et6);

        fill_in_transaction_information_tishi = findViewById(R.id.fill_in_transaction_information_tishi);

        fill_in_transaction_information_rv = findViewById(R.id.fill_in_transaction_information_rv);

        //默认两位小数
        fill_in_transaction_information_et4.setFilters(new InputFilter[]{new MoneyValueFilter()});
        fill_in_transaction_information_et5.setFilters(new InputFilter[]{new MoneyValueFilter()});

        fang_hao_et3 = findViewById(R.id.fang_hao_et3);
        fang_hao_et2 = findViewById(R.id.fang_hao_et2);
        fang_hao_et1 = findViewById(R.id.fang_hao_et1);

        fang_hao_et1.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        fang_hao_et2.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        fang_hao_et3.setInputType(EditorInfo.TYPE_CLASS_PHONE);

        fill_in_transaction_information_return.setOnClickListener(this);
        fill_in_transaction_information_btn.setOnClickListener(this);
        fill_in_transaction_information_rl1.setOnClickListener(this);
        fill_in_transaction_information_rl2.setOnClickListener(this);
        fill_in_transaction_information_rl3.setOnClickListener(this);
        fill_in_transaction_information_rl4.setOnClickListener(this);
        fill_in_transaction_information_rl5.setOnClickListener(this);
        fill_in_transaction_information_rl6.setOnClickListener(this);


        fill_in_transaction_information_et5.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                //当actionId == XX_SEND 或者 XX_DONE时都触发
                //或者event.getKeyCode == ENTER 且 event.getAction == ACTION_DOWN时也触发
                //注意，这是一定要判断event != null。因为在某些输入法上会返回null。
                if (actionId == EditorInfo.IME_ACTION_SEND || actionId == EditorInfo.IME_ACTION_DONE || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                    //处理事件
                    KeyUtils.hideKeyboard(fill_in_transaction_information_et5);
                    String s1 = fill_in_transaction_information_et4.getText().toString();
                    String s2 = fill_in_transaction_information_et5.getText().toString();
                    if (s1.equals("") || s2.equals("")) {
                    } else {
                        double area = Double.parseDouble(s1);
                        double price = Double.parseDouble(s2);
                        sum = GetInt.getInt((area * price));
                        str = sum;
                        fill_in_transaction_information_et6.setText(str + "元");
                        fill_in_transaction_information_tishi.setVisibility(View.GONE);
                        return true;
                    }
                }
                return false;
            }
        });


        fill_in_transaction_information_et4.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (fill_in_transaction_information_et5.getText().toString().equals("")) {
                } else {
                    //当actionId == XX_SEND 或者 XX_DONE时都触发
                    //或者event.getKeyCode == ENTER 且 event.getAction == ACTION_DOWN时也触发
                    //注意，这是一定要判断event != null。因为在某些输入法上会返回null。
                    if (actionId == EditorInfo.IME_ACTION_SEND || actionId == EditorInfo.IME_ACTION_DONE || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                        //处理事件
                        KeyUtils.hideKeyboard(fill_in_transaction_information_et5);
                        String s1 = fill_in_transaction_information_et4.getText().toString();
                        String s2 = fill_in_transaction_information_et5.getText().toString();
                        if (s1.equals("") || s2.equals("")) {
                        } else {
                            double area = Double.parseDouble(s1);
                            double price = Double.parseDouble(s2);
                            sum = GetInt.getInt((area * price));
                            str = sum;
                            fill_in_transaction_information_et6.setText(str + "元");
                            fill_in_transaction_information_tishi.setVisibility(View.GONE);
                            return true;
                        }
                    }
                }
                return false;
            }
        });


        if (FinalContents.getTiaodan().equals("调单")) {
            initFindAdjustApply();
            fill_in_transaction_information_et4.setOnEditorActionListener(new TextView.OnEditorActionListener() {

                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    //当actionId == XX_SEND 或者 XX_DONE时都触发
                    //或者event.getKeyCode == ENTER 且 event.getAction == ACTION_DOWN时也触发
                    //注意，这是一定要判断event != null。因为在某些输入法上会返回null。
                    if (actionId == EditorInfo.IME_ACTION_SEND || actionId == EditorInfo.IME_ACTION_DONE || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                        //处理事件
                        KeyUtils.hideKeyboard(fill_in_transaction_information_et5);
                        String s1 = fill_in_transaction_information_et4.getText().toString();
                        String s2 = fill_in_transaction_information_et5.getText().toString();
                        if (s1.equals("") || s2.equals("")) {
                        } else {
                            double area = Double.parseDouble(s1);
                            double price = Double.parseDouble(s2);
                            sum = GetInt.getInt((area * price));
                            str = sum;
                            fill_in_transaction_information_et6.setText(str + "元");
                            fill_in_transaction_information_tishi.setVisibility(View.GONE);
                            return true;
                        }
                    }
                    return false;
                }
            });
        } else if (FinalContents.getTiaodan().equals("成交")) {
            initFindTrade();
        }

        if (fill_in_transaction_information_et6.getText().toString().equals("")) {
        } else {
            fill_in_transaction_information_tishi.setVisibility(View.GONE);
        }


        fill_in_transaction_information_et4.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // 获得焦点
                } else {
                    // 失去焦点
                    hideInput();
                    String s1 = fill_in_transaction_information_et4.getText().toString();
                    String s2 = fill_in_transaction_information_et5.getText().toString();
                    if (s1.equals("") || s2.equals("")) {
                    } else {
                        double area = Double.parseDouble(s1);
                        double price = Double.parseDouble(s2);
                        sum = GetInt.getInt((area * price));
                        str = sum;
                        fill_in_transaction_information_et6.setText(str + "元");
                        fill_in_transaction_information_tishi.setVisibility(View.GONE);
                    }
                }
            }


        });

        fill_in_transaction_information_et5.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (hasFocus) {
                    // 获得焦点
                } else {
                    // 失去焦点
                    hideInput();
                    String s1 = fill_in_transaction_information_et4.getText().toString();
                    String s2 = fill_in_transaction_information_et5.getText().toString();
                    if (s1.equals("") || s2.equals("")) {
                    } else {
                        double area = Double.parseDouble(s1);
                        double price = Double.parseDouble(s2);
                        sum = GetInt.getInt((area * price));
                        str = sum;
                        fill_in_transaction_information_et6.setText(str + "元");
                        fill_in_transaction_information_tishi.setVisibility(View.GONE);
                    }
                }
            }


        });
    }

    private void initselect() {
        if (house_type.getText().toString().equals("不限")) {
            apartment = "0";
        } else if (house_type.getText().toString().equals("一室")) {
            apartment = "1";
        } else if (house_type.getText().toString().equals("二室")) {
            apartment = "2";
        } else if (house_type.getText().toString().equals("三室")) {
            apartment = "3";
        } else if (house_type.getText().toString().equals("四室")) {
            apartment = "4";
        } else if (house_type.getText().toString().equals("五室")) {
            apartment = "5";
        } else if (house_type.getText().toString().equals("五室以上")) {
            apartment = "6";
        }

        if (fill_in_transaction_information_rb1.isChecked()) {
            gender = "男";
        } else if (fill_in_transaction_information_rb2.isChecked()) {
            gender = "女";
        }
        if (project_type.getText().toString().equals("住宅")) {
            projecttype = "1";
        } else if (project_type.getText().toString().equals("公寓")) {
            projecttype = "2";
        } else if (project_type.getText().toString().equals("写字楼")) {
            projecttype = "3";
        } else if (project_type.getText().toString().equals("商铺")) {
            projecttype = "4";
        } else if (project_type.getText().toString().equals("别墅")) {
            projecttype = "5";
        }
    }

    // TODO 添加成交信息数据
    private void initTradeSave() {
        initselect();

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<TradeSaveBean> userMessage = fzbInterface.getTradeSave(id, FinalContents.getProjectID(), "", FinalContents.getPreparationId(), FinalContents.getCustomerID(), fang_hao_et1.getText().toString() + "栋" + fang_hao_et2.getText().toString() + "单元" + fang_hao_et3.getText().toString() + "室", apartment, fill_in_transaction_information_et4.getText().toString(), fill_in_transaction_information_et5.getText().toString(), str, payment_way.getText().toString(), FinalContents.getCommissionId(), projecttype, gender, project_relation.getText().toString(), fill_in_transaction_information_et1.getText().toString(), fill_in_transaction_information_et2.getText().toString(), fill_in_transaction_information_et3.getText().toString(), FinalContents.getUserID(), project_time.getText().toString());
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TradeSaveBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(TradeSaveBean tradeSaveBean) {
                        if (FinalContents.getCommissionId().equals("")) {
                            ToastUtil.showToast(VisitingScheduleActivity.this, "请选择佣金");
                            isnum1 = 0;
                        } else {
                            ToastUtil.showToast(VisitingScheduleActivity.this, tradeSaveBean.getData().getMessage());
                            finish();
                            isnum1 = 0;
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("成交信息", "错误" + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        if (FinalContents.getCommissionId().equals("")) {
            ToastUtil.showToast(VisitingScheduleActivity.this, "请选择佣金");
        } else {
            FinalContents.setTiaozhuang("成交");
        }
    }

    @SingleClick(1000)
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
//            TODO 返回上一层
            case R.id.fill_in_transaction_information_return:
                finish();
                break;
            //            TODO 提交
            case R.id.fill_in_transaction_information_btn:
                if (!MatcherUtils.isMobile(fill_in_transaction_information_et2.getText().toString())) {
                    ToastUtil.showToast(this, "请输入正确的手机号");
                    return;
                } else {

                    if (project_type.getText().toString().equals("")) {
                        ToastUtil.showToast(this, "请选择产品类型");
                        return;
                    }

                    if (fill_in_transaction_information_et1.getText().toString().equals("")) {
                        ToastUtil.showToast(this, "请输入成交客户姓名");
                        return;
                    }

                    if (fill_in_transaction_information_et2.getText().toString().equals("")) {
                        ToastUtil.showToast(this, "请输入成交客户电话");
                        return;
                    }

                    if (fill_in_transaction_information_et3.getText().toString().equals("")) {
                        ToastUtil.showToast(this, "请输入成交客户身份证号");
                        return;
                    }

                    if (project_relation.getText().toString().equals("")) {
                        ToastUtil.showToast(this, "请选择成交客户与报备客户关系");
                        return;
                    }

                    if (fang_hao_et1.getText().toString().equals("") || fang_hao_et2.getText().toString().equals("") || fang_hao_et3.getText().toString().equals("")) {
                        ToastUtil.showToast(this, "请输入成交房号");
                        return;
                    }

                    if (house_type.getText().toString().equals("")) {
                        ToastUtil.showToast(this, "请选择成交户型");
                        return;
                    }

                    if (fill_in_transaction_information_et4.getText().toString().equals("")) {
                        ToastUtil.showToast(this, "请输入成交面积");
                        return;
                    }

                    if (fill_in_transaction_information_et5.getText().toString().equals("")) {
                        ToastUtil.showToast(this, "请填写成交单价");
                        return;
                    }

                    if (str.equals("")) {
                        ToastUtil.showToast(this, "请填写成交单价并确认");
                        return;
                    }

                    if (payment_way.getText().toString().equals("")) {
                        ToastUtil.showToast(this, "请选择付款方式");
                        return;
                    }

                    if (project_time.getText().toString().equals("")) {
                        ToastUtil.showToast(this, "请选择成交时间");
                        return;
                    }

                    if (isnum1 == 0) {
                        isnum1 = 1;
                        initTradeSave();
                    }

                }


                break;
            //            TODO 产品类型
            case R.id.fill_in_transaction_information_rl1:
                if (ifnum1 == 0) {
                    ifnum1 = 1;
                    type = "procuct_type";
                    initData(project_type);
                    ifnum1 = 0;
                }

                break;
            //            TODO 成交客户与报备客户关系
            case R.id.fill_in_transaction_information_rl2:

                if (ifnum2 == 0) {
                    ifnum2 = 1;
                    List<String> relation = new ArrayList<>();
                    relation.add("本人");
                    relation.add("父母");
                    relation.add("子女");
                    relation.add("配偶");
                    relation.add("其他");
                    initSelect(relation, project_relation);
                    ifnum2 = 0;
                }


                break;
            //            TODO 成交户型
            case R.id.fill_in_transaction_information_rl3:

                if (ifnum3 == 0) {
                    ifnum3 = 1;
                    type = "apartment";
                    initData(house_type);
                    ifnum3 = 0;
                }


                break;
            //            TODO 付款方式
            case R.id.fill_in_transaction_information_rl4:

                if (ifnum4 == 0) {
                    ifnum4 = 1;
                    List<String> payment = new ArrayList<>();
                    payment.add("一次性");
                    payment.add("按揭");
                    payment.add("分期");
                    initSelect(payment, payment_way);
                    ifnum4 = 0;
                }


                break;
            //            TODO 成交时间
            case R.id.fill_in_transaction_information_rl5:

                hideInput();
                Calendar selectedDate = Calendar.getInstance();//系统当前时间
                Calendar startDate = Calendar.getInstance();
                startDate.set(year-2, month, dayOfMonth);
                final Calendar endDate = Calendar.getInstance();
                endDate.set(year, month, dayOfMonth);
                TimePickerView pvTime = new TimePickerBuilder(VisitingScheduleActivity.this, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        project_time.setText(getTime1(date));
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


                break;
            //            TODO 佣金
            case R.id.fill_in_transaction_information_rl6:
                if (whether) {
                    transition_layout.setVisibility(View.VISIBLE);
                    initTimeData();
                    whether = false;
                } else {
                    transition_layout.setVisibility(View.GONE);
                    whether = true;
                }
                break;
            //            TODO 佣金选择
            case R.id.transition_layout:
                transition_layout.setVisibility(View.GONE);
                break;

        }
    }

    private void initTimeData() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<BrokerBean> userMessage = fzbInterface.getTimeRange(project_time.getText().toString(), FinalContents.getProjectID(), FinalContents.getJJrID());
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BrokerBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(final BrokerBean brokerBean) {
                        MyLinearLayoutManager layoutManager = new MyLinearLayoutManager(VisitingScheduleActivity.this);
                        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        layoutManager.setScrollEnabled(false);
                        transition_recycler.setLayoutManager(layoutManager);
                        TimeRangeAdapter timeRangeAdapter = new TimeRangeAdapter(brokerBean.getData());
                        transition_recycler.setNestedScrollingEnabled(false);
                        transition_recycler.setAdapter(timeRangeAdapter);
                        timeRangeAdapter.setOnItemClickListener(new TimeRangeAdapter.OnItemClickLisenter() {
                            @Override
                            public void onItemClick(int postion) {
                                project_brokerage.setText(brokerBean.getData().get(postion).getCommissionFormat());
                                FinalContents.setCommissionId(brokerBean.getData().get(postion).getId());
                                transition_layout.setVisibility(View.GONE);
                            }
                        });
                        timeRangeAdapter.notifyDataSetChanged();
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

    private void initFindTrade() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<FindTradeBean> userMessage = fzbInterface.getFindTrade(FinalContents.getPreparationId());
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FindTradeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(FindTradeBean findTradeBean) {
                        MyLinearLayoutManager layoutManager = new MyLinearLayoutManager(VisitingScheduleActivity.this);
                        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        layoutManager.setScrollEnabled(false);
                        fill_in_transaction_information_rv.setLayoutManager(layoutManager);
                        ConfessAdapter confessAdapter = new ConfessAdapter(findTradeBean.getData().getShowData());
                        fill_in_transaction_information_rv.setNestedScrollingEnabled(false);
                        fill_in_transaction_information_rv.setAdapter(confessAdapter);
                        fill_in_transaction_information_et1.setText(findTradeBean.getData().getFfServerEarnestMoney().getFullName());
                        fill_in_transaction_information_et2.setText(findTradeBean.getData().getFfServerEarnestMoney().getPhone());
                        fill_in_transaction_information_et3.setText(findTradeBean.getData().getFfServerEarnestMoney().getIdNumber());
                        FinalContents.setCustomerID(findTradeBean.getData().getFfServerEarnestMoney().getCustomerId());
                        project_relation.setText(findTradeBean.getData().getFfServerEarnestMoney().getRelation());
                        if (findTradeBean.getData().getFfServerEarnestMoney().getGender().equals("男")) {
                            fill_in_transaction_information_rb1.setChecked(true);
                        } else if (findTradeBean.getData().getFfServerEarnestMoney().getGender().equals("女")) {
                            fill_in_transaction_information_rb2.setChecked(true);
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

    private void initFindAdjustApply() {

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<TradeAuditBean> userMessage = fzbInterface.getTradeAuditBean(FinalContents.getUserID(), FinalContents.getPreparationId(), "1");
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TradeAuditBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(TradeAuditBean tradeAuditBean) {

                        fill_in_transaction_information_et1.setText(tradeAuditBean.getData().getFullName());
                        fill_in_transaction_information_et2.setText(tradeAuditBean.getData().getPhone());
                        fill_in_transaction_information_et3.setText(tradeAuditBean.getData().getIdNumber());
                        project_relation.setText(tradeAuditBean.getData().getRelation());
                        if (tradeAuditBean.getData().getGender().equals("男")) {
                            fill_in_transaction_information_rb1.setChecked(true);
                        } else if (tradeAuditBean.getData().getGender().equals("女")) {
                            fill_in_transaction_information_rb2.setChecked(true);
                        }

                        if (tradeAuditBean.getData().getRoomNumber().equals("")) {
                        } else {
                            String roomNumber = tradeAuditBean.getData().getRoomNumber();
                            String substring1 = roomNumber.substring(0, roomNumber.indexOf("栋"));
                            String substring2 = roomNumber.substring(roomNumber.indexOf("栋") + 1, roomNumber.indexOf("单"));
                            String substring3 = roomNumber.substring(roomNumber.indexOf("元") + 1, roomNumber.indexOf("室"));

                            fang_hao_et1.setText(substring1);
                            fang_hao_et2.setText(substring2);
                            fang_hao_et3.setText(substring3);
                        }

                        project_relation.setText(tradeAuditBean.getData().getRelation());
                        if (tradeAuditBean.getData().getProcuctType().equals("1")) {
                            project_type.setText("住宅");
                        } else if (tradeAuditBean.getData().getProcuctType().equals("2")) {
                            project_type.setText("公寓");
                        } else if (tradeAuditBean.getData().getProcuctType().equals("3")) {
                            project_type.setText("写字间");
                        } else if (tradeAuditBean.getData().getProcuctType().equals("4")) {
                            project_type.setText("商铺");
                        } else if (tradeAuditBean.getData().getProcuctType().equals("5")) {
                            project_type.setText("别墅");
                        }

                        if (tradeAuditBean.getData().getApartment().equals("1")) {
                            house_type.setText("一室");
                        } else if (tradeAuditBean.getData().getApartment().equals("2")) {
                            house_type.setText("二室");
                        } else if (tradeAuditBean.getData().getApartment().equals("3")) {
                            house_type.setText("三室");
                        } else if (tradeAuditBean.getData().getApartment().equals("4")) {
                            house_type.setText("四室");
                        } else if (tradeAuditBean.getData().getApartment().equals("5")) {
                            house_type.setText("五室");
                        } else if (tradeAuditBean.getData().getApartment().equals("6")) {
                            house_type.setText("五室以上");
                        } else if (tradeAuditBean.getData().getApartment().equals("0")) {
                            house_type.setText("不限");
                        }

                        fill_in_transaction_information_et4.setText(tradeAuditBean.getData().getArea());
                        fill_in_transaction_information_et5.setText(tradeAuditBean.getData().getPrice());
                        fill_in_transaction_information_et6.setText(tradeAuditBean.getData().getTotalPrice() + "元");
                        sum = tradeAuditBean.getData().getTotalPrice();
                        str = tradeAuditBean.getData().getTotalPrice();
                        payment_way.setText(tradeAuditBean.getData().getPaymentMethod());
                        project_time.setText(tradeAuditBean.getData().getTradeDate());
                        id = tradeAuditBean.getData().getId();

                        if (tradeAuditBean.getData().getTotalPrice().equals("")) {
                        } else {
                            fill_in_transaction_information_tishi.setVisibility(View.GONE);
                        }

                        project_brokerage.setText(tradeAuditBean.getData().getCommission().getCommissionFormat());

                        FinalContents.setPreparationId(tradeAuditBean.getData().getPreparationId());
                        FinalContents.setCommissionId(tradeAuditBean.getData().getCommissionId());
                        FinalContents.setCustomerID(tradeAuditBean.getData().getCustomerId());
                        FinalContents.setProjectID(tradeAuditBean.getData().getProjectId());
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

    private void initData(final TextView textView) {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<DictListBean> userMessage = fzbInterface.getDictList(FinalContents.getUserID(), type);
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DictListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(DictListBean dictListBean) {
                        List<String> list = new ArrayList<>();
                        List<DictListBean.DataBean> dictListBeanList = dictListBean.getData();
                        for (int i = 0; i < dictListBeanList.size(); i++) {
                            list.add(dictListBeanList.get(i).getName());
                        }
                        initSelect(list, textView);
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

    //选择器
    private void initSelect(final List<String> list, final TextView textView) {
        //      监听选中
        hideInput();
        OptionsPickerView pvOptions = new OptionsPickerBuilder(VisitingScheduleActivity.this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //               返回的分别是三个级别的选中位置
                //              展示选中数据
                textView.setText(list.get(options1));
            }
        })
                .setSelectOptions(0)//设置选择第一个
                .setOutSideCancelable(false)//点击背的地方不消失
                .build();//创建
        //      把数据绑定到控件上面
        pvOptions.setPicker(list);
        //      展示
        pvOptions.show();
    }

    /**
     * 隐藏键盘
     */
    protected void hideInput() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        View v = getWindow().peekDecorView();
        if (null != v) {
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }



    public String getTime1(Date date) {//可根据需要自行截取数据显示
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        return format.format(date);
    }

}
