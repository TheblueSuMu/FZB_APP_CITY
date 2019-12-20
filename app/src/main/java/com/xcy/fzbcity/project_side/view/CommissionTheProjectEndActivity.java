package com.xcy.fzbcity.project_side.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.api.NewlyIncreased;
import com.xcy.fzbcity.all.modle.MoneyCountBean;
import com.xcy.fzbcity.all.modle.ReceivableBean;
import com.xcy.fzbcity.all.persente.MyLinearLayoutManager;
import com.xcy.fzbcity.all.persente.SingleClick;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.CommonUtil;
import com.xcy.fzbcity.all.utils.KeyUtils;
import com.xcy.fzbcity.all.utils.ToastUtil;
import com.xcy.fzbcity.all.view.AllActivity;
import com.xcy.fzbcity.project_side.adapter.TheProjectEndCommissionAdapter;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CommissionTheProjectEndActivity extends AllActivity implements View.OnClickListener {


    RelativeLayout commission_the_project_end_return;
    ImageView commission_the_project_end_screen;
    EditText commission_the_project_end_et;

    TextView commission_the_project_end_tv1;
    TextView commission_the_project_end_tv2;
    TextView commission_the_project_end_tv3;
    TextView commission_the_project_end_tv4;

    LinearLayout commission_the_project_end_l1;
    LinearLayout commission_the_project_end_l2;
    LinearLayout commission_the_project_end_l3;
    LinearLayout commission_the_project_end_l4;
    LinearLayout commission_the_project_end_l5;
    LinearLayout commission_the_project_end_l6;

    RelativeLayout commission_the_project_end_rl;
    RecyclerView commission_the_project_end_rv;

    private TheProjectEndCommissionAdapter adapter;

    String search = "";
    String status = "";
    String projectType = "3";
    String projectId = "";
    String startTime = "";
    String endTime = "";

    private LinearLayout mRlRight;
    private LinearLayout drawer_linear;
    private DrawerLayout mDlMain;

    RadioButton drawer_time_all;
    RadioButton drawer_time_custom;
    RadioButton drawer_status_all;
    RadioButton drawer_status_custom;
    RadioButton drawer_status_back;
    RadioButton drawer_status_settle;

    TextView drawer_start_time;
    TextView drawer_end_time;
    TextView drawer_reset;
    TextView drawer_ensure;

    private PtrClassicFrameLayout project_side_commission_the_project_end_ptrclass;
    private int year;
    private int month;
    private int dayOfMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_side_activity_commission_the_project_end);
        init_No_Network();

    }

    private void init_No_Network(){
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
        project_side_commission_the_project_end_ptrclass = findViewById(R.id.project_side_commission_the_project_end_ptrclass);

        commission_the_project_end_return = findViewById(R.id.commission_the_project_end_return);
        commission_the_project_end_screen = findViewById(R.id.commission_the_project_end_screen);
        commission_the_project_end_et = findViewById(R.id.commission_the_project_end_et);
        commission_the_project_end_tv1 = findViewById(R.id.commission_the_project_end_tv1);
        commission_the_project_end_tv2 = findViewById(R.id.commission_the_project_end_tv2);
        commission_the_project_end_tv3 = findViewById(R.id.commission_the_project_end_tv3);
        commission_the_project_end_tv4 = findViewById(R.id.commission_the_project_end_tv4);
        commission_the_project_end_l1 = findViewById(R.id.commission_the_project_end_l1);
        commission_the_project_end_l2 = findViewById(R.id.commission_the_project_end_l2);
        commission_the_project_end_l3 = findViewById(R.id.commission_the_project_end_l3);
        commission_the_project_end_l4 = findViewById(R.id.commission_the_project_end_l4);
        commission_the_project_end_l5 = findViewById(R.id.commission_the_project_end_l5);
        commission_the_project_end_l6 = findViewById(R.id.commission_the_project_end_l6);
        commission_the_project_end_rl = findViewById(R.id.commission_the_project_end_rl);
        commission_the_project_end_rv = findViewById(R.id.commission_the_project_end_rv);

        project_side_commission_the_project_end_ptrclass.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        project_side_commission_the_project_end_ptrclass.refreshComplete();
                        project_side_commission_the_project_end_ptrclass.setLastUpdateTimeKey("2017-2-10");
                        commission_the_project_end_et.setText("");
                        search = commission_the_project_end_et.getText().toString();
                        initData();
                        initDataBean();
                    }
                }, 1000);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });


        // TODO 侧滑菜单
        mDlMain = findViewById(R.id.mDlMain);
        mRlRight = findViewById(R.id.rl_right);
        drawer_linear = findViewById(R.id.drawer_linear);

        drawer_time_all = findViewById(R.id.drawer_time_all);
        drawer_time_custom = findViewById(R.id.drawer_time_custom);
        drawer_status_all = findViewById(R.id.drawer_status_all);
        drawer_status_custom = findViewById(R.id.drawer_status_custom);
        drawer_status_back = findViewById(R.id.drawer_status_back);
        drawer_status_settle = findViewById(R.id.drawer_status_settle);

        drawer_start_time = findViewById(R.id.drawer_start_time);
        drawer_end_time = findViewById(R.id.drawer_end_time);
        drawer_reset = findViewById(R.id.drawer_reset);
        drawer_ensure = findViewById(R.id.drawer_ensure);

        initData();
        initDataBean();

        drawer_time_all.setChecked(true);
        drawer_time_all.setOnClickListener(this);
        drawer_time_custom.setOnClickListener(this);
        drawer_status_all.setOnClickListener(this);
        drawer_status_custom.setOnClickListener(this);
        drawer_status_back.setOnClickListener(this);
        drawer_status_settle.setOnClickListener(this);

        drawer_start_time.setOnClickListener(this);
        drawer_end_time.setOnClickListener(this);
        drawer_reset.setOnClickListener(this);
        drawer_ensure.setOnClickListener(this);

        commission_the_project_end_return.setOnClickListener(this);
        commission_the_project_end_screen.setOnClickListener(this);
        commission_the_project_end_l1.setOnClickListener(this);
        commission_the_project_end_l3.setOnClickListener(this);
        commission_the_project_end_l5.setOnClickListener(this);

        commission_the_project_end_et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                //如果actionId是搜索的id，则进行下一步的操作
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    // 当按了搜索之后关闭软键盘
                    KeyUtils.hideKeyboard(commission_the_project_end_et);
                    search = commission_the_project_end_et.getText().toString();
                    initDataBean();
                }
                return false;    
            }
        });
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        String string = String.format(Locale.getDefault(), "%d.%02d.%02d", year, month, dayOfMonth);
        drawer_start_time.setText("<"+string);
        drawer_end_time.setText("-"+string+" >");
    }

    @SingleClick(1000)
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
//            TODO 返回上一层
            case R.id.commission_the_project_end_return:
                finish();
                break;
//                TODO 右边菜单栏
            case R.id.commission_the_project_end_screen:
                mDlMain.openDrawer(Gravity.RIGHT);
                break;
//                TODO 旅居房产
            case R.id.commission_the_project_end_l1:

                commission_the_project_end_l2.setVisibility(View.VISIBLE);
                commission_the_project_end_l4.setVisibility(View.GONE);
                commission_the_project_end_l6.setVisibility(View.GONE);
                projectType = "3";
                initDataBean();

                break;
//                TODO 海外房产
            case R.id.commission_the_project_end_l3:

                commission_the_project_end_l2.setVisibility(View.GONE);
                commission_the_project_end_l4.setVisibility(View.VISIBLE);
                commission_the_project_end_l6.setVisibility(View.GONE);
                projectType = "2";
                initDataBean();

                break;
//                TODO 城市房产
            case R.id.commission_the_project_end_l5:

                commission_the_project_end_l2.setVisibility(View.GONE);
                commission_the_project_end_l4.setVisibility(View.GONE);
                commission_the_project_end_l6.setVisibility(View.VISIBLE);
                projectType = "1";
                initDataBean();

                break;
            //   TODO 时间选择  全部
            case R.id.drawer_time_all:
                drawer_linear.setVisibility(View.GONE);
                break;
            //   TODO 时间选择  自定义
            case R.id.drawer_time_custom:
                drawer_linear.setVisibility(View.VISIBLE);
                break;
            //   TODO 状态选择  全部
            case R.id.drawer_status_all:
                status = "";
                break;
            //   TODO 状态选择  未结
            case R.id.drawer_status_custom:
                status = "0";
                break;
            //   TODO 状态选择  退还
            case R.id.drawer_status_back:
                status = "2";
                break;
            //   TODO 状态选择  结清
            case R.id.drawer_status_settle:
                status = "1";
                break;
            //   TODO 时间选择  自定义 开始时间
            case R.id.drawer_start_time:
                initDate();
                break;
            //   TODO 时间选择  自定义 结束时间
            case R.id.drawer_end_time:
                initDate();
                break;
            //   TODO 重置
            case R.id.drawer_reset:
                status = "";
                startTime = "";
                endTime = "";
                drawer_status_all.setChecked(true);
                drawer_time_all.setChecked(true);
                drawer_linear.setVisibility(View.GONE);
                break;
            //   TODO 确定
            case R.id.drawer_ensure:
                mDlMain.closeDrawer(Gravity.RIGHT);
                initDataBean();
                break;
        }

    }

    //TODO 我的佣金侧滑菜单时间赋值
    private void initDate(){
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        String string = String.format(Locale.getDefault(), "%d.%02d.%02d", year, month + 1, dayOfMonth);
        drawer_start_time.setText("<"+string);
        drawer_end_time.setText("-"+string+" >");
        startTime = drawer_start_time.getText().toString();
        endTime = drawer_end_time.getText().toString();

        //            TODO 开始时间
        drawer_start_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initTimePickerView1();
            }
        });
        //                TODO 结束时间
        drawer_end_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initTimePickerView2();
            }
        });
    }

    //    TODO 我的佣金 右滑菜单栏 时间选择 开始时间
    private void initTimePickerView1(){
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(year-2, month, dayOfMonth);
        final Calendar endDate = Calendar.getInstance();
        endDate.set(year+1, month, dayOfMonth);
        TimePickerView pvTime = new TimePickerBuilder(CommissionTheProjectEndActivity.this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                drawer_start_time.setText("<"+ getTime2(date));
                startTime = getTime2(date);
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

    //    TODO 我的佣金 右滑菜单栏 时间选择 结束时间
    private void initTimePickerView2(){
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(year-2, month, dayOfMonth);
        final Calendar endDate = Calendar.getInstance();
        endDate.set(year+1, month, dayOfMonth);
        TimePickerView pvTime = new TimePickerBuilder(CommissionTheProjectEndActivity.this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                drawer_end_time.setText("-"+ getTime2(date) +" >");
                endTime = getTime2(date);
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

    //    TODO 我的佣金上半部分数据填充
    private void initData() {

        String ProjectID = "";

        if (FinalContents.getDetails().equals("项目详情")) {
            ProjectID = FinalContents.getProjectID();
        }

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<MoneyCountBean> userMessage = fzbInterface.getMoneyCountList(FinalContents.getUserID(),ProjectID, NewlyIncreased.getYJType(), NewlyIncreased.getYJstartDate(), NewlyIncreased.getYJendDate());
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MoneyCountBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(MoneyCountBean moneyCountBean) {
                        commission_the_project_end_tv1.setText(moneyCountBean.getData().getMoneyData().getReceivableMoney());
                        commission_the_project_end_tv2.setText(moneyCountBean.getData().getMoneyData().getBackMoney());
                        commission_the_project_end_tv3.setText(moneyCountBean.getData().getMoneyData().getInvoiceMoney());
                        commission_the_project_end_tv4.setText(moneyCountBean.getData().getMoneyData().getSurplusMoney());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("列表数据获取错误","错误"+e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //TODO 我的佣金下半部分数据填充
    private void initDataBean() {
        String ProjectID = "";

        if (FinalContents.getDetails().equals("项目详情")) {
            ProjectID = FinalContents.getProjectID();
        }

        if (endTime.equals("") && startTime.equals("")) {
            if (NewlyIncreased.getYJType().equals("3")) {
                endTime = NewlyIncreased.getYJendDate();
                startTime = NewlyIncreased.getYJstartDate();
            }
        }


        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<ReceivableBean> userMessage = fzbInterface.getMoneyList(FinalContents.getUserID(),search,status,projectType,ProjectID,startTime,endTime,"1000",NewlyIncreased.getYJType());
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ReceivableBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(ReceivableBean receivableBean) {
                        if (receivableBean.getCode().equals("1")) {
                            if (receivableBean.getData() != null) {
                                ReceivableBean.DataBean receivableBeanData = receivableBean.getData();
                                List<ReceivableBean.DataBean.RowsBean> rowsBeanList = receivableBeanData.getRows();
                                if (rowsBeanList.size() != 0) {
                                    commission_the_project_end_rl.setVisibility(View.GONE);
                                    //在此处修改布局排列方向
                                    commission_the_project_end_rv.setVisibility(View.VISIBLE);
                                    MyLinearLayoutManager layoutManager = new MyLinearLayoutManager(CommissionTheProjectEndActivity.this);
                                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                                    commission_the_project_end_rv.setLayoutManager(layoutManager);
                                    TheProjectEndCommissionAdapter recyclerAdapter = new TheProjectEndCommissionAdapter(rowsBeanList);
                                    commission_the_project_end_rv.setAdapter(recyclerAdapter);
                                    recyclerAdapter.notifyDataSetChanged();
                                }else {
                                    commission_the_project_end_rl.setVisibility(View.VISIBLE);
                                    commission_the_project_end_rv.setVisibility(View.GONE);
                                }

                            }else {
                                commission_the_project_end_rv.setVisibility(View.GONE);
                                commission_the_project_end_rl.setVisibility(View.VISIBLE);
                            }
                        }else {
                            commission_the_project_end_rv.setVisibility(View.GONE);
                            commission_the_project_end_rl.setVisibility(View.VISIBLE);
                        }
                        hideInput();
                    }

                    @Override
                    public void onError(Throwable e) {
                        commission_the_project_end_rv.setVisibility(View.GONE);
                        commission_the_project_end_rl.setVisibility(View.VISIBLE);
                        Log.i("列表数据获取错误","错误"+e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

}
